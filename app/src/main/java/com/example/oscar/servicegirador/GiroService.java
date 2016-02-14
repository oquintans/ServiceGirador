package com.example.oscar.servicegirador;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class GiroService extends Service {

    private static final String TAG = "GiroService";
    private boolean isRunning = false;
    Context ct;

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");
        isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service onStartCommand");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            createIntentResult("LANDSCAPE");
        } else {
            createIntentResult("VERTICAL");
        }
        Log.i(TAG, "Service running");

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        Log.i(TAG, "Service onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    public void createIntentResult(String o) {
        Intent intent = new Intent(this, ResultServiceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("orientation", o);
        startActivity(intent);
    }
}
