package com.foodstoreapi.models;

import javax.persistence.*;

@Entity
@Table(name="FoodProduct")
public class Product {
    //this is "primary key"
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) //auto-increment
    //you can also use "sequence"
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    //validate = constraint
    private String name;
    private int foodClass; //1: Dry - 2: Fresh
    private int Quantity;
    private String importDay;
    private String expiry;
    private double attribute;

    //default constructor
    public Product() {}

    public Product(String name, int foodClass, int quantity, String importDay, String expiry, double attribute) {
        this.name = name;
        this.foodClass = foodClass;
        Quantity = quantity;
        this.importDay = importDay;
        this.expiry = expiry;
        this.attribute = attribute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFoodClass() {
        return foodClass;
    }

    public void setFoodClass(int foodClass) {
        this.foodClass = foodClass;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getImportDay() {
        return importDay;
    }

    public void setImportDay(String importDay) {
        this.importDay = importDay;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public double getAttribute() {
        return attribute;
    }

    public void setAttribute(double attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foodClass=" + foodClass +
                ", Quantity=" + Quantity +
                ", importDay='" + importDay + '\'' +
                ", expiry='" + expiry + '\'' +
                ", attribute=" + attribute +
                '}';
    }
}
