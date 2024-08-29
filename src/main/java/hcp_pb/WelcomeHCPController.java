/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.crypto.SecretKey;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class WelcomeHCPController implements Initializable {

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
    private TextField password;

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

    private static final String DB_URL = "jdbc:mysql://localhost:3306/HCP_PBP";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "mymyel14";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTheMaxUid();
        bDay.setValue(LocalDate.now());

        roleCombo.setItems(FXCollections.observableArrayList("System Administrator", "Case Manager", "Care Service Officer"));
        roleCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void loginBtn(ActionEvent event) throws IOException {
        String username = usnField.getText();
        String password = pwdField.getText();

        // Retrieve the encrypted password from the database
        String encryptedPassword = getEncryptedPasswordFromDB(username);

        if (encryptedPassword == null) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            return;
        }

        try {
            // Decrypt the password
            SecretKey key = EncryptionUtil.getKeyFromBytes(EncryptionUtil.getKeyBytes());
            String decryptedPassword = EncryptionUtil.decrypt(encryptedPassword, key);

            // Check if the decrypted password matches the provided password
            if (!password.equals(decryptedPassword)) {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                return;
            }

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Update user status
                try (PreparedStatement updateStmt = connection.prepareStatement("UPDATE userAccounts SET stats = '2' WHERE userName = ?")) {
                    updateStmt.setString(1, username);
                    updateStmt.executeUpdate();
                }

                // Fetch user details
                String query = "SELECT userID, roleID FROM userAccounts WHERE userName = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setString(1, username);

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            String userID = resultSet.getString("userID");
                            String roleID = resultSet.getString("roleID");

                            FXMLLoader loader;
                            if ("3".equals(roleID)) {
                                loader = new FXMLLoader(getClass().getResource("adminDashboard.fxml"));
                            } else {
                                loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                            }

                            Parent welcomePage = loader.load();

                            if (!"3".equals(roleID)) {
                                // Ensure DashboardController is initialized
                                DashboardController dashboardController = loader.getController();
                                if (dashboardController != null) {
                                    dashboardController.setUser(userID);
                                } else {
                                    throw new IllegalStateException("DashboardController not initialized");
                                }
                            }
                            Scene welcomeScene = new Scene(welcomePage);

                            // Set new scene
                            Stage stage = (Stage) loginBtn.getScene().getWindow();
                            stage.setScene(welcomeScene);
                            stage.setTitle("Home Care Clients Profiling and Planning");
                            stage.setResizable(false);
                            stage.setWidth(1038);
                            stage.setHeight(632);
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while accessing the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Decryption Error", "An error occurred while decrypting the password.");
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

    //loginBtn
    @FXML
    private void regLbl(MouseEvent event) {

        loginPanel.setVisible(false);
        regPanel.setVisible(true);
    }

    @FXML
    private void forgotPass(MouseEvent event) {
        // prompt alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Forgot Password");
        alert.setHeaderText(null);
        alert.setContentText("Please contact the administrator for password recovery assistance.");

        // Show the alert and wait for user interaction
        alert.showAndWait();
    }

    @FXML
    private void cancelBtn(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void userRegBtn(ActionEvent event) throws IOException {

        String uid = lblId.getText();
        String empID = eid.getText();
        String firstName = fName.getText();
        String lastName = lName.getText();
        String birthDate = bDay.getValue() != null ? bDay.getValue().toString() : "";
        String email = eMail.getText();
        String mobile = mobileNum.getText();
        String addr = address.getText();
        String zip = zipCode.getText();
        String user = username.getText();
        String pass = password.getText();

        String selectedRole = roleCombo.getValue();
        String role = "";
        if (selectedRole.equals("Case Manager")) {
            role = "1";
        } else if (selectedRole.equals("Care Service Officer")) {
            role = "2";
        } else if (selectedRole.equals("System Administrator")) {
            role = "3";
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
            username.clear();
            alert.showAndWait();
            return;
        }

        // Encrypt the password
        String encryptedPassword;
        try {
            SecretKey key = EncryptionUtil.getKeyFromBytes(EncryptionUtil.getKeyBytes()); // Adjust as necessary
            encryptedPassword = EncryptionUtil.encrypt(pass, key);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Encryption Error", "An error occurred while encrypting the password.");
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("New User Accounts has been created");
                alert.showAndWait();

                loginPanel.setVisible(true);
                regPanel.setVisible(false);
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

}
