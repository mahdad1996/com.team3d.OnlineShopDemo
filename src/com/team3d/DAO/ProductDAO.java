package com.team3d.DAO;

import com.team3d.Entities.Product;

import java.util.ArrayList;

public interface ProductDAO {

    Product getProductById(int pid);
    ArrayList<Product> getAllProducts();
    boolean add(Product product);
    boolean delete(int pid);
    boolean update(Product product,int id);
    boolean MinusOne (Product product);
    boolean PlusOne(Product product);

}
