/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.awt.Desktop;
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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.crypto.SecretKey;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class WelcomeHCPController implements Initializable {

    private String userOut;
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
    private Button userRegBtn;
    @FXML
    private Button cancelRegBtn;
    @FXML
    private Button clearRegBtn;

    @FXML
    private TextField usnField;
    @FXML
    private PasswordField pwdField;
    @FXML
    private Button loginBtn;
//    @FXML
//    private Button cancelBtn;

    @FXML
    private Label forgotPass;
    @FXML
    private Label regLbl;

    @FXML
    private Pane regPanel;
    @FXML
    private Pane loginPanel;

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

        roleCombo.setItems(FXCollections.observableArrayList("System Administrator", "Case Manager", "Care Service Officer"));
        roleCombo.getSelectionModel().selectFirst();
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
    private void loginBtn(ActionEvent event) throws IOException, SQLException {
        String username = usnField.getText();
        String password = pwdField.getText();

        // Retrieve the encrypted password from the database
        String encryptedPassword = getEncryptedPasswordFromDB(username);
        
        if (encryptedPassword == null) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            System.out.println("encrypted: " + username + " enc: " + encryptedPassword);  // >>not passing value
            return;
        }

        try {
            // Decrypt the password using the correct encryption key
            SecretKey key = EncryptionUtil.getKeyFromBytes(EncryptionUtil.getKeyBytes());
            String decryptedPassword = EncryptionUtil.decrypt(encryptedPassword, key);
            System.out.println(decryptedPassword);
            // Check if the decrypted password matches the input password
            if (!password.equals(decryptedPassword)) {

                System.out.println("encrypted: " + decryptedPassword + " pass: " + password); //>> not passing value
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                return;
            }

            // Validate credentials and proceed with login actions
            if (isValidCredentials(username, encryptedPassword)) {
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    // Check if the user is logged in from another device
                    String checkStatusQuery = "SELECT stats FROM userAccounts WHERE userName = ?";
                    try (PreparedStatement checkStatusStmt = connection.prepareStatement(checkStatusQuery)) {
                        checkStatusStmt.setString(1, username);
                        try (ResultSet resultSet = checkStatusStmt.executeQuery()) {
                            if (resultSet.next()) {
                                int stats = resultSet.getInt("stats");

                                if (stats == 2) {
                                    // If stats is 2, show a warning and exit
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setTitle("Warning");
                                    alert.setHeaderText(null);
                                    alert.setContentText("You are currently logged in from another device.");
                                    alert.showAndWait();
                                    return; // Exit to avoid multiple logins
                                }
                            }
                        }
                    }

                    // Proceed with login and update the status to 2 (logged in)
                    String updateStatusQuery = "UPDATE userAccounts SET stats = '2' WHERE userName = ?";
                    try (PreparedStatement updateStatusStmt = connection.prepareStatement(updateStatusQuery)) {
                        updateStatusStmt.setString(1, username);
                        updateStatusStmt.executeUpdate();
                    }

                    // Fetch userID and roleID for further actions
                    String query = "SELECT userID, roleID FROM userAccounts WHERE userName = ?";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, username);

                        try (ResultSet resultSet = statement.executeQuery()) {
                            if (resultSet.next()) {
                                String userID = resultSet.getString("userID");
                                String roleID = resultSet.getString("roleID");

                                FXMLLoader loader;

                                // Load appropriate dashboard based on role
                                if ("3".equals(roleID)) {
                                    loader = new FXMLLoader(getClass().getResource("adminDashboard.fxml"));
                                } else {
                                    loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                                }

                                Parent welcomePage = loader.load();

                                if (!"3".equals(roleID)) {
                                    DashboardController dashboardController = loader.getController();
                                    if (dashboardController != null) {
                                        dashboardController.setUser(userID);
                                    }
                                } else {
                                    AdminDashboardController adminController = loader.getController();
                                    if (adminController != null) {
                                        adminController.setUser(userID);
                                    }
                                }

                                Scene welcomeScene = new Scene(welcomePage);
                                Stage stage = (Stage) loginBtn.getScene().getWindow();
                                stage.setScene(welcomeScene);
                                stage.setTitle("Home Care Clients Profiling and Planning");
                                stage.setResizable(false);
                                stage.setWidth(1038);
                                stage.setHeight(632);

                                // Optional: Log successful login
                                // logAudit(username + " has successfully logged in", userID);
                            }
                        }
                    }
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "An error occurred while decrypting the password.");
            e.printStackTrace();
        }
    }

    private String getEncryptedPasswordFromDB(String username) {
        String query = "SELECT userPass FROM userAccounts WHERE userName = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("userPass");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isValidCredentials(String username, String password) {
        String query = "SELECT * FROM userAccounts WHERE userName = ? AND userPass = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Check if the user's access is active
                    String isActive = resultSet.getString("isActive");
                    if (isActive.equals("1")) {
                        return true;

                    } else {
                        // Prompt with access deactivated message
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Access Suspended");
                        alert.setHeaderText(null);
                        alert.setContentText("Your access is suspended! Seek Assistance.");
                        alert.showAndWait();
                        usnField.clear();
                        pwdField.clear();
                        return false;
                    }

                } else {
                    // Prompt with wrong credentials message
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Wrong Credentials");
                    alert.setHeaderText(null);
                    alert.setContentText("Username or Password is invalid");
                    alert.showAndWait();
                    usnField.clear();
                    pwdField.clear();
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void regLbl(MouseEvent event) {

        loginPanel.setVisible(false);
        regPanel.setVisible(true);
    }

    @FXML
    private void forgotPass(MouseEvent event) {
   try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("resetPass.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Services Offered");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

//                                                stage.setWidth(679);  // Set the width 
//                                                stage.setHeight(580); // Set the height
            stage.showAndWait(); // Wait for the dialog to close before continuing with main window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelBtn(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void userRegBtn(ActionEvent event) throws IOException {
        String empID = eid.getText();

    

        // Proceed with user registration
        String uid = lblId.getText();
        String firstName = fName.getText();
        String lastName = lName.getText();
        String birthDate = bDay.getValue() != null ? bDay.getValue().toString() : "";
        String email = eMail.getText();
        String mobile = mobileNum.getText();
        String addr = address.getText();
        String zip = zipCode.getText();

        // Role handling
        String role = "";
        String selectedRole = roleCombo.getValue();
        switch (selectedRole) {
            case "Case Manager":
                role = "1";
                break;
            case "Care Service Officer":
                role = "2";
                break;
            case "System Administrator":
                role = "3";
                break;
            default:
                showAlert(Alert.AlertType.ERROR, "Role Selection Error", "Invalid role selection.");
                return;
        }

        String user = username.getText();
        String pass = password.getText();
      

        // Check for existing username
        if (isUsernameExists(user)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Username already exists!");
            username.clear();
            return;
        }

    
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
        
        
            // Check if employee exists
        if (!checkEmployeeExist(empID)) {
            showAlert(Alert.AlertType.ERROR, "Employee Not Found", "Only approved employees can register as a user!");
            return;
        }
        
                
            // Check for existing employee number
        if (isEmpNumExists(empID)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Employee ID has an active account!");
            eid.clear();
            return;
        }
        
        

        //insert to db
        String insertQuery = "INSERT INTO userAccounts (userID, employeeID, fName, lName, bDay, userEmail, userMobile, userAddress, userZip, roleID, userName, userPass,isActive) "
                + "VALUES ('" + uid + "','" + empID + "', '" + firstName + "', '" + lastName + "', "
                + "'" + birthDate + "', '" + email + "', "
                + "'" + mobile + "', '" + addr + "', '" + zip + "', "
                + "'" + role + "', '" + user + "', '" + encryptedPassword + "','1')";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Execute the query
            int rowsAffected = stmt.executeUpdate(insertQuery);
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "New user account has been created.");

                logAudit(empID + ": " + firstName + " " + lastName + " has created a new account", uid);
                clearFields();
                togglePanels();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while saving data. Please try again.");
        }
    }
    
    
    
    
    
    
    
    
    
    

    private void clearFields() {
        username.clear();
        password.clear();
        eid.clear();
        fName.clear();
        lName.clear();
        eMail.clear();
        mobileNum.clear();
        address.clear();
        zipCode.clear();
        roleCombo.getSelectionModel().selectFirst();
        bDay.setValue(LocalDate.now());
    }

    private void togglePanels() {
        loginPanel.setVisible(true);
        regPanel.setVisible(false);
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
    
    
        private boolean isEmpNumExists(String empID) {
        String query = "SELECT employeeID FROM userAccounts WHERE employeeID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, empID);
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

    private boolean checkEmployeeExist(String empID) {
        boolean exists = false; // Flag to track existence
        String query = "SELECT employeeID FROM employeelist WHERE employeeID = ?"; // Query to check for empID

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Set the employeeID parameter
            pstmt.setString(1, empID);
            System.out.println("Executing query for employeeID: " + empID);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                exists = true; // empID exists if a record is found
                System.out.println("Employee exists with employeeID: " + empID);
            } else {
                System.out.println("No employee found with employeeID: " + empID);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            System.out.println("Error occurred while checking employee existence: " + e.getMessage());
        }

        return exists; // Return true if empID exists, otherwise false
    }

    private boolean validateInput(String empID, String firstName, String lastName, String email, String mobile, String addr, String zip, String user, String pass) {
        String numeric = "\\d+";

        if (empID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()
                || mobile.isEmpty() || addr.isEmpty() || zip.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required!");
            return false;
        }

        if (!mobile.matches(numeric)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Contact must contain only numbers!");
            return false;
        }

        if (!empID.matches(numeric)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Employee ID must contain only numbers!");
            return false;
        }

        if (!zip.matches(numeric)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Zip Code must contain only numbers!");
            return false;
        }

        return true;
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

    @FXML
    private void cancelRegBtn(ActionEvent event) {

        loginPanel.setVisible(true);
        regPanel.setVisible(false);

    }

    @FXML
    private void clearRegBtn(ActionEvent event) {

        clearRegFields();
    }

    private void clearRegFields() {
        if (eid != null) {
            eid.clear();
        }
        if (fName != null) {
            fName.clear();
        }
        if (lName != null) {
            lName.clear();
        }
        bDay.setValue(LocalDate.now());
        if (eMail != null) {
            eMail.clear();
        }
        if (mobileNum != null) {
            mobileNum.clear();
        }
        if (address != null) {
            address.clear();
        }
        if (zipCode != null) {
            zipCode.clear();
        }
        roleCombo.getSelectionModel().selectFirst();
        if (username != null) {
            username.clear();
        }
        if (password != null) {
            password.clear();
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
