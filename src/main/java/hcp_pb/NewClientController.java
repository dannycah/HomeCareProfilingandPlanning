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
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class NewClientController implements Initializable {

    private String cmID;
    @FXML
    private TextField cID;
    @FXML
    private TextField cMedicare;
  @FXML
    private TextField zip;
    @FXML
    private TextField cFname;

    @FXML
    private TextField cLname;

    @FXML
    private DatePicker cBday;

    @FXML
    private TextField cAddress;

    @FXML
    private TextField cMobile;

    @FXML
    private TextField cEmail;

    @FXML
    private TextField cEmergencyName;

    @FXML
    private TextField cRelation;

    @FXML
    private TextField cEmergencyMob;

    @FXML
    private TextField cEmergencyMail;

    @FXML
    private Button saveNewClientBtn;

//                  @FXML
//                          private Button
//                          saveNewClientBtn;
    @FXML
    private Button cancelClientReg;

    @FXML
    private ComboBox<String> standingCmb;
    @FXML
    private ComboBox<String> fLevelCmb;
    @FXML

    private ComboBox<String> cmCmb;

    @FXML
    private TextField reffCode;

    @FXML
    private TextArea handOver;

    @FXML
    private Button saveNewClient;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/HCP_PBP";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "!Student1";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        getTheMaxCid();
        initComboBox();
        cBday.setValue(LocalDate.now());

    }

    private void initComboBox() {
        standingCmb.setItems(FXCollections.observableArrayList("New Entry", "New-Referral", "Transfer-Referral"));
        standingCmb.getSelectionModel().selectFirst();

        fLevelCmb.setItems(FXCollections.observableArrayList("Level 0", "Level 1", "Level 2", "Level 3", "Level 4"));
        fLevelCmb.getSelectionModel().selectFirst();

        // Query to fetch names of users with roleID = '1'
        String query = "SELECT userID, CONCAT(fName, ' ', lName) AS fullName FROM userAccounts WHERE roleID = '1'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing items in ComboBox
            cmCmb.getItems().clear();

            // Process the result set and populate the ComboBox
            List<String> namesList = new ArrayList<>();

//           namesList.add("Select One");
            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                cmID = resultSet.getString("userID");
                namesList.add(cmID + " - " + fullName);
            }

            // Convert the list to an ObservableList and set it in the ComboBox
            ObservableList<String> observableNamesList = FXCollections.observableArrayList(namesList);
            cmCmb.setItems(observableNamesList);
            cmCmb.getSelectionModel().selectFirst(); // Optional: Select the first item

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void standingCmb(ActionEvent event) {
        // Get the selected value from the ComboBox
        String selectedValue = standingCmb.getValue();

        // Check if the selected value is "Transfer"
        if ("New Entry".equals(selectedValue)) {
            // Enable the specified components
            reffCode.setDisable(true);
            fLevelCmb.setDisable(true);
            cmCmb.setDisable(true);
            handOver.setDisable(true);
        } else {
            // Disable the specified components

            reffCode.setDisable(false);
            fLevelCmb.setDisable(false);
            cmCmb.setDisable(false);
            handOver.setDisable(false);
        }
    }

    @FXML
    private void saveNewClientBtn(ActionEvent event) throws IOException {

        String cId = cID.getText();
        String mediCare = cMedicare.getText();
        String firstName = cFname.getText();
        String lastName = cLname.getText();
        String birthDate = cBday.getValue() != null ? cBday.getValue().toString() : "";

        String addr = cAddress.getText();
        String email = cEmail.getText();
        String mobile = cMobile.getText();

        String eContactName = cEmergencyName.getText();
        String eRelation = cRelation.getText();
        String eMob = cEmergencyMob.getText();
        String eEmail = cEmergencyMail.getText();
        String standing = standingCmb.getValue();
        String refCode = reffCode.getText();
        String level = fLevelCmb.getValue();
String zipCode = zip.getText();
        
        String instruct = (handOver.getText() != null) ? handOver.getText() : "";

  
        String cmValue = cmCmb.getValue(); // 
String cmID = cmValue.substring(0, 5); // 
String cmName = cmValue.substring(8);
        
        
        //validate input (call validateinput method)
        if (!validateInput(firstName, lastName, email, mobile, addr, mediCare, eContactName, eRelation, eMob, eEmail, refCode, zipCode)) {
            return;
        }

        //insert to db
        String regClient = "INSERT INTO clientdata (clientID, fName, lName, clientMedicare, clientAddress, clientMobile, clientEmail,clientBday, emergencyContactPerson,emergencyContactNumber, relationship,levelID, clientZip) "
                + "VALUES ('" + cId + "','" + firstName + "', '" + lastName + "', "
                + "'" + mediCare + "', '" + addr + "', '" + mobile + "',  '" + email + "', "
                + "'" + birthDate + "', '" + eContactName + "', '" + eMob + "', "
                + "'" + eRelation + "', '" + level + "', '" + zipCode + "')";

        //insert to client management
        String insertCareManagement = "INSERT INTO clientCareManagement (clientID, clientStanding, referralCode, levelID, userID, handOver) "
                + "VALUES ('" + cId + "', '" + standing + "', '" + refCode + "', "
                + "'" + level + "', '" + cmID + "', '" + instruct + "')";
        
        //insert primary contact
        
        String insertContact = "INSERT INTO clientContact "
        + "(clientID, primaryContact, primaryRelationship, primaryPhone, primaryEmail) "
        + "VALUES ( '" + cId + "',  '" + eContactName + "',  '" + eRelation + "',  '" + eMob + "', '" + eEmail + "')";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Execute the query
            int rowsAffected = stmt.executeUpdate(regClient);
            int rowsAffected2 = stmt.executeUpdate(insertCareManagement);
              int rowsAffected3 = stmt.executeUpdate(insertContact);

            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Client Record Created");
                alert.showAndWait();

                Stage stage = (Stage) saveNewClientBtn.getScene().getWindow();
                stage.close();

//               FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
//                Parent root = loader.load();
//
//                Scene scene = new Scene(root);
//                Stage stage = (Stage) saveNewClientBtn.getScene().getWindow();
//                stage.setScene(scene);
//                stage.setResizable(false);
//                stage.setWidth(680);  // Set the width 
//                stage.setHeight(520);
//                stage.show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to save data to the database");
        }

    }

    private void getTheMaxCid() {

        String query = "SELECT clientID FROM clientdata ORDER BY clientID DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            //hidden placeholder for next userid
            if (rs.next()) {
                int lastUser = rs.getInt("clientID");
                cID.setText(String.valueOf(lastUser + 1));
            } else {
                cID.setText("200001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean validateInput(String firstName, String lastName, String email, String mobile, String addr, String mediCare, String eContactName, String eRelation, String eMob, String eEmail, String refCode, String zipCode) {
        String numeric = "\\d+";

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()
                || mobile.isEmpty() || addr.isEmpty() || mediCare.isEmpty() || eContactName.isEmpty() || eRelation.isEmpty() || eMob.isEmpty() || eEmail.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required!");
            return false;
        }

        // Check if the clien is at least 65 years old
        LocalDate today = LocalDate.now();
        Period age = Period.between(cBday.getValue(), today);

        if (age.getYears() < 65) {
            showAlert(Alert.AlertType.ERROR, "Error", "Client must be at least 65 years old.");
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
            showAlert(Alert.AlertType.ERROR, "Error", "Mobile number must contain 10 digit numbers.");
            return false;
        }

        if (!mediCare.matches(numeric)) {
//<<<<<<< HEAD (aad685d) - Additional features and
            showAlert(Alert.AlertType.ERROR, "Error", "Medicare Number should contain only numbers!");
//=======
            showAlert(Alert.AlertType.ERROR, "Error", "Medicare must contain numbers only.");
            return false;
        }

        if (!eMob.matches("\\d{10}")) {
            showAlert(Alert.AlertType.ERROR, "Error", "Mobile number must contain 10 digit numbers.");
            return false;
        }
        // Regular expression for validating Emergency contact email
        String eEmailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (eEmail == null || !eEmail.matches(eEmailRegex)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid email address.");
//>>>>>>> origin/master (51df165) - Update Admin,
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
//<<<<<<< HEAD (aad685d) - Additional features and

    @FXML
    private void clearRegClient(ActionEvent event) throws IOException {

        // cID.setText("");
        cMedicare.setText("");
        cFname.setText("");
        cLname.setText("");
        cAddress.setText("");
        cMobile.setText("");
        cEmail.setText("");
        cEmergencyName.setText("");
        cRelation.setText("");
        cEmergencyMob.setText("");
        cEmergencyMail.setText("");

    }

    @FXML
    private void cancelClientReg() {
        // Get the current stage (window)
        Stage stage = (Stage) cancelClientReg.getScene().getWindow();
        // Close the stage
        stage.close();
    }

//             
//=======
//
//>>>>>>> origin/master (51df165) - Update Admin,
}
