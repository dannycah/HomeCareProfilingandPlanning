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
import javafx.scene.control.ComboBox;
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
public class ManageAccountsController implements Initializable {


    @FXML
    private Label lblId;
    @FXML
    private TableView<?> tab1;
    @FXML
    private TableColumn<?, ?> eID;
    @FXML
    private TableColumn<?, ?> fullName;
    @FXML
    private TableColumn<?, ?> position;
    @FXML
    private TableColumn<?, ?> userName;
    @FXML
    private TableColumn<?, ?> active;
    @FXML
    private Button activateUser;
    @FXML
    private Button resetPass;
    @FXML
    private Button updateStaff;
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
    private TextField zipCode;
    @FXML
    private ComboBox<?> roleCombo;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField uid;
    @FXML
    private Button myRecSave;
    @FXML
    private Button myRecCancel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void activateUser(ActionEvent event) {
    }

    @FXML
    private void resetPass(ActionEvent event) {
    }

    @FXML
    private void updateStaff(ActionEvent event) {
    }

    @FXML
    private void myRecSave(ActionEvent event) {
    }

    @FXML
    private void myRecCancel(ActionEvent event) {
    }

}
