package com.example.snowtam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.snowtam.Model.Service;
import com.example.snowtam.data.DataOaci;
import com.example.snowtam.data.ListeOaci;

public class ActivityResult extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);
        Intent intent = getIntent();
        final String[] Oaci = {intent.getStringExtra("oaci")};
        recyclerView = findViewById(R.id.oacirecyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        TextView airport = findViewById(R.id.airport);
        Response.Listener<DataOaci[]> rep = new Response.Listener<DataOaci[]>() {
            @Override
            public void onResponse(DataOaci[] response){
                OaciAdapter mAdapter = new OaciAdapter(response);
                ListeOaci listeOaci = new ListeOaci(mAdapter.getSnotam());
                recyclerView.setAdapter(listeOaci);
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


