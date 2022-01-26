package rs.raf.prviprojekat.view.fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import rs.raf.prviprojekat.R;
import rs.raf.prviprojekat.models.Prihod;
import rs.raf.prviprojekat.models.Rashod;
import rs.raf.prviprojekat.viewmodels.RecyclerViewModel;
import timber.log.Timber;

public class StanjeFragment extends Fragment {

    private RecyclerViewModel recyclerViewModel;
    private TextView prihodTv;
    private TextView rashodTv;
    private TextView razlikaTv;
    private TextView razlikaBezStringaTv;

    public StanjeFragment(){
        super(R.layout.fragment_stanje);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerViewModel = new ViewModelProvider(getActivity()).get(RecyclerViewModel.class);
        init(view);

    }
    private void init(View view){
        initView(view);
    }

    private int primer = 1;

    private void initView(View view){
        prihodTv = view.findViewById(R.id.prihodTV);
        rashodTv = view.findViewById(R.id.rashodTV);
        razlikaTv = view.findViewById(R.id.razlikaTV);
        razlikaBezStringaTv = view.findViewById(R.id.razlikaBezStringaTV);

        izracunajPrihod();
        izracunajRashod();
        razlika();
    }
//    private void izracunajPrihod(){
//
//    }
//    private void izracunajRashod(){
//
//    }


private void razlika(){
    recyclerViewModel.getRashodi().observe(getViewLifecycleOwner(), rashodi ->{

        int counter = 0;
        for(Rashod ras : rashodi){
            counter += ras.getKolicina();
        }
        final int test = counter;

        recyclerViewModel.getPrihodi().observe(getViewLifecycleOwner(), prihodi ->{

            int counter1 = 0;
            for(Prihod pr : prihodi){
                counter1 += pr.getKolicina();
            }
            int test2 = test;

            Timber.e(test2 +"test99");
            counter1 -= test2;

            Timber.e(counter1 +"KANTERR");
            razlikaTv.setText(counter1 +"");

            if(counter1>0) {
                razlikaBezStringaTv.setTextColor(Color.parseColor("#12F846"));
                razlikaTv.setTextColor(Color.parseColor("#12F846"));
            }else if(counter1 == 0){
                razlikaBezStringaTv.setTextColor(Color.parseColor("#FF000000"));
                razlikaTv.setTextColor(Color.parseColor("#FF000000"));
            }else if(counter1 < 0){
                razlikaBezStringaTv.setTextColor(Color.parseColor("#FF0000"));
                razlikaTv.setTextColor(Color.parseColor("#FF0000"));
            }
        });
    });

}
    private void izracunajRashod(){
        recyclerViewModel.getRashodi().observe(getViewLifecycleOwner(), rashodi ->{

            SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("PREF_0", 0);
            SharedPreferences.Editor editor = pref.edit();

            int counter = 0;
            for(Rashod ras : rashodi){
                counter += ras.getKolicina();
            }

            editor.putInt("rashod", counter);
            Timber.e(counter +" OVO JE RASHOD");
//            if(pref.getBoolean("dodatrashod", false)){
//                editor.putBoolean("dodatrashod", false);
//
//                int elemZaDodavanje = rashodi.get(rashodi.size()-1).getKolicina();
//                int trenutni = Integer.parseInt(razlikaTv.getText().toString());
//                trenutni -= elemZaDodavanje;
//                razlikaTv.setText(trenutni + "");
//
//                if(trenutni>0) {
//                    razlikaBezStringaTv.setTextColor(Color.parseColor("#12F846"));
//                    razlikaTv.setTextColor(Color.parseColor("#12F846"));
//                }else if(trenutni == 0){
//                    razlikaBezStringaTv.setTextColor(Color.parseColor("#FF000000"));
//                    razlikaTv.setTextColor(Color.parseColor("#FF000000"));
//                }else if(trenutni < 0){
//                    razlikaBezStringaTv.setTextColor(Color.parseColor("#FF0000"));
//                    razlikaTv.setTextColor(Color.parseColor("#FF0000"));
//                }
//
//            }
            rashodTv.setText(counter + "");
        });

    }

    private void izracunajPrihod(){
        recyclerViewModel.getPrihodi().observe(getViewLifecycleOwner(), prihodii ->{


            int counter = 0;
            for(Prihod pr : prihodii){
                   counter += pr.getKolicina();
            }


            //            if(pref.getBoolean("dodatprihod", false)) {
//                editor.putBoolean("dodatprihod", false);
//                int elemZaDodavanje = prihodii.get(prihodii.size() - 1).getKolicina();
//                Timber.e(razlikaTv.getText().toString());
//                int trenutni = Integer.parseInt(razlikaTv.getText().toString());
//                trenutni += elemZaDodavanje;
//
//                razlikaTv.setText(trenutni + "");
//                if(trenutni>0) {
//                    razlikaBezStringaTv.setTextColor(Color.parseColor("#12F846"));
//                    razlikaTv.setTextColor(Color.parseColor("#12F846"));
//                }else if(trenutni == 0){
//                    razlikaBezStringaTv.setTextColor(Color.parseColor("#FF000000"));
//                    razlikaTv.setTextColor(Color.parseColor("#FF000000"));
//                }else if(trenutni < 0){
//                    razlikaBezStringaTv.setTextColor(Color.parseColor("#FF0000"));
//                    razlikaTv.setTextColor(Color.parseColor("#FF0000"));
//                }
//            }
            prihodTv.setText(counter + "");

        });
    }
//
//    private void razlika(){
//        LiveData<List<Prihod>> prihodi = recyclerViewModel.getPrihodi();
//        ArrayList<Prihod> prihods = new ArrayList<Prihod>(prihodi.getValue());
//        int counter = 0;
//        for(Prihod pr : prihods){
//            counter += pr.getKolicina();
//        }
//        LiveData<List<Rashod>> rashodi = recyclerViewModel.getRashodi();
//        ArrayList<Rashod> rashods = new ArrayList<Rashod>(rashodi.getValue());
//        int counter1 = 0;
//        for(Rashod ras : rashods){
//            counter1 += ras.getKolicina();
//        }
//        int resenje = 0;
//        resenje = (counter -= counter1);
//        razlikaTv.setText(Integer.toString(resenje));
//        if(resenje>0) {
//            razlikaBezStringaTv.setTextColor(Color.parseColor("#12F846"));
//            razlikaTv.setTextColor(Color.parseColor("#12F846"));
//        }else if(resenje == 0){
//            razlikaBezStringaTv.setTextColor(Color.parseColor("#FF000000"));
//            razlikaTv.setTextColor(Color.parseColor("#FF000000"));
//        }else if(resenje < 0){
//            razlikaBezStringaTv.setTextColor(Color.parseColor("#FF0000"));
//            razlikaTv.setTextColor(Color.parseColor("#FF0000"));
//        }

    //}
}
