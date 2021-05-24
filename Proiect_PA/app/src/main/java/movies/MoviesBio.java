package movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.filmtastic.Actors;
import com.example.filmtastic.Directors;
import com.example.filmtastic.LoginPage;
import com.example.filmtastic.MainPage;
import com.example.filmtastic.Profile;
import com.example.filmtastic.R;
import tvseries.SeriesAction;
import tvseries.SeriesAnimated;
import tvseries.SeriesBio;
import tvseries.SeriesComedy;
import tvseries.SeriesDoc;
import tvseries.SeriesDrama;
import tvseries.SeriesFan;
import tvseries.SeriesHorror;
import tvseries.SeriesRomance;

public class MoviesBio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_bio);
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
            Intent intent = new Intent(MoviesBio.this, MoviesAction.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item12) {
            Intent intent = new Intent(MoviesBio.this, MoviesAnimated.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item13) {
            Intent intent = new Intent(MoviesBio.this, MoviesBio.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item14) {
            Intent intent = new Intent(MoviesBio.this, MoviesComedy.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item15) {
            Intent intent = new Intent(MoviesBio.this, MoviesDoc.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item16) {
            Intent intent = new Intent(MoviesBio.this, MoviesDrama.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item17) {
            Intent intent = new Intent(MoviesBio.this, MoviesFan.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item18) {
            Intent intent = new Intent(MoviesBio.this, MoviesHorror.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item19) {
            Intent intent = new Intent(MoviesBio.this, MoviesRomance.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item21) {
            Intent intent = new Intent(MoviesBio.this, SeriesAction.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item22) {
            Intent intent = new Intent(MoviesBio.this, SeriesAnimated.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item23) {
            Intent intent = new Intent(MoviesBio.this, SeriesBio.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item24) {
            Intent intent = new Intent(MoviesBio.this, SeriesComedy.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item25) {
            Intent intent = new Intent(MoviesBio.this, SeriesDoc.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item26) {
            Intent intent = new Intent(MoviesBio.this, SeriesDrama.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.item27) {
            Intent intent = new Intent(MoviesBio.this, SeriesFan.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.item28) {
            Intent intent = new Intent(MoviesBio.this, SeriesHorror.class);
            startActivity(intent);
            return true;
        }
        else
        if (id == R.id.item29) {
            Intent intent = new Intent(MoviesBio.this, SeriesRomance.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item3){
            Intent intent = new Intent (MoviesBio.this, Actors.class);
            startActivity(intent);
            return true;
        }
         else if(id == R.id.item4){
            Intent intent = new Intent (MoviesBio.this, Directors.class);
            startActivity(intent);
            return true;
        }
         else if(id == R.id.item5){
            Intent intent = new Intent (MoviesBio.this, Profile.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item6){
            Intent intent = new Intent (MoviesBio.this, MainPage.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.item7){
            Intent intent = new Intent (MoviesBio.this, LoginPage.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}