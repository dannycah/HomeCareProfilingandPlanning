/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class BudgetViewerController implements Initializable {

    @FXML
    private Label lblId;

    @FXML
    private TableView<budgetClient> tblBudgetClient;
    @FXML
    private TableColumn<budgetClient, Integer> bClientID;
    @FXML
    private TableColumn<budgetClient, String> bClientName;

    @FXML
    private TextField txtcname;
    @FXML
    private TextField txtCdate;
    @FXML
    private TextField txtCdob;
    @FXML
    private TextField txtPby;
    @FXML
    private TextField txtCli;
    @FXML
    private TextField txtFlevel;
    @FXML
    private TextField txtGovS;
    @FXML
    private TextField txtSuppLessC;
    @FXML
    private TextField txtSuppLessP;
    @FXML
    private TextField txtMonth1;
    @FXML
    private TextField txtMonth7;
    @FXML
    private TextField txtMonth8;
    @FXML
    private TextField txtTotalA;
    @FXML
    private TextField txtTotalExpense;
    
    @FXML
    private TableView<BudgetEntry> tblBudget;

    @FXML
    private TableColumn<BudgetEntry, String> careDesc;
    @FXML
    private TableColumn<BudgetEntry, String> numUnits;
    @FXML
    private TableColumn<BudgetEntry, String> totalCare;
    @FXML
    private Button extractBudget;
    @FXML
    private Button printBudget;
    private int budgetCaseid;

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
        
        bClientID.setCellValueFactory(new PropertyValueFactory<>("bcid"));
        bClientName.setCellValueFactory(new PropertyValueFactory<>("bcname"));

        careDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        numUnits.setCellValueFactory(new PropertyValueFactory<>("numUnits"));
        totalCare.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadBudgetClientDatabase();

        tblBudgetClient.setOnMouseClicked(event -> {
            try {
                handleclientbudget(event);

            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, "Error handling row selection", ex);
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
//                alert.showAndWait();
                    System.exit(1); // Exit if an error occurs while reading the file
        }
    }



    


@FXML
private void extractBudget(ActionEvent event) throws IOException {
    
        // Check if a client is selected in the table
    if (tblBudgetClient.getSelectionModel().isEmpty()) {
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setTitle("No Client Selected");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Please ensure that a client is selected");
        errorAlert.showAndWait();
        return; // Exit the method if no client is selected
    }

    
    // Confirm before generating the PDF
    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation");
    confirmationAlert.setHeaderText(null);
    confirmationAlert.setContentText("You are about to generate the budget PDF. Do you want to proceed?");

    Optional<ButtonType> result = confirmationAlert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // Create a FileChooser to select the save location and file name
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFile != null) {
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage(PDRectangle.A4); // Set page size
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    PDType1Font fontBold = PDType1Font.HELVETICA_BOLD;
                    PDType1Font fontRegular = PDType1Font.HELVETICA;
                    
                    contentStream.setFont(fontBold, 16); // Header font
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, 750); // Start position

                    // Add static header
                    contentStream.showText("Sample Company Home Care Provider");
                    contentStream.newLineAtOffset(0, -20);
                     contentStream.setFont(fontBold, 12);
                    contentStream.showText("120 Spencer St, Melbourne VIC 3000");
                    contentStream.newLineAtOffset(0, -10); // More space after header
                             contentStream.showText("--------------------------------------------------------------------------------");
                    contentStream.newLineAtOffset(0, -5); // Space before data
                             contentStream.showText("--------------------------------------------------------------------------------");
                    contentStream.newLineAtOffset(0, -20); // Space before data

                    // Add budget plan header
                    contentStream.setFont(fontBold, 14); // Change font for the header
                    contentStream.showText("BUDGET PLAN:");
                    contentStream.newLineAtOffset(0, -30); // Space after the header

                    // Reset font for details
                    contentStream.setFont(fontRegular, 10);
                    contentStream.showText("Client Number:           " + txtCli.getText());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Client Name:              " + txtcname.getText());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Assessed Level:        " + txtFlevel.getText());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Date of Birth:             " + txtCdob.getText());
                    contentStream.newLineAtOffset(0, -30); // Space before management details

                    // Management details header
                    contentStream.setFont(fontBold, 12);
                    contentStream.showText("Management Details:");
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.setFont(fontRegular, 10);
                    contentStream.showText("Government Subsidy:                         " + txtGovS.getText());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("    Care Management Fee:                   " + txtSuppLessC.getText());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("    Package Management Fee:          " + txtMonth7.getText());
                    contentStream.newLineAtOffset(0, -30); // Space before expenses

                    // Add expenses header
                    contentStream.setFont(fontBold, 12);
                    contentStream.showText("Expenses / Services:");
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Services                 Units      Cost");
                    contentStream.newLineAtOffset(0, -20); // Space before data

                    // Add expense data
                    double totalExpenseSum = 0; // Reset total expenses sum
                    for (BudgetEntry budget : tblBudget.getItems()) {
                        contentStream.setFont(fontRegular, 10);
                        contentStream.showText(String.format("%-25s %-10d $%.2f", budget.getDescription(), budget.getNumUnits(), budget.getTotal()));
                        contentStream.newLineAtOffset(0, -20); // Space after each entry
                        totalExpenseSum += budget.getTotal(); // Calculate total expenses
                    }

                    // Total Expenses
                     contentStream.showText("--------------------------------------------------------------------------------");
                    contentStream.newLineAtOffset(0, -10); // Space before data
                    contentStream.showText("Total Expenses:                           $" + String.format("%.2f", totalExpenseSum));
                    contentStream.newLineAtOffset(0, -40); // Space before footer

                    // Add footer
                     contentStream.setFont(fontRegular, 9);
                    contentStream.showText("Prepared by: " + txtPby.getText() + "                           Date Created: " + txtCdate.getText());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.endText(); // End the text block
                }

                // Save the document
                document.save(selectedFile);
                System.out.println("PDF created successfully at: " + selectedFile.getAbsolutePath());
                
                
                 // Show success alert
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("PDF created successfully at: " + selectedFile.getAbsolutePath());
                successAlert.showAndWait();
                
            } catch (IOException e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("An error occurred while creating the PDF.");
                errorAlert.showAndWait();
            }
        }
    }
}


@FXML
private void printBudget(ActionEvent event) {
    // Check if a client is selected in the table
    if (tblBudgetClient.getSelectionModel().isEmpty()) {
        Alert errorAlert = new Alert(Alert.AlertType.WARNING);
        errorAlert.setTitle("No Client Selected");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Please ensure that a client is selected");
        errorAlert.showAndWait();
        return; // Exit the method if no client is selected
    }

    // Confirm before printing
    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation");
    confirmationAlert.setHeaderText(null);
    confirmationAlert.setContentText("You are about to print the budget. Do you want to proceed?");

    Optional<ButtonType> result = confirmationAlert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            // Create a VBox or Pane to hold the printable content
            VBox printLayout = new VBox();
            printLayout.setSpacing(10); // Spacing between elements

            // Add static header
            Text header = new Text("Sample Company Home Care Provider");
            header.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
            printLayout.getChildren().add(header);
            
            Text address = new Text("120 Spencer St, Melbourne VIC 3000");
            address.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
            printLayout.getChildren().add(address);
            printLayout.getChildren().add(new Separator());

            // Add budget plan header
            Text budgetPlanHeader = new Text("BUDGET PLAN:");
            budgetPlanHeader.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
            printLayout.getChildren().add(budgetPlanHeader);

            // Add client details
            printLayout.getChildren().add(new Text("Client Number:         " + txtCli.getText()));
            printLayout.getChildren().add(new Text("Client Name:             " + txtcname.getText()));
            printLayout.getChildren().add(new Text("Assessed Level:         " + txtFlevel.getText()));
            printLayout.getChildren().add(new Text("Date of Birth:            " + txtCdob.getText()));
            printLayout.getChildren().add(new Separator());

            // Add management details
            Text managementHeader = new Text("Management Details:");
            managementHeader.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
            printLayout.getChildren().add(managementHeader);

            printLayout.getChildren().add(new Text("Government Subsidy:                " + txtGovS.getText()));
            printLayout.getChildren().add(new Text("Care Management Fee:               " + txtSuppLessC.getText()));
            printLayout.getChildren().add(new Text("Package Management Fee:     " + txtMonth7.getText()));
            printLayout.getChildren().add(new Separator());

            // Add expenses header
            Text expensesHeader = new Text("Expenses / Services:");
            expensesHeader.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
            printLayout.getChildren().add(expensesHeader);

            // Add table-like header
            printLayout.getChildren().add(new Text("Services                        Units      Cost"));
            
            // Add expense data
            double totalExpenseSum = 0; // Reset total expenses sum
            for (BudgetEntry budget : tblBudget.getItems()) {
                String entry = String.format("%-25s %-10d $%.2f", budget.getDescription(), budget.getNumUnits(), budget.getTotal());
                printLayout.getChildren().add(new Text(entry));
                totalExpenseSum += budget.getTotal(); // Calculate total expenses
            }

            // Total Expenses
            printLayout.getChildren().add(new Separator());
            printLayout.getChildren().add(new Text("Total Expenses:                            $" + String.format("%.2f", totalExpenseSum)));
            printLayout.getChildren().add(new Separator());

            // Add footer
            Text footer = new Text("Prepared by: " + txtPby.getText() + "    Date Created: " + txtCdate.getText());
            footer.setFont(Font.font("Helvetica", FontWeight.NORMAL, 9));
            printLayout.getChildren().add(footer);

            // Print the layout
            if (job.showPrintDialog(tblBudget.getScene().getWindow())) {
                boolean success = job.printPage(printLayout);
                if (success) {
                    job.endJob(); // End the print job
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Print Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Printing completed successfully.");
                    successAlert.showAndWait();
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




    public void loadBudgetClientDatabase() {
        // SQL query
        String query = "SELECT cc.caseID as bCaseID, cd.clientID, CONCAT(cd.fName, ' ', cd.lName) AS fullName "
                + "FROM clientdata cd "
                + "JOIN clientcases cc ON cd.clientID = cc.clientID "
                + "WHERE cc.assessmentStatus = 'Closed';";

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
            // Clear fields when a new selection is made
            clearFields();

            budgetClient selectedClient = tblBudgetClient.getSelectionModel().getSelectedItem();
            if (selectedClient != null) {
                // Load the budget details for the selected client
                loadBudgetClientDets(selectedClient.getBcid());

//            loadClientExpenseData();
                loadClientExpenseData(selectedClient.getBcid());
            }
            // If selectedClient is null, fields are already cleared
        }
    }

  
      private void clearFields() {
        txtCli.clear();
        txtcname.clear();
        txtCdob.clear();
        txtFlevel.clear();
        txtPby.clear();
        txtCdate.clear();
        txtGovS.setText("0.00");
        txtSuppLessP.setText("0");
        txtSuppLessC.setText("0");
        txtMonth1.setText("0.00");
        txtMonth7.setText("0.00");
        txtMonth8.setText("0.00");
        txtTotalA.setText("0.00");
        txtTotalExpense.clear();
    }

    private void loadBudgetClientDets(int cid) {
        // SQL query to fetch data from budgetSummary by clientID
        String budgetSummaryQuery = "SELECT clientID, clientName, clientDOB, clientLevel, prepBy, prepDate, "
                + "dailySubsidy, manPerc, carePerc, monthlySubsidy, manFee, careFee, toSpend "
                + "FROM budgetSummary WHERE clientID = ? "
                + "ORDER BY prepDate DESC LIMIT 1";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Prepare the query to fetch budget summary details
            try (PreparedStatement preparedStatement = connection.prepareStatement(budgetSummaryQuery)) {
                preparedStatement.setInt(1, cid);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Set fields with values from the ResultSet
                        txtCli.setText(resultSet.getString("clientID"));
                        txtcname.setText(resultSet.getString("clientName"));
                        txtCdob.setText(resultSet.getString("clientDOB"));
                        txtFlevel.setText(resultSet.getString("clientLevel"));
                        txtPby.setText(resultSet.getString("prepBy"));
                        txtCdate.setText(resultSet.getString("prepDate"));
                        txtGovS.setText(resultSet.getString("dailySubsidy")); // Assuming this maps to txtGovS
                        txtSuppLessP.setText(resultSet.getString("manPerc")); // Adjust as needed
                        txtSuppLessC.setText(resultSet.getString("carePerc")); // Adjust as needed
                        txtMonth1.setText(resultSet.getString("monthlySubsidy")); // Adjust as needed
                        txtMonth7.setText(resultSet.getString("manFee")); // Adjust as needed
                        txtMonth8.setText(resultSet.getString("careFee")); // Adjust as needed
                        txtTotalA.setText(resultSet.getString("toSpend")); // Assuming this maps to txtTotalA
                    } else {
                        // Handle case where no data is found
                        //  System.out.println("No data found for clientID: " + cid);
                    }
                }
            }

            // Now fetch the expense data for the current client
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Method to load client expense data
  private void loadClientExpenseData(int cid) {
    // SQL query to fetch budget data from expenseSummary for the given clientID with today's prepDate
   String clientExpenseQuery = "SELECT "
        + "serviceDesc, "
        + "numUnits, "
        + "subTotal "
        + "FROM expenseSummary "
        + "WHERE clientID = ? "
        + "AND prepDate = ("
        + "    SELECT MAX(prepDate) "
        + "    FROM expenseSummary "
        + "    WHERE clientID = ?"
        + ") "
        + "ORDER BY prepDate DESC;";


    double totalExpenseSum = 0;  // Variable to hold the sum of total expenses

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); 
         PreparedStatement preparedStatement = connection.prepareStatement(clientExpenseQuery)) {
        
        // Set the client ID parameter
        preparedStatement.setInt(1, cid);
             preparedStatement.setInt(2, cid);
        
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            // Clear existing data in tblBudget
            tblBudget.getItems().clear();

            // Debugging: Check if resultSet has rows
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No data found for client ID: " + cid);
            }

            // Process the result set and populate the TableView
            while (resultSet.next()) {
                String description = resultSet.getString("serviceDesc");
                int numUnits = resultSet.getInt("numUnits");
                double subTotal = resultSet.getDouble("subTotal");
             

                // Add to the total expense sum
                totalExpenseSum += subTotal;

           

                // Create a BudgetEntry object
                BudgetEntry budgets = new BudgetEntry(description, numUnits, subTotal);

                // Add the BudgetEntry object to the TableView
                tblBudget.getItems().add(budgets);
            }

            // Set the total expense in the txtTotalExpense TextField
            txtTotalExpense.setText(String.format("%.2f", totalExpenseSum));
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


      
      
      
      
      

}
