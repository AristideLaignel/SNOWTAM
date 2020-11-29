package com.example.snowtam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.widget.TextView;

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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.listView);
        text = findViewById(R.id.text);

        try {
            // get JSONObject from JSON file
            String jsonText = readText(MainActivity.this, R.raw.realtime);
            //JSONObject obj = new JSONObject(jsonText);
            //text.setText(obj.);
            // fetch JSONArray named users
            //JSONArray AllArray = obj.getJSONArray("");
            JSONArray AllArray = new JSONArray(jsonText);
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


        catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(intent);
    }

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
        private static String readText (Context context,int resId) throws IOException {
            InputStream is = context.getResources().openRawResource(resId);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }
            return sb.toString();
        }

}