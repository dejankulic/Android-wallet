package rs.raf.prviprojekat.viewmodels;

import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rs.raf.prviprojekat.application.Timberr;
import rs.raf.prviprojekat.models.Prihod;
import rs.raf.prviprojekat.models.Rashod;
import rs.raf.prviprojekat.view.activities.EditPrihodActivity;
import rs.raf.prviprojekat.view.activities.LoginActivity;
import rs.raf.prviprojekat.view.activities.MainActivity;
import rs.raf.prviprojekat.view.fragments.PrihodFragment;
import timber.log.Timber;

import static androidx.core.content.ContextCompat.startActivity;

public class RecyclerViewModel extends ViewModel {

    public static int counter = 101;

    private final MutableLiveData<List<Prihod>> prihodi = new MutableLiveData<>();
    private final ArrayList<Prihod> prihodList = new ArrayList<>();

    private final MutableLiveData<List<Rashod>> rashodi = new MutableLiveData<>();
    private final ArrayList<Rashod> rashodList = new ArrayList<>();

    public RecyclerViewModel(){

        for(int i = 0; i <=3;i++){


            Prihod pr = new Prihod(i,"Test", "opis",25);
            prihodList.add(pr);
        }

        for(int i = 0; i <=3;i++){


            Rashod ras = new Rashod(i,"Test","opis1", 10);
            rashodList.add(ras);
        }

        ArrayList<Prihod> prihodToSubmit = new ArrayList<>(prihodList);
        prihodi.setValue(prihodToSubmit);

        ArrayList<Rashod> rashodToSubmit = new ArrayList<>(rashodList);
        rashodi.setValue(rashodToSubmit);


    }
    public void addPrihod(int id, String naslov, String opis,int kolicina) {
        Prihod pr = new Prihod(id, naslov,opis, kolicina);
        prihodList.add(pr);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodList);
        prihodi.setValue(listToSubmit);


    }public void addPrihodWithSound(int id, String naslov, File file, int kolicina) {
        Prihod pr = new Prihod(id, naslov,file, kolicina);
        prihodList.add(pr);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodList);
        prihodi.setValue(listToSubmit);


    }
    public void addRashod(int id, String naslov, String opis,int kolicina) {
        Rashod ras = new Rashod(id, naslov,opis, kolicina);
        rashodList.add(ras);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodList);
        rashodi.setValue(listToSubmit);
    }
    public void deletePrihod(int id){

        Prihod pr1 = null;
            for(Prihod pr : prihodList){
                if(pr.getId() == id){
                    pr1 = pr;
                }

            }

        ArrayList<Prihod> listToRemove = new ArrayList<Prihod>();
        prihodList.remove(pr1);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodList);
        prihodi.setValue(listToSubmit);
    }

    public void deleteRashod(int id){

        Rashod ras1 = null;
        for(Rashod ras : rashodList){
            if(ras.getId() == id){
                ras1 = ras;
            }

        }

        rashodList.remove(ras1);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodList);
        rashodi.setValue(listToSubmit);
    }


    public void izmeniRashod(int idZaIzmenu,String naslov,String kolicina, String opis) {

        Rashod ras1 = new Rashod(5,"naslov", "opis", 50);

        for(Rashod ras : rashodList){
            if(ras.getId() == idZaIzmenu){
                ras.setNaslov(naslov);
                ras.setKolicina(Integer.parseInt(kolicina));
                ras.setOpis(opis);
            }

        }

        rashodList.set(idZaIzmenu,ras1);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodList);
        rashodi.setValue(listToSubmit);

    }

    public void izmeniPrihod(int idZaIzmenu,String naslov,String kolicina, String opis) {
       // Prihod ras1 = prihodList.get(idZaIzmenu);

        Prihod pr1 = new Prihod(5,"naslov", "opis", 50);

        Timber.e(idZaIzmenu + "IDDDDDDD");
        for(Prihod pr : prihodList){
            if(pr.getId() == idZaIzmenu){
                pr1.setNaslov(naslov);
                pr1.setKolicina(Integer.parseInt(kolicina));
                pr1.setOpis(opis);
            }

        }

        prihodList.set(idZaIzmenu,pr1);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodList);
        prihodi.setValue(listToSubmit);

    }

    public LiveData<List<Prihod>> getPrihodi(){

        return prihodi;
    }



    public LiveData<List<Rashod>> getRashodi(){

        return rashodi;
    }


}
