package com.example.asus.isbul.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.isbul.Adapters.BasvuruAdapter;
import com.example.asus.isbul.Models.BasvuruListModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BasvurularFragment extends Fragment {
    String ilanId;
    RecyclerView recyclerView;
    View view;
    List<BasvuruListModel> list;
    BasvuruAdapter basvuruAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_basvurular, container, false);
        tanimla();
        listele(ilanId);
        return view;
    }
    public void tanimla()
    {
        ilanId = getArguments().getString("ilanid").toString();
        recyclerView=view.findViewById(R.id.basvuruRecylerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        list=new ArrayList<>();


    }

    public void listele(String id)
    {
       Call<List<BasvuruListModel>> req= ManagerAll.getInstance().basvuruListele(id);
       req.enqueue(new Callback<List<BasvuruListModel>>() {
           @Override
           public void onResponse(Call<List<BasvuruListModel>> call, Response<List<BasvuruListModel>> response) {

              // Log.i("deneme",response.body().toString());
               if(response.isSuccessful())
               {
                   list=response.body();
                   basvuruAdapter=new BasvuruAdapter(list,getContext());
                   recyclerView.setAdapter(basvuruAdapter);
               }

           }

           @Override
           public void onFailure(Call<List<BasvuruListModel>> call, Throwable t) {

           }
       });


    }


}
