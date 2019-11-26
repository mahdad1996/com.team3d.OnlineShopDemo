package com.team3d.DAO;

import com.team3d.Entities.Product;
import com.team3d.Entities.User;

import java.util.ArrayList;

public interface UserDAO {
    User getUserById(int id);
    ArrayList<User> getAllUsers();
    boolean add(User user);
    boolean delete(int id);
    boolean update(User user,int id);
}
