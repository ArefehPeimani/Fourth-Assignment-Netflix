package org.example;

import java.util.ArrayList;

class Movie extends TVShow {

    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Movie(String title, String genre, int year, int duration, float rating, ArrayList<String> cast) {
        super(title, genre, year, duration, rating, cast);
        this.setLength(duration);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + this.getTitle() + '\'' +
                ", genre='" + this.getGenre() + '\'' +
                ", year=" + this.getYear() +
                ", length=" + length +
                ", rating=" + this.getRating() +
                ", cast=" + this.getCast() +
                '}';
    }
}
