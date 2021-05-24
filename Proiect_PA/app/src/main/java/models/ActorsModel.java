package models;

import java.io.Serializable;
import java.sql.Date;

public class ActorsModel {
    public static String name;
    public static String fullname;
    public static String movies;
    public static String tvseries;
    public static String image;
    public static String nationality;
    //String id;
    public static String age;
    public static String date_birth;

    public ActorsModel(){

    }

    public ActorsModel(String name, String fullname, String movies, String tvseries, String image, String nationality, String age, String date_birth) {
        this.name = name;
        this.fullname = fullname;
        this.movies = movies;
        this.tvseries = tvseries;
        this.image = image;
        this.nationality = nationality;
        this.age = age;
        this.date_birth = date_birth;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public static String getImageURL() {
        return image;
    }

    public void setImageURL(String image) {
        this.image = image;
    }

    public static String getNational() {
        return nationality;
    }

    public void setNational(String nationality) {
        this.nationality = nationality;
    }

    public static String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    @Override
    public String toString() {
        return name + '\'' + fullname + '\''+ movies + '\''  + tvseries + '\''  + image + '\'' +
                 nationality + '\'' + age + '\'' + date_birth;
    }
}
