package com.example.asus.isbul.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.isbul.Models.IlanPaylasimiModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.ChangeFragments;
import com.example.asus.isbul.Utils.GetSharedPref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IlanPaylasFragment extends Fragment {

    View view;
    TextView ilanPaylasBaslikText, ilanPaylasAciklamaText, ilanPaylasAdresText;
    Button ilanPaylasButon;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ilan_paylas, container, false);
        tanimla();
        action();
        return view;
    }

    public void tanimla() {
        ilanPaylasBaslikText = view.findViewById(R.id.ilanPaylasBaslikText);
        ilanPaylasAciklamaText = view.findViewById(R.id.ilanPaylasAciklamaText);
        ilanPaylasAdresText = view.findViewById(R.id.ilanPaylasAdresText);
        ilanPaylasButon = view.findViewById(R.id.ilanPaylasButon);

        GetSharedPref getSharedPref = new GetSharedPref(getActivity());
        userId = getSharedPref.getSession().getString("id", null);
    }
    public void action()
    {
        ilanPaylasButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baslik="",aciklama="",adres="";
                baslik=ilanPaylasBaslikText.getText().toString();
                aciklama=ilanPaylasAciklamaText.getText().toString();
                adres=ilanPaylasBaslikText.getText().toString();

                if(!baslik.equals("") && !aciklama.equals("") && !adres.equals(""))
                {
                    ilanPaylasBaslikText.setText("");
                    ilanPaylasAciklamaText.setText("");
                    ilanPaylasBaslikText.setText("");
                    paylas(userId,baslik,aciklama,adres);
                }
                else
                {
                    Toast.makeText(getContext(),"Tüm alanların girilmesi zorunludur.",Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    public void paylas(String kid, String baslik, String aciklama, String adres) {
        Call<IlanPaylasimiModel> req = ManagerAll.getInstance().ilanPaylas(kid, baslik, aciklama, adres);
        req.enqueue(new Callback<IlanPaylasimiModel>() {
            @Override
            public void onResponse(Call<IlanPaylasimiModel> call, Response<IlanPaylasimiModel> response) {
                if(response.isSuccessful())
                {
                    if(response.body().isTf())
                    {
                        ChangeFragments changeFragments=new ChangeFragments(getContext());
                        changeFragments.changeWith1Parameter(new IlanPaylasDetayFragment(),response.body().getIlanid());
                    }
                    else
                    {
                        Toast.makeText(getContext(),response.body().getText(),Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<IlanPaylasimiModel> call, Throwable t) {

            }
        });

    }

}
