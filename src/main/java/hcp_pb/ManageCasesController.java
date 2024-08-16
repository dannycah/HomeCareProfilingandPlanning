/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author mark
 */
public class ManageCasesController implements Initializable {


    @FXML
    private Label lblId;
    @FXML
    private TextField eid;
    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private DatePicker bDay;
    @FXML
    private TextField eMail;
    @FXML
    private TextField mobileNum;
    @FXML
    private TextField address;
    @FXML
    private TextField uid;
    @FXML
    private Button activateUser;
    @FXML
    private Button resetPass;
    @FXML
    private Button updateStaff;
    @FXML
    private Button myRecSave;
    @FXML
    private Button myRecCancel;
    @FXML
    private TableView<?> tab1;
    @FXML
    private TableColumn<?, ?> caseID;
    @FXML
    private TableColumn<?, ?> clientName;
    @FXML
    private TableColumn<?, ?> caseType;
    @FXML
    private TableColumn<?, ?> assignedCSO;
    @FXML
    private TableColumn<?, ?> status;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void myRecSave(ActionEvent event) {
    }

    @FXML
    private void myRecCancel(ActionEvent event) {
    }

}
