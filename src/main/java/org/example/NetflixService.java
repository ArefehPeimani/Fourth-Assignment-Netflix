package org.example;

import java.util.ArrayList;

class NetflixService {

    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<TVShow> tvShows = new ArrayList<TVShow>();
    private ArrayList<TVShow> movies = new ArrayList<TVShow>();
    public User user = null;

    public ArrayList<User> getUsers() {
        return users;
    }
    public ArrayList<TVShow> getTvShows() {
        return tvShows;
    }
    public ArrayList<TVShow> getMovies() {
        return movies;
    }

    public void addTVShow(TVShow tvShow){
        tvShows.add(tvShow);
        System.out.println("TV Show was added successfully.");
    }

    public void addMovie(Movie movie){
        movies.add(movie);
        System.out.println("Movie was added successfully.");
    }

    public boolean createAccount(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return false;
            }
        }
        users.add(new User(username, password));
        System.out.println("Account is created.");
        return true;
    }

    public boolean login(String username, String password) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                user = users.get(i);
                return true;
            }
        }
        return false;
    }

    public void logout() {
        user = null;
        System.out.println("You logged out of your account.");
    }

    public ArrayList<TVShow> searchByTitle(String title) {
        ArrayList<TVShow> found = new ArrayList<TVShow>();
        for (int i = 0; i < tvShows.size(); i++) {
            if (tvShows.get(i).getTitle().equals(title)) {
                found.add(tvShows.get(i));
            }
        }
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTitle().equals(title)) {
                found.add(movies.get(i));
            }
        }
        return found;
    }

    public ArrayList<TVShow> searchByGenre(String genre) {
        ArrayList<TVShow> found = new ArrayList<TVShow>();
        for (int i = 0; i < tvShows.size(); i++) {
            if (tvShows.get(i).getGenre().equals(genre)) {
                found.add(tvShows.get(i));
            }
        }
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getGenre().equals(genre)) {
                found.add(movies.get(i));
            }
        }
        return found;
    }

    public ArrayList<TVShow> searchByReleaseYear(int year) {
        ArrayList<TVShow> found = new ArrayList<TVShow>();
        for (int i = 0; i < tvShows.size(); i++) {
            if (tvShows.get(i).getYear() == year) {
                found.add(tvShows.get(i));
            }
        }
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getYear() == year) {
                found.add(movies.get(i));
            }
        }
        return found;
    }
}