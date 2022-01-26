package rs.raf.prviprojekat.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private String sifra = "12345";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        preferences();
    }
    public void preferences(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("PREF_0", 0);
        SharedPreferences.Editor editor = pref.edit();

        if(pref.getBoolean("ulogovan", false)){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}