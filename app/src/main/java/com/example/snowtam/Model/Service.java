package com.example.snowtam.Model;

import android.content.Context;
import android.os.Handler;
import android.support.v4.os.IResultReceiver;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.snowtam.GsonRequest;
import com.example.snowtam.MainActivity;
import com.example.snowtam.data.DataOaci;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import java.net.URLEncoder;

public class Service {

    private static String jsonText;
    private static final String TAG = "Service";

    public static void getOACI (Response.Listener<DataOaci[]> response, Response.ErrorListener errorListener, Context context, String Oaci){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=b1944770-3576-11eb-853d-396efe154b9d&format=json&criticality=1&locations="+ URLEncoder.encode(Oaci);
        GsonRequest<DataOaci[]> gsonRequest= new GsonRequest(url, DataOaci[].class,null,response,errorListener);
        queue.add(gsonRequest);
    }






//     public static String getOACI (String OACI, Context context){
//        final String[] result = new String[1];
//        final String[] errort = new String[1];
//        ArrayList<String> snowtam = new ArrayList<>();
//        ArrayList<String> res = new ArrayList<>();
//        RequestQueue queue = Volley.newRequestQueue(context);
//        String url = "https://applications.icao.int/dataservices/api/notams-realtime-list?api_key=b1944770-3576-11eb-853d-396efe154b9d&format=json&criticality=1&locations=ENGM";
//        //+ URLEncoder.encode(OACI)
//        StringRequest stringRequest;
//        stringRequest = new StringRequest(Request.Method.GET, url, response -> {
//            // Display the first 500 characters of the response string.
//            jsonText = response;
//            snowtam.add(jsonText);
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                errort[0] = error.toString();
//            }
//        });
//        // Add the request to the RequestQueue.
//
//        result[0] = " Juste Avavnt";
//        queue.add(stringRequest);
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//        public void run() {
//            try {
//                JSONArray AllArray = new JSONArray(snowtam.get(0));
//                int i = 0;
//                while (!AllArray.getJSONObject(i).getString("id").substring(0,2).equals("SW")){
//                    i++;
//                }
//                int indexdeb = AllArray.getJSONObject(i).getString("all").indexOf("SNOWTAM");
//                int indexfin= AllArray.getJSONObject(i).getString("all").indexOf("CREATED");
//                result[0] = AllArray.getJSONObject(i).getString("all").substring(indexdeb,indexfin);
//                res.add(result[0]);
//            }
//            catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        }, 8000);
//        return result[0] +res.get(0);
//     }

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
    private static String readText (Context context, int resId) throws IOException {
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
