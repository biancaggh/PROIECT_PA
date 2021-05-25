package adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.filmtastic.Actors;
import com.example.filmtastic.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import models.ActorsModel;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.MyViewHolder> {
    Context context;
    ArrayList<ActorsModel> actorsModels;



    public ActorsAdapter(Context context, ArrayList<ActorsModel> actorsModels) {
        this.context = context;
        this.actorsModels = actorsModels;
    }

    @NonNull
    @Override
    public ActorsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.actors_model,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ActorsAdapter.MyViewHolder holder, int position) {

        ActorsModel actor =actorsModels.get(position);
        holder.name.setText(actor.name);
        holder.full_name.setText(actor.full_name);
        holder.date_birth.setText(actor.date_birth);
        holder.nationality.setText(actor.nationality);
        holder.movies.setText(actor.movies);
        holder.tvseries.setText(actor.tvseries);

    }

    @Override
    public int getItemCount() {
        return actorsModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView full_name;
        TextView date_birth;
        TextView nationality;
        TextView movies;
        TextView tvseries;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.anameview);
            full_name=itemView.findViewById(R.id.afullnameview);
            date_birth=itemView.findViewById(R.id.adateview);
            nationality=itemView.findViewById(R.id.anatview);
            movies=itemView.findViewById(R.id.amoviesview);
            tvseries=itemView.findViewById(R.id.atvsview);
            imageView=itemView.findViewById(R.id.aimg);
        }
    }
}