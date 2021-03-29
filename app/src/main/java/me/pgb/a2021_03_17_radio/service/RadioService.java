package me.pgb.a2021_03_17_radio.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class RadioService extends Service {
    //private final IBinder binder = new LocalBinder();
    private final Random mGenerator = new Random();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class localBinder extends Binder {
        public RadioService getService(){
            return RadioService.this;
        }
    }

    /*
    @Override
    public IBinder onBind(Intent intent){
        return binder;
    }


     */
    public int getRandomNumber(){
        return mGenerator.nextInt(100);
    }
}
