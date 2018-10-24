package com.robertson.app;

import com.robertson.domain.interfaces.IReportable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.util.List;

/*
    Report controller is the main controller for the JavaFX Report form. Displays the grade report for the user.
 */
public class ReportController {

    // JavaFX form controls
    @FXML
    private TableView<IReportable> tableView;
    @FXML
    private TableColumn<IReportable, String> activityName;
    @FXML
    private TableColumn<IReportable, String> activityGrade;
    @FXML
    private TableColumn<IReportable, Double> activityPercentage;

    // On form initialization, load the table with the data from the activity objects
    public void initialize() {
        try {
            activityName.setCellValueFactory(new PropertyValueFactory<>("name"));
            activityGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
            activityPercentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));

            List<IReportable> reportables = getReportData();
            if (reportables != null){
            tableView.getItems().setAll(reportables);
            } else {
                JOptionPane.showMessageDialog(null, "We encountered an unexpected error.\n" +
                        "Please retry grade entry.", "Reporting Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // Get a List<IReportable> from the course grades wrapper
    private List<IReportable> getReportData(){
        return CourseGradesInitializer.getReportableActivities();
    }
}
