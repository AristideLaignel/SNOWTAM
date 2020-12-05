package com.example.snowtam.data;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.snowtam.OaciAdapter;
import com.example.snowtam.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListeOaci extends RecyclerView.Adapter<ListeOaci.OaciViewHolder> {
    private ArrayList<LigneOaci> listeOaci;
    private LigneOaci ligneOaci;
    public ListeOaci(String oaci) {
        listeOaci = new ArrayList<LigneOaci>();
        this.constructOaci(oaci);
    }

    private void constructOaci(String oaci) {
        int i = 0;
        int indexdeb = oaci.indexOf(")",i);
        int indexfin = oaci.indexOf(")",indexdeb+1);
        while(!oaci.substring(indexfin-1,indexfin+1).equals(".)")) {
            ligneOaci = new LigneOaci(oaci.substring(indexdeb-1,indexdeb), oaci.substring(indexdeb+1,indexfin-1));
            i = indexdeb;
            indexdeb = indexfin;
            indexfin = oaci.indexOf(")",indexdeb+1);
            listeOaci.add(ligneOaci);
        }
        ligneOaci = new LigneOaci(oaci.substring(indexdeb-1,indexdeb), oaci.substring(indexdeb+1,indexfin-1));
        listeOaci.add(ligneOaci);
        Log.e("Liste Oaci", listeOaci.get(0).getLettre());
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
        Log.e("ICIIIIIIIIIII", listeOaci.get(position).getLettre());
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
}
