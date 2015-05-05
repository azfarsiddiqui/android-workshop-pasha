package com.tenpearls.android.displayingdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button mBtnListViewSimple;
    Button mBtnListViewCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        setListeners();
    }

    private void initUI() {

        setContentView(R.layout.activity_main);

        mBtnListViewSimple = (Button) findViewById(R.id.btnListViewSimple);
        mBtnListViewCustomAdapter = (Button) findViewById(R.id.btnListViewCustomAdapter);
    }

    private void setListeners() {

        mBtnListViewSimple.setOnClickListener(this);
        mBtnListViewCustomAdapter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

         switch (v.getId()) {
             case R.id.btnListViewSimple:
                 onSimpleListView();
                 break;
             case R.id.btnListViewCustomAdapter:
                 onListViewCustomAdapter();
                 break;
             default:
                 Log.d("OnClick", "Unhandled case");
         }
    }

    private void onSimpleListView() {

        Intent intent = new Intent(this, SimpleListViewActivity.class);
        startActivity(intent);
    }

    private void onListViewCustomAdapter() {

        Intent intent = new Intent(this, ListViewCustomAdapter.class);
        startActivity(intent);
    }
}
