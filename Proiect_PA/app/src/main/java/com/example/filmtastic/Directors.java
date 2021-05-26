package com.example.filmtastic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import movies.MoviesAction;
import movies.MoviesAnimated;
import movies.MoviesBio;
import movies.MoviesComedy;
import movies.MoviesDoc;
import movies.MoviesDrama;
import movies.MoviesFan;
import movies.MoviesHorror;
import movies.MoviesRomance;


public class Directors extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView recyclerView;


    TextView name;
    TextView fullname;
    TextView date_birth;
    TextView nationality;
    TextView movies;
    TextView tvseries;
    TextView moviesSize;
    TextView seriesSize;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directors);

        db=FirebaseFirestore.getInstance();

        name=findViewById(R.id.dname);
        fullname=findViewById(R.id.dfullname);
        date_birth=findViewById(R.id.ddate);
        nationality=findViewById(R.id.dnat);
        movies=findViewById(R.id.dmovies);
        tvseries=findViewById(R.id.dtvseries);
        imageView=findViewById(R.id.dimage);



//        Map<String,Object> directors= new HashMap<>();
//        directors.put("name","Quentin Tarantino");
//        directors.put("full_name","Quentin Jerome Tarantino");
//        directors.put("date_birth","27.03.1963");
//        directors.put("nationality","American");
//        directors.put("movies0","Django Unchained");
//        directors.put("movies1","Pulp Fiction");
//        directors.put("tvseries0","");
//        directors.put("msize","2");
//        directors.put("tsize","1");
//        directors.put("image","https://www.imdb.com/name/nm0000233/mediaviewer/rm4146963200/");
//
//        db.collection("DIRECTORS").document("2").set(directors).
//                addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(Directors.this,"Add new Director",Toast.LENGTH_LONG).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("ERROR",e.getMessage());
//            }
//        });
        readDirector();
        showData();

    }

    private void showData() {
        db.collection("DIRECTORS").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

    private void readDirector() {
        List<String> list = new ArrayList<>();
        db.collection("DIRECTORS").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        DocumentReference documentReference = db.collection("DIRECTORS").document("1");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
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

                    StringBuilder nr2=new StringBuilder("");
                    int sizet = Integer.parseInt(String.valueOf(nr2.append(doc.getString("tsize"))));


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


                    name.setText(nume1.toString());
                    fullname.setText(full1.toString());
                    date_birth.setText(data1.toString());
                    nationality.setText(nat1.toString());
                    movies.setText(mov1.toString());
                    tvseries.setText(tv1.toString());

                    Picasso.get().load(String.valueOf(img1)).into(imageView);



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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.item11) {
            Intent intent = new Intent(Directors.this, MoviesAction.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item12) {
            Intent intent = new Intent(Directors.this, MoviesAnimated.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item13) {
            Intent intent = new Intent(Directors.this, MoviesBio.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item14) {
            Intent intent = new Intent(Directors.this, MoviesComedy.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item15) {
            Intent intent = new Intent(Directors.this, MoviesDoc.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item16) {
            Intent intent = new Intent(Directors.this, MoviesDrama.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item17) {
            Intent intent = new Intent(Directors.this, MoviesFan.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item18) {
            Intent intent = new Intent(Directors.this, MoviesHorror.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item19) {
            Intent intent = new Intent(Directors.this, MoviesRomance.class);
            startActivity(intent);
            return true;
        }

        else if(id == R.id.item3){
            Intent intent = new Intent (Directors.this,Actors.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item4){
            Intent intent = new Intent (Directors.this,Directors.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item5){
            Intent intent = new Intent (Directors.this,Profile.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item6){
            Intent intent = new Intent (Directors.this,MainPage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item7){
            Intent intent = new Intent (Directors.this,LoginPage.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}