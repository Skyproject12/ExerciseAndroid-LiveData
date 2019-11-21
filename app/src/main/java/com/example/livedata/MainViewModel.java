package com.example.livedata;

import android.os.SystemClock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class MainViewModel extends ViewModel {

    private static final int ONE_SECOND=1000;
    private long mInitialTime;
    private MutableLiveData<Long> mElapsedTime= new MutableLiveData<>();

    public MainViewModel(){
        // add the time realtime
        mInitialTime= SystemClock.elapsedRealtime();
        // initial timer
        Timer timer= new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // mengambil waktu realtime dalam format second
                final long newValue= (SystemClock.elapsedRealtime()-mInitialTime)/1000;
                // post value of time with format second
                mElapsedTime.postValue(newValue);
            }
        }, ONE_SECOND, ONE_SECOND);
    }

    LiveData<Long> getElapsedTime(){
        // menampilkan data terbaru setelah data di post
        return mElapsedTime;

    }
}
