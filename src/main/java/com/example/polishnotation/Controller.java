package com.example.polishnotation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

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
    private Label sentenceLabelOne1;

    @FXML
    private Label sentenceLabelThree;

    @FXML
    private Label sentenceLabelTwo;

    @FXML
    private TextField userExpression;

    @FXML
    private MenuBar myMenuBar;

    String[] convertFromOptions = {"Infix", "Postfix", "Prefix"};
    String userConvertFrom = "";


    //---------METHODS---------

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstComboBox.getItems().addAll(convertFromOptions);
        firstComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                userConvertFrom = firstComboBox.getSelectionModel().getSelectedItem();
                userExpression.setText("hello");
            }
        });
    }
}