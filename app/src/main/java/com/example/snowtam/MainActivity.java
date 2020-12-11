package com.example.snowtam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.example.snowtam.data.LigneOaci;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> oaciList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        EditText oaci1 = findViewById(R.id.oaci1);
        EditText oaci2 = findViewById(R.id.oaci2);
        EditText oaci3 = findViewById(R.id.oaci3);
        EditText oaci4 = findViewById(R.id.oaci4);
        EditText oaci5 = findViewById(R.id.oaci5);
        ImageButton boutonsetting = findViewById(R.id.button);
        Button bouton = findViewById(R.id.submit);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oaciList = new ArrayList<>();
                if(!oaci1.getText().toString().equals("")){
                    oaciList.add(oaci1.getText().toString());
                }
                if(!oaci2.getText().toString().equals("")){
                    oaciList.add(oaci2.getText().toString());
                }
                if(!oaci3.getText().toString().equals("")){
                    oaciList.add(oaci3.getText().toString());
                }
                if(!oaci4.getText().toString().equals("")){
                    oaciList.add(oaci4.getText().toString());
                }
                if(!oaci5.getText().toString().equals("")){
                    oaciList.add(oaci5.getText().toString());
                }
                Intent intent = new Intent(MainActivity.this, ScreenSlidePagerActivity.class);
                intent.putStringArrayListExtra("oaci",oaciList);
                //intent.putExtra("oaci1",oaci1.getText().toString());
                startActivity(intent);
            }
        });
        boutonsetting.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
            }
        });

    }
}