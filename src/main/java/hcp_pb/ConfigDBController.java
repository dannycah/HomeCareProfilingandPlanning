/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class ConfigDBController implements Initializable {

    @FXML
    private TextField txtURL;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField dbName;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnComplete;
    @FXML
    private Button btnCancel;
    @FXML
    private Button testBtn;
    @FXML
    private Label connectStat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Disable the Complete button initially
        btnComplete.setDisable(true);
    }

    @FXML
    private void btnComplete(ActionEvent event) {
        // Get the database connection details from the text fields
        String dbUrl = txtURL.getText().trim();
        String dbNM = dbName.getText().trim();
        String dbUser = txtUser.getText().trim();
        String dbPassword = txtPass.getText().trim();

        // Validate that none of the fields are null or empty
        if (dbUrl.isEmpty() || dbNM.isEmpty() || dbUser.isEmpty() || dbPassword.isEmpty()) {
            showAlert("Please fill out all fields to proceed.", "Input Error", Alert.AlertType.ERROR);
            return; // Exit the method if validation fails
        }

        // Write the details to dbConfig.txt
        writeDatabaseConfig(dbUrl, dbNM, dbUser, dbPassword);
writeDatabaseInitConfig(dbUrl,dbUser,dbPassword);
        // Close the program and set isOpen to 0
        closeProgram();
    }

    @FXML
    private void testBtn(ActionEvent event) {
        // Get the database connection details from the text fields
        String dbUrl = txtURL.getText().trim();
        String dbNM = dbName.getText().trim();
        String dbUser = txtUser.getText().trim();
        String dbPassword = txtPass.getText().trim();

        // Validate that none of the fields are null or empty
        if (dbUrl.isEmpty() || dbNM.isEmpty() || dbUser.isEmpty() || dbPassword.isEmpty()) {
            showAlert("Please fill out all fields to test the connection.", "Input Error", Alert.AlertType.ERROR);
            return; // Exit the method if validation fails
        }

        // Test the database connection
        if (testDatabaseConnection(dbUrl, dbUser, dbPassword)) {
            connectStat.setText("Database is connection successful!");
            btnComplete.setDisable(false); // Enable the Complete button if connection is successful
        } else {
            connectStat.setText("Database connection failed.");
            btnComplete.setDisable(true); // Disable the Complete button if connection fails
        }
    }

    private boolean testDatabaseConnection(String dbUrl, String dbUser, String dbPassword) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbUrl + ":3306/", dbUser, dbPassword)) {
            return connection != null; // Return true if connection is successful
        } catch (SQLException e) {
            // Handle exceptions and show alert if needed
            return false; // Return false if connection fails
        }
    }

    private void showAlert(String message, String title, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        // Close the application or perform any other cancellation actions
        closeProgram();
    }

    
    
      
        private void writeDatabaseInitConfig(String dbUrl, String dbUser, String dbPassword) {
        String configFilePath = "config/dbInitConfig.txt"; // Path to the config file
        try (FileWriter writer = new FileWriter(configFilePath)) {
            writer.write("jdbc:mysql://" + dbUrl + ":3306/\n"); // Write the URL
            writer.write(dbUser + "\n"); // Write the username
            writer.write(dbPassword + "\n"); // Write the password
    
        } catch (IOException e) {
            showAlert("Error writing to configuration file: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }  
    
    private void writeDatabaseConfig(String dbUrl, String dbNM, String dbUser, String dbPassword) {
        String configFilePath = "config/dbConfig.txt"; // Path to the config file
        try (FileWriter writer = new FileWriter(configFilePath)) {
            writer.write("jdbc:mysql://" + dbUrl + ":3306/" + dbNM + "\n"); // Write the URL
            writer.write(dbUser + "\n"); // Write the username
            writer.write(dbPassword + "\n"); // Write the password
            showAlert("Database configuration is successful.\n Please re-run the Program.", "Success", Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            showAlert("Error writing to configuration file: " + e.getMessage(), "Error", Alert.AlertType.ERROR);
        }
    }

    private void closeProgram() {
        // Set isOpen to 0
        try (FileWriter writer = new FileWriter("config/src/main/starters/isOpen.txt")) {
            writer.write("0");
            System.out.println("isOpen file updated to 0."); // Debugging statement
        } catch (IOException e) {
            System.out.println("Error updating isOpen file: " + e.getMessage());
        }

        // Terminate the program
        System.exit(0);
    }
}
