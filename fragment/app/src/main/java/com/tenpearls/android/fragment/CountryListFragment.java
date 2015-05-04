package com.tenpearls.android.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CountryListFragment extends Fragment {

    ListView mListViewCountries;
    String[] countries = new String[] {
            "Pakistan",
            "India",
            "Bangladesh",
            "Sri Lanka"
    };

    public CountryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_countries, container, false);
        initUI(rootView);
        populateCountriesList();

        return rootView;
    }

    private void initUI(View rootView) {

        mListViewCountries = (ListView) rootView.findViewById(R.id.listViewCountries);
    }

    private void populateCountriesList() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, countries);
        mListViewCountries.setAdapter(adapter);
    }

}
