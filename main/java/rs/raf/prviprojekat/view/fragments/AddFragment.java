package rs.raf.prviprojekat.view.fragments;

import android.Manifest;
import android.Manifest.permission;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Image;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.io.IOException;

import rs.raf.prviprojekat.R;
import rs.raf.prviprojekat.viewmodels.RecyclerViewModel;
import timber.log.Timber;

public class AddFragment extends Fragment {
    private Spinner dropdown;
    private EditText naslov;
    private EditText kolicina;
    private EditText opis;
    private CheckBox audiocb;
    private Button addBtn;
    private ImageView micBtn;
    private ImageView recordingBtn;

    public static File file;

    private View view;

    private MediaRecorder mediaRecorder;

    private final int PERMISSION_ALL =1;
    private final String[] PERMISSIONS = {
            permission.RECORD_AUDIO,
            permission.WRITE_EXTERNAL_STORAGE
    };

    RecyclerViewModel recyclerViewModel;
    private static int idPrihod = 4;
    private static int idRashod = 4;

    public AddFragment(){
        super(R.layout.fragment_add
        );
    }

    @Override
    public void onViewCreated(@NonNull View view1, @Nullable Bundle savedInstanceState) {
        view = view1;
        super.onViewCreated(view, savedInstanceState);

        if(hasPermissions(requireActivity(),PERMISSIONS)){
            init();
        }else{
            requestPermissions(PERMISSIONS, PERMISSION_ALL);
        }
        recyclerViewModel = new ViewModelProvider(getActivity()).get(RecyclerViewModel.class);

        init();
    }
    private void init() {
        initView();
        initListeners();
        initRecorder();
    }
    private boolean hasPermissions(Context context, String... permissions){
        if(context != null && permissions != null){
            for(String permission : permissions){
                if(ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_ALL){
            if(grantResults.length > 0){
                StringBuilder permissionsDenied = new StringBuilder();
                for(int i=0; i<grantResults.length;i++){
                    if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                        permissionsDenied.append("\n").append(permissions[i]);
                    }
                }
                if(permissions.toString().length() == 0){
                    initRecorder();
                }else{
                    Toast.makeText(getActivity(), "Missing permissions!" + permissionsDenied.toString(), Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
            }
        }
    }

    private void initRecorder(){
        File folder = new File(getActivity().getFilesDir(), "sounds");
        if(!folder.exists()) folder.mkdir();
        file = new File(folder, "record.3gp");
        initListeners();
    }
    private void initMediaRecorder(File file){
        mediaRecorder = new MediaRecorder();
        //postavljamo parametre za mediaRecorder
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(file);
    }
    private void initView(){
        dropdown = view.findViewById(R.id.spinner);
        naslov = view.findViewById(R.id.naslovUnosEt);
        kolicina = view.findViewById(R.id.kolicinaUnosEt);
        opis = view.findViewById(R.id.opis);
        audiocb = view.findViewById(R.id.audioCb);
        addBtn = view.findViewById(R.id.unosBtn);
        micBtn = view.findViewById(R.id.micBtn);
        recordingBtn = view.findViewById(R.id.recordingBtn);

        String[] items = new String[]{"Rashod", "Prihod"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

    }
    private void initListeners(){

        addBtn.setOnClickListener(v -> {
            String pr_ras = dropdown.getSelectedItem().toString();
            String nasl = naslov.getText().toString();
            int kol = Integer.parseInt(kolicina.getText().toString());
            String op = opis.getText().toString();


            if(pr_ras.equals("Prihod")){
                if(file == null) {
                    recyclerViewModel.addPrihod(idPrihod++, nasl, op, kol);
                }else{
                    recyclerViewModel.addPrihodWithSound(idPrihod++, nasl, file, kol);
                }
            }else if(pr_ras.equals("Rashod")){
                     recyclerViewModel.addRashod(idPrihod++,nasl,
                        op, kol);
            }
        });
        audiocb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (audiocb.isChecked()) {
                    opis.setVisibility(View.GONE);
                    micBtn.setVisibility(View.VISIBLE);
                } else {
                    micBtn.setVisibility(View.GONE);
                    opis.setVisibility(View.VISIBLE);
                }

            }
        });
        micBtn.setOnClickListener(v ->{
            try{
                micBtn.setVisibility(View.GONE);
                recordingBtn.setVisibility(View.VISIBLE);
                initMediaRecorder(file);
                mediaRecorder.prepare();
                mediaRecorder.start();

            }catch (IOException e){
                e.printStackTrace();
            }
        });
        recordingBtn.setOnClickListener(v -> {
            micBtn.setVisibility(View.VISIBLE);
            recordingBtn.setVisibility(View.GONE);
            // Zaustavljamo snimanje i oslobadjamo resurse
            // Metodom stop() se snimljeni resurs cuva u fajlu koji smo prosledili pri inicijalizaciji mediaRecorder-a
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        });
    }

}
