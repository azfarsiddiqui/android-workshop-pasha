package com.tenpearls.android.datastorage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    Button mBtnSave;
    Button mBtnRetrieve;
    EditText mTxtName;
    final String SHARED_PREF_KEY = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        setListeners();
    }

    private void initUI() {

        setContentView(R.layout.activity_main);

        mBtnSave = (Button) findViewById(R.id.btnSave);
        mBtnRetrieve = (Button) findViewById(R.id.btnRetrieve);
        mTxtName = (EditText) findViewById(R.id.txtName);
    }

    private void setListeners() {

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onSave();
            }
        });

        mBtnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onRetrieve();
            }
        });
    }

    private void onSave() {

        saveNameInSharedPrefs();
    }

    private void onRetrieve() {

        retrieveNameFromSharedPrefs();
    }

    private void retrieveNameFromSharedPrefs() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (this);
        String name = prefs.getString (SHARED_PREF_KEY, "");
        mTxtName.setText(name);
    }

    private void saveNameInSharedPrefs() {

        String name = mTxtName.getText().toString();

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit ();
        editor.putString (SHARED_PREF_KEY, name);
        editor.commit ();
    }
}
