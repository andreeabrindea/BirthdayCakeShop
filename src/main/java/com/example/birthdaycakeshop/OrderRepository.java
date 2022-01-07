package com.example.birthdaycakeshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Comparator;

public class OrderRepository implements Serializable {

    public OrderRepository() {}

    private ObservableList<Order> orders = FXCollections.observableArrayList();

    public ObservableList<Order> getOrders() {
        return orders;
    }

    public void add(Order order){
        order.setOrder_id(getMaxId() + 1);
        orders.add(order);
    }

    public Order getById(int id){
        return orders.stream()
                .filter(o -> o.getOrder_id() == id)
                .findFirst().orElse(null);
    }

    public void remove(int id){
        var order = getById(id);
        if(order != null)
            orders.remove(order);
    }

    public void writeToFile(FileOutputStream outputFile) throws IOException {
        var out = new ObjectOutputStream(outputFile);

        out.writeObject(orders);

        out.close();
        outputFile.close();
    }

    public void readFromFile(FileInputStream inputFile) throws IOException, ClassNotFoundException {
        var in = new ObjectInputStream(inputFile);

        orders = (ObservableList<Order>) in.readObject();

        in.close();
        inputFile.close();
    }

    private int getMaxId() {
        var stream = orders.stream().max(Comparator.comparingInt(Order::getOrder_id));
        if (stream.isEmpty())
            return 0;
        return stream.get().getOrder_id();
    }
    public void displayAll(){
        System.out.println(orders);
    }
}
