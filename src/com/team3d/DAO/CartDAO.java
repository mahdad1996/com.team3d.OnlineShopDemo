package com.team3d.DAO;

import com.team3d.Entities.Cart;
import com.team3d.Entities.Product;

import java.util.ArrayList;

public interface CartDAO {
    Cart getCartContentById(int id);
    Cart getCartById(int id);
    boolean add(Cart cart);
    boolean update(Cart cart,int id);
    boolean addProductToCart(int Cart_id,int Product_id);
    boolean deleteProductFromCart(int cart_id,int Product_id);
    int getProductIdFromCart(int id);
    void confirm(Cart cart);

}
