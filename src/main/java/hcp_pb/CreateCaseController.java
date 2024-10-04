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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class CreateCaseController implements Initializable {

    private String cmID;
    private String createUser;

    @FXML
    private Label cmName;
    @FXML
    private Label lblId;
    @FXML
    private TableView<CreateCase> tblCreateCase;
    @FXML
    private TableColumn<CreateCase, Integer> clientID;
    @FXML
    private TableColumn<CreateCase, String> clientName;
    @FXML
    private TableColumn<CreateCase, String> mediCare;
    @FXML
    private TableColumn<CreateCase, String> fundingLevel;
    @FXML
    private TextField caseNum;
    @FXML
    private TextField cID;
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private DatePicker bDay;
    @FXML
    private TextField medi_Care;
    @FXML
    private TextField mobileNum;
    @FXML
    private TextField address;
    @FXML
    private ComboBox<String> clientStatusCombo;
    @FXML
    private DatePicker dateCreated;
    @FXML
    private ComboBox<String> assessmentTypeCombo;
    @FXML
    private ComboBox<String> casePriorityCombo;
    @FXML
    private ComboBox<String> csoAssignmentCombo;
    @FXML
    private DatePicker completionCombo;
    @FXML
    private Button createCaseBtn;
    @FXML
    private Button confirmReg;
    
    @FXML
    private TextField searchKey;
    
    @FXML
    private Label createName;
    
    @FXML
    private Label createName1;
    
    
    
    @FXML private Button searchCase;
    @FXML
    private Button cancelCaseBtn;
    
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
        getTheMaxCid();
          initComboBox();
        searchChange();

        
        
        dateCreated.setValue(LocalDate.now());
        completionCombo.setValue(LocalDate.now());
      
        clientID.setCellValueFactory(new PropertyValueFactory<>("cID"));
        clientName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        mediCare.setCellValueFactory(new PropertyValueFactory<>("mediCare"));
        fundingLevel.setCellValueFactory(new PropertyValueFactory<>("fundLevel"));
        loadDataFromDatabase();

        // Add listener to the table for row selection
        tblCreateCase.setOnMouseClicked(event -> {
            try {
                handleRowSelect(event);
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

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

    private void getTheMaxCid() {

        String query = "SELECT caseID FROM clientcases ORDER BY caseID DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            //hidden placeholder for next userid
            if (rs.next()) {
                int lastUser = rs.getInt("caseID");
                caseNum.setText(String.valueOf(lastUser + 1));
            } else {
                caseNum.setText("10000001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
     @FXML
    private void searchCase(ActionEvent event) {
        
          
           // Get the search term from the text field
        String ssSearch = searchKey.getText().trim();

        cID.clear();
        fName.clear();
        lName.clear();
        medi_Care.clear();
        mobileNum.clear();
        address.clear();
    
        bDay.setValue(LocalDate.now());

        if (!ssSearch.isEmpty()) {
            // Construct the SQL query with the search term



   String searchClient = "SELECT clientID, CONCAT(fName, ' ', lName) AS fullName, clientMedicare, levelID FROM clientdata "
           + "WHERE clientID LIKE '%" + ssSearch + "%' "
           + "OR lName LIKE '%" + ssSearch + "%' "
              + "OR fName LIKE '%" + ssSearch + "%' "
            + "OR clientMedicare LIKE '%" + ssSearch + "%' "  ;
           
                     
            
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {

                // Execute the query
                ResultSet resultSet = statement.executeQuery(searchClient);

                // Clear the TableView items
                tblCreateCase.getItems().clear();

                // Flag to check if any records were found
                boolean recordsFound = false;

                // Populate the TableView with search results
                while (resultSet.next()) {
                    int cID= resultSet.getInt("clientID");
                    String fullName = resultSet.getString("fullName");
                    String fundLevel = resultSet.getString("levelID");
                    String mediCare = resultSet.getString("clientMedicare");
                  

                    // Create a Clients object
                    CreateCase css = new CreateCase(cID, fullName, fundLevel, mediCare);

                    // Add the Client object to the TableView
                    tblCreateCase.getItems().add(css);

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
                          loadDataFromDatabase();
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
                  loadDataFromDatabase();
        }
    }
        
        
        
        
        
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @FXML
    private void cancelCaseBtn() {
        // Get the current stage (window)
//        Stage stage = (Stage) cancelCaseBtn.getScene().getWindow();
//        // Close the stage
//        stage.close();
        createName1.setVisible(false);
 createName.setText("Create a new Case Profile for a client.");

        tblCreateCase.setDisable(false);

        // Enable ComboBoxes and set to the first selection
        clientStatusCombo.setDisable(true);
        clientStatusCombo.getSelectionModel().selectFirst();

        assessmentTypeCombo.setDisable(true);
        assessmentTypeCombo.getSelectionModel().selectFirst();

        casePriorityCombo.setDisable(true);
        casePriorityCombo.getSelectionModel().selectFirst();

        csoAssignmentCombo.setDisable(true);
        csoAssignmentCombo.getSelectionModel().selectFirst();

        // Enable DatePickers and set to today's date
        LocalDate today = LocalDate.now();

        dateCreated.setDisable(true);
        dateCreated.setValue(today);

        completionCombo.setDisable(true);
        completionCombo.setValue(today);

//    tblCreateCase.setDisable(true);
        confirmReg.setDisable(true);
        cancelCaseBtn.setDisable(true);
        createCaseBtn.setDisable(false);

    }

//                @FXML
//    private void clearCaseBtn() {
//  
//             //   caseNum.clear();
//        cID.clear();
//        fName.clear();
//        lName.clear();
//        medi_Care.clear();
//        mobileNum.clear();
//        address.clear();
//
//        // Set ComboBox to the first selection
//        clientStatusCombo.getSelectionModel().selectFirst();
//        assessmentTypeCombo.getSelectionModel().selectFirst();
//        casePriorityCombo.getSelectionModel().selectFirst();
//        csoAssignmentCombo.getSelectionModel().selectFirst();
//
//        // Set DatePicker to today's date
//        LocalDate today = LocalDate.now();
//        bDay.setValue(today);
//        dateCreated.setValue(today);
//        completionCombo.setValue(today);
//        
//        
//    }
//        
 
    
@FXML
private void createCaseBtn() {
    String clID = cID.getText(); // Get the client ID from the text field
    String clN = fName.getText() +" " + lName.getText();
        
//        createName
    // Check if TableView has a selected item before proceeding
    if (tblCreateCase.getSelectionModel().getSelectedItem() != null) {
        // Check for active cases for the given client ID
        if (checkForActiveCase(clID)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Active Case Found");
            alert.setHeaderText(null);
            alert.setContentText("There is still an active case for this client.");
            alert.showAndWait();
        } else {
            // Proceed with enabling components and setting values
            tblCreateCase.setDisable(true);

            enableComboBoxes();
            setDatePickersToToday();

            confirmReg.setDisable(false);
            cancelCaseBtn.setDisable(false);
            createCaseBtn.setDisable(true);
       
           createName.setText("Creating a new case for client: ");
           createName1.setVisible(true);
           createName1.setText(clN);
        }
    } else {
        // Notify user if no item is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText(null);
        alert.setContentText("A client must be selected.");
        alert.showAndWait();
    }
}



// Method to check if there's an active case
private boolean checkForActiveCase(String clID) {
    boolean hasActiveCase = false;
    String query = "SELECT assessmentStatus FROM clientcases WHERE clientID = ?";

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = connection.prepareStatement(query)) {
        
        // Set the clientID parameter
        pstmt.setString(1, clID);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String status = rs.getString("assessmentStatus");
            if (!"Closed".equalsIgnoreCase(status)) {
                hasActiveCase = true; // Active case exists if status is not "Close"
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions appropriately
    }

    return hasActiveCase;
}


// Helper method to enable ComboBoxes and set the first selection
private void enableComboBoxes() {
    clientStatusCombo.setDisable(false);
    clientStatusCombo.getSelectionModel().selectFirst();

    assessmentTypeCombo.setDisable(false);
    assessmentTypeCombo.getSelectionModel().selectFirst();

    casePriorityCombo.setDisable(false);
    casePriorityCombo.getSelectionModel().selectFirst();

    csoAssignmentCombo.setDisable(false);
    csoAssignmentCombo.getSelectionModel().selectFirst();
}

// Helper method to set DatePickers to today's date
private void setDatePickersToToday() {
    LocalDate today = LocalDate.now();
    
    dateCreated.setDisable(false);
    dateCreated.setValue(today);

    completionCombo.setDisable(false);
    completionCombo.setValue(today);
}

    
    
    
    
    
    
    
    
    
    
//    

    
        private void searchChange() {
        // Add a listener to usersID to call loadUserData when its value changes
        searchKey.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null || newValue.trim().isEmpty()) {
                    loadDataFromDatabase();
                }
            }
        });
    }
    
    
    public void loadDataFromDatabase() {
        // SQL query
        String query = "SELECT clientID, CONCAT(fName, ' ', lName) AS fullName, clientMedicare, levelID FROM clientdata";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing data
            tblCreateCase.getItems().clear();

            // Process the result set and populate the TableView
            while (resultSet.next()) {
                int cID = resultSet.getInt("clientID");
                String fullName = resultSet.getString("fullName");
                String mediCare = resultSet.getString("clientMedicare");
                String fundLevel = resultSet.getString("levelID");

                // Create a CreateCase object
                CreateCase createCase = new CreateCase(cID, fullName, fundLevel, mediCare);

                // Add the CreateCase object to the TableView
                tblCreateCase.getItems().add(createCase);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
// 

    private void loadClientDetails(int clientID) {

        String query = "SELECT * FROM clientData WHERE clientID = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, clientID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cID.setText(resultSet.getString("clientID"));
                    fName.setText(resultSet.getString("fName"));
                    lName.setText(resultSet.getString("lName"));
                    medi_Care.setText(resultSet.getString("clientMedicare"));
                    address.setText(resultSet.getString("clientAddress"));
                    mobileNum.setText(resultSet.getString("clientMobile"));
                    // aEmail.setText(resultSet.getString("clientEmail"));

                    String birthdayStr = resultSet.getString("clientBday");
                    if (birthdayStr != null && !birthdayStr.isEmpty()) {
                        LocalDate birthday = LocalDate.parse(birthdayStr);
                        bDay.setValue(birthday);

//           aEMail.getText();
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleRowSelect(MouseEvent event) throws SQLException {
        if (event == null || event.getClickCount() == 1) { // Single-click detection
            CreateCase sc = tblCreateCase.getSelectionModel().getSelectedItem();
            if (sc != null) {
                loadClientDetails(sc.getCID());
            }
        }
    }

//
//   String caseNumValue = caseNum.getText();
//    String cIDValue = cID.getText();
//    String fNameValue = fName.getText();
//    String lNameValue = lName.getText();
//    String eMailValue = eMail.getText();
//    String mobileNumValue = mobileNum.getText();
//    String addressValue = address.getText();
    private void initComboBox() {

        // Initialize position combo box
        clientStatusCombo.setItems(FXCollections.observableArrayList("New Client", "Old Client"));
        clientStatusCombo.getSelectionModel().selectFirst();

        // Initialize role combo box
        assessmentTypeCombo.setItems(FXCollections.observableArrayList("New Assessment", "Re-Assessment", "Risk Assessment", "Welfare Assessment"));
        assessmentTypeCombo.getSelectionModel().selectFirst();

        // Initialize role combo box
        casePriorityCombo.setItems(FXCollections.observableArrayList("High", "Medium", "Low"));
        casePriorityCombo.getSelectionModel().selectFirst();

        // Query to fetch names of users with roleID = '2'
        String query = "SELECT userID, CONCAT(fName, ' ', lName) AS fullName FROM userAccounts WHERE roleID = '2'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing items in ComboBox
            csoAssignmentCombo.getItems().clear();

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
            csoAssignmentCombo.setItems(observableNamesList);
            csoAssignmentCombo.getSelectionModel().selectFirst(); // Optional: Select the first item

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
      //  System.out.println("Create User received: " + createUser);  // For debugging purposes
    }

    @FXML
    private void confirmReg(ActionEvent event) throws IOException {

        // Retrieve text from TextFields
        String caseNumberText = caseNum.getText();
        String clientIDText = cID.getText();
//    String firstNameText = fName.getText();
//    String lastNameText = lName.getText();
//    String medicareIDText = medi_Care.getText();
//    String mobileNumberText = mobileNum.getText();
//    String addressText = address.getText();

        // Retrieve values from ComboBoxes
        String clientStatus = clientStatusCombo.getValue();
        String assessmentType = assessmentTypeCombo.getValue();
        String casePriority = casePriorityCombo.getValue();
        String csoAssignment = csoAssignmentCombo.getValue();

        // Retrieve values from DatePickers
//    LocalDate bDayValue = bDay.getValue();
        LocalDate dateCreatedValue = dateCreated.getValue();
        LocalDate completionDateValue = completionCombo.getValue();

        String csoAssignmentValue = csoAssignmentCombo.getValue(); // 
        String csoID = csoAssignmentValue.substring(0, 5); // 
        String csoName = csoAssignmentValue.substring(8);

//        //validate input (call validateinput method)
//        if (!validateInput(firstName, lastName, email, mobile, addr, mediCare, eContactName, eRelation, eMob, email)) {
//            return;
//        }
        //insert to db
        String regClient = "INSERT INTO clientcases (caseID, caseType, casePriority, caseAssignment, caseDate, clientType, clientID,userID, assessmentStatus, createUser ) "
                + "VALUES ('" + caseNumberText + "','" + assessmentType + "', '" + casePriority + "', "
                + "'" + csoName + "', '" + dateCreatedValue + "', '" + clientStatus + "', "
                + "'" + clientIDText + "','" + csoID + "', 'Open',  '" + createUser + "' )"; // temporary user id - for presentation purposes

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Execute the query
            int rowsAffected = stmt.executeUpdate(regClient);

            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("A New Case Successfully Created");
                alert.showAndWait();

                Stage stage = (Stage) confirmReg.getScene().getWindow();
                stage.close();

                
                	
		          logAudit("A new case for client " + clientIDText + " has been created", createUser);
  
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



    tblCreateCase.setDisable(false);
            confirmReg.setDisable(true);
            cancelCaseBtn.setDisable(true);
            createCaseBtn.setDisable(false);
            createName1.setVisible(false);
             createName.setText("Create a new Case Profile for a client.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to save data to the database");
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
