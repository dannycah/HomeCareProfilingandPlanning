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
            + "levelID VARCHAR(20) PRIMARY KEY, "
            + "dailyFund DECIMAL(10, 2), "
            + "fortnightlyFund DECIMAL(10, 2), "
            + "monthlyFund DECIMAL(10, 2), "
            + "dementiaFlag VARCHAR(10)"
            + ");";

    private final String TABLE_USER_ROLES = "CREATE TABLE IF NOT EXISTS userRoles ("
            + "roleID INT PRIMARY KEY, "
            + "roleDesc  VARCHAR(50), "
            + "dateAdded DATE "
            + ");";

    private final String TABLE_EMPLOYEE_LIST = "CREATE TABLE IF NOT EXISTS employeeList ("
            + "employeeID INT PRIMARY KEY, "
            + "fullName VARCHAR(100) NOT NULL, "
            + "startDate VARCHAR(20), "
            + "endDate VARCHAR(20), "
            + "activeFlag BOOLEAN NOT NULL DEFAULT TRUE"
            + ");";

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
            + "stats int, "
            + "FOREIGN KEY (employeeID) REFERENCES employeeList(employeeID)"
            + ");";

    private final String TABLE_SERVICE_OFFERED = "CREATE TABLE IF NOT EXISTS serviceoffered ("
            + "serviceID VARCHAR(10), "
            + "servicedesc VARCHAR(255), "
            + "dayshift DECIMAL(10, 2), "
            + "eveningshift DECIMAL(10, 2), "
            + "satShift DECIMAL(10, 2), "
            + "sunShift DECIMAL(10, 2), "
            + "holidayShift DECIMAL(10, 2), "
            + "satNight DECIMAL(10, 2), "
            + "sunNight DECIMAL(10, 2), "
            + "holidayNight DECIMAL(10, 2)"
            + ");";

    private final String CREATE_BUDGET_STAGING_TABLE = "CREATE TABLE IF NOT EXISTS budget_staging ("
            + "stagingID INT AUTO_INCREMENT PRIMARY KEY, "
            + "clientID VARCHAR(10), "
            + "assessmentID VARCHAR(10), "
            + "caseID VARCHAR(10), "
            + "serviceID VARCHAR(10), "
            + "counter INT DEFAULT 0"
            + ");";

    private final String TABLE_LOGIN_ATTEMPTS = "CREATE TABLE IF NOT EXISTS loginAttempts ("
            + "loginID INT PRIMARY KEY, "
            + "userName  VARCHAR(20), "
            + "lastAttempt DATETIME, "
            + "loginCount INT "
            + ");";
//            + "FOREIGN KEY (userName) REFERENCES userAccounts(userName))";

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
            + "levelID VARCHAR(20), "
            + "clientMedicare VARCHAR(20), "
            + "clientAddress VARCHAR(250), "
            + "clientZip VARCHAR(5), "
            + "clientMobile VARCHAR(20), "
            + "clientEmail VARCHAR(50), "
            + "clientBday DATE, "
            + "emergencyContactPerson VARCHAR(20), "
            + "emergencyContactNumber VARCHAR(20), "
            + "relationship VARCHAR(20), "
            + "isActive VARCHAR(10)DEFAULT 1, "
            + "FOREIGN KEY (levelID) REFERENCES fundingLevel(levelID))";

    private final String TABLE_CARE_MANAGEMENT = "CREATE TABLE IF NOT EXISTS clientCareManagement ("
            + "managementID INT AUTO_INCREMENT PRIMARY KEY, "
            + "clientID INT, "
            + "clientStanding VARCHAR(20), "
            + "referralCode VARCHAR(20), "
            + "levelID  VARCHAR(20), "
            + "userID INT, "
            + "handOver VARCHAR(200), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (levelID) REFERENCES fundingLevel(levelID), "
            + "FOREIGN KEY (userID) REFERENCES userAccounts(userID))";

    private final String TABLE_CLIENT_CONTACT = "CREATE TABLE IF NOT EXISTS clientContact ("
            + "contactID INT AUTO_INCREMENT PRIMARY KEY, "
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
            + "caseAssignment VARCHAR(50), "
            + "caseDate DATE, "
            + "clientType VARCHAR(20), "
            + "clientID INT, "
            + "userID INT, "
            + "assessmentStatus VARCHAR(20), "
                 + "createUser VARCHAR(20),"
            + "closingReason VARCHAR(500), "
            + "FOREIGN KEY (userID) REFERENCES userAccounts(userID), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID))";

    private final String TABLE_CLIENT_ASSESSMENT = "CREATE TABLE IF NOT EXISTS clientAssessment ("
            + "assessmentID INT PRIMARY KEY, "
            + "clientID INT NOT NULL, "
            + "caseID INT, "
            + "assignedCSO VARCHAR(20), "
            + "assessmentDate DATE, "
            + "assessVenue VARCHAR(50), "
            + "assessAddress VARCHAR(150), "
            + "asessedLevel VARCHAR(10), "
            + "assessOutcome VARCHAR(250), "
            + "assessStatus VARCHAR(10), "
            + "userID INT, "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (caseID) REFERENCES clientCases(caseID), "
            + "FOREIGN KEY (userID) REFERENCES userAccounts(userID))";

    private static final String TABLE_CLIENT_HYGIENE = "CREATE TABLE IF NOT EXISTS clientHygiene ("
            + "hygieneID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "bpgRequirement VARCHAR(100), "
            + "bathing VARCHAR(100), "
            + "bodyb VARCHAR(100), "
            + "groominScrug VARCHAR(100), "
            + "showerIntervals VARCHAR(100), "
            + "showerCarerReq VARCHAR(100), "
            + "showerCarerGender VARCHAR(100), "
            + "showerPrefDay VARCHAR(100), "
            + "dressingRequirement VARCHAR(100), "
            + "dressing VARCHAR(100), "
            + "undressing VARCHAR(100), "
            + "socks VARCHAR(100), "
            + "shoes VARCHAR(100), "
            + "dressingCarerReq VARCHAR(100), "
            + "dressingCarerGender VARCHAR(100), "
            + "dressingPrefDay VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_DENTAL = "CREATE TABLE IF NOT EXISTS clientDental ("
            + "dentalID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "oralHygieneRequirement VARCHAR(100), "
            + "holdToothbrush VARCHAR(100), "
            + "toothPasteSq VARCHAR(100), "
            + "mouthRinse VARCHAR(100), "
            + "mouthIssues VARCHAR(100), "
            + "teethForms VARCHAR(100), "
            + "dentistVisit DATE, "
            + "lastVisit DATE, "
            + "dentistName VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_TOILETING = "CREATE TABLE IF NOT EXISTS clientToileting ("
            + "toiletingID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "toiletingRequirement VARCHAR(100), "
            + "walkToilet VARCHAR(100), "
            + "pantsOff VARCHAR(100), "
            + "toiletSitting VARCHAR(100), "
            + "pWashing VARCHAR(100), "
            + "pDrying VARCHAR(100), "
            + "continenceRequirement VARCHAR(100), "
            + "setupPads VARCHAR(100), "
            + "pWipeDry VARCHAR(100), "
            + "continenceNeed VARCHAR(100), "
            + "size VARCHAR(100), "
            + "continenceFund VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_MOBILITY = "CREATE TABLE IF NOT EXISTS clientMobility ("
            + "mobilityID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "mobilityRequirement VARCHAR(100), "
            + "mobilityIssue VARCHAR(100), "
            + "issueDets TEXT, "
            + "tStand VARCHAR(100), "
            + "tSit VARCHAR(100), "
            + "tLying VARCHAR(100), "
            + "tSitting VARCHAR(100), "
            + "walking VARCHAR(100), "
            + "carRide VARCHAR(100), "
            + "useStairs VARCHAR(100), "
            + "mobilityAids VARCHAR(100), "
            + "lifterRequirement VARCHAR(100), "
            + "fallHistory VARCHAR(100), "
            + "fallDets TEXT, "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_OTHER_MOBILITY = "CREATE TABLE IF NOT EXISTS otherMobility ("
            + "omobID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "exercise VARCHAR(100), "
            + "exerciseDets TEXT, "
            + "alliedHealthRef VARCHAR(100), "
            + "unsteady VARCHAR(100), "
            + "stooped VARCHAR(100), "
            + "leaning VARCHAR(100), "
            + "breathIssue VARCHAR(100), "
            + "breathIssueDets TEXT, "
            + "commsRequirement VARCHAR(100), "
            + "hearingRef VARCHAR(100), "
            + "hAidsFitting VARCHAR(100), "
            + "battChange VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_NUTRITION = "CREATE TABLE IF NOT EXISTS clientNutrition ("
            + "nutrionID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "nutritionRequirement VARCHAR(100), "
            + "mealPrep VARCHAR(100), "
            + "foodService VARCHAR(100), "
            + "swallowIssue VARCHAR(100), "
            + "weightLoss VARCHAR(100), "
            + "needSupplement VARCHAR(100), "
            + "useCutlery VARCHAR(100), "
            + "dietaryRequirement VARCHAR(100), "
            + "mealSize VARCHAR(100), "
            + "dietRef VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_SKIN = "CREATE TABLE IF NOT EXISTS clientSkin ("
            + "dermaID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "skinTear VARCHAR(100), "
            + "skinProblem VARCHAR(100), "
            + "useSkinProduct VARCHAR(100), "
            + "prescribed VARCHAR(100), "
            + "skinProductDets TEXT, "
            + "skinSpecialistRef VARCHAR(100), "
            + "woundDress VARCHAR(100), "
            + "assistReq VARCHAR(100), "
            + "pressureInjury VARCHAR(100), "
            + "injuryDets TEXT, "
            + "attendPodiatrist VARCHAR(100), "
            + "podiatrist VARCHAR(100), "
            + "visitFreq VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_MENTAL = "CREATE TABLE IF NOT EXISTS clientMental ("
            + "mentalHealthID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "cognitiveImpairment VARCHAR(100), "
            + "cognitiveAssessment VARCHAR(100), "
            + "memoryLoss VARCHAR(100), "
            + "depression VARCHAR(100), "
            + "schizophrenia VARCHAR(100), "
            + "anxiety VARCHAR(100), "
            + "otherMental VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_MEDICATION = "CREATE TABLE IF NOT EXISTS clientMedication ("
            + "medID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "medicationRequirement VARCHAR(100), "
            + "needReminder VARCHAR(100), "
            + "needPrompt VARCHAR(100), "
            + "medAdmin VARCHAR(100), "
            + "medPickup VARCHAR(100), "
            + "takingPresc VARCHAR(100), "
            + "medsMismanagement VARCHAR(100), "
            + "medPep VARCHAR(100), "
            + "medInterval VARCHAR(100), "
            + "specialMed VARCHAR(100), "
            + "smDets TEXT, "
            + "supplement VARCHAR(100), "
            + "bloodMonitorAssist VARCHAR(100), "
            + "injectAssist VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_PAIN = "CREATE TABLE IF NOT EXISTS clientPain ("
            + "painMgmtID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "painExp VARCHAR(100), "
            + "painMed VARCHAR(100), "
            + "painMedFreq VARCHAR(100), "
            + "painLevel VARCHAR(100), "
            + "respiteRequire VARCHAR(100), "
            + "respiteDelivery VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_HOUSING = "CREATE TABLE IF NOT EXISTS clientHousing ("
            + "housingID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "housingOwnership VARCHAR(100), "
            + "housingSetup VARCHAR(100), "
            + "livingSetup VARCHAR(100), "
            + "personalAlarm VARCHAR(100), "
            + "alarmCompany VARCHAR(100), "
            + "keyLockBox VARCHAR(100), "
            + "keyLockCode VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_HOME = "CREATE TABLE IF NOT EXISTS clientHome ("
            + "HomeTransID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "homeMaintainSupport VARCHAR(100), "
            + "cleaning VARCHAR(100), "
            + "decluttering VARCHAR(100), "
            + "dusting VARCHAR(100), "
            + "vacuum VARCHAR(100), "
            + "mop VARCHAR(100), "
            + "sweeping VARCHAR(100), "
            + "laundry VARCHAR(100), "
            + "hangClothes VARCHAR(100), "
            + "lightGardening VARCHAR(100), "
            + "mowGarden VARCHAR(100), "
            + "windowCleaning VARCHAR(100), "
            + "gutterCleaning VARCHAR(100), "
            + "bedroomCleaning VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_CLIENT_TRANSPORT = "CREATE TABLE IF NOT EXISTS clientTransport ("
            + "transportID INT PRIMARY KEY AUTO_INCREMENT, "
            + "clientID INT NOT NULL, "
            + "assessmentID INT NOT NULL, "
            + "socialActivity VARCHAR(100), "
            + "activityAssist VARCHAR(100), "
            + "transportAssist VARCHAR(100), "
            + "canDrive VARCHAR(100), "
            + "taxiVoucher VARCHAR(100), "
            + "shoppingAssist VARCHAR(100), "
            + "docAptAssist VARCHAR(100), "
            + "otherAssist VARCHAR(100), "
            + "FOREIGN KEY (clientID) REFERENCES clientData(clientID), "
            + "FOREIGN KEY (assessmentID) REFERENCES clientAssessment(assessmentID), "
            + "UNIQUE (clientID, assessmentID)"
            + ")";

    private static final String TABLE_NEAR_DEATH_PREFERENCES = "CREATE TABLE IF NOT EXISTS nearDeathPreferences ("
            + "preferenceID INT PRIMARY KEY AUTO_INCREMENT, "
            + "eolPractice VARCHAR(100), "
            + "palliativeCare VARCHAR(100), "
            + "dnr VARCHAR(100), "
            + "dni VARCHAR(100), "
            + "informFam VARCHAR(100), "
            + "informGP VARCHAR(100), "
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
                                    statement.executeUpdate(TABLE_EMPLOYEE_LIST);
                                    statement.executeUpdate(TABLE_USER_ACCOUNTS);
                                    statement.executeUpdate(TABLE_SERVICE_OFFERED);
                                    statement.executeUpdate(CREATE_BUDGET_STAGING_TABLE);
                                    statement.executeUpdate(TABLE_LOGIN_ATTEMPTS);
                                    statement.executeUpdate(TABLE_AUDIT_TRAIL);
                                    statement.executeUpdate(TABLE_CLIENT_DATA);
                                    statement.executeUpdate(TABLE_CARE_MANAGEMENT);
                                    statement.executeUpdate(TABLE_CLIENT_CONTACT);
                                    statement.executeUpdate(TABLE_CLIENT_CASES);
                                    statement.executeUpdate(TABLE_CLIENT_ASSESSMENT);
                                    statement.executeUpdate(TABLE_CLIENT_HYGIENE);
                                    statement.executeUpdate(TABLE_CLIENT_DENTAL);
                                    statement.executeUpdate(TABLE_CLIENT_TOILETING);
                                    statement.executeUpdate(TABLE_CLIENT_MOBILITY);
                                    statement.executeUpdate(TABLE_OTHER_MOBILITY);
                                    statement.executeUpdate(TABLE_CLIENT_NUTRITION);
                                    statement.executeUpdate(TABLE_CLIENT_SKIN);
                                    statement.executeUpdate(TABLE_CLIENT_MENTAL);
                                    statement.executeUpdate(TABLE_CLIENT_MEDICATION);
                                    statement.executeUpdate(TABLE_CLIENT_PAIN);
                                    statement.executeUpdate(TABLE_CLIENT_HOUSING);
                                    statement.executeUpdate(TABLE_CLIENT_HOME);
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
