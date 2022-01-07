package com.example.birthdaycakeshop;


import java.io.Serializable;

public class BirthdayCake implements Serializable {
    private int id;
    private String filling;
    private String glaze;
    private Double price;

    public BirthdayCake(int id, String filling, String glaze, Double price) {
        this.id = id;
        this.filling = filling;
        this.glaze = glaze;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilling() {
        return filling;
    }

    public void setFilling(String filling) {
        this.filling = filling;
    }

    public String getGlaze() {
        return glaze;
    }

    public void setGlaze(String glaze) {
        this.glaze = glaze;
    }

    @Override
    public String toString() {
        return "Birthdate cake id: " + id +
                ", filling: '" + filling + '\'' +
                ", glaze: '" + glaze + '\'';
    }

}
