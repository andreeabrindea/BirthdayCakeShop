package com.example.birthdaycakeshop;

import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();

    public OrderService(String inputFile) throws IOException, ClassNotFoundException {
        orderRepository.readFromFile(new FileInputStream(inputFile));
    }

    public OrderService() {  };

    public void add(Order order)
    {
        orderRepository.add(order);
    }

    public void remove(int id)
    {
        orderRepository.remove(id);
    }

    public ArrayList<BirthdayCake> orderedCakes(Date day) {
        var cakes = new ArrayList<BirthdayCake>();
        orderRepository.getOrders()
                .stream()
                .filter(o -> o.getDue_date().equals(day))
                .forEach(o -> cakes.addAll(o.getBirthdayCakes()));
        return cakes;
    }

    public LocalDate getDeliveryDate(BirthdayCake cake) {
        var stream = orderRepository.getOrders().stream()
                .filter(o -> orderContainsCake(o, cake))
                .map(Order::getDue_date)
                .findFirst();
        if (stream.isEmpty())
            return null;

        return stream.get();
    }

    public ArrayList<BirthdayCake> getCakesWithFilling(String filling) {
        var cakes = new ArrayList<BirthdayCake>();
        orderRepository.getOrders().stream()
                .filter(o -> orderContainsCakeWithFilling(o, filling))
                .forEach(o -> cakes.addAll(o.getBirthdayCakes().stream()
                        .filter(c -> c.getFilling().equals(filling))
                        .toList()));
        return cakes;
    }

    public double orderPrice(int id) throws Exception {
        var stream = orderRepository.getOrders().stream()
                .filter(o -> o.getOrder_id() == id)
                .findFirst();

        if(stream.isEmpty())
            throw new Exception("Id not found");

        var order = stream.get();
        return order.getBirthdayCakes().stream()
                .mapToDouble(BirthdayCake::getPrice)
                .sum();
    }

    public boolean orderContainsCake(Order order, BirthdayCake cake) {
        return order.getBirthdayCakes().stream()
                .anyMatch(c -> c.getId() == cake.getId());
    }

    public boolean orderContainsCakeWithFilling(Order order, String filling) {
        return order.getBirthdayCakes().stream()
                .anyMatch(c -> c.getFilling().equals(filling));
    }

    public ObservableList<Order> getAll() {
        return orderRepository.getOrders();
    }
}
