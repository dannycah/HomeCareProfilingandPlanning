/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author mark
 */
public class CreateCaseController implements Initializable {


    @FXML
    private Label lblId;
    @FXML
    private TextField uid1;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
