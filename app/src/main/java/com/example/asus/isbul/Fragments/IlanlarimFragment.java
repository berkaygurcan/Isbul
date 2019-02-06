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
import com.example.asus.isbul.Adapters.IlanlarimAdapter;
import com.example.asus.isbul.Models.IlanModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.GetSharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IlanlarimFragment extends Fragment {

    RecyclerView ilanlarimRecyView;
    View view;
    String id;
    List<IlanModel> list;
    IlanlarimAdapter ılanlarimAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ilanlarim, container, false);
        tanimla();
        ilanlarimiListele(id);
        return view;
    }

    public void tanimla() {
        ilanlarimRecyView = view.findViewById(R.id.ilanlarimRecyView);
        ilanlarimRecyView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        GetSharedPref getSharedPref = new GetSharedPref(getActivity());
        id = getSharedPref.getSession().getString("id", null);

        list=new ArrayList<>();
    }
    public void ilanlarimiListele(String id)
    {
        Log.i("deneme",id);
        Call<List<IlanModel>> req= ManagerAll.getInstance().ilanlarim(id);
        req.enqueue(new Callback<List<IlanModel>>() {

            @Override
            public void onResponse(Call<List<IlanModel>> call, Response<List<IlanModel>> response) {
                if(response.body().size()>0)
                {

                    //Log.i("deneme",response.body().toString());
                    list=response.body();
                    ılanlarimAdapter=new IlanlarimAdapter(list,getContext(),getActivity());
                    ilanlarimRecyView.setAdapter(ılanlarimAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<IlanModel>> call, Throwable t) {

            }
        });
    }


}
