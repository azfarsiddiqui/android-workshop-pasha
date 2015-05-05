package com.tenpearls.android.displayingdata;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListViewCustomAdapter extends ActionBarActivity {

    ListView mListView;
    ArrayList<News> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        prepareDataSource();
        populateList();
    }

    private void initUI() {

        setContentView(R.layout.activity_simple_list_view);

        mListView = (ListView) findViewById(R.id.listView);
    }

    private void prepareDataSource() {

        news.add(new News("This is the title", "This should be a long description..."));
        news.add(new News("This is the title", "This should be a long description..."));
        news.add(new News("This is the title", "This should be a long description..."));
        news.add(new News("This is the title", "This should be a long description..."));
        news.add(new News("This is the title", "This should be a long description..."));
    }

    private void populateList() {

        mListView.setAdapter(new NewsAdapter());
    }

    private class NewsAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return news.size();
        }

        @Override
        public Object getItem(int position) {

            return news.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            News newsItem = (News) getItem(position);

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item_news, parent, false);
            }

            TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            TextView txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);

            txtTitle.setText(newsItem.getTitle());
            txtDescription.setText(newsItem.getDescription());

            return convertView;
        }
    }

    private class News {

        String title;
        String description;

        public News (String title, String description) {

            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}
