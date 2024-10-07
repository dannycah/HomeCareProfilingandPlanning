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
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class AdminDashboardController implements Initializable, CloseCallBack {

    @FXML
    private Hyperlink refresh;
    @FXML
    private TableView<employeeList> empTbl;
    @FXML
    private TableColumn<employeeList, Integer> employeeID;
    @FXML
    private TableColumn<employeeList, String> fullName;
    @FXML
    private TableColumn<employeeList, String> status;
    @FXML
    private TableColumn<employeeList, String> startD;
    @FXML
    private TableColumn<employeeList, String> endD;
    @FXML
    private TableView<logsEntry> tblLogs;
    @FXML
    private TableColumn<logsEntry, Integer> logID;
    @FXML
    private TableColumn<logsEntry, String> tstmp;
    @FXML
    private TableColumn<logsEntry, String> logDets;
    @FXML
    private Button chooseFile;
    @FXML
    private Button upload;
    @FXML
    private TextField filePathTextField;
    private File selectedFile;
    @FXML
    private Button bulkUploadBtn;
    @FXML
    private Button addEmpBtn;
    @FXML
    private Button retireEmpBtn;
    @FXML
    private Button cancelBulkBtn;
    @FXML
    private Button saveEmpBtn;
    @FXML
    private Button cancelEmpBtn;
    @FXML
    private TextField emp_id;
    @FXML
    private TextField emp_name;
    @FXML
    private DatePicker emp_Start;
    @FXML
    private Pane employeeRecordsPane;
    @FXML
    private Pane manageUsersPane;
    @FXML
    private Button userSearch;
    @FXML
    private TextField searchKey;
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
    private Label userHolder;
    @FXML
    private Label uploadLbl;
    @FXML
    private Label recordsLbl;
    @FXML
    private Label serviceRatesLbl;
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
    private TableColumn<UserManager, String> Stats;
    @FXML
    private TextField usersID;
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
    @FXML
    private Label totalUsers;
    @FXML
    private Label numOfLockouts;
    @FXML
    private Label numOfActiveUSers;
    @FXML
    private Hyperlink helpLink;

    private String userID;
    private String roleID;
    private String accUser;

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
        searchChange();
        loadEmpDataFromDatabase();
        loadUserData();
        loadLogs();
        loadTotalUsers();
        loadNumOfLockouts();
        loadNumOfActiveUsers();

        logID.setCellValueFactory(new PropertyValueFactory<>("logID"));
        tstmp.setCellValueFactory(new PropertyValueFactory<>("tstmp"));
        logDets.setCellValueFactory(new PropertyValueFactory<>("logDets"));

        uID.setCellValueFactory(new PropertyValueFactory<>("uID"));
        empName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        Stats.setCellValueFactory(new PropertyValueFactory<>("ustats"));

        emp_Start.setValue(LocalDate.now());
        roleCombo.setItems(FXCollections.observableArrayList("System Administrator", "Case Manager", "Care Service Officer"));

        employeeID.setCellValueFactory(new PropertyValueFactory<>("eid"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        startD.setCellValueFactory(new PropertyValueFactory<>("startD"));
        endD.setCellValueFactory(new PropertyValueFactory<>("endD"));

        filePathTextField.textProperty().addListener((observable, oldValue, newValue) -> {

            upload.setDisable(false);
        });

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

        //set the status flag to 1 when the x button wasaccidentally clicked
        Platform.runLater(() -> {
            Scene scene = numOfActiveUSers.getScene();
            if (scene != null) {
                Stage stage = (Stage) scene.getWindow();
                if (stage != null) {
                    stage.setOnCloseRequest(event -> {

                        Alert CloseWindow = new Alert(Alert.AlertType.INFORMATION);
                        CloseWindow.setTitle("Closing the Program");
                        CloseWindow.setHeaderText(null);
                        CloseWindow.setContentText("You are about to turn off the system.");
                        CloseWindow.showAndWait();

                        logAudit(accUser + " have successfully logged-in", userID);

                        try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement s = c.createStatement()) {

                            // Prepare the SQL statement to update the active status of the user
                            String sql = "UPDATE userAccounts SET stats = '1' WHERE userID = '" + userID + "'";

                            // Execute the update statement
                            int row = s.executeUpdate(sql);

                        } catch (SQLException ex) {

                        }

                        System.out.println("Logging out...");

                    });
                }
            }
        });

    }

    private void loadDatabaseConfig() {
        try {
            File configFile = new File(DB_CONFIG_FILE);
            if (!configFile.exists()) {

            }

            // Read the database configuration
            try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
                DB_URL = reader.readLine(); // Read the first line (URL)
                DB_USER = reader.readLine(); // Read the second line (username)
                DB_PASSWORD = reader.readLine(); // Read the third line (password)
            }
        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error reading the database configuration.");
//               
            System.exit(1); // Exit if an error occurs while reading the file
        }
    }

    @FXML
    private void helpLink(MouseEvent event) {
        try {
            // Path to your PDF file
            File pdfFile = new File("HCCPP-User-Manual.pdf");

            // Check if the file exists
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR, "Manual file not found.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found, no associated application)
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred while trying to open the manual.");
            alert.showAndWait();
        }
    }

    public void loadLogs() {
        // SQL query
        String query = "SELECT logID, logDateTime, logDetails FROM auditTrail";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing data
            tblLogs.getItems().clear();

            // Process the result set and populate the TableView
            while (resultSet.next()) {
                int logID = resultSet.getInt("logID");
                String tstmp = resultSet.getString("logDateTime");
                String logDets = resultSet.getString("logDetails");

                // Create a CreateCase object
                logsEntry logs = new logsEntry(logID, tstmp, logDets);

                // Add the CreateCase object to the TableView
                tblLogs.getItems().add(logs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUser(String userID) throws SQLException {
        this.userID = userID;
        System.out.println("Setting User Details: userID=" + userID);   //to test
        userHolder.setText(userID);

        String query = "SELECT userID, roleID, CONCAT(fName, ' ', lName) AS fullName FROM userAccounts WHERE userID = ?";

        new Thread(() -> {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userID); // Set the parameter for the userID

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String roleID = resultSet.getString("roleID");
                        accUser = resultSet.getString("fullName");
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

            logAudit(accUser + " have successfully logged-in", userID);

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

    private void updateUIForRole(String roleID, String accUser) {

        headerLbl.setText("Hi " + accUser);

    }

    @FXML
    private void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );

        Stage stage = (Stage) chooseFile.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            filePathTextField.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void upload(ActionEvent event) {
        if (selectedFile != null) {
            showEmp(selectedFile);
        } else {
            System.out.println("No file selected.");
        }
    }

    private void showEmp(File file) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("showEmpData.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Employee Data");
            dialogStage.setScene(new Scene(root));

            ShowEmpDataController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCloseCallback(this); // Set the callback

            String fileContent = readFile(file);
            controller.setFileContent(fileContent);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length >= 3) {
                    content.append(columns[0]).append(", ")
                            .append(columns[1]).append(", ")
                            .append(columns[2]).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @FXML
    private void bulkUploadBtn(ActionEvent event) {
        bulkUploadBtn.setDisable(true);
        addEmpBtn.setDisable(true);
        retireEmpBtn.setDisable(true);
        chooseFile.setDisable(false);
        cancelBulkBtn.setDisable(false);
        chooseFile.requestFocus();

    }

    @FXML
    private void cancelBulkBtn(ActionEvent event) {
        bulkUploadBtn.setDisable(false);
        addEmpBtn.setDisable(false);
        retireEmpBtn.setDisable(false);
        chooseFile.setDisable(true);
        cancelBulkBtn.setDisable(true);
        filePathTextField.setText("");
        upload.setDisable(true);

    }

    @FXML
    private void addEmpBtn(ActionEvent event) {
        emp_id.setDisable(false);
        emp_name.setDisable(false);
        emp_Start.setDisable(false);
        bulkUploadBtn.setDisable(true);
        addEmpBtn.setDisable(true);
        retireEmpBtn.setDisable(true);
        saveEmpBtn.setDisable(false);
        cancelEmpBtn.setDisable(false);

    }

    @FXML
    private void cancelEmpBtn(ActionEvent event) {
        emp_id.setDisable(true);
        emp_name.setDisable(true);
        emp_Start.setDisable(true);
        bulkUploadBtn.setDisable(false);
        addEmpBtn.setDisable(false);
        retireEmpBtn.setDisable(false);
        emp_Start.setValue(LocalDate.now());
        emp_id.setText("");
        emp_name.setText("");
        saveEmpBtn.setDisable(true);
        cancelEmpBtn.setDisable(true);

    }

    @FXML
    private void saveEmpBtn(ActionEvent event) {
        String EID = emp_id.getText();
        String EName = emp_name.getText();
        String EStart = emp_Start.getValue() != null ? emp_Start.getValue().toString() : "";

        String empList = "INSERT INTO employeeList (employeeID, fullName, startDate) "
                + "VALUES ('" + EID + "', '" + EName + "', '" + EStart + "')";

        String checkQuery = "SELECT COUNT(*) FROM employeeList WHERE employeeID = '" + EID + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Check if employeeID exists
            ResultSet rs = stmt.executeQuery(checkQuery);
            rs.next();  // Move to the first row
            loadEmpDataFromDatabase();

            if (rs.getInt(1) > 0) {
                // Employee ID exists, show a prompt
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Employee ID already exists.");
                alert.showAndWait();
            } else {
                // Employee ID doesn't exist, proceed with insert
                int rowsAffected = stmt.executeUpdate(empList);

                if (rowsAffected > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee Record Created");
                    alert.showAndWait();
                    loadEmpDataFromDatabase();
                }

                emp_id.setDisable(true);
                emp_name.setDisable(true);
                emp_Start.setDisable(true);
                bulkUploadBtn.setDisable(false);
                addEmpBtn.setDisable(false);
                retireEmpBtn.setDisable(false);
                emp_Start.setValue(LocalDate.now());
                emp_id.setText("");
                emp_name.setText("");
                saveEmpBtn.setDisable(true);
                cancelEmpBtn.setDisable(true);
                logAudit(EID + " has been addedd to the employee list", userID);

            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void retireEmpBtn(ActionEvent event) {
        // Check if TableView has a selected item before proceeding
        if (empTbl.getSelectionModel().getSelectedItem() != null) {

            // Get the selected employee's ID
            employeeList emp_list = empTbl.getSelectionModel().getSelectedItem();
            int employeeID = emp_list.getEid(); // Adjust this to match how you get employee ID from selected item

            // Check current status of the employee
            String checkStatusQuery = "SELECT activeFlag FROM employeeList WHERE employeeID = ?";

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement checkStatusStmt = connection.prepareStatement(checkStatusQuery)) {

                // Set parameters and execute query
                checkStatusStmt.setInt(1, employeeID);
                ResultSet resultSet = checkStatusStmt.executeQuery();

                if (resultSet.next()) {
                    String activeFlag = resultSet.getString("activeFlag");

                    if ("0".equals(activeFlag)) {
                        // Employee is already inactive
                        Alert alreadyInactiveAlert = new Alert(Alert.AlertType.INFORMATION);
                        alreadyInactiveAlert.setTitle("Already Inactive");
                        alreadyInactiveAlert.setHeaderText(null);
                        alreadyInactiveAlert.setContentText("This employee is already inactive.");
                        alreadyInactiveAlert.showAndWait();
                        return; // Exit method

                    }
                }

                // If employee is not inactive, proceed with retiring the employee
                LocalDate localDate = LocalDate.now(); // Current date
                String endDate = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // Show confirmation dialog
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setTitle("Retire Employee");
                confirmAlert.setHeaderText(null);
                confirmAlert.setContentText("Are you sure you want to retire this employee?");

                Optional<ButtonType> result = confirmAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // User clicked OK, proceed with retiring the employee
                    String retireEmp = "UPDATE employeeList SET endDate = ?, activeFlag = '0' WHERE employeeID = ?";

                    try (PreparedStatement preparedStatement = connection.prepareStatement(retireEmp)) {
                        // Set parameters
                        preparedStatement.setString(1, endDate);
                        preparedStatement.setInt(2, employeeID);

                        // Execute the update query
                        int rowsUpdated = preparedStatement.executeUpdate();

                        if (rowsUpdated > 0) {
                            // Display success alert
                            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                            successAlert.setTitle("Success");
                            successAlert.setHeaderText(null);
                            successAlert.setContentText("Employee successfully retired.");
                            successAlert.showAndWait();

                            logAudit("Employee " + employeeID + " has been retired from the system", userID);
                        }

                        loadEmpDataFromDatabase();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            // Notify user to select an item first
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select an employee record.");
            alert.showAndWait();
        }
    }

    private boolean validateInput(String e_id, String e_full) {
        String numeric = "\\d+";

        if (e_id.isEmpty() || e_full.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required!");
            return false;
        }

        // Check if the clien is at least 65 years old
        LocalDate today = LocalDate.now();
        Period age = Period.between(emp_Start.getValue(), today);

        if (age.getYears() < 18) {
            showAlert(Alert.AlertType.ERROR, "Error", "Employee must be at least 18 years old.");
            return false;
        }

        if (!e_id.matches(numeric)) {

            showAlert(Alert.AlertType.ERROR, "Error", "Employee ID must contain numbers only.");
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

    private void loadEmpDataFromDatabase() {
        String Empquery = "SELECT * FROM employeeList";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(Empquery)) {

            empTbl.getItems().clear();

            while (resultSet.next()) {
                int eid = resultSet.getInt("employeeID");
                String fullName = resultSet.getString("fullName");
                String status = resultSet.getString("activeFlag");
                String startD = resultSet.getString("startDate");
                String endD = resultSet.getString("endDate");
                if (endD == null || endD.isEmpty()) {
                    endD = "Employed";
                }
                String empFlag = "1".equals(status) ? "Active" : "Inactive";
                employeeList emplist = new employeeList(eid, fullName, empFlag, startD, endD);
                empTbl.getItems().add(emplist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadUserData() {
        // SQL query
        String query = "SELECT userID, CONCAT(fName, ' ', lName) AS fullName, isActive FROM userAccounts ORDER BY isActive DESC";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing data
            tblUser.getItems().clear();

            // Process the result set and populate the TableView
            while (resultSet.next()) {
                int uID = resultSet.getInt("userID");
                String empName = resultSet.getString("fullName");
                int uStatus = resultSet.getInt("isActive");
                String userStat = (uStatus == 1) ? "Active" : "Inactive";
                // Create a CreateCase object
                UserManager users = new UserManager(uID, empName, userStat);

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


                    usersID.setText(resultSet.getString("userID"));
                    empID.setText(resultSet.getString("employeeID"));
                    Fname.setText(resultSet.getString("fName"));
                    Lname.setText(resultSet.getString("lName"));
                    Address.setText(resultSet.getString("userAddress"));
                    Mobile.setText(resultSet.getString("userMobile"));
                    Email.setText(resultSet.getString("userEmail"));
                    Zip.setText(resultSet.getString("userZip"));
                    usnField.setText(resultSet.getString("username"));
                    Bday.setValue(resultSet.getDate("bDay").toLocalDate());


                    String roleID = resultSet.getString("roleID");


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
        
        String loggedInUserId = userID;  // or retrieve this value appropriately

        if (tblUser.getSelectionModel().getSelectedItem() != null) {
            String selectedUserId = usersID.getText();  // Adjust based on your data structure

            if (selectedUserId.equals(loggedInUserId)) {

                // Enable all TextField components
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
                roleCombo.setDisable(true);

                // Enable Button components
                resetPassBtn.setDisable(false);
                updateUser.setDisable(true);
                deactUser.setDisable(true);
                cancelUpdate.setDisable(false);
                saveUpdate.setDisable(false);
                tblUser.setDisable(true);

            } else {

                // Enable all TextField components
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

        } else {
            // Notify user that no item is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("A user must be selected.");
            alert.showAndWait();
        }
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
            String uID = usersID.getText();
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
                roleID = 3;
            } else if ("Case Manager".equals(role)) {
                roleID = 1;
            } else if ("Care Service Officer".equals(role)) {
                roleID = 2;
            } else {
                roleID = -1; // to handle the case where the role is not recognized
            }

            // Validate required fields
            if (fnameText == null || fnameText.isEmpty()
                    || lnameText == null || lnameText.isEmpty()
                    || addressText == null || addressText.isEmpty()
                    || mobileText == null || mobileText.isEmpty()
                    || emailText == null || emailText.isEmpty()
                    || zipText == null || zipText.isEmpty()
                    || usernameText == null || usernameText.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("All fields are required!");
                alert.showAndWait();
                return;
            }

            // Validate mobile field as numeric
            String numeric = "\\d+";
            if (!mobileText.matches(numeric)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Contact should contain only numbers!");
                alert.showAndWait();
                return;
            }

            if (!mobileText.matches("\\d{10}")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Mobile number must contain 10 digits");
                alert.showAndWait();
                return;

            }

            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                    + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

            if (emailText == null || !emailText.matches(emailRegex)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid email address.");
                alert.showAndWait();
                return;

            }

            String usernameRegex = "^[a-zA-Z0-9]{6,16}$";

            if (!usernameText.matches(usernameRegex)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Username must be 6-16 characters long and can only contain letters, and numbers.");
                alert.showAndWait();
                return;

            }

            if (!zipText.matches("\\d{4}")) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Zip code must contain 4 digits.");
                alert.showAndWait();
                return;

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

                    logAudit("User " + empIDText + " has been updated", userID);

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
    private void refresh(MouseEvent event) {
        loadLogs();
        loadNumOfActiveUsers();
    }

    @FXML
    private void logoutBtn(MouseEvent event) {
        // Get the current stage (window) from the logout button
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (stage != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Logout");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Are you sure you want to logout?");

            // Show the confirmation dialog and wait for the user's response
            confirmation.showAndWait().ifPresent(response -> {
                if (response == javafx.scene.control.ButtonType.OK) {

                    try {

                        try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement s = c.createStatement()) {

                            // Prepare the SQL statement to update the active status of the user
                            String sql = "UPDATE userAccounts SET stats = '1' WHERE userID = '" + userID + "'";

                            // Execute the update statement
                            int row = s.executeUpdate(sql);

                            logAudit(accUser + " have successfully logged-out", userID);

                        } catch (SQLException ex) {

                        }

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
        if (tblUser.getSelectionModel().getSelectedItem() != null) {
            // Get the selected user and userID
            UserManager selectedUser = tblUser.getSelectionModel().getSelectedItem();
            int selectedUserID = selectedUser.getUID(); // Assuming this method gets the userID of the selected user

            int currentUserID = Integer.parseInt(userHolder.getText().trim()); // Assuming userHolder holds the current user's ID

            // Confirm password reset action
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Reset Password");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Are you sure you want to reset this password?");

            // Show the confirmation alert and wait for user response
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // If the user confirms, generate a 10-digit random alphanumeric token
                String newToken = generateRandomToken(10);

                // Show the new token in a dialog
                Alert tokenAlert = new Alert(Alert.AlertType.INFORMATION);
                tokenAlert.setTitle("Reset Token");
                tokenAlert.setHeaderText(null);
                tokenAlert.setContentText("Password reset one-time token: \n\n" + newToken);
                tokenAlert.showAndWait();

                // TODO: Implement logic to save the new token for the selected user
                String updateQuery = "UPDATE userAccounts SET resetToken = ?, resetFlag = ? WHERE userID = ?";

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

                    pstmt.setString(1, newToken); // Set the resetToken
                    pstmt.setString(2, "1"); // Set the resetFlag to '1'
                    pstmt.setInt(3, selectedUserID); // Set the userID

                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println("Reset details updated successfully for user ID: " + userID);

                        Fname.setDisable(true);
                        Lname.setDisable(true);
                        Address.setDisable(true);
                        Mobile.setDisable(true);
                        Email.setDisable(true);
                        Zip.setDisable(true);
                        usnField.setDisable(true);
                        Bday.setDisable(true);
                        roleCombo.setDisable(true);
                        resetPassBtn.setDisable(true);
                        updateUser.setDisable(false);
                        deactUser.setDisable(false);
                        cancelUpdate.setDisable(true);
                        saveUpdate.setDisable(true);
                       tblUser.setDisable(false);

                    } else {
                        System.out.println("No user found with ID: " + userID);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle database connection or query error
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Database Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Error updating reset details: " + e.getMessage());
                    errorAlert.showAndWait();
                }
            } else {

            }
        } else {
            // Display warning alert if no user is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("A user must be selected.");
            alert.showAndWait();
        }
    }

// Utility method to generate a random alphanumeric token
    private String generateRandomToken(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            token.append(chars.charAt(random.nextInt(chars.length())));
        }
        return token.toString();
    }

    @FXML
    private void deactUser(ActionEvent event) {
        // Check if a user is selected in the TableView
        if (tblUser.getSelectionModel().getSelectedItem() != null) {
            // Get the selected user and the userID to be deactivated
            UserManager selectedUser = tblUser.getSelectionModel().getSelectedItem();
            int selectedUserID = selectedUser.getUID(); // Assuming this method gets the userID of the selected user
            String currentStat = selectedUser.getUstats(); // Assuming this method gets the status of the selected user
            int currentUserID = Integer.parseInt(userHolder.getText().trim()); // Assuming userHolder holds the current user's ID

            // Check if the selected userID is not the same as the current userID
            if (selectedUserID != currentUserID) {
                // Check if the user is already inactive
                if ("Inactive".equalsIgnoreCase(currentStat)) {
                    // Display warning alert if the user is already inactive
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Already Inactive");
                    alert.setHeaderText(null);
                    alert.setContentText("The user is already inactive.");
                    alert.showAndWait();
                } else {
                    // Display confirmation alert before deactivation
                    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmationAlert.setTitle("Confirm Deactivation");
                    confirmationAlert.setHeaderText(null);
                    confirmationAlert.setContentText("Are you sure you want to deactivate this user?");

                    // Handle the user's response
                    confirmationAlert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            // Proceed with deactivation
                            String updateUserData = "UPDATE userAccounts SET isActive = '0' WHERE userID = ?";

                            // Establish connection and create a prepared statement
                            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(updateUserData)) {

                                // Set the userID parameter
                                preparedStatement.setInt(1, selectedUserID);

                                // Execute the update query
                                int rowsUpdated = preparedStatement.executeUpdate();

                                // Check if the update was successful
                                if (rowsUpdated > 0) {
                                    // Display success alert
                                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                                    successAlert.setTitle("Success");
                                    successAlert.setHeaderText(null);
                                    successAlert.setContentText("User access has been deactivated.");
                                    successAlert.showAndWait();
                                    loadUserData();

                                    logAudit("User " + selectedUserID + " has been deactivated", userID);
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } else {
                // Display warning alert if attempting to deactivate own account
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("You cannot deactivate your own account.");
                alert.showAndWait();
            }
        } else {
            // Display warning alert if no user is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("A user must be selected.");
            alert.showAndWait();
        }
    }

    private void searchChange() {
        // Add a listener to usersID to call loadUserData when its value changes
        searchKey.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null || newValue.trim().isEmpty()) {
                    loadUserData();
                }
            }
        });
    }

    @FXML
    private void userSearch(ActionEvent event) {

        // Get the search term from the text field
        String uSearch = searchKey.getText().trim();

        usersID.clear();
        empID.clear();
        Fname.clear();
        Lname.clear();
        Address.clear();
        Mobile.clear();
        Email.clear();
        Zip.clear();
        Bday.setValue(LocalDate.now());

        if (!uSearch.isEmpty()) {
            // Construct the SQL query with the search term

            String searchU = "SELECT userID, CONCAT(fName, ' ', lName) AS fullName, isActive FROM userAccounts "
                    + "WHERE userID LIKE '%" + uSearch + "%' "
                    + "OR lName LIKE '%" + uSearch + "%' "
                    + "OR fName LIKE '%" + uSearch + "%' ";

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {

                // Execute the query
                ResultSet resultSet = statement.executeQuery(searchU);

                // Clear the TableView items
                tblUser.getItems().clear();

                // Flag to check if any records were found
                boolean recordsFound = false;

                // Populate the TableView with search results
                while (resultSet.next()) {
                    int cID = resultSet.getInt("userID");
                    String fullName = resultSet.getString("fullName");
                    int uStatus = resultSet.getInt("isActive");
                    String userStat = (uStatus == 1) ? "Active" : "Inactive";

                    // Create a Clients object
                    UserManager userList = new UserManager(cID, fullName, userStat);

                    // Add the Client object to the TableView
                    tblUser.getItems().add(userList);

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
                    loadUserData();
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
            loadUserData();
        }

    }

    @FXML
    private void addUserBtn(ActionEvent event) {
        ;
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
    private void serviceRatesLbl(MouseEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("servicesOffered.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Services Offered");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            stage.showAndWait(); // Wait for the dialog to close before continuing with main window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void fundRatesLbl(MouseEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fundLevelUpdate.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Services Offered");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

            stage.showAndWait(); // Wait for the dialog to close before continuing with main window
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logsLbl(MouseEvent event) {

    }

    @FXML
    private void uploadLbl(MouseEvent event) {

    }

    @Override
    public void onClose() {
        loadEmpDataFromDatabase(); // Reload data when the second form closes
    }

    //-----------------------------------------------ANALYTICS----------------------------------------
    // Method to load the total count of users from the usersAccount table
    public void loadTotalUsers() {
        String query = "SELECT COUNT(*) AS total FROM userAccounts";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                int total = resultSet.getInt("total");  // Get the total count from the query result
                totalUsers.setText(String.valueOf(total));  // Update the label with the total count
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving total users: " + e.getMessage());
        }
    }
    // Method to load the number of lockouts where loginCount > 3

    public void loadNumOfLockouts() {
        String query = "SELECT COUNT(*) AS lockouts FROM loginAttempts WHERE loginCount >= 3";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                int lockouts = resultSet.getInt("lockouts");  // Get the count of rows where loginCount > 3
                numOfLockouts.setText(String.valueOf(lockouts));  // Update the label with the lockout count
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving lockouts count: " + e.getMessage());
        }
    }

    // Method to load the number of active users (where stat = 2)
    public void loadNumOfActiveUsers() {
        String query = "SELECT COUNT(*) AS activeUsers FROM userAccounts WHERE stats = 2";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                int activeUsers = resultSet.getInt("activeUsers");  // Get the count of active users
                numOfActiveUSers.setText(String.valueOf(activeUsers));  // Update the label with the active user count
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error retrieving active users count: " + e.getMessage());
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
