package com.example.polishnotation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {

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

    @FXML
    private MenuItem convertOption;

    @FXML
    private MenuItem evaluateOption;

    String[] evaluateOptions = {"Infix", "Postfix", "Prefix"};
    String firstItem = "";

    //---------METHODS---------
    // 1. Initialize combo box for user to choose what type of expression
    // to evaluate
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Add string array to both combo boxes
        firstComboBox.getItems().addAll(evaluateOptions);

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

        warningLabel.setText("Please enter expression with a space between operators" +
                " and operands.");
    }

    // 2. Method for calculating given expression and conversion options
    @FXML
    protected void onConvertButtonClick() {
        // Create evaluation object to call appropriate evaluation methods
        Evaluation evaluation = new Evaluation();

        try {
            // Store expression inputted by the user in a string
            String exp = userExpression.getText();

            // Call appropriate method for evaluation
            if (firstItem.equals("Infix")) {
                answerLabel.setText(evaluation.evaluateArithmetic(exp));
            } else if (firstItem.equals("Postfix")) {
                answerLabel.setText(evaluation.evaluatePostfix());
            } else if (firstItem.equals("Prefix")) {
                answerLabel.setText(evaluation.evaluatePrefix());
            }
        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
    }

    //4. Hide error message on certain mouse clicks
    @FXML
    protected void hideWarningMessage() {
        warningLabel.setText("");
    }

    // 5. Method to go to evaluation page from menu option
    @FXML
    protected void goConvertPage(ActionEvent event) throws IOException {
        // Call getScene() on any node that isn't null. Any node could be chosen
        // from this controller. Then, typecast window to stage
        Stage stage = (Stage) warningLabel.getScene().getWindow();

        // Copy/paste start method in driver and change fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("sample.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 435);
        stage.setTitle("Polish Notation Converter");
        stage.setScene(scene);
        stage.show();
    }
}