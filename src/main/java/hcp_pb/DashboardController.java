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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
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
public class DashboardController implements Initializable {

    @FXML
    private Hyperlink helpLink;

    private String cmID;
    private int budgetCaseid;
    @FXML
    private Pane budgetPane;

    @FXML
    private TextField txtGovS;
    @FXML
    private TextField txtSuppLessC;
    @FXML
    private TextField txtSuppLessP;

    @FXML
    private TextField txtTotalA;

    @FXML
    private TextField txtMonth1;
    @FXML
    private TextField txtMonth7;

    @FXML
    private TextField txtMonth8;

    @FXML
    private Button GenBudget;

    @FXML
    private Button saveBudgetButton;

    @FXML
    private Button modBudgetButton;
    @FXML
    private Button modifButton;

    @FXML
    private TableView<budgetClient> tblBudgetClient;
    @FXML
    private TableColumn<budgetClient, Integer> bClientID;
    @FXML
    private TableColumn<budgetClient, String> bClientName;

    @FXML
    private TextField txtcname;

    @FXML
    private TextField txtCli;

    @FXML
    private TextField txtCdob;

    @FXML
    private TextField txtPby;

    @FXML
    private TextField txtFlevel;

    @FXML
    private TextField txtCdate;

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
    private Label txtCaseID;

    @FXML
    private ComboBox<String> aFundLevelCombo;

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
    private Label budgetViewer;
    @FXML
    private Pane dashboardPane;

    @FXML
    private Pane reportsPane;

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
    private TableView<BudgetEntry> tblBudget;

    @FXML
    private TableColumn<BudgetEntry, String> careDesc;
    @FXML
    private TableColumn<BudgetEntry, String> numUnits;
    @FXML
    private TableColumn<BudgetEntry, String> totalCare;

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
    private TableColumn<MyCases, String> case_Date;

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

    @FXML
    private Label activeCasesCount; // analytics
    @FXML
    private Label activeAgeingCount; //analytics
    @FXML
    private Label pendingCaseCount; //analytics
    @FXML
    private Label highLevelCount; //analytics
    @FXML
    private Label medLevelCount; //analytics
    @FXML
    private Label lowLevelCount;//analytics
    @FXML
    private PieChart weeklyCasesGraph; //analytics

//    private String outID;
    @FXML
    private AnchorPane mainAcn;

    private static final String IS_OPEN_FILE = "config/isOpen.txt"; // Relative path to isOpen file
    private static final String DB_CONFIG_FILE = "config/dbConfig.txt"; // Relative path to dbConfig file

    private static Scene scene;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDatabaseConfig();
        initTables();
        initReports();

        // loadDatabaseConfig();
        loadBudgetClientDatabase();
        loadCases();
        loadClients();
        loadMyCases();
        initClientComboBox();
        searchCasesChange();
        searchMyChange();
        searchClientChange();

        updateActiveCasesCount(); //ANAYLYTICS
        updateActiveAgeingCount();//ANAYLYTICS
        updatePendingCaseCount();//ANALYTICS
        updateHighLevelCount(); //ANALYTICS
        updateLowPriorityCount(); //ANALYTICS
        updateMedPriorityCount(); //ANALYTICS
        updateWeeklyCasesGraph(); //ANALYTICS

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
//---

        //CSO budgetclient table
        // Set up the mouse click event handler for the TableView
        tblBudgetClient.setOnMouseClicked(event -> {
            try {
                handleclientbudget(event);

            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, "Error handling row selection", ex);
            }
        });

// Select the first record if available
        if (!tblBudgetClient.getItems().isEmpty()) {
            // Select the first item in the TableView
            tblBudgetClient.getSelectionModel().selectFirst();
            try {
                // Manually trigger the row selection handler to load details
                handleclientbudget(null);
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, "Error handling initial row selection", ex);
            }
        }
        //set the status flag to 1 when the x button wasaccidentally clicked
        Platform.runLater(() -> {
            Scene scene = lowLevelCount.getScene();
            if (scene != null) {
                Stage stage = (Stage) scene.getWindow();
                if (stage != null) {
                    stage.setOnCloseRequest(event -> {

                        Alert CloseWindow = new Alert(Alert.AlertType.INFORMATION);
                        CloseWindow.setTitle("Closing the Program");
                        CloseWindow.setHeaderText(null);
                        CloseWindow.setContentText("You are about to turn off the system.");
                        CloseWindow.showAndWait();

                        logAudit(headerLbl.getText().substring(3) + " have successfully logged-in", userID);

                        try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement s = c.createStatement()) {
                            //String ID = userHolder.getText(); // Retrieve the current user ID from userHolder
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

    public void loadbudget() {
        String selectedClient = txtCli.getText();

        String clientBudgetQuery = "SELECT "
                + "    bs.clientID, "
                + "    bs.serviceID, "
                + "    so.servicedesc AS CareDesc, "
                + "    so.dayshift AS shiftCost, "
                + "    MAX(bs.counter) AS totalCounter, "
                + "    MAX(bs.caseID) AS maxCaseID "
                + "FROM "
                + "    budget_staging bs "
                + "JOIN "
                + "    serviceoffered so ON bs.serviceID = so.serviceID "
                + "JOIN "
                + "    clientcases cc ON bs.caseID = cc.caseID "
                + "WHERE "
                + "    bs.clientID = " + selectedClient + " "
                + "    AND cc.assessmentStatus = 'For Budget' "
                + "    AND bs.caseID = (SELECT MAX(caseID) "
                + "                     FROM budget_staging "
                + "                     WHERE clientID = bs.clientID) "
                + "GROUP BY "
                + "    bs.clientID, "
                + "    bs.serviceID, "
                + "    so.servicedesc, "
                + "    so.dayshift "
                + "HAVING "
                + "    MAX(bs.counter) >= 1 "
                + "ORDER BY "
                + "    bs.serviceID;";


        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(clientBudgetQuery)) {

            // Clear existing data
            tblBudget.getItems().clear();

            // Process the result set and populate the TableView
            while (resultSet.next()) {
                String desc = resultSet.getString("CareDesc");
                int unitStage = resultSet.getInt("totalCounter");
                int units = (unitStage == 1) ? 2 : (unitStage >= 2) ? 3 : 0;

                txtCaseID.setText(resultSet.getString("maxCaseID"));

                double tCost = resultSet.getDouble("shiftCost");
                double tCare = tCost * units;

                // Create a BudgetEntry object
                BudgetEntry budgets = new BudgetEntry(desc, units, tCare);

                // Add the BudgetEntry object to the TableView
                tblBudget.getItems().add(budgets);
            }

            // Add the right-click context menu
            addContextMenuToBudgetTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addContextMenuToBudgetTable() {
        // Create the Context Menu
        ContextMenu contextMenu = new ContextMenu();

        // Create MenuItems
        MenuItem deleteItem = new MenuItem("Delete Selected");
        MenuItem addItem = new MenuItem("Add Service");

        // Add MenuItems to ContextMenu
        contextMenu.getItems().addAll(deleteItem, addItem);

        // Disable menu items if the table is empty or no item is selected
        tblBudget.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean isEmpty = tblBudget.getItems().isEmpty();
            deleteItem.setDisable(isEmpty || newSelection == null);
            addItem.setDisable(isEmpty);
        });

        // Handle Delete Action
        deleteItem.setOnAction(event -> {
            BudgetEntry selectedItem = tblBudget.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirm Deletion");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("Are you sure you want to delete the selected item?");
                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    tblBudget.getItems().remove(selectedItem);
                }
            }
        });

        // Handle Add Service Action
        addItem.setOnAction(event -> {
            // Fetch service descriptions and values from the database
            List<ServiceDetail> serviceDetails = fetchServiceDetails();

            // Show the dialog to get user input for service description and numUnits
            if (!serviceDetails.isEmpty()) {
                String selectedDescription = showDescriptionSelectionDialog(serviceDetails);
                if (selectedDescription != null) {
                    // Prompt for numUnits
                    TextInputDialog numUnitsDialog = new TextInputDialog("1");
                    numUnitsDialog.setTitle("Input Num Units");
                    numUnitsDialog.setHeaderText("Enter Number of Units");
                    numUnitsDialog.setContentText("Num Units:");

                    Optional<String> numUnitsResult = numUnitsDialog.showAndWait();
                    int numUnits = numUnitsResult.map(Integer::parseInt).orElse(1); // Default to 1 if no input

                    // Find the corresponding service detail
                    double dayShiftValue = serviceDetails.stream()
                            .filter(detail -> detail.getDescription().equals(selectedDescription))
                            .findFirst()
                            .map(ServiceDetail::getDayShiftValue)
                            .orElse(0.0);

                    // Compute total
                    double total = dayShiftValue * numUnits;

                    // Create a new BudgetEntry with the selected description
                    BudgetEntry newEntry = new BudgetEntry(selectedDescription, numUnits, total);
                    tblBudget.getItems().add(newEntry); // Add to the table
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Descriptions");
                alert.setHeaderText(null);
                alert.setContentText("No service descriptions available.");
                alert.showAndWait();
            }
        });

        // Show ContextMenu on right-click
        tblBudget.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(tblBudget, event.getScreenX(), event.getScreenY());
            }
        });
    }

// Method to fetch service descriptions and dayShift values from the database
    private List<ServiceDetail> fetchServiceDetails() {
        List<ServiceDetail> serviceDetails = new ArrayList<>();
        String query = "SELECT servicedesc, dayshift FROM serviceoffered WHERE serviceID <> 'CM' AND serviceID <> 'PM'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String description = resultSet.getString("servicedesc");
                double dayShiftValue = resultSet.getDouble("dayshift");
                serviceDetails.add(new ServiceDetail(description, dayShiftValue));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return serviceDetails;
    }

// Method to show a dialog for selecting service descriptions
    private String showDescriptionSelectionDialog(List<ServiceDetail> serviceDetails) {
        ChoiceDialog<String> dialog = new ChoiceDialog<>(serviceDetails.get(0).getDescription(),
                serviceDetails.stream().map(ServiceDetail::getDescription).toList());
        dialog.setTitle("Select Service Description");
        dialog.setHeaderText("Choose a Service Description");
        dialog.setContentText("Available Descriptions:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null); // Return the selected description or null
    }

// Class to hold service details
    private static class ServiceDetail {

        private final String description;
        private final double dayShiftValue;

        public ServiceDetail(String description, double dayShiftValue) {
            this.description = description;
            this.dayShiftValue = dayShiftValue;
        }

        public String getDescription() {
            return description;
        }

        public double getDayShiftValue() {
            return dayShiftValue;
        }
    }

    public void loadBudgetClientDatabase() {
        // SQL query
        String query = "SELECT cc.caseID as bCaseID, cd.clientID, CONCAT(cd.fName, ' ', cd.lName) AS fullName "
                + "FROM clientdata cd "
                + "JOIN clientcases cc ON cd.clientID = cc.clientID "
                + "WHERE cc.assessmentStatus = 'For Budget';";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            // Clear existing data
            tblBudgetClient.getItems().clear();

            // Process the result set and populate the TableView
            while (resultSet.next()) {
                int bcid = resultSet.getInt("clientID");
                String bcname = resultSet.getString("fullName");
                budgetCaseid = resultSet.getInt("bCaseID");
                // txtCaseID.setText(resultSet.getString("caseID"));
                // Create a CreateCase object
                budgetClient budgetclient = new budgetClient(bcid, bcname);

                // Add the CreateCase object to the TableView
                tblBudgetClient.getItems().add(budgetclient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleclientbudget(MouseEvent event) throws SQLException {
        if (event == null || event.getClickCount() == 1) { // Single-click detection
            budgetClient selectedClient = tblBudgetClient.getSelectionModel().getSelectedItem();
            if (selectedClient != null) {
                loadBudgetClientDets(selectedClient.getBcid());
            }
        }
    }

    private void loadBudgetClientDets(int cid) {
        String clientDataQuery = "SELECT clientID,CONCAT(fName, ' ', lName) AS fullName, clientBday, levelID FROM clientData WHERE clientID = ?";
//        String clientContactQuery = "SELECT * FROM clientContact WHERE clientID = ?";
//        String clientManagement = "SELECT * FROM clientCareManagement WHERE clientID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // First query: Fetch client data
            try (PreparedStatement preparedStatement = connection.prepareStatement(clientDataQuery)) {
                preparedStatement.setInt(1, cid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        txtCli.setText(resultSet.getString("clientID"));
                        txtcname.setText(resultSet.getString("fullName"));
                        txtCdob.setText(resultSet.getString("clientBday"));
                        txtFlevel.setText(resultSet.getString("levelID"));

                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void GenBudget(ActionEvent event) {
        String txt = txtCli.getText();

        // Handle potential NumberFormatException
        int whoID;
        try {
            whoID = Integer.parseInt(txt);
        } catch (NumberFormatException e) {
            System.out.println("Invalid client ID format: " + txt);
            return; // Exit the method if the client ID is invalid
        }

        // SQL query with parameter placeholder
        String clientFundQuery = "SELECT fl.dailyFund AS daily, fl.monthlyFund AS monthly, cd.levelID AS cID "
                + "FROM fundinglevel fl "
                + "JOIN clientData cd ON fl.levelID = cd.levelID "
                + "WHERE cd.clientID = ?";

        double careManValue = 0.00; // Default value, will be replaced by DB value if found
        double carePackValue = 0.00; // Default value, will be replaced by DB value if found

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Prepare the statement with the query
            try (PreparedStatement preparedStatement = connection.prepareStatement(clientFundQuery)) {
                preparedStatement.setInt(1, whoID); // Set the parameter
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Fetch and set data
                        txtGovS.setText(resultSet.getString("daily"));
                        txtMonth1.setText(resultSet.getString("monthly"));

                        double monthfund = resultSet.getDouble("monthly");
                        double fundA = monthfund * 12;

                        // Fetch dayshift for serviceID = PM
                        String dayshiftPMQuery = "SELECT dayshift FROM serviceoffered WHERE serviceID = ?";
                        try (PreparedStatement pmStmt = connection.prepareStatement(dayshiftPMQuery)) {
                            pmStmt.setString(1, "PM");
                            try (ResultSet pmResultSet = pmStmt.executeQuery()) {
                                if (pmResultSet.next()) {
                                    carePackValue = Double.parseDouble(pmResultSet.getString("dayshift")); // Replace with fetched value
                                    txtSuppLessP.setText(pmResultSet.getString("dayshift"));
                                }
                            }
                        }

                        // Fetch dayshift for serviceID = CM
                        String dayshiftCMQuery = "SELECT dayshift FROM serviceoffered WHERE serviceID = ?";
                        try (PreparedStatement cmStmt = connection.prepareStatement(dayshiftCMQuery)) {
                            cmStmt.setString(1, "CM");
                            try (ResultSet cmResultSet = cmStmt.executeQuery()) {
                                if (cmResultSet.next()) {
                                    careManValue = Double.parseDouble(cmResultSet.getString("dayshift")); // Replace with fetched value
                                    txtSuppLessC.setText(cmResultSet.getString("dayshift"));
                                }
                            }
                        }

                        // Calculate with fetched values
                        double careMan = monthfund * careManValue;
                        double carePack = monthfund * carePackValue;
                        double annual = fundA - (careMan + carePack);

                        txtMonth1.setText(String.format("%.2f", fundA));
                        txtMonth7.setText(String.format("%.2f", careMan));
                        txtMonth8.setText(String.format("%.2f", carePack));
                        txtTotalA.setText(String.format("%.2f", annual));

                        txtFlevel.setText(resultSet.getString("cID"));
                        txtCdate.setText(LocalDate.now().toString());

                        // Assuming usersname is already defined and set elsewhere
                        txtCdate.setText(LocalDate.now().toString());

                        txtPby.setText(usersname);

                        loadbudget();

                        logAudit("A new budget plan has been ge erated for client: " + txt, userID);

                        GenBudget.setDisable(true);
                        tblBudgetClient.setDisable(true);
                        saveBudgetButton.setDisable(false);
                        modBudgetButton.setDisable(false);
                    } else {
                        // Handle case where no result is found
                        System.out.println("No data found for client ID: " + whoID);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void modBudgetButton(ActionEvent event) {

        GenBudget.setDisable(false);
        tblBudgetClient.setDisable(false);
        saveBudgetButton.setDisable(true);
        modBudgetButton.setDisable(true);
        txtGovS.clear();
        txtSuppLessC.clear();
        txtSuppLessP.clear();
        txtMonth1.clear();
        txtMonth7.clear();
        txtMonth8.clear();
        txtTotalA.clear();

        tblBudget.getItems().clear();

    }

    @FXML
    private void modifButton(ActionEvent event) {

        GenBudget.setDisable(false);
        tblBudgetClient.setDisable(false);
        saveBudgetButton.setDisable(true);
        modBudgetButton.setDisable(true);
        txtGovS.clear();
        txtSuppLessC.clear();
        txtSuppLessP.clear();
        txtMonth1.clear();
        txtMonth7.clear();
        txtMonth8.clear();
        txtTotalA.clear();

        tblBudget.getItems().clear();

    }

    @FXML
    private void saveBudgetButton(ActionEvent event) {
        String scid = txtCli.getText();

        String clientName = txtcname.getText();
        String clientDOB = txtCdob.getText();
        String clientLevel = txtFlevel.getText();
        String prepBy = txtPby.getText();
        String prepDate = txtCdate.getText();
        String dailySubsidy = txtGovS.getText();
        String manPerc = txtSuppLessC.getText();
        String carePerc = txtSuppLessP.getText();
        String monthlySubsidy = txtMonth1.getText();
        String manFee = txtMonth7.getText();
        String careFee = txtMonth8.getText();
        String toSpend = txtTotalA.getText();

        // SQL query to insert into budgetSummary table
        String budgetSummaryQuery = "INSERT INTO budgetSummary (clientID, clientName, clientDOB, clientLevel, prepBy, prepDate, "
                + "dailySubsidy, manPerc, carePerc, monthlySubsidy, manFee, careFee, toSpend) "
                + "VALUES ('" + scid + "', '" + clientName + "', '" + clientDOB + "', '" + clientLevel + "', '"
                + prepBy + "', '" + prepDate + "', '" + dailySubsidy + "', '" + manPerc + "', '" + carePerc + "', '"
                + monthlySubsidy + "', '" + manFee + "', '" + careFee + "', '" + toSpend + "')";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {

            // Execute the insert for budgetSummary
            statement.executeUpdate(budgetSummaryQuery);

            // Now for expenseSummary, we need to sum the total expenses and insert each row
            double totalExpense = 0.0;

// First, calculate the total expense by summing up all entries
            for (BudgetEntry entry : tblBudget.getItems()) {
                double subTotal = entry.getTotal();
                totalExpense += subTotal;
            }

// Now, insert each row into expenseSummary with the same totalExpense value
            for (BudgetEntry entry : tblBudget.getItems()) {
                String serviceDesc = entry.getDescription();
                int numUnits = entry.getNumUnits();

                // SQL query to insert each row into expenseSummary
                String expenseSummaryQuery = "INSERT INTO expenseSummary (clientID, prepDate, serviceDesc, numUnits, subTotal, totalExpense) "
                        + "VALUES ('" + scid + "', '" + prepDate + "', '" + serviceDesc + "', " + numUnits + ", " + entry.getTotal() + ", " + totalExpense + ")";

                statement.executeUpdate(expenseSummaryQuery); // Insert each row into expenseSummary
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Construct the SQL UPDATE query
        String closeCase = "UPDATE clientcases SET assessmentStatus = 'Closed', closingReason = 'Budget created' WHERE caseID = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(closeCase)) {

            // Set the parameter for caseID
            preparedStatement.setInt(1, budgetCaseid); // Set the caseID

            // Execute the update query
            int clientRowsUpdated = preparedStatement.executeUpdate();

            // Check if the update was successful
            if (clientRowsUpdated > 0) {
                // Show an alert that the budget has been saved
                Platform.runLater(() -> {
                    Alert assessmentStartedAlert = new Alert(Alert.AlertType.INFORMATION);
                    assessmentStartedAlert.setTitle("Budget Saved");
                    assessmentStartedAlert.setHeaderText(null);
                    assessmentStartedAlert.setContentText("Budget has been Created.");
                    assessmentStartedAlert.showAndWait();

                    logAudit("A generated budget for client: " + scid + " has been confirmed", userID);

                });

                // Continue with the rest of the method
                GenBudget.setDisable(false);
                tblBudgetClient.setDisable(false);
                saveBudgetButton.setDisable(true);
                modBudgetButton.setDisable(true);
                txtGovS.clear();
                txtSuppLessC.clear();
                txtSuppLessP.clear();
                txtMonth1.clear();
                txtMonth7.clear();
                txtMonth8.clear();
                txtTotalA.clear();
                txtCli.clear();
                txtcname.clear();
                txtCdob.clear();
                txtFlevel.clear();
                txtPby.clear();
                txtCdate.clear();

                // Clear the TableView
                tblBudget.getItems().clear();
                loadBudgetClientDatabase();
            } else {
                // Handle the case where no rows were updated (optional)
                Platform.runLater(() -> {
                    Alert failureAlert = new Alert(Alert.AlertType.WARNING);
                    failureAlert.setTitle("Update Failed");
                    failureAlert.setHeaderText(null);
                    failureAlert.setContentText("No case found with the provided ID.");
                    failureAlert.showAndWait();
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Show an error alert to the user
            Platform.runLater(() -> {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Database Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("An error occurred while updating the case.");
                errorAlert.showAndWait();
            });
        }
    }

    private void searchCasesChange() {
        // Add a listener to usersID to call loadUserData when its value changes
        aCaseSearch.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null || newValue.trim().isEmpty()) {
                    loadCases();
                }
            }

        });
    }

    private void searchMyChange() {
        // Add a listener to usersID to call loadUserData when its value changes
        clientSearches.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null || newValue.trim().isEmpty()) {
                    loadMyCases();
                }
            }

        });
    }

    private void searchClientChange() {
        // Add a listener to usersID to call loadUserData when its value changes
        caseSearch.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null || newValue.trim().isEmpty()) {
                    loadClients();
                }
            }

        });
    }

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

    @FXML
    private void finaliseBtn(ActionEvent event) {

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
        if (!aStats.equals("Complete")) {
            // Display a message if the assessment has already been started
            Alert assessmentStartedAlert = new Alert(Alert.AlertType.INFORMATION);
            assessmentStartedAlert.setTitle("Warning");
            assessmentStartedAlert.setHeaderText(null);
            assessmentStartedAlert.setContentText("The assessment is either in progress or not not started");
            assessmentStartedAlert.showAndWait();
            return; // Exit the method if the assessment is not "Not Started"
        } else {
            String ci = csID.getText();
            try {
                // Load the FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FinaliseCasePlan.fxml"));

                Parent root = loader.load();

                System.out.println(ci);
                // Get the controller and set client details
                FinaliseCasePlanController finCase = loader.getController();
                finCase.setCaseNo(ci);

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

    }

    @FXML
    private void myRecSave(ActionEvent event) {

        String CN = csID.getText();
        String CI = cID.getText();

        String AT = cmbAssessment.getValue();
        String PR = cmbPriority.getValue();
        String AA = asignedCSO.getValue().toString();

        String csoID = AA.substring(0, 5); // 
        String csoName = AA.substring(8);

        // Construct the SQL UPDATE query
        String upCase = "UPDATE clientCases SET caseType = '" + AT + "', caseAssignment = '" + csoName + "', casePriority = '" + PR + "', userID = '" + csoID + "' WHERE caseID = '" + CN + "'";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement()) {
            // Execute the update query
            int caseUpdated = statement.executeUpdate(upCase);

            // Check if the update was successful
            if (caseUpdated > 0) {
                // Display success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Case details updated successfully.");
                successAlert.showAndWait();

                loadClients();
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        asignedCSO.setDisable(true);
        cmbAssessment.setDisable(true);
        cmbPriority.setDisable(true);
        myRecSave.setDisable(true);
        myRecCancel.setDisable(true);
        updateCase.setDisable(false);
        finaliseBtn.setDisable(false);
        loadCases();
        casesTbl.setDisable(false);

        logAudit("Client case No: " + CN + " has been updated", userID);

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

    public void loadMyCases() {
        String currentUser = userHolder.getText(); // Retrieve the current user ID from userHolder

        System.out.println("Current User ID: " + currentUser);

        // Correctly use parameterized query
        String query = "SELECT cc.caseID AS cID, "
                + "cc.clientID AS clientID, "
                + "CONCAT(cd.fName, ' ', cd.lName) AS fullName, "
                + "cc.caseType AS caseType, "
                + "cc.casePriority AS casePriority, "
                + "cc.caseDate AS createDate, "
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
                    String caseDt = resultSet.getString("createDate");
                    // Convert the caseDt String to LocalDate
                    LocalDate createDate = LocalDate.parse(caseDt);

                    // Get today's date
                    LocalDate today = LocalDate.now();

                    // Calculate the total number of days between createDate and today
                    long cAge = ChronoUnit.DAYS.between(createDate, today);
                    //System.out.println(cAge);
                    // Create a MyCases object
                    MyCases myCase = new MyCases(caseID, clientID, clientName, caseType, casePriority, cAge);

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
                            aFundLevelCombo.setValue(resultSet.getString("levelID"));

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
                        // aFundLevelCombo.setValue(resultSet.getString("levelID"));
                        aCMCombo.setValue(Integer.toString(resultSet.getInt("userID"))); // need to replace with cm name

                        refCode.setText(resultSet.getString("referralCode"));
                        aOutcome.setText(resultSet.getString("handOver"));
                    } else {
//                        // Clear contact fields if no client contact data is found
                        refCode.clear();
                        aOutcome.clear();
                        //   aFundLevelCombo.setValue("Not Assessed");
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
        if (aStats.equals("Closed") || aStats.equals("Complete") || aStats.equals("For Budget")) {
            // Display a message if the assessment has already been started
            Alert assessmentStartedAlert = new Alert(Alert.AlertType.INFORMATION);
            assessmentStartedAlert.setTitle("Warning");
            assessmentStartedAlert.setHeaderText(null);
            assessmentStartedAlert.setContentText("The assessment has either already completed or started and cannot be updated at the moment.");
            assessmentStartedAlert.showAndWait();
            return; // Exit the method if the assessment is not "Not Started"
        }

        initComboBox();
        cmbAssessment.setDisable(false);
        cmbPriority.setDisable(false);
        asignedCSO.setDisable(false);
//        csID.setDisable(false);
//        cID.setDisable(false);
//        fName.setDisable(false);
//        lName.setDisable(false);
//        mobileNum.setDisable(false);
//        address.setDisable(false);
//        mediCare.setDisable(false);

        // dateCreate.setDisable(false);
        myRecSave.setDisable(false);
        myRecCancel.setDisable(false);
        updateCase.setDisable(true);
        finaliseBtn.setDisable(true);
        //  reassignBtn.setDisable(true);
        casesTbl.setDisable(true);
    }

    @FXML
    private void myRecCancel(ActionEvent event) {
//        dateCreate.setDisable(true);
//        csID.setDisable(true);
//        cID.setDisable(true);
//        fName.setDisable(true);
//        lName.setDisable(true);
//        mobileNum.setDisable(true);
//        address.setDisable(true);
//        mediCare.setDisable(true);
        asignedCSO.setDisable(true);
        cmbAssessment.setDisable(true);
        cmbPriority.setDisable(true);
        myRecSave.setDisable(true);
        myRecCancel.setDisable(true);
        updateCase.setDisable(false);
        finaliseBtn.setDisable(false);

        casesTbl.setDisable(false);
    }
    private String usersname;

    private void updateUIForRole(String roleID, String accUser) {
        usersname = accUser;
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

    @FXML
    private void dashboardLbl(MouseEvent event) {

        dashboardPane.setVisible(true);
        manageCasePane.setVisible(false);
        budgetPane.setVisible(false);
        headLbl.setText("Dashboard");
        reportsPane.setVisible(false);
        updateActiveCasesCount(); //ANAYLYTICS
        updateActiveAgeingCount();//ANAYLYTICS
        updatePendingCaseCount();//ANALYTICS
        updateHighLevelCount(); //ANALYTICS
        updateLowPriorityCount(); //ANALYTICS
        updateMedPriorityCount(); //ANALYTICS
        updateWeeklyCasesGraph(); //ANALYTICS
    }

    @FXML
    private void manageCaseLbl(MouseEvent event) {
        loadCases();
        headLbl.setText("Manage Cases");
        manageCasePane.setVisible(true);
        budgetPane.setVisible(false);
        reportsPane.setVisible(false);
        dashboardPane.setVisible(false);

    }

    @FXML
    private void budgetPlanLbl(MouseEvent event) {
        loadBudgetClientDatabase();
        headLbl.setText("Budget Planning");
        budgetPane.setVisible(true);
        manageCasePane.setVisible(false);
        reportsPane.setVisible(false);
        dashboardPane.setVisible(false);

    }

    @FXML
    private void budgetViewer(MouseEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("budgetViewer.fxml"));
            Parent root = loader.load();

//            String createUser = userID;
//            CreateCaseController createCasec = loader.getController();
//
//            if (createCasec != null) {
//                createCasec.setCreateUser(createUser);
//            } else {
//                System.out.println("Error: Controller not found!");
//            }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Budget Viewer");
            stage.setScene(new Scene(root));
            stage.setResizable(false);

//             stage.setWidth(679);  // Set the width 
//             stage.setHeight(580); // Set the height
            stage.showAndWait(); // Wait for the dialog to close before continuing with main window
        } catch (IOException e) {
            e.printStackTrace();
        }
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

//           stage.setWidth(679);  // Set the width 
//           stage.setHeight(580); // Set the height
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

    //CSO
    @FXML
    private void csoDashboardLbl(MouseEvent event) {
        reportsPane.setVisible(false);
        dashboardPane.setVisible(true);
        myCasePane.setVisible(false);
        manageClientPane.setVisible(false);
        headLbl.setText("Dashboard");
    }

    @FXML
    private void myCaseLbl(MouseEvent event) {
        loadMyCases();
        reportsPane.setVisible(false);
        dashboardPane.setVisible(false);
        myCasePane.setVisible(true);
        manageClientPane.setVisible(false);
        headLbl.setText("My Cases");
    }

    @FXML
    private void manageClientLbl(MouseEvent event) {
        loadClients();
        reportsPane.setVisible(false);
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

        dashboardPane.setVisible(false);
        myCasePane.setVisible(false);
        manageClientPane.setVisible(false);
        manageCasePane.setVisible(false);
        budgetPane.setVisible(false);
        reportsPane.setVisible(true);

        headLbl.setText("Reports");

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
        String Zips = aZip.getText();
        String level = aFundLevelCombo.getValue();

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
                || Zips == null || Zips.isEmpty()
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

        if (!mobileValue.matches("\\d{10}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Mobile number must contain 10 digits");
            alert.showAndWait();
            return;

        }

        // Validate mobile field as numeric
        if (!ERMobile.matches(numeric)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Contact should contain only numbers!");
            alert.showAndWait();
            return;
        }

        if (!ERMobile.matches("\\d{10}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Mobile number must contain 10 digits");
            alert.showAndWait();
            return;

        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        if (emailValue == null || !emailValue.matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address.");
            alert.showAndWait();
            return;

        }

        if (EREMail == null || !emailValue.matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid email address.");
            alert.showAndWait();
            return;

        }

        if (!Zips.matches("\\d{4}")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Zip code must contain 4 digits.");
            alert.showAndWait();
            return;

        }

        // Construct the SQL UPDATE query
        String updateClient = "UPDATE clientData SET fName = '" + fnameValue + "', lName = '" + lnameValue + "', clientMedicare = '" + medicareValue + "',clientZip = '" + Zips + "', clientAddress = '" + addressValue + "', clientMobile = '" + mobileValue + "',"
                + "emergencyContactPerson = '" + ERContact + "', emergencyContactNumber = '" + ERMobile + "', relationship = '" + ERRelation + "',clientBday = '" + bdayValue + "',levelID = '" + level + "' WHERE clientID = '" + clientIDValue + "'";

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

                logAudit("A client record with clientID: " + clientIDValue + " has been updated", userID);

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

    @FXML
    private void helpLink(MouseEvent event) {
        try {
            // Path to your PDF file
            File pdfFile = new File("HCCPP-User-Manual.pdf");

            // Check if the file exists
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                // Optionally show an alert if the file does not exist
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
            confirmation.setHeaderText(null);
            confirmation.setContentText("Are you sure you want to logout?");

            // Show the confirmation dialog and wait for the user's response
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement s = c.createStatement()) {

                        String sql = "UPDATE userAccounts SET stats = '1' WHERE userID = '" + userID + "'";

                        // Execute the update statement
                        int row = s.executeUpdate(sql);

                        logAudit(headerLbl.getText().substring(3) + " have successfully logged-out", userID);

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
        } else {

            // Retrieve client details from UI elements
            String cno = myClientID.getText();
            String fn = myClientFname.getText();
            String ln = myClientLname.getText();
            String mc = myClientMedicare.getText();
            String mob = myClientContact.getText();
            String ca = myClientAddress.getText();
            String csid = clID.getText();
            String bd = myClientBday.getValue() != null ? myClientBday.getValue().toString() : "";
            String csonumb = userHolder.getText();
            String csonam = headerLbl.getText().substring(3); // Extract substring after the 3rd character

            // Debugging output
            System.out.println("Client Number: " + cno);

            try {

                // Load the FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("assessmentForm.fxml"));
                Parent root = loader.load();

                // Get the controller and set client details
                AssessmentFormController assessmentForm = loader.getController();
                assessmentForm.setClientDetails(cno, fn, ln, mc, mob, ca, csid, bd, csonumb, csonam);

                // Create a new stage for the assessment form
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Client Assessment");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setWidth(850);  // Set the width
                stage.setHeight(650); // Set the height

                stage.showAndWait(); // Wait for the dialog to close before continuing with main window
            } catch (IOException e) {
                e.printStackTrace(); // Log or handle exception as needed
            }
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

            String searchCases = "SELECT cc.caseID AS cID, CONCAT(cd.fName, ' ', cd.lName) AS fullName, cc.caseType AS caseType, assessmentStatus AS caseStats, CONCAT(ua.fName, ' ', ua.lName) AS assignedCSO FROM clientCases cc JOIN clientData cd ON cc.clientID = cd.clientID JOIN userAccounts ua ON cc.userID = ua.userID "
                    + "WHERE cc.caseID LIKE '%" + ssSearch + "%' "
                    + "OR cd.lName LIKE '%" + ssSearch + "%' "
                    + "OR cd.fName LIKE '%" + ssSearch + "%' "
                    + "OR cc.caseType LIKE '%" + ssSearch + "%' ";

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
                    int cssID = resultSet.getInt("cID");
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

                            logAudit("A client with ClientID: " + selectedID + " has been deactivated", userID);

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
                successAlert.setContentText("Case has been closed!.");
                successAlert.showAndWait();

                logAudit("A client case with Case No: " + clID + " has been closed", userID);

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
                    + "cc.caseDate as AS createDate, "
                    + "FROM clientCases cc "
                    + "JOIN clientData cd ON cc.clientID = cd.clientID "
                    + "JOIN userAccounts ua ON cc.userID = ua.userID "
                    + "WHERE cc.caseID LIKE '%" + searchCase + "%' "
                    + "OR cc.clientID LIKE '%" + searchCase + "%' "
                    + "OR cd.lName LIKE '%" + searchCase + "%' "
                    + "OR cd.fName LIKE '%" + searchCase + "%' "
                    + "OR cc.caseType LIKE '%" + searchCase + "%' "
                    + "OR cc.casePriority LIKE '%" + searchCase + "%'";

            // "SELECT caseID,clientID CONCAT(fname, ' ', lname) AS fullName, caseType FROM clientdata WHERE fName LIKE '%" + searchC + "%' OR lName LIKE '%" + searchC + "%' OR clientID LIKE '%" + searchC + "%' OR clientMedicare LIKE '%" + searchC + "%'";
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

                    String caseDt = resultSet.getString("createDate");

                    // Convert the caseDt String to LocalDate
                    LocalDate createDate = LocalDate.parse(caseDt);

                    // Get today's date
                    LocalDate today = LocalDate.now();

                    // Calculate the total number of days between createDate and today
                    long cAge = ChronoUnit.DAYS.between(createDate, today);

                    // Create a Clients object
                    MyCases cases = new MyCases(caID, cliID, clName, caType, caPriority, cAge);

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

    // -------------------------- ANALYTICS------------------------------
    // Query to get active cases count from the clientCases table
    private final String ACTIVE_CASES_COUNT_QUERY = "SELECT COUNT(*) FROM clientCases WHERE assessmentStatus != 'Closed'";

// Method to get the count of active cases and update the Label
    private void updateActiveCasesCount() {
        try (Connection dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = dbConnection.createStatement(); ResultSet resultSet = statement.executeQuery(ACTIVE_CASES_COUNT_QUERY)) {

            if (resultSet.next()) {
                int activeCases = resultSet.getInt(1); // Get the count from the first column
                Platform.runLater(() -> activeCasesCount.setText(String.valueOf(activeCases)));
            }
        } catch (SQLException e) {
            // Log error or display an alert to the user
            System.err.println("Error fetching active cases: " + e.getMessage());
        }
    }

    // SQL query to count cases older than 3 days where assessmentStatus is not 'Complete'
    private final String COUNT_ACTIVE_AGEING_QUERY = "SELECT COUNT(*) AS activeAgeingCases FROM clientCases WHERE caseDate < ? AND assessmentStatus != 'Complete'";

    // Method to update the active ageing count label
    private void updateActiveAgeingCount() {
        // Calculate the date 3 days ago
        LocalDate threeDaysAgo = LocalDate.now().minusDays(3);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(COUNT_ACTIVE_AGEING_QUERY)) {

            // Set the parameter for the caseDate to 3 days ago
            statement.setString(1, threeDaysAgo.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                // Get the count from the result set
                if (resultSet.next()) {
                    int activeAgeingCountValue = resultSet.getInt("activeAgeingCases");

                    // Update the label on the JavaFX Application Thread
                    Platform.runLater(() -> activeAgeingCount.setText(String.valueOf(activeAgeingCountValue)));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SQL query to count all cases where assessmentStatus is 'Complete'
    private final String COUNT_COMPLETE_CASES_QUERY = "SELECT COUNT(*) AS completedCases FROM clientCases WHERE assessmentStatus = 'Complete'";

    // Method to update the pending case count label
    private void updatePendingCaseCount() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(COUNT_COMPLETE_CASES_QUERY); ResultSet resultSet = statement.executeQuery()) {

            // Get the count from the result set
            if (resultSet.next()) {
                int completedCaseCount = resultSet.getInt("completedCases");

                // Update the label on the JavaFX Application Thread
                Platform.runLater(() -> pendingCaseCount.setText(String.valueOf(completedCaseCount)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SQL query to count cases with high priority and assessmentStatus not 'Closed'
    private final String COUNT_HIGH_PRIORITY_CASES_QUERY = "SELECT COUNT(*) AS highPriorityCases FROM clientCases WHERE casePriority = 'High' AND assessmentStatus != 'Closed'";

    // Method to update the high level count label
    private void updateHighLevelCount() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(COUNT_HIGH_PRIORITY_CASES_QUERY)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                int highPriorityCount = 0;
                // Get the count from the result set
                if (resultSet.next()) {
                    highPriorityCount = resultSet.getInt("highPriorityCases");
                }
                // Update the label on the JavaFX Application Thread
                int finalHighPriorityCount = highPriorityCount;
                Platform.runLater(() -> {
                    highLevelCount.setText(finalHighPriorityCount + " Cases");
                    // You can optionally style the label
                    highLevelCount.setStyle("-fx-text-fill: red;");  // Maintain red color
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SQL query to count low priority cases where assessmentStatus is not 'Closed'
    private final String COUNT_LOW_PRIORITY_QUERY = "SELECT COUNT(*) AS lowPriorityCases FROM clientCases WHERE casePriority = 'Low' AND assessmentStatus != 'Closed'";

    // Method to update the highLevelCount label
    private void updateLowPriorityCount() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(COUNT_LOW_PRIORITY_QUERY)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                int lowPriorityCount = 0;
                // Get the count from the result set
                if (resultSet.next()) {
                    lowPriorityCount = resultSet.getInt("lowPriorityCases");
                }
                // Update the label on the JavaFX Application Thread
                int finalLowPriorityCount = lowPriorityCount;
                Platform.runLater(() -> {
                    lowLevelCount.setText(finalLowPriorityCount + " Cases");
                    lowLevelCount.setStyle("-fx-text-fill: green;"); // Set the color to blue or any other color
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// SQL query to count medium priority cases where assessmentStatus is not 'Closed'
    private final String COUNT_MED_PRIORITY_QUERY = "SELECT COUNT(*) AS medPriorityCases FROM clientCases WHERE casePriority = 'Medium' AND assessmentStatus != 'Closed'";

    // Method to update the medLevelCount label
    private void updateMedPriorityCount() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(COUNT_MED_PRIORITY_QUERY)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                int medPriorityCount = 0; // Default value if no records are found

                // Get the count from the result set
                if (resultSet.next()) {
                    medPriorityCount = resultSet.getInt("medPriorityCases");
                }

                // Update the label on the JavaFX Application Thread
                int finalMedPriorityCount = medPriorityCount;
                Platform.runLater(() -> {
                    medLevelCount.setText(finalMedPriorityCount + " Cases");
                    medLevelCount.setStyle("-fx-text-fill: blue;");
                });

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // SQL query to get weekly data based on assessmentStatus
    private final String WEEKLY_ASSESSMENT_QUERY
            = "SELECT assessmentStatus, COUNT(*) AS statusCount "
            + "FROM clientCases "
            + "WHERE caseDate >= CURDATE() - INTERVAL 7 DAY "
            + "GROUP BY assessmentStatus";

    public void updateWeeklyCasesGraph() {
        // Clear previous data in case it exists
        weeklyCasesGraph.getData().clear();

        // Fetch data for the PieChart
        List<PieChart.Data> data = getWeeklyAssessmentStatusData();

        // Add all the data to the PieChart
        weeklyCasesGraph.getData().addAll(data);

        // Set the title of the PieChart
        weeklyCasesGraph.setTitle("Weekly Assessment Status");

        // Customize legend and appearance (optional)
        weeklyCasesGraph.setLegendVisible(true);
        weeklyCasesGraph.setLabelsVisible(true); // Show the labels with numbers on slices
    }

    // Fetch weekly data for the PieChart from the database
    private List<PieChart.Data> getWeeklyAssessmentStatusData() {
        List<PieChart.Data> data = new ArrayList<>();
        String query = "SELECT assessmentStatus, COUNT(*) as count "
                + "FROM clientCases "
                + "WHERE caseDate >= CURDATE() - INTERVAL 7 DAY "
                + "GROUP BY assessmentStatus";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String assessmentStatus = resultSet.getString("assessmentStatus");
                int count = resultSet.getInt("count");

                // Add data to the PieChart with the label formatted as "Status (count)"
                PieChart.Data pieData = new PieChart.Data(assessmentStatus + " (" + count + ")", count);
                data.add(pieData);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving assessment status data: " + e.getMessage());
        }

        return data;
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

            logAudit("A Case Report has been generated", userID);

        } else if ("Assessment Reports".equals(reportCombo.getValue())) {
            // Call the method to load cases based on the selected radio button
            loadAssessmentReports();

            logAudit("An Assessment Report has been generated", userID);

        } else if ("Client Reports".equals(reportCombo.getValue())) {
            // Call the method to load cases based on the selected radio button
            loadClientReports();

            logAudit("A Client Report has been generated", userID);

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
                    contentStream.showText("Generated by: " + headerLbl.getText().substring(3));
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

                logAudit(reportCombo.getValue() + "has been extracted and saved to" + selectedFile.getAbsolutePath(), userID);

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
                printLayout.getChildren().add(new Text("Generated by: " + headerLbl.getText().substring(3)));
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

                        logAudit(reportCombo.getValue() + "was successfully printed", userID);

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

    private void handleClearTabs() {
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

        careDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        numUnits.setCellValueFactory(new PropertyValueFactory<>("numUnits"));
        totalCare.setCellValueFactory(new PropertyValueFactory<>("total"));

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
        case_Date.setCellValueFactory(new PropertyValueFactory<>("cAge"));

        acID.setCellValueFactory(new PropertyValueFactory<>("acID"));
        acName.setCellValueFactory(new PropertyValueFactory<>("acName"));

        bClientID.setCellValueFactory(new PropertyValueFactory<>("bcid"));
        bClientName.setCellValueFactory(new PropertyValueFactory<>("bcname"));

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

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

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
