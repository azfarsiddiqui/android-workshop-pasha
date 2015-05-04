package com.tenpearls.android.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Button mBtnRegisterReceiver;
    Button mBtnUnregisterReceiver;
    LowBatteryReceiver mLowBatteryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        setListeners();
    }

    private void initUI() {

        setContentView(R.layout.activity_main);

        mBtnRegisterReceiver = (Button) findViewById(R.id.btnRegisterReceiver);
        mBtnUnregisterReceiver = (Button) findViewById(R.id.btnUnregisterReceiver);
    }

    private void setListeners() {

        mBtnRegisterReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver();
            }
        });

        mBtnUnregisterReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver();
            }
        });
    }

    private void registerReceiver() {

        mLowBatteryReceiver = new LowBatteryReceiver();
        registerReceiver(mLowBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_LOW));
        mBtnRegisterReceiver.setEnabled(false);
        mBtnUnregisterReceiver.setEnabled(true);
    }

    private void unregisterReceiver() {

        unregisterReceiver(mLowBatteryReceiver);
        mLowBatteryReceiver = null;
        mBtnRegisterReceiver.setEnabled(true);
        mBtnUnregisterReceiver.setEnabled(false);
    }

    private class LowBatteryReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context, "Low battery triggered..", Toast.LENGTH_SHORT).show();
        }
    }
}
