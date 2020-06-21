package com.example.pokemondex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class StartMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocal();
        setContentView(R.layout.activity_start_menu);

        Button changeLanguage = findViewById(R.id.changeLanguage);
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Alertdialog with list of languages
                showLanguageDialog();
            }
        });
    }

    private void showLanguageDialog() {
//        Languages that will be displayed when choosing
        final String[] langList = {"English", "Dutch", "French"};
        AlertDialog.Builder mBuidler = new AlertDialog.Builder(StartMenu.this);
        mBuidler.setTitle("Choose your language");
        mBuidler.setSingleChoiceItems(langList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                checks which option has been chosen and sets the right language
                if (i == 0){
                    setLocale("en");
                    recreate();
                }
                if (i == 1){
                    setLocale("nl");
                    recreate();
                }
                if (i == 2){
                    setLocale("fr");
                    recreate();
                }
//                end the dialog as soon as a language has been chosen
                dialogInterface.dismiss();
            }
        });
//        show the dialog
        AlertDialog mDialog = mBuidler.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext(). getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

//        save data to/as shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

//    Load language saved in the shared preferences
    public void loadLocal(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang", "");
        setLocale(language);
    }

//    button to make the user go from menu screen to pokedex list
    public void getPokemonList(View v) {
        startActivity(new Intent(StartMenu.this, MainActivity.class));
    }

    //    button to go to maps
    public void openMaps(View v){
        startActivity(new Intent(StartMenu.this, PokeMapsActivity.class));
    }

//    button to exit & close the app
    public void exitApp(View v){
        System.exit(0);
    }
}
