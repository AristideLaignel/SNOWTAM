package com.example.snowtam;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import net.bytebuddy.asm.Advice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Setting extends AppCompatActivity {
    private Spinner spinner;
    private Context context;
    private static final String LOCALE_ENGLISH = "en";
    private static final String LOCALE_FRENCH = "fr";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
        spinner = (Spinner) findViewById(R.id.spinner);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        List languelist = new ArrayList();
        languelist.add("Français");
        languelist.add("Anglais");
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                languelist
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        context = this;
        Button change = (Button) findViewById(R.id.boutonchange);
        Resources resources = context.getResources();
//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(spinner.getSelectedItem().equals("Français")){
//                   Locale locale = new Locale("fr");
//                   Locale.setDefault(locale);
//                   Configuration configuration = context.getResources().getConfiguration();
//                   configuration.setLocale(locale);
//                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//                    SharedPreferences.Editor editor = preferences.edit();
//
//                    editor.putString(SELECTED_LANGUAGE, LOCALE_FRENCH);
//                    editor.apply();
//                    resources.updateConfiguration(configuration, resources.getDisplayMetrics());
//               }
//               else{
//                   Locale locale = new Locale("en");
//                   Locale.setDefault(locale);
//                   Configuration configuration = context.getResources().getConfiguration();
//                   configuration.setLocale(locale);
//                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString(SELECTED_LANGUAGE, LOCALE_ENGLISH);
//                    editor.apply();
//                    resources.updateConfiguration(configuration, resources.getDisplayMetrics());
//               }
//            }
//        });
//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//               if(spinner.getSelectedItem().equals("Français")){
//                   Locale locale = new Locale("fr");
//                   Locale.setDefault(locale);
//
//                   Configuration configuration = context.getResources().getConfiguration();
//                   configuration.setLocale(locale);
//               }
//               else{
//                   Locale locale = new Locale("en");
//                   Locale.setDefault(locale);
//
//                   Configuration configuration = context.getResources().getConfiguration();
//                   configuration.setLocale(locale);
//               }
//
//
//            }
//        });

    }
}
