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
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

public class ShowEmpDataController implements Initializable {

    @FXML private TextArea fileContentArea;
    @FXML private Button confirmUpload;
    @FXML private Button cancelUpload;

    private Stage dialogStage;
    private CloseCallBack closeCallback; // Add this field

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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCloseCallback(CloseCallBack closeCallback) { // Add this method
        this.closeCallback = closeCallback;
    }

    public void setFileContent(String content) {
        if (fileContentArea != null) {
            fileContentArea.setText(content);
        }
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

    

    @FXML
    private void confirmUpload(ActionEvent event) {
        String fileContent = fileContentArea.getText();
        String[] lines = fileContent.split("\n");

        String insertEmployeeSQL = "INSERT INTO employeeList (employeeID, fullName, startDate) VALUES ";
        StringBuilder values = new StringBuilder();

        for (String line : lines) {
            String[] columns = line.split(",");
            if (columns.length >= 3) {
                String employeeID = columns[0].trim().replaceAll("^\\D+", "");
                String fullName = columns[1].trim();
                String startDate = columns[2].trim();

                values.append("(")
                      .append("'" + employeeID + "', ")
                      .append("'" + fullName + "', ")
                      .append("'" + startDate + "'), ");
            }
        }

        if (values.length() > 0) {
            values.setLength(values.length() - 2);
        }

        String finalQuery = insertEmployeeSQL + values.toString();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(finalQuery);

            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Employee Records Created: " + rowsAffected);
                alert.showAndWait();
                
                 logAudit(rowsAffected+" new sets of employee records has been added to the System", "00000");
  
  
   
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to insert employee records.");
            alert.showAndWait();
        }

        if (closeCallback != null) {
            closeCallback.onClose(); // Call the callback method
        }

        Stage stage = (Stage) confirmUpload.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelUpload(ActionEvent event) {
        Stage stage = (Stage) cancelUpload.getScene().getWindow();
        stage.close();
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
