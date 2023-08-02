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
import java.util.ResourceBundle;

public class Controller implements Initializable{

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

    String[] convertFromOptions = {"Infix", "Postfix", "Prefix"};
    String firstItem = "";


    //---------METHODS---------
    // 1. Initialize combo box for user to choose what to convert from
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Add string array to combo box
        firstComboBox.getItems().addAll(convertFromOptions);

        /* -- Alt. way to add items to combo box
        firstComboBox.setItems(FXCollections.observableArrayList("Infix", "Postfix", "Prefix"));
        */

        // Store selected item
        firstItem = firstComboBox.getSelectionModel().getSelectedItem();

//        myComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                interestRate = Float.parseFloat(myComboBox.getSelectionModel().getSelectedItem());
//            }
//        });
    }

    @FXML
    public void Select(ActionEvent event) {
        firstItem = firstComboBox.getSelectionModel().getSelectedItem();
        answerLabel.setText(firstItem);
    }
    @FXML
    protected void onConvertButtonClick() {
        answerLabel.setText("Hi, Man!");
    }
}