/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class TestCarePlanController implements Initializable {

    private final String DB_URL = "jdbc:mysql://localhost:3306/HCP_PBP";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "!Student1";

    @FXML
    private TextField cID;
    @FXML
    private TextField cMedicare;
    @FXML
    private TextField cFname;
    @FXML
    private TextField cAddress;
    @FXML
    private DatePicker aDate;
    @FXML
    private TextField aVenue;
    @FXML
    private TextField asssessID;
    @FXML
    private TextField csID;

    @FXML
    private Button genCP;
    @FXML
    private Button cancelCP;
    //ADL
    @FXML
    private Pane adlPane;
    @FXML
    private TextArea txtADL;
    @FXML
    private TextArea txtActons;

    private String clientName;
    private String assessmentNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setClient();
    }

    private void setClient() {

        String case_ID = "201"; // must be changed by the value of case id in the previous class (DashboardController)

        String queryclient = "SELECT "
                + "c.clientID, "
                + "CONCAT(c.fName, ' ', c.lName) AS fullName, "
                + "c.clientMedicare, "
                + "c.clientAddress, "
                + "a.assessmentDate, "
                + "a.assessVenue, "
                + "a.caseID, "
                + "a.assessmentID "
                + "FROM clientData c "
                + "JOIN clientAssessment a ON c.clientID = a.clientID "
                + "WHERE a.caseID = '" + case_ID + "';";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(queryclient)) {
            //hidden placeholder for next userid
            if (rs.next()) {

                assessmentNo = rs.getString("assessmentID");
                clientName = rs.getString("fullName");
                cID.setText(rs.getString("clientID"));
                cFname.setText(rs.getString("fullName"));
                cMedicare.setText(rs.getString("clientMedicare"));
                cAddress.setText(rs.getString("clientAddress"));
                aVenue.setText(rs.getString("assessVenue"));
                csID.setText(rs.getString("caseID"));
                asssessID.setText(rs.getString("assessmentID"));

                String assessDate = rs.getString("assessmentDate");
                if (assessDate != null && !assessDate.isEmpty()) {
                    LocalDate assessmentDate = LocalDate.parse(assessDate);
                    aDate.setValue(assessmentDate);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void genCP(ActionEvent event) {

        genCP.setDisable(true);

        //ADL GOALS - PANEL 1
        // MAKE ANOTHER SELECT STATEMENT FOR OTHER ACTIVITY OF DAILY LIVING AND THEN ADD TO THE END OF THE ADL TEXT AREA
        String queryHygiene = "SELECT * FROM clienthygiene WHERE assessmentID = '" + assessmentNo + "';";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(queryHygiene)) {
            //hidden placeholder for next userid
            if (rs.next()) {


                LocalDate assessdate = aDate.getValue();
                LocalDate newaDate = assessdate.plusYears(1);
                String newAssessDate = newaDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                String BPGLevel = rs.getString("bpgRequirement");
                String hygiene = "Independent".equalsIgnoreCase(BPGLevel) ? "maintain dignity" : "avail personal care services";
                //  String pcaReq = "Independent".equalsIgnoreCase(BPGLevel) ? "maintain dignity" : "avail personal care services";

                String Hbath = rs.getString("bathing");
                String bathing = "yes".equalsIgnoreCase(Hbath) ? "can still" : "cannot";

                String Hbodyb = rs.getString("bodyb");
                String scrub = "yes".equalsIgnoreCase(Hbodyb) ? "can still" : "cannot";

                String hGroom = rs.getString("groominScrug");
                String grooming = "yes".equalsIgnoreCase(hGroom) ? "can still" : "cannot";

                String showering = rs.getString("showerIntervals");

                String st = rs.getString("showerPrefDay");
                String showerTime = "AM".equalsIgnoreCase(st) ? "the morning" : "the afternoon";

                String carer = rs.getString("showerCarerGender");
                String careReq = "No".equalsIgnoreCase(st) ? "No one" : carer;

                String dressingLevel = rs.getString("dressingRequirement");
                String dressingReq = "Independent".equalsIgnoreCase(dressingLevel) ? "maintain dignity" : "avail assistance";

                String hDress = rs.getString("dressing");
                String dressing = "yes".equalsIgnoreCase(hDress) ? "can still" : "cannot";

                String hUndress = rs.getString("undressing");
                String undress = "yes".equalsIgnoreCase(hUndress) ? "can still" : "cannot";

                String hSocks = rs.getString("socks");
                String Socks = "yes".equalsIgnoreCase(hSocks) ? "can still" : "cannot";

                String hUnsocks = rs.getString("shoes");
                String Unsocks = "yes".equalsIgnoreCase(hUnsocks) ? "can still" : "cannot";

                String dt = rs.getString("showerPrefDay");
                String dresingTime = "AM".equalsIgnoreCase(dt) ? "the morning" : "the afternoon";

                String dcarer = rs.getString("showerCarerGender");
                String dressingCarer = "No".equalsIgnoreCase(st) ? "No one" : dcarer;

                //GOALS
                txtADL.setText("Currently, " + clientName + " requires " + BPGLevel + " in bathing, showering, personal hygiene, and grooming tasks.\n"
                        + clientName + " would like to " + " " + hygiene + " in   proper hygiene. \n"
                        + clientName + " " + bathing + " walk to the bathroom or shower room.\n"
                        + clientName + " " + scrub + " scrub with a sponge or brush any parts of the body. \n"
                        + clientName + " " + grooming + " do grooming properly. \n"
                        + clientName + " showers " + showering + " preferably during " + showerTime + ". \n"
                        + clientName + " prefers " + careReq + " in doing personal hygiene, bathing and grooming. \n\n"
                        + clientName + " requires " + dressingReq + " in dressing and undressing.\n"
                        + clientName + " would like to " + " " + dressingReq + " in  tasks like dressing and undressing.\n"
                        + clientName + " " + dressing + " dress on a personal capacity.\n"
                        + clientName + " " + undress + " undress on a personal capacity.\n"
                        + clientName + " " + Socks + " put on shoes.\n"
                        + clientName + " " + Unsocks + " take off on shoes.\n"
                        //        + clientName + " showers " + showering + " preferably during " + showerTime + ". \n"
                        + clientName + " prefers " + careReq + " in doing personal hygiene, bathing and grooming. \n");

                //ACTIONS
                txtActons.setText("Case Manager to review and monitor the ADL requirements of " + clientName + ".\n"
                        + "Case Manager trough CSO will arrange an approriate support staff to " + hygiene + " during personal care. \n"
                +"please cpom,pley");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
