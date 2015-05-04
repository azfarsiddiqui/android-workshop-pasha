package com.tenpearls.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    Button mBtnOpenCountriesList;
    Button mBtnOpenCountriesListForResult;
    TextView mTxtActivityResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        setListeners();
    }

    private void initUI () {

        setContentView(R.layout.activity_main);

        mBtnOpenCountriesList = (Button) findViewById(R.id.btnOpenCountriesList);
        mBtnOpenCountriesListForResult = (Button) findViewById(R.id.btnOpenCountriesListForResult);
        mTxtActivityResult = (TextView) findViewById(R.id.txtActivityResult);
    }

    private void setListeners () {

        mBtnOpenCountriesList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                onOpenCountriesActivity();
            }
        });

        mBtnOpenCountriesListForResult.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                onOpenCountriesActivityForResult();
            }
        });
    }

    private void onOpenCountriesActivity () {

        Intent intent = new Intent (this, CountryListActivity.class);
        startActivity (intent);
    }

    private void onOpenCountriesActivityForResult () {

        Intent intent = new Intent (this, CountryListActivity.class);
        startActivityForResult (intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String selectedCountry = data.getStringExtra("country");
            mTxtActivityResult.setText("Selected Country: " + selectedCountry);
        }
    }
}
