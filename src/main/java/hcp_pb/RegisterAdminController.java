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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TextField password;
    @FXML
    private Button registerButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button clearButton;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/HCP_PBP";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "!Student1";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTheMaxUid();
         bDay.setValue(LocalDate.now());
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
        String role = "System Administrator";
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

        //insert to db
        String insertUser = "INSERT INTO userAccounts (userID, employeeID, fName, lName, bDay, userEmail, userMobile, userAddress, userZip, userType, userName, userPass,isActive) "
                + "VALUES ('" + uid + "','" + empID + "', '" + firstName + "', '" + lastName + "', "
                + "'" + birthDate + "', '" + email + "', "
                + "'" + mobile + "', '" + addr + "', '" + zip + "', "
                + "'" + role + "', '" + user + "', '" + pass + "','1')";

        String fullName = firstName + " " + lastName;
        String insertEmp = "INSERT INTO employeeList (employeeId, fullname, activeFlag) "
                + "VALUES ('" + empID + "','" + fullName + "','1')";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Execute the query
            int rowsAffected = stmt.executeUpdate(insertUser);
              int empRowsAffected = stmt.executeUpdate(insertEmp);
            
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("System Administrator Created");
                alert.showAndWait();

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
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required!");
            return false;
        }

        if (!mobile.matches(numeric)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Contact should contain only numbers!");
            return false;
        }

        if (!empID.matches(numeric)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Employee ID should contain only numbers!");
            return false;
        }

        if (!zip.matches(numeric)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Zip Code should contain only numbers!");
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

}
