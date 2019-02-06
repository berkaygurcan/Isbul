package com.example.asus.isbul.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.isbul.Fragments.IlanDetayFragment;
import com.example.asus.isbul.Models.BasvuruListModel;
import com.example.asus.isbul.Models.BasvuruSonucModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.ChangeFragments;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasvuruAdapter extends RecyclerView.Adapter<BasvuruAdapter.ViewHolder> {

    List<BasvuruListModel> list;
    Context context;


    public BasvuruAdapter(List<BasvuruListModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout tanımlaması yapılır
        View view = LayoutInflater.from(context).inflate(R.layout.basvurulayout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //atama işlemleri gerçekleştirilir(Set işlemleri)

        holder.basvuruKullaniciText.setText(list.get(position).getKullaniciadi());
        holder.basvuruKullaniciMailText.setText(list.get(position).getMailadres());
        holder.basvuruOnayButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onay(list.get(position).getKullaniciadi(), list.get(position).getMailadres(), list.get(position).getId(), list.get(position).getBaslik(), position);
            }
        });

        holder.basvuruRedButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                red(list.get(position).getKullaniciadi(), list.get(position).getMailadres(), list.get(position).getId(), list.get(position).getBaslik(), position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView basvuruKullaniciText, basvuruKullaniciMailText;
        Button basvuruOnayButon, basvuruRedButon;

        //itemview ile listview in her elemanı için layout ile oluşturduğumuz view tanımlanması gerçekleştirilecek
        public ViewHolder(View itemView) {
            super(itemView);
            basvuruKullaniciText = itemView.findViewById(R.id.basvuruKullaniciText);
            basvuruKullaniciMailText = itemView.findViewById(R.id.basvuruKullaniciMailText);
            basvuruOnayButon = itemView.findViewById(R.id.basvuruOnayButon);
            basvuruRedButon = itemView.findViewById(R.id.basvuruRedButon);


        }
    }

    public void onay(String kid, String mail, String id, String baslik, final int position) {
        Call<BasvuruSonucModel> req = ManagerAll.getInstance().basvuruOnay(kid, mail, id, baslik);
        req.enqueue(new Callback<BasvuruSonucModel>() {
            @Override
            public void onResponse(Call<BasvuruSonucModel> call, Response<BasvuruSonucModel> response) {
                Toast.makeText(context, response.body().getText(), Toast.LENGTH_LONG).show();
                deleteToList(position);
            }

            @Override
            public void onFailure(Call<BasvuruSonucModel> call, Throwable t) {

            }
        });
    }

    public void red(String kid, String mail, String id, String baslik, final int position) {
        Call<BasvuruSonucModel> req = ManagerAll.getInstance().basvuruRed(kid, mail, id, baslik);
        req.enqueue(new Callback<BasvuruSonucModel>() {
            @Override
            public void onResponse(Call<BasvuruSonucModel> call, Response<BasvuruSonucModel> response) {
                Toast.makeText(context, response.body().getText(), Toast.LENGTH_LONG).show();
                deleteToList(position);
            }

            @Override
            public void onFailure(Call<BasvuruSonucModel> call, Throwable t) {

            }
        });
    }

    public void deleteToList(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();//silindikden sonra itemlerin indexlerinin yeniden düzenlenmesi yani listenin yenilenmesi için kullandık


    }

}
