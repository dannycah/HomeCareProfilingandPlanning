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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Pane employeeRecordsPane;
    @FXML
    private Pane manageUsersPane;
    @FXML
    private Button userSearch;
    @FXML
    private Button resetPassBtn;
    @FXML
    private Pane caseManagerPane;
    @FXML
    private Label headerLbl;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchBtn;
    @FXML
    private Pane cmPane;
    @FXML
    private Label dashboardLbl;
    @FXML
    private Label manageCaseLbl;
    @FXML
    private Label createCaseLbl;
    @FXML
    private Label assessmentLbl;
    @FXML
    private Label carePlanLbl;
    @FXML
    private Label budgetPlanLbl;
    @FXML
    private Pane csoPane;
    @FXML
    private Label csoDashboardLbl;
    @FXML
    private Label myCaseLbl;
    @FXML
    private Label manageClientLbl;
    @FXML
    private Label newClientLbl;
    @FXML
    private Label reportsLbl;
    @FXML
    private Button addUserBtn;
    @FXML
    private Label headLbl;

    @FXML
    private Label manageUsersLbl;

    @FXML
    private Label uploadLbl;

    @FXML
    private Label recordsLbl;

    @FXML
    private Pane motherPane;
    @FXML
    private Pane dashboardPane;
    @FXML
    private Pane dashboardPane1;
    @FXML
    private TableView<UserManager> tblUser;
    @FXML
    private TableColumn<UserManager, Integer> uID;
    @FXML
    private TableColumn<UserManager, String> empName;
    @FXML
    private TextField userID;
    @FXML
    private TextField empID;
    @FXML
    private TextField Fname;
    @FXML
    private TextField Lname;
    @FXML
    private TextField Address;
    @FXML
    private TextField Mobile;
    @FXML
    private TextField Email;
    @FXML
    private DatePicker Bday;
    @FXML
    private TextField Zip;
    @FXML
    private ComboBox<String> roleCombo;
    @FXML
    private TextField usnField;
    @FXML
    private PasswordField passField;

    @FXML
    private Button updateUser;
    @FXML
    private Button deactUser;
    @FXML
    private Button cancelUpdate;
    @FXML
    private Button saveUpdate;

    private final String DB_URL = "jdbc:mysql://localhost:3306/HCP_PBP";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "!Student1";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        uID.setCellValueFactory(new PropertyValueFactory<>("uID"));
        empName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        loadUserData();
        roleCombo.setItems(FXCollections.observableArrayList("System Administrator", "Case Manager", "Care Service Officer"));
        // Set up the mouse click event handler for the TableView
        tblUser.setOnMouseClicked(event -> {
            try {
                handleUserRow(event);

            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, "Error handling row selection", ex);
            }
        });

// Select the first record if available
        if (!tblUser.getItems().isEmpty()) {
            // Select the first item in the TableView
            tblUser.getSelectionModel().selectFirst();
            try {
                // Manually trigger the row selection handler to load details
                handleUserRow(null);
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, "Error handling initial row selection", ex);
            }
        }

    }

    public void loadUserData() {
        // SQL query
        String query = "SELECT userID, CONCAT(fName, ' ', lName) AS fullName FROM userAccounts";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing data
            tblUser.getItems().clear();

            // Process the result set and populate the TableView
            while (resultSet.next()) {
                int uID = resultSet.getInt("userID");
                String empName = resultSet.getString("fullName");

                // Create a CreateCase object
                UserManager users = new UserManager(uID, empName);

                // Add the CreateCase object to the TableView
                tblUser.getItems().add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleUserRow(MouseEvent event) throws SQLException {
        if (event == null || event.getClickCount() == 1) { // Single-click detection
            UserManager sc = tblUser.getSelectionModel().getSelectedItem();
            if (sc != null) {
                loadClientDetails(sc.getUID());
            }
        }
    }

    private void loadClientDetails(int uID) {
        String query = "SELECT * FROM userAccounts WHERE userID = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, uID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

// Set values for TextFields
                    userID.setText(resultSet.getString("userID"));
                    empID.setText(resultSet.getString("employeeID"));
                    Fname.setText(resultSet.getString("fName"));
                    Lname.setText(resultSet.getString("lName"));
                    Address.setText(resultSet.getString("userAddress"));
                    Mobile.setText(resultSet.getString("userMobile"));
                    Email.setText(resultSet.getString("userEmail"));
                    Zip.setText(resultSet.getString("userZip"));
                    usnField.setText(resultSet.getString("username"));
                    //passField.setText(resultSet.getString("password"));  // Note: Password should be handled securely

// Set value for DatePicker
                    Bday.setValue(resultSet.getDate("bDay").toLocalDate());

// Set value for ComboBox
                    String roleID = resultSet.getString("roleID");

// Set value for ComboBox based on roleID
                    switch (roleID) {
                        case "1":
                            roleCombo.setValue("Case Manager");
                            break;
                        case "2":
                            roleCombo.setValue("Care Service Officer");
                            break;
                        case "3":
                            roleCombo.setValue("System Administrator");
                            break;
                        default:
                            roleCombo.setValue(""); // Or handle the case where roleID is not found
                            break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void manageCaseLbl(MouseEvent event) {
    }

    @FXML
    private void createCaseLbl(MouseEvent event) {
    }

    @FXML
    private void assessmentLbl(MouseEvent event) {
        
    }

    @FXML
    private void carePlanLbl(MouseEvent event) {
    }

    @FXML
    private void budgetPlanLbl(MouseEvent event) {
    }

    @FXML
    private void csoDashboardLbl(MouseEvent event) {

    }

    @FXML
    private void myCaseLbl(MouseEvent event) {
    }

    @FXML
    private void manageClientLbl(MouseEvent event) {
    }

    @FXML
    private void newClientLbl(MouseEvent event) {
    }

    @FXML
    private void reportsLbl(MouseEvent event) {
    }

    @FXML
    private void UpdateClient(ActionEvent event) {

// Enable all TextField components
//empID.setDisable(false);
        Fname.setDisable(false);
        Lname.setDisable(false);
        Address.setDisable(false);
        Mobile.setDisable(false);
        Email.setDisable(false);
        Zip.setDisable(false);
        usnField.setDisable(false);

// Enable DatePicker
        Bday.setDisable(false);

// Enable ComboBox
        roleCombo.setDisable(false);

// Enable Button components
        resetPassBtn.setDisable(false);
        updateUser.setDisable(true);
        deactUser.setDisable(true);
        cancelUpdate.setDisable(false);
        saveUpdate.setDisable(false);
        tblUser.setDisable(true);
    }

    @FXML
    private void CancelUpdate(ActionEvent event) {

        // Disable all TextField components
//empID.setDisable(true);
        Fname.setDisable(true);
        Lname.setDisable(true);
        Address.setDisable(true);
        Mobile.setDisable(true);
        Email.setDisable(true);
        Zip.setDisable(true);
        usnField.setDisable(true);

// Disable DatePicker
        Bday.setDisable(true);

// Disable ComboBox
        roleCombo.setDisable(true);

// Disable Button components
        resetPassBtn.setDisable(true);
        updateUser.setDisable(false);
        deactUser.setDisable(false);
        cancelUpdate.setDisable(true);
        saveUpdate.setDisable(true);

// Disable TableView
        tblUser.setDisable(false);

    }

    @FXML
    private void SaveUpdate(ActionEvent event) {
        try {

            // Retrieve values from TextField components
            String empIDText = empID.getText();
            String uID = userID.getText();
            String fnameText = Fname.getText();
            String lnameText = Lname.getText();
            String addressText = Address.getText();
            String mobileText = Mobile.getText();
            String emailText = Email.getText();
            String zipText = Zip.getText();
            String usernameText = usnField.getText();

// Retrieve value from DatePicker
            LocalDate bdayDate = Bday.getValue();

// Retrieve value from ComboBox
            String role = roleCombo.getValue();
            int roleID;

            if ("System Administrator".equals(role)) {
                roleID = 13;
            } else if ("Case Manager".equals(role)) {
                roleID = 1;
            } else if ("Care Service Officer".equals(role)) {
                roleID = 2;
            } else {
                roleID = -1; // to handle the case where the role is not recognized
            }

// SQL UPDATE query
            String updateUserData = "UPDATE userAccounts SET lName = '" + lnameText + "', fName = '" + fnameText + "', bDay = '" + bdayDate + "', userEmail = '" + emailText + "', userAddress = '" + addressText + "', userMobile = '" + mobileText + "', userZip = '" + zipText + "', roleID = '" + roleID + "', userName = '" + usernameText + "' WHERE userID = '" + uID + "'";

            // Establish connection and create statement
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {
                // Execute the update query
                int rowsUpdated = statement.executeUpdate(updateUserData);

                // Check if the update was successful
                if (rowsUpdated > 0) {
                    // Display success alert
                    Alert successAlert = new Alert(AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("User record updated successfully.");
                    successAlert.showAndWait();

                    // Disable all TextField components
//empID.setDisable(true);
                    Fname.setDisable(true);
                    Lname.setDisable(true);
                    Address.setDisable(true);
                    Mobile.setDisable(true);
                    Email.setDisable(true);
                    Zip.setDisable(true);
                    usnField.setDisable(true);

// Disable DatePicker
                    Bday.setDisable(true);

// Disable ComboBox
                    roleCombo.setDisable(true);

// Disable Button components
                    resetPassBtn.setDisable(true);
                    updateUser.setDisable(false);
                    deactUser.setDisable(false);
                    cancelUpdate.setDisable(true);
                    saveUpdate.setDisable(true);
loadUserData();
// Disable TableView
                    tblUser.setDisable(false);

                }
            } catch (SQLException e) {
                e.printStackTrace();

            }

        } catch (Exception e) {
            e.printStackTrace();
            // Display error alert

        }

    }

    @FXML
    private void logoutBtn(MouseEvent event) {
        // Get the current stage (window) from the logout button
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (stage != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Logout");
            confirmation.setHeaderText("Are you sure you want to logout?");
            confirmation.setContentText("Click OK to confirm logout or Cancel to stay.");

            // Show the confirmation dialog and wait for the user's response
            confirmation.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {
                    try {

                        // Load the WelcomeHCP.fxml file
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeHCP.fxml"));
                        Parent root = loader.load();

                        // Set the new scene to the stage
                        Scene scene = new Scene(root);
                        stage.setScene(scene);

                        stage.setWidth(680);  // Set the width 
                        stage.setHeight(520); // Set the height
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @FXML
    private void resetPassBtn(ActionEvent event) {
        showUnderDevelopmentAlert();
    }

    @FXML
    private void deactUser(ActionEvent event) {
        showUnderDevelopmentAlert();
    }

    @FXML
    private void userSearch(ActionEvent event) {
        showUnderDevelopmentAlert();
    }

    @FXML
    private void addUserBtn(ActionEvent event) {
        showUnderDevelopmentAlert();
    }

    @FXML
    private void manageUsersLbl(MouseEvent event) {
        manageUsersPane.setVisible(true);
        dashboardPane.setVisible(false);
        employeeRecordsPane.setVisible(false);

        headLbl.setText("Manage Users");
    }

    @FXML
    private void recordsLbl(MouseEvent event) {

        manageUsersPane.setVisible(false);
        dashboardPane.setVisible(false);
        employeeRecordsPane.setVisible(true);
        headLbl.setText("Manage Employee Records");
    }

    @FXML
    private void dashboardLbl(MouseEvent event) {

        manageUsersPane.setVisible(false);
        dashboardPane.setVisible(true);
        employeeRecordsPane.setVisible(false);
        headLbl.setText("Dashboard");
    }

    @FXML
    private void logsLbl(MouseEvent event) {

        showUnderDevelopmentAlert();
    }

    @FXML
    private void uploadLbl(MouseEvent event) {

        showUnderDevelopmentAlert();
    }

    private void showUnderDevelopmentAlert() {
        // Create and configure the alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Developer's Notice");
        alert.setHeaderText(null);
        alert.setContentText("This feature is currently under development.");

        // Show the alert
        alert.showAndWait();
    }

}
