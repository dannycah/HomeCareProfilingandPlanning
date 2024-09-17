/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hcp_pb;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import javafx.scene.control.ProgressIndicator;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mark
 */
public class AssessmentFormController implements Initializable {

    //db connections
    private static final String DB_URL = "jdbc:mysql://localhost:3306/HCP_PBP";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "!Student1";

    //closing
    @FXML
    private Button completeAssessment;
    @FXML
    private Button cancelAssess;
    @FXML 
            private TextArea remarks;
    
    
    @FXML
    private Label headerLbl;
    @FXML
    private Label manageCaseLbl;
    @FXML
    private Label viewRequestsLbl;
    @FXML
    private Label createCaseLbl;
    @FXML
    private Label carePlanlbl;
    @FXML
    private Label budgetLbl;
    @FXML
    private Label reportsLbl;
    @FXML
    private Pane mainPanel;

    // Side Labels/ 
    @FXML
    private Label lblClientInfo;
    @FXML
    private Label lblPersonalHygiene;
    @FXML
    private Label lblOralHygiene;
    @FXML
    private Label lblToileting;
    @FXML
    private Label lblMobility;
    @FXML
    private Label lblOtherMobility;
    @FXML
    private Label lblNutrition;
    @FXML
    private Label lblSkinIntegrity;
    @FXML
    private Label lblMentalHealth;
    @FXML
    private Label lblMedication;
    @FXML
    private Label lblPainManagement;
    @FXML
    private Label lvlHousing;
    @FXML
    private Label lblHouseEnvironment;
    @FXML
    private Label lblEndOfLife;
    @FXML
    private Label lblContactInfo;

    //Client and Assessment Details
    @FXML
    private TextField caseNo;
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
    private ComboBox cLevel;
    @FXML
    private TextField csoName;
    @FXML
    private DatePicker aDate;
    @FXML
    private ComboBox aVenue;
    @FXML
    private TextField aVenueOthers;
    @FXML
    private Button cancelAssessment;
    @FXML
    private Button startAssesmentBtn;
    @FXML
    private TextField assessNo;

    //Personal Hygiene/  
    @FXML
    private Pane paneClientHygiene;
    //Question 1/
    @FXML
    private RadioButton rdoBpgRequirement1;
    @FXML
    private RadioButton rdoBpgRequirement2;
    @FXML
    private RadioButton rdoBpgRequirement3;
    @FXML
    private RadioButton rdoBpgRequirement4;
    private ToggleGroup P1Q1_toggleGroup;

    //Question 2/
    @FXML
    private RadioButton rdoBathingY;
    @FXML
    private RadioButton rdoBathingN;
    private ToggleGroup P1Q2_toggleGroup;

    //Question 3/
    @FXML
    private RadioButton rdoBodyScrabY;
    @FXML
    private RadioButton rdoBodyScrabN;
    private ToggleGroup P1Q3_toggleGroup;

    //Question 4/
    @FXML
    private RadioButton rdoGroomingY;
    @FXML
    private RadioButton rdoGroomingN;
    private ToggleGroup P1Q4_toggleGroup;

    //Question 5/
    @FXML
    private Pane showerIntervalsD;
    @FXML
    private RadioButton rdoShowerIntervalsD;
    @FXML
    private RadioButton rdoShowerIntervals2;
    private ToggleGroup P1Q5_toggleGroup;

    //Question 6/
    @FXML
    private RadioButton rdoShowerCarerReqY;
    @FXML
    private RadioButton rdoShowerCarerReqN;

    //Question 7/
    @FXML
    private RadioButton rdoShowerCarerGenderN;
    @FXML
    private RadioButton rdoShowerCarerGenderA;
    @FXML
    private RadioButton rdoShowerCarerGenderM;
    @FXML
    private RadioButton rdoShowerCarerGenderF;
    private ToggleGroup P1Q7_toggleGroup;

    //Question 8/
    @FXML
    private RadioButton rdoShowerPrefDayP;
    @FXML
    private RadioButton rdoShowerPrefDayA;
    private ToggleGroup P1Q8_toggleGroup;

    //Question 9/
    @FXML
    private RadioButton rdoDressingRequirement1;
    @FXML
    private RadioButton rdoDressingRequirement2;
    @FXML
    private RadioButton rdoDressingRequirement3;
    @FXML
    private RadioButton rdoDressingRequirement4;
    private ToggleGroup P1Q9_toggleGroup;

    //Question 10/
    @FXML
    private RadioButton rdoDressingY;
    @FXML
    private RadioButton rdoDressingN;
    private ToggleGroup P1Q10_toggleGroup;

    //Question 11/
    @FXML
    private RadioButton rdoUndressingY;
    @FXML
    private RadioButton rdoUndressingN;
    private ToggleGroup P1Q11_toggleGroup;

    //Question 12/
    @FXML
    private RadioButton rdoSocksY;
    @FXML
    private RadioButton rdoSocksN;
    private ToggleGroup P1Q12_toggleGroup;

    //Question 13/
    @FXML
    private RadioButton rdoShoesY;
    @FXML
    private RadioButton rdoShoesN;
    private ToggleGroup P1Q13_toggleGroup;

//    //Question 14/
//    @FXML
//    private RadioButton rdoDressingCarerReqY;
//    @FXML
//    private RadioButton rdoDressingCarerReqN;
//    private ToggleGroup P1Q14_toggleGroup;
    //Question 15/
    @FXML
    private RadioButton rdoDressingCarerGenderN;
    @FXML
    private RadioButton rdoDressingCarerGenderA;
    @FXML
    private RadioButton rdoDressingCarerGenderM;
    @FXML
    private RadioButton rdoDressingCarerGenderF;
    private ToggleGroup P1Q15_toggleGroup;

    //Question 16/
    @FXML
    private RadioButton rdoDressingPrefDayA;
    @FXML
    private RadioButton rdoDressingPrefDayP;
    private ToggleGroup P1Q16_toggleGroup;

    //Back and Next Button
    @FXML
    private Button btnBackClientHygiene;
    @FXML
    private Button btnNextClientHygiene;

    // Oral Hygiene/  
    @FXML
    private Pane paneOralHygiene;

    //Question 1/
    @FXML
    private RadioButton rdoOralHygieneRequirement1;
    @FXML
    private RadioButton rdoOralHygieneRequirement3;
    @FXML
    private RadioButton rdoOralHygieneRequirement4;
    @FXML
    private RadioButton rdoOralHygieneRequirement2;
    private ToggleGroup O1Q1_toggleGroup;

    //Question 2/
    @FXML
    private RadioButton rdoHoldToothbrushY;
    @FXML
    private RadioButton rdoHoldToothbrushN;
    private ToggleGroup O1Q2_toggleGroup;

    //Question 3/
    @FXML
    private RadioButton rdoToothPasteSqY;
    @FXML
    private RadioButton rdoToothPasteSqN;
    private ToggleGroup O1Q3_toggleGroup;

    //Question 4/
    @FXML
    private RadioButton rdoMouthRinseY;
    @FXML
    private RadioButton rdoMouthRinseN;
    private ToggleGroup O1Q4_toggleGroup;

    //Question 5/
    @FXML
    private RadioButton rdoMouthIssuesY;
    @FXML
    private RadioButton rdoMouthIssuesG;
    @FXML
    private RadioButton rdoMouthIssuesN;
    @FXML
    private RadioButton rdoMouthIssuesM;
    private ToggleGroup O1Q5_toggleGroup;

    //Question 6/
    @FXML
    private RadioButton rdoTeethFormsCrowns;
    @FXML
    private RadioButton rdoTeethFormsD;
    @FXML
    private RadioButton teethFormsCaps;
    @FXML
    private RadioButton rdoTeethFormsP;
    private ToggleGroup O1Q6_toggleGroup;

    //Question 7/
    @FXML
    private DatePicker dateDentistVisit;
    //Question 8/
    @FXML
    private DatePicker dateLastVisit;
    //Question 9/
    @FXML
    private TextArea TextDentistName;

    //Back and Next Button
    @FXML
    private Button btnBackOralHygiene;
    @FXML
    private Button btnNextOralHygiene;

    //Toileting/ 
    @FXML
    private Pane paneToileting;
    //Question 1/
    @FXML
    private RadioButton rdoToiletingRequirement1;
    @FXML
    private RadioButton rdoToiletingRequirement2;
    @FXML
    private RadioButton rdoToiletingRequirement3;
    @FXML
    private RadioButton rdoToiletingRequirement4;
    private ToggleGroup T1Q1_toggleGroup;

    //Question 2/
    @FXML
    private RadioButton rdoWalkToiletY;
    @FXML
    private RadioButton rdoWalkToiletN;
    private ToggleGroup T1Q2_toggleGroup;

    //Question 3/
    @FXML
    private RadioButton rdoPantsOffY;
    @FXML
    private RadioButton rdoPantsOffN;
    private ToggleGroup T1Q3_toggleGroup;

    //Question 4/
    @FXML
    private RadioButton rdoToiletSittingY;
    @FXML
    private RadioButton rdoToiletSittingN;
    private ToggleGroup T1Q4_toggleGroup;

    //Question 5/
    @FXML
    private RadioButton rdoPWashingY;
    @FXML
    private RadioButton rdoPWashingN;
    private ToggleGroup T1Q5_toggleGroup;

    //Question 6/
    @FXML
    private RadioButton rdoPDryingY;
    @FXML
    private RadioButton rdoPDryingN;
    private ToggleGroup T1Q6_toggleGroup;

    //Question 7/
    @FXML
    private RadioButton rdoContinenceRequirement1;
    @FXML
    private RadioButton rdoContinenceRequirement2;
    @FXML
    private RadioButton rdoContinenceRequirement3;
    @FXML
    private RadioButton rdoContinenceRequirement4;
    private ToggleGroup T1Q7_toggleGroup;

    //Question 8/
    @FXML
    private RadioButton rdoSetupPadsY;
    @FXML
    private RadioButton rdoSetupPadsN;
    private ToggleGroup T1Q8_toggleGroup;

    //Question 9/ 
    @FXML
    private RadioButton rdoPWipeDryY;
    @FXML
    private RadioButton rdoPWipeDryN;
    private ToggleGroup T1Q9_toggleGroup;

    //Question 10/
    @FXML
    private TextField txtContinenceNeed;

    //Question 11/
    @FXML
    private TextField txtSize;

    //Question 12/
    @FXML
    private TextField txtContinenceFund;

    //Back and Next Button
    @FXML
    private Button btnContinenceNeeds;
    @FXML
    private Button btnNextContinenceNeeds;

    //Mobility Needs/
    @FXML
    private Pane paneMobilityNeeds;

    //Question 1/ 
    @FXML
    private RadioButton rdoMobilityRequirement1;
    @FXML
    private RadioButton rdoMobilityRequirement2;
    @FXML
    private RadioButton rdoMobilityRequirement3;
    private ToggleGroup M1Q1_toggleGroup;

    //Question 2/
    @FXML
    private RadioButton rdoMobilityIssueY;
    @FXML
    private RadioButton rdoMobilityIssueN;
    private ToggleGroup M1Q2_toggleGroup;

    //Question 3/
    @FXML
    private TextArea txtIssueDets;

    //Question 4/
    @FXML
    private RadioButton rdoTStandY;
    @FXML
    private RadioButton rdoTStandN;
    private ToggleGroup M1Q4_toggleGroup;

    //Question 5/
    @FXML
    private RadioButton rdoTSitY;
    @FXML
    private RadioButton rdoTSitN;
    private ToggleGroup M1Q5_toggleGroup;

    //Question 6/
    @FXML
    private RadioButton rdoTLyingY;
    @FXML
    private RadioButton rdoTLyingN;
    private ToggleGroup M1Q6_toggleGroup;

    //Question 7/
    @FXML
    private RadioButton rdoTSittingY;
    @FXML
    private RadioButton rdoTSittingN;
    private ToggleGroup M1Q7_toggleGroup;

    //Question 8/
    @FXML
    private RadioButton rdoWalkingY;
    @FXML
    private RadioButton rdoWalkingN;
    private ToggleGroup M1Q8_toggleGroup;

    //Question 9/
    @FXML
    private RadioButton rdoCarRideY;
    @FXML
    private RadioButton rdoCarRideN;
    private ToggleGroup M1Q9_toggleGroup;

    //Question 10/
    @FXML
    private RadioButton rdoUseStairsY;
    @FXML
    private RadioButton rdoUseStairsN;
    private ToggleGroup M1Q10_toggleGroup;

    //Question 11/
    @FXML
    private RadioButton rdoMobilityAids4;
    @FXML
    private RadioButton rdoMobilityAidsWalking;
    @FXML
    private RadioButton rdoMobilityAidsWheel;
    @FXML
    private RadioButton rdoMobilityAidsM;
    @FXML
    private RadioButton rdoMobilityAidsR;
    private ToggleGroup M1Q11_toggleGroup;

    //Question 12/
    @FXML
    private RadioButton rdoLifterRequirementY;
    @FXML
    private RadioButton rdoLifterRequirementN;
    private ToggleGroup M1Q12_toggleGroup;

    //Question 13/
    @FXML
    private RadioButton rdoFallHistoryY;
    @FXML
    private RadioButton rdoFallHistoryN;
    private ToggleGroup M1Q13_toggleGroup;

    //Question 14/
    @FXML
    private TextArea txtFallDets;

    //Back and Next Button
    @FXML
    private Button btnBackMobilityNeeds;
    @FXML
    private Button btnNextMobilityNeeds;


    // Other Mobility/
    @FXML
    private Pane paneMobilityNeeds1;

    //Question 1/
    @FXML
    private RadioButton rdoExerciseY;
    @FXML
    private RadioButton rdoExerciseN;
    private ToggleGroup OM1Q1_toggleGroup;

    //Question 2/
    @FXML
    private TextArea txtExerciseDets;

    //Question 3/
    @FXML
    private RadioButton rdoAlliedHealthRefY;
    @FXML
    private RadioButton rdoAlliedHealthRefN;
    private ToggleGroup OM1Q3_toggleGroup;

    //Question 4/
    @FXML
    private RadioButton rdoUnsteadyY;
    @FXML
    private RadioButton rdoUnsteadyN;
    private ToggleGroup OM1Q4_toggleGroup;

    //Question 5/
    @FXML
    private RadioButton rdoStoopedY;
    @FXML
    private RadioButton rdoStoopedN;
    private ToggleGroup OM1Q5_toggleGroup;

    //Question 6/
    @FXML
    private RadioButton rdoLeaningY;
    @FXML
    private RadioButton rdoLeaningN;
    private ToggleGroup OM1Q6_toggleGroup;

    //Question 7/
    @FXML
    private RadioButton rdoBreathIssueY;
    @FXML
    private RadioButton rdoBreathIssueN;
    private ToggleGroup OM1Q7_toggleGroup;

    //Question 8/
    @FXML
    private TextArea txtBreathIssueDets;

    //Question 9/
    @FXML
    private RadioButton rdoCommsRequirement1;
    @FXML
    private RadioButton rdoCommsRequirement2;
    @FXML
    private RadioButton rdoCommsRequirement3;
    @FXML
    private RadioButton rdoCommsRequirement4;
    private ToggleGroup OM1Q9_toggleGroup;

    //Question 10/
    @FXML
    private RadioButton rdoHAidsFittingY;
    @FXML
    private RadioButton rdoHAidsFittingN;
    private ToggleGroup OM1Q10_toggleGroup;

    //Question 11/
    @FXML
    private RadioButton rdoBattChangeY;
    @FXML
    private RadioButton rdoBattChangeN;
    private ToggleGroup OM1Q11_toggleGroup;

    //Back and Next Button 
    @FXML
    private Button btnBackMobilityNeeds1;
    @FXML
    private Button btnNextMobilityNeeds1;

    // Nutrition/  
    @FXML
    private Pane paneNutritionalNeeds;

    //Question 1/
    @FXML
    private RadioButton rdoNutritionRequirement1;
    @FXML
    private RadioButton rdoNutritionRequirement2;
    @FXML
    private RadioButton rdoNutritionRequirement3;
    @FXML
    private RadioButton rdoNutritionRequirement4;
    private ToggleGroup N1Q1_toggleGroup;

    //Question 2/
    @FXML
    private RadioButton rdoMealPrepY;
    @FXML
    private RadioButton rdoMealPrepN;
    private ToggleGroup N1Q2_toggleGroup;

    //Question 3/
    @FXML
    private RadioButton rdoFoodServiceY;
    @FXML
    private RadioButton rdoFoodServiceN;
    private ToggleGroup N1Q3_toggleGroup;

    //Question 4/
    @FXML
    private RadioButton rdoSwallowIssueY;
    @FXML
    private RadioButton rdoSwallowIssueN;
    private ToggleGroup N1Q4_toggleGroup;

    //Question 5/
    @FXML
    private RadioButton rdoWeightLossY;
    @FXML
    private RadioButton rdoWeightLossN;
    private ToggleGroup N1Q5_toggleGroup;

    //Question 6/
    @FXML
    private RadioButton rdoNeedSupplementY;
    @FXML
    private RadioButton rdoNeedSupplementN;
    private ToggleGroup N1Q6_toggleGroup;

    //Question 7/
    @FXML
    private RadioButton rdoUseCutleryY;
    @FXML
    private RadioButton rdoUseCutleryN;
    private ToggleGroup N1Q7_toggleGroup;

    //Question 8/
    @FXML
    private RadioButton rdoDietaryRequirementY;
    @FXML
    private RadioButton rdoDietaryRequirementN;
    private ToggleGroup N1Q8_toggleGroup;

    //Question 9/
    @FXML
    private RadioButton rdoMealSizeS;
    @FXML
    private RadioButton rdoMealSizeM;
    @FXML
    private RadioButton rdoMealSizeL;
    private ToggleGroup N1Q9_toggleGroup;

    //Question 10/
    @FXML
    private RadioButton rdoDietRefY;
    @FXML
    private RadioButton rdoDietRefN;
    private ToggleGroup N1Q10_toggleGroup;

    @FXML
    private Button btnBackNutritionalNeeds;
    @FXML
    private Button btnNextNutritionalNeeds;

    //Skin Integrity/
    @FXML
    private Pane paneSkinIntegrityNeeds;

    //Question 1/
    @FXML
    private RadioButton rdoSkinTearY;
    @FXML
    private RadioButton rdoSkinTearN;
    private ToggleGroup S1Q1_toggleGroup;

    //Question 2/
    @FXML
    private RadioButton rdoSkinProblemY;
    @FXML
    private RadioButton rdoSkinProblemN;
    private ToggleGroup S1Q2_toggleGroup;

    //Question 3/
    @FXML
    private RadioButton rdoUseSkinProductY;
    @FXML
    private RadioButton rdoUseSkinProductN;
    private ToggleGroup S1Q3_toggleGroup;

    //Secondary Question 3//
    @FXML
    private RadioButton rdoPrescribedP;
    @FXML
    private RadioButton rdoPrescribedO;
    private ToggleGroup S1Q3S_toggleGroup;

    //Question 4/
    @FXML
    private TextArea txtSkinProductDets;

    //Question 5/
    @FXML
    private RadioButton rdoWoundDressY;
    @FXML
    private RadioButton rdoWoundDressN;
    private ToggleGroup S1Q5_toggleGroup;

    //Question 6/
    @FXML
    private RadioButton rdoAssistReqY;
    @FXML
    private RadioButton rdoAssistReqN;
    private ToggleGroup S1Q6_toggleGroup;

    //Question 7/
    @FXML
    private RadioButton rdoPressureInjuryY;
    @FXML
    private RadioButton rdoPressureInjuryN;
    private ToggleGroup S1Q7_toggleGroup;

    //Question 8/
    @FXML
    private TextArea txtInjuryDets;

    //Question 9/
    @FXML
    private RadioButton rdoAttendPodiatristY;
    @FXML
    private RadioButton rdoAttendPodiatristN;
    private ToggleGroup S1Q9_toggleGroup;

    //Question 10/
    @FXML
    private TextArea txtPodiatrist;

    //Question 11/
    @FXML
    private TextArea txtVisitFreq;

    //Back and Next Button/
    @FXML
    private Button btnBackSkinIntegrityNeeds;
    @FXML
    private Button btnNextSkinIntegrityNeeds;

    //Mental Health/
    @FXML
    private Pane paneCMHBN;

    //Question 1/
    @FXML
    private RadioButton rdoCognitiveImpairmentY;
    @FXML
    private RadioButton rdoCognitiveImpairmentN;
    private ToggleGroup MH1Q1_toggleGroup;

    //Question 2/
    @FXML
    private RadioButton rdoCognitiveAssessmentY;
    @FXML
    private RadioButton rdoCognitiveAssessmentN;
    private ToggleGroup MH1Q2_toggleGroup;

    //Question 3/
    @FXML
    private RadioButton rdoMemoryLossY;
    @FXML
    private RadioButton rdoMemoryLossN;
    private ToggleGroup MH1Q3_toggleGroup;

    //Question 4/
    @FXML
    private RadioButton rdoDepressionY;
    @FXML
    private RadioButton rdoDepressionN;
    private ToggleGroup MH1Q4_toggleGroup;

    //Question 5/
    @FXML
    private RadioButton rdoSchizophreniaY;
    @FXML
    private RadioButton rdoSchizophreniaN;
    private ToggleGroup MH1Q5_toggleGroup;

    //Question 6/
    @FXML
    private RadioButton rdoAnxietyY;
    @FXML
    private RadioButton rdoAnxietyN;
    private ToggleGroup MH1Q6_toggleGroup;

    //Question 7/
    @FXML
    private RadioButton rdoOtherMentalY;
    @FXML
    private RadioButton rdoOtherMentalN;
    private ToggleGroup MH1Q7_toggleGroup;

    //Back and Next Bu8tton/
    @FXML
    private Button btnBackCMHBN;
    @FXML
    private Button btnNextCMHBN;

    // Medication/
    @FXML
    private Pane paneMPMN;

    //Question 1/
    @FXML
    private RadioButton rdoMedicationRequirement1;
    @FXML
    private RadioButton rdoMedicationRequirement2;
    @FXML
    private RadioButton rdoMedicationRequirement3;
    @FXML
    private RadioButton rdoMedicationRequirement4;
    private ToggleGroup MD1Q1_toggleGroup;

    //Question 2/
    @FXML
    private RadioButton rdoNeedReminderY;
    @FXML
    private RadioButton rdoNeedReminderN;
    private ToggleGroup MD1Q2_toggleGroup;

    //Question 3/
    @FXML
    private RadioButton rdoNeedPromptY;
    @FXML
    private RadioButton rdoNeedPromptN;
    private ToggleGroup MD1Q3_toggleGroup;

    //Question 4/
    @FXML
    private RadioButton rdoMedAdminY;
    @FXML
    private RadioButton rdoMedAdminN;
    private ToggleGroup MD1Q4_toggleGroup;

    //Question 5/
    @FXML
    private RadioButton rdoMedPickupY;
    @FXML
    private RadioButton rdoMedPickupN;
    private ToggleGroup MD1Q5_toggleGroup;

    //Question 6/
    @FXML
    private RadioButton rdoTakingPrescY;
    @FXML
    private RadioButton rdoTakingPrescN;
    private ToggleGroup MD1Q6_toggleGroup;

    //Question 7/
    @FXML
    private RadioButton rdoMedsMismanagementY;
    @FXML
    private RadioButton rdoMedsMismanagementN;
    private ToggleGroup MD1Q7_toggleGroup;

    //Question 8/
    @FXML
    private RadioButton rdoMedPepW;
    @FXML
    private RadioButton rdoMedPepO;
    private ToggleGroup MD1Q8_toggleGroup;

    //Question 9/
    @FXML
    private TextArea txtMedInterval;

    //Question 10/
    @FXML
    private TextArea txtSpecialMed;

    //Question 11/
    @FXML
    private TextArea txtSmDets;

    //Question 12/
    @FXML
    private RadioButton rdoSupplementY;
    @FXML
    private RadioButton rdoSupplementN;
    private ToggleGroup MD1Q12_toggleGroup;

    //Question 13/
    @FXML
    private RadioButton rdoBloodMonitorAssistY;
    @FXML
    private RadioButton rdoBloodMonitorAssistN;
    private ToggleGroup MD1Q13_toggleGroup;

    //Question 14/
    @FXML
    private RadioButton rdoInjectAssistY;
    @FXML
    private RadioButton rdoInjectAssistN;
    private ToggleGroup MD1Q14_toggleGroup;

    //Back and Next Button
    @FXML
    private Button btnBackMPMN;
    @FXML
    private Button btnNextMPMN;

    // Pain Management/  
    @FXML
    private Pane panePMN;

    //Question 1/
    @FXML
    private RadioButton rdoPainExpY;
    @FXML
    private RadioButton rdoPainExpN;
    private ToggleGroup PM1Q1_toggleGroup;

    //Question 2/
    @FXML
    private RadioButton rdoPainMedY;
    @FXML
    private RadioButton rdoPainMedN;
    private ToggleGroup PM1Q2_toggleGroup;

    //Question 3/
    @FXML
    private TextArea txtPainMedFreq;

    //Question 4/
    @FXML
    private TextArea txtPainLevel;

    //Question 5/
    @FXML
    private RadioButton txtRespiteRequireY;
    @FXML
    private RadioButton rdoRespiteRequireN;
    private ToggleGroup PM1Q5_toggleGroup;

    //Secondary Question 5/
    @FXML
    private RadioButton rdoRespiteDeliveryI;
    @FXML
    private RadioButton rdoRespiteDeliveryR;
    private ToggleGroup PM1SQ5_toggleGroup;

    //Back and Next Button/
    @FXML
    private Button btnBackPMN;
    @FXML
    private Button btnNextPMN;

    //Housing/
    @FXML
    private Pane paneClientHousing;
    
    

//Question 1/
    @FXML
    private RadioButton rdoHousingOwnershipY;
    @FXML
    private RadioButton rdoHousingOwnershipR;
    @FXML
    private RadioButton rdoHousingOwnershipG;
    @FXML
    private RadioButton rdoHousingOwnershipO;
    private ToggleGroup H1Q1_toggleGroup;
    //Question 2/
    @FXML
    private RadioButton rdoLivingSetupY;
    @FXML
    private RadioButton rdoLivingSetupN1;
    @FXML
    private RadioButton rdoLivingSetupN2;
    private ToggleGroup H1Q2_toggleGroup;

    //Question 3/
    @FXML
    private RadioButton rdoPersonalAlarmY;
    @FXML
    private RadioButton rdoPersonalAlarmN;
    private ToggleGroup H1Q3_toggleGroup;

    //Question 4/
    @FXML
    private TextArea txtAlarmCompany;

    //Question 5/
    @FXML
    private TextField txtKeyLockCode;

    //Question 6/
    @FXML
    private RadioButton rdoKeyLockBoxY;
    @FXML
    private RadioButton rdoKeyLockBoxN;
    private ToggleGroup H1Q6_toggleGroup;

    //Back and Next Button/
    @FXML
    private Button btnBackClientHousing;
    @FXML
    private Button btnNextClientHousing;

    // Home Environment/
    @FXML
    private Pane paneMHEN;

    //Question 1/
    @FXML
    private RadioButton rdoHomeMaintainSupportY;
    @FXML
    private RadioButton rdoHomeMaintainSupportN;
    private ToggleGroup HE1Q1_toggleGroup;

    //Question 2/
    @FXML
    private CheckBox ckCleaning;
    @FXML
    private CheckBox ckDecluttering;
    @FXML
    private CheckBox ckDusting;
    @FXML
    private CheckBox ckVacuum;
    @FXML
    private CheckBox ckHangClothes;
    @FXML
    private CheckBox ckLightGardening;
    @FXML
    private CheckBox ckMowGarden;
    @FXML
    private CheckBox ckWindowCleaning;
    @FXML
    private CheckBox ckLaundry;
    @FXML
    private CheckBox ckMop;
    @FXML
    private CheckBox ckSweeping;
    @FXML
    private CheckBox ckGutterCleaning;
    @FXML
    private CheckBox ckBedroomCleaning;

    //Question 3/
    @FXML
    private RadioButton rdoSocialActivityY;
    @FXML
    private RadioButton rdoSocialActivityN;
    private ToggleGroup HE1Q3_toggleGroup;

    //Question 4/ 
    @FXML
    private RadioButton rdoTransportAssistY;
    @FXML
    private RadioButton rdoTransportAssistN;
    private ToggleGroup HE1Q4_toggleGroup;

    //Question 5/ 
    @FXML
    private RadioButton rdoCanDriveY;
    @FXML
    private RadioButton rdoCanDriveN;
    private ToggleGroup HE1Q5_toggleGroup;
    
    
    //Secondary Qustion 6/
    @FXML
    private CheckBox ckTaxiVoucher;
    @FXML
    private CheckBox ckShoppingAssist;
    @FXML
    private CheckBox ckDocAptAssist;

    //Secondary Qustion 6/
    @FXML
    private RadioButton rdoTaxiVoucher;
    @FXML
    private RadioButton rdoShoppingAssist;
    @FXML
    private RadioButton rdoDocAptAssist;
    @FXML
    private RadioButton rdoOtherAssist;
    private ToggleGroup HE1SQ6_toggleGroup;

    //Question 7/
    @FXML
    private TextArea txtOtherAssist;

    //Back and Next Buttin
    @FXML
    private Button btnBackMHEN;
    @FXML
    private Button btnNextMHEN;

    // End of Life/
    @FXML
    private Pane paneNDP;
    @FXML
    private TextField txtEolPractice;
    @FXML
    private TextField txtPalliativeCare;
    @FXML
    private TextField txtDnr;
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtInformFam;
    @FXML
    private TextField txtInformGP;
    @FXML
    private Button btnBackNDP;
    @FXML
    private Button btnNextNDP;

    // Contact Info/
    @FXML
    private Pane paneClientContact;
    @FXML
    private TextField txtPrimaryContact;
    @FXML
    private TextField txtPrimaryRelationship;
    @FXML
    private TextField txtPrimaryEmail;
    @FXML
    private TextField txtPrimaryPhone;
    @FXML
    private TextField txtSecondaryContact;
    @FXML
    private TextField txtSecondaryRelationship;
    @FXML
    private TextField txtSecondaryEmail;
    @FXML
    private TextField txtSecondaryPhone;
    @FXML
    private TextField txtMedPractitioner;
    @FXML
    private TextField txtPracticePhone;
    @FXML
    private TextField txtPracticeAddress;
    @FXML
    private TextField txtMedPharma;
    @FXML
    private TextField txtPharmaPhone;
    @FXML
    private TextField txtPharmaAddress;
    @FXML
    private TextField txtPharmaEmail;
    @FXML
    private TextField txtPracticeEmail;
    @FXML
    private Button btnBackClientContact;
    @FXML
    private Button btnNextClientContact;

    @FXML
    private Pane paneAssessD;
    @FXML
    private TextField txtAsssignedCSO;
    @FXML
    private DatePicker dateAssessmentDate;
    @FXML
    private TextField txtReferrralCode;
    @FXML
    private TextField txtAssessedLevel;
    @FXML
    private Button btnBackAssessD;
    @FXML
    private Button btnNextAssessD;
    @FXML
    private Pane paneServiceP;
    @FXML
    private TextField txtServiceType;
    @FXML
    private TextField txtServiceAmount;
    @FXML
    private TextArea txtServiceDesc;
    @FXML
    private Button btnBackAssessD1;
    @FXML
    private Button btnNextAssessD1;
    @FXML
    private Pane paneBudgetP;
    @FXML
    private TextField txtFreq;
    @FXML
    private TextField txtSubTotal;
    @FXML
    private TextField txtTotal;
    @FXML
    private Button btnBackAssessD11;
    @FXML
    private Button btnNextAssessD11;

    @FXML
    private Pane paneCareP;
    @FXML
    private TextField txtToiletingCare;
    @FXML
    private TextField txtHygieneCare;
    @FXML
    private TextField txtMobilityCare;
    @FXML
    private TextField txtHealthAssist;
    @FXML
    private TextField txtMedicationAssist;
    @FXML
    private TextField txtAlliedHealth;
    @FXML
    private TextField txtHousingCare;
    @FXML
    private TextField txtCleaningCare;
    @FXML
    private TextField txtTransportAssist;
    @FXML
    private TextField txtCommunityAccess;
    @FXML
    private TextField txtSocialSupport;
    @FXML
    private TextField txtAfterLifeAssist;
    @FXML
    private TextField txtRequireObservation;
    @FXML
    private TextField txtRequireReassessment;
    @FXML
    private Button btnBackCareP;
    @FXML
    private Button btnNextCareP;
    @FXML
    private Pane paneAuditT;
    @FXML
    private TextArea txtLogDetails;
    @FXML
    private DatePicker dateLogDateTime;
    @FXML
    private Button btnBackAuditT;
    @FXML
    private Button btnNextAuditT;
    @FXML
    private Pane paneUserA;
    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private TextField txtUserMobile;
    @FXML
    private TextField txtUserType;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtUserPass;
    @FXML
    private TextArea txtUserAddress;
    @FXML
    private Button btnBackUserA;
    @FXML
    private Button btnNextUserA;
    @FXML
    private Pane paneEmployeeL;
    @FXML
    private TextField txtEmployeeID;
    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtActiveFlag;
    @FXML
    private DatePicker dateStartDate;
    @FXML
    private DatePicker dateEndDate;
    @FXML
    private Button btnBackEmployeeL;
    @FXML
    private Button btnNextEmployeeL;
    @FXML
    private Pane paneClientC;
    @FXML
    private TextField txtCaseType;
    @FXML
    private TextField txtCasePriority;
    @FXML
    private TextField txtClientType;
    @FXML
    private TextArea txtCaseDetails;
    @FXML
    private TextField txtCaseAssignment;
    @FXML
    private Button btnBackEmployeeL1;
    @FXML
    private Button btnNextEmployeeL1;
    @FXML
    private Pane paneFundingL;
    @FXML
    private TextField txtDailyFund;
    @FXML
    private TextField txtMonthlyFund;
    @FXML
    private TextField txtMonthlyITF;
    @FXML
    private TextField txtDementiaFlag;
    @FXML
    private Button btnBackFundingL;
    @FXML
    private Button btnNextFundingL;
    @FXML
    private Pane paneClientD;
    @FXML
    private TextField txtClientMedicare;
    @FXML
    private TextField txtClientAddress;
    @FXML
    private TextField txtClientMobile;
    @FXML
    private TextField txtEmergencyContactPerson;
    @FXML
    private TextField txtEmergencyContactNumber;
    @FXML
    private TextField txtRelationship;
    @FXML
    private DatePicker dateClientBday;
    @FXML
    private Button btnBackClientD;
    @FXML
    private Button btnNextClientD;
    @FXML 
    private Label csonum;

    @FXML
    private Pane clientDetsPane;

    private String clientID;
    private String assessmentID;
    private String caseN;
    
   private String cno;
    private String fn;
    private String ln;
    private String mc;
    private String mob;
    private String ca;
    private String csid;
    private String bd;
    private String csonumb;
    private String csonam;
   
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
   
        getTheMaxAssessID();
        initComboDate();
        PHygienge_BtnGrp();
        OHygienge_BtnGrp();
        Toileting_BtnGrp();
        Mobility_BtnGrp();
        OMobility_BtnGrp();
        Nutrition_BtnGrp();
        Skin_BtnGrp();
        MHealth_BtnGrp();
        Medication_BtnGrp();
        PMgt_BtnGrp();
        House_BtnGrp();
        HomeE_BtnGrp();

        clientID = cID.getText();
        assessmentID = assessNo.getText();

      

    }
    
    
       public void setClientDetails(String cno, String fn, String ln, String mc, 
                                 String mob, String ca, String csid, String bd, String csonumb, String csonam) {
        this.cno = cno;
        this.fn = fn;
        this.ln = ln;
        this.mc = mc;
        this.mob = mob;
        this.ca = ca;
        this.csid = csid;
        this.bd = bd;
        this.csonumb = csonumb;
        this.csonam = csonam;
        

           cID.setText(cno);
           cFname.setText(fn);
           cLname.setText(ln);
           cMedicare.setText(mc);
           caseNo.setText(csid);
           csoName.setText(csonam);
           csonum.setText(csonumb);
             caseN = caseNo.getText();
        //        

//         // Assuming you are using a ComboBox for selecting venue options
//String selectedOption = aVenue.getValue().toString();
//
//// Check if the selected option is "Others - please specify"
//boolean isOthersSelected = selectedOption != null && selectedOption.equals("Others");
//
//if (isOthersSelected) {
//    // If "Others - please specify" is selected, enable aVenueOthers and clear any previous text
//    aVenueOthers.setDisable(false);
//    aVenueOthers.clear();
//} else {
//    // Disable the field if "Others" is not selected
//    aVenueOthers.setDisable(true);
//    aVenueOthers.clear(); // Optional: Clear if switching from "Others" to another option
//}   
//          
           
           
       }

    private void initComboDate() {
        
        aDate.setValue(LocalDate.now());
        dateDentistVisit.setValue(LocalDate.now());
        dateLastVisit.setValue(LocalDate.now());

        aVenue.setItems(FXCollections.observableArrayList("Physically At Home", "Via Voice Call", "Via Video Call", "Others"));
        aVenue.getSelectionModel().selectFirst();
//        


//
   
    }

    private void getTheMaxAssessID() {

        String query = "SELECT assessmentID FROM clientAssessment ORDER BY assessmentID DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            //hidden placeholder for next userid
            if (rs.next()) {
                int lastUser = rs.getInt("assessmentID");
                assessNo.setText(String.valueOf(lastUser + 1));
            } else {
                assessNo.setText("2024000001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
private void PHygienge_BtnGrp() {

        //Question 1
        P1Q1_toggleGroup = new ToggleGroup();
        rdoBpgRequirement1.setToggleGroup(P1Q1_toggleGroup);
        rdoBpgRequirement2.setToggleGroup(P1Q1_toggleGroup);
        rdoBpgRequirement3.setToggleGroup(P1Q1_toggleGroup);
        rdoBpgRequirement4.setToggleGroup(P1Q1_toggleGroup);
        rdoBpgRequirement1.setSelected(true);

        //Question 2
        P1Q2_toggleGroup = new ToggleGroup();
        rdoBathingY.setToggleGroup(P1Q2_toggleGroup);
        rdoBathingN.setToggleGroup(P1Q2_toggleGroup);
        rdoBathingY.setSelected(true);

        //Question 3
        P1Q3_toggleGroup = new ToggleGroup();
        rdoBodyScrabY.setToggleGroup(P1Q3_toggleGroup);
        rdoBodyScrabN.setToggleGroup(P1Q3_toggleGroup);
        rdoBodyScrabY.setSelected(true);

        //Question 4
        P1Q4_toggleGroup = new ToggleGroup();
        rdoGroomingY.setToggleGroup(P1Q4_toggleGroup);
        rdoGroomingN.setToggleGroup(P1Q4_toggleGroup);
        rdoGroomingY.setSelected(true);

        //Question 5
        P1Q5_toggleGroup = new ToggleGroup();
        rdoShowerIntervalsD.setToggleGroup(P1Q5_toggleGroup);
        rdoShowerIntervals2.setToggleGroup(P1Q5_toggleGroup);
        rdoShowerIntervalsD.setSelected(true);

//        //Question 6
//        P1Q6_toggleGroup = new ToggleGroup();
//        rdoShowerCarerReqY.setToggleGroup(P1Q6_toggleGroup);
//        rdoShowerCarerReqN.setToggleGroup(P1Q6_toggleGroup);
//        rdoShowerCarerReqY.setSelected(true);
        //Question 7
        P1Q7_toggleGroup = new ToggleGroup();
        rdoShowerCarerGenderN.setToggleGroup(P1Q7_toggleGroup);
        rdoShowerCarerGenderA.setToggleGroup(P1Q7_toggleGroup);
        rdoShowerCarerGenderM.setToggleGroup(P1Q7_toggleGroup);
        rdoShowerCarerGenderF.setToggleGroup(P1Q7_toggleGroup);
        rdoShowerCarerGenderN.setSelected(true);

        //Question 8
        P1Q8_toggleGroup = new ToggleGroup();
        rdoShowerPrefDayP.setToggleGroup(P1Q8_toggleGroup);
        rdoShowerPrefDayA.setToggleGroup(P1Q8_toggleGroup);
        rdoShowerPrefDayA.setSelected(true);

        //Question 9
        P1Q9_toggleGroup = new ToggleGroup();
        rdoDressingRequirement1.setToggleGroup(P1Q9_toggleGroup);
        rdoDressingRequirement2.setToggleGroup(P1Q9_toggleGroup);
        rdoDressingRequirement3.setToggleGroup(P1Q9_toggleGroup);
        rdoDressingRequirement4.setToggleGroup(P1Q9_toggleGroup);
        rdoDressingRequirement1.setSelected(true);

        //Question 10
        P1Q10_toggleGroup = new ToggleGroup();
        rdoDressingY.setToggleGroup(P1Q10_toggleGroup);
        rdoDressingN.setToggleGroup(P1Q10_toggleGroup);
        rdoDressingY.setSelected(true);

        //Question 11
        P1Q11_toggleGroup = new ToggleGroup();
        rdoUndressingY.setToggleGroup(P1Q11_toggleGroup);
        rdoUndressingN.setToggleGroup(P1Q11_toggleGroup);
        rdoUndressingY.setSelected(true);

        //Question 12
        P1Q12_toggleGroup = new ToggleGroup();
        rdoSocksY.setToggleGroup(P1Q12_toggleGroup);
        rdoSocksN.setToggleGroup(P1Q12_toggleGroup);
        rdoSocksY.setSelected(true);

        //Question 13
        P1Q13_toggleGroup = new ToggleGroup();
        rdoShoesY.setToggleGroup(P1Q13_toggleGroup);
        rdoShoesN.setToggleGroup(P1Q13_toggleGroup);
        rdoShoesY.setSelected(true);

//        //Question 14
//        P1Q14_toggleGroup = new ToggleGroup();
//        rdoDressingCarerReqY.setToggleGroup(P1Q14_toggleGroup);
//        rdoDressingCarerReqN.setToggleGroup(P1Q14_toggleGroup);
//        rdoDressingCarerReqY.setSelected(true);
        //Question 15
        P1Q15_toggleGroup = new ToggleGroup();
        rdoDressingCarerGenderN.setToggleGroup(P1Q15_toggleGroup);
        rdoDressingCarerGenderA.setToggleGroup(P1Q15_toggleGroup);
        rdoDressingCarerGenderM.setToggleGroup(P1Q15_toggleGroup);
        rdoDressingCarerGenderF.setToggleGroup(P1Q15_toggleGroup);
        rdoDressingCarerGenderN.setSelected(true);

        //Question 16
        P1Q16_toggleGroup = new ToggleGroup();
        rdoDressingPrefDayA.setToggleGroup(P1Q16_toggleGroup);
        rdoDressingPrefDayP.setToggleGroup(P1Q16_toggleGroup);
        rdoDressingPrefDayA.setSelected(true);
    }

    private void OHygienge_BtnGrp() {
        //Question 1
        O1Q1_toggleGroup = new ToggleGroup();
        rdoOralHygieneRequirement1.setToggleGroup(O1Q1_toggleGroup);
        rdoOralHygieneRequirement2.setToggleGroup(O1Q1_toggleGroup);
        rdoOralHygieneRequirement3.setToggleGroup(O1Q1_toggleGroup);
        rdoOralHygieneRequirement4.setToggleGroup(O1Q1_toggleGroup);
        rdoOralHygieneRequirement1.setSelected(true);

        //Question 2
        O1Q2_toggleGroup = new ToggleGroup();
        rdoHoldToothbrushY.setToggleGroup(O1Q2_toggleGroup);
        rdoHoldToothbrushN.setToggleGroup(O1Q2_toggleGroup);
        rdoHoldToothbrushY.setSelected(true);

        //Question 3
        O1Q3_toggleGroup = new ToggleGroup();
        rdoToothPasteSqY.setToggleGroup(O1Q3_toggleGroup);
        rdoToothPasteSqN.setToggleGroup(O1Q3_toggleGroup);
        rdoToothPasteSqY.setSelected(true);

        //Question 4
        O1Q4_toggleGroup = new ToggleGroup();
        rdoMouthRinseY.setToggleGroup(O1Q4_toggleGroup);
        rdoMouthRinseN.setToggleGroup(O1Q4_toggleGroup);
        rdoMouthRinseY.setSelected(true);

        //Question 5
        O1Q5_toggleGroup = new ToggleGroup();
        rdoMouthIssuesY.setToggleGroup(O1Q5_toggleGroup);
        rdoMouthIssuesG.setToggleGroup(O1Q5_toggleGroup);
        rdoMouthIssuesN.setToggleGroup(O1Q5_toggleGroup);
        rdoMouthIssuesM.setToggleGroup(O1Q5_toggleGroup);
        rdoMouthIssuesY.setSelected(true);

        //Question 6
        O1Q6_toggleGroup = new ToggleGroup();
        rdoTeethFormsD.setToggleGroup(O1Q6_toggleGroup);
        rdoTeethFormsCrowns.setToggleGroup(O1Q6_toggleGroup);
        teethFormsCaps.setToggleGroup(O1Q6_toggleGroup);
        rdoTeethFormsP.setToggleGroup(O1Q6_toggleGroup);
        rdoTeethFormsD.setSelected(true);
    }

    private void Toileting_BtnGrp() {
        //Question 1
        T1Q1_toggleGroup = new ToggleGroup();
        rdoToiletingRequirement1.setToggleGroup(T1Q1_toggleGroup);
        rdoToiletingRequirement2.setToggleGroup(T1Q1_toggleGroup);
        rdoToiletingRequirement3.setToggleGroup(T1Q1_toggleGroup);
        rdoToiletingRequirement4.setToggleGroup(T1Q1_toggleGroup);
        rdoToiletingRequirement1.setSelected(true);

        //Question 2
        T1Q2_toggleGroup = new ToggleGroup();
        rdoWalkToiletY.setToggleGroup(T1Q2_toggleGroup);
        rdoWalkToiletN.setToggleGroup(T1Q2_toggleGroup);
        rdoWalkToiletY.setSelected(true);

        //Question 3
        T1Q3_toggleGroup = new ToggleGroup();
        rdoPantsOffY.setToggleGroup(T1Q3_toggleGroup);
        rdoPantsOffN.setToggleGroup(T1Q3_toggleGroup);
        rdoPantsOffY.setSelected(true);

        //Question 4
        T1Q4_toggleGroup = new ToggleGroup();
        rdoToiletSittingY.setToggleGroup(T1Q4_toggleGroup);
        rdoToiletSittingN.setToggleGroup(T1Q4_toggleGroup);
        rdoToiletSittingY.setSelected(true);

        //Question 5
        T1Q5_toggleGroup = new ToggleGroup();
        rdoPWashingY.setToggleGroup(T1Q5_toggleGroup);
        rdoPWashingN.setToggleGroup(T1Q5_toggleGroup);
        rdoPWashingY.setSelected(true);

        //Question 6
        T1Q6_toggleGroup = new ToggleGroup();
        rdoPDryingY.setToggleGroup(T1Q6_toggleGroup);
        rdoPDryingN.setToggleGroup(T1Q6_toggleGroup);
        rdoPDryingY.setSelected(true);

        //Question 7
        T1Q7_toggleGroup = new ToggleGroup();
        rdoContinenceRequirement1.setToggleGroup(T1Q7_toggleGroup);
        rdoContinenceRequirement2.setToggleGroup(T1Q7_toggleGroup);
        rdoContinenceRequirement3.setToggleGroup(T1Q7_toggleGroup);
        rdoContinenceRequirement4.setToggleGroup(T1Q7_toggleGroup);
        rdoContinenceRequirement1.setSelected(true);

        //Question 8
        T1Q8_toggleGroup = new ToggleGroup();
        rdoSetupPadsY.setToggleGroup(T1Q8_toggleGroup);
        rdoSetupPadsN.setToggleGroup(T1Q8_toggleGroup);
        rdoSetupPadsY.setSelected(true);

        //Question 9
        T1Q9_toggleGroup = new ToggleGroup();
        rdoPWipeDryY.setToggleGroup(T1Q9_toggleGroup);
        rdoPWipeDryN.setToggleGroup(T1Q9_toggleGroup);
        rdoPWipeDryY.setSelected(true);

    }

    private void Mobility_BtnGrp() {
        //Question 1
        M1Q1_toggleGroup = new ToggleGroup();
        rdoMobilityRequirement1.setToggleGroup(M1Q1_toggleGroup);
        rdoMobilityRequirement2.setToggleGroup(M1Q1_toggleGroup);
        rdoMobilityRequirement3.setToggleGroup(M1Q1_toggleGroup);
        rdoMobilityRequirement1.setSelected(true);

        //Question 3
        M1Q2_toggleGroup = new ToggleGroup();
        rdoMobilityIssueY.setToggleGroup(M1Q2_toggleGroup);
        rdoMobilityIssueN.setToggleGroup(M1Q2_toggleGroup);
        rdoMobilityIssueY.setSelected(true);

        // Initially, Assistance Required text field is enabled because "Yes" (rdoMobilityIssueY) is selected
        txtIssueDets.setDisable(false);

        M1Q2_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoMobilityIssueY) {
                // Enable Assistance Required text field when "Yes" is selected
                txtIssueDets.setDisable(false);
            } else if (newValue == rdoMobilityIssueN) {
                // Disable Assistance Required text field when "No" is selected
                txtIssueDets.setDisable(true);
            }
        });

        //Question 4
        M1Q4_toggleGroup = new ToggleGroup();
        rdoTStandY.setToggleGroup(M1Q4_toggleGroup);
        rdoTStandN.setToggleGroup(M1Q4_toggleGroup);
        rdoTStandY.setSelected(true);

        //Question 5
        M1Q5_toggleGroup = new ToggleGroup();
        rdoTSitY.setToggleGroup(M1Q5_toggleGroup);
        rdoTSitN.setToggleGroup(M1Q5_toggleGroup);
        rdoTSitY.setSelected(true);

        //Question 6
        M1Q6_toggleGroup = new ToggleGroup();
        rdoTLyingY.setToggleGroup(M1Q6_toggleGroup);
        rdoTLyingN.setToggleGroup(M1Q6_toggleGroup);
        rdoTLyingY.setSelected(true);

        //Question 7
        M1Q7_toggleGroup = new ToggleGroup();
        rdoTSittingY.setToggleGroup(M1Q7_toggleGroup);
        rdoTSittingN.setToggleGroup(M1Q7_toggleGroup);
        rdoTSittingY.setSelected(true);

        //Question 8
        M1Q8_toggleGroup = new ToggleGroup();
        rdoWalkingY.setToggleGroup(M1Q8_toggleGroup);
        rdoWalkingN.setToggleGroup(M1Q8_toggleGroup);
        rdoWalkingY.setSelected(true);

        //Question 9
        M1Q9_toggleGroup = new ToggleGroup();
        rdoCarRideY.setToggleGroup(M1Q9_toggleGroup);
        rdoCarRideN.setToggleGroup(M1Q9_toggleGroup);
        rdoCarRideY.setSelected(true);

        //Question 10
        M1Q10_toggleGroup = new ToggleGroup();
        rdoUseStairsY.setToggleGroup(M1Q10_toggleGroup);
        rdoUseStairsN.setToggleGroup(M1Q10_toggleGroup);
        rdoUseStairsY.setSelected(true);

        //Question 11
        M1Q11_toggleGroup = new ToggleGroup();
        rdoMobilityAids4.setToggleGroup(M1Q11_toggleGroup);
        rdoMobilityAidsWalking.setToggleGroup(M1Q11_toggleGroup);
        rdoMobilityAidsWheel.setToggleGroup(M1Q11_toggleGroup);
        rdoMobilityAidsM.setToggleGroup(M1Q11_toggleGroup);
        rdoMobilityAids4.setSelected(true);

        //Question 12
        M1Q12_toggleGroup = new ToggleGroup();
        rdoLifterRequirementY.setToggleGroup(M1Q12_toggleGroup);
        rdoLifterRequirementN.setToggleGroup(M1Q12_toggleGroup);
        rdoLifterRequirementY.setSelected(true);

        //Question 13
        M1Q13_toggleGroup = new ToggleGroup();
        rdoFallHistoryY.setToggleGroup(M1Q13_toggleGroup);
        rdoFallHistoryN.setToggleGroup(M1Q13_toggleGroup);
        rdoFallHistoryY.setSelected(true);

        // Initially, txtFallDets is enabled because "Yes" (rdoFallHistoryY) is selected
        txtFallDets.setDisable(false);

        M1Q13_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoFallHistoryY) {
                // Enable txtFallDets when "Yes" is selected
                txtFallDets.setDisable(false);
            } else if (newValue == rdoFallHistoryN) {
                // Disable txtFallDets when "No" is selected
                txtFallDets.setDisable(true);
            }
        });

    }

    private void OMobility_BtnGrp() {
        //Question 1
        OM1Q1_toggleGroup = new ToggleGroup();
        rdoExerciseY.setToggleGroup(OM1Q1_toggleGroup);
        rdoExerciseN.setToggleGroup(OM1Q1_toggleGroup);
        rdoExerciseY.setSelected(true);

        // Initially enable txtExerciseDets because "Yes" (rdoExerciseY) is selected
        txtExerciseDets.setDisable(false);

        // Add listener to the toggle group for Exercise question
        OM1Q1_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoExerciseY) {
                // Enable txtExerciseDets when "Yes" is selected
                txtExerciseDets.setDisable(false);
            } else if (newValue == rdoExerciseN) {
                // Disable txtExerciseDets when "No" is selected
                txtExerciseDets.setDisable(true);
            }
        });

        //Question 3
        OM1Q3_toggleGroup = new ToggleGroup();
        rdoAlliedHealthRefY.setToggleGroup(OM1Q3_toggleGroup);
        rdoAlliedHealthRefN.setToggleGroup(OM1Q3_toggleGroup);
        rdoAlliedHealthRefY.setSelected(true);

        //Question 4
        OM1Q4_toggleGroup = new ToggleGroup();
        rdoUnsteadyY.setToggleGroup(OM1Q4_toggleGroup);
        rdoUnsteadyN.setToggleGroup(OM1Q4_toggleGroup);
        rdoUnsteadyY.setSelected(true);

        //Question 5
        OM1Q5_toggleGroup = new ToggleGroup();
        rdoStoopedY.setToggleGroup(OM1Q5_toggleGroup);
        rdoStoopedN.setToggleGroup(OM1Q5_toggleGroup);
        rdoStoopedY.setSelected(true);

        //Question 6
        OM1Q6_toggleGroup = new ToggleGroup();
        rdoLeaningY.setToggleGroup(OM1Q6_toggleGroup);
        rdoLeaningN.setToggleGroup(OM1Q6_toggleGroup);
        rdoLeaningY.setSelected(true);

        //Question 7
        OM1Q7_toggleGroup = new ToggleGroup();
        rdoBreathIssueY.setToggleGroup(OM1Q7_toggleGroup);
        rdoBreathIssueN.setToggleGroup(OM1Q7_toggleGroup);
        rdoBreathIssueY.setSelected(true);

        // Initially enable txtBreathIssueDets because "Yes" (rdoBreathIssueY) is selected
        txtBreathIssueDets.setDisable(false);

        // Add listener to the toggle group for breathing issue question
        OM1Q7_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoBreathIssueY) {
                // Enable txtBreathIssueDets when "Yes" is selected
                txtBreathIssueDets.setDisable(false);
            } else if (newValue == rdoBreathIssueN) {
                // Disable txtBreathIssueDets when "No" is selected
                txtBreathIssueDets.setDisable(true);
            }
        });

        //Question 9
        OM1Q9_toggleGroup = new ToggleGroup();
        rdoCommsRequirement1.setToggleGroup(OM1Q9_toggleGroup);
        rdoCommsRequirement2.setToggleGroup(OM1Q9_toggleGroup);
        rdoCommsRequirement3.setToggleGroup(OM1Q9_toggleGroup);
        rdoCommsRequirement4.setToggleGroup(OM1Q9_toggleGroup);
        rdoCommsRequirement1.setSelected(true);

        //Question 10
        OM1Q10_toggleGroup = new ToggleGroup();
        rdoHAidsFittingY.setToggleGroup(OM1Q10_toggleGroup);
        rdoHAidsFittingN.setToggleGroup(OM1Q10_toggleGroup);
        rdoHAidsFittingY.setSelected(true);

        //Question 11
        OM1Q11_toggleGroup = new ToggleGroup();
        rdoBattChangeY.setToggleGroup(OM1Q11_toggleGroup);
        rdoBattChangeN.setToggleGroup(OM1Q11_toggleGroup);
        rdoBattChangeY.setSelected(true);

    }

    private void Nutrition_BtnGrp() {
        //Question 1
        N1Q1_toggleGroup = new ToggleGroup();
        rdoNutritionRequirement1.setToggleGroup(N1Q1_toggleGroup);
        rdoNutritionRequirement2.setToggleGroup(N1Q1_toggleGroup);
        rdoNutritionRequirement3.setToggleGroup(N1Q1_toggleGroup);
        rdoNutritionRequirement4.setToggleGroup(N1Q1_toggleGroup);
        rdoNutritionRequirement1.setSelected(true);

        //Question 2
        N1Q2_toggleGroup = new ToggleGroup();
        rdoMealPrepY.setToggleGroup(N1Q2_toggleGroup);
        rdoMealPrepN.setToggleGroup(N1Q2_toggleGroup);
        rdoMealPrepY.setSelected(true);

        //Question 3
        N1Q3_toggleGroup = new ToggleGroup();
        rdoFoodServiceY.setToggleGroup(N1Q3_toggleGroup);
        rdoFoodServiceN.setToggleGroup(N1Q3_toggleGroup);
        rdoFoodServiceY.setSelected(true);

        //Question 4
        N1Q4_toggleGroup = new ToggleGroup();
        rdoSwallowIssueY.setToggleGroup(N1Q4_toggleGroup);
        rdoSwallowIssueN.setToggleGroup(N1Q4_toggleGroup);
        rdoSwallowIssueY.setSelected(true);

        //Question 5/
        N1Q5_toggleGroup = new ToggleGroup();
        rdoWeightLossY.setToggleGroup(N1Q5_toggleGroup);
        rdoWeightLossN.setToggleGroup(N1Q5_toggleGroup);
        rdoWeightLossY.setSelected(true);

        //Question 6/
        N1Q6_toggleGroup = new ToggleGroup();
        rdoNeedSupplementY.setToggleGroup(N1Q6_toggleGroup);
        rdoNeedSupplementN.setToggleGroup(N1Q6_toggleGroup);
        rdoNeedSupplementY.setSelected(true);

        //Question 7/
        N1Q7_toggleGroup = new ToggleGroup();
        rdoUseCutleryY.setToggleGroup(N1Q7_toggleGroup);
        rdoUseCutleryN.setToggleGroup(N1Q7_toggleGroup);
        rdoUseCutleryY.setSelected(true);

        //Question 8/
        N1Q8_toggleGroup = new ToggleGroup();
        rdoDietaryRequirementY.setToggleGroup(N1Q8_toggleGroup);
        rdoDietaryRequirementN.setToggleGroup(N1Q8_toggleGroup);
        rdoDietaryRequirementY.setSelected(true);

        //Question 9/
        N1Q9_toggleGroup = new ToggleGroup();
        rdoMealSizeS.setToggleGroup(N1Q9_toggleGroup);
        rdoMealSizeM.setToggleGroup(N1Q9_toggleGroup);
        rdoMealSizeL.setToggleGroup(N1Q9_toggleGroup);
        rdoMealSizeS.setSelected(true);

        //Question 10/
        N1Q10_toggleGroup = new ToggleGroup();
        rdoDietRefY.setToggleGroup(N1Q10_toggleGroup);
        rdoDietRefN.setToggleGroup(N1Q10_toggleGroup);
        rdoDietRefY.setSelected(true);

    }

    private void Skin_BtnGrp() {
        //Question 1/
        S1Q1_toggleGroup = new ToggleGroup();
        rdoSkinTearY.setToggleGroup(S1Q1_toggleGroup);
        rdoSkinTearN.setToggleGroup(S1Q1_toggleGroup);
        rdoSkinTearY.setSelected(true);

        //Question 2/
        S1Q2_toggleGroup = new ToggleGroup();
        rdoSkinProblemY.setToggleGroup(S1Q2_toggleGroup);
        rdoSkinProblemN.setToggleGroup(S1Q2_toggleGroup);
        rdoSkinProblemY.setSelected(true);

        //Question 3/
        S1Q3_toggleGroup = new ToggleGroup();
        rdoUseSkinProductY.setToggleGroup(S1Q3_toggleGroup);
        rdoUseSkinProductN.setToggleGroup(S1Q3_toggleGroup);
        rdoUseSkinProductY.setSelected(true);

        //Secondary Question 3//
        S1Q3S_toggleGroup = new ToggleGroup();
        rdoPrescribedP.setToggleGroup(S1Q3S_toggleGroup);
        rdoPrescribedO.setToggleGroup(S1Q3S_toggleGroup);

        // Initially enable txtSkinProductDets because "Yes" (rdoUseSkinProductY) is selected
        txtSkinProductDets.setDisable(false);

        // Add listener to the S1Q3_toggleGroup for Use of Skin Products
        S1Q3_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoUseSkinProductY) {
                // Enable txtSkinProductDets if "Yes" is selected
                txtSkinProductDets.setDisable(false);
                // Deselect any option in S1Q3S_toggleGroup
                S1Q3S_toggleGroup.selectToggle(null);
            } else if (newValue == rdoUseSkinProductN) {
                // Disable txtSkinProductDets if "No" is selected
                txtSkinProductDets.setDisable(true);
                // Deselect any option in S1Q3S_toggleGroup
                S1Q3S_toggleGroup.selectToggle(null);
            }
        });

        // Add listener to the S1Q3S_toggleGroup for Prescribed Products
        S1Q3S_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoPrescribedP || newValue == rdoPrescribedO) {
                // Disable txtSkinProductDets if either prescribed option is selected
                txtSkinProductDets.setDisable(true);
                // Deselect any option in S1Q3_toggleGroup
                S1Q3_toggleGroup.selectToggle(null);
            }
        });

        //Question 5/
        S1Q5_toggleGroup = new ToggleGroup();
        rdoWoundDressY.setToggleGroup(S1Q5_toggleGroup);
        rdoWoundDressN.setToggleGroup(S1Q5_toggleGroup);
        rdoWoundDressY.setSelected(true);

        //Question 6/
        S1Q6_toggleGroup = new ToggleGroup();
        rdoAssistReqY.setToggleGroup(S1Q6_toggleGroup);
        rdoAssistReqN.setToggleGroup(S1Q6_toggleGroup);
        rdoAssistReqY.setSelected(true);

        //Question 7/
        S1Q7_toggleGroup = new ToggleGroup();
        rdoPressureInjuryY.setToggleGroup(S1Q7_toggleGroup);
        rdoPressureInjuryN.setToggleGroup(S1Q7_toggleGroup);
        rdoPressureInjuryY.setSelected(true);

        // Initially enable txtInjuryDets because "Yes" (rdoPressureInjuryY) is selected
        txtInjuryDets.setDisable(false);

        // Add listener to the S1Q7_toggleGroup
        S1Q7_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoPressureInjuryY) {
                // Enable txtInjuryDets if "Yes" is selected
                txtInjuryDets.setDisable(false);
            } else if (newValue == rdoPressureInjuryN) {
                // Disable txtInjuryDets if "No" is selected
                txtInjuryDets.setDisable(true);
            }
        });

        //Question 9/
        S1Q9_toggleGroup = new ToggleGroup();
        rdoAttendPodiatristY.setToggleGroup(S1Q9_toggleGroup);
        rdoAttendPodiatristN.setToggleGroup(S1Q9_toggleGroup);
        rdoAttendPodiatristY.setSelected(true);

        // Initially enable txtPodiatrist and txtVisitFreq because "Yes" is selected
        txtPodiatrist.setDisable(false);
        txtVisitFreq.setDisable(false);

        // Add listener to the S1Q9_toggleGroup
        S1Q9_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoAttendPodiatristY) {
                // Enable txtPodiatrist and txtVisitFreq if "Yes" is selected
                txtPodiatrist.setDisable(false);
                txtVisitFreq.setDisable(false);
            } else if (newValue == rdoAttendPodiatristN) {
                // Disable txtPodiatrist and txtVisitFreq if "No" is selected
                txtPodiatrist.setDisable(true);
                txtVisitFreq.setDisable(true);
            }
        });

    }

    private void MHealth_BtnGrp() {
        //Question 1/
        MH1Q1_toggleGroup = new ToggleGroup();
        rdoCognitiveImpairmentY.setToggleGroup(MH1Q1_toggleGroup);
        rdoCognitiveImpairmentN.setToggleGroup(MH1Q1_toggleGroup);
        rdoCognitiveImpairmentY.setSelected(true);

        //Question 2/
        MH1Q2_toggleGroup = new ToggleGroup();
        rdoCognitiveAssessmentY.setToggleGroup(MH1Q2_toggleGroup);
        rdoCognitiveAssessmentN.setToggleGroup(MH1Q2_toggleGroup);
        rdoCognitiveAssessmentY.setSelected(true);

        //Question 3/
        MH1Q3_toggleGroup = new ToggleGroup();
        rdoMemoryLossY.setToggleGroup(MH1Q3_toggleGroup);
        rdoMemoryLossN.setToggleGroup(MH1Q3_toggleGroup);
        rdoMemoryLossY.setSelected(true);

        //Question 4/
        MH1Q4_toggleGroup = new ToggleGroup();
        rdoDepressionY.setToggleGroup(MH1Q4_toggleGroup);
        rdoDepressionN.setToggleGroup(MH1Q4_toggleGroup);
        rdoDepressionY.setSelected(true);

        //Question 5/
        MH1Q5_toggleGroup = new ToggleGroup();
        rdoSchizophreniaY.setToggleGroup(MH1Q5_toggleGroup);
        rdoSchizophreniaN.setToggleGroup(MH1Q5_toggleGroup);
        rdoSchizophreniaY.setSelected(true);

        //Question 6/
        MH1Q6_toggleGroup = new ToggleGroup();
        rdoAnxietyY.setToggleGroup(MH1Q6_toggleGroup);
        rdoAnxietyN.setToggleGroup(MH1Q6_toggleGroup);
        rdoAnxietyY.setSelected(true);

        //Question 7/
        MH1Q7_toggleGroup = new ToggleGroup();
        rdoOtherMentalY.setToggleGroup(MH1Q7_toggleGroup);
        rdoOtherMentalN.setToggleGroup(MH1Q7_toggleGroup);
        rdoOtherMentalY.setSelected(true);

    }

    private void Medication_BtnGrp() {
        //Question 1/
        MD1Q1_toggleGroup = new ToggleGroup();
        rdoMedicationRequirement1.setToggleGroup(MD1Q1_toggleGroup);
        rdoMedicationRequirement2.setToggleGroup(MD1Q1_toggleGroup);
        rdoMedicationRequirement3.setToggleGroup(MD1Q1_toggleGroup);
        rdoMedicationRequirement4.setToggleGroup(MD1Q1_toggleGroup);
        rdoMedicationRequirement1.setSelected(true);

        //Question 2/
        MD1Q2_toggleGroup = new ToggleGroup();
        rdoNeedReminderY.setToggleGroup(MD1Q2_toggleGroup);
        rdoNeedReminderN.setToggleGroup(MD1Q2_toggleGroup);
        rdoNeedReminderY.setSelected(true);

        //Question 3/
        MD1Q3_toggleGroup = new ToggleGroup();
        rdoNeedPromptY.setToggleGroup(MD1Q3_toggleGroup);
        rdoNeedPromptN.setToggleGroup(MD1Q3_toggleGroup);
        rdoNeedPromptY.setSelected(true);

        //Question 4/
        MD1Q4_toggleGroup = new ToggleGroup();
        rdoMedAdminY.setToggleGroup(MD1Q4_toggleGroup);
        rdoMedAdminN.setToggleGroup(MD1Q4_toggleGroup);
        rdoMedAdminY.setSelected(true);

        //Question 5/
        MD1Q5_toggleGroup = new ToggleGroup();
        rdoMedPickupY.setToggleGroup(MD1Q5_toggleGroup);
        rdoMedPickupN.setToggleGroup(MD1Q5_toggleGroup);
        rdoMedPickupY.setSelected(true);

        //Question 6/
        MD1Q6_toggleGroup = new ToggleGroup();
        rdoTakingPrescY.setToggleGroup(MD1Q6_toggleGroup);
        rdoTakingPrescN.setToggleGroup(MD1Q6_toggleGroup);
        rdoTakingPrescY.setSelected(true);

        //Question 7/
        MD1Q7_toggleGroup = new ToggleGroup();
        rdoMedsMismanagementY.setToggleGroup(MD1Q7_toggleGroup);
        rdoMedsMismanagementN.setToggleGroup(MD1Q7_toggleGroup);
        rdoMedsMismanagementY.setSelected(true);

        //Question 8/
        MD1Q8_toggleGroup = new ToggleGroup();
        rdoMedPepW.setToggleGroup(MD1Q8_toggleGroup);
        rdoMedPepO.setToggleGroup(MD1Q8_toggleGroup);
        rdoMedPepW.setSelected(true);

        //Question 12/
        MD1Q12_toggleGroup = new ToggleGroup();
        rdoSupplementY.setToggleGroup(MD1Q12_toggleGroup);
        rdoSupplementN.setToggleGroup(MD1Q12_toggleGroup);
        rdoSupplementY.setSelected(true);

        //Question 13/
        MD1Q13_toggleGroup = new ToggleGroup();
        rdoBloodMonitorAssistY.setToggleGroup(MD1Q13_toggleGroup);
        rdoBloodMonitorAssistN.setToggleGroup(MD1Q13_toggleGroup);
        rdoBloodMonitorAssistY.setSelected(true);

        //Question 14/
        MD1Q14_toggleGroup = new ToggleGroup();
        rdoInjectAssistY.setToggleGroup(MD1Q14_toggleGroup);
        rdoInjectAssistN.setToggleGroup(MD1Q14_toggleGroup);
        rdoInjectAssistY.setSelected(true);

    }

    private void PMgt_BtnGrp() {
        //Question 1/
        PM1Q1_toggleGroup = new ToggleGroup();
        rdoPainExpY.setToggleGroup(PM1Q1_toggleGroup);
        rdoPainExpN.setToggleGroup(PM1Q1_toggleGroup);
        rdoPainExpY.setSelected(true);

        //Question 2/
        PM1Q2_toggleGroup = new ToggleGroup();
        rdoPainMedY.setToggleGroup(PM1Q2_toggleGroup);
        rdoPainMedN.setToggleGroup(PM1Q2_toggleGroup);
        rdoPainMedY.setSelected(true);

        // Initially enable txtPainMedFreq and txtPainLevel because "Yes" is selected
        txtPainMedFreq.setDisable(false);
        txtPainLevel.setDisable(false);

        // Add listener to the PM1Q2_toggleGroup
        PM1Q2_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoPainMedY) {
                // Enable txtPainMedFreq and txtPainLevel if "Yes" is selected
                txtPainMedFreq.setDisable(false);
                txtPainLevel.setDisable(false);
            } else if (newValue == rdoPainMedN) {
                // Disable txtPainMedFreq and txtPainLevel if "No" is selected
                txtPainMedFreq.setDisable(true);
                txtPainLevel.setDisable(true);
            }
        });

        //Question 5/
        PM1Q5_toggleGroup = new ToggleGroup();
        txtRespiteRequireY.setToggleGroup(PM1Q5_toggleGroup);
        rdoRespiteRequireN.setToggleGroup(PM1Q5_toggleGroup);
        txtRespiteRequireY.setSelected(true);

        //Question 6/
        PM1SQ5_toggleGroup = new ToggleGroup();
        rdoRespiteDeliveryI.setToggleGroup(PM1SQ5_toggleGroup);
        rdoRespiteDeliveryR.setToggleGroup(PM1SQ5_toggleGroup);

        // Add listener to the PM1Q5_toggleGroup
        PM1Q5_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == txtRespiteRequireY) {
                // Enable respite delivery options if "Yes" is selected
                rdoRespiteDeliveryI.setDisable(false);
                rdoRespiteDeliveryR.setDisable(false);
            } else if (newValue == rdoRespiteRequireN) {
                // Disable respite delivery options if "No" is selected
                rdoRespiteDeliveryI.setDisable(true);
                rdoRespiteDeliveryR.setDisable(true);
            }
        });

    }

    private void House_BtnGrp() {
        //Question 1/
        H1Q1_toggleGroup = new ToggleGroup();
        rdoHousingOwnershipY.setToggleGroup(H1Q1_toggleGroup);
        rdoHousingOwnershipR.setToggleGroup(H1Q1_toggleGroup);
        rdoHousingOwnershipG.setToggleGroup(H1Q1_toggleGroup);
        rdoHousingOwnershipO.setToggleGroup(H1Q1_toggleGroup);
        rdoHousingOwnershipY.setSelected(true);

        //Question 2/
        H1Q2_toggleGroup = new ToggleGroup();
        rdoLivingSetupY.setToggleGroup(H1Q2_toggleGroup);
        rdoLivingSetupN1.setToggleGroup(H1Q2_toggleGroup);
        rdoLivingSetupN2.setToggleGroup(H1Q2_toggleGroup);
        rdoLivingSetupY.setSelected(true);

        //Question 3/
        H1Q3_toggleGroup = new ToggleGroup();
        rdoPersonalAlarmY.setToggleGroup(H1Q3_toggleGroup);
        rdoPersonalAlarmN.setToggleGroup(H1Q3_toggleGroup);
        rdoPersonalAlarmY.setSelected(true);

        // Initially enable txtAlarmCompany because "Yes" is selected
        txtAlarmCompany.setDisable(false);

        // Add listener to the H1Q3_toggleGroup
        H1Q3_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoPersonalAlarmY) {
                // Enable txtAlarmCompany if "Yes" is selected
                txtAlarmCompany.setDisable(false);
                txtPodiatrist.setDisable(false);  // Enable txtPodiatrist when "Yes" is selected
            } else if (newValue == rdoPersonalAlarmN) {
                // Disable txtAlarmCompany and txtPodiatrist if "No" is selected
                txtAlarmCompany.setDisable(true);
                txtPodiatrist.setDisable(true);
            }
        });

        //Question 6/
        H1Q6_toggleGroup = new ToggleGroup();
        rdoKeyLockBoxY.setToggleGroup(H1Q6_toggleGroup);
        rdoKeyLockBoxN.setToggleGroup(H1Q6_toggleGroup);
        rdoKeyLockBoxY.setSelected(true);

        //Question 6/
        H1Q6_toggleGroup = new ToggleGroup();
        rdoKeyLockBoxY.setToggleGroup(H1Q6_toggleGroup);
        rdoKeyLockBoxN.setToggleGroup(H1Q6_toggleGroup);
        rdoKeyLockBoxY.setSelected(true);

        // Initially enable txtKeyLockCode because "Yes" is selected
        txtKeyLockCode.setDisable(false);

        // Add listener to the H1Q6_toggleGroup
        H1Q6_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == rdoKeyLockBoxY) {
                // Enable txtKeyLockCode if "Yes" is selected
                txtKeyLockCode.setDisable(false);
                txtPodiatrist.setDisable(false);  // Enable txtPodiatrist when "Yes" is selected
            } else if (newValue == rdoKeyLockBoxN) {
                // Disable txtKeyLockCode and txtPodiatrist if "No" is selected
                txtKeyLockCode.setDisable(true);
                txtPodiatrist.setDisable(true);
            }
        });

    }

    private void HomeE_BtnGrp() {
        //Question 1/
        HE1Q1_toggleGroup = new ToggleGroup();
        rdoHomeMaintainSupportY.setToggleGroup(HE1Q1_toggleGroup);
        rdoHomeMaintainSupportN.setToggleGroup(HE1Q1_toggleGroup);
        rdoHomeMaintainSupportY.setSelected(true);

        //Question 3/ 
        HE1Q3_toggleGroup = new ToggleGroup();
        rdoSocialActivityY.setToggleGroup(HE1Q3_toggleGroup);
        rdoSocialActivityN.setToggleGroup(HE1Q3_toggleGroup);
        rdoSocialActivityY.setSelected(true);

        //Question 4/ 
        HE1Q4_toggleGroup = new ToggleGroup();
        rdoTransportAssistY.setToggleGroup(HE1Q4_toggleGroup);
        rdoTransportAssistN.setToggleGroup(HE1Q4_toggleGroup);
        rdoTransportAssistY.setSelected(true);

        //Question 5/ 
        HE1Q5_toggleGroup = new ToggleGroup();
        rdoCanDriveY.setToggleGroup(HE1Q5_toggleGroup);
        rdoCanDriveN.setToggleGroup(HE1Q5_toggleGroup);
        rdoCanDriveY.setSelected(true);

//              //Secondary Question 6/
//        HE1SQ6_toggleGroup = new ToggleGroup();
//        rdoTaxiVoucher.setToggleGroup(HE1SQ6_toggleGroup);
//        rdoShoppingAssist.setToggleGroup(HE1SQ6_toggleGroup);
//        rdoDocAptAssist.setToggleGroup(HE1SQ6_toggleGroup);
//        rdoOtherAssist.setToggleGroup(HE1SQ6_toggleGroup);
//
//        // Initially disable txtOtherAssist because a specific option (not "Other") might be selected
//        txtOtherAssist.setDisable(true);
//
//        // Add listener to the HE1SQ6_toggleGroup
//        HE1SQ6_toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue == rdoOtherAssist) {
//                // Enable txtOtherAssist if "Other" is selected
//                txtOtherAssist.setDisable(false);
//            } else {
//                // Disable txtOtherAssist if any other option is selected
//                txtOtherAssist.setDisable(true);
//            }
//        });
    }

    @FXML
    private void alliedHealthRefY(ActionEvent event) {
    }

    @FXML
    private void alliedHealthRefN(ActionEvent event) {
    }

    @FXML
    private void startAssesmentBtn(ActionEvent event) {

//             String aID = assessNo.getText();
//        String ID = cID.getText();
        String CSO = csoName.getText();
 
        
        String av = aVenue.getValue().toString();
        String ov = aVenueOthers.getText();
        System.out.println(caseN);

        String asessDate = aDate.getValue() != null ? aDate.getValue().toString() : "";
//     //Create an entry to Assessment Table
        String insertAssessment = "INSERT INTO clientassessment (clientID, assessmentID,assignedCSO, assessmentDate, caseID, assessVenue, assessAddress)"
                + "VALUES ('" + clientID + "', '" + assessmentID + "', '" + CSO + "', '" + asessDate + "', '" + caseN + "', '" + av + "', '" + ov + "')";

        //, '" + aVenue + "', '" + aVenueOthers + "')";
        //create budgetstaging entry for this particular client,case and assessment
        String insertBudgetStaging = "INSERT INTO budget_staging (clientID, assessmentID, caseID, serviceID) "
                + "SELECT '" + clientID + "', '" + assessmentID + "', '" + caseN + "', serviceID "
                + "FROM serviceoffered";

// SQL query to insert the clientHygiene record
        String insertHygiene = "INSERT INTO clientHygiene (clientID, assessmentID) VALUES ('" + clientID + "', '" + assessmentID + "')";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Execute the insert query
//    int rowsAffected = stmt.executeUpdate(insertHygiene);
            int rowsAffected = stmt.executeUpdate(insertAssessment);

            if (rowsAffected > 0) {
                int rowsAffected2 = stmt.executeUpdate(insertHygiene);
                int rowsAffected3 = stmt.executeUpdate(insertBudgetStaging);

                System.out.println("Record inserted successfully.");
                       clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(true);
        startAssesmentBtn.setDisable(true);
            } else {
                System.out.println("Insert failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(true);
        startAssesmentBtn.setDisable(true);
    }

    @FXML
    private void cancelAssessment() {
        // Get the current stage (window)
        Stage stage = (Stage) cancelAssessment.getScene().getWindow();
        // Close the stage
        stage.close();
    }

    @FXML
    private void btnBackClientHygiene(ActionEvent event) {
        clientDetsPane.setVisible(true);
        paneClientHygiene.setVisible(false);

    }

    @FXML
    private void btnNextClientHygiene(ActionEvent event) {

        RadioButton q1_Selected = (RadioButton) P1Q1_toggleGroup.getSelectedToggle();
        String bpgRequirement = q1_Selected != null ? q1_Selected.getText() : null;

        RadioButton q2_Selected = (RadioButton) P1Q2_toggleGroup.getSelectedToggle();
        String bathing = q2_Selected != null ? q2_Selected.getText() : null;

        RadioButton q3_Selected = (RadioButton) P1Q3_toggleGroup.getSelectedToggle();
        String bodyb = q3_Selected != null ? q3_Selected.getText() : null;

        RadioButton q4_Selected = (RadioButton) P1Q4_toggleGroup.getSelectedToggle();
        String groominScrug = q4_Selected != null ? q4_Selected.getText() : null;

        RadioButton q5_Selected = (RadioButton) P1Q5_toggleGroup.getSelectedToggle();
        String showerIntervals = q5_Selected != null ? q5_Selected.getText() : null;

        RadioButton q7_Selected = (RadioButton) P1Q7_toggleGroup.getSelectedToggle();
        String showerCarerGender = q7_Selected != null ? q7_Selected.getText() : null;

        RadioButton q8_Selected = (RadioButton) P1Q8_toggleGroup.getSelectedToggle();
        String showerPrefDay = q8_Selected != null ? q8_Selected.getText() : null;

        RadioButton q9_Selected = (RadioButton) P1Q9_toggleGroup.getSelectedToggle();
        String dressingRequirement = q9_Selected != null ? q9_Selected.getText() : null;

        RadioButton q10_Selected = (RadioButton) P1Q10_toggleGroup.getSelectedToggle();
        String dressing = q10_Selected != null ? q10_Selected.getText() : null;

        RadioButton q11_Selected = (RadioButton) P1Q11_toggleGroup.getSelectedToggle();
        String undressing = q11_Selected != null ? q11_Selected.getText() : null;

        RadioButton q12_Selected = (RadioButton) P1Q12_toggleGroup.getSelectedToggle();
        String socks = q12_Selected != null ? q12_Selected.getText() : null;

        RadioButton q13_Selected = (RadioButton) P1Q13_toggleGroup.getSelectedToggle();
        String shoes = q13_Selected != null ? q13_Selected.getText() : null;

        RadioButton q15_Selected = (RadioButton) P1Q15_toggleGroup.getSelectedToggle();
        String dressingCarerGender = q15_Selected != null ? q15_Selected.getText() : null;

        RadioButton q16_Selected = (RadioButton) P1Q16_toggleGroup.getSelectedToggle();
        String dressingPrefDay = q16_Selected != null ? q16_Selected.getText() : null;

        String checkAssessmentExistenceQuery = "SELECT COUNT(*) FROM clientassessment WHERE assessmentID = '" + assessmentID + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Check if the assessmentID exists
            ResultSet rs = stmt.executeQuery(checkAssessmentExistenceQuery);
            rs.next();
            int assessmentCount = rs.getInt(1);

            if (assessmentCount == 0) {
                System.out.println("AssessmentID does not exist. Please check the ID and try again.");
            } else {

                //
                //update or insert entry for this client
                //dont forget to change the rdoBpgRequirement3 with the correct button in each panel
                String counter = "1";
                String sID = "PCA";
                String upsertBudgetStaging = "INSERT INTO budget_staging (clientID, assessmentID, caseID, counter, serviceID) "
                        + "SELECT '" + clientID + "', '" + assessmentID + "', '" + caseN + "', '" + counter + "', serviceID "
                        + "FROM serviceoffered "
                        + "WHERE serviceID = '" + sID + "' "
                        + "ON DUPLICATE KEY UPDATE "
                        + "clientID = VALUES(clientID), "
                        + "assessmentID = VALUES(assessmentID), "
                        + "caseID = VALUES(caseID), "
                        + "counter = VALUES(counter), "
                        + "serviceID = VALUES(serviceID)";

                if (!rdoBpgRequirement3.isSelected()) {

                    int rowsAffected = stmt.executeUpdate(upsertBudgetStaging);
                }

                // SQL query for upsert
                String upsertQuery = "INSERT INTO clientHygiene (clientID, assessmentID, bpgRequirement, bathing, bodyb, groominScrug, showerIntervals, showerCarerGender, showerPrefDay, dressingRequirement, dressing, undressing, socks, shoes, dressingCarerGender, dressingPrefDay) "
                        + "VALUES ('" + clientID + "', '" + assessmentID + "', '"
                        + bpgRequirement + "', '" + bathing + "', '" + bodyb + "', '"
                        + groominScrug + "', '" + showerIntervals + "', '" + showerCarerGender + "', '"
                        + showerPrefDay + "', '" + dressingRequirement + "', '" + dressing + "', '"
                        + undressing + "', '" + socks + "', '" + shoes + "', '"
                        + dressingCarerGender + "', '" + dressingPrefDay + "') "
                        + "ON DUPLICATE KEY UPDATE "
                        + "bpgRequirement = VALUES(bpgRequirement), "
                        + "bathing = VALUES(bathing), "
                        + "bodyb = VALUES(bodyb), "
                        + "groominScrug = VALUES(groominScrug), "
                        + "showerIntervals = VALUES(showerIntervals), "
                        + "showerCarerGender = VALUES(showerCarerGender), "
                        + "showerPrefDay = VALUES(showerPrefDay), "
                        + "dressingRequirement = VALUES(dressingRequirement), "
                        + "dressing = VALUES(dressing), "
                        + "undressing = VALUES(undressing), "
                        + "socks = VALUES(socks), "
                        + "shoes = VALUES(shoes), "
                        + "dressingCarerGender = VALUES(dressingCarerGender), "
                        + "dressingPrefDay = VALUES(dressingPrefDay)";

                // Execute the upsert query
                int rowsAffected = stmt.executeUpdate(upsertQuery);
                if (rowsAffected > 0) {
                    System.out.println("Record inserted or updated successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(true);
    }

    @FXML
    private void btnBackOralHygiene(ActionEvent event) {
        paneClientHygiene.setVisible(true);
        paneOralHygiene.setVisible(false);

    }

    @FXML
    private void btnNextOralHygiene(ActionEvent event) {

        // Question 1
        RadioButton o1_Selected = (RadioButton) O1Q1_toggleGroup.getSelectedToggle();
        String o1_selectedValue = o1_Selected != null ? o1_Selected.getText() : null;

        // Question 2
        RadioButton o2_Selected = (RadioButton) O1Q2_toggleGroup.getSelectedToggle();
        String o2_selectedValue = o2_Selected != null ? o2_Selected.getText() : null;

        // Question 3
        RadioButton o3_Selected = (RadioButton) O1Q3_toggleGroup.getSelectedToggle();
        String o3_selectedValue = o3_Selected != null ? o3_Selected.getText() : null;

        // Question 4
        RadioButton o4_Selected = (RadioButton) O1Q4_toggleGroup.getSelectedToggle();
        String o4_selectedValue = o4_Selected != null ? o4_Selected.getText() : null;

        // Question 5
        RadioButton o5_Selected = (RadioButton) O1Q5_toggleGroup.getSelectedToggle();
        String o5_selectedValue = o5_Selected != null ? o5_Selected.getText() : null;

        // Question 6
        RadioButton o6_Selected = (RadioButton) O1Q6_toggleGroup.getSelectedToggle();
        String o6_selectedValue = o6_Selected != null ? o6_Selected.getText() : null;

        // Question 7 - Dentist Visit Date
        LocalDate dentistVisitDate = dateDentistVisit.getValue();
        String dentistVisit = dentistVisitDate != null ? dentistVisitDate.toString() : LocalDate.now().toString();

        // Question 8 - Last Visit Date
        LocalDate lastVisitDate = dateLastVisit.getValue();
        String lastVisit = lastVisitDate != null ? lastVisitDate.toString() : LocalDate.now().toString();

        // Question 9 - Dentist Name
        String dentistName = TextDentistName.getText();

        // SQL query for UPSERT
        String upsertQuery = "INSERT INTO clientDental (clientID, assessmentID, oralHygieneRequirement, holdToothbrush, toothPasteSq, mouthRinse, mouthIssues, teethForms, dentistVisit, lastVisit, dentistName) "
                + "VALUES ('" + clientID + "', '" + assessmentID + "', '" + o1_selectedValue + "', '" + o2_selectedValue + "', '"
                + o3_selectedValue + "', '" + o4_selectedValue + "', '" + o5_selectedValue + "', '"
                + o6_selectedValue + "', '" + dentistVisit + "', '" + lastVisit + "', '" + dentistName + "') "
                + "ON DUPLICATE KEY UPDATE "
                + "oralHygieneRequirement = VALUES(oralHygieneRequirement), "
                + "holdToothbrush = VALUES(holdToothbrush), "
                + "toothPasteSq = VALUES(toothPasteSq), "
                + "mouthRinse = VALUES(mouthRinse), "
                + "mouthIssues = VALUES(mouthIssues), "
                + "teethForms = VALUES(teethForms), "
                + "dentistVisit = VALUES(dentistVisit), "
                + "lastVisit = VALUES(lastVisit), "
                + "dentistName = VALUES(dentistName)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(upsertQuery);
            if (rowsAffected > 0) {
                System.out.println("Record inserted or updated successfully.");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(true);
    }

    @FXML
    private void btnContinenceNeeds(ActionEvent event) {
        paneOralHygiene.setVisible(true);
        paneToileting.setVisible(false);

    }

    @FXML
    private void btnNextContinenceNeeds(ActionEvent event) {

        // Question 1
        RadioButton q1_Selected = (RadioButton) T1Q1_toggleGroup.getSelectedToggle();
        String toiletingRequirement = q1_Selected != null ? q1_Selected.getText() : null;

        // Question 2
        RadioButton q2_Selected = (RadioButton) T1Q2_toggleGroup.getSelectedToggle();
        String walkToilet = q2_Selected != null ? q2_Selected.getText() : null;

        // Question 3
        RadioButton q3_Selected = (RadioButton) T1Q3_toggleGroup.getSelectedToggle();
        String pantsOff = q3_Selected != null ? q3_Selected.getText() : null;

        // Question 4
        RadioButton q4_Selected = (RadioButton) T1Q4_toggleGroup.getSelectedToggle();
        String toiletSitting = q4_Selected != null ? q4_Selected.getText() : null;

        // Question 5
        RadioButton q5_Selected = (RadioButton) T1Q5_toggleGroup.getSelectedToggle();
        String pWashing = q5_Selected != null ? q5_Selected.getText() : null;

        // Question 6
        RadioButton q6_Selected = (RadioButton) T1Q6_toggleGroup.getSelectedToggle();
        String pDrying = q6_Selected != null ? q6_Selected.getText() : null;

        // Question 7
        RadioButton q7_Selected = (RadioButton) T1Q7_toggleGroup.getSelectedToggle();
        String continenceRequirement = q7_Selected != null ? q7_Selected.getText() : null;

        // Question 8
        RadioButton q8_Selected = (RadioButton) T1Q8_toggleGroup.getSelectedToggle();
        String setupPads = q8_Selected != null ? q8_Selected.getText() : null;

        // Question 9
        RadioButton q9_Selected = (RadioButton) T1Q9_toggleGroup.getSelectedToggle();
        String pWipeDry = q9_Selected != null ? q9_Selected.getText() : null;

        // Question 10, 11, 12 (TextFields)
        String continenceNeed = txtContinenceNeed.getText();
        String size = txtSize.getText();
        String continenceFund = txtContinenceFund.getText();

        // SQL query for inserting or updating continence needs
        String clientToiletingQuery = "INSERT INTO clientToileting (clientID, assessmentID, toiletingRequirement, walkToilet, pantsOff, toiletSitting, pWashing, pDrying, continenceRequirement, setupPads, pWipeDry, continenceNeed, size, continenceFund) "
                + "VALUES ('" + clientID + "', '" + assessmentID + "', '"
                + toiletingRequirement + "', '" + walkToilet + "', '" + pantsOff + "', '"
                + toiletSitting + "', '" + pWashing + "', '" + pDrying + "', '"
                + continenceRequirement + "', '" + setupPads + "', '" + pWipeDry + "', '"
                + continenceNeed + "', '" + size + "', '" + continenceFund + "') "
                + "ON DUPLICATE KEY UPDATE "
                + "toiletingRequirement = VALUES(toiletingRequirement), "
                + "walkToilet = VALUES(walkToilet), "
                + "pantsOff = VALUES(pantsOff), "
                + "toiletSitting = VALUES(toiletSitting), "
                + "pWashing = VALUES(pWashing), "
                + "pDrying = VALUES(pDrying), "
                + "continenceRequirement = VALUES(continenceRequirement), "
                + "setupPads = VALUES(setupPads), "
                + "pWipeDry = VALUES(pWipeDry), "
                + "continenceNeed = VALUES(continenceNeed), "
                + "size = VALUES(size), "
                + "continenceFund = VALUES(continenceFund)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(clientToiletingQuery);
            if (rowsAffected > 0) {
                System.out.println("Continence needs data inserted or updated successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(true);
    }

    @FXML
    private void btnBackMobilityNeeds(ActionEvent event) {
        paneToileting.setVisible(true);
        paneMobilityNeeds.setVisible(false);

    }

    @FXML
    private void btnNextMobilityNeeds(ActionEvent event) {

        // Question 1
        RadioButton m1_Selected = (RadioButton) M1Q1_toggleGroup.getSelectedToggle();
        String mobilityRequirement = m1_Selected != null ? m1_Selected.getText() : null;

        // Question 2
        RadioButton m2_Selected = (RadioButton) M1Q2_toggleGroup.getSelectedToggle();
        String mobilityIssue = m2_Selected != null ? m2_Selected.getText() : null;

        // Question 3
        String issueDets = txtIssueDets.getText();

        // Question 4
        RadioButton m4_Selected = (RadioButton) M1Q4_toggleGroup.getSelectedToggle();
        String tStand = m4_Selected != null ? m4_Selected.getText() : null;

        // Question 5
        RadioButton m5_Selected = (RadioButton) M1Q5_toggleGroup.getSelectedToggle();
        String tSit = m5_Selected != null ? m5_Selected.getText() : null;

        // Question 6
        RadioButton m6_Selected = (RadioButton) M1Q6_toggleGroup.getSelectedToggle();
        String tLying = m6_Selected != null ? m6_Selected.getText() : null;

        // Question 7
        RadioButton m7_Selected = (RadioButton) M1Q7_toggleGroup.getSelectedToggle();
        String tSitting = m7_Selected != null ? m7_Selected.getText() : null;

        // Question 8
        RadioButton m8_Selected = (RadioButton) M1Q8_toggleGroup.getSelectedToggle();
        String walking = m8_Selected != null ? m8_Selected.getText() : null;

        // Question 9
        RadioButton m9_Selected = (RadioButton) M1Q9_toggleGroup.getSelectedToggle();
        String carRide = m9_Selected != null ? m9_Selected.getText() : null;

        // Question 10
        RadioButton m10_Selected = (RadioButton) M1Q10_toggleGroup.getSelectedToggle();
        String useStairs = m10_Selected != null ? m10_Selected.getText() : null;

        // Question 11
        RadioButton m11_Selected = (RadioButton) M1Q11_toggleGroup.getSelectedToggle();
        String mobilityAids = m11_Selected != null ? m11_Selected.getText() : null;

        // Question 12
        RadioButton m12_Selected = (RadioButton) M1Q12_toggleGroup.getSelectedToggle();
        String lifterRequirement = m12_Selected != null ? m12_Selected.getText() : null;

        // Question 13
        RadioButton m13_Selected = (RadioButton) M1Q13_toggleGroup.getSelectedToggle();
        String fallHistory = m13_Selected != null ? m13_Selected.getText() : null;

        // Question 14
        String fallDets = txtFallDets.getText();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            String counter = "1";
            String sID = "CA";
            String upsertBudgetStaging = "INSERT INTO budget_staging (clientID, assessmentID, caseID, counter, serviceID) "
                    + "SELECT '" + clientID + "', '" + assessmentID + "', '" + caseN + "', '" + counter + "', serviceID "
                    + "FROM serviceoffered "
                    + "WHERE serviceID = '" + sID + "' "
                    + "ON DUPLICATE KEY UPDATE "
                    + "clientID = VALUES(clientID), "
                    + "assessmentID = VALUES(assessmentID), "
                    + "caseID = VALUES(caseID), "
                    + "counter = VALUES(counter), "
                    + "serviceID = VALUES(serviceID)";

            if (!rdoMobilityRequirement1.isSelected()) {

                int rowsAffected2 = stmt.executeUpdate(upsertBudgetStaging);
            }

            // SQL query for inserting or updating mobility needs
            String clientMobilityQuery = "INSERT INTO clientMobility (clientID, assessmentID, mobilityRequirement, mobilityIssue, issueDets, tStand, tSit, tLying, tSitting, walking, carRide, useStairs, mobilityAids, lifterRequirement, fallHistory, fallDets) "
                    + "VALUES ('" + clientID + "', '" + assessmentID + "', '"
                    + mobilityRequirement + "', '" + mobilityIssue + "', '" + issueDets + "', '"
                    + tStand + "', '" + tSit + "', '" + tLying + "', '"
                    + tSitting + "', '" + walking + "', '" + carRide + "', '"
                    + useStairs + "', '" + mobilityAids + "', '" + lifterRequirement + "', '"
                    + fallHistory + "', '" + fallDets + "') "
                    + "ON DUPLICATE KEY UPDATE "
                    + "mobilityRequirement = VALUES(mobilityRequirement), "
                    + "mobilityIssue = VALUES(mobilityIssue), "
                    + "issueDets = VALUES(issueDets), "
                    + "tStand = VALUES(tStand), "
                    + "tSit = VALUES(tSit), "
                    + "tLying = VALUES(tLying), "
                    + "tSitting = VALUES(tSitting), "
                    + "walking = VALUES(walking), "
                    + "carRide = VALUES(carRide), "
                    + "useStairs = VALUES(useStairs), "
                    + "mobilityAids = VALUES(mobilityAids), "
                    + "lifterRequirement = VALUES(lifterRequirement), "
                    + "fallHistory = VALUES(fallHistory), "
                    + "fallDets = VALUES(fallDets)";

            int rowsAffected = stmt.executeUpdate(clientMobilityQuery);
            if (rowsAffected > 0) {
                System.out.println("Mobility needs data inserted or updated successfully.");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(true);
    }

    @FXML
    private void btnBackMobilityNeeds1(ActionEvent event) {
        paneMobilityNeeds.setVisible(true);
        paneMobilityNeeds1.setVisible(false);

    }

    @FXML
    private void btnNextMobilityNeeds1(ActionEvent event) {

        RadioButton om1_Selected = (RadioButton) OM1Q1_toggleGroup.getSelectedToggle();
        String om1_selectedValue = om1_Selected != null ? om1_Selected.getText() : null;

        String om2_selectedValue = txtExerciseDets.getText();

        RadioButton om3_Selected = (RadioButton) OM1Q3_toggleGroup.getSelectedToggle();
        String om3_selectedValue = om3_Selected != null ? om3_Selected.getText() : null;

        RadioButton om4_Selected = (RadioButton) OM1Q4_toggleGroup.getSelectedToggle();
        String om4_selectedValue = om4_Selected != null ? om4_Selected.getText() : null;

        RadioButton om5_Selected = (RadioButton) OM1Q5_toggleGroup.getSelectedToggle();
        String om5_selectedValue = om5_Selected != null ? om5_Selected.getText() : null;

        RadioButton om6_Selected = (RadioButton) OM1Q6_toggleGroup.getSelectedToggle();
        String om6_selectedValue = om6_Selected != null ? om6_Selected.getText() : null;

        RadioButton om7_Selected = (RadioButton) OM1Q7_toggleGroup.getSelectedToggle();
        String om7_selectedValue = om7_Selected != null ? om7_Selected.getText() : null;

        String om8_selectedValue = txtBreathIssueDets.getText();

        RadioButton om9_Selected = (RadioButton) OM1Q9_toggleGroup.getSelectedToggle();
        String om9_selectedValue = om9_Selected != null ? om9_Selected.getText() : null;

        RadioButton om10_Selected = (RadioButton) OM1Q10_toggleGroup.getSelectedToggle();
        String om10_selectedValue = om10_Selected != null ? om10_Selected.getText() : null;

        RadioButton om11_Selected = (RadioButton) OM1Q11_toggleGroup.getSelectedToggle();
        String om11_selectedValue = om11_Selected != null ? om11_Selected.getText() : null;

        // Construct the SQL query
        String otherMobilityQuery = "INSERT INTO otherMobility (clientID, assessmentID, exercise, exerciseDets, alliedHealthRef, unsteady, stooped, leaning, breathIssue, breathIssueDets, commsRequirement, hAidsFitting, battChange) "
                + "VALUES ('" + clientID + "', '" + assessmentID + "', '" + om1_selectedValue + "', '" + om2_selectedValue + "', '"
                + om3_selectedValue + "', '" + om4_selectedValue + "', '" + om5_selectedValue + "', '" + om6_selectedValue + "', '"
                + om7_selectedValue + "', '" + om8_selectedValue + "', '" + om9_selectedValue + "', '"
                + om10_selectedValue + "', '" + om11_selectedValue + "') "
                + "ON DUPLICATE KEY UPDATE "
                + "exercise = VALUES(exercise), "
                + "exerciseDets = VALUES(exerciseDets), "
                + "alliedHealthRef = VALUES(alliedHealthRef), "
                + "unsteady = VALUES(unsteady), "
                + "stooped = VALUES(stooped), "
                + "leaning = VALUES(leaning), "
                + "breathIssue = VALUES(breathIssue), "
                + "breathIssueDets = VALUES(breathIssueDets), "
                + "commsRequirement = VALUES(commsRequirement), "
                + "hAidsFitting = VALUES(hAidsFitting), "
                + "battChange = VALUES(battChange)";

        // Execute the query
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(otherMobilityQuery);
            if (rowsAffected > 0) {
                System.out.println("Record inserted or updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(true);
    }

    @FXML
    private void btnBackNutritionalNeeds(ActionEvent event) {
        paneMobilityNeeds1.setVisible(true);
        paneNutritionalNeeds.setVisible(false);

    }

    @FXML
    private void btnNextNutritionalNeeds(ActionEvent event) {

        RadioButton n1_Selected = (RadioButton) N1Q1_toggleGroup.getSelectedToggle();
        String n1_selectedValue = n1_Selected != null ? n1_Selected.getText() : null;

        RadioButton n2_Selected = (RadioButton) N1Q2_toggleGroup.getSelectedToggle();
        String n2_selectedValue = n2_Selected != null ? n2_Selected.getText() : null;

        RadioButton n3_Selected = (RadioButton) N1Q3_toggleGroup.getSelectedToggle();
        String n3_selectedValue = n3_Selected != null ? n3_Selected.getText() : null;

        RadioButton n4_Selected = (RadioButton) N1Q4_toggleGroup.getSelectedToggle();
        String n4_selectedValue = n4_Selected != null ? n4_Selected.getText() : null;

        RadioButton n5_Selected = (RadioButton) N1Q5_toggleGroup.getSelectedToggle();
        String n5_selectedValue = n5_Selected != null ? n5_Selected.getText() : null;

        RadioButton n6_Selected = (RadioButton) N1Q6_toggleGroup.getSelectedToggle();
        String n6_selectedValue = n6_Selected != null ? n6_Selected.getText() : null;

        RadioButton n7_Selected = (RadioButton) N1Q7_toggleGroup.getSelectedToggle();
        String n7_selectedValue = n7_Selected != null ? n7_Selected.getText() : null;

        RadioButton n8_Selected = (RadioButton) N1Q8_toggleGroup.getSelectedToggle();
        String n8_selectedValue = n8_Selected != null ? n8_Selected.getText() : null;

        RadioButton n9_Selected = (RadioButton) N1Q9_toggleGroup.getSelectedToggle();
        String n9_selectedValue = n9_Selected != null ? n9_Selected.getText() : null;

        RadioButton n10_Selected = (RadioButton) N1Q10_toggleGroup.getSelectedToggle();
        String n10_selectedValue = n10_Selected != null ? n10_Selected.getText() : null;

        // SQL query to check if assessmentID exists
        String checkAssessmentExistenceQuery = "SELECT COUNT(*) FROM clientassessment WHERE assessmentID = '" + assessmentID + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Check if the assessmentID exists
            ResultSet rs = stmt.executeQuery(checkAssessmentExistenceQuery);
            rs.next();
            int assessmentCount = rs.getInt(1);

            if (assessmentCount == 0) {
                System.out.println("AssessmentID does not exist. Please check the ID and try again.");
            } else {
                // SQL query for upsert
                String upsertQuery = "INSERT INTO clientNutrition (clientID, assessmentID, nutritionRequirement, mealPrep, foodService, swallowIssue, weightLoss, needSupplement, useCutlery, dietaryRequirement, mealSize, dietRef) "
                        + "VALUES ('" + clientID + "', '" + assessmentID + "', '" + n1_selectedValue + "', '" + n2_selectedValue + "', '"
                        + n3_selectedValue + "', '" + n4_selectedValue + "', '" + n5_selectedValue + "', '"
                        + n6_selectedValue + "', '" + n7_selectedValue + "', '" + n8_selectedValue + "', '"
                        + n9_selectedValue + "', '" + n10_selectedValue + "') "
                        + "ON DUPLICATE KEY UPDATE "
                        + "nutritionRequirement = VALUES(nutritionRequirement), "
                        + "mealPrep = VALUES(mealPrep), "
                        + "foodService = VALUES(foodService), "
                        + "swallowIssue = VALUES(swallowIssue), "
                        + "weightLoss = VALUES(weightLoss), "
                        + "needSupplement = VALUES(needSupplement), "
                        + "useCutlery = VALUES(useCutlery), "
                        + "dietaryRequirement = VALUES(dietaryRequirement), "
                        + "mealSize = VALUES(mealSize), "
                        + "dietRef = VALUES(dietRef)";

                // Execute the upsert query
                int rowsAffected = stmt.executeUpdate(upsertQuery);

                String counter = "1";
                String sID = "MP";
                String upsertBudgetStaging = "INSERT INTO budget_staging (clientID, assessmentID, caseID, counter, serviceID) "
                        + "SELECT '" + clientID + "', '" + assessmentID + "', '" + caseN + "', '" + counter + "', serviceID "
                        + "FROM serviceoffered "
                        + "WHERE serviceID = '" + sID + "' "
                        + "ON DUPLICATE KEY UPDATE "
                        + "clientID = VALUES(clientID), "
                        + "assessmentID = VALUES(assessmentID), "
                        + "caseID = VALUES(caseID), "
                        + "counter = VALUES(counter), "
                        + "serviceID = VALUES(serviceID)";

                if (!rdoNutritionRequirement1.isSelected()) {

                    int rowsAffected2 = stmt.executeUpdate(upsertBudgetStaging);
                }

                if (rowsAffected > 0) {
                    System.out.println("Record inserted or updated successfully.");

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(true);
    }

    @FXML
    private void btnBackSkinIntegrityNeeds(ActionEvent event) {
        paneNutritionalNeeds.setVisible(true);
        paneSkinIntegrityNeeds.setVisible(false);

    }

    @FXML
    private void btnNextSkinIntegrityNeeds(ActionEvent event) {

        RadioButton q1_Selected = (RadioButton) S1Q1_toggleGroup.getSelectedToggle();
        String skinTear = q1_Selected != null ? q1_Selected.getText() : null;

        RadioButton q2_Selected = (RadioButton) S1Q2_toggleGroup.getSelectedToggle();
        String skinProblem = q2_Selected != null ? q2_Selected.getText() : null;

        RadioButton q3_Selected = (RadioButton) S1Q3_toggleGroup.getSelectedToggle();
        String useSkinProduct = q3_Selected != null ? q3_Selected.getText() : null;

        RadioButton q3S_Selected = (RadioButton) S1Q3S_toggleGroup.getSelectedToggle();
        String prescribed = q3S_Selected != null ? q3S_Selected.getText() : null;

        String skinProductDets = txtSkinProductDets.getText();

        RadioButton q5_Selected = (RadioButton) S1Q5_toggleGroup.getSelectedToggle();
        String woundDress = q5_Selected != null ? q5_Selected.getText() : null;

        RadioButton q6_Selected = (RadioButton) S1Q6_toggleGroup.getSelectedToggle();
        String assistReq = q6_Selected != null ? q6_Selected.getText() : null;

        RadioButton q7_Selected = (RadioButton) S1Q7_toggleGroup.getSelectedToggle();
        String pressureInjury = q7_Selected != null ? q7_Selected.getText() : null;

        String injuryDets = txtInjuryDets.getText();

        RadioButton q9_Selected = (RadioButton) S1Q9_toggleGroup.getSelectedToggle();
        String attendPodiatrist = q9_Selected != null ? q9_Selected.getText() : null;

        String podiatrist = txtPodiatrist.getText();
        String visitFreq = txtVisitFreq.getText();

        // SQL query to check if assessmentID exists
        String checkAssessmentExistenceQuery = "SELECT COUNT(*) FROM clientassessment WHERE assessmentID = '" + assessmentID + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Check if the assessmentID exists
            ResultSet rs = stmt.executeQuery(checkAssessmentExistenceQuery);
            rs.next();
            int assessmentCount = rs.getInt(1);

            if (assessmentCount == 0) {
                System.out.println("AssessmentID does not exist. Please check the ID and try again.");
            } else {
                // SQL query for upsert
                String upsertQuery = "INSERT INTO clientSkin (clientID, assessmentID, skinTear, skinProblem, useSkinProduct, prescribed, skinProductDets, woundDress, assistReq, pressureInjury, injuryDets, attendPodiatrist, podiatrist, visitFreq) "
                        + "VALUES ('" + clientID + "', '" + assessmentID + "', '" + skinTear + "', '" + skinProblem + "', '"
                        + useSkinProduct + "', '" + prescribed + "', '" + skinProductDets + "', '"
                        + woundDress + "', '" + assistReq + "', '" + pressureInjury + "', '"
                        + injuryDets + "', '" + attendPodiatrist + "', '" + podiatrist + "', '"
                        + visitFreq + "') "
                        + "ON DUPLICATE KEY UPDATE "
                        + "skinTear = VALUES(skinTear), "
                        + "skinProblem = VALUES(skinProblem), "
                        + "useSkinProduct = VALUES(useSkinProduct), "
                        + "prescribed = VALUES(prescribed), "
                        + "skinProductDets = VALUES(skinProductDets), "
                        + "woundDress = VALUES(woundDress), "
                        + "assistReq = VALUES(assistReq), "
                        + "pressureInjury = VALUES(pressureInjury), "
                        + "injuryDets = VALUES(injuryDets), "
                        + "attendPodiatrist = VALUES(attendPodiatrist), "
                        + "podiatrist = VALUES(podiatrist), "
                        + "visitFreq = VALUES(visitFreq)";

                // Execute the upsert query
                int rowsAffected = stmt.executeUpdate(upsertQuery);
                if (rowsAffected > 0) {
                    System.out.println("Record inserted or updated successfully.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(true);
    }

    @FXML
    private void btnBackCMHBN(ActionEvent event) {
        paneSkinIntegrityNeeds.setVisible(true);
        paneCMHBN.setVisible(false);

    }

    @FXML
    private void btnNextCMHBN(ActionEvent event) {

        RadioButton q1_Selected = (RadioButton) MH1Q1_toggleGroup.getSelectedToggle();
        String q1_selectedValue = q1_Selected != null ? q1_Selected.getText() : null;

        RadioButton q2_Selected = (RadioButton) MH1Q2_toggleGroup.getSelectedToggle();
        String q2_selectedValue = q2_Selected != null ? q2_Selected.getText() : null;

        RadioButton q3_Selected = (RadioButton) MH1Q3_toggleGroup.getSelectedToggle();
        String q3_selectedValue = q3_Selected != null ? q3_Selected.getText() : null;

        RadioButton q4_Selected = (RadioButton) MH1Q4_toggleGroup.getSelectedToggle();
        String q4_selectedValue = q4_Selected != null ? q4_Selected.getText() : null;

        RadioButton q5_Selected = (RadioButton) MH1Q5_toggleGroup.getSelectedToggle();
        String q5_selectedValue = q5_Selected != null ? q5_Selected.getText() : null;

        RadioButton q6_Selected = (RadioButton) MH1Q6_toggleGroup.getSelectedToggle();
        String q6_selectedValue = q6_Selected != null ? q6_Selected.getText() : null;

        RadioButton q7_Selected = (RadioButton) MH1Q7_toggleGroup.getSelectedToggle();
        String q7_selectedValue = q7_Selected != null ? q7_Selected.getText() : null;

        // SQL query to check if assessmentID exists
        String checkAssessmentExistenceQuery = "SELECT COUNT(*) FROM clientassessment WHERE assessmentID = '" + assessmentID + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Check if the assessmentID exists
            ResultSet rs = stmt.executeQuery(checkAssessmentExistenceQuery);
            rs.next();
            int assessmentCount = rs.getInt(1);

            if (assessmentCount == 0) {
                System.out.println("AssessmentID does not exist. Please check the ID and try again.");
            } else {
                // SQL query for upsert
                String upsertQuery = "INSERT INTO clientMental (clientID, assessmentID, cognitiveImpairment, cognitiveAssessment, memoryLoss, depression, schizophrenia, anxiety, otherMental) "
                        + "VALUES ('" + clientID + "', '" + assessmentID + "', '" + q1_selectedValue + "', '" + q2_selectedValue + "', '"
                        + q3_selectedValue + "', '" + q4_selectedValue + "', '" + q5_selectedValue + "', '"
                        + q6_selectedValue + "', '" + q7_selectedValue + "') "
                        + "ON DUPLICATE KEY UPDATE "
                        + "cognitiveImpairment = VALUES(cognitiveImpairment), "
                        + "cognitiveAssessment = VALUES(cognitiveAssessment), "
                        + "memoryLoss = VALUES(memoryLoss), "
                        + "depression = VALUES(depression), "
                        + "schizophrenia = VALUES(schizophrenia), "
                        + "anxiety = VALUES(anxiety), "
                        + "otherMental = VALUES(otherMental)";

                // Execute the upsert query
                int rowsAffected = stmt.executeUpdate(upsertQuery);
                if (rowsAffected > 0) {
                    System.out.println("Record inserted or updated successfully.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(true);
    }

    @FXML
    private void btnBackMPMN(ActionEvent event) {
        paneCMHBN.setVisible(true);
        paneMPMN.setVisible(false);

    }

    @FXML
    private void btnNextMPMN(ActionEvent event) {

        RadioButton q1_Selected = (RadioButton) MD1Q1_toggleGroup.getSelectedToggle();
        String q1_selectedValue = q1_Selected != null ? q1_Selected.getText() : null;

        RadioButton q2_Selected = (RadioButton) MD1Q2_toggleGroup.getSelectedToggle();
        String q2_selectedValue = q2_Selected != null ? q2_Selected.getText() : null;

        RadioButton q3_Selected = (RadioButton) MD1Q3_toggleGroup.getSelectedToggle();
        String q3_selectedValue = q3_Selected != null ? q3_Selected.getText() : null;

        RadioButton q4_Selected = (RadioButton) MD1Q4_toggleGroup.getSelectedToggle();
        String q4_selectedValue = q4_Selected != null ? q4_Selected.getText() : null;

        RadioButton q5_Selected = (RadioButton) MD1Q5_toggleGroup.getSelectedToggle();
        String q5_selectedValue = q5_Selected != null ? q5_Selected.getText() : null;

        RadioButton q6_Selected = (RadioButton) MD1Q6_toggleGroup.getSelectedToggle();
        String q6_selectedValue = q6_Selected != null ? q6_Selected.getText() : null;

        RadioButton q7_Selected = (RadioButton) MD1Q7_toggleGroup.getSelectedToggle();
        String q7_selectedValue = q7_Selected != null ? q7_Selected.getText() : null;

        RadioButton q8_Selected = (RadioButton) MD1Q8_toggleGroup.getSelectedToggle();
        String q8_selectedValue = q8_Selected != null ? q8_Selected.getText() : null;

        String medInterval = txtMedInterval.getText();
        String specialMed = txtSpecialMed.getText();
        String smDets = txtSmDets.getText();

        RadioButton q12_Selected = (RadioButton) MD1Q12_toggleGroup.getSelectedToggle();
        String q12_selectedValue = q12_Selected != null ? q12_Selected.getText() : null;

        RadioButton q13_Selected = (RadioButton) MD1Q13_toggleGroup.getSelectedToggle();
        String q13_selectedValue = q13_Selected != null ? q13_Selected.getText() : null;

        RadioButton q14_Selected = (RadioButton) MD1Q14_toggleGroup.getSelectedToggle();
        String q14_selectedValue = q14_Selected != null ? q14_Selected.getText() : null;

        // SQL query to check if assessmentID exists
        String checkAssessmentExistenceQuery = "SELECT COUNT(*) FROM clientassessment WHERE assessmentID = '" + assessmentID + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Check if the assessmentID exists
            ResultSet rs = stmt.executeQuery(checkAssessmentExistenceQuery);
            rs.next();
            int assessmentCount = rs.getInt(1);

            if (assessmentCount == 0) {
                System.out.println("AssessmentID does not exist. Please check the ID and try again.");
            } else {
                // SQL query for upsert
                String upsertQuery = "INSERT INTO clientMedication (clientID, assessmentID, medicationRequirement, needReminder, needPrompt, medAdmin, medPickup, takingPresc, medsMismanagement, medPep, medInterval, specialMed, smDets, supplement, bloodMonitorAssist, injectAssist) "
                        + "VALUES ('" + clientID + "', '" + assessmentID + "', '"
                        + q1_selectedValue + "', '" + q2_selectedValue + "', '"
                        + q3_selectedValue + "', '" + q4_selectedValue + "', '"
                        + q5_selectedValue + "', '" + q6_selectedValue + "', '"
                        + q7_selectedValue + "', '" + q8_selectedValue + "', '"
                        + medInterval + "', '" + specialMed + "', '" + smDets + "', '"
                        + q12_selectedValue + "', '" + q13_selectedValue + "', '"
                        + q14_selectedValue + "') "
                        + "ON DUPLICATE KEY UPDATE "
                        + "medicationRequirement = VALUES(medicationRequirement), "
                        + "needReminder = VALUES(needReminder), "
                        + "needPrompt = VALUES(needPrompt), "
                        + "medAdmin = VALUES(medAdmin), "
                        + "medPickup = VALUES(medPickup), "
                        + "takingPresc = VALUES(takingPresc), "
                        + "medsMismanagement = VALUES(medsMismanagement), "
                        + "medPep = VALUES(medPep), "
                        + "medInterval = VALUES(medInterval), "
                        + "specialMed = VALUES(specialMed), "
                        + "smDets = VALUES(smDets), "
                        + "supplement = VALUES(supplement), "
                        + "bloodMonitorAssist = VALUES(bloodMonitorAssist), "
                        + "injectAssist = VALUES(injectAssist)";

                // Execute the upsert query
                int rowsAffected = stmt.executeUpdate(upsertQuery);

                String counter = "2";
                String sID = "MP";
                String upsertBudgetStaging = "INSERT INTO budget_staging (clientID, assessmentID, caseID, counter, serviceID) "
                        + "SELECT '" + clientID + "', '" + assessmentID + "', '" + caseN + "', '" + counter + "', serviceID "
                        + "FROM serviceoffered "
                        + "WHERE serviceID = '" + sID + "' "
                        + "ON DUPLICATE KEY UPDATE "
                        + "clientID = VALUES(clientID), "
                        + "assessmentID = VALUES(assessmentID), "
                        + "caseID = VALUES(caseID), "
                        + "counter = VALUES(counter), "
                        + "serviceID = VALUES(serviceID)";

                if (!rdoMedicationRequirement1.isSelected()) {

                    int rowsAffected2 = stmt.executeUpdate(upsertBudgetStaging);
                }

                if (rowsAffected > 0) {
                    System.out.println("Record inserted or updated successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        paneMPMN.setVisible(false);
        panePMN.setVisible(true);
    }

    @FXML
    private void btnBackPMN(ActionEvent event) {
        paneMPMN.setVisible(true);
        panePMN.setVisible(false);

    }

    @FXML
    private void btnNextPMN(ActionEvent event) {

        RadioButton q1_Selected = (RadioButton) PM1Q1_toggleGroup.getSelectedToggle();
        String q1_selectedValue = q1_Selected != null ? q1_Selected.getText() : null;

        RadioButton q2_Selected = (RadioButton) PM1Q2_toggleGroup.getSelectedToggle();
        String q2_selectedValue = q2_Selected != null ? q2_Selected.getText() : null;

        String painMedFreq = txtPainMedFreq.getText();
        String painLevel = txtPainLevel.getText();

        RadioButton q5_Selected = (RadioButton) PM1Q5_toggleGroup.getSelectedToggle();
        String q5_selectedValue = q5_Selected != null ? q5_Selected.getText() : null;

        RadioButton q5S_Selected = (RadioButton) PM1SQ5_toggleGroup.getSelectedToggle();
        String q5S_selectedValue = q5S_Selected != null ? q5S_Selected.getText() : "";

        // SQL query to check if assessmentID exists
        String checkAssessmentExistenceQuery = "SELECT COUNT(*) FROM clientassessment WHERE assessmentID = '" + assessmentID + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Check if the assessmentID exists
            ResultSet rs = stmt.executeQuery(checkAssessmentExistenceQuery);
            rs.next();
            int assessmentCount = rs.getInt(1);

            if (assessmentCount == 0) {
                System.out.println("AssessmentID does not exist. Please check the ID and try again.");
            } else {
                // SQL query for upsert
                String upsertQuery = "INSERT INTO clientPain (clientID, assessmentID, painExp, painMed, painMedFreq, painLevel, respiteRequire, respiteDelivery) "
                        + "VALUES ('" + clientID + "', '" + assessmentID + "', '"
                        + q1_selectedValue + "', '" + q2_selectedValue + "', '"
                        + painMedFreq + "', '" + painLevel + "', '"
                        + q5_selectedValue + "', '" + q5S_selectedValue + "') "
                        + "ON DUPLICATE KEY UPDATE "
                        + "painExp = VALUES(painExp), "
                        + "painMed = VALUES(painMed), "
                        + "painMedFreq = VALUES(painMedFreq), "
                        + "painLevel = VALUES(painLevel), "
                        + "respiteRequire = VALUES(respiteRequire), "
                        + "respiteDelivery = VALUES(respiteDelivery)";

                // Execute the upsert query
                int rowsAffected = stmt.executeUpdate(upsertQuery);
                if (rowsAffected > 0) {
                    System.out.println("Record inserted or updated successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        panePMN.setVisible(false);
        paneClientHousing.setVisible(true);
    }

    @FXML
    private void btnBackClientHousing(ActionEvent event) {
        panePMN.setVisible(true);
        paneClientHousing.setVisible(false);

    }

    @FXML
    private void btnNextClientHousing(ActionEvent event) {

        RadioButton q1_Selected = (RadioButton) H1Q1_toggleGroup.getSelectedToggle();
        String housingOwnership = q1_Selected != null ? q1_Selected.getText() : null;

        RadioButton q2_Selected = (RadioButton) H1Q2_toggleGroup.getSelectedToggle();
        String livingSetup = q2_Selected != null ? q2_Selected.getText() : null;

        RadioButton q3_Selected = (RadioButton) H1Q3_toggleGroup.getSelectedToggle();
        String personalAlarm = q3_Selected != null ? q3_Selected.getText() : null;

        String alarmCompany = txtAlarmCompany.getText();
        String keyLockCode = txtKeyLockCode.getText();

        RadioButton q6_Selected = (RadioButton) H1Q6_toggleGroup.getSelectedToggle();
        String keyLockBox = q6_Selected != null ? q6_Selected.getText() : null;

        // SQL query to check if assessmentID exists
        String checkAssessmentExistenceQuery = "SELECT COUNT(*) FROM clientassessment WHERE assessmentID = '" + assessmentID + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Check if the assessmentID exists
            ResultSet rs = stmt.executeQuery(checkAssessmentExistenceQuery);
            rs.next();
            int assessmentCount = rs.getInt(1);

            if (assessmentCount == 0) {
                System.out.println("AssessmentID does not exist. Please check the ID and try again.");
            } else {
                // SQL query for upsert
                String upsertQuery = "INSERT INTO clientHousing (clientID, assessmentID, housingOwnership, livingSetup, personalAlarm, alarmCompany, keyLockBox, keyLockCode) "
                        + "VALUES ('" + clientID + "', '" + assessmentID + "', '"
                        + housingOwnership + "', '" + livingSetup + "', '" + personalAlarm + "', '"
                        + alarmCompany + "', '" + keyLockBox + "', '" + keyLockCode + "') "
                        + "ON DUPLICATE KEY UPDATE "
                        + "housingOwnership = VALUES(housingOwnership), "
                        + "livingSetup = VALUES(livingSetup), "
                        + "personalAlarm = VALUES(personalAlarm), "
                        + "alarmCompany = VALUES(alarmCompany), "
                        + "keyLockBox = VALUES(keyLockBox), "
                        + "keyLockCode = VALUES(keyLockCode)";

                // Execute the upsert query
                int rowsAffected = stmt.executeUpdate(upsertQuery);
                if (rowsAffected > 0) {
                    System.out.println("Record inserted or updated successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(true);
    }

    @FXML
    private void btnBackMHEN(ActionEvent event) {
        paneClientHousing.setVisible(true);
        paneMHEN.setVisible(false);

    }

    @FXML
    private void btnNextMHEN(ActionEvent event) {
        // Home Maintain Support
        RadioButton q1_Selected = (RadioButton) HE1Q1_toggleGroup.getSelectedToggle();
        String homeMaintainSupport = q1_Selected != null ? q1_Selected.getText() : null;

        // Various Home Maintenance Activities
        String cleaning = ckCleaning.isSelected() ? "Yes" : "No";
        String decluttering = ckDecluttering.isSelected() ? "Yes" : "No";
        String dusting = ckDusting.isSelected() ? "Yes" : "No";
        String vacuum = ckVacuum.isSelected() ? "Yes" : "No";
        String mop = ckMop.isSelected() ? "Yes" : "No";
        String sweeping = ckSweeping.isSelected() ? "Yes" : "No";
        String laundry = ckLaundry.isSelected() ? "Yes" : "No";
        String hangClothes = ckHangClothes.isSelected() ? "Yes" : "No";
        String lightGardening = ckLightGardening.isSelected() ? "Yes" : "No";
        String mowGarden = ckMowGarden.isSelected() ? "Yes" : "No";
        String windowCleaning = ckWindowCleaning.isSelected() ? "Yes" : "No";
        String gutterCleaning = ckGutterCleaning.isSelected() ? "Yes" : "No";
        String bedroomCleaning = ckBedroomCleaning.isSelected() ? "Yes" : "No";

        // Social Activity
        RadioButton q3_Selected = (RadioButton) HE1Q3_toggleGroup.getSelectedToggle();
        String socialActivity = q3_Selected != null ? q3_Selected.getText() : null;

        // Transport Assistance
        RadioButton q4_Selected = (RadioButton) HE1Q4_toggleGroup.getSelectedToggle();
        String transportAssist = q4_Selected != null ? q4_Selected.getText() : null;

        // Ability to Drive
        RadioButton q5_Selected = (RadioButton) HE1Q5_toggleGroup.getSelectedToggle();
        String canDrive = q5_Selected != null ? q5_Selected.getText() : null;

        // Now move the taxi, shopping, and appointment assist strings after canDrive
        String taxiVoucher = ckTaxiVoucher.isSelected() ? "Yes" : "No";
        String shoppingAssist = ckShoppingAssist.isSelected() ? "Yes" : "No";
        String docAptAssist = ckDocAptAssist.isSelected() ? "Yes" : "No";

        // Check if assessmentID exists
        String checkAssessmentExistenceQuery = "SELECT COUNT(*) FROM clientassessment WHERE assessmentID = '" + assessmentID + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Check if the assessmentID exists
            ResultSet rs = stmt.executeQuery(checkAssessmentExistenceQuery);
            rs.next();
            int assessmentCount = rs.getInt(1);

            if (assessmentCount == 0) {
                System.out.println("AssessmentID does not exist. Please check the ID and try again.");
            } else {
                // SQL query for upsert for clientHome
                String upsertHomeQuery = "INSERT INTO clientHome (clientID, assessmentID, homeMaintainSupport, cleaning, decluttering, dusting, vacuum, mop, sweeping, laundry, hangClothes, lightGardening, mowGarden, windowCleaning, gutterCleaning, bedroomCleaning) "
                        + "VALUES ('" + clientID + "', '" + assessmentID + "', '"
                        + homeMaintainSupport + "', '" + cleaning + "', '" + decluttering + "', '"
                        + dusting + "', '" + vacuum + "', '" + mop + "', '" + sweeping + "', '"
                        + laundry + "', '" + hangClothes + "', '" + lightGardening + "', '"
                        + mowGarden + "', '" + windowCleaning + "', '" + gutterCleaning + "', '"
                        + bedroomCleaning + "') "
                        + "ON DUPLICATE KEY UPDATE "
                        + "homeMaintainSupport = VALUES(homeMaintainSupport), "
                        + "cleaning = VALUES(cleaning), "
                        + "decluttering = VALUES(decluttering), "
                        + "dusting = VALUES(dusting), "
                        + "vacuum = VALUES(vacuum), "
                        + "mop = VALUES(mop), "
                        + "sweeping = VALUES(sweeping), "
                        + "laundry = VALUES(laundry), "
                        + "hangClothes = VALUES(hangClothes), "
                        + "lightGardening = VALUES(lightGardening), "
                        + "mowGarden = VALUES(mowGarden), "
                        + "windowCleaning = VALUES(windowCleaning), "
                        + "gutterCleaning = VALUES(gutterCleaning), "
                        + "bedroomCleaning = VALUES(bedroomCleaning)";

                // Execute the upsert query for clientHome
                int rowsAffectedHome = stmt.executeUpdate(upsertHomeQuery);

                String counter = "1";
                String sID = "DA";
                String upsertBudgetStaging = "INSERT INTO budget_staging (clientID, assessmentID, caseID, counter, serviceID) "
                        + "SELECT '" + clientID + "', '" + assessmentID + "', '" + caseN + "', '" + counter + "', serviceID "
                        + "FROM serviceoffered "
                        + "WHERE serviceID = '" + sID + "' "
                        + "ON DUPLICATE KEY UPDATE "
                        + "clientID = VALUES(clientID), "
                        + "assessmentID = VALUES(assessmentID), "
                        + "caseID = VALUES(caseID), "
                        + "counter = VALUES(counter), "
                        + "serviceID = VALUES(serviceID)";

                if (!rdoHomeMaintainSupportN.isSelected()) {

                    int rowsAffected2 = stmt.executeUpdate(upsertBudgetStaging);
                }

                if (rowsAffectedHome > 0) {
                    System.out.println("clientHome record inserted or updated successfully.");
                }

                // SQL query for upsert for clientTransport
                String upsertTransportQuery = "INSERT INTO clientTransport (clientID, assessmentID, socialActivity, transportAssist, canDrive, TaxiVoucher, ShoppingAssist, DocAptAssist) "
                        + "VALUES ('" + clientID + "', '" + assessmentID + "', '"
                        + socialActivity + "', '" + transportAssist + "', '" + canDrive + "', '"
                        + taxiVoucher + "', '" + shoppingAssist + "', '" + docAptAssist + "') "
                        + "ON DUPLICATE KEY UPDATE "
                        + "socialActivity = VALUES(socialActivity), "
                        + "transportAssist = VALUES(transportAssist), "
                        + "canDrive = VALUES(canDrive), "
                        + "TaxiVoucher = VALUES(TaxiVoucher), "
                        + "ShoppingAssist = VALUES(ShoppingAssist), "
                        + "DocAptAssist = VALUES(DocAptAssist)";

                // Execute the upsert query for clientTransport
                int rowsAffectedTransport = stmt.executeUpdate(upsertTransportQuery);

                String sSID = "TA";
                String upsertBudgetStaging2 = "INSERT INTO budget_staging (clientID, assessmentID, caseID, counter, serviceID) "
                        + "SELECT '" + clientID + "', '" + assessmentID + "', '" + caseN + "', '" + counter + "', serviceID "
                        + "FROM serviceoffered "
                        + "WHERE serviceID = '" + sSID + "' "
                        + "ON DUPLICATE KEY UPDATE "
                        + "clientID = VALUES(clientID), "
                        + "assessmentID = VALUES(assessmentID), "
                        + "caseID = VALUES(caseID), "
                        + "counter = VALUES(counter), "
                        + "serviceID = VALUES(serviceID)";

                if (!rdoSocialActivityN.isSelected()) {

                    int rowsAffected2 = stmt.executeUpdate(upsertBudgetStaging2);
                }

                if (rowsAffectedTransport > 0) {
                    System.out.println("clientTransport record inserted or updated successfully.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        paneMHEN.setVisible(false);
        paneNDP.setVisible(true);
    }

    @FXML
    private void btnBackNDP(ActionEvent event) {
        paneMHEN.setVisible(true);
        paneNDP.setVisible(false);

    }

    @FXML
    private void btnNextNDP(ActionEvent event) {

        String eolPractice = txtEolPractice.getText();
        String palliativeCare = txtPalliativeCare.getText();
        String dnr = txtDnr.getText();
        String dni = txtDni.getText();
        String informFam = txtInformFam.getText();
        String informGP = txtInformGP.getText();

        // SQL query to check if assessmentID exists
        String checkAssessmentExistenceQuery = "SELECT COUNT(*) FROM clientassessment WHERE assessmentID = '" + assessmentID + "'";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {

            // Check if the assessmentID exists
            ResultSet rs = stmt.executeQuery(checkAssessmentExistenceQuery);
            rs.next();
            int assessmentCount = rs.getInt(1);

            if (assessmentCount == 0) {
                System.out.println("AssessmentID does not exist. Please check the ID and try again.");
            } else {
                // SQL query for upsert
                String upsertQuery = "INSERT INTO nearDeathPreferences (clientID, eolPractice, palliativeCare, dnr, dni, informFam, informGP) "
                        + "VALUES ('" + clientID + "', '"
                        + eolPractice + "', '" + palliativeCare + "', '" + dnr + "', '"
                        + dni + "', '" + informFam + "', '" + informGP + "') "
                        + "ON DUPLICATE KEY UPDATE "
                        + "eolPractice = VALUES(eolPractice), "
                        + "palliativeCare = VALUES(palliativeCare), "
                        + "dnr = VALUES(dnr), "
                        + "dni = VALUES(dni), "
                        + "informFam = VALUES(informFam), "
                        + "informGP = VALUES(informGP)";

                // Execute the upsert query
                int rowsAffected = stmt.executeUpdate(upsertQuery);
                if (rowsAffected > 0) {
                    System.out.println("Record inserted or updated successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        paneNDP.setVisible(false);
        paneClientContact.setVisible(true);
    }

    @FXML
    private void btnBackClientContact(ActionEvent event) {
        paneNDP.setVisible(true);
        paneClientContact.setVisible(false);

    }

    @FXML
    private void btnNextClientContact(ActionEvent event) {

        // Collecting form input values
        String primaryContact = txtPrimaryContact.getText();
        String primaryRelationship = txtPrimaryRelationship.getText();
        String primaryEmail = txtPrimaryEmail.getText();
        String primaryPhone = txtPrimaryPhone.getText();
        String secondaryContact = txtSecondaryContact.getText();
        String secondaryRelationship = txtSecondaryRelationship.getText();
        String secondaryEmail = txtSecondaryEmail.getText();
        String secondaryPhone = txtSecondaryPhone.getText();
        String medPractitioner = txtMedPractitioner.getText();
        String practicePhone = txtPracticePhone.getText();
        String practiceAddress = txtPracticeAddress.getText();
        String medPharma = txtMedPharma.getText();
        String pharmaPhone = txtPharmaPhone.getText();
        String pharmaAddress = txtPharmaAddress.getText();
        String pharmaEmail = txtPharmaEmail.getText();
        String practiceEmail = txtPracticeEmail.getText();

        String clientContact = "INSERT INTO clientContact (clientID, primaryContact, primaryRelationship, primaryPhone, primaryEmail, secondaryContact, secondaryRelationship, secondaryPhone, secondaryEmail, medPractitioner, practicePhone, practiceAddress, practiceEmail, medPharma, pharmaPhone, pharmaAddress, pharmaEmail) "
                + "VALUES ('200001', '" + primaryContact + "', '" + primaryRelationship + "', '" + primaryPhone + "', '"
                + primaryEmail + "', '" + secondaryContact + "', '" + secondaryRelationship + "', '" + secondaryPhone + "', '"
                + secondaryEmail + "', '" + medPractitioner + "', '" + practicePhone + "', '" + practiceAddress + "', '"
                + practiceEmail + "', '" + medPharma + "', '" + pharmaPhone + "', '" + pharmaAddress + "', '" + pharmaEmail + "')";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(clientContact);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        paneClientContact.setVisible(false);
        paneClientContact.setVisible(true);
    }

    @FXML
    private void lblClientInfo(MouseEvent event) {
        clientDetsPane.setVisible(true);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblPersonalHygiene(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(true);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblOralHygiene(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(true);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblToileting(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(true);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblMobility(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(true);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblOtherMobility(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(true);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblNutrition(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(true);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblSkinIntegrity(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(true);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblMentalHealth(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(true);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblMedication(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(true);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblPainManagement(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(true);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lvlHousing(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(true);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblHouseEnvironment(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(true);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblEndOfLife(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(true);
        paneClientContact.setVisible(false);
    }

    @FXML
    private void lblContactInfo(MouseEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(false);
        panePMN.setVisible(false);
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(false);
        paneNDP.setVisible(false);
        paneClientContact.setVisible(true);
    }



    

        @FXML
private void completeAssessment(ActionEvent event) {
    String outcome = remarks.getText(); // Get the remarks text
    String aID = caseNo.getText();      // Get the case number

    // SQL query with placeholders for parameters
    String closeCases = "UPDATE clientcases SET assessmentStatus = ?, closingReason = ? WHERE caseID = ?";

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement(closeCases)) {

        // Set the parameters for the PreparedStatement
        preparedStatement.setString(1, "Complete");  // First parameter (assessmentStatus)
        preparedStatement.setString(2, outcome);   // Second parameter (closingReason)
        preparedStatement.setString(3, aID);       // Third parameter (caseID)

        // Execute the update query
        int rowsUpdated = preparedStatement.executeUpdate();

        // Check if the update was successful
        if (rowsUpdated > 0) {
            // Display success alert
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Assessment Complete.");
            successAlert.showAndWait();

            // Close the current stage/window after success
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

        
        
        
        
        
        
        
        
        
    
    
    

}
