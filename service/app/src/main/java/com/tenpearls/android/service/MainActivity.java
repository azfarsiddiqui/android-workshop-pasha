package com.tenpearls.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    Button mBtnInvokeService;
    TextView mTxtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        setListeners();
    }

    private void initUI() {

        setContentView(R.layout.activity_main);

        mBtnInvokeService = (Button) findViewById(R.id.btnInvokeService);
        mTxtTime = (TextView) findViewById(R.id.txtTime);
    }

    private void setListeners() {

        mBtnInvokeService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startService();
            }
        });

    }

    private void startService() {

        registerReceiver(mReceiver, new IntentFilter(TimeQueryService.KEY_ID));
        Intent intent = new Intent(this, TimeQueryService.class);
        startService(intent);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String date = intent.getStringExtra(TimeQueryService.KEY_TIME);
            mTxtTime.setText(date);
        }
    };

}
