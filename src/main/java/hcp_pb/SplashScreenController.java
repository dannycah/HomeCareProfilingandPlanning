package hcp_pb;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class SplashScreenController implements Initializable {

    @FXML
    private Label lblProgress;
    @FXML
    private ProgressBar barProgress;

    private final String MYSQL_URL = "jdbc:mysql://localhost:3306";
    private final String DB_URL = MYSQL_URL + "/HCP_PBP";
    private final String USER_NAME = "root";
    private final String PASSWORD = "!Student1";
    private final String dbCreateSQL = "CREATE DATABASE IF NOT EXISTS HCP_PBP";
    private final String TABLE_FUNDING_LEVEL = "CREATE TABLE IF NOT EXISTS fundingLevel ("
            + "levelID INT PRIMARY KEY, "
            + "dailyFund DECIMAL(10, 2), "
            + "monthlyFund DECIMAL(10, 2), "
            + "monthlyITF DECIMAL(10, 2), "
            + "dementiaFlag BOOLEAN)";
    
    
          private final String TABLE_USER_ROLES = "CREATE TABLE IF NOT EXISTS userRoles ("
            + "roleID INT PRIMARY KEY, "
            + "roleDesc  VARCHAR(50), "
            + "dateAdded DATE) ";

    private final String TABLE_USER_ACCOUNTS = "CREATE TABLE IF NOT EXISTS userAccounts ("
            + "userID INT PRIMARY KEY, "
            + "employeeID INT, "
            + "fName VARCHAR(20) NOT NULL, "
            + "lName VARCHAR(20) NOT NULL, "
            + "bDay DATE, "
            + "userEmail VARCHAR(20), "
            + "userMobile VARCHAR(20), "
            + "userAddress VARCHAR(250), "
            + "userZip VARCHAR(20), "
            + "roleID VARCHAR(20), "
            + "userName VARCHAR(100) NOT NULL, "
            + "userPass VARCHAR(100) NOT NULL, "
            + "isActive int, "
            + "stats int) ";
    
        private final String TABLE_LOGIN_ATTEMPTS = "CREATE TABLE IF NOT EXISTS loginAttempts ("
            + "loginID INT PRIMARY KEY, "
            + "userName  VARCHAR(20), "
            + "lastAttempt DATETIME, "
            + "loginCount INT) ";
//            + "FOREIGN KEY (userName) REFERENCES userAccounts(userName))";
    
    

    private final String TABLE_EMPLOYEE_LIST = "CREATE TABLE IF NOT EXISTS employeeList ("
            + "employeeID INT PRIMARY KEY, "
            + "userID INT, "
            + "fullName VARCHAR(100) NOT NULL, "
            + "startDate DATE, "
            + "endDate DATE, "
            + "activeFlag BOOLEAN NOT NULL DEFAULT TRUE, "
            + "UNIQUE (employeeID), "
            + "FOREIGN KEY (userID) REFERENCES userAccounts(userID))";

    private final String TABLE_AUDIT_TRAIL = "CREATE TABLE IF NOT EXISTS auditTrail ("
            + "logID INT PRIMARY KEY, "
            + "logDateTime DATETIME, "
            + "logDetails TEXT, "
            + "userID INT, "
            + "FOREIGN KEY (userID) REFERENCES userAccounts(userID))";

    private final String TABLE_CLIENT_DATA = "CREATE TABLE IF NOT EXISTS clientData ("
            + "clientID INT PRIMARY KEY, "
            + "fName VARCHAR(20) NOT NULL, "
            + "lName VARCHAR(20) NOT NULL, "
            + "levelID INT NOT NULL, "
            + "clientMedicare VARCHAR(20), "
            + "clientAddress VARCHAR(250), "
            + "clientMobile VARCHAR(20), "
            + "clientBday DATE, "
            + "emergencyContactPerson VARCHAR(20), "
            + "emergencyContactNumber VARCHAR(20), "
            + "relationship VARCHAR(20), "
            + "FOREIGN KEY (levelID) REFERENCES fundingLevel(levelID))";

    private final String TABLE_CLIENT_CONTACT = "CREATE TABLE IF NOT EXISTS clientContact ("
            + "contactID INT PRIMARY KEY, "
            + "primaryContact VARCHAR(20), "
            + "primaryRelationship VARCHAR(20), "
            + "primaryPhone VARCHAR(20), "
            + "primaryEmail VARCHAR(20), "
            + "secondaryContact VARCHAR(20), "
            + "secondaryRelationship VARCHAR(20), "
            + "secondaryPhone VARCHAR(20), "
            + "secondaryEmail VARCHAR(20), "
            + "medPractitioner VARCHAR(20), "
            + "practiceName VARCHAR(20), "
            + "practiceAddress VARCHAR(250), "
            + "practicePhone VARCHAR(20), "
            + "practiceEmail VARCHAR(20), "
            + "medPharma VARCHAR(20), "
            + "pharmaAddress VARCHAR(250), "
            + "pharmaPhone VARCHAR(20), "
            + "pharmaEmail VARCHAR(20), "
            + "clientID INT, "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID))";

    private final String TABLE_CLIENT_CASES = "CREATE TABLE IF NOT EXISTS clientCases ("
            + "caseID INT PRIMARY KEY, "
            + "caseType VARCHAR(20), "
            + "casePriority VARCHAR(20), "
            + "caseAssignment VARCHAR(20), "
            + "caseDate DATE, "
            + "clientType VARCHAR(20), "
            + "clientID INT, "
            + "userID INT, "
            + "FOREIGN KEY (userID) REFERENCES userAccounts(userID), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID))";

    private final String TABLE_CLIENT_ASSESSMENT = "CREATE TABLE IF NOT EXISTS clientAssessment ("
            + "assessmentID INT PRIMARY KEY, "
            + "clientID INT NOT NULL, "
            + "caseID INT, "
            + "assignedCSO VARCHAR(20), "
            + "assessmentDate DATE, "
            + "referralCode VARCHAR(20), "
            + "assessedLevel VARCHAR(20), "
            + "userID INT, "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (caseID) REFERENCES clientCases(caseID), "
            + "FOREIGN KEY (userID) REFERENCES userAccounts(userID))";

    private final String TABLE_CLIENT_HYGIENE = "CREATE TABLE IF NOT EXISTS clientHygiene ("
            + "hygieneID INT PRIMARY KEY, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "bpgRequirement VARCHAR(20), "
            + "bathing VARCHAR(20), "
            + "bodyScrub VARCHAR(20), "
            + "grooming VARCHAR(20), "
            + "showerIntervals VARCHAR(20), "
            + "showerCarerReq VARCHAR(20), "
            + "showerCarerGender VARCHAR(20), "
            + "showerPrefDay VARCHAR(20), "
            + "dressingRequirement VARCHAR(20), "
            + "dressing VARCHAR(20), "
            + "undressing VARCHAR(20), "
            + "socks VARCHAR(20), "
            + "shoes VARCHAR(20), "
            + "dressingCarerReq VARCHAR(20), "
            + "dressingCarerGender VARCHAR(20), "
            + "dressingPrefDay VARCHAR(20), "
            + "oralHygieneRequirement VARCHAR(20), "
            + "holdToothbrush VARCHAR(20), "
            + "toothPasteSq VARCHAR(20), "
            + "mouthRinse VARCHAR(20), "
            + "mouthIssues VARCHAR(20), "
            + "teethForms VARCHAR(20), "
            + "dentistVisit DATE, "
            + "lastVisit DATE, "
            + "dentistName VARCHAR(20), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID))";

    private final String TABLE_CLIENT_TOILETING = "CREATE TABLE IF NOT EXISTS clientToileting ("
            + "toiletingID INT PRIMARY KEY, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "toiletingRequirement VARCHAR(20), "
            + "walkToilet VARCHAR(20), "
            + "pantsOff VARCHAR(20), "
            + "toiletSitting VARCHAR(20), "
            + "pWashing VARCHAR(20), "
            + "pDrying VARCHAR(20), "
            + "continenceRequirement VARCHAR(20), "
            + "setupPads VARCHAR(20), "
            + "pWipeDry VARCHAR(20), "
            + "continenceNeed VARCHAR(20), "
            + "size VARCHAR(20), "
            + "continenceFund VARCHAR(20), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID))";

    private final String TABLE_CLIENT_MOBILITY = "CREATE TABLE IF NOT EXISTS clientMobility ("
            + "mobilityID INT PRIMARY KEY, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "mobilityRequirement VARCHAR(20), "
            + "mobilityIssue VARCHAR(20), "
            + "issueDets TEXT, "
            + "tStand VARCHAR(20), "
            + "tSit VARCHAR(20), "
            + "tLying VARCHAR(20), "
            + "tSitting VARCHAR(20), "
            + "walking VARCHAR(20), "
            + "carRide VARCHAR(20), "
            + "useStairs VARCHAR(20), "
            + "mobilityAids VARCHAR(20), "
            + "lifterRequirement VARCHAR(20), "
            + "fallHistory VARCHAR(20), "
            + "fallDets TEXT, "
            + "fallCause VARCHAR(20), "
            + "exercise VARCHAR(20), "
            + "exerciseDets TEXT, "
            + "alliedHealthRef VARCHAR(20), "
            + "mobilityObserved VARCHAR(20), "
            + "unsteady VARCHAR(20), "
            + "stooped VARCHAR(20), "
            + "leaning VARCHAR(20), "
            + "breathIssue VARCHAR(20), "
            + "breathIssueDets TEXT, "
            + "commsRequirement VARCHAR(20), "
            + "hAidsFitting VARCHAR(20), "
            + "battChange VARCHAR(20), "
            + "aidCleaning VARCHAR(20), "
            + "usingPhone VARCHAR(20), "
            + "hearingRef VARCHAR(20), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID))";

    private final String TABLE_CLIENT_HEALTH = "CREATE TABLE IF NOT EXISTS clientHealth ("
            + "healthID INT PRIMARY KEY, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "nutritionRequirement VARCHAR(20), "
            + "mealPrep VARCHAR(20), "
            + "foodService VARCHAR(20), "
            + "swallowIssue VARCHAR(20), "
            + "weightLoss VARCHAR(20), "
            + "needSupplement VARCHAR(20), "
            + "useCutlery VARCHAR(20), "
            + "dietaryRequirement VARCHAR(20), "
            + "mealSize VARCHAR(20), "
            + "dietRef VARCHAR(20), "
            + "skinTear VARCHAR(20), "
            + "skinProblem VARCHAR(20), "
            + "useSkinProduct VARCHAR(20), "
            + "prescribed VARCHAR(20), "
            + "skinProductDets TEXT, "
            + "skinSpecialistRef VARCHAR(20), "
            + "woundDress VARCHAR(20), "
            + "assistReq VARCHAR(20), "
            + "pressureInjury VARCHAR(20), "
            + "injuryDets TEXT, "
            + "attendPodiatrist VARCHAR(20), "
            + "podiatrist VARCHAR(20), "
            + "visitFreq VARCHAR(20), "
            + "cognitiveImpairment VARCHAR(20), "
            + "cognitiveAssessment VARCHAR(20), "
            + "memoryLoss VARCHAR(20), "
            + "depression VARCHAR(20), "
            + "anxiety VARCHAR(20), "
            + "schizophrenia VARCHAR(20), "
            + "otherMental VARCHAR(20), "
            + "medicationRequirement VARCHAR(20), "
            + "needReminder VARCHAR(20), "
            + "needPrompt VARCHAR(20), "
            + "medAdmin VARCHAR(20), "
            + "medPickup VARCHAR(20), "
            + "takingPresc VARCHAR(20), "
            + "medsMismanagement VARCHAR(20), "
            + "medPep VARCHAR(20), "
            + "medInterval VARCHAR(20), "
            + "specialMed VARCHAR(20), "
            + "smDets TEXT, "
            + "supplement VARCHAR(20), "
            + "bloodMonitorAssist VARCHAR(20), "
            + "injectAssist VARCHAR(20), "
            + "glucoseAssist VARCHAR(20), "
            + "painExp VARCHAR(20), "
            + "painMed VARCHAR(20), "
            + "painMedFreq VARCHAR(20), "
            + "painLevel VARCHAR(20), "
            + "respiteRequire VARCHAR(20), "
            + "respiteDelivery VARCHAR(20), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID))";

    private final String TABLE_CLIENT_HOUSING = "CREATE TABLE IF NOT EXISTS clientHousing ("
            + "housingID INT PRIMARY KEY, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "housingSetup VARCHAR(20), "
            + "housingOwnership VARCHAR(20), "
            + "livingSetup VARCHAR(20), "
            + "personalAlarm VARCHAR(20), "
            + "alarmCompany VARCHAR(20), "
            + "keyLockBox VARCHAR(20), "
            + "keyLockCode VARCHAR(20), "
            + "homeMaintainSupport VARCHAR(20), "
            + "cleaning VARCHAR(20), "
            + "decluttering VARCHAR(20), "
            + "dusting VARCHAR(20), "
            + "vacuum VARCHAR(20), "
            + "mop VARCHAR(20), "
            + "sweeping VARCHAR(20), "
            + "laundry VARCHAR(20), "
            + "hangClothes VARCHAR(20), "
            + "lightGardening VARCHAR(20), "
            + "mowGarden VARCHAR(20), "
            + "windowCleaning VARCHAR(20), "
            + "gutterCleaning VARCHAR(20), "
            + "bedroomCleaning VARCHAR(20), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID))";

    private static final String TABLE_CLIENT_TRANSPORT = "CREATE TABLE IF NOT EXISTS clientTransport ("
            + "transportID INT PRIMARY KEY, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "socialActivity VARCHAR(20), "
            + "activityAssist VARCHAR(20), "
            + "transportAssist VARCHAR(20), "
            + "canDrive VARCHAR(20), "
            + "taxiVoucher VARCHAR(20), "
            + "shoppingAssist VARCHAR(20), "
            + "docAptAssist VARCHAR(20), "
            + "otherAssist VARCHAR(20), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID))";

    private static final String TABLE_NEAR_DEATH_PREFERENCES = "CREATE TABLE IF NOT EXISTS nearDeathPreferences ("
            + "preferenceID INT PRIMARY KEY, "
            + "eolPractice VARCHAR(20), "
            + "palliativeCare VARCHAR(20), "
            + "dnr VARCHAR(20), "
            + "dni VARCHAR(20), "
            + "informFam VARCHAR(20), "
            + "informGP VARCHAR(20), "
            + "clientID INT NOT NULL, "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID))";

    private static final String TABLE_CARE_PLAN = "CREATE TABLE IF NOT EXISTS carePlan ("
            + "carePlanID INT PRIMARY KEY, "
            + "clientID INT NOT NULL, "
            + "userID INT NOT NULL, "
            + "dateCreated DATE NOT NULL, "
            + "toiletingCare VARCHAR(20), "
            + "hygieneCare VARCHAR(20), "
            + "mobilityCare VARCHAR(20), "
            + "healthAssist VARCHAR(20), "
            + "medicationAssist VARCHAR(20), "
            + "alliedHealth VARCHAR(20), "
            + "housingCare VARCHAR(20), "
            + "cleaningCare VARCHAR(20), "
            + "transportAssist VARCHAR(20), "
            + "communityAccess VARCHAR(20), "
            + "socialSupport VARCHAR(20), "
            + "afterLifeAssist VARCHAR(20), "
            + "requireObservation VARCHAR(20), "
            + "requireReassessment VARCHAR(20), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (userID) REFERENCES userAccounts(userID))";

    private static final String TABLE_SERVICE_PROVIDED = "CREATE TABLE IF NOT EXISTS serviceProvided ("
            + "serviceID INT PRIMARY KEY, "
            + "serviceType VARCHAR(20), "
            + "serviceDesc TEXT, "
            + "serviceAmount DECIMAL(10, 2))";

    private static final String TABLE_BUDGET_PLAN = "CREATE TABLE IF NOT EXISTS budgetPlan ("
            + "budgetID INT PRIMARY KEY, "
            + "clientID INT NOT NULL, "
            + "userID INT NOT NULL, "
            + "carePlanID INT NOT NULL, "
            + "serviceID INT NOT NULL, "
            + "serviceAmount DECIMAL(10, 2), "
            + "Freq VARCHAR(20), "
            + "subTotal DECIMAL(10, 2), "
            + "Total DECIMAL(10, 2), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (userID) REFERENCES userAccounts(userID), "
            + "FOREIGN KEY (serviceID) REFERENCES serviceProvided(serviceID), "
            + "FOREIGN KEY (carePlanID) REFERENCES carePlan(carePlanID))";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblProgress.setText("Initializing...");
        barProgress.setProgress(ProgressBar.INDETERMINATE_PROGRESS);

        // Perform database setup in a separate thread
        new Thread(() -> {
            try {
                // Connect to MySQL server
                try (Connection sqlConnection = DriverManager.getConnection(MYSQL_URL, USER_NAME, PASSWORD)) {
                    if (sqlConnection != null) {
                        Platform.runLater(() -> {
                            lblProgress.setText("Connecting to database...");
                        });
                        System.out.println("MySQL Connection Successful!");

                        // Pause for visual effect
                        Thread.sleep(2000);

                        // Create database if it does not exist
                        if (!databaseExists(sqlConnection, "HCP_PBP")) {
                            try (Statement statement = sqlConnection.createStatement()) {
                                statement.executeUpdate(dbCreateSQL);
                            }
                        }

                        // Connect to the specific database
                        try (Connection dbConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD)) {
                            if (dbConnection != null) {
                                Platform.runLater(() -> {
                                    lblProgress.setText("Checking database schema...");
                                });
                                System.out.println("HCP_PBP Database Connection Successful!");

                                // Pause for visual effect
                                Thread.sleep(2000);

                                // Create tables if they do not exist
                                try (Statement statement = dbConnection.createStatement()) {
                                    statement.executeUpdate(TABLE_FUNDING_LEVEL);
                                    statement.executeUpdate(TABLE_USER_ROLES);
                                    statement.executeUpdate(TABLE_USER_ACCOUNTS);
                                    statement.executeUpdate(TABLE_LOGIN_ATTEMPTS);
                                    statement.executeUpdate(TABLE_EMPLOYEE_LIST);
                                    statement.executeUpdate(TABLE_AUDIT_TRAIL);
                                    statement.executeUpdate(TABLE_CLIENT_DATA);
                                    statement.executeUpdate(TABLE_CLIENT_CONTACT);
                                    statement.executeUpdate(TABLE_CLIENT_CASES);
                                    statement.executeUpdate(TABLE_CLIENT_ASSESSMENT);
                                    statement.executeUpdate(TABLE_CLIENT_HYGIENE);
                                    statement.executeUpdate(TABLE_CLIENT_TOILETING);
                                    statement.executeUpdate(TABLE_CLIENT_MOBILITY);
                                    statement.executeUpdate(TABLE_CLIENT_HEALTH);
                                    statement.executeUpdate(TABLE_CLIENT_HOUSING);
                                    statement.executeUpdate(TABLE_CLIENT_TRANSPORT);
                                    statement.executeUpdate(TABLE_NEAR_DEATH_PREFERENCES);
                                    statement.executeUpdate(TABLE_CARE_PLAN);
                                    statement.executeUpdate(TABLE_SERVICE_PROVIDED);
                                    statement.executeUpdate(TABLE_BUDGET_PLAN);
                                    System.out.println("Tables Created Successfully!");

                                    Platform.runLater(() -> {
                                        lblProgress.setText("Loading information...");
                                    });

                                    // Pause for visual effect
                                    Thread.sleep(2000);

                                    // Check if the user table is empty
                                    try (Statement connectStatement = dbConnection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM useraccounts")) {
                                        if (resultSet.next() && resultSet.getInt(1) == 0) {
                                            // User table is empty, show prompt to create admin user
                                            Platform.runLater(() -> {
                                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                                alert.setTitle("Setup User");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Set up system for first time use, please create an admin profile");
                                                alert.showAndWait();

                                                // Proceed to user creation
                                                try {
                                                    Parent enrolStaffRoot = FXMLLoader.load(getClass().getResource("registerAdmin.fxml"));
                                                    Scene enrolStaffScene = new Scene(enrolStaffRoot);
                                                    Stage primaryStage = (Stage) barProgress.getScene().getWindow();
                                                    primaryStage.setScene(enrolStaffScene);
                                                    primaryStage.setTitle("Administrator Registration");
                                                    //primaryStage.initStyle(StageStyle.UNDECORATED);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            });
                                        } else {
                                            // User table is not empty, proceed to welcome page
                                            Platform.runLater(() -> {
                                                lblProgress.setText("Initializing UI...");
                                                barProgress.setProgress(1.0);
                                            });

                                            // Load the welcome page after completion
                                            Platform.runLater(() -> {
                                                try {
                                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomeHCP.fxml"));
                                                    Parent welcomePage = loader.load();
                                                    Scene welcomeScene = new Scene(welcomePage);

                                                    // Get the current stage and set the new scene
                                                    Stage stage = (Stage) lblProgress.getScene().getWindow();
                                                    stage.setScene(welcomeScene);
                                                    stage.setTitle("Welcome Page");
                                                    stage.setResizable(false);
                                                    stage.setWidth(680);  // Set the width 
                                                    stage.setHeight(520); // Set the height

                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            });
                                        }
                                    }
                                }
                            } else {
                                Platform.runLater(() -> lblProgress.setText("Failed to connect to HCP_PBP Database."));
                            }
                        }
                    } else {
                        Platform.runLater(() -> lblProgress.setText("Failed to connect to MySQL Server."));
                    }
                } catch (SQLException e) {
                    handleSQLException(e);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        lblProgress.setText("Connecting to database...");
//        barProgress.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
//
//        // Perform database setup in a separate thread
//        new Thread(() -> {
//            // Connect to MySQL server
//            try (Connection sqlConnection = DriverManager.getConnection(MYSQL_URL, USER_NAME, PASSWORD)) {
//                if (sqlConnection != null) {
//                    System.out.println("MySQL Connection Successful!");
//                    
//                    // Create database if it does not exist
//                    if (!databaseExists(sqlConnection, "HCP_PBP")) {
//                        try (Statement statement = sqlConnection.createStatement()) {
//                            statement.executeUpdate(dbCreateSQL);
//                        }
//                    }
//                    
//                    // Connect to the specific database
//                    try (Connection dbConnection = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD)) {
//                        if (dbConnection != null) {
//                            System.out.println("HCP_PBP Database Connection Successful!");
//                            
//                            // Create tables if they do not exist
//                            try (Statement statement = dbConnection.createStatement()) {
//                                statement.executeUpdate(TABLE_FUNDING_LEVEL);
//                                statement.executeUpdate(TABLE_USER_ACCOUNTS);
//                                statement.executeUpdate(TABLE_EMPLOYEE_LIST);
//                                statement.executeUpdate(TABLE_AUDIT_TRAIL);
//                                statement.executeUpdate(TABLE_CLIENT_DATA);
//                                statement.executeUpdate(TABLE_CLIENT_CONTACT);
//                                statement.executeUpdate(TABLE_CLIENT_CASES);
//                                statement.executeUpdate(TABLE_CLIENT_ASSESSMENT);
//                                statement.executeUpdate(TABLE_CLIENT_HYGIENE);
//                                statement.executeUpdate(TABLE_CLIENT_TOILETING);
//                                statement.executeUpdate(TABLE_CLIENT_MOBILITY);
//                                statement.executeUpdate(TABLE_CLIENT_HEALTH);
//                                statement.executeUpdate(TABLE_CLIENT_HOUSING);
//                                statement.executeUpdate(TABLE_CLIENT_TRANSPORT);
//                                statement.executeUpdate(TABLE_NEAR_DEATH_PREFERENCES);
//                                statement.executeUpdate(TABLE_CARE_PLAN);
//                                statement.executeUpdate(TABLE_SERVICE_PROVIDED);
//                                statement.executeUpdate(TABLE_BUDGET_PLAN);
//                                System.out.println("Tables Created Successfully!");
//                                
//                                Platform.runLater(() -> {
//                                    lblProgress.setText("Setup Complete!");
//                                    barProgress.setProgress(1.0);
//                                });
//                            }
//                        } else {
//                            Platform.runLater(() -> lblProgress.setText("Failed to connect to HCP_PBP Database."));
//                        }
//                    }
//                } else {
//                    Platform.runLater(() -> lblProgress.setText("Failed to connect to MySQL Server."));
//                }
//            } catch (SQLException e) {
//                handleSQLException(e);
//            }
//        }).start();
//    }
    // Method to check if a database exists
    private boolean databaseExists(Connection connection, String dbName) throws SQLException {
        try (ResultSet dbData = connection.getMetaData().getCatalogs()) {
            while (dbData.next()) {
                if (dbData.getString(1).equalsIgnoreCase(dbName)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Method to handle SQLException
    private void handleSQLException(SQLException e) {
        System.out.println("Connection Failed! Check output console");
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        e.printStackTrace();
    }
}
