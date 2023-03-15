package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {
        System.out.println("------------------ WELCOME TO NETFLIX ------------------");
        NetflixService service = new NetflixService();
        Scanner in = new Scanner(System.in);
        service = authenticate(service, in);
        int choice;
        if (service == null) {
            choice = 0;
        }
        else {
            choice = -1;
        }
        while (choice != 0) {
            userMenu();
            choice = Integer.parseInt(in.nextLine());
            if (choice == 1) {
                System.out.println("Enter the Title : ");
                chooseShow(service, service.searchByTitle(in.nextLine()), in);
            }
            else if (choice == 2) {
                System.out.println("Enter the Genre : ");
                chooseShow(service, service.searchByGenre(in.nextLine()), in);
            }
            else if (choice == 3) {
                System.out.println("Enter the Year : ");
                ArrayList<TVShow> found = service.searchByReleaseYear(Integer.parseInt(in.nextLine()));
                chooseShow(service, service.searchByReleaseYear(Integer.parseInt(in.nextLine())), in);
            }
            else if (choice == 4) {
                System.out.println("Enter the Title : ");
                ArrayList<TVShow> found = service.user.searchByTitle(in.nextLine());
                if (found.isEmpty()) {
                    System.out.println("There are no matching results.");
                }
                else {
                    System.out.println(found);
                }
            }
            else if (choice == 5) {
                System.out.println("Enter the Genre : ");
                ArrayList<TVShow> found = service.user.searchByGenre(in.nextLine());
                if (found.isEmpty()) {
                    System.out.println("There are no matching results.");
                }
                else {
                    System.out.println(found);
                }
            }
            else if (choice == 6) {
                System.out.println("Enter the Year : ");
                ArrayList<TVShow> found = service.user.searchByReleaseYear(Integer.parseInt(in.nextLine()));
                if (found.isEmpty()) {
                    System.out.println("There are no matching results.");
                }
                else {
                    System.out.println(found);
                }
            }
            else if (choice == 7) {
                System.out.println(service.user.getFavorites());
            }
            else if (choice == 8) {
                System.out.println(service.user.getWatched());
            }
            else if (choice == 9) {
                System.out.println("Enter the Title : ");
                ArrayList<TVShow> found = service.user.searchByTitle(in.nextLine());
                System.out.println("Choose the Show : ");
                for (int i = 0; i < found.size(); i++) {
                    System.out.println(i + ". " + found.get(i));
                }
                service.user.addToFavorites(found.get(Integer.parseInt(in.nextLine())-1));
            }
            else if (choice == 10) {
                System.out.println("TV SHOWS : ");
                service.user.getRecommendations(service.getTvShows());
                System.out.println("MOVIES : ");
                service.user.getRecommendations(service.getMovies());
            }
            else if (choice == 11) {
                System.out.println("Enter the information of the TV Show");
                System.out.println("Title : ");
                String title = in.nextLine();
                System.out.println("Genre : ");
                String genre = in.nextLine();
                System.out.println("Year : ");
                int year = Integer.parseInt(in.nextLine());
                System.out.println("Duration : ");
                int duration = Integer.parseInt(in.nextLine());
                System.out.println("Rating (0 to 10) : ");
                float rating = Float.parseFloat(in.nextLine());
                System.out.println("Cast : (Write each cast member's name and hit enter. When you're done, write \"done\")");
                ArrayList<String> cast = new ArrayList<String>();
                String member = in.nextLine();
                while (!member.equals("done")) {
                    cast.add(member);
                    member = in.nextLine();
                }
                service.addTVShow(new TVShow(title, genre, year, duration, rating, cast));
            }
            else if (choice == 12) {
                System.out.println("Enter the information of the Movie");
                System.out.println("Title : ");
                String title = in.nextLine();
                System.out.println("Genre : ");
                String genre = in.nextLine();
                System.out.println("Year : ");
                int year = Integer.parseInt(in.nextLine());
                System.out.println("Length : ");
                int duration = Integer.parseInt(in.nextLine());
                System.out.println("Rating (0 to 10) : ");
                float rating = Float.parseFloat(in.nextLine());
                System.out.println("Cast : (Write each cast member's name and hit enter. When you're done, write \"done\")");
                ArrayList<String> cast = new ArrayList<String>();
                String member = in.nextLine();
                while (!member.equals("done")) {
                    cast.add(member);
                    member = in.nextLine();
                }
                service.addMovie(new Movie(title, genre, year, duration, rating, cast));
            }
            else if (choice == 20) {
                service.logout();
                service = authenticate(service, in);
                if (service == null) {
                    choice = 0;
                }
            }
        }
    }

    public static void chooseShow(NetflixService service, ArrayList<TVShow> found, Scanner in) {
        if (found.isEmpty()) {
            System.out.println("There are no matching results.");
        }
        else {
            System.out.println("Choose the Show : ");
            for (int i = 0; i < found.size(); i++) {
                System.out.println(i+1 + ". " + found.get(i));
            }
            TVShow show = found.get(Integer.parseInt(in.nextLine())-1);
            System.out.println("Do you want to watch the show? (y/n)");
            if (in.nextLine().equals("y")) {
                service.user.addToWatched(show);
            }
            System.out.println("Do you want to add the show to your favorite list? (y/n)");
            if (in.nextLine().equals("y")) {
                service.user.addToFavorites(show);
            }
        }
    }

    public static NetflixService authenticate(NetflixService service, Scanner in) {
        int command = -1;
        while (service.user == null) {
            System.out.println("~~~~~~~~~~~~~~~~~ Choose From The Menu ~~~~~~~~~~~~~~~~~");
            System.out.println("1. Create an Account");
            System.out.println("2. Log in");
            System.out.println("0. Exit");
            command = Integer.parseInt(in.nextLine());
            if (command == 1) {
                System.out.println("Enter Username : ");
                String username = in.nextLine();
                System.out.println("Enter Password : ");
                String password = in.nextLine();
                if (password.length() < 8) {
                    System.out.println("Your password must be more than 8 characters.");
                }
                else {
                    if (service.createAccount(username, password)) {
                        System.out.println("Do you want to log in to the created account? (y/n)");
                        String ans = in.nextLine();
                        if (ans.equals("y")) {
                            service.login(username, password);
                            System.out.println("You are logged in.");
                        }
                    }
                }
            }
            else if (command == 2) {
                System.out.println("Enter Username : ");
                String username = in.nextLine();
                System.out.println("Enter Password : ");
                String password = in.nextLine();
                if (service.login(username, password)) {
                    System.out.println("You logged in to your account.");
                }
                else {
                    System.out.println("Username or Password is incorrect.");
                }
            }
            else if (command == 0) {
                return null;
            }
        }
        return service;
    }

    public static void userMenu() {
        System.out.println("~~~~~~~~~~~~~~~~~ Choose From The Menu ~~~~~~~~~~~~~~~~~");
        System.out.println("============== Search All Shows and Watch ==============");
        System.out.println("1. by Title");
        System.out.println("2. by Genre");
        System.out.println("3. by Year");
        System.out.println("======= Search Your Favorite Movies and TV Shows =======");
        System.out.println("4. by Title");
        System.out.println("5. by Genre");
        System.out.println("6. by Year");
        System.out.println("============= Favorite Movies and TV Shows =============");
        System.out.println("7. Show Favorites List");
        System.out.println("8. Show Watched List");
        System.out.println("9. Add to Favorites");
        System.out.println("10. Get Recommendations Based on Favorites");
        System.out.println("================= Add Shows to Service =================");
        System.out.println("11. Add a TV Show");
        System.out.println("12. Add a Movie");
        System.out.println("========================================================");
        System.out.println("20. Log out");
        System.out.println("0. Exit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
