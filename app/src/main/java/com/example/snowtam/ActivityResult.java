package com.example.snowtam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.snowtam.Model.Service;
import com.example.snowtam.data.DataOaci;
import com.example.snowtam.data.ListeOaci;
import com.example.snowtam.data.reponsePosition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActivityResult extends AppCompatActivity implements OnMapReadyCallback {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GoogleMap mMap;
    private LatLng position = new LatLng(50.23,86.24);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);
        Intent intent = getIntent();
        final String[] Oaci = {intent.getStringExtra("oaci1")};
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
        //Service.getOACI(rep,error,ActivityResult.this,Oaci[0]);
        Response.Listener<reponsePosition[]> repo = new Response.Listener<reponsePosition[]>() {
            @Override
            public void onResponse(reponsePosition[] responseposition){
                position = new LatLng(responseposition[0].getLatitude(),responseposition[0].getLongitude());
                airport.setText("Lati :"+responseposition[0].getLatitude().toString()+"  \n Longi : "+responseposition[0].getLongitude());
            }
        };
        final Response.ErrorListener error2 = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error Activity result: " + error.getMessage());
            }
        };
        Service.getPosition(repo,error2,this,Oaci[0]);
        try {
            Thread.sleep(2000);

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions()
                .position(position));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,new Float(14.42)));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }

    public void changelocation(LatLng position){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                position,new Float(14.42)));
    }
}


