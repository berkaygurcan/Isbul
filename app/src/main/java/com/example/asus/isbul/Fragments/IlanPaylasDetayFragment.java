package com.example.asus.isbul.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.isbul.Models.DetayModel;
import com.example.asus.isbul.Models.IlanDetayModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.ChangeFragments;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IlanPaylasDetayFragment extends Fragment {

    EditText ilanDetayTecrubeEditText, ilanDetayEgitimSeviyesiEditText, ilanDetayFirmaSektoruEditText,
            ilanDetayCalismaSekliEditText, ilanDetayDepartmanEditText, ilanDetayPozisyonSeviyesiEditText, ilanDetayIsTanimiEditText, ilanDetayNitelikEditText;
    ImageView ilanDetayKriterEkleImage, ilanDetayPozisyonEkleImage, ilanDetayIsTanimiEkleImage, ilanDetayNitelikEkleImage;
    String ilanId;//fragment deişikliginde gönderiyoruz zaten
    View view;
    Button ilanDetayYayınlaButon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ilan_paylas_detay, container, false);
        tanimla();
        action();
        return view;
    }

    public void tanimla() {
        ilanDetayTecrubeEditText = view.findViewById(R.id.ilanDetayTecrubeEditText);
        ilanDetayEgitimSeviyesiEditText = view.findViewById(R.id.ilanDetayEgitimSeviyesiEditText);
        ilanDetayKriterEkleImage = view.findViewById(R.id.ilanDetayKriterEkleImage);

        ilanDetayFirmaSektoruEditText = view.findViewById(R.id.ilanDetayFirmaSektoruEditText);
        ilanDetayCalismaSekliEditText = view.findViewById(R.id.ilanDetayCalismaSekliEditText);
        ilanDetayDepartmanEditText = view.findViewById(R.id.ilanDetayDepartmanEditText);
        ilanDetayPozisyonSeviyesiEditText = view.findViewById(R.id.ilanDetayPozisyonSeviyesiEditText);
        ilanDetayPozisyonEkleImage = view.findViewById(R.id.ilanDetayPozisyonEkleImage);

        ilanDetayIsTanimiEditText = view.findViewById(R.id.ilanDetayIsTanimiEditText);
        ilanDetayNitelikEditText = view.findViewById(R.id.ilanDetayNitelikEditText);

        ilanDetayIsTanimiEkleImage = view.findViewById(R.id.ilanDetayIsTanimiEkleImage);
        ilanDetayNitelikEkleImage = view.findViewById(R.id.ilanDetayNitelikEkleImage);

        ilanDetayYayınlaButon = view.findViewById(R.id.ilanDetayYayınlaButon);


        ilanId = getArguments().getString("ilanid").toString();
    }

    public void action() {
        ilanDetayKriterEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tecrube, egitim;
                tecrube = ilanDetayTecrubeEditText.getText().toString();
                egitim = ilanDetayEgitimSeviyesiEditText.getText().toString();
                if (!tecrube.equals("") && !egitim.equals("")) {
                    kriterEkle(ilanId, "kriter", tecrube, egitim);
                } else {
                    Toast.makeText(getContext(), "Tüm alanların doldurulması zorunludur.", Toast.LENGTH_LONG).show();
                }


            }
        });

        ilanDetayPozisyonEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firmaSektor, calismaSekli, departman, pozisyonSeviye;

                firmaSektor = ilanDetayFirmaSektoruEditText.getText().toString();
                calismaSekli = ilanDetayCalismaSekliEditText.getText().toString();
                departman = ilanDetayDepartmanEditText.getText().toString();
                pozisyonSeviye = ilanDetayPozisyonSeviyesiEditText.getText().toString();

                if (!firmaSektor.equals("") && !calismaSekli.equals("") && !departman.equals("") && !pozisyonSeviye.equals("")) {
                    pozisyonBilgiEkle(firmaSektor, "pozisyon", calismaSekli, departman, pozisyonSeviye, ilanId);

                } else {
                    Toast.makeText(getContext(), "Tüm alanların doldurulması zorunludur.", Toast.LENGTH_LONG).show();
                }

            }
        });
        ilanDetayNitelikEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nitelik;
                nitelik = ilanDetayNitelikEditText.getText().toString();
                if (!nitelik.equals("")) {
                    nitelikEkle(ilanId, "nitelik", nitelik);
                    ilanDetayNitelikEditText.setText("");
                } else {
                    Toast.makeText(getContext(), "Tüm alanların doldurulması zorunludur.", Toast.LENGTH_LONG).show();

                }

            }
        });

        ilanDetayIsTanimiEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanim;
                tanim = ilanDetayIsTanimiEditText.getText().toString();
                if (!tanim.equals("")) {
                    tanimEkle(ilanId, "tanim", tanim);
                } else {
                    Toast.makeText(getContext(), "Tüm alanların doldurulması zorunludur.", Toast.LENGTH_LONG).show();

                }


            }
        });
        ilanDetayYayınlaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kontrol(ilanId);
            }
        });

    }

    public void kriterEkle(String ilanId, String text, String tecrube, String egitimbilgisi) {
        Call<DetayModel> req = ManagerAll.getInstance().kriterEkle(ilanId, text, tecrube, egitimbilgisi);
        req.enqueue(new Callback<DetayModel>() {
            @Override
            public void onResponse(Call<DetayModel> call, Response<DetayModel> response) {

                Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<DetayModel> call, Throwable t) {

            }
        });
    }

    public void pozisyonBilgiEkle(String firmasektoru, String text, String calismasekli, String departman, String pozisyonseviyesi, String ilanId) {

        Call<DetayModel> req = ManagerAll.getInstance().pozisyonBilgiEkle(firmasektoru, text, calismasekli, departman, pozisyonseviyesi, ilanId);
        req.enqueue(new Callback<DetayModel>() {
            @Override
            public void onResponse(Call<DetayModel> call, Response<DetayModel> response) {

                Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DetayModel> call, Throwable t) {

            }
        });
    }

    public void tanimEkle(String ilanid, String text, String tanim) {
        Call<DetayModel> req = ManagerAll.getInstance().tanimEkle(ilanid, text, tanim);
        req.enqueue(new Callback<DetayModel>() {
            @Override
            public void onResponse(Call<DetayModel> call, Response<DetayModel> response) {

                Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<DetayModel> call, Throwable t) {

            }
        });
    }

    public void nitelikEkle(String ilanid, String text, String nitelik) {
        Call<DetayModel> req = ManagerAll.getInstance().nitelikEkle(ilanid, text, nitelik);
        req.enqueue(new Callback<DetayModel>() {
            @Override
            public void onResponse(Call<DetayModel> call, Response<DetayModel> response) {

                Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DetayModel> call, Throwable t) {

            }
        });
    }

    public void kontrol(String ilanId) {
        Call<List<IlanDetayModel>> req = ManagerAll.getInstance().ilanDetay(ilanId);
        req.enqueue(new Callback<List<IlanDetayModel>>() {
            @Override
            public void onResponse(Call<List<IlanDetayModel>> call, Response<List<IlanDetayModel>> response) {

                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {

                        ChangeFragments changeFragments = new ChangeFragments(getContext());
                        changeFragments.change(new IlanlarimFragment());

                    }


                } else {


                    Toast.makeText(getContext(), "ilanınızın yayınlanabilmesi için tüm detayların girilmesi zorunludur", Toast.LENGTH_LONG).show();
                }

            }


            @Override
            public void onFailure(Call<List<IlanDetayModel>> call, Throwable t) {

            }
        });


    }
}
