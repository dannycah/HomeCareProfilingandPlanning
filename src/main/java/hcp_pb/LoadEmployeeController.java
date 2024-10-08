/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;

public class LoadEmployeeController implements Initializable, CloseCallBack {

    @FXML
    private TextField empID;

    @FXML
    private TextField empFullName;
    @FXML
    private TextField empStat;
    @FXML
    private DatePicker empStart;
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
    private Button chooseFile;
    @FXML
    private Button upload;

    @FXML
    private TextField filePathTextField;

    private File selectedFile;
    
    
     private static final String IS_OPEN_FILE = "config/isOpen.txt"; // Relative path to isOpen file
    private static final String DB_CONFIG_FILE = "config/dbConfig.txt"; // Relative path to dbConfig file

    private static Scene scene;
    private String DB_URL;
    private String DB_USER;
    private String DB_PASSWORD;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           loadDatabaseConfig();
        employeeID.setCellValueFactory(new PropertyValueFactory<>("eid"));
        fullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        startD.setCellValueFactory(new PropertyValueFactory<>("startD"));
        endD.setCellValueFactory(new PropertyValueFactory<>("endD"));

        loadEmpDataFromDatabase();

        empTbl.setOnMouseClicked(event -> {
            try {
                handleEmpRowSelect(event);
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

    private void loadEmpDataFromDatabase() {
        String Empquery = "SELECT * FROM employeeList";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Empquery)) {

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

    private void loadEmpDetails(int employeeID) {
        String empListQuery = "SELECT * FROM employeeList WHERE employeeID = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(empListQuery)) {

            preparedStatement.setInt(1, employeeID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    empID.setText(String.valueOf(resultSet.getInt("employeeID")));
                    empFullName.setText(resultSet.getString("fullName"));
                    empStart.setValue(LocalDate.parse(resultSet.getString("startDate")));
                    empStat.setText(resultSet.getString("activeFlag"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleEmpRowSelect(MouseEvent event) throws SQLException {
        employeeList selectedEmp = empTbl.getSelectionModel().getSelectedItem();
        if (selectedEmp != null) {
            loadEmpDetails(selectedEmp.getEid());
        }
    }

    @Override
    public void onClose() {
        loadEmpDataFromDatabase(); // Reload data when the second form closes
    }
}
