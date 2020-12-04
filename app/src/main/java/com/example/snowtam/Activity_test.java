package com.example.snowtam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.snowtam.Model.Service;
import com.example.snowtam.data.DataOaci;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URLEncoder;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_test extends AppCompatActivity {

    String jsonText;
    private static final String TAG = "Test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView text = findViewById(R.id.text);
        Intent intent = getIntent();
        final String[] Oaci = {intent.getStringExtra("oaci")};
        //text.setText(Oaci[0]);
        final String[] Oaciresponse = new String[1];
        Response.Listener<DataOaci[]> rep = new Response.Listener<DataOaci[]>() {
            @Override
            public void onResponse(DataOaci[] response){
                OaciAdapter mAdapter = new OaciAdapter(response);
                text.setText(mAdapter.getSnotam());
            }
        };
        final Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "searchAlbum onErrorResponse: " + error.getMessage());
            }
        };
        Service.getOACI(rep,error,Activity_test.this);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                Oaciresponse[0] = Service.getOACI(Oaci[0],Activity_test.this);
//                text.setText(Oaciresponse[0]);
//            }}, 10000);

//        final String[] result = new String[1];
//        final String[] errort = new String[1];
//        ArrayList<String> snowtam = new ArrayList<>();
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=b1944770-3576-11eb-853d-396efe154b9d&format=json&criticality=1&locations=ENGM";
//        //+ URLEncoder.encode(Oaci[0])
//        StringRequest stringRequest;
//        stringRequest = new StringRequest(Request.Method.GET, url, response -> {
//            // Display the first 500 characters of the response string.
//            jsonText = response;
//            snowtam.add(jsonText);
//
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                errort[0] = error.toString();
//            }
//        });
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//
//                try {
//                    JSONArray AllArray = new JSONArray(snowtam.get(0));
//                    int i = 0;
//                    while (!AllArray.getJSONObject(i).getString("id").substring(0,2).equals("SW")){
//                        i++;
//                    }
//                    int indexdeb = AllArray.getJSONObject(i).getString("all").indexOf("SNOWTAM");
//                    int indexfin= AllArray.getJSONObject(i).getString("all").indexOf("CREATED");
//                    result[0] = AllArray.getJSONObject(i).getString("all").substring(indexdeb,indexfin);
//                    text.setText(result[0]);
//                }
//                catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 8000);

    }
}
