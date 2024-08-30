/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author mark
 */
public class NewClientController implements Initializable {

    
    
      @FXML
    private TextField cID;
        @FXML
    private TextField cMedicare;
              
                @FXML
    private TextField cFname;
    
           @FXML
    private TextField cLname;
           
  @FXML
    private DatePicker cBday;
           
                  @FXML
    private TextField cAddress;
                  
                  
                         @FXML
    private TextField cMobile;
                         
                                @FXML
    private TextField cEmail;
                                
                     @FXML
    private TextField cEmergencyName;
                     
                            @FXML
    private TextField cRelation;
                            
                   @FXML
    private TextField cEmergencyMob;
                   
                          @FXML
    private TextField cEmergencyMail;
                          
                  @FXML
                          private Button
                          saveNewClientBtn;
                        @FXML          
                     private Button cancelClientReg      ;
           
           
                  

    @FXML
    private Button saveNewClient;
    
    
      private static final String DB_URL = "jdbc:mysql://localhost:3306/HCP_PBP";
        private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "!Student1";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        getTheMaxCid();
         cBday.setValue(LocalDate.now());
    }
    
    
     @FXML
    private void saveNewClientBtn(ActionEvent event) throws IOException {

        String cId = cID.getText();
		String mediCare = cMedicare.getText();
        String firstName = cFname.getText();
        String lastName = cLname.getText();
        String birthDate = cBday.getValue() != null ? cBday.getValue().toString() : "";
     
	
	 
        String addr = cAddress.getText();
		   String email = cEmail.getText();
        String mobile = cMobile.getText();

        String eContactName = cEmergencyName.getText();
 String eRelation = cRelation.getText();
  String eMob = cEmergencyMob.getText();
    String eMail = cEmergencyMail.getText();




        //validate input (call validateinput method)
        if (!validateInput(firstName, lastName, email, mobile, addr, mediCare, eContactName, eRelation, eMob, email)) {
            return;
        }

       
      
        //insert to db
        String regClient = "INSERT INTO clientdata (clientID, fName, lName, clientMedicare, clientAddress, clientMobile, clientBday, emergencyContactPerson,emergencyContactNumber, relationship,levelID) "
                + "VALUES ('" + cId + "','" + firstName + "', '" + lastName + "', "
                + "'" + mediCare + "', '" + addr + "', '" + mobile + "', "
                + "'" + birthDate + "', '" + eContactName + "', '" + eMob + "', "
                + "'" + eRelation + "','1')";

 

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Execute the query
            int rowsAffected = stmt.executeUpdate(regClient);
          
            
            if (rowsAffected > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Client Record Created");
                alert.showAndWait();
                
               
Stage stage = (Stage) saveNewClientBtn.getScene().getWindow();
stage.close();

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

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to save data to the database");
        }

    }
	
	


    
    
    
    
    
    
    
    	    private void getTheMaxCid() {

        String query = "SELECT clientID FROM clientdata ORDER BY clientID DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            //hidden placeholder for next userid
            if (rs.next()) {
                int lastUser = rs.getInt("clientID");
                cID.setText(String.valueOf(lastUser + 1));
            } else {
                cID.setText("200001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
            
            
            
            
	 private boolean validateInput(String firstName, String lastName, String email, String mobile, String addr, String mediCare, String eContactName, String eRelation, String eMob, String Email) {
        String numeric = "\\d+";

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()
                || mobile.isEmpty() || addr.isEmpty() || mediCare.isEmpty() || eContactName.isEmpty() || eRelation.isEmpty()|| eMob.isEmpty()|| Email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required!");
            return false;
        }

        if (!mobile.matches(numeric)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Contact should contain only numbers!");
            return false;
        }
		
		    if (!eMob.matches(numeric)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Contact should contain only numbers!");
            return false;
        }

        if (!mediCare.matches(numeric)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Medicare Number should contain only numbers!");
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
	
    
             
             
       @FXML
    private void clearRegClient(ActionEvent event) throws IOException {
   
             
                    // cID.setText("");
        cMedicare.setText("");
        cFname.setText("");
        cLname.setText("");
        cAddress.setText("");
        cMobile.setText("");
        cEmail.setText("");
        cEmergencyName.setText("");
        cRelation.setText("");
        cEmergencyMob.setText("");
        cEmergencyMail.setText("");
             

    }       
    
        @FXML
    private void cancelClientReg() {
        // Get the current stage (window)
        Stage stage = (Stage) cancelClientReg.getScene().getWindow();
        // Close the stage
        stage.close();
    }
             
             
             
             
             
             
             
             
}
