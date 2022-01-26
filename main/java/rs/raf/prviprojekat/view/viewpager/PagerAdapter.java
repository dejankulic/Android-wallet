package rs.raf.prviprojekat.view.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.prviprojekat.view.fragments.AddFragment;
import rs.raf.prviprojekat.view.fragments.ListeFragment;
import rs.raf.prviprojekat.view.fragments.PrihodFragment;
import rs.raf.prviprojekat.view.fragments.ProfilFragment;
import rs.raf.prviprojekat.view.fragments.StanjeFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 4;
    public static final int FRAGMENT_1 = 0;
    public static final int FRAGMENT_2 = 1;
    public static final int FRAGMENT_3 = 2;
    public static final int FRAGMENT_4 = 3;

    public PagerAdapter(@NonNull FragmentManager fm){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch(position){
            case FRAGMENT_1: fragment = new StanjeFragment(); break;
            case FRAGMENT_2: fragment = new AddFragment(); break;
            case FRAGMENT_3: fragment = new ListeFragment(); break;
            default:
                fragment = new ProfilFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case FRAGMENT_1: return "1";
            case FRAGMENT_2: return "2";
            case FRAGMENT_3: return "3";
            default: return"4";
        }
    }
}
