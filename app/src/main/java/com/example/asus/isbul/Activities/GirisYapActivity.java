package com.example.asus.isbul.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.isbul.Models.GirisYapModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.GetSharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GirisYapActivity extends AppCompatActivity {

    EditText loginMailAdress, loginPassword;
    Button loginButton;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);
        tanimla();
    }

    public void tanimla() {
        loginMailAdress = findViewById(R.id.loginMailAdress);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        loginText = findViewById(R.id.loginText);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ıntent = new Intent(GirisYapActivity.this, KayitOlActivity.class);
                startActivity(ıntent);
                finish();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail="",pass="";
                mail = loginMailAdress.getText().toString();
                pass = loginPassword.getText().toString();
                loginMailAdress.setText("");
                loginPassword.setText("");
                if(!mail.equals("") && !pass.equals(""))
                {
                    girisYap(mail, pass);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "tüm bilgilerin doldurulması zorunludur", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void girisYap(String mail, String pass) {
        Call<GirisYapModel> req = ManagerAll.getInstance().girisYap(mail, pass);
        req.enqueue(new Callback<GirisYapModel>() {
            @Override
            public void onResponse(Call<GirisYapModel> call, Response<GirisYapModel> response) {
                if (response.body().isTf()) {
                    Intent ıntent = new Intent(GirisYapActivity.this, MainActivity.class);
                    startActivity(ıntent);
                    finish();
                    GetSharedPref getSharedPref = new GetSharedPref(GirisYapActivity.this);
                    //bu nesne aracılıgıyla içinde yazdıgımız metodlara ulaşıp heryerden get ve set işlemi yapabiliriz
                    getSharedPref.setSession(response.body().getId(), response.body().getMailadres(), response.body().getKadi());
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<GirisYapModel> call, Throwable t) {

            }
        });
    }
}
