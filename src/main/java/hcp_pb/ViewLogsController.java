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
/**
 * FXML Controller class
 *
 * @author mark
 */
public class ViewLogsController implements Initializable {


    @FXML
    private Label lblId;
    @FXML
    private DatePicker d1;
    @FXML
    private DatePicker d2;
    @FXML
    private Button gen;
    @FXML
    private TableView<?> tab5;
    @FXML
    private TableColumn<?, ?> ID;
    @FXML
    private TableColumn<?, ?> actions;
    @FXML
    private TableColumn<?, ?> date;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void gen(ActionEvent event) {
    }

}
