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
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author mark
 */
public class RegisterUserController implements Initializable {


    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField mobileNum;
    @FXML
    private TextField eid;
    @FXML
    private TextField eMail;
    @FXML
    private Button userRegBtn;
    @FXML
    private Button cancelRegBtn;
    @FXML
    private DatePicker bDay;
    @FXML
    private Button clearRegBtn;
    @FXML
    private TextField address;
    @FXML
    private TextField zipCode;
    @FXML
    private ComboBox<?> roleCombo;
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

}
