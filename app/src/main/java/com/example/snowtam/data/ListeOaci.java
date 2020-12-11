package com.example.snowtam.data;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.snowtam.OaciAdapter;
import com.example.snowtam.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListeOaci extends RecyclerView.Adapter<ListeOaci.OaciViewHolder> {
    private ArrayList<LigneOaci> listeOaci;
    private LigneOaci ligneOaci;
    private LigneOaci ligne;
    private ListeOaci listeDecode;
    private LigneOaci nouveligne;
    private int piste;
    public ListeOaci(String oaci) {
        listeOaci = new ArrayList<LigneOaci>();
        this.constructOaci(oaci);
    }
    public ListeOaci() {
        listeOaci = new ArrayList<LigneOaci>();
    }
    public void addligneListOaci(LigneOaci ligne){
        listeOaci.add(ligne);
    }

    private void constructOaci(String oaci) {
        int i = 0;
        int indexdeb = oaci.indexOf(")",i);
        int indexfin = oaci.indexOf(")",indexdeb+1);
        while(!oaci.substring(indexfin-1,indexfin+1).equals(".)")) {
            ligneOaci = new LigneOaci(oaci.substring(indexdeb-1,indexdeb), oaci.substring(indexdeb+2,indexfin-1));
            i = indexdeb;
            indexdeb = indexfin;
            indexfin = oaci.indexOf(")",indexdeb+1);
            listeOaci.add(ligneOaci);
        }
        ligneOaci = new LigneOaci(oaci.substring(indexdeb-1,indexdeb), oaci.substring(indexdeb+1,indexfin-1));
        listeOaci.add(ligneOaci);
    }

    @NonNull
    @Override
    public ListeOaci.OaciViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_oaci, parent, false);
        OaciViewHolder oaciViewHolder = new OaciViewHolder(view);
        return oaciViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListeOaci.OaciViewHolder holder, int position) {
        LigneOaci ligneOaciICI = listeOaci.get(position);
        holder.titre.setText(ligneOaciICI.getLettre());
        holder.info.setText(ligneOaciICI.getLigne());
    }

    @Override
    public int getItemCount() {
        return listeOaci.size();
    }

    public static class OaciViewHolder extends RecyclerView.ViewHolder{
        TextView titre;
        TextView info;
        public OaciViewHolder(@NonNull View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.label);
            info = itemView.findViewById(R.id.info);
        }
    }

    public ListeOaci decode(String airport) throws ParseException {
        listeDecode = new ListeOaci();
        for(int i=0;i<listeOaci.size();i++){
            ligne = listeOaci.get(i);

            switch(ligne.getLettre()){
                case "A":
                    nouveligne = new LigneOaci("A", airport);
                    listeDecode.addligneListOaci(nouveligne);
                    break;

                case "B":
                    char[] tabDate=ligne.getLigne().toCharArray();
                    String date="";
                    String jour=tabDate[2]+tabDate[3]+" ";
                    String mois=tabDate[0]+tabDate[1]+"";
                    switch(mois){
                        case "01":
                            mois="January";
                            break;
                        case"02":
                            mois="February";
                            break;
                        case"03":
                            mois="March";
                            break;
                        case"04":
                            mois="April";
                            break;
                        case"05":
                            mois="May";
                            break;
                        case"06":
                            mois="June";
                            break;
                        case "07":
                            mois="July";
                            break;
                        case"08":
                            mois="August";
                            break;
                        case"09":
                            mois="September";
                            break;
                        case"10":
                            mois="October";
                            break;
                        case"11":
                            mois="November";
                            break;
                        case"12":
                            mois="December";
                            break;
                    }
                    String heure=tabDate[4]+tabDate[5]+"";
                    String minutes=tabDate[6]+tabDate[7]+"";
                    date =jour+" "+mois+" "+heure+"h"+minutes;
                    Date date1 = new SimpleDateFormat("MMddHHmm").parse(ligne.getLigne());
                    nouveligne = new LigneOaci("B", date1.toString());
                    listeDecode.addligneListOaci(nouveligne);
                    break;

                case "C":
                    piste = Integer.parseInt(ligne.getLigne().substring(0,2));
                    if(piste<37) {
                        nouveligne = new LigneOaci("C", "RUNWAY" + String.valueOf(piste) + "L");
                        listeDecode.addligneListOaci(nouveligne);
                    }
                    else if(piste>51 && piste<87){
                        nouveligne = new LigneOaci("C", "RUNWAY"+ String.valueOf(piste)+"R");
                        listeDecode.addligneListOaci(nouveligne);
                    }
                    else if(piste==88){
                        nouveligne = new LigneOaci("C", "ALL RUNWAYS");
                        listeDecode.addligneListOaci(nouveligne);
                    }
                    break;

                case "F":
                    String[] tabEtat = ligne.getLigne().split("/");
                    String etat="";
                    for (int j=0;j<tabEtat.length;j++){
                        switch(tabEtat[j]){
                            case "0":
                                if(j==0){
                                    etat=etat+"Threshold: "+"CLEAR AND DRY ";
                                }
                                if(j==1){
                                    etat=etat+ "/ Mid Runway: " +"CLEAR AND DRY ";
                                }
                                if(j==2){
                                    etat=etat+ "/ Roll out: "+"CLEAR AND DRY ";
                                }
                                break;
                            case "1":
                                if(j==0){
                                    etat=etat+"Threshold: "+"DAMP ";
                                }
                                if(j==1){
                                    etat=etat+ "/ Mid Runway: " +"DAMP ";
                                }
                                if(j==2){
                                    etat=etat+ "/ Roll out: "+"DAMP ";
                                }
                                break;

                            case "2":
                                if(j==0){
                                    etat=etat+"Threshold: "+"WET OR WET PATCHES ";
                                }
                                if(j==1){
                                    etat=etat+ "/ Mid Runway: " +"WET OR WET PATCHES ";
                                }
                                if(j==2){
                                    etat=etat+ "/ Roll out: "+"WET OR WET PATCHES ";
                                }
                                break;

                            case "3":
                                if(j==0){
                                    etat=etat+"Threshold: "+"RIME OR FROST COVERED ";
                                }
                                if(j==1){
                                    etat=etat+ "/ Mid Runway: " +"RIME OR FROST COVERED ";
                                }
                                if(j==2){
                                    etat=etat+ "/ Roll out: "+"RIME OR FROST COVERED ";
                                }
                                break;

                            case "4":
                                if(j==0){
                                    etat=etat+"Threshold: "+"DRY SNOW ";
                                }
                                if(j==1){
                                    etat=etat+ "/ Mid Runway: " +"DRY SNOW ";
                                }
                                if(j==2){
                                    etat=etat+ "/ Roll out: "+"DRY SNOW ";
                                }
                                break;

                            case "5":
                                if(j==0){
                                    etat=etat+"Threshold: "+"WET SNOW ";
                                }
                                if(j==1){
                                    etat=etat+ "/ Mid Runway: " +"WET SNOW ";
                                }
                                if(j==2){
                                    etat=etat+ "/ Roll out: "+"WET SNOW ";
                                }
                                break;

                            case "6":
                                if(j==0){
                                    etat=etat+"Threshold: "+"SLUSH ";
                                }
                                if(j==1){
                                    etat=etat+ "/ Mid Runway: " +"SLUSH ";
                                }
                                if(j==2){
                                    etat=etat+ "/ Roll out: "+"SLUSH ";
                                }
                                break;

                            case "7":
                                if(j==0){
                                    etat=etat+"Threshold: "+"ICE ";
                                }
                                if(j==1){
                                    etat=etat+ "/ Mid Runway: " +"ICE ";
                                }
                                if(j==2){
                                    etat=etat+ "/ Roll out: "+"ICE ";
                                }
                                break;

                            case "8":
                                if(j==0){
                                    etat=etat+"Threshold: "+"COMPACTED OR ROLLED SNOW ";
                                }
                                if(j==1){
                                    etat=etat+ "/ Mid Runway: " +"COMPACTED OR ROLLED SNOW ";
                                }
                                if(j==2){
                                    etat=etat+ "/ Roll out: "+"COMPACTED OR ROLLED SNOW ";
                                }
                                break;

                            case "9":
                                if(j==0){
                                    etat=etat+"Threshold: "+"FROZEN RUTS OR RIDGES ";
                                }
                                if(j==1){
                                    etat=etat+ "/ Mid Runway: " +"FROZEN RUTS OR RIDGES ";
                                }
                                if(j==2){
                                    etat=etat+ "/ Roll out: "+"FROZEN RUTS OR RIDGES ";
                                }
                                break;
                        }
                    }
                    nouveligne = new LigneOaci("F", etat);
                    listeDecode.addligneListOaci(nouveligne);
                    break;
                default:
                    break;
            }
        }
        return listeDecode;
    }
}
