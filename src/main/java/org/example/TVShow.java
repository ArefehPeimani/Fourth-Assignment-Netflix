package org.example;

import java.util.ArrayList;

class TVShow {

    private String title;
    private String genre;
    private int year;
    private int duration;
    private float rating;
    private ArrayList<String> cast = new ArrayList<String>();

    public TVShow(String title, String genre, int year, int duration, float rating, ArrayList<String> cast) {
        this.setTitle(title);
        this.setGenre(genre);
        this.setYear(year);
        this.setDuration(duration);
        this.setRating(rating);
        this.setCast(cast);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() { return genre; }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public float getRating() {
        return rating;
    }

    public ArrayList<String> getCast() { return cast; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) { this.genre = genre; }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setCast(ArrayList<String> cast) { this.cast = cast; }

    @Override
    public String toString() {
        return "TVShow{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", rating=" + rating +
                ", cast=" + cast +
                '}';
    }
}
