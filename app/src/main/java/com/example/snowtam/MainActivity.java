package com.example.snowtam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> all = new ArrayList<>();
    ListView mListView;
    TextView text;
    String jsonText;
    String errort;
    ArrayList<String> Snowtam = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.listView);
        text = findViewById(R.id.text);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=e1d9c5c0-2a83-11eb-83a7-2bc643ee461e&format=json&criticality=1&locations=ENGM";
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            // Display the first 500 characters of the response string.
            jsonText = response;
            Snowtam.add(jsonText);

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errort = error.toString();
            }
        });



// Add the request to the RequestQueue.
        queue.add(stringRequest);
        //text.setText(jsonText);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // yourMethod();
                //text.setText(Snowtam.get(0));

        try {
            // get JSONObject from JSON file
            //jsonText = readText(MainActivity.this, R.raw.realtime);
            //JSONObject obj = new JSONObject(jsonText);
            //text.setText(obj.);
            // fetch JSONArray named users
            //JSONArray AllArray = obj.getJSONArray("");
            JSONArray AllArray = new JSONArray(Snowtam.get(0));
            //text.setText(AllArray.getJSONObject(0).getString("id"));
            // implement for loop for getting users list data
            int i = 0;

            while (!AllArray.getJSONObject(i).getString("id").substring(0,2).equals("SW")){
                i++;
                // create a JSONObject for fetching single user data
                //JSONObject tout = AllArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                //text.setText(tout.getString("all"));
                //id.add(tout.getString("id"));
                //all.add(tout.getString("all"));
                // create a object for getting contact data from JSONObject
            }
            int indexdeb = AllArray.getJSONObject(i).getString("all").indexOf("(SNOWTAM");
            int indexfin= AllArray.getJSONObject(i).getString("all").indexOf("CREATED");
            text.setText(AllArray.getJSONObject(i).getString("all").substring(indexdeb,indexfin));
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                    android.R.layout.simple_list_item_1, id);
            mListView.setAdapter(adapter);

        }


        catch (JSONException e) {
            e.printStackTrace();
        }
            }
        }, 7000);
////        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
////        startActivity(intent);
//    }

//    public String loadJSONFromAsset() {
//        String json = null;
//        try {
//            InputStream is = getAssets().open("Realtime_NOTAMS.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }
//        private static String readText (Context context,int resId) throws IOException {
//            InputStream is = context.getResources().openRawResource(resId);
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            StringBuilder sb = new StringBuilder();
//            String s;
//            while ((s = br.readLine()) != null) {
//                sb.append(s);
//                sb.append("\n");
//            }
//            return sb.toString();
//        }

    }
}