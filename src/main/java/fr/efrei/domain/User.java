package fr.efrei.domain;
import java.util.*;

public class User {
    private int userId;
    private String username;
    private String password;
    private String mail;
    private List<Rental> rentals;

    public User() {
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
            this.userId=user.userId;
            this.username=user.username;
            this.password=user.password;
            this.mail=user.mail;
            this.rentals=user.rentals;
            return this;
        }
        public User build(){return new User(this);}

    }
    private User (Builder builder){
        this.userId=builder.userId;
        this.username= builder.username;
        this.password=builder.password;
        this.mail=builder.mail;
        this.rentals=builder.rentals;
    }

    public int getUserId() {return userId;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getMail() {return mail;}

    public List<Rental> getRentals() {return rentals;}


    public boolean login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your e-mail or username :");
        String Identicator = sc.next();
        System.out.println("Please enter your password :");
        String passwd= sc.next();
        if((Identicator.equals(username)||Identicator.equals(mail))&&(passwd.equals(passwd))){
            System.out.println("succesfully logged");
            return true;
        }
        else{
            System.out.println("Usename or passwd false try again");
            return false;
        }
    }
    public void logout(){
        System.out.println("The User"+this.username+"has been logged out");
    }
    public void register(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your UserId");
        int uid = scan.nextInt();
        userId = uid;
        System.out.println("Enter your username : ");
        String usern = scan.next();
        username = usern;
        System.out.println("Enter your password : ");
        String passwd = scan.next();
        password=passwd;
        System.out.println("Enter your e-mail : ");
        String email = scan.next();
        mail = email;
        System.out.println("User registered successfully.");
    }
    public void returnMovie(Rental rental) {
        // La logique de retour pourrait inclure des vérifications supplémentaires.
        System.out.println(rental.getRentedMovie()+"Movie returned succesfully.");
    }
    public Rental rentMovie(Movie movie){
        Scanner sca = new Scanner(System.in);
        System.out.println("Enter the name of the movie you want to rent :");
        String movi = sca.next();
        for(int i = 0;i<rentals.size();i++){
            if(movi.equals(movie.getTitle())){
                if(movie.getAvailableCopies()!=0){
                    Rental rental= new Rental(this);
                }
            }
        }
    }




}
