package com.example.asus.isbul.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.isbul.Adapters.DeneyimAdapter;
import com.example.asus.isbul.Adapters.EgitimAdapter;
import com.example.asus.isbul.Adapters.YetenekAdapter;
import com.example.asus.isbul.Models.DeneyimEkleModel;
import com.example.asus.isbul.Models.DeneyimModel;
import com.example.asus.isbul.Models.EgitimEkleModel;
import com.example.asus.isbul.Models.EgitimModel;
import com.example.asus.isbul.Models.KullaniciBilgiModel;
import com.example.asus.isbul.Models.YetenekEkleModel;
import com.example.asus.isbul.Models.YetenekModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.GetSharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KullaniciBilgiFragment extends Fragment {
    ImageView deneyimEkleImageView, egitimEkleImage, yetenekEkleImage;
    View view;
    String id;
    RecyclerView deneyimRecylerView, egitimRecylerView, yetenekRecylerView;
    List<DeneyimModel> list;
    List<EgitimModel> egitimList;
    List<YetenekModel> yetenekList;
    DeneyimAdapter deneyimAdapter;
    EgitimAdapter egitimAdapter;
    YetenekAdapter yetenekAdapter;
    TextView   userEmailAdress,userUserName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kullanici_bilgi, container, false);
        tanimla();
        action();
        deneyimListele(id);
        egitimListele(id);
        yetenekListele(id);
        kullaniciBilgiGetir(id);
        return view;
    }

    public void tanimla() {
        deneyimEkleImageView = view.findViewById(R.id.deneyimEkleImageView);
        egitimEkleImage = view.findViewById(R.id.egitimEkleImage);
        yetenekEkleImage = view.findViewById(R.id.yetenekEkleImage);

        userEmailAdress = view.findViewById(R.id.userEmailAdress);
        userUserName = view.findViewById(R.id.userUserName);

        GetSharedPref getSharedPref = new GetSharedPref(getActivity());
        id = getSharedPref.getSession().getString("id", null);

        deneyimRecylerView = view.findViewById(R.id.deneyimRecylerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        //layout managerlar unique birbiri yerine kullanamıyoruzs
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getContext(), 1);
        RecyclerView.LayoutManager layoutManager3 = new GridLayoutManager(getContext(), 1);
        deneyimRecylerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        egitimList = new ArrayList<>();
        egitimRecylerView = view.findViewById(R.id.egitimRecylerView);
        egitimRecylerView.setLayoutManager(layoutManager2);

        yetenekRecylerView = view.findViewById(R.id.yetenekRecylerView);
        yetenekRecylerView.setLayoutManager(layoutManager3);
        yetenekList = new ArrayList<>();


    }

    public void action() {
        deneyimEkleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deneyimEkleAlert();

            }
        });
        egitimEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                egitimEkleAlert();
            }
        });

        yetenekEkleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yetenekEkleAlert();
            }
        });
    }

    public void deneyimEkleAlert() {
        //alert diyalog acilması icin kodlama yapmamız lazım
        LayoutInflater layoutInflater = this.getLayoutInflater();//?
        View view = layoutInflater.inflate(R.layout.deneyimeklelayout, null);

        final EditText sirketIsmi = view.findViewById(R.id.deneyimEkleSirket);
        final EditText titleText = view.findViewById(R.id.deneyimEkleTitle);
        final EditText tecrubeYili = view.findViewById(R.id.deneyimEkleYil);
        Button deneyimEkleButon = view.findViewById(R.id.deneyimEkleButon);


        deneyimEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tecrube = "", sirket = "", title = "";
                tecrube = tecrubeYili.getText().toString();
                sirket = sirketIsmi.getText().toString();
                title = titleText.getText().toString();
                if (!tecrube.equals("") && !sirket.equals("") && !title.equals("")) {

                    deneyimEkle(id, sirket, title, tecrube);

                    tecrubeYili.setText("");
                    sirketIsmi.setText("");
                    titleText.setText("");
                    //real time degil ondan tekrar listeletmek icin kullandık
                    deneyimListele(id);
                } else {
                    Toast.makeText(getContext(), "Tüm alanların doldurulması zorunludur.", Toast.LENGTH_LONG).show();
                }


            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        //artık alert dialogumuzu açabiliriz
        final AlertDialog alertDialog = alert.create();


        alertDialog.show();
    }


    public void egitimEkleAlert() {
        //alert diyalog acilması icin kodlama yapmamız lazım
        LayoutInflater layoutInflater = this.getLayoutInflater();//?
        View view = layoutInflater.inflate(R.layout.egitimeklelayout, null);

        final EditText egitimEkleUniversiteText = view.findViewById(R.id.egitimEkleUniversiteText);
        final EditText egitimEkleBolumText = view.findViewById(R.id.egitimEkleBolumText);
        final EditText egitimEkleBaslangicText = view.findViewById(R.id.egitimEkleBaslangicText);
        final EditText egitimEkleBitisText = view.findViewById(R.id.egitimEkleBitisText);
        Button egitimEkleButon = view.findViewById(R.id.egitimEkleButon);


        egitimEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String universite = "", bolum = "", baslangic = "", bitis = "";
                universite = egitimEkleUniversiteText.getText().toString();
                bolum = egitimEkleBolumText.getText().toString();
                baslangic = egitimEkleBaslangicText.getText().toString();
                bitis = egitimEkleBitisText.getText().toString();
                if (!universite.equals("") && !bolum.equals("") && !baslangic.equals("") && !bitis.equals("")) {

                    egitimEkle(universite, bolum, baslangic, bitis, id);

                    egitimEkleUniversiteText.setText("");
                    egitimEkleBolumText.setText("");
                    egitimEkleBaslangicText.setText("");
                    egitimEkleBitisText.setText("");
                    //real time degil ondan tekrar listeletmek icin kullandık
                    egitimListele(id);
                } else {
                    Toast.makeText(getContext(), "Tüm alanların doldurulması zorunludur.", Toast.LENGTH_LONG).show();
                }


            }
        });
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        //artık alert dialogumuzu açabiliriz
        final AlertDialog alertDialog = alert.create();


        alertDialog.show();
    }

    public void yetenekEkleAlert() {
        //alert diyalog acilması icin kodlama yapmamız lazım
        LayoutInflater layoutInflater = this.getLayoutInflater();//?
        View view = layoutInflater.inflate(R.layout.yetenekeklealert, null);


        final EditText yetenekText = view.findViewById(R.id.yetenekText);

        Button yetenekEkleButon = view.findViewById(R.id.yetenekEkleButon);


        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(true);
        //artık alert dialogumuzu açabiliriz
        final AlertDialog alertDialog = alert.create();

        yetenekEkleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yetenek = "";
                yetenek = yetenekText.getText().toString();

                if (!yetenek.equals("")) {

                    yetenekEkle(id, yetenek);
                    yetenekText.setText("");
                    //real time degil ondan tekrar listeletmek icin kullandık
                    yetenekListele(id);
                    alertDialog.cancel();
                } else {
                    Toast.makeText(getContext(), "Tüm alanların doldurulması zorunludur.", Toast.LENGTH_LONG).show();
                }


            }
        });


        alertDialog.show();
    }

    public void deneyimEkle(String id, String sirket, String title, String yil) {
        Call<DeneyimEkleModel> req = ManagerAll.getInstance().deneyimEkle(id, sirket, title, yil);
        req.enqueue(new Callback<DeneyimEkleModel>() {
            @Override
            public void onResponse(Call<DeneyimEkleModel> call, Response<DeneyimEkleModel> response) {
                Toast.makeText(getContext(), response.body().getText(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DeneyimEkleModel> call, Throwable t) {


            }
        });
    }

    public void deneyimListele(String idm) {
        Call<List<DeneyimModel>> req = ManagerAll.getInstance().deneyimGetir(idm);
        req.enqueue(new Callback<List<DeneyimModel>>() {
            @Override
            public void onResponse(Call<List<DeneyimModel>> call, Response<List<DeneyimModel>> response) {
                //  Log.i("Deneyimlerim",response.body().toString()); artık log kullanmicaz
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        list = response.body();
                        deneyimAdapter = new DeneyimAdapter(list, getContext());
                        deneyimRecylerView.setAdapter(deneyimAdapter);
                    }

                }

            }

            @Override
            public void onFailure(Call<List<DeneyimModel>> call, Throwable t) {

            }
        });

    }

    public void egitimEkle(String universite, String bolum, String bas, String bit, String kid) {

        Call<EgitimEkleModel> req = ManagerAll.getInstance().egitimEkle(universite, bolum, bas, bit, kid);
        req.enqueue(new Callback<EgitimEkleModel>() {
            @Override
            public void onResponse(Call<EgitimEkleModel> call, Response<EgitimEkleModel> response) {
                Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<EgitimEkleModel> call, Throwable t) {

            }
        });

    }

    public void egitimListele(String id) {
        Call<List<EgitimModel>> req = ManagerAll.getInstance().egitimGetir(id);
        req.enqueue(new Callback<List<EgitimModel>>() {
            @Override
            public void onResponse(Call<List<EgitimModel>> call, Response<List<EgitimModel>> response) {

                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        //  Log.i("egitimm",response.body().toString()); test içindi
                        egitimList = response.body();
                        egitimAdapter = new EgitimAdapter(egitimList, getContext());
                        egitimRecylerView.setAdapter(egitimAdapter);
                    }


                }
            }

            @Override
            public void onFailure(Call<List<EgitimModel>> call, Throwable t) {

            }
        });


    }

    public void yetenekListele(String id) {
        Call<List<YetenekModel>> req = ManagerAll.getInstance().yetenekGetir(id);
        req.enqueue(new Callback<List<YetenekModel>>() {
            @Override
            public void onResponse(Call<List<YetenekModel>> call, Response<List<YetenekModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        yetenekList = response.body();
                        yetenekAdapter = new YetenekAdapter(yetenekList, getContext());
                        yetenekRecylerView.setAdapter(yetenekAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<YetenekModel>> call, Throwable t) {

            }
        });
    }

    public void yetenekEkle(String id, String text) {
        Call<YetenekEkleModel> req = ManagerAll.getInstance().yetenekEkle(id, text);
        req.enqueue(new Callback<YetenekEkleModel>() {
            @Override
            public void onResponse(Call<YetenekEkleModel> call, Response<YetenekEkleModel> response) {

                Toast.makeText(getContext(), response.body().getText().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<YetenekEkleModel> call, Throwable t) {

            }
        });

    }

    public void kullaniciBilgiGetir(String id)
    {
        Call<List<KullaniciBilgiModel>> req=ManagerAll.getInstance().bilgiGetir(id);
       req.enqueue(new Callback<List<KullaniciBilgiModel>>() {
           @Override
           public void onResponse(Call<List<KullaniciBilgiModel>> call, Response<List<KullaniciBilgiModel>> response) {
               if(response.isSuccessful())
               {
                   userEmailAdress.setText(response.body().get(0).getKullaniciadi());
                   userUserName.setText(response.body().get(0).getMailadres());
               }
           }

           @Override
           public void onFailure(Call<List<KullaniciBilgiModel>> call, Throwable t) {

           }
       });

    }

}
