package com.example.exercice5;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://www.reddit.com/r/Android/new.json";
    ;

    private RecyclerView myrcv;
    private RecyclerView.Adapter adapter;
    List<Listenouveaute> etudiants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myrcv = (RecyclerView) findViewById(R.id.rcv);
        myrcv.setHasFixedSize(true);
        myrcv.setLayoutManager(new LinearLayoutManager(this));

        etudiants = new ArrayList<>();

        loadUrlData();
    }
    protected String doInBackground(String... params) {
        HttpHandler sh = new HttpHandler();
        String result = sh.makeServiceCall(URL_DATA);
        return result;
    }

    private void loadUrlData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading news. Please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject data=jsonObject.getJSONObject("data");
                    JSONArray array = data.getJSONArray("children");


                    for (int i = 0; i < array.length(); i++) {

                        JSONObject w = array.getJSONObject ( i).getJSONObject("data");
                        Listenouveaute liste = new Listenouveaute (
                                w.getString ( "selftext" ),

                                w.getString ( "selftext" ),
                                w.getString ( "title" ),w.getString ( "author_fullname" ),
                                w.getString ( "thumbnail" ),
                                w.getString ( "name" ));
                        etudiants.add( liste );
                    }

                    adapter = new ListeAdapter (etudiants,getApplicationContext());
                    myrcv.setAdapter(adapter);

                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

