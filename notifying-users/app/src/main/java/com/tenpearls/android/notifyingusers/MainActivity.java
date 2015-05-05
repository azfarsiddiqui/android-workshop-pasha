package com.tenpearls.android.notifyingusers;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button mBtnShowToast;
    Button mBtnShowAlertDialog;
    Button mBtnShowProgressDialog;
    Button mBtnShowNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        setListeners();
    }

    private void initUI() {

        setContentView(R.layout.activity_main);

        mBtnShowToast = (Button) findViewById(R.id.btnShowToast);
        mBtnShowAlertDialog = (Button) findViewById(R.id.btnShowAlertDialog);
        mBtnShowProgressDialog = (Button) findViewById(R.id.btnShowProgressDialog);
        mBtnShowNotification = (Button) findViewById(R.id.btnShowNotification);
    }

    private void setListeners() {

        mBtnShowToast.setOnClickListener(this);
        mBtnShowAlertDialog.setOnClickListener(this);
        mBtnShowProgressDialog.setOnClickListener(this);
        mBtnShowNotification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnShowToast:
                onShowToast();
                break;
            case R.id.btnShowAlertDialog:
                onShowAlertDialog();
                break;
            case R.id.btnShowProgressDialog:
                onShowProgressDialog();
                break;
            case R.id.btnShowNotification:
                onShowNotification();
                break;
            default:
                Log.d("OnClick", "Unhandled case");
        }
    }

    private void onShowToast() {

        Toast.makeText(this, "We're at the NEST I/O !!!", Toast.LENGTH_SHORT).show();
    }

    private void onShowAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("CAUTION!");
        builder.setMessage("Are you sure you want to delete this record?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(MainActivity.this, "Operation cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }

    private void onShowProgressDialog() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Fetching your profile...");
        progressDialog.show();
    }

    private void onShowNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Notification success");
        builder.setContentText("This is the notification content");

        int notificationId = 1;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, builder.build());
    }
}
