package com.team3d.DatabaseContext;

import com.sun.jdi.connect.Connector;
import com.team3d.DAO.ProductDAO;
import com.team3d.Entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDb implements ProductDAO {
    @Override
    public Product getProductById(int pid) {
        Connection connection = DbContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM product WHERE id=" + pid);
            if(rs.next()){
                Product product = new Product();
                product.setPid(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setCount(rs.getInt("count"));
                product.setPrice(rs.getDouble("price"));
                product.setType(rs.getString("type"));
                product.setColor(rs.getString("color"));
                product.setWeight(rs.getDouble("weight"));

                return product;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public ArrayList<Product> getAllProducts() {
        Connection connection = DbContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from product");
            ArrayList<Product> products = new ArrayList<>();
            while (rs.next()){
                Product product = new Product();
                product.setPid(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setCount(rs.getInt("count"));
                product.setPrice(rs.getDouble("price"));
                product.setType(rs.getString("type"));
                product.setColor(rs.getString("color"));
                product.setWeight(rs.getDouble("weight"));

                products.add(product);

            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean add(Product product) {
        Connection connection = DbContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product(name,brand,count,price,type,color,weight) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setInt(3, product.getCount());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getType());
            preparedStatement.setString(6, product.getColor());
            preparedStatement.setDouble(7, product.getWeight());

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
    public boolean delete(int pid) {
        Connection connection = DbContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE from product where id=" + pid);
            if(i==1){

                return true;
            }
            } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean update(Product product,int id) {
        Connection connection = DbContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update product set name=?,brand=?,count =?,price=?,type =?,color =?,weight=? WHERE id=?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getBrand());
            preparedStatement.setInt(3, product.getCount());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setString(5, product.getType());
            preparedStatement.setString(6, product.getColor());
            preparedStatement.setDouble(7, product.getWeight());
            preparedStatement.setInt(8,id);

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
    public boolean MinusOne(Product product) {
        Product myProduct = getProductById(product.getPid());
        int count = myProduct.getCount();
        if(count!=0){
            myProduct.setCount(count-1);
            update(myProduct,myProduct.getPid());
            return true;
        }
        else System.out.println("mojudi tamam!");
        return false;
    }

    @Override
    public boolean PlusOne(Product product) {
        Product myProduct = getProductById(product.getPid());
        int count = myProduct.getCount();
            myProduct.setCount(count+1);
            update(myProduct,myProduct.getPid());
            return true;


    }
}
