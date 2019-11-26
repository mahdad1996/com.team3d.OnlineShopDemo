package com.team3d.Entities;

import java.util.ArrayList;
import java.util.Objects;

public class Cart {

    protected int id;
    protected double total;
    protected ArrayList<Product> products = new ArrayList<>();
    protected boolean confirm;

    public Cart(int id,double total, ArrayList<Product> products, boolean confirm) {
        this.id = id;
        this.total = total;
        this.products = products;
        this.confirm = confirm;
    }

    public Cart() {
        this.id = 1;
        this.total = 0.0;
        this.confirm=false;
        this.products = null;
    }

    public double calculateTotal(){
        for (Product p:products) {
           total+= p.getPrice();
        }

        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id &&
                Double.compare(cart.total, total) == 0 &&
                Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total, products);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", total=" + total +
                ", products=" + products +
                ", confirm=" + confirm +
                '}';
    }
}
