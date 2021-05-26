package movies;

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

import com.example.filmtastic.Actors;
import com.example.filmtastic.Directors;
import com.example.filmtastic.LoginPage;
import com.example.filmtastic.MainPage;
import com.example.filmtastic.Profile;
import com.example.filmtastic.R;
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


public class MoviesHorror extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;


    TextView title;
    TextView genre;
    TextView director;
    TextView actors;
    TextView runtime;
    TextView rating, synopsys,release;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_horror);


        db=FirebaseFirestore.getInstance();

        title=findViewById(R.id.atitle);
        genre=findViewById(R.id.agenre);
        director=findViewById(R.id.adirector);
        actors=findViewById(R.id.aactors);
        runtime=findViewById(R.id.arun);
        rating=findViewById(R.id.arate);
        synopsys=findViewById(R.id.asin);
        release=findViewById(R.id.arel);
        imageView=findViewById(R.id.aimg);



//
//        Map<String,Object> action= new HashMap<>();
//        action.put("title","Interview with the Vampire: The Vampire Chronicles");
//        action.put("director","Neil Jordan");
//        action.put("genre0","Horror");
//        action.put("genre1","Drama");
//        action.put("actor0","Brad Pitt");
//        action.put("actor1","Tom Cruise");
//        action.put("actor2","Kristen Dunst");
//        action.put("gsize","2");
//        action.put("asize","3");
//        action.put("image","https://www.syfy.com/sites/syfy/files/2020/11/interview-with-the-vampire.jpg");
//        action.put("runtime","123 min");
//        action.put("rating","7.5");
//        action.put("release_date","31.03.1995");
//        action.put("synopsys","A vampire tells his epic life story: love, betrayal, loneliness, and hunger.");
//
//        db.collection("/MOVIES/movies/horror").document("2").set(action).
//                addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Toast.makeText(MoviesHorror.this,"Add new Horror Movie", Toast.LENGTH_LONG).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("ERROR",e.getMessage());
//            }
//        });

        readMovie();
        showData();

    }

    private void showData() {
        db.collection("/MOVIES/movies/horror").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

    private void readMovie() {
        List<String> list = new ArrayList<>();
        db.collection("/MOVIES/movies/horror").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        DocumentReference documentReference = db.collection("/MOVIES/movies/horror").document("1");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();

                    StringBuilder titl = new StringBuilder("");
                    titl.append(doc.getString("title"));

                    StringBuilder nr = new StringBuilder("");

                    int sizeg = Integer.parseInt(String.valueOf(nr.append(doc.getString("gsize"))));
                    int sizea = Integer.parseInt(String.valueOf(nr.append(doc.getString("asize"))));

                    StringBuilder full = new StringBuilder("");
                    full.append(doc.getString("director"));

                    StringBuilder data = new StringBuilder("");
                    data.append(doc.getString("release_date"));

                    StringBuilder nat = new StringBuilder("");
                    nat.append(doc.getString("runtime"));


                    StringBuilder rat=new StringBuilder("");
                    rat.append(doc.getString("rating"));

                    StringBuilder sino=new StringBuilder("");
                    sino.append(doc.getString("synopsys"));

                    StringBuilder mov = new StringBuilder("");

                    for (int j = 0; j < sizeg-1; ++j) {
                        mov.append(doc.get("genre" + j));
                        mov.append("|");
                    }
                    mov.append(doc.get("genre" + (sizeg-1)));

                    StringBuilder tv = new StringBuilder("");

                    for (int h = 0; h < sizea; ++h) {
                        tv.append(System.getProperty("line.separator"));
                        tv.append(doc.get("actor" + h));
                    }

                    StringBuilder img = new StringBuilder();
                    img.append(doc.getString("image"));



                    title.setText(titl.toString());
                    director.setText(full.toString());
                    release.setText(data.toString());
                    runtime.setText(nat.toString());
                    genre.setText(mov.toString());
                    actors.setText(tv.toString());
                    rating.setText(rat.toString());
                    synopsys.setText(sino.toString());

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.item11) {
            Intent intent = new Intent(MoviesHorror.this, MoviesAction.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item12) {
            Intent intent = new Intent(MoviesHorror.this, MoviesAnimated.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item13) {
            Intent intent = new Intent(MoviesHorror.this, MoviesBio.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item14) {
            Intent intent = new Intent(MoviesHorror.this, MoviesComedy.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item15) {
            Intent intent = new Intent(MoviesHorror.this, MoviesDoc.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item16) {
            Intent intent = new Intent(MoviesHorror.this, MoviesDrama.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item17) {
            Intent intent = new Intent(MoviesHorror.this, MoviesFan.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item18) {
            Intent intent = new Intent(MoviesHorror.this, MoviesHorror.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item19) {
            Intent intent = new Intent(MoviesHorror.this, MoviesRomance.class);
            startActivity(intent);
            return true;
        }

        else if(id == R.id.item3){
            Intent intent = new Intent (MoviesHorror.this, Actors.class);
            startActivity(intent);
            return true;
        }
         else if(id == R.id.item4){
            Intent intent = new Intent (MoviesHorror.this, Directors.class);
            startActivity(intent);
            return true;
        }
         else if(id == R.id.item5){
            Intent intent = new Intent (MoviesHorror.this, Profile.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item6){
            Intent intent = new Intent (MoviesHorror.this, MainPage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item7){
            Intent intent = new Intent (MoviesHorror.this, LoginPage.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}