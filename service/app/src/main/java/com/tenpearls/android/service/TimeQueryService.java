package com.tenpearls.android.service;

import android.app.IntentService;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeQueryService extends IntentService {

    public static final String KEY_ID   = "my_id";
    public static final String KEY_TIME = "time";

    public TimeQueryService() {
        super("TimeQueryService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        String date = getTimeInStringFormat();
        publishResults(date);
    }

    private String getTimeInStringFormat() {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("KK:mm:ss a");
        return dateFormat.format(date);
    }

    private void publishResults(String date) {

        Intent intent = new Intent(KEY_ID);
        intent.putExtra(KEY_TIME, date);

        sendBroadcast(intent);
    }
}
