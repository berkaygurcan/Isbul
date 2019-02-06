package com.example.asus.isbul.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.isbul.Adapters.IlanDetayNitelikAdapter;
import com.example.asus.isbul.Models.BasvuruModel;
import com.example.asus.isbul.Models.IlanDetayModel;
import com.example.asus.isbul.Models.IlanDetayNitelikModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.ChangeFragments;
import com.example.asus.isbul.Utils.GetSharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IlanDetayFragment extends Fragment {
    String ilanId, paylasanId, userId;
    View view;
    TextView ılanDetayIlanBaslik, ılanDetayIlanAciklama, ılanDetayIlanAdres, ilanDetayIsTanimiText, ilanDetayFirmaSektoruText,
            ilanDetayCalismaSekliText, ilanDetayDepartmanText, ilanDetayPozisyonSeviyesiText, ilanDetayTecrubeText, ilanDetayEgitimSeviyesiText, ilanDetayGeriText;
    Button ilanDetayFavoriButon, ilanDetayBasvurButon;

    RecyclerView ilanDetayNitelikRecyView;

    List<IlanDetayNitelikModel> list;
    IlanDetayNitelikAdapter ılanDetayNitelikAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ilan_detay, container, false);
        tanimla();
        ilanDetay(ilanId);
        action();
        return view;
    }

    public void tanimla() {
        ilanId = getArguments().getString("ilanid").toString();
        paylasanId = getArguments().getString("kid").toString();

        GetSharedPref getSharedPref = new GetSharedPref(getActivity());
        userId = getSharedPref.getSession().getString("id", null);

        ılanDetayIlanBaslik = view.findViewById(R.id.ılanDetayIlanBaslik);
        ılanDetayIlanAciklama = view.findViewById(R.id.ılanDetayIlanAciklama);
        ılanDetayIlanAdres = view.findViewById(R.id.ılanDetayIlanAdres);
        ilanDetayIsTanimiText = view.findViewById(R.id.ilanDetayIsTanimiText);
        ilanDetayFirmaSektoruText = view.findViewById(R.id.ilanDetayFirmaSektoruText);
        ilanDetayCalismaSekliText = view.findViewById(R.id.ilanDetayCalismaSekliText);
        ilanDetayDepartmanText = view.findViewById(R.id.ilanDetayDepartmanText);
        ilanDetayPozisyonSeviyesiText = view.findViewById(R.id.ilanDetayPozisyonSeviyesiText);
        ilanDetayTecrubeText = view.findViewById(R.id.ilanDetayTecrubeText);
        ilanDetayEgitimSeviyesiText = view.findViewById(R.id.ilanDetayEgitimSeviyesiText);
        ilanDetayFavoriButon = view.findViewById(R.id.ilanDetayFavoriButon);
        ilanDetayBasvurButon = view.findViewById(R.id.ilanDetayBasvurButon);
        ilanDetayNitelikRecyView = view.findViewById(R.id.ilanDetayNitelikRecyView);
        ilanDetayGeriText = view.findViewById(R.id.ilanDetayGeriText);
        list = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        ilanDetayNitelikRecyView.setLayoutManager(layoutManager);

    }

    public void action() {
        ilanDetayGeriText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragments changeFragments = new ChangeFragments(getContext());
                changeFragments.change(new IlanlarFragment());
            }
        });
        ilanDetayBasvurButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basvuruYap(userId, paylasanId, ilanId);
            }
        });
    }

    public void ilanDetay(String ilanId) {
        Call<List<IlanDetayModel>> req = ManagerAll.getInstance().ilanDetay(ilanId);
        req.enqueue(new Callback<List<IlanDetayModel>>() {
            @Override
            public void onResponse(Call<List<IlanDetayModel>> call, Response<List<IlanDetayModel>> response) {

                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        ılanDetayIlanBaslik.setText(response.body().get(0).getBaslik());
                        ılanDetayIlanAciklama.setText(response.body().get(0).getAciklama());
                        ılanDetayIlanAdres.setText(response.body().get(0).getAdres());
                        ilanDetayIsTanimiText.setText(response.body().get(0).getTanim());
                        ilanDetayFirmaSektoruText.setText(response.body().get(0).getFirmasektoru());
                        ilanDetayCalismaSekliText.setText(response.body().get(0).getCalismasekli());
                        ilanDetayDepartmanText.setText(response.body().get(0).getDepartman());
                        ilanDetayPozisyonSeviyesiText.setText(response.body().get(0).getPozisyonseviyesi());
                        ilanDetayTecrubeText.setText(response.body().get(0).getTecrube());
                        ilanDetayEgitimSeviyesiText.setText(response.body().get(0).getEgitimbilgisi());

                    } else {
                        ChangeFragments changeFragments = new ChangeFragments(getContext());
                        changeFragments.change(new IlanlarFragment());
                        Toast.makeText(getContext(), "Bu ilana ait bir bilgi girilmemiştir.", Toast.LENGTH_LONG).show();
                    }

                }

            }

            @Override
            public void onFailure(Call<List<IlanDetayModel>> call, Throwable t) {

            }
        });

        Call<List<IlanDetayNitelikModel>> req2 = ManagerAll.getInstance().ilanDetayNitelik(ilanId);
        req2.enqueue(new Callback<List<IlanDetayNitelikModel>>() {
            @Override
            public void onResponse(Call<List<IlanDetayNitelikModel>> call, Response<List<IlanDetayNitelikModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        list = response.body();
                        ılanDetayNitelikAdapter = new IlanDetayNitelikAdapter(list, getContext());
                        ilanDetayNitelikRecyView.setAdapter(ılanDetayNitelikAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<IlanDetayNitelikModel>> call, Throwable t) {

            }
        });
    }

    public void basvuruYap(String userid, String paylasanid, String ilanid) {

        Call<BasvuruModel> req = ManagerAll.getInstance().basvuruYap(userid, paylasanid, ilanid);
        req.enqueue(new Callback<BasvuruModel>() {
            @Override
            public void onResponse(Call<BasvuruModel> call, Response<BasvuruModel> response) {

                if (response.isSuccessful()) {
                    if (response.body().isTf()) {
                        Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<BasvuruModel> call, Throwable t) {

            }
        });
    }

}
