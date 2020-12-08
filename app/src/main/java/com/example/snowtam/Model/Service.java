package com.example.snowtam.Model;

import android.content.Context;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.snowtam.GsonRequest;
import com.example.snowtam.data.DataOaci;
import com.example.snowtam.data.reponsePosition;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;



public class Service {

    private static String jsonText;
    private static final String TAG = "Service";

    public static void getOACI (Response.Listener<DataOaci[]> response, Response.ErrorListener errorListener, Context context, String Oaci){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=aaeb1380-397d-11eb-b01e-8f2011b89737&format=json&criticality=1&locations="+ URLEncoder.encode(Oaci);
        GsonRequest<DataOaci[]> gsonRequest= new GsonRequest(url, DataOaci[].class,null,response,errorListener);
        queue.add(gsonRequest);
    }

    public static void getPosition (Response.Listener<reponsePosition> response, Response.ErrorListener errorListener, Context context, String Oaci){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://applications.icao.int/dataservices/api/indicators-list?api_key=aaeb1380-397d-11eb-b01e-8f2011b89737&state=&airports="+ URLEncoder.encode(Oaci)+"&format=json";
        GsonRequest<reponsePosition> gsonRequest= new GsonRequest(url, reponsePosition.class,null,response,errorListener);
        queue.add(gsonRequest);
    }






}
