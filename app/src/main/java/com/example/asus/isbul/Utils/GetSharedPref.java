package com.example.asus.isbul.Utils;

import android.app.Activity;
import android.content.SharedPreferences;

public class GetSharedPref {

    private SharedPreferences sharedPreferences;
    private Activity activity;

    public GetSharedPref(Activity activity) {
        this.activity = activity;
        //her iki metod içinde tekrar tanımlamaktasa kurucu fonksiyon oluştururken bir kere tanımlamamız daha sağlıklı olur
        sharedPreferences = activity.getApplicationContext().getSharedPreferences("session", 0);
    }

    public void setSession(String id, String mail, String kadi) {


        //Setleme işlemleri
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("id",id);
        editor.putString("kadi",kadi);
        editor.putString("mail",mail);
        editor.commit();



    }
    public SharedPreferences getSession()
    {

       return  sharedPreferences;
    }

}
