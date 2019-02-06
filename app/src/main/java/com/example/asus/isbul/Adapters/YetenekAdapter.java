package com.example.asus.isbul.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.isbul.Models.DeneyimModel;
import com.example.asus.isbul.Models.SilModel;
import com.example.asus.isbul.Models.YetenekModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YetenekAdapter extends RecyclerView.Adapter<YetenekAdapter.ViewHolder> {

    List<YetenekModel> list;
    Context context;

    public YetenekAdapter(List<YetenekModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout tanımlaması yapılır
        View view = LayoutInflater.from(context).inflate(R.layout.recyyeteneklayout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //atama işlemleri gerçekleştirilir(Set işlemleri)
        holder.yetenekNameText.setText(list.get(position).getYetenek().toString());
        if (!list.get(position).getYetenekcount().equals("0")) {
            holder.yetenekCountText.setText(list.get(position).getYetenekcount().toString() + " kişi " + list.get(position).getYetenek() + " yeteneğinizi onayladı ");

        } else {
            holder.yetenekCountText.setText(list.get(position).getYetenek() + " yeteneğinizi kimse onaylamadı ");
        }
        holder.silYetenekImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil(list.get(position).getYetenekid().toString(), position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView yetenekNameText, yetenekCountText;
        ImageView silYetenekImage;


        //itemview ile listview in her elemanı için layout ile oluşturduğumuz view tanımlanması gerçekleştirilecek
        public ViewHolder(View itemView) {
            super(itemView);
            yetenekNameText = itemView.findViewById(R.id.yetenekNameText);
            yetenekCountText = itemView.findViewById(R.id.yetenekCountText);
            silYetenekImage = itemView.findViewById(R.id.silYetenekImage);

        }
    }

    public void sil(String id, final int position) {
        Call<SilModel> req = ManagerAll.getInstance().yetenekSil(id);
        req.enqueue(new Callback<SilModel>() {
            @Override
            public void onResponse(Call<SilModel> call, Response<SilModel> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, response.body().getText().toString(), Toast.LENGTH_LONG).show();
                    deleteToList(position);
                }

            }

            @Override
            public void onFailure(Call<SilModel> call, Throwable t) {

            }
        });
    }

    public void deleteToList(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();//silindikden sonra itemlerin indexlerinin yeniden düzenlenmesi yani listenin yenilenmesi için kullandık


    }
}
