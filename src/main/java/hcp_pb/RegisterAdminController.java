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
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.crypto.SecretKey;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class RegisterAdminController implements Initializable {

    @FXML
    private Label lblId;
    @FXML
    private TextField eid;
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private DatePicker bDay;
    @FXML
    private TextField eMail;
    @FXML
    private TextField mobileNum;
    @FXML
    private TextField address;
    @FXML
    private TextField zipCode;
    @FXML
    private ComboBox<String> roleCombo;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button registerButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button clearButton;

    private static final String IS_OPEN_FILE = "config/isOpen.txt"; // Relative path to isOpen file
    private static final String DB_CONFIG_FILE = "config/dbConfig.txt"; // Relative path to dbConfig file

    private static Scene scene;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             loadDatabaseConfig();
        getTheMaxUid();
         bDay.setValue(LocalDate.now());
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
    private void registerButton(ActionEvent event) throws IOException {

        String uid = lblId.getText();
        String empID = eid.getText();
        String firstName = fName.getText();
        String lastName = lName.getText();
        String birthDate = bDay.getValue() != null ? bDay.getValue().toString() : "";
        String email = eMail.getText();
        String mobile = mobileNum.getText();
        String addr = address.getText();
        String zip = zipCode.getText();
        String role = "3";
        String user = username.getText();
        String pass = password.getText();

        //validate input (call validateinput method)
        if (!validateInput(empID, firstName, lastName, email, mobile, addr, zip, user, pass)) {
            return;
        }

        if (isUsernameExists(user)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Username already exists!");
            alert.showAndWait();
            return;
        }

        
        // Encrypt the password
        String encryptedPassword;
        try {
            SecretKey key = EncryptionUtil.getKeyFromBytes(EncryptionUtil.getKeyBytes());
            encryptedPassword = EncryptionUtil.encrypt(pass, key);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Encryption Error", "An error occurred while encrypting the password.");
            return;
        }
        
        
        
        //insert to db
        String insertUser = "INSERT INTO userAccounts (userID, employeeID, fName, lName, bDay, userEmail, userMobile, userAddress, userZip, roleID, userName, userPass,isActive) "
                + "VALUES ('" + uid + "','" + empID + "', '" + firstName + "', '" + lastName + "', "
                + "'" + birthDate + "', '" + email + "', "
                + "'" + mobile + "', '" + addr + "', '" + zip + "', "
                + "'" + role + "', '" + user + "', '" + encryptedPassword + "','1')";

//        String fullName = firstName + " " + lastName;
//        String insertEmp = "INSERT INTO employeeList (employeeId, fullname, activeFlag) "
//                + "VALUES ('" + empID + "','" + fullName + "','1')";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Execute the query
            int rowsAffected = stmt.executeUpdate(insertUser);
           //   int empRowsAffected = stmt.executeUpdate(insertEmp);
            
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("System Administrator Created");
                alert.showAndWait();
                
                  logAudit("A System Administrator Account has been Created", uid);
  
                
                

                FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomeHCP.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                Stage stage = (Stage) registerButton.getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setWidth(680);  // Set the width 
                stage.setHeight(520);
                stage.show();

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to save data to the database");
        }

    }

  private boolean validateInput(String empID, String firstName, String lastName, String email, String mobile, String addr, String zip, String user, String pass) {
        String numeric = "\\d+";

        if (empID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()
                || mobile.isEmpty() || addr.isEmpty() || zip.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
            return false;
        }

        if (!empID.matches(numeric)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Employee ID must contain numbers only.");
            return false;
        }

        // Check if the Admin is at least 18 years old
        LocalDate today = LocalDate.now();
        Period age = Period.between(bDay.getValue(), today);

        if (age.getYears() < 18) {
            showAlert(Alert.AlertType.ERROR, "Error", "Admin must be at least 18 years old.");
            return false;
        }

        // Regular expression for validating email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (email == null || !email.matches(emailRegex)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid email address.");
            return false;
        }

        if (!mobile.matches("\\d{10}")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Mobile number must contain 10 digits.");
            return false;
        }

        if (!zip.matches("\\d{4}")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Zip code must contain 4 digits.");
            return false;
        }

        String usernameRegex = "^[a-zA-Z0-9]{6,16}$";

        if (!user.matches(usernameRegex)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Username must be 6-16 characters long and can only contain letters, and numbers.");
            return false;
        }

        // Regular expression for validating password
        // - At least 8 characters
        // - At least one alphabetic character (a-z or A-Z)
        // - At least one numeric character (0-9)
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

        if (!pass.matches(passwordRegex)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Password must have at least 8 characters, one letter, one number, and one special character.");
            return false;
        }

        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean isUsernameExists(String user) {
        String query = "SELECT COUNT(*) FROM userAccounts WHERE userName = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, user);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void getTheMaxUid() {

        String query = "SELECT userID FROM useraccounts ORDER BY userID DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            //hidden placeholder for next userid
            if (rs.next()) {
                int lastUser = rs.getInt("userID");
                lblId.setText(String.valueOf(lastUser + 1));
            } else {
                lblId.setText("10010");
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
