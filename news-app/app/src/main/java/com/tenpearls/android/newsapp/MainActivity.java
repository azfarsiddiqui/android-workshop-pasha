package com.tenpearls.android.newsapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView mListView;
    ArrayList<News> mNewsItems = new ArrayList<>();
    RequestQueue mRequestQueue;
    ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
        initVolley();
        dispatchNetworkRequestToGetNews();
    }

    private void initUI() {

        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listViewNews);
    }

    private void initVolley() {

        mRequestQueue = Volley.newRequestQueue(this);
        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(LruBitmapCache.getCacheSize(this)));
    }

    private void dispatchNetworkRequestToGetNews () {

        String url = "https://api.myjson.com/bins/2y7ol";
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {

                    parseNewsJson(s);
                    updateUI();
                    progressDialog.hide();
                }
            },
            new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                    progressDialog.hide();
                    Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        );

        mRequestQueue.add(request);
    }

    private void parseNewsJson(String json) {

        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                News newsItem = new News();
                newsItem.setTitle(jsonObject.getString("title"));
                newsItem.setDescription(jsonObject.getString("abstract"));
                newsItem.setThumbnailUrl(jsonObject.getString("url"));

                mNewsItems.add(newsItem);
            }
        }
        catch (Exception exception) {
            Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI() {

        NewsAdapter newsAdapter = new NewsAdapter();
        mListView.setAdapter(newsAdapter);
    }

    private class NewsAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return mNewsItems.size();
        }

        @Override
        public Object getItem(int position) {

            return mNewsItems.get(position);
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
            NetworkImageView imageView = (NetworkImageView) convertView.findViewById(R.id.imageThumbnail);

            txtTitle.setText(newsItem.getTitle());
            txtDescription.setText(newsItem.getDescription());
            imageView.setImageUrl(newsItem.getThumbnailUrl(), mImageLoader);

            return convertView;
        }
    }
}
