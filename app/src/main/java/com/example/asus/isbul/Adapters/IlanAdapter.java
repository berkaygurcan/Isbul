package com.example.asus.isbul.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.isbul.Fragments.IlanDetayFragment;
import com.example.asus.isbul.Fragments.IlanlarFragment;
import com.example.asus.isbul.Models.DeneyimModel;
import com.example.asus.isbul.Models.IlanModel;
import com.example.asus.isbul.Models.SilModel;
import com.example.asus.isbul.R;
import com.example.asus.isbul.RestApi.ManagerAll;
import com.example.asus.isbul.Utils.ChangeFragments;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IlanAdapter extends RecyclerView.Adapter<IlanAdapter.ViewHolder> {

    List<IlanModel> list;
    Context context;


    public IlanAdapter(List<IlanModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout tanımlaması yapılır
        View view = LayoutInflater.from(context).inflate(R.layout.recyilanlarlayout, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //atama işlemleri gerçekleştirilir(Set işlemleri)

        holder.ilanBaslikText.setText(list.get(position).getBaslik());
        holder.ilanAciklamaText.setText(list.get(position).getAciklama());
        holder.ilanAdresText.setText(list.get(position).getAdres());
        holder.ilanlarAnaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragments changeFragments = new ChangeFragments(context);
                changeFragments.changeWithParameter(new IlanDetayFragment(), list.get(position).getId(), list.get(position).getKid());
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ilanAdresText, ilanAciklamaText, ilanBaslikText;
        LinearLayout ilanlarAnaLayout;


        //itemview ile listview in her elemanı için layout ile oluşturduğumuz view tanımlanması gerçekleştirilecek
        public ViewHolder(View itemView) {
            super(itemView);
            ilanAdresText = itemView.findViewById(R.id.ilanAdresText);
            ilanAciklamaText = itemView.findViewById(R.id.ilanAciklamaText);
            ilanBaslikText = itemView.findViewById(R.id.ilanBaslikText);
            ilanlarAnaLayout = itemView.findViewById(R.id.ilanlarAnaLayout);


        }
    }

    public void sil(String id, final int position) {
        Call<SilModel> req = ManagerAll.getInstance().deneyimSil(id);
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
