package com.example.trupti.volley_with_arraydemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<Iteamlist> iteamlistsa;
    Iteamlist iteamlistpojo;
    String name,image,link;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        iteamlistsa=new ArrayList<>();
        loadurl();
    }

    private void loadurl() {
        String Datauri = "http://vaksys.com/100_application/api/v1/more_apps";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Datauri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("row");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject c = jsonArray.getJSONObject(i);

                        iteamlistpojo = new Iteamlist(c.getString("name"), c.getString("image"), c.getString("link"));
                        iteamlistsa.add(iteamlistpojo);
                    }

                    MyAdapter myAdapter = new MyAdapter(iteamlistsa, getApplicationContext());
                    recyclerView.setAdapter(myAdapter);
                    progressDialog.dismiss();


                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        progressDialog=progressDialog.show(MainActivity.this,"Pleas wait ...",null,true,true);
        progressDialog.setMessage("featching your data ! please wait...!");
        progressDialog.setCancelable(false);
    }
}
