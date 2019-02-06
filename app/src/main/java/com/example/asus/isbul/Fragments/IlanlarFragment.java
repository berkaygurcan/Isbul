package com.example.asus.isbul.Fragments;

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

import com.example.asus.isbul.Adapters.IlanAdapter;
import com.example.asus.isbul.Models.IlanModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.GetSharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanlarFragment extends Fragment {

RecyclerView ilanlarRecyView;
View view;
List<IlanModel> ilanList;
String id;
IlanAdapter ılanAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_ilanlar, container, false);
        tanimla();
        listele(id);
        return view;
    }
    public void tanimla()
    {
        ilanlarRecyView=view.findViewById(R.id.ilanlarRecyView);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),1);
        ilanlarRecyView.setLayoutManager(layoutManager);

        GetSharedPref getSharedPref = new GetSharedPref(getActivity());
        id = getSharedPref.getSession().getString("id", null);

        ilanList=new ArrayList<>();

    }
    public void listele(String id)
    {
        Call<List<IlanModel>> req= ManagerAll.getInstance().ilanListele(id);
        req.enqueue(new Callback<List<IlanModel>>() {
            @Override
            public void onResponse(Call<List<IlanModel>> call, Response<List<IlanModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        //Log.i("deneme",response.body().toString());
                         ilanList=response.body();
                         ılanAdapter=new IlanAdapter(ilanList,getContext());
                         ilanlarRecyView.setAdapter(ılanAdapter);

                    }

                }
            }

            @Override
            public void onFailure(Call<List<IlanModel>> call, Throwable t) {

            }
        });
    }


}
