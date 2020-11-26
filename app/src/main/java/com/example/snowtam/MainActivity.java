package com.example.snowtam;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> all = new ArrayList<>();
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mListView = (ListView) findViewById(R.id.listView);
        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
//            // fetch JSONArray named users
//            JSONArray AllArray = obj.getJSONArray("");
//            // implement for loop for getting users list data
//            for (int i = 0; i < obj.length(); i++) {
//                // create a JSONObject for fetching single user data
//                JSONObject tout = AllArray.getJSONObject(i);
//                // fetch email and name and store it in arraylist
//                id.add(tout.getString("id"));
//                all.add(tout.getString("all"));
//                // create a object for getting contact data from JSONObject
//            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
//                android.R.layout.simple_list_item_1, id);
//        mListView.setAdapter(adapter);

    }



    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("Realtime_NOTAMS.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
