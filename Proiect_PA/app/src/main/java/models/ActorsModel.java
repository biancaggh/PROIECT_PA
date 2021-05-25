package models;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ActorsModel {
    public static String name;
    public static String full_name;
    public static String movies;
    public static String tvseries;
    public static String image;
    public static String nationality;
    public static String date_birth;

    public ActorsModel(){

    }

    public ActorsModel(String name, String fullname, String movies, String tvseries,String image, String nationality, String date_birth) {
        this.name = name;
        this.full_name = fullname;
        this.movies = movies;
        this.tvseries = tvseries;
        this.image = image;
        this.nationality = nationality;
        this.date_birth = date_birth;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getFullname() {
        return full_name;
    }

    public void setFullname(String full_name) {
        this.full_name = full_name;
    }

    public static String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    public static String getTvseries() {
        return tvseries;
    }

    public void setTvseries(String tvseries) {
        this.tvseries = tvseries;
    }

    public static String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static String getNational() {
        return nationality;
    }

    public void setNational(String nationality) {
        this.nationality = nationality;
    }


    public static String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    @Override
    public String toString() {
        return name + '\'' + full_name + '\''+ movies + '\''  + tvseries + '\'' +image + '\'' +
                 nationality + '\''+ '\'' + date_birth;
    }
}
