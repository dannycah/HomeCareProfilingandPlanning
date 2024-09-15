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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class DashboardController implements Initializable {

    private String cmID;

    
    @FXML
    private TextField refCode;

    @FXML
    private TextField assessmentStats;

    @FXML
    private TextField clientStat;
    @FXML
    private TextField caseDate;
    @FXML
    private TextField assessmentType;
    @FXML
    private TextField casePriority;

    @FXML
    private Button searchCase;

    @FXML
    private Button sCase;
    @FXML
    private Hyperlink logoutBtn;

    @FXML
    private Button reassignBtn;

    @FXML
    private Button finaliseBtn;
    @FXML
    private TextField aClientID;

    @FXML
    private TextField aMedicare;

    @FXML
    private TextField aFname;

    @FXML
    private TextField aLname;

    @FXML
    private TextField aAddress;

    @FXML
    private TextField aMobile;

    @FXML
    private TextField aEmail;

    @FXML
    private TextField aZip;

    @FXML
    private DatePicker aBday;

    @FXML
    private ComboBox<String> aFundLevelCombo;
//
//    @FXML
//    private TextField aAssessmentDate;

//    @FXML
//    private TextField aCSO;
    @FXML
    private TextArea aOutcome;

    @FXML
    private ComboBox<String> aCMCombo;

    @FXML
    private TextField aEContact;

    @FXML
    private TextField aERelation;

    @FXML
    private TextField aEMobile;

    @FXML
    private TextField aEMail;
    
    @FXML
    private TextField aCaseSearch;

    @FXML
    private Button aDeactClient;
    @FXML
    private Button completeTaskBtn;
    @FXML
    private Button clientSearch;

    @FXML
    private TextField clientSearches;

    @FXML
    private Button aSaveUpdate;

    @FXML
    private Button aCancelUpdate;

    @FXML
    private Button aUpdateClient;

    @FXML
    private Label headLbl;
    @FXML
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
    @FXML
    private Pane myCasePane;

    @FXML
    private Label myCaseLbl;

    @FXML
    private Label manageClientLbl;
    @FXML
    private Pane manageClientPane;

    @FXML
    private Label newClientLbl;

    @FXML
    private Label headerLbl;
    @FXML
    private Pane csoPane;

    @FXML
    private Pane cmPane;

    @FXML
    private TextField clID;

    @FXML
    private TextField clDate;

    @FXML
    private TextArea clReason;

    @FXML
    private Button clConfirm;

    @FXML
    private Button clCancel;

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
    private TableView<Clients> tblClient;

    @FXML
    private TableColumn<Clients, Integer> acID;

    @FXML
    private TableColumn<Clients, String> acName;

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
    private TextField caseSearch;

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
    private ComboBox asignedCSO;
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
    private TableView<String> tab1;
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

    @FXML
    private Button clientAssessment;

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

        acID.setCellValueFactory(new PropertyValueFactory<>("acID"));
        acName.setCellValueFactory(new PropertyValueFactory<>("acName"));

        loadCases();
        loadClients();
        loadMyCases();
        initClientComboBox();

        initComboBox();
        dateCreate.setValue(LocalDate.now());

        //CM case table
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

        //CSO - Mycasetable
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

        //CSO client table
        // Set up the mouse click event handler for the TableView
        tblClient.setOnMouseClicked(event -> {
            try {
                handleClientSelect(event);

            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, "Error handling row selection", ex);
            }
        });

// Select the first record if available
        if (!tblClient.getItems().isEmpty()) {
            // Select the first item in the TableView
            tblClient.getSelectionModel().selectFirst();
            try {
                // Manually trigger the row selection handler to load details
                handleClientSelect(null);
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, "Error handling initial row selection", ex);
            }
        }

        // tblClient
    }

//    public void setUser(String userID) throws SQLException {
//        this.userID = userID;
//        System.out.println("Setting User Details: userID=" + userID);
//        userHolder.setText(userID);
//
//        String query = "SELECT userID, roleID FROM userAccounts WHERE userID = ?";
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, userID); // Set the parameter for the userID
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    String roleID = resultSet.getString("roleID");
//                    System.out.println("Setting User Details: roleID=" + roleID);
//                    updateUIForRole(roleID);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    
//        public void setUser(String userID) {
//    
//    //Encryption - FDV
//    
////     public void setUser(String userID) {
////        this.userID = userID;
////        System.out.println("Setting User Details: userID=" + userID);
////        //userHolder.setText(userID);
////
////        String query = "SELECT userID, roleID FROM userAccounts WHERE userID = ?";
////       
////        // Run database query on a background thread to avoid blocking the JavaFX Application Thread
////        new Thread(() -> {
////            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
////                 PreparedStatement statement = connection.prepareStatement(query)) {
////                statement.setString(1, userID); // Set the parameter for the userID
////
////                try (ResultSet resultSet = statement.executeQuery()) {
////                    if (resultSet.next()) {
////                        String roleID = resultSet.getString("roleID");
////                        System.out.println("Setting User Details: roleID=" + roleID);
////                        // Update UI safely on the JavaFX Application Thread
////                        Platform.runLater(() -> updateUIForRole(roleID));
////                    } else {
////                        Platform.runLater(() -> handleNoUserFound());
////                    }
////                }
////            } catch (SQLException e) {
////                e.printStackTrace();
////                Platform.runLater(() -> handleDatabaseError(e));
////            }
////        
////        }).start();
//    }
//        
//     
//
//
//    private void handleNoUserFound() {
//        // Handle case where no user was found with the provided userID
//        System.err.println("No user found with userID: " + userID);
//    }
//
//    private void handleDatabaseError(SQLException e) {
//        // Handle database errors
//        System.err.println("Database error occurred: " + e.getMessage());
//    }+
    public void setUser(String userID) throws SQLException {
        this.userID = userID;
        System.out.println("Setting User Details: userID=" + userID);
        userHolder.setText(userID);

        String query = "SELECT userID, roleID, CONCAT(fName, ' ', lName) AS fullName FROM userAccounts WHERE userID = ?";
        // Run database query on a background thread to avoid blocking the JavaFX Application Thread
        new Thread(() -> {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userID); // Set the parameter for the userID

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String roleID = resultSet.getString("roleID");
                        String accUser = resultSet.getString("fullName");
                        System.out.println("Setting User Details: roleID=" + roleID);
                        // Update UI safely on the JavaFX Application Thread
                        Platform.runLater(() -> updateUIForRole(roleID, accUser));
                    } else {
                        Platform.runLater(() -> handleNoUserFound());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Platform.runLater(() -> handleDatabaseError(e));
            }
        }).start();
    }

    private void handleNoUserFound() {
        // Handle case where no user was found with the provided userID
        System.err.println("No user found with userID: " + userID);
    }

    private void handleDatabaseError(SQLException e) {
        // Handle database errors
        System.err.println("Database error occurred: " + e.getMessage());
    }

    private void initComboBox() {

        cmbAssessment.setItems(FXCollections.observableArrayList("New Assessment", "Re-Assessment", "Risk Assessment", "Welfare Assessment"));
        cmbAssessment.getSelectionModel().selectFirst();

        cmbPriority.setItems(FXCollections.observableArrayList("High", "Medium", "Low"));
        cmbPriority.getSelectionModel().selectFirst();

//        comboRole.setItems(FXCollections.observableArrayList("Mid-Level", "Senior Level", "Manager", "Supervisor"));
//        comboRole.getSelectionModel().selectFirst();
        aFundLevelCombo.setItems(FXCollections.observableArrayList("Level 0", "Level 1", "Level 2", "Level 3", "Level 4"));
        aFundLevelCombo.getSelectionModel().selectFirst();

        // Query to fetch names of users with roleID = '1'
        String CMquery = "SELECT userID, CONCAT(fName, ' ', lName) AS fullName FROM userAccounts WHERE roleID = '1'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(CMquery)) {

            // Clear existing items in ComboBox
            aCMCombo.getItems().clear();

            // Process the result set and populate the ComboBox
            List<String> namesList = new ArrayList<>();

//           namesList.add("Select One");
            while (resultSet.next()) {
                String fullName = resultSet.getString("fullName");
                cmID = resultSet.getString("userID");
                namesList.add(fullName);
            }

            // Convert the list to an ObservableList and set it in the ComboBox
            ObservableList<String> observableNamesList = FXCollections.observableArrayList(namesList);
            aCMCombo.setItems(observableNamesList);
            aCMCombo.getSelectionModel().selectFirst(); // Optional: Select the first item

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Query to fetch names of users with roleID = '2'
        String CSOquery = "SELECT userID, CONCAT(fName, ' ', lName) AS fullName FROM userAccounts WHERE roleID = '2'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(CSOquery)) {

            // Clear existing items in ComboBox
            asignedCSO.getItems().clear();

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
            asignedCSO.setItems(observableNamesList);
            asignedCSO.getSelectionModel().selectFirst(); // Optional: Select the first item

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//    @FXML
//    private void reassignBtn(ActionEvent event) {
//
//String aStats = assessmentStats.getText();
//if (!aStats.equals("Not Started")) {
//    // Display a message if the assessment has already been started
//    Alert assessmentStartedAlert = new Alert(Alert.AlertType.INFORMATION);
//    assessmentStartedAlert.setTitle("Warning");
//    assessmentStartedAlert.setHeaderText(null);
//    assessmentStartedAlert.setContentText("The assessment has either already completed or started and cannot be updated at the moment.");
//    assessmentStartedAlert.showAndWait();
//    return; // Exit the method if the assessment is not "Not Started"
//}
//    }
    @FXML
    private void finaliseBtn(ActionEvent event) {

        String aStats = assessmentStats.getText();
        if (!aStats.equals("Complete")) {
            // Display a message if the assessment has already been started
            Alert assessmentStartedAlert = new Alert(Alert.AlertType.INFORMATION);
            assessmentStartedAlert.setTitle("Warning");
            assessmentStartedAlert.setHeaderText(null);
            assessmentStartedAlert.setContentText("The assessment is either in progress or not not started");
            assessmentStartedAlert.showAndWait();
            return; // Exit the method if the assessment is not "Not Started"
        }

        Alert noRecordSelectedAlert = new Alert(Alert.AlertType.INFORMATION);
        noRecordSelectedAlert.setTitle("Warning");
        noRecordSelectedAlert.setHeaderText(null);
        noRecordSelectedAlert.setContentText("Opening Care Plannning");
        noRecordSelectedAlert.showAndWait();

    }

    @FXML
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
        asignedCSO.setDisable(true);
        cmbAssessment.setDisable(true);
        dateCreate.setDisable(true);
        cmbPriority.setDisable(true);
        myRecSave.setDisable(true);
        myRecCancel.setDisable(true);
        updateCase.setDisable(false);
        finaliseBtn.setDisable(false);
        reassignBtn.setDisable(false);
        casesTbl.setDisable(true);

    }

    private void loadCases() {

        // SQL query
        String query = "SELECT cc.caseID AS cID, CONCAT(cd.fName, ' ', cd.lName) AS fullName, cc.caseType AS caseType, assessmentStatus AS caseStats, CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO FROM clientCases cc JOIN clientData cd ON cc.clientID = cd.clientID JOIN userAccounts ua ON cc.userID = ua.userID WHERE cc.createUser = '" + userID + "'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing data
            casesTbl.getItems().clear();

            // Process the result set and populate the TableView
            while (resultSet.next()) {
                int caseID = resultSet.getInt("cID");
                String clientName = resultSet.getString("fullName");
                String caseType = resultSet.getString("caseType");
                String assignedCSO = resultSet.getString("assignedCSO");
                String status = resultSet.getString("caseStats");

                // Create a Management object
                Cases Case = new Cases(caseID, clientName, caseType, assignedCSO, status);

                // Add the Management object to the TableView
                casesTbl.getItems().add(Case);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//String currentUser = "";

    public void loadMyCases() {
        String currentUser = userHolder.getText(); // Retrieve the current user ID from userHolder

        System.out.println("Current User ID: " + currentUser);

        // Correctly use parameterized query
        String query = "SELECT cc.caseID AS cID, "
                + "cc.clientID AS clientID, "
                + "CONCAT(cd.fName, ' ', cd.lName) AS fullName, "
                + "cc.caseType AS caseType, "
                + "cc.casePriority AS casePriority, "
                + "CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO "
                + "FROM clientCases cc "
                + "JOIN clientData cd ON cc.clientID = cd.clientID "
                + "JOIN userAccounts ua ON cc.userID = ua.userID "
                + "WHERE cc.userID = ? "
                + "AND cc.assessmentStatus NOT IN ('Complete', 'Closed') "
                + "ORDER BY FIELD(cc.casePriority, 'High', 'Medium', 'Low')";

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

    private void loadClients() {

        // SQL query
        String query = "SELECT clientID, CONCAT(fName, ' ', lName) AS fullName FROM clientData WHERE isActive = '1'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing data
            tblClient.getItems().clear();

            // Process the result set and populate the TableView
            while (resultSet.next()) {
                int acID = resultSet.getInt("clientID");
                String acName = resultSet.getString("fullName");

                // Create a Management object
                Clients clients = new Clients(acID, acName);

                // Add the Management object to the TableView
                tblClient.getItems().add(clients);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void initClientComboBox() {

//         Initialize combo boxes
        aFundLevelCombo.setItems(FXCollections.observableArrayList("Level 1", "Level 2", "Level 3"));
        aFundLevelCombo.getSelectionModel().selectFirst();

        aCMCombo.setItems(FXCollections.observableArrayList("Joylyn Espinosa", "Joylyn Espinosa", "Joylyn Espinosa", "Joylyn Espinosa"));
        aCMCombo.getSelectionModel().selectFirst();
//        
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
        String query = "SELECT cc.caseID AS caseID, "
                + "cd.clientID AS clientID, "
                + "cd.fName AS fName, "
                + "cd.lName AS lName, "
                + "cd.clientMobile AS clientMobile, "
                + "cd.clientAddress AS clientAddress, "
                + "cd.clientBday AS clientBday, "
                + "cd.clientMedicare AS clientMedicare, "
                + "cc.caseType AS caseType, "
                + "cc.casePriority AS casePriority, "
                + "cc.caseAssignment AS caseAssignment, "
                + "cc.caseDate AS caseDate, "
                + "cc.assessmentStatus as Stat, "
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
//                    asignedCSO.setValue(resultSet.getString("assignedCSO"));
                    cmbAssessment.setValue(resultSet.getString("caseType"));
                    cmbPriority.setValue(resultSet.getString("casePriority"));
                    asignedCSO.setValue(resultSet.getString("caseAssignment"));
                    assessmentStats.setText(resultSet.getString("Stat"));

                    String startDate = resultSet.getString("caseDate");
                    if (startDate != null && !startDate.isEmpty()) {
                        LocalDate cDate = LocalDate.parse(startDate);
                        dateCreate.setValue(cDate);
                    }

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
        String query = "SELECT cc.caseID AS selectedcaseID, "
                + "cd.clientID AS clientID, "
                + "cd.fName AS fName, "
                + "cd.lName AS lName, "
                + "cd.clientMobile AS clientMobile, "
                + "cd.clientAddress AS clientAddress, "
                + "cd.clientBday AS clientBday, "
                + "cd.clientMedicare AS clientMedicare, "
                + "cc.caseType AS caseType, "
                + "cc.caseDate AS caseDate, "
                + "cc.clientType AS clientType, "
                + "cc.casePriority AS casePriority, "
                + "CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO "
                + "FROM clientCases cc "
                + "JOIN clientData cd ON cc.clientID = cd.clientID "
                + "JOIN userAccounts ua ON cc.userID = ua.userID "
                + "WHERE cc.caseID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the parameter for the caseID
            preparedStatement.setInt(1, caseID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Update text fields with data from the result set
                    clID.setText(resultSet.getString("selectedcaseID"));
                    myClientID.setText(resultSet.getString("clientID"));
                    myClientFname.setText(resultSet.getString("fName"));
                    myClientLname.setText(resultSet.getString("lName"));
                    myClientContact.setText(resultSet.getString("clientMobile"));
                    myClientAddress.setText(resultSet.getString("clientAddress"));
                    myClientMedicare.setText(resultSet.getString("clientMedicare"));
                    clientStat.setText(resultSet.getString("clientType"));
                    caseDate.setText(resultSet.getString("caseDate"));
                    assessmentType.setText(resultSet.getString("caseType"));
                    casePriority.setText(resultSet.getString("casePriority"));

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

    private void handleClientSelect(MouseEvent event) throws SQLException {
        if (event == null || event.getClickCount() == 1) { // Single-click detection
            Clients selectedClient = tblClient.getSelectionModel().getSelectedItem();
            if (selectedClient != null) {
                loadClientDetails(selectedClient.getAcID());
            }
        }
    }

//    private void loadClientDetails(int clientID) {
//        String query = "SELECT * FROM clientData WHERE clientID = ?";
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//
//            preparedStatement.setInt(1, clientID);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    aClientID.setText(resultSet.getString("clientID"));
//                    aFname.setText(resultSet.getString("fName"));
//                    aLname.setText(resultSet.getString("lName"));
//                    aMedicare.setText(resultSet.getString("clientMedicare"));
//                    aAddress.setText(resultSet.getString("clientAddress"));
//                    aMobile.setText(resultSet.getString("clientMobile"));
//                    // aEmail.setText(resultSet.getString("clientEmail"));
//
//                    String birthdayStr = resultSet.getString("clientBday");
//                    if (birthdayStr != null && !birthdayStr.isEmpty()) {
//                        LocalDate birthday = LocalDate.parse(birthdayStr);
//                        aBday.setValue(birthday);
//                        
//                        
//                  aEContact.setText(resultSet.getString("emergencyContactPerson"));
//              aERelation.setText(resultSet.getString("relationship"));
//         aEMobile.setText(resultSet.getString("emergencyContactNumber"));
////           aEMail.getText();
//                        
////                            aEContact.setText(resultSet.getString("econtact"));
//////              aERelation.setText(resultSet.getString("erelationship"));
//////         aEMobile.setText(resultSet.getString("ePhone"));
//////           aEMail.setText(resultSet.getString("eemail"));
//////                        
////                                   
//                        
//                        
//                    }
//
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    private void loadClientDetails(int clientID) {
        String clientDataQuery = "SELECT * FROM clientData WHERE clientID = ?";
        String clientContactQuery = "SELECT * FROM clientContact WHERE clientID = ?";
        String clientManagement = "SELECT * FROM clientCareManagement WHERE clientID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // First query: Fetch client data
            try (PreparedStatement preparedStatement = connection.prepareStatement(clientDataQuery)) {
                preparedStatement.setInt(1, clientID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        aClientID.setText(resultSet.getString("clientID"));
                        aFname.setText(resultSet.getString("fName"));
                        aLname.setText(resultSet.getString("lName"));
                        aMedicare.setText(resultSet.getString("clientMedicare"));
                        aAddress.setText(resultSet.getString("clientAddress"));
                        aZip.setText(resultSet.getString("clientZip"));
                        aMobile.setText(resultSet.getString("clientMobile"));
                        aEmail.setText(resultSet.getString("clientEmail"));
                        String birthdayStr = resultSet.getString("clientBday");
                        if (birthdayStr != null && !birthdayStr.isEmpty()) {
                            LocalDate birthday = LocalDate.parse(birthdayStr);
                            aBday.setValue(birthday);
                        }
                    }
                }
            }

            // Second query: Fetch client contact data
            try (PreparedStatement preparedStatement = connection.prepareStatement(clientContactQuery)) {
                preparedStatement.setInt(1, clientID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        aEContact.setText(resultSet.getString("primaryContact"));
                        aERelation.setText(resultSet.getString("primaryRelationship"));
                        aEMobile.setText(resultSet.getString("primaryPhone"));
                        aEMail.setText(resultSet.getString("primaryEmail"));
                    } else {
                        // Clear contact fields if no client contact data is found
                        aEContact.clear();
                        aERelation.clear();
                        aEMobile.clear();
                        aEMail.clear();
                    }
                }
            }

            // Third query: Fetch client management 
            try (PreparedStatement preparedStatement = connection.prepareStatement(clientManagement)) {
                preparedStatement.setInt(1, clientID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        aFundLevelCombo.setValue(resultSet.getString("levelID"));
                        aCMCombo.setValue(Integer.toString(resultSet.getInt("userID"))); // need to replace with cm name

                        refCode.setText(resultSet.getString("referralCode"));
                        aOutcome.setText(resultSet.getString("handOver"));
                    } else {
//                        // Clear contact fields if no client contact data is found
                        refCode.clear();
                        aOutcome.clear();
                        aFundLevelCombo.setValue("Not Assessed");
                        aCMCombo.setValue("Not Assigned");
//                        aEMobile.clear();
//                        aEMail.clear();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
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

        String aStats = assessmentStats.getText();
        if (!aStats.equals("Not Started")) {
            // Display a message if the assessment has already been started
            Alert assessmentStartedAlert = new Alert(Alert.AlertType.INFORMATION);
            assessmentStartedAlert.setTitle("Warning");
            assessmentStartedAlert.setHeaderText(null);
            assessmentStartedAlert.setContentText("The assessment has either already completed or started and cannot be updated at the moment.");
            assessmentStartedAlert.showAndWait();
            return; // Exit the method if the assessment is not "Not Started"
        }

        initComboBox();

        csID.setDisable(false);
        cID.setDisable(false);
        fName.setDisable(false);
        lName.setDisable(false);
        mobileNum.setDisable(false);
        address.setDisable(false);
        mediCare.setDisable(false);
        asignedCSO.setDisable(false);
        cmbAssessment.setDisable(false);
        dateCreate.setDisable(false);
        cmbPriority.setDisable(false);
        myRecSave.setDisable(false);
        myRecCancel.setDisable(false);
        updateCase.setDisable(true);
        finaliseBtn.setDisable(true);
        reassignBtn.setDisable(true);
        casesTbl.setDisable(true);
    }

    @FXML
    private void myRecCancel(ActionEvent event) {

        csID.setDisable(true);
        cID.setDisable(true);
        fName.setDisable(true);
        lName.setDisable(true);
        mobileNum.setDisable(true);
        address.setDisable(true);
        mediCare.setDisable(true);
        asignedCSO.setDisable(true);
        cmbAssessment.setDisable(true);
        dateCreate.setDisable(true);
        cmbPriority.setDisable(true);
        myRecSave.setDisable(true);
        myRecCancel.setDisable(true);
        updateCase.setDisable(false);
        finaliseBtn.setDisable(false);
        reassignBtn.setDisable(false);
        casesTbl.setDisable(false);
    }

    private void updateUIForRole(String roleID, String accUser) {
        if ("1".equals(roleID)) {
            headerLbl.setText("Hi " + accUser);
            csoPane.setVisible(false);
            cmPane.setVisible(true);
        } else if ("2".equals(roleID)) {
            headerLbl.setText("Hi " + accUser);
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
        loadCases();
        headLbl.setText("Manage Cases");
        manageCasePane.setVisible(true);

        dashboardPane.setVisible(false);

    }

    @FXML
    private void createCaseLbl(MouseEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("createCase.fxml"));
            Parent root = loader.load();

            String createUser = userID;
            CreateCaseController createCasec = loader.getController();

            if (createCasec != null) {
                createCasec.setCreateUser(createUser);
            } else {
                System.out.println("Error: Controller not found!");
            }

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
    private void viewAssessment(MouseEvent event) {

        // Check if a record is selected in casetbl
        if (casesTbl.getSelectionModel().isEmpty()) {
            // If no record is selected, show an alert and return
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a case from the table first.");
            alert.showAndWait();
            return;
        }
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("assessmentForm.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("HCP - Care Planning");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setWidth(840);  // Set the width 
            stage.setHeight(645); // Set the height

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

    //manage client
    @FXML
    private void aUpdateClient(ActionEvent event) {

        // aClientID.setDisable(false);
        aMedicare.setDisable(false);
        aFname.setDisable(false);
        aLname.setDisable(false);
        aAddress.setDisable(false);
        aMobile.setDisable(false);
        aEmail.setDisable(false);
        aZip.setDisable(false);
        aBday.setDisable(false);
        aFundLevelCombo.setDisable(false);
//        aAssessmentDate.setDisable(false);
//        aCSO.setDisable(false);
        aOutcome.setDisable(false);
        aCMCombo.setDisable(false);
        aEContact.setDisable(false);
        aERelation.setDisable(false);
        aEMobile.setDisable(false);
        aEMail.setDisable(false);
        aSaveUpdate.setDisable(false);
        aCancelUpdate.setDisable(false);
        aUpdateClient.setDisable(true);
        aDeactClient.setDisable(true);
        tblClient.setDisable(true);
    }

    @FXML
    private void aCancelUpdate(ActionEvent event) {

        // aClientID.setDisable(true);
        aMedicare.setDisable(true);
        aFname.setDisable(true);
        aLname.setDisable(true);
        aAddress.setDisable(true);
        aMobile.setDisable(true);
        aEmail.setDisable(true);
        aZip.setDisable(true);
        aBday.setDisable(true);
        aFundLevelCombo.setDisable(true);
//        aAssessmentDate.setDisable(true);
//        aCSO.setDisable(true);
        aOutcome.setDisable(true);
        aCMCombo.setDisable(true);
        aEContact.setDisable(true);
        aERelation.setDisable(true);
        aEMobile.setDisable(true);
        aEMail.setDisable(true);
        aSaveUpdate.setDisable(true);
        aCancelUpdate.setDisable(true);
        aUpdateClient.setDisable(false);
        aDeactClient.setDisable(false);
        tblClient.setDisable(false);

    }

    @FXML
    private void aSaveUpdate(ActionEvent event) {

        // Retrieve data from text fields
        String clientIDValue = aClientID.getText();
        String fnameValue = aFname.getText();
        String lnameValue = aLname.getText();
        String medicareValue = aMedicare.getText();
        String addressValue = aAddress.getText();
        String mobileValue = aMobile.getText();
        String emailValue = aEmail.getText();
        LocalDate bdayValue = aBday.getValue();
        String ERContact = aEContact.getText();
        String ERRelation = aERelation.getText();
        String ERMobile = aEMobile.getText();
        String EREMail = aEMail.getText();

// Validate required fields
        if (fnameValue == null || fnameValue.isEmpty()
                || lnameValue == null || lnameValue.isEmpty()
                || medicareValue == null || medicareValue.isEmpty()
                || addressValue == null || addressValue.isEmpty()
                || mobileValue == null || mobileValue.isEmpty()
                || ERContact == null || ERContact.isEmpty()
                || ERRelation == null || ERRelation.isEmpty()
                || ERMobile == null || ERMobile.isEmpty()
                || mobileValue == null || mobileValue.isEmpty()
                || EREMail == null || EREMail.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields are required!");
            alert.showAndWait();
            return;
        }

        // Validate mobile field as numeric
        String numeric = "\\d+";
        if (!mobileValue.matches(numeric)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Contact should contain only numbers!");
            alert.showAndWait();
            return;
        }

        // Construct the SQL UPDATE query
        String updateClient = "UPDATE clientData SET fName = '" + fnameValue + "', lName = '" + lnameValue + "', clientMedicare = '" + medicareValue + "', clientAddress = '" + addressValue + "', clientMobile = '" + mobileValue + "',"
                + "emergencyContactPerson = '" + ERContact + "', emergencyContactNumber = '" + ERMobile + "', relationship = '" + ERRelation + "',clientBday = '" + bdayValue + "' WHERE clientID = '" + clientIDValue + "'";

////        String updateQuery = "UPDATE clientData SET clientID =  '" + clientIDValue + "', fName = '" + fnameValue + "', lName = '" + lnameValue + "', clientMedicare = '" + medicareValue + "', clientAddress = '" + addressValue + "', clientMobile = '" + mobileValue + "', clientEmail = '" + emailValue + "', clientBday = '" + bdayValue + "' WHERE clientID = '" + clientIDValue + "'";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {
            // Execute the update query
            int clientRowsUpdated = statement.executeUpdate(updateClient);

            // Check if the update was successful
            if (clientRowsUpdated > 0) {
                // Display success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Client record updated successfully.");
                successAlert.showAndWait();

                loadClients();
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        aMedicare.setDisable(true);
        aFname.setDisable(true);
        aLname.setDisable(true);
        aAddress.setDisable(true);
        aMobile.setDisable(true);
        aEmail.setDisable(true);
        aZip.setDisable(true);
        aBday.setDisable(true);
        aFundLevelCombo.setDisable(true);
//        aAssessmentDate.setDisable(true);
//        aCSO.setDisable(true);
        aOutcome.setDisable(true);
        aCMCombo.setDisable(true);
        aEContact.setDisable(true);
        aERelation.setDisable(true);
        aEMobile.setDisable(true);
        aEMail.setDisable(true);
        aSaveUpdate.setDisable(true);
        aCancelUpdate.setDisable(true);
        aUpdateClient.setDisable(false);
        aDeactClient.setDisable(false);
        tblClient.setDisable(false);
    }

//    @FXML
//private void logoutBtn(MouseEvent event) {
//    // Get the current stage (window) from the logout button
//    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//    if (stage != null) {
//        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
//        confirmation.setTitle("Logout");
//        confirmation.setHeaderText("Are you sure you want to logout?");
//        confirmation.setContentText("Click OK to confirm logout or Cancel to stay.");
//
//        // Show the confirmation dialog and wait for the user's response
//        confirmation.showAndWait().ifPresent(response -> {
//            if (response == javafx.scene.control.ButtonType.OK) {
//                try {
//        
//                    // Load the WelcomeHCP.fxml file
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeHCP.fxml"));
//                    Parent root = loader.load();
//
//                    // Set the new scene to the stage
//                    Scene scene = new Scene(root);
//                    stage.setScene(scene);
//                    
//                         stage.setWidth(680);  // Set the width 
//                                                    stage.setHeight(520); // Set the height
//                    stage.show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//}
//      private Stage log;
//
//    // Set the stage and handle the window close request
//    public void setStage(Stage log) {
//        this.log = log;
//        this.log.setOnCloseRequest(event -> {
//            event.consume();  // Prevent the default close action
//            xLogout(event);   // Call xLogout and pass the event
//        });
//    }
//    
//      // Handle logout and confirmation when "X" button is clicked
//    public void xLogout(WindowEvent event) {
//        // Create a confirmation dialog
//        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
//        confirmation.setTitle("Logout");
//        confirmation.setHeaderText("Are you sure you want to logout?");
//        confirmation.setContentText("Click OK to confirm logout or Cancel to stay.");
//
//        // Show the dialog and handle the user's response
//        confirmation.showAndWait().ifPresent(response -> {
//            if (response == ButtonType.OK) {
//                // Perform the logout action
//                try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); 
//                     Statement s = c.createStatement()) {
//
//                    // Update the active status of the user
//                    String sql = "UPDATE userAccounts SET stats = '1' WHERE userID = '" + userID + "'";
//                    int row = s.executeUpdate(sql);
//                    System.out.println("Updated " + row + " row(s).");
//
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//                // Load the WelcomeHCP.fxml file
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeHCP.fxml"));
//                    Parent root = loader.load();
//
//                    // Set the new scene to the stage
//                    Scene scene = new Scene(root);
//                    log.setScene(scene);
//
//                    log.setWidth(680);  // Set the width
//                    log.setHeight(520); // Set the height
//
//                    log.show();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                // If Cancel is clicked, don't close the window
//                event.consume();  // Prevent the window from closing
//            }
//        });
//    }
//    
    @FXML
    private void handleCloseButton(ActionEvent event) {
        // Stage stage = (Stage) closeButton.getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("You are about to close the window.");
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // stage.close();
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
                if (response == ButtonType.OK) {

                    try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement s = c.createStatement()) {
//String ID = userHolder.getText(); // Retrieve the current user ID from userHolder
                        // Prepare the SQL statement to update the active status of the user
                        String sql = "UPDATE userAccounts SET stats = '1' WHERE userID = '" + userID + "'";

                        // Execute the update statement
                        int row = s.executeUpdate(sql);
                        System.out.println("Updated " + row + " row(s).");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    // Load the WelcomeHCP.fxml file
                    try {
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
    private void clientAssessment(ActionEvent event) {
        // Check if a record is selected
        if (myCaseTbl.getSelectionModel().getSelectedItem() == null) {
            // Show an alert if no record is selected
            Alert alert = new Alert(AlertType.WARNING, "Please select a client to assess.", ButtonType.OK);
            alert.setHeaderText("No Selection");
            alert.showAndWait();
            return; // Exit the method if no record is selected
        }
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("assessmentForm.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Client Assessment");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setWidth(850);  // Set the width 
            stage.setHeight(650); // Set the height

            stage.showAndWait(); // Wait for the dialog to close before continuing with main window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addUserBtn(ActionEvent event) {
        showUnderDevelopmentAlert();
    }

    @FXML
    private void searchCase(ActionEvent event) {
        
          
           // Get the search term from the text field
        String ssSearch = aCaseSearch.getText().trim();

        clID.clear();
        myClientID.clear();
        myClientFname.clear();
        myClientLname.clear();
        myClientContact.clear();
        myClientAddress.clear();
        myClientMedicare.clear();
        bDay.setValue(LocalDate.now());

        if (!ssSearch.isEmpty()) {
            // Construct the SQL query with the search term

//            String searchCases = 
   String searchCases = "SELECT cc.caseID AS cID, CONCAT(cd.fName, ' ', cd.lName) AS fullName, cc.caseType AS caseType, assessmentStatus AS caseStats, CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO FROM clientCases cc JOIN clientData cd ON cc.clientID = cd.clientID JOIN userAccounts ua ON cc.userID = ua.userID "
           + "WHERE cc.caseID LIKE '%" + ssSearch + "%' "
              + "OR cd.lName LIKE '%" + ssSearch + "%' "
                    + "OR cd.fName LIKE '%" + ssSearch + "%' "
             + "OR cc.caseType LIKE '%" + ssSearch + "%' "  ;


            
            

            //       "SELECT caseID,clientID CONCAT(fname, ' ', lname) AS fullName, caseType FROM clientdata WHERE fName LIKE '%" + searchC + "%' OR lName LIKE '%" + searchC + "%' OR clientID LIKE '%" + searchC + "%' OR clientMedicare LIKE '%" + searchC + "%'";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {

                // Execute the query
                ResultSet resultSet = statement.executeQuery(searchCases);

                // Clear the TableView items
                casesTbl.getItems().clear();

                // Flag to check if any records were found
                boolean recordsFound = false;

                // Populate the TableView with search results
                while (resultSet.next()) {
                    int cssID= resultSet.getInt("cID");
                    String cssName = resultSet.getString("fullName");
                    String cssType = resultSet.getString("caseType");
                    String cssCSO = resultSet.getString("assignedCSO");
                    String cssStats = resultSet.getString("caseStats");

                    // Create a Clients object
                    Cases css = new Cases(cssID, cssName, cssType, cssCSO, cssStats);

                    // Add the Client object to the TableView
                    casesTbl.getItems().add(css);

                    recordsFound = true;
                }

                // If no records are found, show an alert and load all clients
                if (!recordsFound) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search Results");
                    alert.setHeaderText(null);
                    alert.setContentText("Can't find anything.");
                    alert.showAndWait();

                    // Optionally, call a method to load all clients
                    loadCases();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Display a message if the search query is empty
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a word to search.");
            alert.showAndWait();

            // Optionally, call a method to load all clients
            loadCases();
        }
        
        
        
        
        
        
        
        
        
        
        
    }

    @FXML
    private void searchBtn(ActionEvent event) {
        
       
        
    }

    @FXML
    private void assessmentLbl(MouseEvent event) {

        showUnderDevelopmentAlert();
    }

    @FXML
    private void searchCase(MouseEvent event) {

        showUnderDevelopmentAlert();
    }

//          @FXML
//    private void reassignBtn(ActionEvent event) {
//
//        showUnderDevelopmentAlert();
//    }
//            @FXML
//    private void finaliseBtn(ActionEvent event) {
//
//        showUnderDevelopmentAlert();
//    }
    @FXML
    private void aDeactClient(ActionEvent event) {

        Clients selectedClient = tblClient.getSelectionModel().getSelectedItem();

// Check if the selected client is null
        if (selectedClient == null) {
            // Display a message or handle the case when no client is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("No client is selected.");
            alert.showAndWait();
        } else {

            // Create a confirmation alert
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Deactivate Record");
            confirmationAlert.setHeaderText("Are you sure you want to deactivate this client?\n This action cannot be undone.");

            // Show the alert and wait for the response
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // If the user confirms, perform the deactivation

                    String selectedID = aClientID.getText();

// Construct the SQL UPDATE query
                    String deactClient = "UPDATE clientData SET isActive = '0' WHERE clientID = '" + selectedID + "'";

////        String updateQuery = "UPDATE clientData SET clientID =  '" + clientIDValue + "', fName = '" + fnameValue + "', lName = '" + lnameValue + "', clientMedicare = '" + medicareValue + "', clientAddress = '" + addressValue + "', clientMobile = '" + mobileValue + "', clientEmail = '" + emailValue + "', clientBday = '" + bdayValue + "' WHERE clientID = '" + clientIDValue + "'";
                    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {
                        // Execute the update query
                        int clientRowsUpdated = statement.executeUpdate(deactClient);

                        // Check if the update was successful
                        if (clientRowsUpdated > 0) {
                            // Display success alert
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setHeaderText(null);
                            successAlert.setContentText("Client deactivated successfully.");
                            successAlert.showAndWait();

                            loadClients();
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();

                    }

                } else {
                    // If the user cancels, do nothing
                    System.out.println("Deactivation cancelled.");
                }
            });

        }

    }

    @FXML
    private void clientSearch(ActionEvent event) {
        // Get the search term from the text field
        String searchC = clientSearches.getText().trim();

        aClientID.clear();
        aFname.clear();
        aLname.clear();
        aMedicare.clear();
        aAddress.clear();
        aZip.clear();
        aMobile.clear();
        aEmail.clear();
        aBday.setValue(LocalDate.now());
        aEContact.clear();
        aERelation.clear();
        aEMobile.clear();
        aEMail.clear();
        aCMCombo.getSelectionModel().selectFirst();
        aFundLevelCombo.getSelectionModel().selectFirst();

        if (!searchC.isEmpty()) {
            // Construct the SQL query with the search term
            String sqlQuery = "SELECT clientID, CONCAT(fname, ' ', lname) AS fullName FROM clientdata WHERE fName LIKE '%" + searchC + "%' OR lName LIKE '%" + searchC + "%' OR clientID LIKE '%" + searchC + "%' OR clientMedicare LIKE '%" + searchC + "%'";

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {

                // Execute the query
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                // Clear the TableView items
                tblClient.getItems().clear();

                // Flag to check if any records were found
                boolean recordsFound = false;

                // Populate the TableView with search results
                while (resultSet.next()) {
                    int cid = resultSet.getInt("clientID");
                    String cname = resultSet.getString("fullName");

                    // Create a Clients object
                    Clients client = new Clients(cid, cname);

                    // Add the Client object to the TableView
                    tblClient.getItems().add(client);

                    recordsFound = true;
                }

                // If no records are found, show an alert and load all clients
                if (!recordsFound) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search Results");
                    alert.setHeaderText(null);
                    alert.setContentText("Can't find anything.");
                    alert.showAndWait();

                    // Optionally, call a method to load all clients
                    loadClients();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Display a message if the search query is empty
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a word to search.");
            alert.showAndWait();

            // Optionally, call a method to load all clients
            loadClients();
        }
    }

    @FXML
    private void completeTaskBtn(ActionEvent event) {

        String basis = myClientID.getText().trim();

// Check if the selected client is null
        if (basis == null || basis.isEmpty()) {
            // Display a message or handle the case when no client is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("No case is selected.");
            alert.showAndWait();
        } else {

            clReason.setDisable(false);
            clConfirm.setDisable(false);
            clCancel.setDisable(false);

            clientAssessment.setDisable(true);
            completeTaskBtn.setDisable(true);

            clDate.setText(LocalDate.now().toString());

        }

    }

    @FXML
    private void clCancel(ActionEvent event) {
        clReason.setEditable(false);
        clReason.clear();
        clDate.clear();
        clConfirm.setDisable(true);
        clCancel.setDisable(true);

        clientAssessment.setDisable(false);
        completeTaskBtn.setDisable(false);

    }

    @FXML
    private void clConfirm(ActionEvent event) {

        String selectedCase = clID.getText();
        String closeReasin = clReason.getText();

// Construct the SQL UPDATE query
        String closeCase = "UPDATE clientcases SET assessmentStatus = 'Closed', closingReason = '" + closeReasin + "' WHERE caseID = '" + selectedCase + "'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {
            // Execute the update query
            int clientRowsUpdated = statement.executeUpdate(closeCase);

            // Check if the update was successful
            if (clientRowsUpdated > 0) {
                // Display success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Case Closed!.");
                successAlert.showAndWait();

                loadMyCases();
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        clReason.setEditable(false);
        clConfirm.setDisable(true);
        clReason.clear();
        clDate.clear();
        clCancel.setDisable(true);

        clientAssessment.setDisable(false);
        completeTaskBtn.setDisable(false);

    }

    @FXML
    private void sCase(ActionEvent event) {

        // Get the search term from the text field
        String searchCase = caseSearch.getText().trim();

        clID.clear();
        myClientID.clear();
        myClientFname.clear();
        myClientLname.clear();
        myClientContact.clear();
        myClientAddress.clear();
        myClientMedicare.clear();
        myClientBday.setValue(LocalDate.now());

        if (!searchCase.isEmpty()) {
            // Construct the SQL query with the search term

            String searchMyCase = "SELECT cc.caseID AS cID, "
                    + "cc.clientID AS clientID, "
                    + "CONCAT(cd.fName, ' ', cd.lName) AS fullName, "
                    + "cc.caseType AS caseType, "
                    + "cc.casePriority AS casePriority, "
                    + "CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO "
                    + "FROM clientCases cc "
                    + "JOIN clientData cd ON cc.clientID = cd.clientID "
                    + "JOIN userAccounts ua ON cc.userID = ua.userID "
                    + "WHERE cc.caseID LIKE '%" + searchCase + "%' "
                    + "OR cc.clientID LIKE '%" + searchCase + "%' "
                    + "OR cd.lName LIKE '%" + searchCase + "%' "
                    + "OR cd.fName LIKE '%" + searchCase + "%' "
                    + "OR cc.caseType LIKE '%" + searchCase + "%' "
                    + "OR cc.casePriority LIKE '%" + searchCase + "%'";

            //       "SELECT caseID,clientID CONCAT(fname, ' ', lname) AS fullName, caseType FROM clientdata WHERE fName LIKE '%" + searchC + "%' OR lName LIKE '%" + searchC + "%' OR clientID LIKE '%" + searchC + "%' OR clientMedicare LIKE '%" + searchC + "%'";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {

                // Execute the query
                ResultSet resultSet = statement.executeQuery(searchMyCase);

                // Clear the TableView items
                myCaseTbl.getItems().clear();

                // Flag to check if any records were found
                boolean recordsFound = false;

                // Populate the TableView with search results
                while (resultSet.next()) {
                    int caID = resultSet.getInt("cID");
                    int cliID = resultSet.getInt("clientID");
                    String clName = resultSet.getString("fullName");
                    String caType = resultSet.getString("caseType");
                    String caPriority = resultSet.getString("casePriority");

                    // Create a Clients object
                    MyCases cases = new MyCases(caID, cliID, clName, caType, caPriority);

                    // Add the Client object to the TableView
                    myCaseTbl.getItems().add(cases);

                    recordsFound = true;
                }

                // If no records are found, show an alert and load all clients
                if (!recordsFound) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Search Results");
                    alert.setHeaderText(null);
                    alert.setContentText("Can't find anything.");
                    alert.showAndWait();

                    // Optionally, call a method to load all clients
                    loadMyCases();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Display a message if the search query is empty
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Search");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a word to search.");
            alert.showAndWait();

            // Optionally, call a method to load all clients
            loadMyCases();
        }

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
