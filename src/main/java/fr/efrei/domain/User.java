package fr.efrei.domain;
import java.util.*;

public class User {
    private int userId;
    private String username;
    private String password;
    private String mail;
    private List<Rental> rentals;

    private User() {
    }
    public int getUserId() {return userId;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getMail() {return mail;}
    public List<Rental> getRentals() {return rentals;}


    public boolean login(String loginIdentifier, String loginPassword) {
        System.out.println("Trying to log in...");
        if ((loginIdentifier.equals(username) || loginIdentifier.equals(mail)) && loginPassword.equals(password)) {
            System.out.println("Successfully logged in as " + username);
            return true;
        } else {
            System.out.println("Username or password is incorrect. Login failed.");
            return false;
        }
    }
    public void logout(){
        System.out.println("The User "+this.username+" has been logged out");
    }

    public void returnMovie(Rental rental) {
        // La logique de retour pourrait inclure des vérifications supplémentaires.
        System.out.println(rental.getRentedMovie()+"Movie returned succesfully.");
    }
    public Rental rentMovie(Movie movie) {
        if (movie.getAvailableCopies() > 0) {
            Rental rental = new Rental.Builder()
                    .setRentedMovie(movie)
                    .setRentalDate(new Date()) // Set the rental date to the current date
                    .build();

            // Mettez à jour le nombre d'exemplaires disponibles
            movie.decreaseAvailableCopies();

            rentals.add(rental);
            return rental; // Return the created rental object
        } else {
            System.out.println("No available copies of the movie. Please try again later.");
            return null;
        }
    }
    public void removeRental(Rental rental) {
        rentals.remove(rental);
    }
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
    private User (Builder builder){
        this.userId=builder.userId;
        this.username= builder.username;
        this.password=builder.password;
        this.mail=builder.mail;
        this.rentals=builder.rentals != null ? new ArrayList<>(builder.rentals) : new ArrayList<>();
    }
    public static class Builder{
        private int userId;
        private String username;
        private String password;
        private String mail;
        private List<Rental> rentals;

        public Builder setUserId(int userId){
            this.userId=userId;
            return this;
        }
        public Builder setUserName(String username){
            this.username=username;
            return this;
        }
        public Builder setPassword(String password){
            this.password=password;
            return this;
        }
        public Builder setMail(String mail){
            this.mail=mail;
            return this;
        }
        public Builder setRentals(List<Rental> rentals){
            this.rentals=rentals;
            return this;
        }
        public Builder copy(User user){
            this.userId= user.userId;
            this.username=user.username;
            this.password=user.password;
            this.mail=user.mail;
            this.rentals=user.rentals;
            return this;
        }
        public User build(){return new User(this);}

    }

}
