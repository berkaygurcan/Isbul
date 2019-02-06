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
import com.example.asus.isbul.Models.EgitimModel;
import com.example.asus.isbul.Models.SilModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EgitimAdapter extends RecyclerView.Adapter<EgitimAdapter.ViewHolder> {

    List<EgitimModel> list;
    Context context;

    public EgitimAdapter(List<EgitimModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout tanımlaması yapılır
        View view = LayoutInflater.from(context).inflate(R.layout.recyegitimlayout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //atama işlemleri gerçekleştirilir(Set işlemleri)
        holder.universityName.setText(list.get(position).getUniversite().toString());
        holder.bolumName.setText(list.get(position).getBolum().toString());
        holder.okumaYili.setText(list.get(position).getBaslangic() + " - " + list.get(position).getBitis() + " yılları arası okudum");
        holder.egitimSilImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil(list.get(position).getId().toString(), position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView universityName, bolumName, okumaYili;

        ImageView egitimSilImageView;

        //itemview ile listview in her elemanı için layout ile oluşturduğumuz view tanımlanması gerçekleştirilecek
        public ViewHolder(View itemView) {
            super(itemView);
            universityName = itemView.findViewById(R.id.universityName);
            bolumName = itemView.findViewById(R.id.bolumName);
            okumaYili = itemView.findViewById(R.id.okumaYili);
            egitimSilImageView = itemView.findViewById(R.id.egitimSilImageView);

        }
    }

    public void sil(String id, final int position) {
        Call<SilModel> req = ManagerAll.getInstance().egitimSil(id);
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
