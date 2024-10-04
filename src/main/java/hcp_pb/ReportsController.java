/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import hcp_pb.assessReps;
import hcp_pb.caseReps;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class ReportsController implements Initializable {

    @FXML
    private ComboBox<String> reportCombo;
    @FXML
    private RadioButton currentRB;
    @FXML
    private RadioButton specificRB;
    @FXML
    private DatePicker reportStart;
    @FXML
    private DatePicker reportEnd;

    @FXML
    private Button extractReportBtn;
    @FXML
    private Button printReportBtn;
    @FXML
    private Button genReportBtn;

    @FXML
    private Pane caseRepPane;
    @FXML
    private Pane assessmentRepPane;
    @FXML
    private Pane clientReppane;
 

    @FXML
    private TableView<caseReps> casesRepTbl;
    @FXML
    private TableColumn<caseReps, String> cName_case;
    @FXML
    private TableColumn<caseReps, Date> cDate_case;
    @FXML
    private TableColumn<caseReps, String> csoCase;
    @FXML
    private TableColumn<caseReps, String> cStat_case;

    @FXML
    private TableView<assessReps> assessmentRepTbl;
    @FXML
    private TableColumn<assessReps, String> assessName;
    @FXML
    private TableColumn<assessReps, Date> assessDate;
    @FXML
    private TableColumn<assessReps, String> assessCSO;
    @FXML
    private TableColumn<assessReps, String> assessRemarks;

    @FXML
    private TableView<clientReps> clientRepTbl;
    @FXML
    private TableColumn<clientReps, Integer> clientRepID;
    @FXML
    private TableColumn<clientReps, String> clientRepName;
    @FXML
    private TableColumn<clientReps, Date> clientRepBirth;
    @FXML
    private TableColumn<clientReps, String> clientRepLevel;
    @FXML
    private TableColumn<clientReps, String> clientRepMed;



    private LocalDate startDate;
    private LocalDate endDate;

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
        initReports();

    }

    @FXML
    private void genReportBtn(ActionEvent event) {

        genReportBtn.setDisable(true);
        extractReportBtn.setDisable(false);
        printReportBtn.setDisable(false);
        currentRB.setDisable(true);
        specificRB.setDisable(true);
    


        if ("Cases Reports".equals(reportCombo.getValue())) {
            // Call the method to load cases based on the selected radio button
            loadCaseReports();

        } else if ("Assessment Reports".equals(reportCombo.getValue())) {
            // Call the method to load cases based on the selected radio button
            loadAssessmentReports();

        } else if ("Client Reports".equals(reportCombo.getValue())) {
            // Call the method to load cases based on the selected radio button
            loadClientReports();
        
      
        }
        
        

    }

 @FXML
private void extractReportBtn(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Save PDF");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
    File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

    if (selectedFile != null) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4); // Set page size
            document.addPage(page);

            // Create a content stream for the page
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 750);
                
                // Add Sample Business Name
                contentStream.showText("Sample Company Home Care Provider");
                contentStream.newLineAtOffset(0, -20);

                // Add report title
                contentStream.showText("Report: " + reportCombo.getValue());
                contentStream.newLineAtOffset(0, -20);
                
                // Add date coverage (Assuming you have startDate and endDate fields for the report)
                String dateCoverage = "Date Coverage: " + reportStart.getValue() + " to " + reportEnd.getValue();
                contentStream.showText(dateCoverage);
                contentStream.newLineAtOffset(0, -40);
                
                String line = "----------------------------------------------------------------------------------------------------------------------------";
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -10);

                // Add table header based on selected report
                if ("Cases Reports".equals(reportCombo.getValue())) {
                    contentStream.showText("Client Name    Date Created    Assigned CSO    Status");
                    contentStream.newLineAtOffset(0, -5);
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -10);
                    
                    // Load data from casesRepTbl and add to PDF
                    for (caseReps caseEntry : casesRepTbl.getItems()) {
                        contentStream.showText(caseEntry.getCName_case() + "    " + caseEntry.getCDate_case() + "    " + caseEntry.getCsoCase() + "    " + caseEntry.getCStat_case());
                        contentStream.newLineAtOffset(0, -20);
                    }
                } else if ("Assessment Reports".equals(reportCombo.getValue())) {
                    contentStream.showText("Client Name    Assessment Date    Assigned CSO    Remarks");
                    contentStream.newLineAtOffset(0, -5);
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -10);
             
                    // Load data from assessmentRepTbl and add to PDF
                    for (assessReps assessEntry : assessmentRepTbl.getItems()) {
                        contentStream.showText(assessEntry.getAssessName() + "    " + assessEntry.getAssessDate() + "    " + assessEntry.getAssessCSO() + "    " + assessEntry.getAssessRemarks());
                        contentStream.newLineAtOffset(0, -5);
                    }
                } else if ("Client Reports".equals(reportCombo.getValue())) {
                    contentStream.showText("Client ID    Client Name    Level    Date of Birth");
                    contentStream.newLineAtOffset(0, -10);
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -20);
                 
                    // Load data from clientRepTbl and add to PDF
                    for (clientReps clientEntry : clientRepTbl.getItems()) {
                        contentStream.showText(clientEntry.getClientRepID() + "    " + clientEntry.getClientRepName() + "    " + clientEntry.getClientRepLevel() + "    " + clientEntry.getClientRepBirth());
                        contentStream.newLineAtOffset(0, -20);
                    }
                }
                contentStream.newLineAtOffset(0, -40);
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -40);
                // Add a summary section at the end of the report
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Summary:");
                contentStream.newLineAtOffset(0, -20);
                
                // Sample summary items; adjust based on your data
                contentStream.showText("Total Records: " + getTotalRecords(reportCombo.getValue()));
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Generated by: " + System.getProperty("user.name"));
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Generated on: " + java.time.LocalDate.now());
                contentStream.endText();
            }

            // Save the document
            document.save(selectedFile);
           
            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("PDF Generation");
            alert.setHeaderText(null);
            alert.setContentText("PDF generated successfully and saved to:\n" + selectedFile.getAbsolutePath());
            alert.showAndWait();
            
            handleClearTabs();
            resetRadio();
              genReportBtn.setDisable(false);
        extractReportBtn.setDisable(true);
        printReportBtn.setDisable(true);
        currentRB.setDisable(false);
        specificRB.setDisable(false);
            

        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error generating PDF.");
            alert.showAndWait();
        }
    }
}

    
    

// Helper method to count total records based on selected report
private int getTotalRecords(String reportType) {
    switch (reportType) {
        case "Cases Reports":
            return casesRepTbl.getItems().size();
        case "Assessment Reports":
            return assessmentRepTbl.getItems().size();
        case "Client Reports":
            return clientRepTbl.getItems().size();
        default:
            return 0;
    }
}



@FXML
private void printReportBtn(ActionEvent event) {
    // Confirm before printing
    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation");
    confirmationAlert.setHeaderText(null);
    confirmationAlert.setContentText("You are about to print the report. Do you want to proceed?");
    
    Optional<ButtonType> result = confirmationAlert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            // Create a VBox to hold the printable content
            VBox printLayout = new VBox();
            printLayout.setSpacing(10); // Spacing between elements

            // Add static header
            Text header = new Text("Sample Company Home Care Provider");
            header.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
            printLayout.getChildren().add(header);
            
            // Add report title
            Text reportTitle = new Text("Report: " + reportCombo.getValue());
            reportTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
            printLayout.getChildren().add(reportTitle);

            // Add date coverage
            String dateCoverage = "Date Coverage: " + reportStart.getValue() + " to " + reportEnd.getValue();
            printLayout.getChildren().add(new Text(dateCoverage));
            printLayout.getChildren().add(new Separator());

            // Add table header based on selected report
            if ("Cases Reports".equals(reportCombo.getValue())) {
                printLayout.getChildren().add(new Text("Client Name    Date Created    Assigned CSO    Status"));
                printLayout.getChildren().add(new Separator());
                
                // Load data from casesRepTbl and add to VBox
                for (caseReps caseEntry : casesRepTbl.getItems()) {
                    String entry = String.format("%-15s %-15s %-15s %-10s", 
                        caseEntry.getCName_case(), 
                        caseEntry.getCDate_case(), 
                        caseEntry.getCsoCase(), 
                        caseEntry.getCStat_case());
                    printLayout.getChildren().add(new Text(entry));
                }
            } else if ("Assessment Reports".equals(reportCombo.getValue())) {
                printLayout.getChildren().add(new Text("Client Name    Assessment Date    Assigned CSO    Remarks"));
                printLayout.getChildren().add(new Separator());
                
                // Load data from assessmentRepTbl and add to VBox
                for (assessReps assessEntry : assessmentRepTbl.getItems()) {
                    String entry = String.format("%-15s %-15s %-15s %-10s", 
                        assessEntry.getAssessName(), 
                        assessEntry.getAssessDate(), 
                        assessEntry.getAssessCSO(), 
                        assessEntry.getAssessRemarks());
                    printLayout.getChildren().add(new Text(entry));
                }
            } else if ("Client Reports".equals(reportCombo.getValue())) {
                printLayout.getChildren().add(new Text("Client ID    Client Name    Level    Date of Birth"));
                printLayout.getChildren().add(new Separator());
                
                // Load data from clientRepTbl and add to VBox
                for (clientReps clientEntry : clientRepTbl.getItems()) {
                    String entry = String.format("%-10s %-15s %-10s %-15s", 
                        clientEntry.getClientRepID(), 
                        clientEntry.getClientRepName(), 
                        clientEntry.getClientRepLevel(), 
                        clientEntry.getClientRepBirth());
                    printLayout.getChildren().add(new Text(entry));
                }
            }
            
            
             // Add a summary section at the end of the report
            printLayout.getChildren().add(new Separator());
            printLayout.getChildren().add(new Text("Summary:"));
            printLayout.getChildren().add(new Text("Total Records: " + getTotalRecords(reportCombo.getValue())));
            printLayout.getChildren().add(new Text("Generated by: " + System.getProperty("user.name")));
            printLayout.getChildren().add(new Text("Generated on: " + java.time.LocalDate.now()));
            
            

            // Print the layout
            if (job.showPrintDialog(clientRepTbl.getScene().getWindow())) {
                boolean success = job.printPage(printLayout);
                if (success) {
                    job.endJob(); // End the print job
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Print Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Printing completed successfully.");
                    successAlert.showAndWait();
                    
                           handleClearTabs();
            resetRadio();
              genReportBtn.setDisable(false);
        extractReportBtn.setDisable(true);
        printReportBtn.setDisable(true);
        currentRB.setDisable(false);
        specificRB.setDisable(false);
        
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Print Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("An error occurred while printing.");
                    errorAlert.showAndWait();
                }
            }
        }
    }
}


    private void initReports() {
        reportCombo.setItems(FXCollections.observableArrayList("Cases Reports", "Assessment Reports", "Client Reports"));
        reportCombo.getSelectionModel().selectFirst();
        //genReportBtn.setOnAction(event -> generateReport());
        resetRadio();
        DatePicks();
        initTables();

        // Set initial state of DatePickers (disabled because currentRB is selected by default)
        reportStart.setDisable(true);
        reportEnd.setDisable(true);

        reportStart.setValue(LocalDate.now());
        reportEnd.setValue(LocalDate.now());

        // Add listener to reportCombo to handle each report type separately
        reportCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            switch (newVal) {
                case "Client Reports":
                    handleClientReports();
                    handleClearTabs();
                    break;
                case "Cases Reports":
                    handleCasesReports();
                    handleClearTabs();
                    break;
                case "Assessment Reports":
                    handleClearTabs();
                    handleAssessmentReports();
                    break;
                             default:
                    resetRadio();
                    break;
            }
        });
    }
    
    private void handleClearTabs(){
                  casesRepTbl.getItems().clear();
                clientRepTbl.getItems().clear();
      assessmentRepTbl.getItems().clear();
    }

    private void handleCasesReports() {
        // Logic for Cases Reports
        resetRadio();
        DatePicks();
//        casesRepTbl.getItems().clear();
        caseRepPane.setVisible(true);
        assessmentRepPane.setVisible(false);
        clientReppane.setVisible(false);
 

    }

    private void handleAssessmentReports() {
        // Logic for Assessment Reports
        resetRadio();
        DatePicks();
        System.out.println("Assessment Reports selected");

        caseRepPane.setVisible(false);
        assessmentRepPane.setVisible(true);
        clientReppane.setVisible(false);
     
    }

    private void handleClientReports() {
        // Logic for Client Reports
        currentRB.setDisable(true);
        specificRB.setDisable(true);
        reportStart.setDisable(true);
        reportEnd.setDisable(true);
        genReportBtn.setDisable(false);
        extractReportBtn.setDisable(true);
        printReportBtn.setDisable(true);
        System.out.println("Client Reports selected");

        caseRepPane.setVisible(false);
        assessmentRepPane.setVisible(false);
        clientReppane.setVisible(true);
  
    }

    

    private void DatePicks() {
        // Add listeners to the radio buttons to handle DatePicker enabling/disabling
        currentRB.selectedProperty().addListener((obs, oldVal, isSelected) -> {
            if (isSelected) {
                reportStart.setDisable(true);
                reportEnd.setDisable(true);
            }
        });

        specificRB.selectedProperty().addListener((obs, oldVal, isSelected) -> {
            if (isSelected) {
                reportStart.setDisable(false);
                reportEnd.setDisable(false);
            }
        });

    }

    private void initTables() {
        cName_case.setCellValueFactory(new PropertyValueFactory<>("cName_case"));
        cDate_case.setCellValueFactory(new PropertyValueFactory<>("cDate_case"));
        csoCase.setCellValueFactory(new PropertyValueFactory<>("csoCase"));
        cStat_case.setCellValueFactory(new PropertyValueFactory<>("cStat_case"));

        assessName.setCellValueFactory(new PropertyValueFactory<>("assessName"));
        assessDate.setCellValueFactory(new PropertyValueFactory<>("assessDate"));
        assessCSO.setCellValueFactory(new PropertyValueFactory<>("assessCSO"));
        assessRemarks.setCellValueFactory(new PropertyValueFactory<>("assessRemarks"));

        clientRepID.setCellValueFactory(new PropertyValueFactory<>("clientRepID"));
        clientRepName.setCellValueFactory(new PropertyValueFactory<>("clientRepName"));
        clientRepBirth.setCellValueFactory(new PropertyValueFactory<>("clientRepBirth"));
        clientRepLevel.setCellValueFactory(new PropertyValueFactory<>("clientRepLevel"));
        clientRepMed.setCellValueFactory(new PropertyValueFactory<>("clientRepMed"));

    }

    private void resetRadio() {
        ToggleGroup reportsRadioGroup = new ToggleGroup();

        // Set the toggle group for both radio buttons
        specificRB.setToggleGroup(reportsRadioGroup);
        currentRB.setToggleGroup(reportsRadioGroup);

        // Set currentRB to be selected by default
        currentRB.setSelected(true);
        // Reset the selection to currentRB and disable DatePickers
        reportsRadioGroup.selectToggle(currentRB);
        currentRB.setDisable(false);
        specificRB.setDisable(false);
        reportStart.setDisable(true);
        reportEnd.setDisable(true);
        genReportBtn.setDisable(false);
        extractReportBtn.setDisable(true);
        printReportBtn.setDisable(true);
    }



    public void loadCaseReports() {

        ObservableList<caseReps> casesList = FXCollections.observableArrayList();
        String query = null;  // Declare query outside

        if (currentRB.isSelected()) {

            System.out.println("current");
            // Get data for the current month
            query = "SELECT CONCAT(c.fname, ' ', c.lname) AS fullName, "
                    + "cc.caseDate, cc.caseAssignment, cc.assessmentStatus "
                    + "FROM clientdata c JOIN clientcases cc ON c.clientid = cc.clientid "
                    + "WHERE MONTH(cc.caseDate) = MONTH(CURRENT_DATE()) "
                    + "AND YEAR(cc.caseDate) = YEAR(CURRENT_DATE())";
        } else if (specificRB.isSelected()) {
            // Get data based on the date range
            startDate = reportStart.getValue();
            endDate = reportEnd.getValue();

            // Ensure both dates are not null
            if (startDate != null && endDate != null) {
                query = "SELECT CONCAT(c.fname, ' ', c.lname) AS fullName, "
                        + "cc.caseDate, cc.caseAssignment, cc.assessmentStatus "
                        + "FROM clientdata c JOIN clientcases cc ON c.clientid = cc.clientid "
                        + "WHERE cc.caseDate BETWEEN ? AND ?";
            } else {
                // Handle case where dates are not selected
                System.out.println("Please select a valid date range.");
                return; // Exit if dates are not valid
            }
        } else {
            // Handle case where no radio button is selected
            System.out.println("Please select a report type.");
            return;
        }

        // Execute the query
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement pstmt = conn.prepareStatement(query)) {
            casesRepTbl.getItems().clear();
            
            if (specificRB.isSelected()) {
                pstmt.setDate(1, Date.valueOf(startDate));
                pstmt.setDate(2, Date.valueOf(endDate));
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String cName_case = rs.getString("fullName");
                Date cDate_case = rs.getDate("caseDate");
                String csoCase = rs.getString("caseAssignment");
                String cStat_case = rs.getString("assessmentStatus");

                caseReps caseEntry = new caseReps(cName_case, cDate_case, csoCase, cStat_case);
                casesList.add(caseEntry);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        casesRepTbl.setItems(casesList);

    }

    private void loadAssessmentReports() {

        ObservableList<assessReps> assessList = FXCollections.observableArrayList();
        String query = null;  // Declare query outside

        if (currentRB.isSelected()) {

            System.out.println("current");
            // Get data for the current month
            query = "SELECT CONCAT(c.fname, ' ', c.lname) AS fullName, "
                    + "a.assessmentDate, a.assignedCSO, cc.closingReason AS Remarks "
                    + "FROM clientdata c "
                    + "JOIN clientcases cc ON c.clientid = cc.clientid "
                    + "JOIN clientassessment a ON cc.caseID = a.caseID "
                    + "WHERE MONTH(a.assessmentDate) = MONTH(CURRENT_DATE()) "
                    + "AND YEAR(a.assessmentDate) = YEAR(CURRENT_DATE())";

        } else if (specificRB.isSelected()) {
            // Get data based on the date range
            startDate = reportStart.getValue();
            endDate = reportEnd.getValue();

            // Ensure both dates are not null
            if (startDate != null && endDate != null) {
                query = "SELECT CONCAT(c.fname, ' ', c.lname) AS fullName, "
                        + "a.assessmentDate, a.assignedCSO, cc.closingReason AS Remarks "
                        + "FROM clientdata c "
                        + "JOIN clientcases cc ON c.clientid = cc.clientid "
                        + "JOIN clientassessment a ON cc.caseID = a.caseID "
                        + "WHERE cc.caseDate BETWEEN ? AND ?";
            } else {
                // Handle case where dates are not selected
                System.out.println("Please select a valid date range.");
                return; // Exit if dates are not valid
            }
        } else {
            // Handle case where no radio button is selected
            System.out.println("Please select a report type.");
            return;
        }

        // Execute the query
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement pstmt = conn.prepareStatement(query)) {
            assessmentRepTbl.getItems().clear();
            if (specificRB.isSelected()) {
                pstmt.setDate(1, Date.valueOf(startDate));
                pstmt.setDate(2, Date.valueOf(endDate));
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String assessName = rs.getString("fullName");
                Date assessDate = rs.getDate("assessmentDate");
                String assessCSO = rs.getString("assignedCSO");
                String assessRemarks = rs.getString("Remarks");

                assessReps assessEntry = new assessReps(assessName, assessDate, assessCSO, assessRemarks);
                assessList.add(assessEntry);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        assessmentRepTbl.setItems(assessList);

    }

    private void loadClientReports() {

//        ObservableList<clientReps> cList = FXCollections.observableArrayList();
//  
        // SQL query
String query = "SELECT clientID, "
             + "CONCAT(fName, ' ', lName) AS fullName, "
             + "LevelID, clientBday, clientMedicare "
             + "FROM clientData";

try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
     Statement statement = connection.createStatement();
     ResultSet resultSet = statement.executeQuery(query)) {

    // Clear existing data
    clientRepTbl.getItems().clear();

    // Process the result set and populate the TableView
    while (resultSet.next()) {
        int clientRepID = resultSet.getInt("clientID");
        String clientRepName = resultSet.getString("fullName");
        Date clientRepBirth = resultSet.getDate("clientBday");
        String clientRepLevel = resultSet.getString("LevelID");
        String clientRepMed = resultSet.getString("clientMedicare");

        // Create a clientReps object
        clientReps clientEntry = new clientReps(clientRepID, clientRepName, clientRepBirth, clientRepLevel, clientRepMed);

        // Add the clientReps object to the TableView
        clientRepTbl.getItems().add(clientEntry);
    }
} catch (SQLException e) {
    e.printStackTrace(); // Handle exceptions appropriately
}


     

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

}
