package com.example.asus.isbul.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.isbul.Models.KayitOlModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.Warnings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KayitOlActivity extends AppCompatActivity {

    private EditText registerMailAdress, registerUserName, registerPassword;
    private Button registerButton;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        tanimla();
    }

    public void tanimla() {
        registerMailAdress = findViewById(R.id.registerMailAdress);
        registerUserName = findViewById(R.id.registerUserName);
        registerPassword = findViewById(R.id.registerPassword);
        registerButton = findViewById(R.id.registerButton);

        registerText = findViewById(R.id.registerText);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ıntent = new Intent(KayitOlActivity.this, GirisYapActivity.class);
                startActivity(ıntent);
                finish();
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail="",kadi="",pass="";
                mail=registerMailAdress.getText().toString();
                kadi=registerUserName.getText().toString();
                pass=registerPassword.getText().toString();


                if(!mail.equals("")&& !kadi.equals("") && !pass.equals(""))
                {
                    registerMailAdress.setText("");
                    registerUserName.setText("");
                    registerPassword.setText("");

                    kayitOl(mail,kadi,pass);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Tüm alanların doldurulması zorunludur.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void kayitOl(String mail, String kadi, String pass) {

        Call<KayitOlModel> req = ManagerAll.getInstance().kayitOl(mail, kadi, pass);
        req.enqueue(new Callback<KayitOlModel>() {
            @Override
            public void onResponse(Call<KayitOlModel> call, Response<KayitOlModel> response) {
                if (response.body().isTf())//gelen cevabın body sinde tf true ise
                {
                    Toast.makeText(getApplicationContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<KayitOlModel> call, Throwable t) {
                Log.i("hata", String.valueOf(t));
                Toast.makeText(getApplicationContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
            }
        });
    }
}
