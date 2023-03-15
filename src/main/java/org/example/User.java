package org.example;

import java.util.ArrayList;

class User {

    private String username;
    private String password;
    private ArrayList<TVShow> favorites = new ArrayList<TVShow>();
    private ArrayList<TVShow> watched = new ArrayList<TVShow>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<TVShow> getFavorites() { return favorites; }

    public ArrayList<TVShow> getWatched() { return watched; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public ArrayList<TVShow> searchByTitle(String title) {
        ArrayList<TVShow> found = new ArrayList<TVShow>();
        for (TVShow favorite : favorites) {
            if (favorite.getTitle().equals(title)) {
                found.add(favorite);
            }
        }
        return found;
    }

    public ArrayList<TVShow> searchByGenre(String genre) {
        ArrayList<TVShow> found = new ArrayList<TVShow>();
        for (TVShow favorite : favorites) {
            if (favorite.getGenre().equals(genre)) {
                found.add(favorite);
            }
        }
        return found;
    }

    public ArrayList<TVShow> searchByReleaseYear(int year) {
        ArrayList<TVShow> found = new ArrayList<TVShow>();
        for (TVShow favorite : favorites) {
            if (favorite.getYear() == year) {
                found.add(favorite);
            }
        }
        return found;
    }

    public void addToFavorites(TVShow show) {
        favorites.add(show);
        System.out.println("Show is added to favorite list.");
    }

    public void addToWatched(TVShow show) {
        watched.add(show);
        System.out.println("Show is added to watched list.");
    }

    public void getRecommendations(ArrayList<TVShow> shows) {
        ArrayList<TVShow> genreBased = new ArrayList<TVShow>();
        ArrayList<TVShow> yearBased = new ArrayList<TVShow>();
        ArrayList<TVShow> castBased = new ArrayList<TVShow>();
        boolean isRecomFound = false;

        for (TVShow favorite : favorites) {
            for (TVShow show : shows) {
                // make sure user hasn't watched the show before
                if (!watched.contains(show)) {
                    if (favorite.getGenre().equals(show.getGenre())) {
                        genreBased.add(show);
                        isRecomFound = true;
                    }
                    // check if show's release year is close to favorite within 5 years
                    if (show.getYear() >= favorite.getYear() - 5 && show.getYear() <= favorite.getYear() + 5) {
                        yearBased.add(show);
                        isRecomFound = true;
                    }
                    if (compareCasts(favorite.getCast(), show.getCast())) {
                        castBased.add(show);
                        isRecomFound = true;
                    }
                }
            }
        }

        if (isRecomFound) {
            System.out.println("--- All recommendations are sorted by their ratings ----");
            if (!genreBased.isEmpty()) {
                sortByRating(genreBased);
                System.out.println("Based on your favorite genres : ");
                System.out.println(genreBased);
            }
            if (!yearBased.isEmpty()) {
                System.out.println("Based on your favorite years of cinema : ");
                System.out.println(yearBased);
            }
            if (!castBased.isEmpty()) {
                System.out.println("Based on your favorite cast : ");
                System.out.println(castBased);
            }
        }
        else {
            System.out.println("Unfortunately, we couldn't find any recommendations based on your favorite shows.");
        }
    }

    public boolean compareCasts(ArrayList<String> favCasts, ArrayList<String> showCasts) {
        for (String favCast : favCasts) {
            for (String showCast : showCasts) {
                if (favCast.equals(showCast)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void sortByRating(ArrayList<TVShow> shows) {
        int n = shows.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (shows.get(j).getRating() > shows.get(j + 1).getRating()) {
                    TVShow temp = shows.get(j);
                    shows.set(j, shows.get(j + 1));
                    shows.set(j + 1, temp);
                }
            }
        }
    }
}