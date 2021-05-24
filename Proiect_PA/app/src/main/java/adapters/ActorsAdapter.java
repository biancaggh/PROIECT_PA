package adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.filmtastic.R;

import java.util.List;

import models.ActorsModel;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ViewH>{
    private Context mContext;
    private List<ActorsModel> mData;
    RequestOptions option;

    public ActorsAdapter(Context mContext, List<ActorsModel> mData) {
        this.mContext = mContext;
        this.mData = mData;

        option= new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }



    @NonNull
    @Override
    public ActorsAdapter.ViewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater=LayoutInflater.from(mContext);
        view=inflater.inflate(R.layout.actors_model,parent,false);

        return new ActorsAdapter.ViewH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorsAdapter.ViewH holder, int position) {
        holder.name.setText(mData.get(position).getName());
        holder.fullname.setText(mData.get(position).getFullname());
        holder.date_b.setText(mData.get(position).getDate_birth());
        holder.age.setText(mData.get(position).getAge());
        holder.nat.setText(mData.get(position).getNational());
        holder.movies.setText(mData.get(position).getMovies());
        holder.tvseries.setText(mData.get(position).getTvseries());

        Glide.with(mContext).load(mData.get(position).getImageURL()).apply(option).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewH extends RecyclerView.ViewHolder{

        TextView name;
        TextView fullname;
        TextView date_b;
        TextView age;
        TextView nat;
        TextView movies;
        TextView tvseries;
        ImageView imageView;

        public ViewH(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.anameview);
            fullname=itemView.findViewById(R.id.afullnameview);
            date_b=itemView.findViewById(R.id.adateview);
            age=itemView.findViewById(R.id.aageview);
            nat=itemView.findViewById(R.id.anatview);
            movies=itemView.findViewById(R.id.amoviesview);
            tvseries=itemView.findViewById(R.id.atvsview);
            imageView=itemView.findViewById(R.id.aimg);


        }
    }
}
