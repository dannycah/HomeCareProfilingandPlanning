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
import java.util.Base64;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
/**
 * FXML Controller class
 *
 * @author mark
 */
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.crypto.SecretKey;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetPassController implements Initializable {

    @FXML
    private Label lblId;
    @FXML
    private TextField username;
    @FXML
    private TextField token;
    @FXML
    private PasswordField newpass;
    @FXML
    private Button resetbtn;
    @FXML
    private Button cancelbtn;

    private static final String IS_OPEN_FILE = "config/isOpen.txt"; // Relative path to isOpen file
    private static final String DB_CONFIG_FILE = "config/dbConfig.txt"; // Relative path to dbConfig file

    private static Scene scene;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDatabaseConfig();
    }

    // Handle reset password button click
    @FXML
    private void resetbtn(ActionEvent event) {
        // Get values from the input fields
        String userNameValue = username.getText().trim();
        String tokenValue = token.getText().trim();
        String newPassValue = newpass.getText().trim();

        // Validate that no fields are empty
        if (userNameValue.isEmpty() || tokenValue.isEmpty() || newPassValue.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "All fields must be filled out.");
            return;
        }

        // Validate the new password using your custom method
        if (!isValidPassword(newPassValue)) {
            showAlert(Alert.AlertType.WARNING, "Invalid Password", "Password does not meet the security criteria.");
            return;
        }

        // Check if the username exists, the resetFlag is 1, and the token matches
        if (checkUserDetails(userNameValue, tokenValue)) {
            // If valid, update the password with encryption
            updatePassword(userNameValue, newPassValue);
            showAlert(Alert.AlertType.INFORMATION, "Password Reset", "Password has been successfully reset.");
        } else {
            // Show an error message if validation fails
            showAlert(Alert.AlertType.ERROR, "Reset Failed", "Token is already expired.");
        }
    }

    // Handle cancel button click
    @FXML
    private void cancelbtn(ActionEvent event) {
        // Get the current window (stage) and close it
        Stage stage = (Stage) cancelbtn.getScene().getWindow();
        stage.close();
    }

    // Method to update the user's password with encryption
    private void updatePassword(String username, String newPassword) {
        // Encrypt the password
        String encryptedPassword;
        try {
            SecretKey key = EncryptionUtil.getKeyFromBytes(EncryptionUtil.getKeyBytes());
            encryptedPassword = EncryptionUtil.encrypt(newPassword, key);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Encryption Error", "An error occurred while encrypting the password.");
            return;
        }

        String updateQuery = "UPDATE userAccounts SET userPass = ?, resetFlag = 0 WHERE userName = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            
            pstmt.setString(1, encryptedPassword);  // Update with the encrypted password
            pstmt.setString(2, username);           // Target the user by their username
            
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password updated successfully for user: " + username);
                
                      Stage stage = (Stage) cancelbtn.getScene().getWindow();
                     stage.close();
                             
                  logAudit(username + " have reset the password", "000000");
  
                
            } else {
                System.out.println("No user found with username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error occurred while updating the password: " + e.getMessage());
        }
    }

    // Check if the username and token match, and resetFlag is 1
    private boolean checkUserDetails(String username, String token) {
        String query = "SELECT resetFlag, resetToken FROM userAccounts WHERE userName = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int resetFlag = rs.getInt("resetFlag");
                String resetToken = rs.getString("resetToken");

                // Check if resetFlag is 1 and the tokens match
                if (resetFlag == 1 && token.equals(resetToken)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error occurred while checking user details: " + e.getMessage());
        }
        return false;
    }

    // Method to validate password (adjust as per your requirements)
    private boolean isValidPassword(String password) {
        return password.length() >= 8;  // Example validation: password must be at least 8 characters
    }

    // Helper method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
    
    
     public void logAudit(String logDesc, String useID) {
        String insertAudit = "INSERT INTO audittrail (logDateTime, logDetails, userID) VALUES (NOW(), ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement pstmt = conn.prepareStatement(insertAudit)) {

            // Set parameters for the SQL query
            pstmt.setString(1, logDesc);
            pstmt.setString(2, useID);

            // Execute the update
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception as necessary
        }
		}

}
