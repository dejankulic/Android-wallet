package rs.raf.prviprojekat.view.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rs.raf.prviprojekat.R;
import rs.raf.prviprojekat.view.activities.LoginActivity;

public class ProfilFragment extends Fragment {
    private TextView ime;
    private TextView prezime;
    private TextView banka;
    private TextView lozinkaLogin;
    private Button loginBtn;

    public ProfilFragment(){
        super(R.layout.fragment_profil);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    //    initPreferences();
        init(view);
    }

    private void init(View view){
        SharedPreferences pref = getActivity().getSharedPreferences("PREF_0", 0);
        SharedPreferences.Editor editor = pref.edit();

        ime = view.findViewById(R.id.imeTV);
        prezime = view.findViewById(R.id.prezimeTV);
        banka = view.findViewById(R.id.bankaTV);
//        lozinkaLogin = (TextView)getActivity().findViewById(R.id.password);
//        loginBtn = view.findViewById(R.id.loginBtn);

        ime.setText(pref.getString("ime", null));
        prezime.setText(pref.getString("prezime", null));
        banka.setText(pref.getString("banka", null));

        initListeners(view,editor);
    }
    private void initListeners(View view, SharedPreferences.Editor editor){

        view.findViewById(R.id.izmeniBtn).setOnClickListener(v ->{
//            lozinkaLogin.setVisibility(View.GONE);
//            loginBtn.setText("Izmeni");
            editor.putBoolean("izmena", true);
            editor.commit();

            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
        view.findViewById(R.id.odjavaBtn).setOnClickListener(v -> {
            editor.putBoolean("ulogovan", false);
            editor.commit();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }
}
