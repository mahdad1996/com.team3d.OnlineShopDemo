package com.team3d.Entities;

import java.util.Date;
import java.util.Objects;

public class Product {


    protected int pid;
    protected String name;
    protected String brand;
    protected int count;
    protected double price;
    protected String type;
    protected String color;
    protected double weight;


    public Product(String name,String brand,int count,double price,String type,String color,double weight){


        this.name = name;
        this.brand = brand;
        this.count = count;
        this.price = price;
        this.type = type;
        this.color = color;
        this.weight = weight;

    }

    public Product(){

    }
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return pid == product.pid &&
                count == product.count &&
                Double.compare(product.price, price) == 0 &&
                Double.compare(product.weight, weight) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(type, product.type) &&
                Objects.equals(color, product.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, name, brand, count, price, type, color, weight);
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

}
