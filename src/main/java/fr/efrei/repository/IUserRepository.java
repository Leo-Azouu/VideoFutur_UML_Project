package fr.efrei.repository;
import java.util.List;

import fr.efrei.domain.User;

public interface IUserRepository extends IRepository<User,Long>{
    public List<User> getAll();
}
