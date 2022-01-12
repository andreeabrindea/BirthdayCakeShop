package com.example.birthdaycakeshop;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloApplication extends Application {

    private OrderService _orderService;

    @Override
    public void start(Stage stage) {
        load();

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #FFE5B4;");
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(5, 5, 5, 5));

        var cakeOptionsContainer = new BorderPane();
        var cakeOptionsButtonContainer = new HBox();
        cakeOptionsButtonContainer.setSpacing(10);
        Button addCakeButton = new Button("Add a new cake");
        Button clearButton = new Button("Clear");

        cakeOptionsButtonContainer.getChildren().addAll(addCakeButton, clearButton);
        cakeOptionsContainer.setTop(cakeOptionsButtonContainer);
        grid.add(cakeOptionsContainer, 2, 2, 1, 1);

        //Options Label
        Label options = new Label("Choose options: ");
        grid.add(options, 1, 1, 1, 1);

        //Labels
        Label filling = new Label("Choose the filling: ");
        grid.add(filling, 2, 0, 3, 17 );
        String fillings[] = { "Chocolate", "Vanilla", "Strawberries" };
        RadioButtonGroup radioButtonGroup1 = addRadioButtons(grid, 5, 3, fillings, 6);

        Label glaze = new Label("and the glaze: ");
        grid.add(glaze, 2, 0, 3, 27);

        String glazes[] = { "Chocolate", "Vanilla", "Strawberries" };
        RadioButtonGroup radioButtonGroup2 = addRadioButtons(grid, 7, 3, glazes, 10);

        clearButton.setOnMouseClicked((event) -> {
            radioButtonGroup1.buttons.forEach(btn -> btn.setSelected(false));
            radioButtonGroup2.buttons.forEach(btn -> btn.setSelected(false));
        });

        var currentCakes = new ArrayList<BirthdayCake>();
        AtomicInteger currentCakesCount = new AtomicInteger();

        addCakeButton.setOnMouseClicked((event) -> {
            var activeFilling = radioButtonGroup1.buttons.stream()
                    .filter(ToggleButton::isSelected)
                    .findFirst()
                    .get()
                    .getText();

            var activeGlaze = radioButtonGroup2.buttons.stream()
                    .filter(ToggleButton::isSelected)
                    .findFirst()
                    .get()
                    .getText();

            currentCakes.add(new BirthdayCake(currentCakesCount.incrementAndGet(), activeFilling, activeGlaze, 20.0));

            radioButtonGroup1.buttons.forEach(btn -> btn.setSelected(false));
            radioButtonGroup2.buttons.forEach(btn -> btn.setSelected(false));
        });

        //Option 2:
        Button addO = new Button("Add Order");
        addO.setOnMouseClicked((EventHandler<? super MouseEvent>) handler -> {
             _orderService.add(new Order(0, currentCakes, LocalDate.now().plusDays(2)));
//            System.out.println(currentCakes);
            currentCakes.clear();
        });
        grid.add(addO, 2, 0, 10, 40);

        //Option 3:
        TextField idToBeRemoved = new TextField();
        idToBeRemoved.setPromptText("Order's ID: ");
        grid.add(idToBeRemoved, 3, 0 , 10, 60);

        Button deleteOrder = new Button("Cancel Order");
        deleteOrder.setOnMouseClicked((EventHandler<? super MouseEvent>) handler -> {
            _orderService.remove(Integer.parseInt(idToBeRemoved.getText()));
            idToBeRemoved.clear();
        });
        grid.add(deleteOrder, 2, 0, 10, 60);

        //Show all orders:
        var ordersContainer = new ScrollableContainer();
        Label allOrders = new Label("Here will be all the orders:");
        ordersContainer.addAll(_orderService.getAll().stream().map(order -> new Label(order.toString())).toList());
        _orderService.getAll().addListener((ListChangeListener<Order>) change -> {
            if(change.next()) {
                ordersContainer.clear();
                ordersContainer.addAll(_orderService.getAll().stream().map(order -> new Label(order.toString())).toList());
            }
        });
        grid.add(allOrders, 2, 8, 10, 80);
        grid.add(ordersContainer, 2, 50, 10, 80);

        Image image = new Image(getClass().getResourceAsStream("cakeshop.JPG"));
        ImageView imageView = new ImageView(image);
        grid.add(imageView, 20, 0, 10, 105);
        Scene scene = new Scene(grid, 1200, 600);
        stage.setTitle("Welcome to my Cake Shop!");
        stage.setScene(scene);
        stage.show();
    }

    RadioButtonGroup addRadioButtons(GridPane gpane, int startingRow, int startingCol, String[] strings, int colums) {
        RadioButtonGroup radioButtonGroup = new RadioButtonGroup(new ToggleGroup(), new ArrayList<RadioButton>());

        for(String s: strings){
            var radioButton = new RadioButton(s);
            radioButton.setToggleGroup(radioButtonGroup.getToggleGroup());
            gpane.add(radioButton, startingCol, startingRow);
            radioButtonGroup.buttons.add(radioButton);
            startingCol = ++startingCol % colums;
            startingRow = startingCol == 0 ? ++startingRow : startingRow;
        }
        return radioButtonGroup;
    }

    public static void main(String[] args) { launch(); }

    private void load() {
        _orderService = new OrderService();

        ArrayList<BirthdayCake> _cakes = new ArrayList<>();
        _cakes.add(new BirthdayCake(0, "cioco", "vanilie", 10.0));
        _cakes.add(new BirthdayCake(1, "zmeura", "ciochi", 15.0));
        _orderService.add(new Order(0, _cakes, LocalDate.now().plusDays(2)));
        _orderService.add(new Order(0, _cakes, LocalDate.now().plusDays(2)));
        _orderService.add(new Order(0, _cakes, LocalDate.now().plusDays(2)));
        _orderService.add(new Order(0, _cakes, LocalDate.now().plusDays(2)));
        _orderService.add(new Order(0, _cakes, LocalDate.now().plusDays(2)));
    }
}