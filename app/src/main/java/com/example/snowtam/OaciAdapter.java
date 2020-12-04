package com.example.snowtam;

import com.example.snowtam.data.DataOaci;

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
}
