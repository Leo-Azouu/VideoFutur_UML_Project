package fr.efrei.repository;
import java.util.*;
import fr.efrei.domain.User;

public class UserRepository implements IUserRepository {
    private static UserRepository repository = null;
    private List<User> user;

    private UserRepository() {
        this.user = new ArrayList<User>();
    }

    public static UserRepository getRepository() {
        if (repository == null) {
            repository = new UserRepository();
        }
        return repository;
    }
    @Override
    public User create(User USER){
        boolean success = user.add(USER);
        if(success){
            return USER;
        }
        else{
            return null;
        }
    }
    @Override
    public User read(Long UserID){
        for(User USER : user){
            if(USER.getUserId()== UserID){
                return USER;
            }
        }
        return null;
    }
    public User update(User USER){
        for(User u : user){
            if(u.getUserId() == USER.getUserId()){
                user.remove(u);
                user.add(USER);
                return USER;
            }
        }
        return null;
    }
    @Override
    public boolean delete(Long UserId){
        User UserToDelete = read(UserId);
        boolean sucess = user.remove(UserToDelete);
        return sucess;
    }
    @Override
    public List<User> getAll(){return user;}

}
