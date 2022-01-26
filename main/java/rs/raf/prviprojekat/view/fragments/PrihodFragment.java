package rs.raf.prviprojekat.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import rs.raf.prviprojekat.R;
import rs.raf.prviprojekat.models.Prihod;
import rs.raf.prviprojekat.view.activities.EditPrihodActivity;
import rs.raf.prviprojekat.view.activities.MainActivity;
import rs.raf.prviprojekat.view.recycler.adapter.PrihodAdapter;
import rs.raf.prviprojekat.view.recycler.differ.PrihodDiffer;
import rs.raf.prviprojekat.view.viewpager.PagerAdapter;
import rs.raf.prviprojekat.viewmodels.RecyclerViewModel;
import timber.log.Timber;

import static rs.raf.prviprojekat.viewmodels.RecyclerViewModel.*;

public class PrihodFragment extends Fragment {

    private RecyclerView recyclerView;


    private RecyclerViewModel recyclerViewModel;
    private PrihodAdapter prihodAdapter;
    private static int idZaIzmenu;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public PrihodFragment(){
        super(R.layout.fragment_prihod);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pref = getActivity().getApplicationContext().getSharedPreferences("PREF_1", 0);
        editor = pref.edit();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewModel = new ViewModelProvider(requireActivity()).get(RecyclerViewModel.class);


        init(view);
    }
    private void init(View view) {
        initView(view);
        initListeners(view);
        initObservers(view);
        initRecycler(view);
    }
    private void initView(View view){

        recyclerView = view.findViewById(R.id.listRv);

    }

    private void initListeners(View view){

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK) {
            //error
        }
        if(requestCode == 123 && pref.getBoolean("nemaIzmene",false) == false){

            recyclerViewModel.izmeniPrihod(idZaIzmenu,pref.getString("naslov",null),pref.getString("kolicina",null),pref.getString("opis",null));

        }else{
            editor.putBoolean("nemaIzmene",false);
            editor.commit();
        }
    }

    private void initObservers(View view){
        recyclerViewModel.getPrihodi().observe(getViewLifecycleOwner(), prihods -> {

            prihodAdapter.submitList(prihods);

        });
    }
    private void initRecycler(View view){
        prihodAdapter = new PrihodAdapter(new PrihodDiffer(), prihod ->{
            ///PRIKAZ
            SharedPreferences pref = requireActivity().getApplicationContext().getSharedPreferences("PREF_1", 0);
            SharedPreferences.Editor editor = pref.edit();

            if(prihod.getFile() == null) {
                editor.putString("naslov", prihod.getNaslov());
                editor.putString("kolicina", prihod.getKolicina() + "");
                editor.putString("opis", prihod.getOpis());
                editor.putBoolean("prikaz", true);

                editor.commit();
            }else{
                editor.putString("naslov", prihod.getNaslov());
                editor.putString("kolicina", prihod.getKolicina() + "");
                editor.putString("opis", prihod.getOpis());
                editor.putBoolean("prikaz", true);
                editor.putBoolean("imaAudio",true);

                editor.commit();
            }

            Intent intent = new Intent(getActivity(), EditPrihodActivity.class);
            startActivity(intent);

            getActivity().finish();


            Toast.makeText(getActivity(), prihod.getId() + "",Toast.LENGTH_SHORT).show();
            return null;
        }, editPr ->{
            SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("PREF_1", 0);
            SharedPreferences.Editor editor = pref.edit();

            if(editPr.getFile() == null) {
                editor.putString("naslov", editPr.getNaslov());
                editor.putString("kolicina", editPr.getKolicina() + "");
                editor.putString("opis", editPr.getOpis());
                editor.putBoolean("prikaz", true);
                editor.putBoolean("izmenaPrihoda", true);

                editor.commit();
            }else{
                editor.putString("naslov", editPr.getNaslov());
                editor.putString("kolicina", editPr.getKolicina() + "");
                editor.putString("opis", editPr.getOpis());
                editor.putBoolean("prikaz", true);
                editor.putBoolean("imaAudio",true);
                editor.putBoolean("izmenaPrihoda", true);

                editor.commit();
            }
            idZaIzmenu = editPr.getId();

            Intent intent = new Intent(getActivity(), EditPrihodActivity.class);

            startActivityForResult(intent,123);


            return null;
        },deletePr ->{

            recyclerViewModel.deletePrihod(deletePr.getId());
//
//            LiveData<List<Prihod>> prihodi = recyclerViewModel.getPrihodi();
//            ArrayList<Prihod> prihods = new ArrayList<Prihod>(prihodi.getValue());
//
//            for(Prihod pr : prihods){
//                if(pr.getId() == deletePr.getId()){
//                    prihods.remove(pr);
//
//                    recyclerViewModel.getPrihodi().setValue(prihods);
//                }
//            }
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(prihodAdapter);
    }

}

