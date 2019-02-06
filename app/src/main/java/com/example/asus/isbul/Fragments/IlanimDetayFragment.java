package com.example.asus.isbul.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.asus.isbul.R;


public class IlanimDetayFragment extends Fragment {

    View view;
    EditText   ilanGuncelleDetayEgitimSeviyesiEditText,ilanGuncelleDetayTecrubeEditText, ilanGuncelleDetayPozisyonSeviyesiEditText,
            ilanGuncelleDetayDepartmanEditText, ilanGuncelleDetayCalismaSekliEditText,ilanGuncelleDetayFirmaSektoruEditText,
            ilanGuncelleDetayNitelikEditText,ilanGuncelleDetayIsTanimiEditText, ılanGunceleDetayIlanAdresEditText,
            ılanGuncelleDetayIlanAciklamaEditText,ılanDetayGunceleIlanBaslikEditText;


    ImageView  ilanGuncelleDetayKriterEkleImage,ilanGuncelleDetayPozisyonEkleImage,ilanGuncelleDetayNitelikEkleImage,
            ilanGuncelleDetayIsTanimiEkleImage,ilanGuncelleDetayIlanBilgiEkleImage;

    RecyclerView ilanGuncelleDetayNitelikRecyView;

    Button ilanDetayGuncelleButon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ilanim_detay, container, false);
        tanimla();
        return  view;
    }

    public void tanimla()
    {
        ilanGuncelleDetayEgitimSeviyesiEditText=view.findViewById(R.id.ilanGuncelleDetayEgitimSeviyesiEditText);
        ilanGuncelleDetayTecrubeEditText=view.findViewById(R.id.ilanGuncelleDetayTecrubeEditText);
        ilanGuncelleDetayPozisyonSeviyesiEditText=view.findViewById(R.id.ilanGuncelleDetayPozisyonSeviyesiEditText);
        ilanGuncelleDetayDepartmanEditText=view.findViewById(R.id.ilanGuncelleDetayDepartmanEditText);
        ilanGuncelleDetayCalismaSekliEditText=view.findViewById(R.id.ilanGuncelleDetayCalismaSekliEditText);
        ilanGuncelleDetayFirmaSektoruEditText=view.findViewById(R.id.ilanGuncelleDetayFirmaSektoruEditText);
        ılanGuncelleDetayIlanAciklamaEditText=view.findViewById(R.id.ılanGuncelleDetayIlanAciklamaEditText);

        ilanGuncelleDetayNitelikEditText=view.findViewById(R.id.ilanGuncelleDetayNitelikEditText);
        ilanGuncelleDetayIsTanimiEditText=view.findViewById(R.id.ilanGuncelleDetayIsTanimiEditText);
        ılanGunceleDetayIlanAdresEditText=view.findViewById(R.id.ılanGunceleDetayIlanAdresEditText);
        ılanDetayGunceleIlanBaslikEditText=view.findViewById(R.id.ılanDetayGunceleIlanBaslikEditText);

        ilanGuncelleDetayKriterEkleImage=view.findViewById(R.id.ilanGuncelleDetayKriterEkleImage);
        ilanGuncelleDetayPozisyonEkleImage=view.findViewById(R.id.ilanGuncelleDetayPozisyonEkleImage);
        ilanGuncelleDetayNitelikEkleImage=view.findViewById(R.id.ilanGuncelleDetayNitelikEkleImage);
        ilanGuncelleDetayIsTanimiEkleImage=view.findViewById(R.id.ilanGuncelleDetayIsTanimiEkleImage);
        ilanGuncelleDetayIlanBilgiEkleImage=view.findViewById(R.id.ilanGuncelleDetayIlanBilgiEkleImage);

        ilanDetayGuncelleButon=view.findViewById(R.id.ilanDetayGuncelleButon);



    }

    
}
