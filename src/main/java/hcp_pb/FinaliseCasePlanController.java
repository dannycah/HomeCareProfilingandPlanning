/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
/**
 * FXML Controller class
 *
 * @author mark
 */
public class FinaliseCasePlanController implements Initializable {

    @FXML
    private ProgressIndicator prog;
    @FXML 
            private TabPane careplan;
    @FXML
    private Label cFname;
    @FXML
    private TextField csID;
    @FXML
    private TextField asssessID;
    @FXML
    private TextField cID;
    @FXML
    private TextField cMedicare;
    @FXML
    private DatePicker aDate;
    @FXML
    private TextField aVenue;
    @FXML
    private TextField cAddress;
    @FXML
    private Button genCP;
    @FXML
    private Button confirmBtn;
    @FXML
    private TextArea txtADL;
    @FXML
    private TextArea txtActons;
    
    
    @FXML
    private Button canFin;
    
    
    private static final String IS_OPEN_FILE = "config/isOpen.txt"; // Relative path to isOpen file
    private static final String DB_CONFIG_FILE = "config/dbConfig.txt"; // Relative path to dbConfig file

    private static Scene scene;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    
    
    
    
    private String clientName;
    private String assessmentNo;
    
      private String ci;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         setClient();
        
             loadDatabaseConfig();
       prog.setVisible(false);
        
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


    
    
      @FXML
    private void canFin(ActionEvent event) {
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
    }
    
    @FXML
    private void genCP(ActionEvent event) {
        
         genCP.setDisable(true);
          prog.setVisible(true);
           txtADL.setDisable(true);
                  txtActons.setDisable(true);
                  careplan.setDisable(true);
          
          
  // Show the progress indicator
        prog.setVisible(true);

        // Create a background task
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Simulate long-running task (e.g., 10 steps with delays)
                for (int i = 0; i <= 10; i++) {
                    // Update progress (from 0 to 1)
                    updateProgress(i, 10);

                    // Simulate work by sleeping
                    Thread.sleep(500); // 500ms delay between each step
                }
                return null;
            }

            @Override
            protected void succeeded() {
                // Hide the progress indicator once the task is complete
                prog.setVisible(false);
                txtADL.setDisable(false);
                  txtActons.setDisable(false);
                  careplan.setDisable(false);
                  
                  Platform.runLater(() -> {
                              Alert assessmentStartedAlert = new Alert(Alert.AlertType.INFORMATION);
    assessmentStartedAlert.setTitle("Warning");
    assessmentStartedAlert.setHeaderText(null);
    assessmentStartedAlert.setContentText("This is an Assessment-based Care Plan\n You can update or edit the details before confirming.");
    assessmentStartedAlert.showAndWait();
    return;
                    });
                  
            }
        };

        // Bind the progress indicator's progress to the task's progress
        prog.progressProperty().bind(task.progressProperty());

        // Run the task on a background thread
        Thread thread = new Thread(task);
        thread.setDaemon(true); // Ensure the thread closes when the application exits
        thread.start();
          
          confirmBtn.setDisable(false); 
          
          
          

        // Generate ADL Goals and append dental information
        generateADLAndDentalInfo();
        
        

        
       
    }

private void generateADLAndDentalInfo() {
    
      
    
    StringBuilder adlText = new StringBuilder();
    StringBuilder actionText = new StringBuilder();  // New StringBuilder for action statements

    // Ensure text wrapping in the TextArea
    txtADL.setWrapText(true);
    txtActons.setWrapText(true); 

    String hygiene = "assist"; // Assuming default hygiene action, can be updated based on fetched data

    // SQL Query for clienthygiene
    String queryHygiene = "SELECT * FROM clienthygiene WHERE assessmentID = '" + assessmentNo + "';";

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(queryHygiene)) {

        if (rs.next()) {
            String BPGLevel = rs.getString("bpgRequirement");
            String bathing = rs.getString("bathing").equalsIgnoreCase("yes") ? "can" : "cannot";
            String scrub = rs.getString("bodyb").equalsIgnoreCase("yes") ? "can" : "cannot";
            String grooming = rs.getString("groominScrug").equalsIgnoreCase("yes") ? "able" : "unable";
            String showering = rs.getString("showerIntervals").equalsIgnoreCase("daily") ? "daily" : "2-3 times per week";
            String showerTime = rs.getString("showerPrefDay").equalsIgnoreCase("AM") ? "morning (AM)" : "evening (PM)";
            String careReq = rs.getString("showerCarerGender").equalsIgnoreCase("No") ? "does not require a caregiver" : "requires a caregiver";
            String carerGenderPref = rs.getString("showerCarerGender").equalsIgnoreCase("No") ? "no one to assist them" : rs.getString("showerCarerGender");
            String dressingReq = rs.getString("dressingRequirement").equalsIgnoreCase("Independent") ? "no assistance" : "full physical assistance";
            String dressing = rs.getString("dressing").equalsIgnoreCase("yes") ? "can" : "cannot";
            String undress = rs.getString("undressing").equalsIgnoreCase("yes") ? "can" : "cannot";
            String socks = rs.getString("socks").equalsIgnoreCase("yes") ? "can" : "cannot";
            String unsocks = rs.getString("shoes").equalsIgnoreCase("yes") ? "can" : "cannot";
            String timePreference = rs.getString("showerPrefDay").equalsIgnoreCase("AM") ? "morning (AM)" : "evening (PM)";
            String assistancePref = rs.getString("showerCarerGender").equalsIgnoreCase("No") ? "no one to assist them" : rs.getString("showerCarerGender");

            adlText.append("HYGIENE\n")
                    .append(clientName).append(" requires ").append(BPGLevel).append(" for bathing, grooming, dressing, and undressing tasks. ")
                    .append(clientName).append(" ").append(bathing).append(" walk to the bathroom, and ").append(scrub).append(" scrub with a sponge or brush. ")
                    .append(clientName).append(" is also ").append(grooming).append(" to groom themselves and prefers to shower ").append(showering)
                    .append(", preferably in the ").append(showerTime).append(". ")
                    .append(clientName).append(" ").append(careReq).append(" and has a preference for ").append(carerGenderPref).append(". ")
                    .append("When it comes to dressing and undressing, ").append(clientName).append(" also requires ").append(dressingReq).append(". ")
                    .append(clientName).append(" ").append(dressing).append(" dress themselves. They ").append(undress).append(" undress themselves. ")
                    .append("Similarly, they ").append(socks).append(" put on socks and shoes. They ").append(unsocks).append(" take off socks and shoes. ")
                    .append("Assistance is preferred in the ").append(timePreference).append(", and ").append(clientName)
                    .append(" requires a caregiver for these tasks, with a preference for ").append(assistancePref).append(".");

            // Additional actions based on hygiene assessment
            actionText.append("The case manager should review and monitor ").append(clientName).append("'s ADL requirements.\n")
                      .append("It is recommended that support staff be arranged through the CSO to assist ").append(clientName).append(" with bathing, grooming, and dressing tasks.\n")
                      .append("The assistance must be provided in accordance with ").append(clientName).append("'s preferences, particularly ensuring that their dignity and independence are maintained.\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // SQL Query for clientdental
    String queryDental = "SELECT * FROM clientdental WHERE assessmentID = ?;";

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(queryDental)) {

        pstmt.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String oralHygieneRequirement = rs.getString("oralHygieneRequirement");
            String holdToothbrush = rs.getString("holdToothbrush").equalsIgnoreCase("yes") ? "can" : "cannot";
            String toothPasteSq = rs.getString("toothPasteSq").equalsIgnoreCase("yes") ? "can" : "cannot";
            String mouthRinse = rs.getString("mouthRinse").equalsIgnoreCase("yes") ? "able" : "unable";
            String mouthIssues = rs.getString("mouthIssues");
            String teethForms = rs.getString("teethForms");
            String dentistVisit = rs.getString("dentistVisit");
            String lastVisit = rs.getString("lastVisit");
            String dentistName = rs.getString("dentistName");

            String mouthCondition = mouthIssues != null ? mouthIssues : "no teeth or mouth issues";
            String teethCondition = teethForms != null ? teethForms : "no specific dental condition";

            adlText.append("\n ")
                    .append(clientName).append(" requires ").append(oralHygieneRequirement).append(" for oral hygiene. ")
                    .append("They ").append(holdToothbrush).append(" hold a toothbrush. They ").append(toothPasteSq)
                    .append(" squeeze toothpaste by themselves. They are ").append(mouthRinse)
                    .append(" to rinse their mouth without assistance. ")
                    .append(clientName).append(" has ").append(mouthCondition).append(" and has ").append(teethCondition).append(". ")
                    .append("Their last dentist visit was on ").append(lastVisit != null ? lastVisit : "unknown").append(", and their next scheduled visit is on ")
                    .append(dentistVisit != null ? dentistVisit : "unknown").append(". ")
                    .append(clientName).append("'s dentist name and contact details are\n ").append(dentistName != null ? dentistName : "unknown").append(".");

            // Additional actions based on dental assessment
              actionText.append("\n")
                      .append("It is important to monitor ").append(clientName).append("'s oral hygiene.\n")
                      .append("Regular encouragement and supervision should be provided to ensure proper dental care, particularly if ").append(clientName)
                      .append(" is ").append(holdToothbrush).append(" hold a toothbrush and ").append(toothPasteSq).append(" squeeze toothpaste on their own.\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // SQL Query for clienttoileting
    String queryToileting = "SELECT * FROM clienttoileting WHERE assessmentID = ?;";

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(queryToileting)) {

        pstmt.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String toiletingRequirement = rs.getString("toiletingRequirement");
            String walkToilet = rs.getString("walkToilet").equalsIgnoreCase("yes") ? "able" : "unable";
            String pantsOff = rs.getString("pantsOff").equalsIgnoreCase("yes") ? "can" : "cannot";
            String toiletSitting = rs.getString("toiletSitting").equalsIgnoreCase("yes") ? "able" : "unable";
            String pWashing = rs.getString("pWashing").equalsIgnoreCase("yes") ? "able" : "unable";
            String pDrying = rs.getString("pDrying").equalsIgnoreCase("yes") ? "able" : "unable";
            String continenceRequirement = rs.getString("continenceRequirement");
            String setupPads = rs.getString("setupPads").equalsIgnoreCase("yes") ? "can" : "cannot";
            String pWipeDry = rs.getString("pWipeDry").equalsIgnoreCase("yes") ? "can" : "cannot";
            String continenceNeed = rs.getString("continenceNeed") != null ? rs.getString("continenceNeed") : "unknown";
            String size = rs.getString("size") != null ? rs.getString("size") : "unknown";
            String continenceFund = rs.getString("continenceFund") != null ? rs.getString("continenceFund") : "unknown";

            adlText.append("\n ")
                    .append(clientName).append(" requires ").append(toiletingRequirement).append(" for toileting. ")
                    .append("They are ").append(walkToilet).append(" to walk to the toilet, ").append(pantsOff)
                    .append(" put down underpants or pads, and ").append(toiletSitting)
                    .append(" sit on the toilet by themselves. ")
                    .append("Additionally, ").append(clientName).append(" is ").append(pWashing)
                    .append(" to wash and ").append(pDrying).append(" to dry their perineal area independently. ")
                    .append("For continence needs, ").append(clientName).append(" also requires ").append(continenceRequirement).append(". They ")
                    .append(setupPads).append(" put on or remove pads, and ").append(pWipeDry)
                    .append(" wipe and dry their perineal area on their own. ")
                    .append(clientName).append("'s preferred brand of incontinence pads is ").append(continenceNeed).append(", and the size is ")
                    .append(size).append(". Their continence aid is funded by ").append(continenceFund).append(".");

            // Additional actions based on toileting assessment
            actionText.append("\n")
                    .append(clientName).append("'s toileting needs require regular supervision.\n")
                      .append("It is recommended that ").append(clientName).append(" be provided with assistance for continence management, particularly in placing or removing incontinence pads.\n")
                      .append("Care staff should ensure that toileting tasks are performed with dignity and respect.\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
    
    // SQL Query for clientMobility
    String queryMobility = "SELECT * FROM clientMobility WHERE assessmentID = ?;";

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(queryMobility)) {

        pstmt.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            // Fetching data from result set with null checks
            String levelOfAssistance = rs.getString("mobilityRequirement") != null ? rs.getString("mobilityRequirement") : "no assistance";
            String sitToStand = rs.getString("tStand").equalsIgnoreCase("yes") ? "can" : "cannot";
            String standToSit = rs.getString("tSit").equalsIgnoreCase("yes") ? "can" : "cannot";
            String sitToLying = rs.getString("tLying").equalsIgnoreCase("yes") ? "can" : "cannot";
            String lyingToSit = rs.getString("tSitting").equalsIgnoreCase("yes") ? "can" : "cannot";
            String walking = rs.getString("walking").equalsIgnoreCase("yes") ? "can" : "cannot";
            String carRide = rs.getString("carRide").equalsIgnoreCase("yes") ? "can" : "cannot";
            String useStairs = rs.getString("useStairs").equalsIgnoreCase("yes") ? "can" : "cannot";
            String mobilityAids = rs.getString("mobilityAids") != null ? rs.getString("mobilityAids") : "no aids";
            String lifterRequirement = rs.getString("lifterRequirement").equalsIgnoreCase("yes") ? "requires" : "does not require";
            String fallHistory = rs.getString("fallHistory").equalsIgnoreCase("yes") ? "has" : "does not have";
            String fallDetails = rs.getString("fallDets") != null ? rs.getString("fallDets") : "no details available";

            // Mapping mobility aids to the correct description
            String mobilityAidDescription;
            switch (mobilityAids.toLowerCase()) {
                case "4wheel":
                    mobilityAidDescription = "4-wheel walking frame";
                    break;
                case "scooter":
                    mobilityAidDescription = "mobility scooter";
                    break;
                case "stick":
                    mobilityAidDescription = "walking stick";
                    break;
                case "canes":
                    mobilityAidDescription = "canes";
                    break;
                case "grabbers":
                    mobilityAidDescription = "reachers and grabbers";
                    break;
                case "wheelchair":
                    mobilityAidDescription = "wheelchair";
                    break;
                default:
                    mobilityAidDescription = "no mobility aids";
                    break;
            }

            // Constructing the assessment text
            adlText.append("\n\nMOBILITY\n")
                        .append(clientName).append(" requires ").append(levelOfAssistance).append(" for toileting. ")
                        .append("They ").append(sitToStand).append(" transfer from sitting to standing. ")
                        .append("They ").append(standToSit).append(" transfer from standing to sitting. ")
                        .append(clientName).append(" ").append(sitToLying).append(" transfer from sitting to lying. ")
                        .append("They ").append(lyingToSit).append(" transfer from lying to sitting. ")
                        .append(clientName).append(" ").append(walking).append(" walk independently. ")
                        .append("They ").append(carRide).append(" get into and out of a car. ")
                        .append("Finally, ").append(clientName).append(" ").append(useStairs).append(" use stairs.\n")
                        .append("The client uses ").append(mobilityAidDescription).append(" as aids for mobility. ")
                        .append(clientName).append(" ").append(lifterRequirement).append(" a mechanical lifter and ")
                        .append(clientName).append(" ").append(fallHistory).append(" a history of falls. ")
                        .append("Details of their last fall include ").append(fallDetails).append(".\n");

            // Constructing mobility actions based on the mobility assessment
            actionText.append("\nThe case manager should monitor ").append(clientName).append("'s mobility and toileting needs.\n")
                              .append("Support staff should be assigned to assist ").append(clientName).append(" with transfers (e.g., sitting to standing, lying to sitting) as needed.\n")
                              .append(clientName).append(" may require ").append(levelOfAssistance).append(" for toileting and the use of mobility aids such as ").append(mobilityAidDescription).append(".\n")
                              .append("It is recommended to regularly evaluate the safety of transfers to prevent falls, especially given ").append(clientName).append("'s ").append(fallHistory).append(" fall history.\n")
                              .append("Details of the last fall should be reviewed to implement preventive measures and ensure ").append(clientName).append("'s safety during mobility tasks.\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // SQL Query for otherMobility
    String queryOtherMobility = "SELECT * FROM otherMobility WHERE assessmentID = ?;";

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(queryOtherMobility)) {

        pstmt.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            // Fetching data from result set with null checks
            String exerciseStatus = rs.getString("exercise").equalsIgnoreCase("yes") ? "is" : "is not";
            String exerciseDets = rs.getString("exerciseDets") != null ? rs.getString("exerciseDets") : "No details provided";
            String alliedHealthRef = rs.getString("alliedHealthRef").equalsIgnoreCase("yes") ? "requires" : "does not require";
            String unsteady = rs.getString("unsteady").equalsIgnoreCase("yes") ? "unsteady" : "steady";
            String stooped = rs.getString("stooped").equalsIgnoreCase("yes") ? "stooped over" : "upright";
            String leaning = rs.getString("leaning").equalsIgnoreCase("yes") ? "leaning to one side" : "maintaining balance";
            String breathIssue = rs.getString("breathIssue").equalsIgnoreCase("yes") ? "does" : "does not";
            String breathIssueDets = rs.getString("breathIssueDets") != null ? rs.getString("breathIssueDets") : "";
            String commsRequirement = rs.getString("commsRequirement") != null ? rs.getString("commsRequirement") : "no specific limitation";
            String hAidsFitting = rs.getString("hAidsFitting").equalsIgnoreCase("yes") ? "able" : "unable";
            String battChange = rs.getString("battChange").equalsIgnoreCase("yes") ? "able" : "unable";

            // Constructing the exact essay-style sentence structure for Other Mobility
            adlText.append("\n")
                        .append(clientName).append(" ").append(exerciseStatus).append(" exercising. ")
                        .append("They are doing ").append(exerciseDets).append(". ")
                        .append("They ").append(alliedHealthRef).append(" a referral to Allied Health Services such as physiotherapy, hydrotherapy, or occupational therapy.\n")
                        .append("In terms of mobility, ").append(clientName).append(" is ").append(unsteady).append(", ")
                        .append(stooped).append(", and ").append(leaning).append(". ")
                        .append(clientName).append(" ").append(breathIssue).append(" have trouble breathing")
                        .append(breathIssue.equals("does") && !breathIssueDets.isEmpty() ? " with noted breathing issues including " + breathIssueDets + "." : ". ")
                        .append("\nRegarding communication, ").append(clientName).append(" has ").append(commsRequirement).append(". ")
                        .append(clientName).append(" is ").append(hAidsFitting).append(" to fit their hearing aids and ")
                        .append(battChange).append(" to check or change batteries.");

            // Constructing actions based on other mobility assessment
            actionText.append("\nIt is recommended that ").append(clientName).append(" continues exercising if capable, with an emphasis on ").append(exerciseDets).append(".\n")
                              .append(clientName).append(" may benefit from a referral to Allied Health services such as physiotherapy or occupational therapy.\n")
                              .append("The care team should monitor ").append(clientName).append("'s unsteadiness, stooped posture, and tendency to lean, providing necessary support to avoid falls or injury.\n")
                              .append("Breathing issues should be carefully monitored, particularly if ").append(clientName).append(" reports difficulties such as ").append(breathIssueDets).append(".\n")
                              .append("Communication should also be supported, ensuring ").append(clientName).append(" can effectively use hearing aids and change batteries as required.\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
    
       // NUTRITIONAL NEEDS
    String queryNutrition = "SELECT * FROM clientNutrition WHERE assessmentID = ?;";
    StringBuilder nutritionActionText = new StringBuilder();

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(queryNutrition)) {

        pstmt.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            // Extract nutrition-related data from the ResultSet
            String nutritionRequirement = rs.getString("nutritionRequirement");
            String mealPrep = rs.getString("mealPrep");
            String foodService = rs.getString("foodService");
            String swallowIssue = rs.getString("swallowIssue");
            String weightLoss = rs.getString("weightLoss");
            String needSupplement = rs.getString("needSupplement");
            String useCutlery = rs.getString("useCutlery");
            String dietaryRequirement = rs.getString("dietaryRequirement");
            String mealSize = rs.getString("mealSize");
            String dietRef = rs.getString("dietRef");

            // Use conditional logic to set descriptive text based on nutrition data
            String nutritionAssistance = "Independent".equalsIgnoreCase(nutritionRequirement) ? "no assistance" : "encouragement/supervision/full physical assistance";
            String mealAssistance = "Yes".equalsIgnoreCase(mealPrep) ? "require" : "do not require";
            String mealService = "Yes".equalsIgnoreCase(foodService) ? "require" : "do not require";
            String swallowDifficulty = "Yes".equalsIgnoreCase(swallowIssue) ? "has" : "does not have";
            String weightLossStatus = "Yes".equalsIgnoreCase(weightLoss) ? "have" : "have not";
            String supplements = "Yes".equalsIgnoreCase(needSupplement) ? "do" : "do not";
            String cutleryUse = "Yes".equalsIgnoreCase(useCutlery) ? "uses" : "does not use";
            String dietaryNeeds = "Yes".equalsIgnoreCase(dietaryRequirement) ? "has" : "does not have";
            String mealSizePreference;
            switch (mealSize.toLowerCase()) {
                case "small":
                    mealSizePreference = "small";
                    break;
                case "medium":
                    mealSizePreference = "medium";
                    break;
                case "large":
                    mealSizePreference = "large";
                    break;
                default:
                    mealSizePreference = "not specified";
            }
            String nutritionReferral = "Yes".equalsIgnoreCase(dietRef) ? "required" : "not required";

            // Construct the output text for `txtNutrition`
          
            adlText.append("\n\nNUTRITION\n")
                    .append(clientName + " requires " + nutritionAssistance + " for their nutritional needs. " +
                "They " + mealAssistance + " assistance with nutrition, meal preparation, or when eating or drinking. " +
                "They " + mealService + " a meal delivery service. " +
                clientName + " " + swallowDifficulty + " swallowing difficulties when eating or drinking. " +
                "They " + weightLossStatus + " lost weight in the past year and " + supplements + " take any meal or nutritional supplements. " +
                clientName + " " + cutleryUse + " specialty cutlery and " + dietaryNeeds + " any special dietary needs. " +
                "Their preferred meal size is " + mealSizePreference + ". " +
                "A referral for further or specialist nutrition assessment or services is " + nutritionReferral + ".");

            // Constructing action text based on the nutrition assessment
            actionText.append("\nThe case manager should review and monitor ").append(clientName).append("'s nutritional needs.\n")
                               .append("It is recommended that support staff assist with meal preparation and ensure that nutritional intake is sufficient.\n")
                               .append(clientName).append(" should be monitored for ").append(swallowDifficulty.equals("has") ? "swallowing difficulties, " : "")
                               .append(weightLossStatus.equals("have") ? "any further weight loss, " : "maintaining a stable weight, ")
                               .append("and ensuring that dietary needs are met based on ").append(clientName).append("'s preferences for meal size and dietary requirements.\n")
                               .append("Regular follow-ups should be conducted, and ").append(clientName).append("'s nutritional needs should be reassessed to determine if a referral for specialist nutritional services is ").append(nutritionReferral).append(".\n");

            // Setting the action text to txtNutritionActions
//            txtNutritionActions.setWrapText(true);  // Enable text wrapping
//            txtNutritionActions.setText(nutritionActionText.toString());
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
    
     // Skin Integrity and Podiatry
    String querySkin = "SELECT * FROM clientskin WHERE assessmentID = ?;";
    StringBuilder skinActionText = new StringBuilder();

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(querySkin)) {

        pstmt.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            // Extracting data from the result set
            String skinTear = rs.getString("skinTear");
            String skinProblem = rs.getString("skinProblem");
            String useSkinProduct = rs.getString("useSkinProduct");
            String prescribed = rs.getString("prescribed");
            String skinProductDets = rs.getString("skinProductDets");
            String woundDress = rs.getString("woundDress");
            String assistReq = rs.getString("assistReq");
            String pressureInjury = rs.getString("pressureInjury");
            String injuryDets = rs.getString("injuryDets");
            String attendPodiatrist = rs.getString("attendPodiatrist");
            String podiatrist = rs.getString("podiatrist");
            String visitFreq = rs.getString("visitFreq");

            // Conditional text for action statements
            String skinTearText = "yes".equalsIgnoreCase(skinTear) ? "has" : "does not have";
            String skinProblemText = "yes".equalsIgnoreCase(skinProblem) ? "have" : "do not have";
            String useSkinProductText = "yes".equalsIgnoreCase(useSkinProduct) ? "use" : "do not use";
            String prescribedText = "yes".equalsIgnoreCase(prescribed) ? "use prescription/over-the-counter" : "do not use";
            String woundDressText = "yes".equalsIgnoreCase(woundDress) ? "has" : "does not have";
            String assistReqText = "yes".equalsIgnoreCase(assistReq) ? "requires" : "does not require";
            String pressureInjuryText = "yes".equalsIgnoreCase(pressureInjury) ? "have" : "do not have";
            String attendPodiatristText = "yes".equalsIgnoreCase(attendPodiatrist) ? "does" : "does not";

            // Constructing the essay-style output for txtSkin
            String skinProductsUsed = (skinProductDets != null && !skinProductDets.isEmpty()) ? skinProductDets : "(N/A)";
            String injuryDetails = (injuryDets != null && !injuryDets.isEmpty()) ? injuryDets : "(N/A)";
            String podiatristDetails = (podiatrist != null && !podiatrist.isEmpty()) ? podiatrist : "(N/A)";
            String visitFrequency = (visitFreq != null && !visitFreq.isEmpty()) ? visitFreq : "(N/A)";

         adlText.append("\n\nSKIN INTEGRITY\n")
                    .append(clientName + " " + skinTearText + " any skin tear, wound, or pressure injury. " +
                "They " + skinProblemText + " skin problems and " + useSkinProductText + " " + prescribedText + " skin care products, which include " + skinProductsUsed + ". \n" +
                clientName + " " + woundDressText + " any wound dressings, and " + assistReqText + " assistance to maintain them. " +
                "They " + pressureInjuryText + " pressure injuries, with a history of pressure injury risks including " + injuryDetails + ". " +
                "\nA podiatrist " + attendPodiatristText + " attend to " + clientName + "'s foot care. " +
                "The podiatrist's name and contact details are " + podiatristDetails + ", and the visit frequency, including the last visit date, is " + visitFrequency + "."
            );

            // Constructing action text for skin and podiatry care based on the assessment
            actionText.append("\nThe case manager should monitor and review ").append(clientName).append("'s skin condition regularly.\n")
                          .append("It is recommended that support staff assist ").append(clientName)
                          .append(" with applying prescribed skin care products and monitor for any changes in skin integrity.\n")
                          .append("Any wounds or dressings should be inspected frequently, and care staff should ensure proper maintenance, especially if assistance is required.\n")
                          .append("Pressure injury risks should be evaluated regularly to prevent any new injuries, and existing risks, if any, should be closely monitored.\n")
                          .append("Additionally, ").append(clientName).append("'s podiatry needs should be addressed, with follow-ups scheduled according to the podiatrist's recommendations, with attention given to ").append(visitFrequency).append(".\n");

    
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
    
     // Mental Health Assessment Query
    String queryMentalHealth = "SELECT * FROM clientMental WHERE assessmentID = ?;";
    StringBuilder mentalActionText = new StringBuilder();

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(queryMentalHealth)) {

        pstmt.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            // Fetching mental health data
            String cognitiveImpairment = rs.getString("cognitiveImpairment");
            String cognitiveAssessment = rs.getString("cognitiveAssessment");
            String memoryLoss = rs.getString("memoryLoss");
            String depression = rs.getString("depression");
            String anxiety = rs.getString("anxiety");
            String schizophrenia = rs.getString("schizophrenia");
            String otherMental = rs.getString("otherMental");

            // Creating essay-style text based on the data
            String cognitiveImpairmentText = cognitiveImpairment.equalsIgnoreCase("yes") ? "has" : "has not";
            String cognitiveAssessmentText = cognitiveAssessment.equalsIgnoreCase("yes") ? "require" : "do not require";
            String memoryLossText = memoryLoss.equalsIgnoreCase("yes") ? "is" : "is not";
            String depressionText = depression.equalsIgnoreCase("yes") ? "have" : "have not";
            String anxietyText = anxiety.equalsIgnoreCase("yes") ? "have" : "have not";
            String schizophreniaText = schizophrenia.equalsIgnoreCase("yes") ? "have" : "have not";
            String otherMentalText = otherMental.equalsIgnoreCase("yes") ? "has" : "has not";

       
             adlText.append("\n\nMENTAL HEALTH\n")
                    .append(clientName + " " + cognitiveImpairmentText + " been diagnosed with Cognitive Impairment (e.g., Dementia). " +
                "They " + cognitiveAssessmentText + " a cognitive assessment. " +
                clientName + " " + memoryLossText + " experiencing memory loss, either short-term or long-term. " +
                "They " + depressionText + " been diagnosed with depression. " +
                "\nThey " + anxietyText + " been diagnosed with anxiety. " +
                "They " + schizophreniaText + " been diagnosed with schizophrenia. " +
                "Additionally, " + clientName + " " + otherMentalText + " been diagnosed with other mental health conditions."
            );

            // Constructing action text based on the mental health assessment
              actionText.append("\nThe case manager should regularly review ").append(clientName).append("'s mental health status.\n")
                            .append("It is recommended that cognitive assessments be performed if ").append(clientName).append(" shows signs of cognitive impairment, memory loss, or other cognitive changes.\n")
                            .append("Support staff should provide regular emotional support and monitor for signs of depression, anxiety, or other mental health conditions, with referral to mental health services as needed.\n")
                            .append(clientName).append("'s care plan should include appropriate interventions to manage anxiety and depression symptoms, and regular check-ins should be scheduled to assess their mental well-being.\n")
                            .append("If ").append(clientName).append(" has been diagnosed with schizophrenia or other mental health disorders, it is important to follow up with specialists for regular treatment reviews and adjustments to care plans as necessary.\n");

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
    
    
    
    
  // Medication and Pain Management Query
    String queryMedication = "SELECT * FROM clientMedication WHERE assessmentID = ?;";
    //StringBuilder actionText = new StringBuilder();  // StringBuilder for action statements

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(queryMedication)) {

        pstmt.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            // Fetching medication and pain management data
            String medicationRequirement = rs.getString("medicationRequirement") != null ? rs.getString("medicationRequirement") : "no assistance";
            String needReminder = rs.getString("needReminder");
            String needPrompt = rs.getString("needPrompt");
            String medAdmin = rs.getString("medAdmin");
            String medPickup = rs.getString("medPickup");
            String takingPresc = rs.getString("takingPresc");
            String medsMismanagement = rs.getString("medsMismanagement");
            String medPep = rs.getString("medPep") != null ? rs.getString("medPep") : "original packaging";
            String medInterval = rs.getString("medInterval") != null ? rs.getString("medInterval") : "regular intervals";
            String specialMed = rs.getString("specialMed");
            String smDets = rs.getString("smDets") != null ? rs.getString("smDets") : "N/A";
            String supplement = rs.getString("supplement");
            String bloodMonitorAssist = rs.getString("bloodMonitorAssist");
            String injectAssist = rs.getString("injectAssist");

            // Constructing the formatted essay-style text for medication details
            String assistanceNeeded = (needReminder.equalsIgnoreCase("yes") ? "reminding, " : "") +
                                      (needPrompt.equalsIgnoreCase("yes") ? "prompting, " : "") +
                                      (medAdmin.equalsIgnoreCase("yes") ? "medication administration, " : "") +
                                      (medPickup.equalsIgnoreCase("yes") ? "picking up medications from the pharmacy" : "");

            assistanceNeeded = assistanceNeeded.replaceAll(", $", "");  // Remove trailing comma

           // txtMedication.setWrapText(true);  // Enable text wrapping

            adlText.append("\n\nPAIN MEDICATION AND MANAGEMENT\n")
                    .append(clientName + " requires " + medicationRequirement + " for medication and pain management. " +
                "They need assistance with " + (assistanceNeeded.isEmpty() ? "no specific tasks" : assistanceNeeded) + ".\n" +
                clientName + " " + (takingPresc.equalsIgnoreCase("yes") ? "is" : "is not") + " taking prescribed medication and " +
                (medsMismanagement.equalsIgnoreCase("yes") ? "has" : "does not have") + " a history of medication mismanagement. " +
                "They take their medications using " + medPep + " and the medications are taken " + medInterval + ".\n" +
                clientName + " " + (specialMed.equalsIgnoreCase("yes") ? "does" : "does not") + " have special medications, such as " + smDets + ", and their medications are " +
                (medPickup.equalsIgnoreCase("yes") ? "delivered/picked up" : "self-administered") + ".\n" +
                clientName + " " + (supplement.equalsIgnoreCase("yes") ? "does" : "does not") + " take vitamins and supplements. " +
                "They " + (bloodMonitorAssist.equalsIgnoreCase("yes") ? "do" : "do not") + " require assistance with blood sugar monitoring and " +
                (injectAssist.equalsIgnoreCase("yes") ? "do" : "do not") + " require assistance with injections."
            );

            // Building the action text for txtMedicationActions
            actionText.append("\nThe case manager should review ").append(clientName).append("'s medication management needs regularly.\n")
                      .append("It is recommended that ").append(clientName).append(" be provided with reminders or prompts if necessary, particularly if ").append(clientName)
                      .append(" has a history of medication mismanagement.\n")
                      .append("If medication administration is required, support staff should ensure that medications are taken according to prescribed intervals.\n")
                      .append("If ").append(clientName).append(" takes special medications, such as ").append(smDets)
                      .append(", regular check-ins should be made to monitor the effectiveness of the treatment and ensure no adverse reactions.\n")
                      .append("If assistance with blood sugar monitoring or injections is required, appropriate healthcare staff should be engaged to assist in monitoring or administering injections.\n");

//            // Set action text to txtMedicationActions
//            txtMedicationActions.setWrapText(true);
//            txtMedicationActions.setText(actionText.toString());
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
    
    
     // Pain Management Query
    String queryPain = "SELECT * FROM clientPain WHERE assessmentID = ?;";
    //StringBuilder actionText = new StringBuilder();  // StringBuilder for action statements

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(queryPain)) {

        pstmt.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            // Fetching pain management data
            String painExp = rs.getString("painExp") != null ? rs.getString("painExp") : "no";
            String painMed = rs.getString("painMed") != null ? rs.getString("painMed") : "no";
            String painMedFreq = rs.getString("painMedFreq") != null ? rs.getString("painMedFreq") : "N/A";
            String painLevel = rs.getString("painLevel") != null ? rs.getString("painLevel") : "N/A";
            String respiteRequire = rs.getString("respiteRequire") != null ? rs.getString("respiteRequire") : "no";
            String respiteDelivery = rs.getString("respiteDelivery") != null ? rs.getString("respiteDelivery") : "N/A";

            // Constructing the formatted essay-style text for pain management
            String painExperienceText = painExp.equalsIgnoreCase("yes") ? "does" : "does not";
            String medicationText = painMed.equalsIgnoreCase("yes") ? "does" : "does not";
            String respiteText = respiteRequire.equalsIgnoreCase("yes") ? "does" : "does not";

           // txtPain.setWrapText(true);  // Enable text wrapping

            StringBuilder painText = new StringBuilder();

            // Pain experience and medication
            adlText.append("\n\n")
                    .append(clientName).append(" ").append(painExperienceText)
                    .append(" experience pain and ").append(medicationText)
                    .append(" take medication for the pain. ");

            if (painExp.equalsIgnoreCase("yes")) {
                adlText.append("The pain occurs with a frequency of ").append(painMedFreq)
                        .append(" and is at a level of ").append(painLevel).append(".");
            } else {
                adlText.append("They do not take medication for pain.");
            }

            adlText.append("\n");

            // Respite care
            adlText.append(clientName).append(" ").append(respiteText)
                    .append(" require respite care. ");

            if (respiteRequire.equalsIgnoreCase("yes")) {
                adlText.append("If respite is required, they prefer ")
                        .append(respiteDelivery.equalsIgnoreCase("in-home") ? "in-home" : "residential facility")
                        .append(" respite care.");
            }

     

            // Constructing action recommendations based on the pain data
            actionText.append("\nThe case manager should review ").append(clientName)
                      .append("'s pain management plan regularly.\n")
                      .append("If ").append(clientName).append(" experiences pain and is taking medication, it is important to monitor the effectiveness of the medication and its side effects.\n");

            if (painExp.equalsIgnoreCase("yes")) {
                 actionText.append("Ensure that pain is being controlled at an appropriate level of ").append(painLevel)
                          .append(" with medication taken ").append(painMedFreq).append(".\n");
            }

            if (respiteRequire.equalsIgnoreCase("yes")) {
                actionText.append(clientName).append(" requires respite care, and it is recommended that ")
                          .append("appropriate arrangements be made to provide ")
                          .append(respiteDelivery.equalsIgnoreCase("in-home") ? "in-home" : "residential facility")
                          .append(" respite care as per their preferences.\n");
            }

            // Set action text to txtPainActions
//            txtPainActions.setWrapText(true);
//            txtPainActions.setText(actionText.toString());
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
    
    
    
    
    
    // House Assessment Query
    String queryHousing = "SELECT * FROM clientHousing WHERE assessmentID = ?;";
//    StringBuilder actionText = new StringBuilder();  // StringBuilder for action statements

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(queryHousing)) {

        pstmt.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            // Fetching housing data
            String housingOwnership = rs.getString("housingOwnership") != null ? rs.getString("housingOwnership") : "unspecified";
            String livingSetup = rs.getString("livingSetup") != null ? rs.getString("livingSetup") : "unspecified";
            String personalAlarm = rs.getString("personalAlarm").equalsIgnoreCase("yes") ? "does" : "does not";
            String alarmCompany = rs.getString("alarmCompany") != null ? rs.getString("alarmCompany") : "N/A";
            String keyLockBox = rs.getString("keyLockBox").equalsIgnoreCase("yes") ? "does" : "does not";
            String keyLockCode = rs.getString("keyLockCode") != null ? rs.getString("keyLockCode") : "N/A";

            // Mapping ownership and living setup to descriptive text
            String housingText;
            switch (housingOwnership.toLowerCase()) {
                case "own":
                    housingText = clientName + " owns their home.";
                    break;
                case "rent":
                    housingText = clientName + " rents their home.";
                    break;
                case "government housing":
                    housingText = clientName + " lives in government housing.";
                    break;
                case "owned by family":
                    housingText = clientName + "'s house is owned by family.";
                    break;
                default:
                    housingText = clientName + "'s housing situation is unspecified.";
            }

            String livingSetupText;
            switch (livingSetup.toLowerCase()) {
                case "alone":
                    livingSetupText = "They live alone.";
                    break;
                case "with carer":
                    livingSetupText = "They live with a carer.";
                    break;
                case "with family":
                    livingSetupText = "They live with family.";
                    break;
                default:
                    livingSetupText = "Their living setup is unspecified.";
            }

            // Constructing the final essay-style text
            StringBuilder finalHousingText = new StringBuilder();
            adlText.append("\n\nHOUSING ARRANGEMENT\n")
                    .append(housingText).append(" ")
                            .append(livingSetupText).append("\n");

            // Personal alarm details
            adlText.append(clientName).append(" ").append(personalAlarm)
                            .append(" have a personal alarm.");
            if ("does".equals(personalAlarm)) {
                adlText.append(" The company details are ").append(alarmCompany).append(".\n");
            } else {
                adlText.append("\n");
            }

            // Key lock box details
            adlText.append(clientName).append(" ").append(keyLockBox)
                            .append(" have a key lock box.");
            if ("does".equals(keyLockBox)) {
                adlText.append(" The code is ").append(keyLockCode).append(".\n");
            } else {
                adlText.append("\n");
            }

            // Setting the text in the TextArea
//            txtHouse.setWrapText(true);
//            txtHouse.setText(finalHousingText.toString());

            // Constructing action recommendations based on housing data
            actionText.append("\nThe case manager should review ").append(clientName).append("'s housing arrangement to ensure it remains suitable.\n");
            actionText.append("Regular monitoring of ").append(clientName).append("'s living setup (").append(livingSetupText).append(") is recommended.\n");

            if ("does".equals(personalAlarm)) {
                actionText.append("It is recommended to periodically check that ").append(clientName).append("'s personal alarm, provided by ")
                          .append(alarmCompany).append(", is functioning properly.\n");
            }

            if ("does".equals(keyLockBox)) {
                actionText.append("Ensure that access to the key lock box is maintained and that the code is updated if needed.\n");
            }

            // Setting the action text to txtHouseActions (ensure it's properly defined in your FXML)
//            txtHouseActions.setWrapText(true);  // Assuming txtHouseActions is another TextArea
//            txtHouseActions.setText(actionText.toString());

        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
    
    
    
    // Home and Transportation Query
    String queryHome = "SELECT * FROM clientHome WHERE assessmentID = ?;";
    String queryTransport = "SELECT * FROM clientTransport WHERE assessmentID = ?;";
    StringBuilder homeContent = new StringBuilder();  // To store the home and transportation data
//    StringBuilder actionText = new StringBuilder();   // To store the action recommendations

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement pstmtHome = conn.prepareStatement(queryHome);
         PreparedStatement pstmtTransport = conn.prepareStatement(queryTransport)) {

        pstmtHome.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection
        pstmtTransport.setString(1, assessmentNo);  // Use PreparedStatement to avoid SQL injection

        // Query Home Maintenance Data
        ResultSet rsHome = pstmtHome.executeQuery();

        if (rsHome.next()) {
            String homeMaintainSupport = rsHome.getString("homeMaintainSupport") != null && rsHome.getString("homeMaintainSupport").equalsIgnoreCase("yes") ? "does" : "does not";

            // Create a list of tasks only if they're marked as "yes"
            List<String> tasks = new ArrayList<>();
            if (rsHome.getString("cleaning") != null && rsHome.getString("cleaning").equalsIgnoreCase("yes")) tasks.add("cleaning");
            if (rsHome.getString("decluttering") != null && rsHome.getString("decluttering").equalsIgnoreCase("yes")) tasks.add("decluttering");
            if (rsHome.getString("dusting") != null && rsHome.getString("dusting").equalsIgnoreCase("yes")) tasks.add("dust and damp-wiping");
            if (rsHome.getString("vacuum") != null && rsHome.getString("vacuum").equalsIgnoreCase("yes")) tasks.add("vacuuming");
            if (rsHome.getString("mop") != null && rsHome.getString("mop").equalsIgnoreCase("yes")) tasks.add("mopping");
            if (rsHome.getString("sweeping") != null && rsHome.getString("sweeping").equalsIgnoreCase("yes")) tasks.add("sweeping hard surface floors");
            if (rsHome.getString("laundry") != null && rsHome.getString("laundry").equalsIgnoreCase("yes")) tasks.add("laundry");
            if (rsHome.getString("hangClothes") != null && rsHome.getString("hangClothes").equalsIgnoreCase("yes")) tasks.add("hanging clothes out to dry");
            if (rsHome.getString("lightGardening") != null && rsHome.getString("lightGardening").equalsIgnoreCase("yes")) tasks.add("light gardening");
            if (rsHome.getString("mowGarden") != null && rsHome.getString("mowGarden").equalsIgnoreCase("yes")) tasks.add("gardening (mowing, edging, etc.)");
            if (rsHome.getString("windowCleaning") != null && rsHome.getString("windowCleaning").equalsIgnoreCase("yes")) tasks.add("window cleaning");
            if (rsHome.getString("gutterCleaning") != null && rsHome.getString("gutterCleaning").equalsIgnoreCase("yes")) tasks.add("gutter cleaning");
            if (rsHome.getString("bedroomCleaning") != null && rsHome.getString("bedroomCleaning").equalsIgnoreCase("yes")) tasks.add("bedroom cleaning");

            adlText.append("\n\nHOME AND TRANSPORT ARRANGEMENTS\n")
                    .append(clientName).append(" ").append(homeMaintainSupport)
                    .append(" require support services for maintaining their home environment.");

            // Only include the list of tasks if there are any selected
            if (!tasks.isEmpty()) {
                adlText.append(" The areas or tasks they need assistance with include: ")
                        .append(String.join(", ", tasks)).append(".");
            }

            adlText.append("\n");

            // Action Recommendations for Home Maintenance (Descriptive and formal)
            actionText.append("\nThe case manager should review ").append(clientName).append("'s housing arrangement to ensure it remains suitable.\n");

            if (!tasks.isEmpty()) {
                actionText.append("\nIt is recommended that support services be arranged for the following tasks: ")
                         .append(String.join(", ", tasks)).append(".\n");
            }
        }

        // Query Transportation Data
        ResultSet rsTransport = pstmtTransport.executeQuery();

        if (rsTransport.next()) {
            String socialActivity = rsTransport.getString("socialActivity") != null && rsTransport.getString("socialActivity").equalsIgnoreCase("yes") ? "does" : "does not";
            String transportAssist = rsTransport.getString("transportAssist") != null && rsTransport.getString("transportAssist").equalsIgnoreCase("yes") ? "does" : "does not";
            String canDrive = rsTransport.getString("canDrive") != null && rsTransport.getString("canDrive").equalsIgnoreCase("yes") ? "still drives" : "no longer drives";

            // List of transport-related assistance
            List<String> transportTasks = new ArrayList<>();
            if (rsTransport.getString("TaxiVoucher") != null && rsTransport.getString("TaxiVoucher").equalsIgnoreCase("yes")) transportTasks.add("taxi voucher");
            if (rsTransport.getString("ShoppingAssist") != null && rsTransport.getString("ShoppingAssist").equalsIgnoreCase("yes")) transportTasks.add("shopping assist");
            if (rsTransport.getString("DocAptAssist") != null && rsTransport.getString("DocAptAssist").equalsIgnoreCase("yes")) transportTasks.add("doctor appointment assist");

            adlText.append("\n")
                    .append(clientName).append(" ").append(socialActivity)
                    .append(" require assistance accessing social and community activities. They ")
                    .append(transportAssist).append(" need transport assistance and ")
                    .append(canDrive).append(".");

            // Only include assistance if there are any selected
            if (!transportTasks.isEmpty()) {
                adlText.append(" If assistance is required, it includes ")
                        .append(String.join(", ", transportTasks)).append(".");
            }

            adlText.append("\n");

            // Action Recommendations for Transportation (Descriptive and formal)
            if (transportAssist.equals("does")) {
                actionText.append("\nThe case manager should monitor ").append(clientName).append("'s transportation needs to ensure continued access to social and community activities.\n");
                if (!transportTasks.isEmpty()) {
                    actionText.append("It is recommended to arrange the following transport assistance: ").append(String.join(", ", transportTasks)).append(".\n");
                }
            }
        }

        // Set the constructed content to the text area
//        txtHome.setText(homeContent.toString());
//
//        // Set the actions to txtHomeActions (Descriptive and formal)
//        txtHomeActions.setWrapText(true);
//        txtHomeActions.setText(actionText.toString());

    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    // Set final combined text to the txtADL TextArea
    txtADL.setText(adlText.toString());

    // Set actions to txtActons with \n in each sentence
    txtActons.setText(actionText.toString());

        
        
        
        
    }
    
    public void setCaseNo(String ci) {
        this.ci = ci;   
        csID.setText(ci);

//        String case_ID = csID.getText();

        String queryclient = "SELECT "
                + "c.clientID, "
                + "CONCAT(c.fName, ' ', c.lName) AS fullName, "
                + "c.clientMedicare, "
                + "c.clientAddress, "
                + "a.assessmentDate, "
                + "a.assessVenue, "
                + "a.caseID, "
                + "a.assessmentID "
                + "FROM clientData c "
                + "JOIN clientAssessment a ON c.clientID = a.clientID "
                + "WHERE a.caseID = '" + ci + "';";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(queryclient)) {

            if (rs.next()) {
                assessmentNo = rs.getString("assessmentID");
                clientName = rs.getString("fullName");
                System.out.println(clientName);
                
                cID.setText(rs.getString("clientID"));
                cFname.setText("Client: "+rs.getString("fullName"));
                cMedicare.setText(rs.getString("clientMedicare"));
                cAddress.setText(rs.getString("clientAddress"));
                aVenue.setText(rs.getString("assessVenue"));
               // csID.setText(rs.getString("caseID"));
                asssessID.setText(rs.getString("assessmentID"));

                String assessDate = rs.getString("assessmentDate");
                if (assessDate != null && !assessDate.isEmpty()) {
                    LocalDate assessmentDate = LocalDate.parse(assessDate);
                    aDate.setValue(assessmentDate);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    private static final float MARGIN = 50;
private static final float PAGE_WIDTH = 600 - 2 * MARGIN; // Adjust according to your page size
private static final float FONT_SIZE = 12;
private static final float LEADING = 14.5f;
     
    @FXML
private void confirmBtn(ActionEvent event) {
    // Show Yes/No confirmation alert
    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation");
    confirmationAlert.setHeaderText(null);
    confirmationAlert.setContentText("You are about to complete a Client's Care Plan. Editing will no longer be available upon confirmation. Do you want to proceed?");
    
    // Show the dialog and wait for user response
    Optional<ButtonType> result = confirmationAlert.showAndWait();
    
    // Proceed if "Yes" was clicked
    if (result.isPresent() && result.get() == ButtonType.OK) {
        String goals = capitalizeAndFormat(txtADL.getText());
        String actions = capitalizeAndFormat(txtActons.getText());

        // Create a FileChooser to select the save location and file name
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            try (PDDocument document = new PDDocument()) {
                // Create a new page
                PDPage page = new PDPage();
                document.addPage(page);

                // Create a content stream to write to the page
                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    PDType1Font font = PDType1Font.HELVETICA; // Store the font being used
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, FONT_SIZE);
                    contentStream.setNonStrokingColor(java.awt.Color.BLACK);  // Ensure java.awt.Color is used

                    // Begin the text output on the PDF
                    contentStream.beginText();
                    contentStream.setLeading(LEADING);
                    contentStream.newLineAtOffset(MARGIN, 750);

                    // Add header
                    contentStream.showText("Sample Company Home Care Provider");
                    contentStream.newLine();
                    contentStream.showText("120 Spencer St, Melbourne VIC 3000");
                    contentStream.newLine();
                    contentStream.showText("Created By: " + "Case Manager Name");
                    contentStream.newLine();
                    contentStream.showText("Date Created: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    contentStream.newLine();
                    contentStream.newLine();

                    // Add section header
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                    contentStream.showText("My Plan");
                    contentStream.newLine();

                    // Add client details
                    contentStream.setFont(font, FONT_SIZE); // Switch back to normal font
                    contentStream.showText("Client Number: " + cID.getText());
                    contentStream.newLine();
                    contentStream.showText("Client Name: " + cFname.getText());
                    contentStream.newLine();
                    contentStream.showText("Client Address: " + cAddress.getText());
                    contentStream.newLine();
                    contentStream.showText("Case Manager: " + "John Doe");
                    contentStream.newLine();
                    contentStream.showText("Assessed Level: " + "Level 2");
                    contentStream.newLine();
                    contentStream.showText("Assessment Date: " + aDate.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    contentStream.newLine();
                    contentStream.showText("Plan Review: N/A");
                    contentStream.newLine();
                    contentStream.showText("Venue: " + aVenue.getText());
                    contentStream.newLine();
                    contentStream.newLine();

                    // Add GOALS section
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, FONT_SIZE);
                    contentStream.showText("GOALS:");
                    contentStream.newLine();
                    contentStream.setFont(font, FONT_SIZE); // Use normal font for content
                    wrapText(contentStream, goals, font, FONT_SIZE, PAGE_WIDTH);
                    contentStream.newLine();

                    // Add ACTION PLAN section
                    contentStream.newLine();
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, FONT_SIZE);
                    contentStream.showText("ACTION PLAN:");
                    contentStream.newLine();
                    contentStream.setFont(font, FONT_SIZE); // Use normal font for content
                    wrapText(contentStream, actions, font, FONT_SIZE, PAGE_WIDTH);
                    contentStream.newLine();

                    // Add confirmation section
                    contentStream.newLine();
                    contentStream.newLine();
                    wrapText(contentStream, "Confirmation of the above information and the execution of care service.", font, FONT_SIZE, PAGE_WIDTH);
                    contentStream.newLine();
                    contentStream.newLine();
                    contentStream.newLine();

                    // Add signature section
                    contentStream.showText("Signature: ________________________   Date: ____________________");
                    contentStream.newLine();
                    contentStream.showText("Witness: ________________________   Date: ____________________");

                    // End text
                    contentStream.endText();
                }

                // Save the document to the selected file
                document.save(selectedFile);
                System.out.println("PDF created successfully at: " + selectedFile.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String selectedCase = csID.getText();
        
        // Construct the SQL UPDATE query
        String closeCase = "UPDATE clientcases SET assessmentStatus = 'For Budget' WHERE caseID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(closeCase)) {
            
            // Set the parameter
            preparedStatement.setString(1, selectedCase);
            
            // Execute the update query
            int clientRowsUpdated = preparedStatement.executeUpdate();

            // Check if the update was successful
            if (clientRowsUpdated > 0) {
                // Display success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Case Closed!");
                successAlert.showAndWait();
                
                
                
                  logAudit("A new care plan has been created for client " + clientName , "000000");
  
  
                
                // Optionally reload cases or update UI
                // loadMyCases();

                // Close the current window
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();

            } else {
                // Handle the case where no rows were updated
                Alert failureAlert = new Alert(Alert.AlertType.WARNING);
                failureAlert.setTitle("Update Failed");
                failureAlert.setHeaderText(null);
                failureAlert.setContentText("No case found with the provided ID.");
                failureAlert.showAndWait();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally show an error alert to the user
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Database Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("An error occurred while updating the case.");
            errorAlert.showAndWait();
        }
    }
}

    
    

    
/**
 * Capitalizes the first letter of each sentence and ensures proper line breaks.
 */
private String capitalizeAndFormat(String input) {
    String[] sentences = input.split("(?<=\\.)\\s+"); // Split by period followed by spaces
    StringBuilder formattedText = new StringBuilder();

    for (String sentence : sentences) {
        String trimmedSentence = sentence.trim();
        if (!trimmedSentence.isEmpty()) {
            formattedText.append(Character.toUpperCase(trimmedSentence.charAt(0)))
                         .append(trimmedSentence.substring(1))
                         .append("\n"); // Add new line after each sentence
        }
    }

    return formattedText.toString();
}


private void wrapText(PDPageContentStream contentStream, String text, PDType1Font font, float fontSize, float maxWidth) throws IOException {
    String[] words = text.split("\\s+");
    StringBuilder line = new StringBuilder();
    for (String word : words) {
        String potentialLine = line + (line.length() == 0 ? "" : " ") + word;
        float width = font.getStringWidth(potentialLine) / 1000 * fontSize;
        if (width > maxWidth) {
            contentStream.showText(line.toString());
            contentStream.newLine();
            line = new StringBuilder(word);
        } else {
            line.append((line.length() == 0 ? "" : " ") + word);
        }
    }
    if (line.length() > 0) {
        contentStream.showText(line.toString());
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
