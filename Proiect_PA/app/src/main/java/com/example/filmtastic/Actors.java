package com.example.filmtastic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapters.ActorsAdapter;
import models.ActorsModel;
import movies.MoviesAction;
import movies.MoviesAnimated;
import movies.MoviesBio;
import movies.MoviesComedy;
import movies.MoviesDoc;
import movies.MoviesDrama;
import movies.MoviesFan;
import movies.MoviesHorror;
import movies.MoviesRomance;
import tvseries.SeriesAction;
import tvseries.SeriesAnimated;
import tvseries.SeriesBio;
import tvseries.SeriesComedy;
import tvseries.SeriesDoc;
import tvseries.SeriesDrama;
import tvseries.SeriesFan;
import tvseries.SeriesHorror;
import tvseries.SeriesRomance;


public class Actors extends AppCompatActivity {
   FirebaseFirestore db;
   RecyclerView recyclerView;


   TextView name;
   TextView fullname;
   TextView date_birth;
   TextView nationality;
   TextView movies;
   TextView tvseries;
   ImageView imageView;
   ArrayList<ActorsModel> actorsModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actors_model);


        db=FirebaseFirestore.getInstance();
        name=findViewById(R.id.anameview);
        fullname=findViewById(R.id.afullnameview);
        date_birth=findViewById(R.id.adateview);
        nationality=findViewById(R.id.anatview);
        movies=findViewById(R.id.amoviesview);
        tvseries=findViewById(R.id.atvsview);
        imageView=findViewById(R.id.aimg);


//        String[] mov={"The Invisible Man"};
//        String[] tvs={"The Handsmaid`s Tale"};
//
//        Map<String,Object> actors= new HashMap<>();
//        actors.put("name","Elisabeth Moss");
//        actors.put("full_name","Elisabeth Singleton Moss");
//        actors.put("date_birth","24.07.1982");
//        actors.put("nationality","American");
//        actors.put("movies",mov);
//        actors.put("tvseries",tvs);
//
//        db.collection("ACTORS").document("3").set(actors).
//                addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(Actors.this,"Add new Actor",Toast.LENGTH_LONG).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("ERROR",e.getMessage());
//            }
//        });
       readActor();


    }


    private void readActor() {
        List<String> list = new ArrayList<>();
        db.collection("ACTORS").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                    }
                    Log.d("TAG", list.toString());
                    System.out.println(list);
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });

            DocumentReference documentReference = db.collection("ACTORS").document("2");
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot doc = task.getResult();

                        StringBuilder nume = new StringBuilder("");
                        nume.append(doc.getString("name"));

                        StringBuilder full = new StringBuilder("");
                        full.append(doc.getString("full_name"));

                        StringBuilder data = new StringBuilder("");
                        data.append(doc.getString("date_birth"));

                        StringBuilder nat = new StringBuilder("");
                        nat.append(doc.getString("nationality"));


                        StringBuilder nr = new StringBuilder("");

                        int sizem = Integer.parseInt(String.valueOf(nr.append(doc.getString("msize"))));
                        int sizet = Integer.parseInt(String.valueOf(nr.append(doc.getString("tsize"))));


                        StringBuilder mov = new StringBuilder("");

                        for (int j = 0; j < sizem; ++j) {
                            mov.append(System.getProperty("line.separator"));
                            mov.append(doc.get("movies" + j));
                        }

                        StringBuilder tv = new StringBuilder("");

                        for (int h = 0; h < sizet; ++h) {
                            data.append(System.getProperty("line.separator"));
                            data.append(doc.get("tvseries" + h));
                        }

                        StringBuilder img = new StringBuilder();
                        img.append(doc.getString("image"));


                        name.setText(nume.toString());
                        fullname.setText(full.toString());
                        date_birth.setText(data.toString());
                        nationality.setText(nat.toString());
                        movies.setText(mov.toString());
                        tvseries.setText(tv.toString());

                        Picasso.get().load(String.valueOf(img)).into(imageView);


                    }

                }
            });

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item11) {
            Intent intent = new Intent(Actors.this, MoviesAction.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item12) {
            Intent intent = new Intent(Actors.this, MoviesAnimated.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item13) {
            Intent intent = new Intent(Actors.this, MoviesBio.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item14) {
            Intent intent = new Intent(Actors.this, MoviesComedy.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item15) {
            Intent intent = new Intent(Actors.this, MoviesDoc.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item16) {
            Intent intent = new Intent(Actors.this, MoviesDrama.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item17) {
            Intent intent = new Intent(Actors.this, MoviesFan.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item18) {
            Intent intent = new Intent(Actors.this, MoviesHorror.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item19) {
            Intent intent = new Intent(Actors.this, MoviesRomance.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item21) {
            Intent intent = new Intent(Actors.this, SeriesAction.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item22) {
            Intent intent = new Intent(Actors.this, SeriesAnimated.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item23) {
            Intent intent = new Intent(Actors.this, SeriesBio.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item24) {
            Intent intent = new Intent(Actors.this, SeriesComedy.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item25) {
            Intent intent = new Intent(Actors.this, SeriesDoc.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item26) {
            Intent intent = new Intent(Actors.this, SeriesDrama.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item27) {
            Intent intent = new Intent(Actors.this, SeriesFan.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item28) {
            Intent intent = new Intent(Actors.this, SeriesHorror.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item29) {
            Intent intent = new Intent(Actors.this, SeriesRomance.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item3){
            Intent intent = new Intent (Actors.this,Actors.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item4){
            Intent intent = new Intent (Actors.this,Directors.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item5){
            Intent intent = new Intent (Actors.this,Profile.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item6){
            Intent intent = new Intent (Actors.this,MainPage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item7){
            Intent intent = new Intent (Actors.this,LoginPage.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
}