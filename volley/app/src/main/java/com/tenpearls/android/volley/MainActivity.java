package com.tenpearls.android.volley;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends ActionBarActivity {

    RequestQueue mRequestQueue;
    Button mBtnDispatchNetworkRequest;
    TextView mTxtNetworkResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        initUI();
    }

    private void initUI() {

        setContentView(R.layout.activity_main);

        mBtnDispatchNetworkRequest = (Button) findViewById(R.id.btnDispatchNetworkRequest);
        mTxtNetworkResponse = (TextView) findViewById(R.id.txtNetworkResponse);

        mBtnDispatchNetworkRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchNetworkRequestToGetNews();
            }
        });
    }

    private void dispatchNetworkRequestToGetNews () {

        String url = "https://api.myjson.com/bins/4wq9h";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {

                    mTxtNetworkResponse.setText(s);
                }
            },
            new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                    Toast.makeText(MainActivity.this, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        );

        mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueue.add(request);
    }
}
