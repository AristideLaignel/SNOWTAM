package com.example.snowtam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.snowtam.Model.Service;
import com.example.snowtam.data.DataOaci;

public class ActivityResult extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);
        Intent intent = getIntent();
        TextView titre = findViewById(R.id.titre);
        TextView info = findViewById(R.id.info);
        final String[] Oaci = {intent.getStringExtra("oaci")};
        Response.Listener<DataOaci[]> rep = new Response.Listener<DataOaci[]>() {
            @Override
            public void onResponse(DataOaci[] response){
                OaciAdapter mAdapter = new OaciAdapter(response);
                titre.setText("Airport");
                info.setText(mAdapter.getAirport());

            }
        };
        final Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error Activity result: " + error.getMessage());
            }
        };
        Service.getOACI(rep,error,ActivityResult.this,Oaci[0]);


    }
}


