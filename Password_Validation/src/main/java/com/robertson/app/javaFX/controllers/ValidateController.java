package com.robertson.app.javaFX.controllers;

import com.robertson.domain.ValidatorManager;
import com.robertson.myutils.NumTest;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javax.swing.*;

/*
    Validate Controller is used for initializing the event listeners to there respective validation and action methods.
 */
public class ValidateController {
    // FXML UI controls
    @FXML
    private Button btnValidate;
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtLength;
    @FXML
    private CheckBox chkUpper;
    @FXML
    private CheckBox chkNumber;

    // Initialize the event listeners for the form actions
    @FXML
    public void initialize(){
        btnValidate.setOnAction(event -> handleButtonAction());
        chkUpper.setOnAction(event -> handleUpperCheckBox());
        chkNumber.setOnAction(event -> handleNumberCheckBox());
        btnExit.setOnAction(event -> handleExitButton());
    }

    // Handles the validate button event, validated that the user entered a number in the min length text box.
    // Then calls the validation manager to orchestrate the validation of the entered password.
    private void handleButtonAction(){
        String redBorder = "-fx-border-color: #f44242; -fx-border-width: 2px;";
        String minLengthStr = txtLength.getText();
        String password = txtPassword.getText();
        int minLength;
        int typeOfMessage;

        if (minLengthStr.length() >= 1){
            if (NumTest.isInteger(minLengthStr)){
                minLength = Integer.parseInt(minLengthStr);
                typeOfMessage =
                        (ValidatorManager.performValidation(password, minLength))?
                                JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE;
                JOptionPane.showMessageDialog(null,
                        ValidatorManager.getLastResultString(),
                        "Validation Result", typeOfMessage);
            } else {
                txtLength.setStyle(redBorder);
            }
        } else {
            txtLength.setStyle(redBorder);
        }
    }
    // Handles the upper case check box and sets the flag for the Validation Manager
    private void handleUpperCheckBox(){
            ValidatorManager.setRequiresUpperCase(chkUpper.isSelected());
    }
    // Handles the number check box and sets the flag for the Validation Manager
    private void handleNumberCheckBox(){
            ValidatorManager.setRequiresNumber(chkNumber.isSelected());
    }

    // Handles the exit button even and closes the UI Platform.
    private void handleExitButton(){
        Platform.exit();
    }

}
