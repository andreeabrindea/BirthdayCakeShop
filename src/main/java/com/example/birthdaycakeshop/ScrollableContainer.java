package com.example.birthdaycakeshop;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ScrollableContainer extends VBox {
    private final VBox _content;

    public ScrollableContainer() {
        var _scrollPane = new ScrollPane();
        this._content = new VBox();

        this.getChildren().add(_scrollPane);
        _scrollPane.setContent(this._content);
        _scrollPane.setMaxHeight(200);
        this._content.setPrefHeight(USE_COMPUTED_SIZE);
    }

    public void add(Node node) {
        this._content.getChildren().add(node);
    }

    public void addAll(List<? extends Node> nodes) {
        this._content.getChildren().addAll(nodes);
    }

    public void clear() {
        this._content.getChildren().clear();
    }
}
