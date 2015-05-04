package com.tenpearls.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CountryListActivity extends ActionBarActivity {

    ListView mListViewCountries;
    String[] countries = new String[] {
            "Pakistan",
            "India",
            "Bangladesh",
            "Sri Lanka"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        setListeners();
        populateCountriesList();
    }

    private void initUI() {

        setContentView(R.layout.activity_countries_list);

        mListViewCountries = (ListView) findViewById(R.id.listViewCountries);
    }

    private void setListeners() {

        mListViewCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String selectedCountry = countries[position];
                onCountrySelected(selectedCountry);
            }
        });
    }

    private void populateCountriesList() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, countries);
        mListViewCountries.setAdapter(adapter);
    }

    private void onCountrySelected (String selectedCountry) {

        Intent intent = new Intent();
        intent.putExtra("country", selectedCountry);
        setResult(RESULT_OK, intent);
        finish();
    }
}
