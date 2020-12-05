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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityResult extends AppCompatActivity implements OnMapReadyCallback {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private GoogleMap mMap;
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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //this.changelocation(48.26, 50.262);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(49.4544618, 2.1113351);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,new Float(14.42)));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }

    public void changelocation(double lat, double longi){

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(lat,longi),50));
    }
}


