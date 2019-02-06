package com.example.asus.isbul.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.isbul.Fragments.IlanDetayFragment;
import com.example.asus.isbul.Models.IlanDetayNitelikModel;
import com.example.asus.isbul.Models.IlanModel;
import com.example.asus.isbul.Models.SilModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.ChangeFragments;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanDetayNitelikAdapter extends RecyclerView.Adapter<IlanDetayNitelikAdapter.ViewHolder> {

    List<IlanDetayNitelikModel> list;
    Context context;


    public IlanDetayNitelikAdapter(List<IlanDetayNitelikModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout tanımlaması yapılır
        //kirmizi ama çalışıyor büyük ihtimalle bug var
        View view = LayoutInflater.from(context).inflate(R.layout.recyilandetaynitelik, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //atama işlemleri gerçekleştirilir(Set işlemleri)

        holder.ilanDetayNitelikText.setText(list.get(position).getNitelik());



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ilanDetayNitelikText;

        //itemview ile listview in her elemanı için layout ile oluşturduğumuz view tanımlanması gerçekleştirilecek
        public ViewHolder(View itemView) {
            super(itemView);
            ilanDetayNitelikText = itemView.findViewById(R.id.ilanDetayNitelikText);


        }
    }

  /*  public void sil(String id,final int position) {
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
    } */
    public void deleteToList(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();//silindikden sonra itemlerin indexlerinin yeniden düzenlenmesi yani listenin yenilenmesi için kullandık


    }
}
