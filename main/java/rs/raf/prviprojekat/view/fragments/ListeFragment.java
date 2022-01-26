package rs.raf.prviprojekat.view.fragments;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import rs.raf.prviprojekat.R;
import rs.raf.prviprojekat.view.recycler.adapter.PrihodAdapter;
import rs.raf.prviprojekat.view.recycler.differ.PrihodDiffer;
import rs.raf.prviprojekat.view.viewpager.PagerAdapter;
import rs.raf.prviprojekat.view.viewpager.PagerAdapterTab;
import rs.raf.prviprojekat.viewmodels.RecyclerViewModel;

public class ListeFragment extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ListeFragment(){
        super(R.layout.fragment_liste);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
    private void init(View view){
        initView(view);
        initTabs(view);
    }

    private void initView(View view){
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
    }
    private void initTabs(View view){

        FragmentManager cfManager = getChildFragmentManager();
        viewPager.setAdapter(new PagerAdapterTab(cfManager));

        tabLayout.setupWithViewPager(viewPager);
    }


}
