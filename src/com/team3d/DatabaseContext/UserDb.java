package com.team3d.DatabaseContext;

import com.team3d.DAO.UserDAO;
import com.team3d.Entities.Product;
import com.team3d.Entities.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDb implements UserDAO {
    @Override
    public User getUserById(int id) {
        Connection connection = DbContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE id=" + id);
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));


                return user;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        Connection connection = DbContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from user");
            ArrayList<User> users = new ArrayList<>();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));


                users.add(user);

            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(User user) {
        Connection connection = DbContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user(name,username,password) VALUES (?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());

            int i = preparedStatement.executeUpdate();
            if(i==1){
                return true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = DbContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE from user where id=" + id);
            if(i==1){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(User user, int id) {
        Connection connection = DbContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update user set name=?,username=?,password =? WHERE id=?");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4,id);

            int i = preparedStatement.executeUpdate();
            if(i==1){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
