/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.control.Alert;
//
//import javafx.scene.control.Button;
//import javafx.scene.control.TextArea;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
///**
// * FXML Controller class
// *
// * @author mark
// */
//
//
//
//
//
//public class ShowEmpDataController implements Initializable {
//
//
//     @FXML private TextArea fileContentArea; // TextArea to display file content
//    private Stage dialogStage;
//
//    @FXML
//    private Button confirmUpload;
//    @FXML
//    private Button cancelUpload;
//    
//        private final String DB_URL = "jdbc:mysql://localhost:3306/HCP_PBP";
//    private final String DB_USER = "root";
//    private final String DB_PASSWORD = "!Student1";
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
//    
//    
//    
//    
//        public void setDialogStage(Stage dialogStage) {
//        this.dialogStage = dialogStage;
//    }
//
//    public void setFileContent(String content) {
//        if (fileContentArea != null) {
//            fileContentArea.setText(content);
//        }
//    }
//    
//    
//    
//  @FXML
//private void confirmUpload(ActionEvent event) {
//    String fileContent = fileContentArea.getText();
//
//    String[] lines = fileContent.split("\n");
//
//    // Prepare SQL statement
//    String insertEmployeeSQL = "INSERT INTO employeeList (employeeID, fullName, startDate) VALUES ";
//    StringBuilder values = new StringBuilder();
//    
//
//    for (String line : lines) {
//        String[] columns = line.split(",");
//        if (columns.length >= 3) { // Ensure there are enough columns
//            // Strip all leading non-digit characters from employeeID
//            String employeeID = columns[0].trim().replaceAll("^\\D+", "");  // Removing leading non-digits
//            String fullName = columns[1].trim();
//            String startDate = columns[2].trim();
//         
// 
//            
////            String endDate = (columns.length > 3 && !columns[3].trim().isEmpty()) ? 
////                             "'" + columns[3].trim() + "'" : "NULL";
////            String activeFlag = (columns.length > 4 && columns[4].trim().equalsIgnoreCase("false")) ? 
////                                "FALSE" : "TRUE"; // Default to TRUE if no flag is provided
//
//        values.append("(")
//                  .append("'" + employeeID + "', ")
//                  .append("'" + fullName + "', ")
//                  .append("'" + startDate + "'), ");
//            
//            
//        }
//    }
//
//    // Remove the trailing comma and space from the last value set
//    if (values.length() > 0) {
//        values.setLength(values.length() - 2); // Remove the final ", "
//    }
//
//    // Final SQL query
//    String finalQuery = insertEmployeeSQL + values.toString();
//
//    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); 
//         Statement stmt = conn.createStatement()) {
//        
//        // Execute the final query
//        int rowsAffected = stmt.executeUpdate(finalQuery);
//        
//        if (rowsAffected > 0) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Success");
//            alert.setHeaderText(null);
//            alert.setContentText("Employee Records Created: " + rowsAffected);
//            alert.showAndWait();
//            
//            
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText(null);
//        alert.setContentText("Failed to insert employee records.");
//        alert.showAndWait();
//    }
//    
//     Stage stage = (Stage) confirmUpload.getScene().getWindow();
//                stage.close();
//               
//  
//                
//
//}
//
//
//
//}





import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

public class ShowEmpDataController implements Initializable {

    @FXML private TextArea fileContentArea;
    @FXML private Button confirmUpload;
    @FXML private Button cancelUpload;

    private Stage dialogStage;
    private CloseCallBack closeCallback; // Add this field

    private final String DB_URL = "jdbc:mysql://localhost:3306/HCP_PBP";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "!Student1";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic
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
}
