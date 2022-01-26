package rs.raf.prviprojekat.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

import rs.raf.prviprojekat.R;
import rs.raf.prviprojekat.models.Prihod;
import rs.raf.prviprojekat.models.Rashod;
import rs.raf.prviprojekat.view.activities.EditPrihodActivity;
import rs.raf.prviprojekat.view.recycler.adapter.RashodAdapter;
import rs.raf.prviprojekat.view.recycler.differ.RashodDiffer;
import rs.raf.prviprojekat.viewmodels.RecyclerViewModel;
import timber.log.Timber;

public class RashodFragment extends Fragment {

    private RecyclerView recyclerView;

    private RecyclerViewModel recyclerViewModel;
    private RashodAdapter rashodAdapter;
    private static int idZaIzmenu;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public RashodFragment(){
        super(R.layout.fragment_rashod);

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
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK) {
            //error
        }
        if(requestCode == 123 && pref.getBoolean("nemaIzmene",false)==false){
            recyclerViewModel.izmeniRashod(idZaIzmenu,pref.getString("naslov",null),pref.getString("kolicina",null),pref.getString("opis",null));

        }else{
            editor.putBoolean("nemaIzmene",false);
            editor.commit();
        }
    }

    private void initView(View view){

        recyclerView = view.findViewById(R.id.listRashodRv);

    }
    private void initListeners(View view){

    }
    private void initObservers(View view){
        recyclerViewModel.getRashodi().observe(getViewLifecycleOwner(), rashods -> {

            rashodAdapter.submitList(rashods);

        });
    }
    private void initRecycler(View view){
        rashodAdapter = new RashodAdapter(new RashodDiffer(),rashod -> {

            return null;
        },editRas ->{


            SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("PREF_1", 0);
            SharedPreferences.Editor editor = pref.edit();

            if(editRas.getFile() == null) {
                editor.putString("naslov", editRas.getNaslov());
                editor.putString("kolicina", editRas.getKolicina() + "");
                editor.putString("opis", editRas.getOpis());
                editor.putBoolean("prikaz", true);
                editor.putBoolean("izmenaPrihoda", true);

                editor.commit();
            }else{
                editor.putString("naslov", editRas.getNaslov());
                editor.putString("kolicina", editRas.getKolicina() + "");
                editor.putString("opis", editRas.getOpis());
                editor.putBoolean("prikaz", true);
                editor.putBoolean("imaAudio",true);
                editor.putBoolean("izmenaPrihoda", true);

                editor.commit();
            }
            idZaIzmenu = editRas.getId();

            Intent intent = new Intent(getActivity(), EditPrihodActivity.class);

            startActivityForResult(intent,123);


            return null;
        },deleteRas ->{
            recyclerViewModel.deleteRashod(deleteRas.getId());
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(rashodAdapter);
    }

}

