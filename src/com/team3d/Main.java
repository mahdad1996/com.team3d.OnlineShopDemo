package com.team3d;

import com.team3d.DAO.CartDAO;
import com.team3d.DAO.ProductDAO;
import com.team3d.DAO.UserDAO;
import com.team3d.DatabaseContext.CartDb;
import com.team3d.DatabaseContext.ProductDb;
import com.team3d.DatabaseContext.UserDb;
import com.team3d.Entities.Cart;
import com.team3d.Entities.Product;
import com.team3d.Entities.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Name : ");
        String name = input.next();
        System.out.println("Enter username : ");
        String username = input.next();
        System.out.println("Enter password : ");
        String password = input.next();
        Cart cart = new Cart();
        CartDAO cartDAO = new CartDb();
        User user = new User(name,username,password);
        cart = cartDAO.getCartById(1);
        user.setCart(cart);
        UserDAO userDAO = new UserDb();
        userDAO.add(user);
        boolean selection=true;

        while (selection) {
            System.out.println("What you want? 1-Add Product | 2-Delete Product | 3-Confirm");
            Scanner inputSelection = new Scanner(System.in);
            int select = inputSelection.nextInt();
            switch (select) {
                case 1: {
                    Product product = new Product();
                    ProductDAO productDAO = new ProductDb();
                    System.out.println(productDAO.getAllProducts().toString());

                    System.out.println("choose product: (enter id)");
                    int pid = input.nextInt();
                    product = productDAO.getProductById(pid);
                    if (productDAO.MinusOne(product)) {
                        if (cartDAO.addProductToCart(cart.getId(), product.getPid())) {
                            System.out.println("product Added to cart!");

                        }

                    }
                    System.out.println(cartDAO.getCartContentById(cart.getId()));
                    break;
                }
                case 2: {
                    Product product = new Product();
                    ProductDAO productDAO = new ProductDb();
                    Cart myCart = new Cart();
                    System.out.println(cartDAO.getCartContentById(cart.getId()));
                    System.out.println("choose product to delete: (enter id)");
                    int pid = input.nextInt();

                    int idToDelete = cartDAO.getProductIdFromCart(pid);
                    product = productDAO.getProductById(idToDelete);

                    if (cartDAO.deleteProductFromCart(cart.getId(), product.getPid())) {
                        System.out.println("product deleted from cart!");

                    }
                    System.out.println(cartDAO.getCartContentById(cart.getId()));
                    break;
                }

                case 3: {
                    cart = cartDAO.getCartById(cart.getId());
                    cartDAO.confirm(cart);
                    selection = false;
                }
                default:
                    select = 0;
            }
        }

    }
}
