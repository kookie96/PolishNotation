package com.example.polishnotation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    //---------GUI COMPONENTS---------

    @FXML
    private Label answerLabel;

    @FXML
    private Button convertButton;

    @FXML
    private Label expressionLabel;

    @FXML
    private ComboBox<String> firstComboBox;

    @FXML
    private ComboBox<String> secondComboBox;

    @FXML
    private Label sentenceLabelOne;

    @FXML
    private Label equalSignLabel;

    @FXML
    private Label sentenceLabelThree;

    @FXML
    private Label sentenceLabelTwo;

    @FXML
    private TextField userExpression;

    @FXML
    private MenuBar myMenuBar;

    @FXML
    private Label newLabel;

    @FXML
    private Label warningLabel;

    String[] convertOptions = {"Infix", "Postfix", "Prefix"};
    String firstItem = "";
    String secondItem = "";


    //---------METHODS---------
    // 1. Initialize combo boxes for user to choose what to convert from
    // and what to convert to
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Add string array to both combo boxes
        firstComboBox.getItems().addAll(convertOptions);
        secondComboBox.getItems().addAll(convertOptions);

        /* ---Alt. way to add items to combo box
        firstComboBox.setItems(FXCollections.observableArrayList("Infix", "Postfix", "Prefix"));
        */

        // Store selected items from both combo boxes
        firstComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                firstItem = firstComboBox.getSelectionModel().getSelectedItem();
                answerLabel.setText(firstItem);
            }
        });
        secondComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                secondItem = secondComboBox.getSelectionModel().getSelectedItem();
                answerLabel.setText(secondItem);
            }
        });
    }
    @FXML
    public void Select(ActionEvent event) {
        firstItem = firstComboBox.getSelectionModel().getSelectedItem();
        answerLabel.setText(firstItem);

        secondItem = secondComboBox.getSelectionModel().getSelectedItem();
        answerLabel.setText(secondItem);
    }


    // 2. Method for calculating given expression and conversion options
    @FXML
    protected void onConvertButtonClick() {
        // Create conversion object to call appropriate conversion methods
        Conversion conversion = new Conversion();

        try {
            String answer = "";

            // Store expression inputted by the user in a string
            String exp = userExpression.getText();

            // Display warning if same options are picked; ex. "Infix to Infix"
            if (firstItem.equals(secondItem)) {
                warningLabel.setText("WARNING! Pick two different options above.");
            }

            // Otherwise, call appropriate method for conversion
            else {
                // Call Infix to Postfix method
                if (firstItem.equals("Infix") && secondItem.equals("Postfix")) {
                    answerLabel.setText(conversion.InfixToPostfix(exp));
                }
            }
        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
    }
}