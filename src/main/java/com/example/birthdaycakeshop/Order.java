package com.example.birthdaycakeshop;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable {
    private int order_id;
    private ArrayList<BirthdayCake> birthdayCakes = new ArrayList<BirthdayCake>();
    private LocalDate due_date;

    public Order(int order_id, ArrayList<BirthdayCake> birthdayCakes, LocalDate due_date) {
        this.order_id = order_id;
        this.birthdayCakes = birthdayCakes;
        this.due_date = due_date;
    }

    public Order() {

    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public ArrayList<BirthdayCake> getBirthdayCakes() {
        return birthdayCakes;
    }

    public void setBirthdayCakes(ArrayList<BirthdayCake> birthdayCakes) {
        this.birthdayCakes = birthdayCakes;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    @Override
    public String toString() {
        return "Order id: " + order_id +
                ", due date: " + due_date +
                ",\n\t birthday cakes: " + birthdayCakes.stream().map(cake -> "\n\t\t".concat(cake.toString())).toList();
    }

    public void addCake(BirthdayCake bc){
        birthdayCakes.add(bc);
    }
}
