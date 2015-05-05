package com.tenpearls.android.displayingdata;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SimpleListViewActivity extends ActionBarActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        populateList();
    }

    private void initUI() {

        setContentView(R.layout.activity_simple_list_view);

        mListView = (ListView) findViewById(R.id.listView);
    }

    private void populateList() {

        String[] items = new String[] {
                "PHP", "Perl", "Ruby", "iOS", "Android", ".net"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items);
        mListView.setAdapter(adapter);
    }
}
