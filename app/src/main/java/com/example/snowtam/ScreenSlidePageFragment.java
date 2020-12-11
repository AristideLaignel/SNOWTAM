package com.example.snowtam;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.snowtam.Model.Service;
import com.example.snowtam.data.DataOaci;
import com.example.snowtam.data.ListeOaci;
import com.example.snowtam.data.reponsePosition;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.text.ParseException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ScreenSlidePageFragment extends Fragment{
    private ViewGroup rootView;
    private String title;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private int count;
    private GoogleMap mMap;
    private LatLng position = new LatLng(52.26,-69.12);
    MapView mMapView;
    private GoogleMap googleMap;
    private ListeOaci listeOacidecode = null;
    private ListeOaci listeOaci;
    private String nameairport;
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
        mMapView = (MapView) rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();
        Response.Listener<DataOaci[]> rep = new Response.Listener<DataOaci[]>() {
            @Override
            public void onResponse(DataOaci[] response){
                OaciAdapter mAdapter = new OaciAdapter(response);
                listeOaci = new ListeOaci(mAdapter.getSnotam());
                try {
                    listeOacidecode = listeOaci.decode(nameairport);
                    recyclerView.setAdapter(listeOaci);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

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
        Response.Listener<reponsePosition[]> repo = new Response.Listener<reponsePosition[]>() {
            @Override
            public void onResponse(reponsePosition[] responseposition){
                position = new LatLng(responseposition[0].getLatitude(),responseposition[0].getLongitude());
                tvLabel.setText(responseposition[0].getAirportName());
                tvLabel.setTextSize(30);
                nameairport = responseposition[0].getAirportName();
            }
        };
        final Response.ErrorListener error2 = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "Error Activity result: " + error.getMessage());
            }
        };
        Service.getPosition(repo,error2,rootView.getContext(),title);

        try {
            Thread.sleep(3000);
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                //googleMap.setMyLocationEnabled(false);
                // For dropping a marker at a point on the Map
                googleMap.addMarker(new MarkerOptions()
                        .position(position));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,new Float(14.42)));
                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
        CheckBox chk = (CheckBox) rootView.findViewById(R.id.checkbox);
        chk.toggle();
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                // Check which checkbox was clicked
                if (checked){
                    recyclerView.setAdapter(listeOacidecode);
                }
                else{
                    recyclerView.setAdapter(listeOaci);
                }
            }
        });
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
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}
