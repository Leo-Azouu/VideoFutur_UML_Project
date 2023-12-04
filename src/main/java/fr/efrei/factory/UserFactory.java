package fr.efrei.factory;
import fr.efrei.Util.Helper;
import fr.efrei.domain.User;

public class UserFactory {
    public static User createUser(int userId,String username, String password, String mail){
        if(Helper.isNumericEmpty(userId)||Helper.isStringValid(username)||Helper.isStringValid(password)||Helper.isStringValid(mail)){
            return null;
        }
        return new User.Builder()
                .setUserId(userId)
                .setUserName(username)
                .setPassword(password)
                .setMail(mail)
                .build();
    }
}
