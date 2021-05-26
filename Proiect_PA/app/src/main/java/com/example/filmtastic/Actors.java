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


public class Actors extends AppCompatActivity {
   FirebaseFirestore db;
   RecyclerView recyclerView;


   TextView name,name1, name2;
   TextView fullname,fullname1, fullname2;
   TextView date_birth, date_birth1,date_birth2;
   TextView nationality, nationality1,nationality2;
   TextView movies, movies1,movies2;
   TextView tvseries,tvseries1,tvseries2;
   ImageView imageView,imageView1,imageView2;
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


        name1=findViewById(R.id.anameview1);
        fullname1=findViewById(R.id.afullnameview1);
        date_birth1=findViewById(R.id.adateview1);
        nationality1=findViewById(R.id.anatview1);
        movies1=findViewById(R.id.amoviesview1);
        tvseries1=findViewById(R.id.atvsview1);
        imageView1=findViewById(R.id.aimg1);


        name2=findViewById(R.id.anameview2);
        fullname2=findViewById(R.id.afullnameview2);
        date_birth2=findViewById(R.id.adateview2);
        nationality2=findViewById(R.id.anatview2);
        movies2=findViewById(R.id.amoviesview2);
        tvseries2=findViewById(R.id.atvsview2);
        imageView2=findViewById(R.id.aimg2);

//        Map<String,Object> actors= new HashMap<>();
//        actors.put("name","Sebastian Stan");
//        actors.put("full_name","Sebastian Stan");
//        actors.put("date_birth","13.08.1982");
//        actors.put("nationality","Romanian");
//        actors.put("movies0","Captain America: The Winter Soldier");
        //actors.put("movies1","Avengers: Infinity War");
//        actors.put("tvseries0","Once Upon A Time");
        //actors.put("tvseries1","The Falcon and The Winter Soldier");
//        actors.put("msize","2");
//        actors.put("tsize","2");
//        actors.put("image","https://retetesivedete.ro/wp-content/uploads/2020/12/sebastian-stan-1024x615.jpg");
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
       showData();


    }

    private void showData() {
        db.collection("ACTORS").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("TAG", document.getId()+"=>"+document.getData());
                    }

                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
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

            DocumentReference documentReference = db.collection("ACTORS").document("1");
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
                            tv.append(System.getProperty("line.separator"));
                            tv.append(doc.get("tvseries" + h));
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

        DocumentReference documentReference1 = db.collection("ACTORS").document("2");
        documentReference1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();

                    StringBuilder nume1 = new StringBuilder("");
                    nume1.append(doc.getString("name"));

                    StringBuilder full1 = new StringBuilder("");
                    full1.append(doc.getString("full_name"));

                    StringBuilder data1 = new StringBuilder("");
                    data1.append(doc.getString("date_birth"));

                    StringBuilder nat1 = new StringBuilder("");
                    nat1.append(doc.getString("nationality"));


                    StringBuilder nr1 = new StringBuilder("");

                    int sizem = Integer.parseInt(String.valueOf(nr1.append(doc.getString("msize"))));
                    int sizet = Integer.parseInt(String.valueOf(nr1.append(doc.getString("tsize"))));


                    StringBuilder mov1 = new StringBuilder("");

                    for (int j = 0; j < sizem; ++j) {
                        mov1.append(System.getProperty("line.separator"));
                        mov1.append(doc.get("movies" + j));
                    }

                    StringBuilder tv1 = new StringBuilder("");

                    for (int h = 0; h < sizet; ++h) {
                        tv1.append(System.getProperty("line.separator"));
                        tv1.append(doc.get("tvseries" + h));
                    }

                    StringBuilder img1 = new StringBuilder();
                    img1.append(doc.getString("image"));


                    name1.setText(nume1.toString());
                    fullname1.setText(full1.toString());
                    date_birth1.setText(data1.toString());
                    nationality1.setText(nat1.toString());
                    movies1.setText(mov1.toString());
                    tvseries1.setText(tv1.toString());

                    Picasso.get().load(String.valueOf(img1)).into(imageView1);


                }

            }
        });


        DocumentReference documentReference2 = db.collection("ACTORS").document("3");
        documentReference2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();

                    StringBuilder nume1 = new StringBuilder("");
                    nume1.append(doc.getString("name"));

                    StringBuilder full1 = new StringBuilder("");
                    full1.append(doc.getString("full_name"));

                    StringBuilder data1 = new StringBuilder("");
                    data1.append(doc.getString("date_birth"));

                    StringBuilder nat1 = new StringBuilder("");
                    nat1.append(doc.getString("nationality"));


                    StringBuilder nr1 = new StringBuilder("");

                    int sizem = Integer.parseInt(String.valueOf(nr1.append(doc.getString("msize"))));
                    int sizet = Integer.parseInt(String.valueOf(nr1.append(doc.getString("tsize"))));


                    StringBuilder mov1 = new StringBuilder("");

                    for (int j = 0; j < sizem; ++j) {
                        mov1.append(System.getProperty("line.separator"));
                        mov1.append(doc.get("movies" + j));
                    }

                    StringBuilder tv1 = new StringBuilder("");

                    for (int h = 0; h < sizet; ++h) {
                        tv1.append(System.getProperty("line.separator"));
                        tv1.append(doc.get("tvseries" + h));
                    }

                    StringBuilder img1 = new StringBuilder();
                    img1.append(doc.getString("image"));


                    name2.setText(nume1.toString());
                    fullname2.setText(full1.toString());
                    date_birth2.setText(data1.toString());
                    nationality2.setText(nat1.toString());
                    movies2.setText(mov1.toString());
                    tvseries2.setText(tv1.toString());

                    Picasso.get().load(String.valueOf(img1)).into(imageView2);


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

}