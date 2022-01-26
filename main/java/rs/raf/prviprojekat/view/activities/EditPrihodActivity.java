package rs.raf.prviprojekat.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import rs.raf.prviprojekat.R;
import timber.log.Timber;

public class EditPrihodActivity extends AppCompatActivity {

    private TextView naslovEt;
    private TextView opisEt;
    private TextView kolicinaEt;
    private Button izmeniBtn;
    private Button odustaniBtn;

    private ImageView playBtn;
    private Handler handler = new Handler();
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private Runnable runnable;


    private void initPlayer() {
        // Initialize media player
        if(rs.raf.prviprojekat.view.fragments.AddFragment.file != null)
            mediaPlayer = MediaPlayer.create(this, Uri.fromFile(rs.raf.prviprojekat.view.fragments.AddFragment.file));

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Get duration of media player
        int duration = mediaPlayer.getDuration();
        // Convert millisecond to minute and second
        //String sDuration = convertFormat(duration);
        // Set duration on text view
        //playerDuration.setText(sDuration);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prihod);
        init();

    }

    private void init(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("PREF_1", 0);
        SharedPreferences.Editor editor = pref.edit();

        initView();
        initListeners();
        if(pref.getBoolean("prikaz", false)) initPrikaz();
        if(pref.getBoolean("izmenaPrihoda",false)) initIzmenaPrihoda();
        if(pref.getBoolean("izmenaRashoda",false)) initIzmenaRashoda();
    }

    private void initIzmenaRashoda(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("PREF_1", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("izmenaRashoda",false);
        editor.commit();

        Intent intent = getIntent();

        izmeniBtn.setVisibility(View.VISIBLE);
        odustaniBtn.setVisibility(View.VISIBLE);

        if(pref.getBoolean("imaAudio",false)) {
            editor.putBoolean("imaAudio",false);
            editor.commit();
            opisEt.setVisibility(View.GONE);
            playBtn.setVisibility(View.VISIBLE);
            naslovEt.setText(pref.getString("naslov", null));
            kolicinaEt.setText(pref.getString("kolicina", null));

        }else{
            opisEt.setVisibility(View.VISIBLE);
            naslovEt.setText(pref.getString("naslov", null));
            kolicinaEt.setText(pref.getString("kolicina", null));
            opisEt.setText(pref.getString("opis", null));

        }
        izmeniBtn.setOnClickListener(v-> {

            editor.putString("naslov", naslovEt.getText().toString());
            editor.putString("kolicina", kolicinaEt.getText().toString());
            editor.putString("opis", opisEt.getText().toString());

            editor.commit();

            setResult(Activity.RESULT_OK);

            finish();


        });
        odustaniBtn.setOnClickListener(v->{
            editor.putBoolean("nemaIzmene",true);
            editor.commit();

            setResult(Activity.RESULT_OK);

            finish();

        });

    }
    private void initIzmenaPrihoda(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("PREF_1", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("izmenaPrihoda",false);
        editor.commit();

        Intent intent = getIntent();

        izmeniBtn.setVisibility(View.VISIBLE);
        odustaniBtn.setVisibility(View.VISIBLE);

        if(pref.getBoolean("imaAudio",false)) {
            editor.putBoolean("imaAudio",false);
            editor.commit();
            opisEt.setVisibility(View.GONE);
            playBtn.setVisibility(View.VISIBLE);
            naslovEt.setText(pref.getString("naslov", null));
            kolicinaEt.setText(pref.getString("kolicina", null));

        }else{
            opisEt.setVisibility(View.VISIBLE);
            naslovEt.setText(pref.getString("naslov", null));
            kolicinaEt.setText(pref.getString("kolicina", null));
            opisEt.setText(pref.getString("opis", null));

        }
        izmeniBtn.setOnClickListener(v-> {

            editor.putString("naslov", naslovEt.getText().toString());
            editor.putString("kolicina", kolicinaEt.getText().toString());
            editor.putString("opis", opisEt.getText().toString());

            editor.commit();

            setResult(Activity.RESULT_OK);

            finish();


        });
        odustaniBtn.setOnClickListener(v->{

            editor.putBoolean("nemaIzmene",true);
            editor.commit();
            setResult(Activity.RESULT_OK);

            finish();

        });

    }
    private void initPrikaz(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("PREF_1", 0);
        SharedPreferences.Editor editor = pref.edit();


        if(pref.getBoolean("imaAudio",false)) {
            editor.putBoolean("imaAudio",false);
            editor.commit();
            naslovEt.setText(pref.getString("naslov", null));
            kolicinaEt.setText(pref.getString("kolicina", null));
            opisEt.setVisibility(View.GONE);
            izmeniBtn.setVisibility(View.GONE);
            odustaniBtn.setVisibility(View.GONE);
            playBtn.setVisibility(View.VISIBLE);
        }else{
            naslovEt.setText(pref.getString("naslov", null));
            kolicinaEt.setText(pref.getString("kolicina", null));
            opisEt.setText(pref.getString("opis", null));
            izmeniBtn.setVisibility(View.GONE);
            odustaniBtn.setVisibility(View.GONE);
            playBtn.setVisibility(View.GONE);
        }

    playBtn.setOnClickListener(v -> {
        Toast playtoast = Toast.makeText(this, "Play dugme", Toast.LENGTH_SHORT);
        playtoast.show();
    });
    }


        private void initView(){

        playBtn = findViewById(R.id.playBtn);
        naslovEt = findViewById(R.id.naslovIzmenaEt);
        opisEt = findViewById(R.id.opisIzmena);
        kolicinaEt = findViewById(R.id.kolicinaIzmenaEt);
        izmeniBtn = findViewById(R.id.izmeniPRBtn);
        odustaniBtn = findViewById(R.id.odustaniPRBtn);

    }
    private void initListeners(){


    }
}