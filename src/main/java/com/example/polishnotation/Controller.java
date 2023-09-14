package com.example.polishnotation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
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

    @FXML
    private MenuItem convertOption;

    @FXML
    private MenuItem evaluateOption;

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

    // 2. Method for calculating given expression and conversion options
    @FXML
    protected void onConvertButtonClick() {
        // Create conversion object to call appropriate conversion methods
        Conversion conversion = new Conversion();

        try {
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

                // Call Infix to Prefix method
                else if (firstItem.equals("Infix") && secondItem.equals("Prefix")) {
                    answerLabel.setText(conversion.InfixToPrefix(exp));
                }

                // Call Postfix to Infix
                else if (firstItem.equals("Postfix") && secondItem.equals("Infix")) {
                    answerLabel.setText(conversion.PostfixToInfix(exp));
                }

                // Call Prefix to Infix
                else if (firstItem.equals("Prefix") && secondItem.equals("Infix")) {
                    answerLabel.setText(conversion.PrefixToInfix(exp));
                }

                // Call Postfix to Prefix
                else if (firstItem.equals("Postfix") && secondItem.equals("Prefix")) {
                    answerLabel.setText(conversion.PostfixToPrefix(exp));
                }

                // Call Prefix to Postfix
                else if (firstItem.equals("Prefix") && secondItem.equals("Postfix")) {
                    answerLabel.setText(conversion.PrefixToPostfix(exp));
                }
            }
        } catch (Exception e) {
            warningLabel.setText(e.getMessage());
        }
    }

    // 3. If certain methods need spaces, show message that spaces must be
    // in between operands and operators
    @FXML
    protected void Select(MouseEvent event) {
        firstItem = firstComboBox.getSelectionModel().getSelectedItem();
        secondItem = secondComboBox.getSelectionModel().getSelectedItem();

        if (firstItem.equals("Prefix") && secondItem.equals("Infix") ||
                firstItem.equals("Postfix") && secondItem.equals("Infix") ||
                firstItem.equals("Prefix") && secondItem.equals("Postfix") ||
                firstItem.equals("Postfix") && secondItem.equals("Prefix")){
            warningLabel.setText("Please enter expression with a space between operators" +
                    " and operands.");
        }
    }
    //4. Hide error message on certain mouse clicks
    @FXML
    protected void hideWarningMessage() {
        warningLabel.setText("");
    }

    // 5. Method to go to evaluation page from menu option
    @FXML
    protected void goEvaluationPage(ActionEvent event) throws IOException {
        // Call getScene() on any node that isn't null. Any node could be chosen
        // from this controller. Then, typecast window to stage
        Stage stage = (Stage) warningLabel.getScene().getWindow();

        // Copy/paste start method in driver and change fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("sample2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 435);
        stage.setTitle("Polish Notation Evaluator");
        stage.setScene(scene);
        stage.show();
    }
}