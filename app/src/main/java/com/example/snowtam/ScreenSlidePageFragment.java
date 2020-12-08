package com.example.snowtam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScreenSlidePageFragment extends Fragment implements OnMapReadyCallback {
    private ViewGroup rootView;
    private String title;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private int count;
    private GoogleMap mMap;
    private LatLng position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        TextView tvLabel = (TextView) rootView.findViewById(R.id.textView2);
        tvLabel.setText(title);
        recyclerView = rootView.findViewById(R.id.oacirecyclerview);
        layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);
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
        try {
            Thread.sleep(count*2500);
            Service.getOACI(rep,error,rootView.getContext(),title);
            Thread.sleep(count*2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Response.Listener<reponsePosition> repo = new Response.Listener<reponsePosition>() {
            @Override
            public void onResponse(reponsePosition responseposition){
                position = new LatLng(responseposition.getLatitude(),responseposition.getLongitude());
                //SupportMapFragment mapFragment = (SupportMapFragment) rootView;
                //mapFragment.getMapAsync(ScreenSlidePageFragment.this::onMapReady);
            }
        };
        final Response.ErrorListener error2 = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error Activity result: " + error.getMessage());
            }
        };
        Service.getPosition(repo,error,rootView.getContext(),title);


        return rootView;
    }

    public static ScreenSlidePageFragment newInstance(String title, int count) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("count", count);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
        count = getArguments().getInt("count");
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
}
