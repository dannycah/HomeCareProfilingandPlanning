/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class DashboardController implements Initializable {

    @FXML
    private Label headLbl;

    private Label userHolder;
    @FXML
    private Pane dashboardPane;

    @FXML
    private Label dashboardLbl;

    @FXML
    private Label createCaseLbl;

    @FXML
    private Label manageCaseLbl;
  @FXML
    private Pane manageCasePane;

    @FXML
    private Label assessmentLbl;

    @FXML
    private Label carePlanLbl;

    @FXML
    private Label budgetPlanLbl;

    private Pane myCasePane;

    @FXML
    private Label myCaseLbl;

    @FXML
    private Label manageClientLbl;

    private Pane manageClientPane;

    @FXML
    private Label newClientLbl;

    @FXML
    private Label headerLbl;
    @FXML
    private Pane csoPane;

    @FXML
    private Pane cmPane;

//    private User user;
    private String userID;
    private String roleID;
    @FXML
    private TableView<Cases> casesTbl;

    @FXML
    private TableColumn<Cases, Integer> caseID;
    @FXML
    private TableColumn<Cases, String> clientName;
    @FXML
    private TableColumn<Cases, String> caseType;
    @FXML
    private TableColumn<Cases, String> assignedCSO;
    @FXML
    private TableColumn<Cases, String> status;

    @FXML
    private TableView<MyCases> myCaseTbl;

    @FXML
    private TableColumn<MyCases, Integer> case_ID;
    @FXML
    private TableColumn<MyCases, Integer> client_ID;
    @FXML
    private TableColumn<MyCases, String> client_Name;
    @FXML
    private TableColumn<MyCases, String> case_Type;
    @FXML
    private TableColumn<MyCases, String> case_Priority;

    @FXML
    private TextField myClientID;
    @FXML
    private TextField myClientFname;
    @FXML
    private TextField myClientLname;
    @FXML
    private TextField myClientContact;
    @FXML
    private TextField myClientAddress;
    @FXML
    private TextField myClientMedicare;
    @FXML
    private DatePicker myClientBday;

    @FXML
    private TextField csID;
    @FXML
    private TextField cID;
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;

    @FXML
    private DatePicker bDay;
    @FXML
    private TextField mediCare;
    @FXML
    private TextField mobileNum;

    @FXML
    private TextField address;
    @FXML
    private TextField textCSO;
    @FXML
    private ComboBox<String> cmbAssessment;
    @FXML
    private ComboBox<String> cmbPriority;
    @FXML
    private DatePicker dateCreate;

    @FXML
    private Button myRecSave;
    @FXML
    private Button myRecCancel;
    @FXML
    private Button updateCase;
    @FXML
    private Button finaliseBtn;
    @FXML
    private Button reassignBtn;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/HCP_PBP";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "!Student1";
    @FXML
    private Pane caseManagerPane;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchBtn;
    @FXML
    private Label csoDashboardLbl;
    @FXML
    private Label reportsLbl;
    @FXML
    private Button addUserBtn;
    @FXML
    private Pane motherPane;
    @FXML
    private Pane manageUsers;
    @FXML
    private TextField eid;
    @FXML
    private TextField eMail;
    @FXML
    private TextField uid;
    @FXML
    private Button activateUser;
    @FXML
    private Button resetPass;
    @FXML
    private TableView<?> tab1;
    @FXML
    private Pane empRecords;
    @FXML
    private TextField eid1;
    @FXML
    private TextField fName1;
    @FXML
    private TextField lName1;
    @FXML
    private DatePicker bDay1;
    @FXML
    private TextField eMail1;
    @FXML
    private TextField mobileNum1;
    @FXML
    private TextField address1;
    @FXML
    private Pane viewLogs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        caseID.setCellValueFactory(new PropertyValueFactory<>("caseID"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        caseType.setCellValueFactory(new PropertyValueFactory<>("caseType"));
        assignedCSO.setCellValueFactory(new PropertyValueFactory<>("assignedCSO"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));

        case_ID.setCellValueFactory(new PropertyValueFactory<>("caseID"));
        client_ID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        client_Name.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        case_Type.setCellValueFactory(new PropertyValueFactory<>("caseType"));
        case_Priority.setCellValueFactory(new PropertyValueFactory<>("casePriority"));

        loadCases();

        initComboBox();
        dateCreate.setValue(LocalDate.now());

        // Add listener to the table for row selection
        casesTbl.setOnMouseClicked(event -> {
            try {
                handleRowSelect(event);
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Select the first record if available
        if (!casesTbl.getItems().isEmpty()) {
            casesTbl.getSelectionModel().selectFirst();
            try {
                handleRowSelect(null); // manually trigger the row selection handler
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

// Set up the mouse click event handler for the TableView
        myCaseTbl.setOnMouseClicked(event -> {
            try {
                handlMyCaseSelection(event);
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, "Error handling row selection", ex);
            }
        });

// Select the first record if available
        if (!myCaseTbl.getItems().isEmpty()) {
            // Select the first item in the TableView
            myCaseTbl.getSelectionModel().selectFirst();
            try {
                // Manually trigger the row selection handler to load details
                handlMyCaseSelection(null);
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, "Error handling initial row selection", ex);
            }
        }

    }

    public void setUser(String userID) throws SQLException {
        this.userID = userID;
        System.out.println("Setting User Details: userID=" + userID);
        //userHolder.setText(userID);

        String query = "SELECT userID, roleID FROM userAccounts WHERE userID = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userID); // Set the parameter for the userID
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String roleID = resultSet.getString("roleID");
                    System.out.println("Setting User Details: roleID=" + roleID);
                    updateUIForRole(roleID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initComboBox() {

        cmbAssessment.setItems(FXCollections.observableArrayList("New Assessment", "Re-Assessment", "Risk Assessment", "Welfare Assessment"));
        cmbAssessment.getSelectionModel().selectFirst();

        cmbPriority.setItems(FXCollections.observableArrayList("High", "Urgent", "Low"));
        cmbPriority.getSelectionModel().selectFirst();

//        comboRole.setItems(FXCollections.observableArrayList("Mid-Level", "Senior Level", "Manager", "Supervisor"));
//        comboRole.getSelectionModel().selectFirst();
    }

    private void reassignBtn(ActionEvent event) {

        Alert noRecordSelectedAlert = new Alert(Alert.AlertType.INFORMATION);
        noRecordSelectedAlert.setTitle("Warning");
        noRecordSelectedAlert.setHeaderText(null);
        noRecordSelectedAlert.setContentText("Under Development.");
        noRecordSelectedAlert.showAndWait();
    }

    private void finaliseBtn(ActionEvent event) {

        Alert noRecordSelectedAlert = new Alert(Alert.AlertType.INFORMATION);
        noRecordSelectedAlert.setTitle("Warning");
        noRecordSelectedAlert.setHeaderText(null);
        noRecordSelectedAlert.setContentText("Under Development.");
        noRecordSelectedAlert.showAndWait();
    }

    private void myRecSave(ActionEvent event) {

        Alert noRecordSelectedAlert = new Alert(Alert.AlertType.INFORMATION);
        noRecordSelectedAlert.setTitle("Warning");
        noRecordSelectedAlert.setHeaderText(null);
        noRecordSelectedAlert.setContentText("Case Data updated successfully.");
        noRecordSelectedAlert.showAndWait();

        csID.setDisable(true);
        cID.setDisable(true);
        fName.setDisable(true);
        lName.setDisable(true);
        mobileNum.setDisable(true);
        address.setDisable(true);
        mediCare.setDisable(true);
        textCSO.setDisable(true);
        cmbAssessment.setDisable(true);
        dateCreate.setDisable(true);
        cmbPriority.setDisable(true);
        myRecSave.setDisable(true);
        myRecCancel.setDisable(true);
        updateCase.setDisable(false);
        finaliseBtn.setDisable(false);
        reassignBtn.setDisable(false);

    }

    private void loadCases() {

        // SQL query
        String query = "SELECT cc.caseID AS cID, CONCAT(cd.fName, ' ', cd.lName) AS fullName, cc.caseType AS caseType, CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO FROM clientCases cc JOIN clientData cd ON cc.clientID = cd.clientID JOIN userAccounts ua ON cc.userID = ua.userID";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing data
            casesTbl.getItems().clear();

            // Process the result set and populate the TableView
            while (resultSet.next()) {
                int caseID = resultSet.getInt("cID");
                String clientName = resultSet.getString("fullName");
                String caseType = resultSet.getString("caseType");
                String assignedCSO = resultSet.getString("assignedCSO");
                String status = "Open";

                // Create a Management object
                Cases Case = new Cases(caseID, clientName, caseType, assignedCSO, status);

                // Add the Management object to the TableView
                casesTbl.getItems().add(Case);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void loadMyCases() {
        String currentUser = userHolder.getText(); // Retrieve the current user ID from userHolder

        System.out.println("Current User ID: " + currentUser);

        // Correctly use parameterized query
        String query = "SELECT cc.caseID AS cID, "
                + "cc.clientID AS clientID, "
                + // Select clientID
                "CONCAT(cd.fName, ' ', cd.lName) AS fullName, "
                + "cc.caseType AS caseType, "
                + "cc.casePriority AS casePriority, "
                + "CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO "
                + "FROM clientCases cc "
                + "JOIN clientData cd ON cc.clientID = cd.clientID "
                + "JOIN userAccounts ua ON cc.userID = ua.userID "
                + "WHERE cc.userID = ?"; // Use placeholder for userID

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, currentUser); // Set the parameter for userID

            try (ResultSet resultSet = statement.executeQuery()) {
                // Clear existing data
                myCaseTbl.getItems().clear();

                // Process the result set and populate the TableView
                while (resultSet.next()) {
                    int caseID = resultSet.getInt("cID");
                    int clientID = resultSet.getInt("clientID"); // Retrieve clientID
                    String clientName = resultSet.getString("fullName");
                    String caseType = resultSet.getString("caseType");
                    String casePriority = resultSet.getString("casePriority");

                    // Create a MyCases object
                    MyCases myCase = new MyCases(caseID, clientID, clientName, caseType, casePriority);

                    // Add the MyCases object to the TableView
                    myCaseTbl.getItems().add(myCase);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    
//   private void loadMyCases() {
//    // SQL query with a placeholder for userID
//    String query = "SELECT cc.caseID AS cID, " +
//                   "CONCAT(cd.fName, ' ', cd.lName) AS fullName, " +
//                   "cc.caseType AS caseType, " +
//                   "cc.casePriority AS casePriority, " +
//                   "CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO " +
//                   "FROM clientCases cc " +
//                   "JOIN clientData cd ON cc.clientID = cd.clientID " +
//                   "JOIN userAccounts ua ON cc.userID = ua.userID " +
//                   "WHERE cc.userID = ?";  
//
//    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//         PreparedStatement statement = connection.prepareStatement(query)) {
//
//        // Set the userID parameter
//        statement.setString(1, userID); 
//
//        try (ResultSet resultSet = statement.executeQuery()) {
//            // Clear existing data
//            myCaseTbl.getItems().clear();
//
//            // Process the result set and populate the TableView
//            while (resultSet.next()) {
//                int c_case_ID = resultSet.getInt("cID");
//                String c_client_Name = resultSet.getString("fullName");
//                String c_case_Type = resultSet.getString("caseType");
//                String c_case_Priority = resultSet.getString("casePriority");
//
//                // Create a MyCases object
//                MyCases myCase = new MyCases(c_case_ID, c_client_Name, c_case_Type, c_case_Priority);
//
//                // Add the MyCases object to the TableView
//                myCaseTbl.getItems().add(myCase);
//            }
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}
    private void handleRowSelect(MouseEvent event) throws SQLException {
        if (event == null || event.getClickCount() == 1) { // Single-click detection
            Cases selectedCase = casesTbl.getSelectionModel().getSelectedItem();
            if (selectedCase != null) {
                loadCaseDetails(selectedCase.getCaseID());
            }
        }
    }

    private void loadCaseDetails(int caseID) {
        String query = "SELECT cc.caseID AS caseID, cd.clientID AS clientID, cd.fName AS fName, "
                + "cd.lName AS lName, cd.clientMobile AS clientMobile, cd.clientAddress AS clientAddress, "
                + "cd.clientBday AS clientBday, cd.clientMedicare AS clientMedicare, "
                + "CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO "
                + "FROM clientCases cc "
                + "JOIN clientData cd ON cc.clientID = cd.clientID "
                + "JOIN userAccounts ua ON cc.userID = ua.userID "
                + "WHERE cc.caseID = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, caseID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    csID.setText(resultSet.getString("caseID"));
                    cID.setText(resultSet.getString("clientID"));
                    fName.setText(resultSet.getString("fName"));
                    lName.setText(resultSet.getString("lName"));
                    mobileNum.setText(resultSet.getString("clientMobile"));
                    address.setText(resultSet.getString("clientAddress"));
                    mediCare.setText(resultSet.getString("clientMedicare"));
                    textCSO.setText(resultSet.getString("assignedCSO"));

                    // Set other fields as needed
                    String birthdayStr = resultSet.getString("clientBday");
                    if (birthdayStr != null && !birthdayStr.isEmpty()) {
                        LocalDate birthday = LocalDate.parse(birthdayStr);
                        bDay.setValue(birthday);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handlMyCaseSelection(MouseEvent event) throws SQLException {
        if (event == null || event.getClickCount() == 1) { // Single-click detection
            MyCases selectedCase = myCaseTbl.getSelectionModel().getSelectedItem();
            if (selectedCase != null) {
                loadMyCaseDetails(selectedCase.getCaseID());
            }
        }
    }

    private void loadMyCaseDetails(int caseID) {

        // Define the SQL query with parameter placeholders
        String query = "SELECT cc.caseID AS caseID, cd.clientID AS clientID, cd.fName AS fName, "
                + "cd.lName AS lName, cd.clientMobile AS clientMobile, cd.clientAddress AS clientAddress, "
                + "cd.clientBday AS clientBday, cd.clientMedicare AS clientMedicare, "
                + "CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO "
                + "FROM clientCases cc "
                + "JOIN clientData cd ON cc.clientID = cd.clientID "
                + "JOIN userAccounts ua ON cc.userID = ua.userID "
                + "WHERE cc.caseID = ?"; // Use caseID for querying

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter for the caseID
            preparedStatement.setInt(1, caseID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Update text fields with data from the result set
                    myClientID.setText(resultSet.getString("clientID"));
                    myClientFname.setText(resultSet.getString("fName"));
                    myClientLname.setText(resultSet.getString("lName"));
                    myClientContact.setText(resultSet.getString("clientMobile"));
                    myClientAddress.setText(resultSet.getString("clientAddress"));
                    myClientMedicare.setText(resultSet.getString("clientMedicare"));

                    // Uncomment if needed: myClientCSO.setText(resultSet.getString("assignedCSO"));
                    // Handle birthday parsing and setting
                    String birthdayStr = resultSet.getString("clientBday");
                    if (birthdayStr != null && !birthdayStr.isEmpty()) {
                        try {
                            LocalDate birthday = LocalDate.parse(birthdayStr);
                            myClientBday.setValue(birthday);
                        } catch (DateTimeParseException e) {
                            System.err.println("Failed to parse date: " + e.getMessage()); // Improved error handling
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage()); // Improved error handling
        }
    }

    private void updateCase(ActionEvent event) {
        // Check if a row is selected in the table
        Cases mycase = casesTbl.getSelectionModel().getSelectedItem();
        if (mycase == null) {
            // Prompt if no row is selected
            Alert noRecordSelectedAlert = new Alert(Alert.AlertType.INFORMATION);
            noRecordSelectedAlert.setTitle("Warning");
            noRecordSelectedAlert.setHeaderText(null);
            noRecordSelectedAlert.setContentText("No record is selected.");
            noRecordSelectedAlert.showAndWait();
            return; // Exit the method if no record is selected
        }
        initComboBox();

        csID.setDisable(false);
        cID.setDisable(false);
        fName.setDisable(false);
        lName.setDisable(false);
        mobileNum.setDisable(false);
        address.setDisable(false);
        mediCare.setDisable(false);
        textCSO.setDisable(false);
        cmbAssessment.setDisable(false);
        dateCreate.setDisable(false);
        cmbPriority.setDisable(false);
        myRecSave.setDisable(false);
        myRecCancel.setDisable(false);
        updateCase.setDisable(true);
        finaliseBtn.setDisable(true);
        reassignBtn.setDisable(true);
    }

    private void myRecCancel(ActionEvent event) {

        csID.setDisable(true);
        cID.setDisable(true);
        fName.setDisable(true);
        lName.setDisable(true);
        mobileNum.setDisable(true);
        address.setDisable(true);
        mediCare.setDisable(true);
        textCSO.setDisable(true);
        cmbAssessment.setDisable(true);
        dateCreate.setDisable(true);
        cmbPriority.setDisable(true);
        myRecSave.setDisable(true);
        myRecCancel.setDisable(true);
        updateCase.setDisable(false);
        finaliseBtn.setDisable(false);
        reassignBtn.setDisable(false);

    }

    private void updateUIForRole(String roleID) {
        if ("1".equals(roleID)) {
            headerLbl.setText("Hi Case Manager!");
            csoPane.setVisible(false);
            cmPane.setVisible(true);
        } else if ("2".equals(roleID)) {
            headerLbl.setText("Hi Care Service Officer!");
            csoPane.setVisible(true);
            cmPane.setVisible(false);
        } else {
            // Handle unknown roles
            headerLbl.setText("Role not recognized");
            csoPane.setVisible(false);
            cmPane.setVisible(false);
        }
    }

//    public void setUser(String userID) throws SQLException {
//        this.userID = userID;
//        String query = "SELECT userID, roleID FROM userAccounts WHERE username = ?";
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, userID);  // Set the parameter for the username
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    String roleID = resultSet.getString("roleID");
////                userDetails.setRoleID(roleID);  // Set the roleID in userDetails
//                    System.out.println("Setting User Details:  roleID=" + roleID);
//                    
//                    if ("1".equals(roleID)) {
//                        headerLbl.setText("Hi Case Manager!");
//                        csoPane.setVisible(false);
//                        cmPane.setVisible(true);
//
//                    }
//                    if ("2".equals(roleID)) {
//                        headerLbl.setText("Hi Care Service Officer!");
//                        csoPane.setVisible(true);
//                        cmPane.setVisible(false);
//
//                    }
//
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//     public void setUserDetails(String userID, String roleID) {
//    
//        
//         this.userDetails = new UserDetails(userID, roleID);
//        
//        
//        updateRoleLabel();  
//        setDashboardControls();
//       
//    }
//    private void updateRoleLabel() {
//        String roleText;
//        String roleID = userDetails.getRoleID();
//
//        if ("1".equals(roleID)) {
//            roleText = "Hi Case Manager!";
//        } else if ("2".equals(roleID)) {
//            roleText = "Hi Care Service Officer!";
//        } else {
//            roleText = "System Administrator!";
//        }
//
//        headerLbl.setText(roleText);
//    }
//
//    private void setDashboardControls() {
//        String roleID = userDetails.getRoleID();
//
//        boolean isCaseManager = "1".equals(roleID);
//        boolean isCareServiceOfficer = "2".equals(roleID);
//
//        // Update visibility based on role
//        csoPane.setVisible(true);
//        cmPane.setVisible(false);
//    }
//    
// private void updateRoleLabel() {
//    String roleText;
//    switch (userDetails.getRoleID()) {
//        case "1":
//            roleText = "Hi Case Manager!";
//            break;
//        case "2":
//            roleText = "Hi Care Service Officer!";
//            break;
//        default:
//            roleText = "System Administrator!";
//            break;
//    }
//    headerLbl.setText(roleText);
//}
//
//private void setDashboardControls() {
//    boolean isCaseManager = "1".equals(userDetails.getRoleID());
//    boolean isCareServiceOfficer = "2".equals(userDetails.getRoleID());
//
//    // Update visibility based on role
//    csoPane.setVisible(isCareServiceOfficer);
//    cmPane.setVisible(isCaseManager);
//}
    @FXML
    private void dashboardLbl(MouseEvent event) {

        dashboardPane.setVisible(true);
        manageCasePane.setVisible(false);
        headLbl.setText("Dashboard");
    }

    @FXML
    private void manageCaseLbl(MouseEvent event) {

        headLbl.setText("Manage Cases");
        manageCasePane.setVisible(true);
        
        dashboardPane.setVisible(false);

    }

    @FXML
    private void createCaseLbl(MouseEvent event) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("createCase.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Create New Case");
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
    private void assessmentLbl(MouseEvent event) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("assessmentForm.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("HCP - Assessments");
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
    private void carePlanLbl(MouseEvent event) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("carePlan.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("HCP - Care Planning");
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
    private void budgetPlanLbl(MouseEvent event) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("budgetPlan.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("HCP - Budget Planning");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
//                                                stage.setWidth(679);  // Set the width 
//                                                stage.setHeight(580); // Set the height

            stage.showAndWait(); // Wait for the dialog to close before continuing with main window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //CSO
    @FXML
    private void csoDashboardLbl(MouseEvent event) {

        dashboardPane.setVisible(true);
        myCasePane.setVisible(false);
        manageClientPane.setVisible(false);
        headLbl.setText("Dashboard");
    }

    @FXML
    private void myCaseLbl(MouseEvent event) {
        loadMyCases();
        dashboardPane.setVisible(false);
        myCasePane.setVisible(true);
        manageClientPane.setVisible(false);
        headLbl.setText("My Cases");
    }

    @FXML
    private void manageClientLbl(MouseEvent event) {

        dashboardPane.setVisible(false);
        myCasePane.setVisible(false);
        manageClientPane.setVisible(true);
        headLbl.setText("Client Profiles");
    }

    @FXML
    private void newClientLbl(MouseEvent event) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newClient.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Register New Client");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setWidth(530);  // Set the width 
            stage.setHeight(605); // Set the height

            stage.showAndWait(); // Wait for the dialog to close before continuing with main window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reportsLbl(MouseEvent event) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reports.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("HCP - Reports");
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
    private void requestsLbl(MouseEvent event) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reports.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("HCP - Reports");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
//                                                stage.setWidth(679);  // Set the width 
//                                                stage.setHeight(580); // Set the height

            stage.showAndWait(); // Wait for the dialog to close before continuing with main window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
