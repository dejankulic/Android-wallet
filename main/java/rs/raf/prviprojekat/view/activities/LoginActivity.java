package rs.raf.prviprojekat.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rs.raf.prviprojekat.R;

public class LoginActivity extends AppCompatActivity {

    private EditText imeET;
    private EditText prezimeET;
    private EditText bankaET;
    private EditText passwordET;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        initView();

    }


    private void initView() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("PREF_0", 0);
        SharedPreferences.Editor editor = pref.edit();

        imeET = findViewById(R.id.ime);
        prezimeET = findViewById(R.id.prezime);
        bankaET = findViewById(R.id.banka);
        passwordET = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        if(pref.getBoolean("izmena", false)){
            imeET.setText(pref.getString("ime",null));
            prezimeET.setText(pref.getString("prezime",null));
            bankaET.setText(pref.getString("banka", null));

            passwordET.setVisibility(View.INVISIBLE);
            loginBtn.setText("Izmeni");
        }else{
            passwordET.setVisibility(View.VISIBLE);
            loginBtn.setText("Prijava");

        }
        initListeners(editor,pref);
    }

    private void initListeners(SharedPreferences.Editor editor,SharedPreferences pref) {
        loginBtn.setOnClickListener(v -> {


                    String ime = imeET.getText().toString();
                    String prezime = prezimeET.getText().toString();
                    String banka = bankaET.getText().toString();


                    editor.putString("ime", ime);
                    editor.putString("prezime", prezime);
                    editor.putString("banka", banka);

                    /*
                    OVIM FLEGOM SMO OMOGUCILI VISE STVARI
                    PRVA STVAR: AKO JE NEKO POLJE PRAZNO NECE SE UOPSTE POOKRETATI PROVERA ZA LOZINKU I TAKO NAM KORISNIK NE MOZE POBECI DALJE I OTICI SA PRAZNIM POLJEM
                    DRUGA STVAR: AKO JE NEKO POLJE PRAZNO A PRITOM I LOZINKA JE PRAZNA ILI NETACNA NECE IZBACIVATI DVA TOASTA VEC CE IZBACITI SAMO JEDAN DA SU IME IME,PREZIME, BANKA
                    PRAZNI DOK SE GOD NE POPUNE A TEK ONDA MOZE IZBACITI TOAST ZA POGRESNU LOZINKU
                     */
                    int flag = 0;
                    if(!pref.getBoolean("izmena",false)){
                        if (ime.isEmpty()){
                            Toast errorToast = Toast.makeText(this, "Polje ime ne sme biti prazno", Toast.LENGTH_SHORT);
                            errorToast.show();
                            flag = 1;
                        }else if(prezime.isEmpty()){
                            Toast errorToast = Toast.makeText(this, "Polje prezime ne sme biti prazno", Toast.LENGTH_SHORT);
                            errorToast.show();
                            flag = 1;
                        }else if(banka.isEmpty()){
                            Toast errorToast = Toast.makeText(this, "Polje banka ne sme biti prazno", Toast.LENGTH_SHORT);
                            errorToast.show();
                            flag = 1;
                        }

                        String password = passwordET.getText().toString();
                        editor.putString("password", password);

                        if(password.equals("12345") && flag == 0){
                            editor.putBoolean("ulogovan", true);
                            Intent intent = new Intent(this,MainActivity.class);
                            editor.commit();
                            startActivity(intent);

                            finish();
                        }else if(flag == 0){
                        Toast errorToast = Toast.makeText(this, "Pogresna lozinka", Toast.LENGTH_SHORT);
                        errorToast.show();
                        }

                    editor.commit();
                    }else{
                        editor.putString("ime", ime);
                        editor.putString("prezime", prezime);
                        editor.putString("banka", banka);

                        editor.putBoolean("izmena",false);
                        editor.commit();
                        Intent intent = new Intent(this,MainActivity.class);
                        startActivity(intent);
                        finish();


                    }

                }

        );
//    }
    }
}