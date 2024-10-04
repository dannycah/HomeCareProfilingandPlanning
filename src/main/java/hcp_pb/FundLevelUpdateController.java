/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author mark
 */


import javafx.scene.control.Alert;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class FundLevelUpdateController implements Initializable {

    @FXML
    private Label lblId;
    @FXML
    private TableView<fundUpdate> tblFund;
    @FXML
    private TableColumn<fundUpdate, String> levelID;
    @FXML
    private TableColumn<fundUpdate, Double> dailyFund;
    @FXML
    private TableColumn<fundUpdate, Double> fortnightlyFund;
    @FXML
    private TableColumn<fundUpdate, Double> monthlyFund;
    @FXML
    private Button saveFund;
    @FXML
    private Button cancelFund;

    private static final String IS_OPEN_FILE = "config/isOpen.txt"; // Relative path to isOpen file
    private static final String DB_CONFIG_FILE = "config/dbConfig.txt"; // Relative path to dbConfig file

    private static Scene scene;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    

    private ObservableList<fundUpdate> fundUpdates; // Store fund updates

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadDatabaseConfig();
        
        levelID.setCellValueFactory(new PropertyValueFactory<>("levelID"));
        dailyFund.setCellValueFactory(new PropertyValueFactory<>("dailyFund"));
        fortnightlyFund.setCellValueFactory(new PropertyValueFactory<>("fortnightlyFund"));
        monthlyFund.setCellValueFactory(new PropertyValueFactory<>("monthlyFund"));

        // Make the table editable
        tblFund.setEditable(true);

        // Set cell factories with custom double string converter for validation
        dailyFund.setCellFactory(TextFieldTableCell.forTableColumn(new CustomDoubleStringConverter()));
        fortnightlyFund.setCellFactory(TextFieldTableCell.forTableColumn(new CustomDoubleStringConverter()));
        monthlyFund.setCellFactory(TextFieldTableCell.forTableColumn(new CustomDoubleStringConverter()));

        // Allow edits to the table
        setUpEditCommitHandlers();

        loadFunds();
    }
    
    
    private void loadDatabaseConfig() {
        try {
            File configFile = new File(DB_CONFIG_FILE);
            if (!configFile.exists()) {
                // Optionally create a default config file if it doesn't exist
                
               // createDefaultConfigFile();
            }

            // Read the database configuration
            try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
                DB_URL = reader.readLine(); // Read the first line (URL)
                DB_USER = reader.readLine(); // Read the second line (username)
                DB_PASSWORD = reader.readLine(); // Read the third line (password)
            }
        } catch (IOException e) {
            e.printStackTrace();
//            showAlert("Error reading the database configuration.", "Error", Alert.AlertType.ERROR);
//        
//            
            
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error reading the database configuration.");
//                alert.showAndWait();
                    System.exit(1); // Exit if an error occurs while reading the file
        }
    }



    private void setUpEditCommitHandlers() {
        dailyFund.setOnEditCommit(event -> {
            Double newValue = event.getNewValue();
            if (newValue != null) { // Check if newValue is not null
                fundUpdate updatedFund = event.getRowValue();
                updatedFund.setDailyFund(newValue); // Set the valid new value
                updateFundInDatabase(updatedFund); // Save the updated fund to the database
            }
        });

        fortnightlyFund.setOnEditCommit(event -> {
            Double newValue = event.getNewValue();
            if (newValue != null) { // Check if newValue is not null
                fundUpdate updatedFund = event.getRowValue();
                updatedFund.setFortnightlyFund(newValue); // Set the valid new value
                updateFundInDatabase(updatedFund); // Save the updated fund to the database
            }
        });

        monthlyFund.setOnEditCommit(event -> {
            Double newValue = event.getNewValue();
            if (newValue != null) { // Check if newValue is not null
                fundUpdate updatedFund = event.getRowValue();
                updatedFund.setMonthlyFund(newValue); // Set the valid new value
                updateFundInDatabase(updatedFund); // Save the updated fund to the database
            }
        });
    }

    @FXML
    private void saveFund(ActionEvent event) {
        // Save all changes to the database
        for (fundUpdate fund : tblFund.getItems()) {
            updateFundInDatabase(fund);
        }

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Funding levels updated successfully.");
        successAlert.showAndWait();
    }

    @FXML
    private void cancelFund(ActionEvent event) {
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void loadFunds() {
        String query = "SELECT * FROM fundingLevel";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            tblFund.getItems().clear();
            fundUpdates = FXCollections.observableArrayList(); // Initialize the list

            while (resultSet.next()) {
                String levelID = resultSet.getString("levelID");
                double dailyFund = resultSet.getDouble("dailyFund");
                double fortnightlyFund = resultSet.getDouble("fortnightlyFund");
                double monthlyFund = resultSet.getDouble("monthlyFund");
                fundUpdate fUpdate = new fundUpdate(levelID, dailyFund, fortnightlyFund, monthlyFund);
                fundUpdates.add(fUpdate);
            }
            tblFund.setItems(fundUpdates); // Set the items to the table

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    private void updateFundInDatabase(fundUpdate fund) {
    // Validate fund fields
    if (fund.getLevelID() == null || fund.getLevelID().isEmpty()) {
        showAlert("Invalid Input", "Level ID cannot be null or empty.");
        return; // Exit the method if the level ID is invalid
    }
    
//    // Check for invalid fund values (non-positive)
//    if (fund.getDailyFund() <= 0 || fund.getFortnightlyFund() <= 0 || fund.getMonthlyFund() <= 0) {
//        showAlert("Invalid Input", "Fund values must be positive.");
//        return; // Exit the method if any fund value is non-positive
//    }

    String upsertQuery = "INSERT INTO fundingLevel (levelID, dailyFund, fortnightlyFund, monthlyFund) " +
                         "VALUES (?, ?, ?, ?) " +
                         "ON DUPLICATE KEY UPDATE " +
                         "dailyFund = VALUES(dailyFund), " +
                         "fortnightlyFund = VALUES(fortnightlyFund), " +
                         "monthlyFund = VALUES(monthlyFund)";

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement(upsertQuery)) {

        // Set parameters
        preparedStatement.setString(1, fund.getLevelID());
        preparedStatement.setDouble(2, fund.getDailyFund());
        preparedStatement.setDouble(3, fund.getFortnightlyFund());
        preparedStatement.setDouble(4, fund.getMonthlyFund());

        // Execute the upsert
        preparedStatement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    
    

    private class CustomDoubleStringConverter extends DoubleStringConverter {
        @Override
        public Double fromString(String string) {
            // Allow custom validation here
            if (string == null || string.trim().isEmpty()) {
                return null; // Allow empty input to revert to previous value
            }

            try {
                double value = Double.parseDouble(string);
                if (value <= 0) {
                    throw new NumberFormatException("Must be positive");
                }
                return value;
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Please enter a valid positive number.");
                return null; // Return null to indicate an invalid input
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}