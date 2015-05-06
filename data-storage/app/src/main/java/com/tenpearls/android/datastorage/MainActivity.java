package com.tenpearls.android.datastorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button mBtnSave;
    Button mBtnRetrieve;
    Button mBtnWriteFile;
    Button mBtnDeleteFile;
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
        mBtnWriteFile = (Button) findViewById(R.id.btnWriteFile);
        mBtnDeleteFile = (Button) findViewById(R.id.btnDeleteFile);
        mTxtName = (EditText) findViewById(R.id.txtName);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSave:
                onSave();
                break;
            case R.id.btnRetrieve:
                onRetrieve();
                break;
            case R.id.btnWriteFile:
                onWriteFile();
                break;
            case R.id.btnDeleteFile:
                onDeleteFile();
                break;
            default:
                Log.e("OnClick", "Unhandled case..");
        }
    }

    private void setListeners() {

        mBtnSave.setOnClickListener(this);
        mBtnRetrieve.setOnClickListener(this);
        mBtnWriteFile.setOnClickListener(this);
        mBtnDeleteFile.setOnClickListener(this);
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

    private void onWriteFile() {

        String filename = "hello.txt";
        String string = "This is my content";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onDeleteFile() {

        File dir = getFilesDir();
        File file = new File(dir, "hello.txt");
        boolean deleted = file.delete();
    }

}
