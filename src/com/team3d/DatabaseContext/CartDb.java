package com.team3d.DatabaseContext;

import com.team3d.DAO.CartDAO;
import com.team3d.Entities.Cart;
import com.team3d.Entities.Product;

import java.sql.*;
import java.util.ArrayList;

public class CartDb implements CartDAO {
    @Override
    public Cart getCartContentById(int id) {
        ArrayList<Product> products = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId(id);
        Connection connection = DbContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT product_id FROM cartcontent WHERE cart_id=" + id);
            while (rs.next()){
                int product_id = rs.getInt("product_id");
                ProductDb productDb = new ProductDb();
                Product product = productDb.getProductById(product_id);
                products.add(product);


            }
            cart.setProducts(products);
            cart.calculateTotal();
            return cart;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Cart getCartById(int id) {
        Connection connection = DbContext.getConnection();
        Cart cart= new Cart();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cart WHERE id=" + id);
            if(rs.next()){

               cart.setId(rs.getInt(1));
               cart.setTotal(rs.getDouble(2));
               cart.setConfirm(rs.getBoolean(3));


            }
            return cart;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public boolean add(Cart cart) {
        Connection connection = DbContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cart(total,confirm) VALUES (?,?)");
            preparedStatement.setDouble(1, cart.getTotal());
            preparedStatement.setBoolean(2, cart.isConfirm());
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
    public boolean update(Cart cart,int id) {
        Connection connection = DbContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update cart set total=?,confirm=? WHERE id=?");
            preparedStatement.setDouble(1, cart.getTotal());
            preparedStatement.setBoolean(2, cart.isConfirm());
            preparedStatement.setInt(3,id);
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
    public boolean addProductToCart(int Cart_id, int Product_id) {
        Connection connection = DbContext.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cartcontent(cart_id,product_id) VALUES (?,?)");
            preparedStatement.setInt(1, Cart_id);
            preparedStatement.setInt(2, Product_id);
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
    public boolean deleteProductFromCart(int cart_id, int Product_id) {
        Connection connection = DbContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE from cartcontent where cart_id="+ cart_id + " AND product_id="+Product_id);
            if(i==1){
                ProductDb p =new ProductDb();

                p.PlusOne(p.getProductById(Product_id));
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public int getProductIdFromCart(int id) {
        Connection connection = DbContext.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM cartcontent WHERE product_id=" + id);
            if(rs.next()){
                int product_id = rs.getInt("product_id");
                return product_id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public void confirm(Cart cart) {

        cart.setConfirm(true);
        CartDAO cartDAO = new CartDb();
        cart.setProducts(cartDAO.getCartContentById(cart.getId()).getProducts());
        cart.calculateTotal();
        cartDAO.update(cart,cart.getId());

        System.out.println("confirmed! total payment : " + cart.getTotal());
        System.out.println(cart.toString());

    }


}
