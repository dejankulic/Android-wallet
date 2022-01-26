package rs.raf.prviprojekat.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.prviprojekat.R;
import rs.raf.prviprojekat.view.viewpager.PagerAdapter;
import rs.raf.prviprojekat.viewmodels.RecyclerViewModel;

public class
MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RecyclerViewModel recyclerViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    private void init(){
        initViewPager();
        initNavigation();

    }

    private void initViewPager(){
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

    }
    private void initNavigation(){

        ((BottomNavigationView)findViewById(R.id.bottomNavigation)).setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.nav1: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_1,false); break;
                case R.id.nav2: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_2,false); break;
                case R.id.nav3: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_3,false); break;
                case R.id.nav4: viewPager.setCurrentItem(PagerAdapter.FRAGMENT_4,false); break;

            }
            return true;
        });
    }
}