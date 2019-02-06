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
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeneyimAdapter extends RecyclerView.Adapter<DeneyimAdapter.ViewHolder> {

    List<DeneyimModel> list;
    Context context;

    public DeneyimAdapter(List<DeneyimModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout tanımlaması yapılır
        View view = LayoutInflater.from(context).inflate(R.layout.recydeneyimlayout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //atama işlemleri gerçekleştirilir(Set işlemleri)
        holder.deneyimTitleText.setText(list.get(position).getTitle().toString());
        holder.deneyimSirketText.setText(list.get(position).getSirket().toString());
        holder.deneyimTecrubeText.setText(list.get(position).getYil().toString() + "yil calistim");
        holder.deneyimSilImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil(list.get(position).getId().toString(),position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView deneyimTitleText, deneyimSirketText, deneyimTecrubeText;
        ImageView deneyimSilImage;

        //itemview ile listview in her elemanı için layout ile oluşturduğumuz view tanımlanması gerçekleştirilecek
        public ViewHolder(View itemView) {
            super(itemView);
            deneyimTitleText = itemView.findViewById(R.id.deneyimTitleText);
            deneyimSirketText = itemView.findViewById(R.id.deneyimSirketText);
            deneyimTecrubeText = itemView.findViewById(R.id.deneyimTecrubeText);
            deneyimSilImage = itemView.findViewById(R.id.deneyimSilImage);

        }
    }

    public void sil(String id,final int position) {
        Call<SilModel> req = ManagerAll.getInstance().deneyimSil(id);
        req.enqueue(new Callback<SilModel>() {
            @Override
            public void onResponse(Call<SilModel> call, Response<SilModel> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(context, response.body().getText().toString(), Toast.LENGTH_LONG).show();
                    deleteToList(position);
                }

            }

            @Override
            public void onFailure(Call<SilModel> call, Throwable t) {

            }
        });
    }
    public void deleteToList(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();//silindikden sonra itemlerin indexlerinin yeniden düzenlenmesi yani listenin yenilenmesi için kullandık


    }
}
