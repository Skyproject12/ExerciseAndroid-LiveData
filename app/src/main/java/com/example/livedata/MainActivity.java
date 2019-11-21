package com.example.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mLiveDataTimerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initial viewmodel
        mLiveDataTimerViewModel= ViewModelProviders.of(this).get(MainViewModel.class);

        subsribe();

    }

    private void subsribe(){
        // tampilkan data terbaru
        final Observer<Long> elapsedTimeObserver= new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                // mengeset data berdasarkan data dari along yang dikirim memalui viewModel
                String newText= MainActivity.this.getResources().getString(R.string.seconds, aLong);
                ((TextView) findViewById(R.id.timer_textview)).setText(newText);
            }
        };
        // mengambil data terbaru dari mutable liveData
        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver);

    }
}
