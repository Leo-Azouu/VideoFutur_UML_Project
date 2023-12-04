package fr.efrei;

import fr.efrei.domain.Category;
import fr.efrei.domain.Movie;
import fr.efrei.domain.Rental;
import fr.efrei.domain.User;
import fr.efrei.factory.CategoryFactory;
import fr.efrei.factory.MovieFactory;
import fr.efrei.factory.UserFactory;
import fr.efrei.repository.CategoryRepository;
import fr.efrei.repository.MovieRepository;
import fr.efrei.repository.UserRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final int MANAGER_ACCESS_CODE = 12012003;
    public static void main(String[] args) {
        Category action = CategoryFactory.createCategory(1, "Action", null);
        Category adventure = CategoryFactory.createCategory(2, "Adventure", null);

        CategoryRepository categoryRepository = CategoryRepository.getRepository();

        categoryRepository.create(action);
        categoryRepository.create(adventure);

        Movie m1 = MovieFactory.createMovie(1, "The Dark Knight", "2008-07-16", 5, 1,15.0);
        Movie m2 = MovieFactory.createMovie(2, "The Dark Knight Rises", "2012-07-20", 5, 1,20.0);
        Movie m3 = MovieFactory.createMovie(3, "The Lord of the Rings: The Fellowship of the Ring", "2001-12-19", 5, 2,10.0);
        Movie m4 = MovieFactory.createMovie(4, "The Lord of the Rings: The Two Towers", "2002-12-18", 5, 2,10.0);

        MovieRepository movieRepository = MovieRepository.getRepository();

        movieRepository.create(m1);
        movieRepository.create(m2);
        movieRepository.create(m3);
        movieRepository.create(m4);

        action.addMovie(m1);
        action.addMovie(m2);

        adventure.addMovie(m3);
        adventure.addMovie(m4);

        // System.out.println(allCategories);
        UserRepository userRepository = UserRepository.getRepository();


        Scanner scanner = new Scanner(System.in);

        User currentUser = null;

        User user1 = UserFactory.createUser(1, "user1", "password1", "user@user1.com");

        userRepository.create(user1);

        while (true) {
            System.out.println("1. Log In");
            System.out.println("2. Manager Menu");
            System.out.println("3. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (currentUser == null) {
                        System.out.print("Enter your username or email: ");
                        String logIdentifier = scanner.next();
                        System.out.print("Enter your password: ");
                        String logPassword = scanner.next();

                        for (User existingUser : userRepository.getAll()) {
                            if (logIdentifier.equals(existingUser.getUsername()) || logIdentifier.equals(existingUser.getMail())) {
                                if (existingUser.login(logIdentifier, logPassword)) {
                                    currentUser = existingUser;

                                    while (currentUser != null) {
                                        System.out.println("1. Rent a Movie");
                                        System.out.println("2. View Rented Movies");
                                        System.out.println("3. View Available Movies");
                                        System.out.println("4. Return a Movie");
                                        System.out.println("5. Log Out");

                                        System.out.print("Enter your choice: ");
                                        int loggedInChoice = scanner.nextInt();

                                        switch (loggedInChoice) {
                                            case 1:
                                                // Code pour louer un film
                                                System.out.println("Available Movies:");
                                                System.out.println(movieRepository.getAll());
                                                System.out.print("Enter the movie ID to rent: ");
                                                int movieIdToRent = scanner.nextInt();
                                                Movie movieToRent = movieRepository.getById(movieIdToRent);
                                                if (movieToRent != null) {
                                                    Rental rental = currentUser.rentMovie(movieToRent); // Appel de la méthode rentMovie

                                                    // Simulation d'une date de location antérieure
                                                    //Calendar rentalDateCalendar = Calendar.getInstance();
                                                    //rentalDateCalendar.add(Calendar.DAY_OF_MONTH, -25); // Par exemple, simuler une location il y a 10 jours
                                                    //rental.setRentalDate(rentalDateCalendar.getTime());

                                                    if (rental != null) {
                                                        System.out.println("Movie rented successfully.");
                                                    } else {
                                                        System.out.println("No available copies of the movie. Please try again later.");
                                                    }
                                                    break; // Ajouter cette ligne pour sortir de la boucle après avoir traité la location avec succès
                                                } else {
                                                    System.out.println("Invalid movie ID. Please try again.");
                                                }
                                                break;
                                            case 2:
                                                // Code pour afficher les informations sur les films loués
                                                System.out.println("Rented Movies:");
                                                for (Rental rental : currentUser.getRentals()) {
                                                    Movie rentedMovie = rental.getRentedMovie();
                                                    Date returnDate;
                                                    if (rental.getReturnDate() != null) {
                                                        returnDate = rental.getReturnDate();
                                                    } else {
                                                        // Si le film n'a pas encore été retourné, calculez la date de retour prévue (7 jours après la date de location)
                                                        Calendar returnDateCalendar = Calendar.getInstance();
                                                        returnDateCalendar.setTime(rental.getRentalDate());
                                                        returnDateCalendar.add(Calendar.DAY_OF_MONTH, 7);
                                                        returnDate = returnDateCalendar.getTime();
                                                    }

                                                    System.out.println("Movie ID: " + rentedMovie.getMovieId() +
                                                            ", Movie Title: " + rentedMovie.getTitle() +
                                                            ", Rental Date: " + rental.getRentalDate() +
                                                            ", Return Date: " + returnDate);
                                                }
                                                break;
                                            case 3:
                                                // Code pour afficher la liste des films disponibles
                                                System.out.println("Available Movies:");
                                                System.out.println(movieRepository.getAll());
                                                break;
                                            case 4:
                                                System.out.println("Rented Movies:");
                                                for (Rental rental : currentUser.getRentals()) {
                                                    Movie rentedMovie = rental.getRentedMovie();
                                                    System.out.println("Movie ID: " + rentedMovie.getMovieId() +
                                                            ", Movie Title: " + rentedMovie.getTitle() +
                                                            ", Rental Date: " + rental.getRentalDate());
                                                }
                                                // Vérifiez d'abord si l'utilisateur a des films à retourner
                                                if (currentUser.getRentals().isEmpty()) {
                                                    System.out.println("You have no movies to return.");
                                                    break;
                                                }

                                                System.out.print("Enter the movie ID to return: ");
                                                int movieIdToReturn = scanner.nextInt();
                                                Movie movieToReturn = movieRepository.getById(movieIdToReturn);

                                                if (movieToReturn != null) {
                                                    movieToReturn.returnMovie(currentUser);
                                                    break;
                                                } else {
                                                    System.out.println("Invalid movie ID. Please try again.");
                                                }
                                                break;
                                            case 5:
                                                currentUser.logout();
                                                currentUser = null; // Déconnecter l'utilisateur
                                                break;
                                            default:
                                                System.out.println("Invalid choice. Please try again.");
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("You are already logged in.");
                    }
                    break;
                case 2:
                    // Manager Menu
                    System.out.print("Enter Manager Access Code: ");
                    int accessCode = scanner.nextInt();

                    if (accessCode == MANAGER_ACCESS_CODE) {
                        boolean managerLoggedIn = true;

                        while (managerLoggedIn) {
                            System.out.println("Manager Menu:");
                            System.out.println("1. View All Categories");
                            System.out.println("2. View All Users (including passwords)");
                            System.out.println("3. View All Rentals");
                            System.out.println("4. View Available Movies");
                            System.out.println("5. Log Out (Manager)");

                            System.out.print("Enter your choice: ");
                            int managerChoice = scanner.nextInt();

                            switch (managerChoice) {
                                case 1:
                                    // View All Categories
                                    System.out.println("All Categories:");
                                    System.out.println(categoryRepository.getAll());
                                    break;
                                case 2:
                                    // View All Users (excluding passwords)
                                    System.out.println("All Users (including passwords):");
                                    for (User user : userRepository.getAll()) {
                                        System.out.println("User ID: " + user.getUserId() +
                                                ", Username: " + user.getUsername() +
                                                ", Email: " + user.getMail() +
                                                ", Password: " + user.getPassword());
                                    }
                                    break;
                                case 3:
                                    // View All Rentals
                                    System.out.println("All Rentals:");
                                    for (User user : userRepository.getAll()) {
                                        for (Rental rental : user.getRentals()) {
                                            Movie rentedMovie = rental.getRentedMovie();
                                            Date returnDate;
                                            if (rental.getReturnDate() != null) {
                                                returnDate = rental.getReturnDate();
                                            } else {
                                                // Si le film n'a pas encore été retourné, calculez la date de retour prévue (7 jours après la date de location)
                                                Calendar returnDateCalendar = Calendar.getInstance();
                                                returnDateCalendar.setTime(rental.getRentalDate());
                                                returnDateCalendar.add(Calendar.DAY_OF_MONTH, 7);
                                                returnDate = returnDateCalendar.getTime();
                                            }

                                            System.out.println("User ID: " + user.getUserId() +
                                                    ", Movie ID: " + rentedMovie.getMovieId() +
                                                    ", Movie Title: " + rentedMovie.getTitle() +
                                                    ", Rental Date: " + rental.getRentalDate() +
                                                    ", Return Date: " + returnDate);
                                        }
                                    }
                                    break;
                                case 4:
                                    // View Available Movies
                                    System.out.println("Available Movies:");
                                    System.out.println(movieRepository.getAll());
                                    break;
                                case 5:
                                    // Log Out (Manager)
                                    System.out.println("Logging out as manager.");
                                    managerLoggedIn = false; // Mettre fin à la boucle du menu manager
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    } else {
                        System.out.println("Incorrect Manager Access Code. Access denied.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}