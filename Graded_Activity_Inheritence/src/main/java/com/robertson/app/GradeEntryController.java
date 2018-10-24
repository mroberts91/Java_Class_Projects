package com.robertson.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

/*
    Grade entry controller class is the main controller for the FX grade entry form, handles the grades entered into
    the window, error reporting to the user, initializing a new course grades obj and fire up a new FX window to show
    the grade report.
 */
public class GradeEntryController {

    // Controller fields
    private String errorString = "";
    private boolean error = false;
    // JavaFX Form controls
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnClose;
    @FXML
    private TextField txtLab;
    @FXML
    private TextField txtPassFail;
    @FXML
    private TextField txtFinal;
    @FXML
    private TextField txtEssayGram;
    @FXML
    private TextField txtEssaySpell;
    @FXML
    private TextField txtEssayLength;
    @FXML
    private TextField txtEssayContent;

    // On FX form load, set the action handler methods for the form buttons
    @FXML
    public void initialize(){
        btnSubmit.setOnAction(this::handleSubmitAction);
        btnClose.setOnAction(this::handleCloseAction);
    }

    /*
        Submit button action, validate the grade inputs, display to the user if there were an errors in the data,
        if all the grade fields are entered, initialize the course grades object and fire up a new FX window to display
        the report table.
     */
    private void handleSubmitAction(ActionEvent event){
        double labGrade = validateLabGrade();
        double passFailGrade = validatePassFailGrade();
        double essayGrade = validateEssayGrade();
        double finalExamGrade = validateFinalExamGrade();
        if (!error){
            btnSubmit.setDisable(true);
            // Call the course grades wrapper to initialize the course grades obj
            CourseGradesInitializer.initialiseCourseGrades(labGrade, passFailGrade,essayGrade, finalExamGrade);
            // Create and load the new FX stage to display the report to the user
            createReportFXWindow();
        } else {
            // If the submit button is clicked with errors in the data entry, display the errors to the user.
            displayErrorMessage();
        }
    }

    // Create and load the new FX stage to display the report to the user
    private void createReportFXWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FxLayouts/report.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Report");
            stage.setScene(new Scene(root, 400, 200));
            stage.setX(100);
            stage.setY(100);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Display the error string in a swing message dialog
    private void displayErrorMessage() {
        JOptionPane.showMessageDialog(null, errorString, "Grade Entry Error",
                JOptionPane.ERROR_MESSAGE);
        errorString = "";
        error = false;
    }

    // Validate the lab grade entry
    private double validateLabGrade() {
        String labGradeTxt = txtLab.getText();
        return validateGrades(labGradeTxt, "Lab Activity", 10);
    }

    // Validate the pass fail grade entry
    private double validatePassFailGrade(){
        String passFailTxt = txtPassFail.getText();
        return validateGrades(passFailTxt, "Pass/Fail Exam", 10);
    }

    // Validate the essay grade entries
    private double validateEssayGrade(){
        String grammarTxt = txtEssayGram.getText();
        String spellingTxt = txtEssaySpell.getText();
        String lengthTxt = txtEssayLength.getText();
        String contentTxt = txtEssayContent.getText();
        double grammar = validateGrades(grammarTxt, "Essay - Grammar", 30);
        double spelling = validateGrades(spellingTxt, "Essay - Spelling", 20);
        double correctLength = validateGrades(lengthTxt, "Essay - Correct Length", 20);
        double content = validateGrades(contentTxt, "Essay - Content", 30);
        return grammar + spelling + correctLength + content;
    }

    // Validate the final exam grade entry
    private double validateFinalExamGrade(){
        String finalExamTxt = txtFinal.getText();
        return validateGrades(finalExamTxt, "Final Exam", 50);
    }

    // Validates a grade entry for empty fields, numeric values and if the grade entered is not over the max value
    private double validateGrades(String gradeString, String activityName, int max){
        double grade = -1;
        // Verify fields was not empty
        if (gradeString.length() < 1){
            error = true;
            errorString += String.format("- %s must have a grade%n", activityName);
        }
        // Verify that the field data was a number
        if (isDouble(gradeString)){
            grade = Double.parseDouble(gradeString);
        } else{
            error = true;
            errorString += String.format("- %s must be a number%n", activityName);
        }
        // Verify that the grade was not greater than max allowed points and not negative
        if (grade > max || grade < 0){
            error = true;
            errorString += String.format("- %s cannot be greater that %d points%n", activityName, max);
        }
        // Add a line break to make the errors message more readable.
        if (error) errorString += String.format("%n");
        return grade;
    }

    // Check if a string is a number
    private boolean isDouble(String str){
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    // Close button event handler, exit the program.
    private void handleCloseAction(ActionEvent event){
        System.exit(1);
    }
}
