package com.example.birthdaycakeshop;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;

public class RadioButtonGroup {
    ToggleGroup toggleGroup;
    ArrayList<RadioButton> buttons;

    public RadioButtonGroup(ToggleGroup toggleGroup, ArrayList<RadioButton> buttons) {
        this.toggleGroup = toggleGroup;
        this.buttons = buttons;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }

    public ArrayList<RadioButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<RadioButton> buttons) {
        this.buttons = buttons;
    }
}
