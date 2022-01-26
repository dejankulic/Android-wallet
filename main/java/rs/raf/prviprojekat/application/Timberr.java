package rs.raf.prviprojekat.application;

import android.app.Application;

import timber.log.Timber;

public class Timberr extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }

}
