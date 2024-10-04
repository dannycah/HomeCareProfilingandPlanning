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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
/**
 * FXML Controller class
 *
 * @author mark
 */
public class ServicesOfferedController implements Initializable {

    @FXML
    private Label lblId;
    @FXML
    private TableView<servicesUpdate> tblServ;
    @FXML
    private TableColumn<servicesUpdate, String> sID;
    @FXML
    private TableColumn<servicesUpdate, String> sDesc;
    @FXML
    private TableColumn<servicesUpdate, Double> sRate;
    @FXML
    private Button modServ;
    @FXML
    private Button canModServ;

 private static final String IS_OPEN_FILE = "config/isOpen.txt"; // Relative path to isOpen file
    private static final String DB_CONFIG_FILE = "config/dbConfig.txt"; // Relative path to dbConfig file

    private static Scene scene;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          loadDatabaseConfig();
        
        
        
        sID.setCellValueFactory(cellData -> cellData.getValue().sIDProperty());
        sDesc.setCellValueFactory(cellData -> cellData.getValue().sDescProperty());
        sRate.setCellValueFactory(cellData -> cellData.getValue().sRateProperty().asObject());

        // Make the table and columns editable
        tblServ.setEditable(true);
        //sID.setCellFactory(TextFieldTableCell.forTableColumn());
        sDesc.setCellFactory(TextFieldTableCell.forTableColumn());
        sRate.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        // Commit edit handlers
        sID.setOnEditCommit(event -> {
            servicesUpdate service = event.getRowValue();
            service.setSID(event.getNewValue());
        });

        sDesc.setOnEditCommit(event -> {
            servicesUpdate service = event.getRowValue();
            service.setSDesc(event.getNewValue());
        });

sRate.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter() {
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
}));

sRate.setOnEditCommit(event -> {
    servicesUpdate service = event.getRowValue();
    Double newValue = event.getNewValue();
    
    if (newValue != null) {
        service.setSRate(newValue); // Set the validated new value
    } else {
        tblServ.refresh(); // Revert to the old value
    }
});



        loadServices();
    }
    
    // Helper method to show alert messages
private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
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
  
    
    @FXML
private void saveService(ActionEvent event) {
    // Perform validation before confirmation
    for (servicesUpdate service : tblServ.getItems()) {
        // Validation check: Ensure no field is null or empty
        if (service.getSID() == null || service.getSID().isEmpty() || 
            service.getSDesc() == null || service.getSDesc().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure all fields are filled out.");
            alert.showAndWait();
            return; // Exit the method if validation fails
        }

        // Validation: Ensure rate is a positive number
        try {
            double rate = service.getSRate(); // `getSRate()` returns a double
            if (rate < 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Error");
                alert.setHeaderText(null);
                alert.setContentText("Service rate must be a valid positive number.");
                alert.showAndWait();
                return; // Exit the method if validation fails
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("Service rate must be a valid number.");
            alert.showAndWait();
            return; // Exit the method if validation fails
        }
    }

    // Show confirmation dialog after validation
    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirm Update");
    confirmationAlert.setHeaderText("Are you sure you want to update the services?");
    ButtonType confirmButton = new ButtonType("Yes");
    ButtonType cancelButton = new ButtonType("No");
    confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton);

    Optional<ButtonType> result = confirmationAlert.showAndWait();
    if (result.isPresent() && result.get() == confirmButton) {
        String query = "INSERT INTO serviceoffered (serviceID, serviceDesc, dayshift) " +
                       "VALUES (?, ?, ?) " +
                       "ON DUPLICATE KEY UPDATE " +
                       "serviceDesc = VALUES(serviceDesc), dayshift = VALUES(dayshift)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (servicesUpdate service : tblServ.getItems()) {
                // Set the parameters for the prepared statement
                preparedStatement.setString(1, service.getSID());
                preparedStatement.setString(2, service.getSDesc());
                preparedStatement.setDouble(3, service.getSRate()); // Corrected to setDouble
                preparedStatement.executeUpdate();
            }

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Services updated successfully!");
            alert.showAndWait();

            // Close the window
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to update services. Please try again.");
            alert.showAndWait();
        }
    } else {
        System.out.println("Update cancelled by the user.");
    }
}

    

    

    @FXML
    private void cancelMod(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void loadServices() {
        String query = "SELECT * FROM serviceoffered";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            tblServ.getItems().clear();

            while (resultSet.next()) {
                String sID = resultSet.getString("serviceID");
                String sDesc = resultSet.getString("serviceDesc");
                double sRate = resultSet.getDouble("dayshift");
                servicesUpdate sUpdate = new servicesUpdate(sID, sDesc, sRate);
                tblServ.getItems().add(sUpdate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

