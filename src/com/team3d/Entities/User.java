package com.team3d.Entities;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class User implements Comparable<User> {

    protected int id;
    protected String name;
    protected String username;
    protected String password;
    protected Cart cart;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int compareTo(User o) {
        int id = ((User) o).getId();
        return this.id - id;

    }

    public static Comparator<User> usernameComprator = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            String uName1 = o1.getUsername();
            String uName2 = o2.getUsername();
            return uName1.compareTo(uName2);
        }
    };

    


}
