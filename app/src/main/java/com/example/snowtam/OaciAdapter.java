package com.example.snowtam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.snowtam.data.DataOaci;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OaciAdapter {

    private DataOaci[] oaci;

    public OaciAdapter(DataOaci[] listeOaci){
        this.oaci = listeOaci;
    }

    public String getSnotam(){

        DataOaci singleOaci = oaci[0];
        int i = 0;
        while (!singleOaci.getId().substring(0,2).equals("SW")){
            i++;
            singleOaci = oaci[i];
        }
        int indexdeb = singleOaci.getAll().indexOf("SNOWTAM");
        int indexfin= singleOaci.getAll().indexOf("CREATED");
        return singleOaci.getAll().substring(indexdeb, indexfin);
    }

    public String getAirport(){
        String Oaci = this.getSnotam();
        int indexdeb = Oaci.indexOf("B)");
        int indexfin= Oaci.indexOf("C)");
        return Oaci.substring(indexdeb,indexfin);
    }


}
