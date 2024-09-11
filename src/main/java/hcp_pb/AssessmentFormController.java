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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
/**
 * FXML Controller class
 *
 * @author mark
 */
public class AssessmentFormController implements Initializable {

@FXML private Button startAssesmentBtn;
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
    private ToggleGroup P1Q6_toggleGroup;
    
    //Question 7/
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
    
    //Question 14/
    @FXML
    private RadioButton rdoDressingCarerReqY;
    @FXML
    private RadioButton rdoDressingCarerReqN;
    private ToggleGroup P1Q14_toggleGroup;
    
    //Question 15/
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
    private TextArea txtIssueDets;
    
    //Question 3/
    @FXML
    private RadioButton rdoMobilityIssueY;
    @FXML
    private RadioButton rdoMobilityIssueN;
    private ToggleGroup M1Q3_toggleGroup;
    
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
    
    //Question 13/
    @FXML
    private TextArea txtFallDets;
    
    //Back and Next Button
    @FXML
    private Button btnBackMobilityNeeds;
    @FXML
    private Button btnNextMobilityNeeds;
    
    /*** Other Mobility*/
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
    
    
    /*** Skin Integrity*/  
    @FXML
    private Pane paneSkinIntegrityNeeds;
    @FXML
    
    //Question 1/
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
    
    /*** Mental Health*/  
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
    private TextArea txtOtherMentalText;
    
    //Question 6/
    @FXML
    private RadioButton rdoSchizophreniaY;
    @FXML
    private RadioButton rdoSchizophreniaN;
    private ToggleGroup MH1Q6_toggleGroup;
    
    //Question 7/
    @FXML
    private RadioButton rdoOtherMentalY;
    @FXML
    private RadioButton rdoOtherMentalN;
    private ToggleGroup MH1Q7_toggleGroup;
    
    //Question 8/
    @FXML
    private RadioButton rdoAnxietyY;
    @FXML
    private RadioButton rdoAnxietyN;
    private ToggleGroup MH1Q8_toggleGroup;
    
    
    //Back and Next Bu8tton/
    @FXML
    private Button btnBackCMHBN;
    @FXML
    private Button btnNextCMHBN;
    
    /*** Medication*/
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
    
    // Pain Management*/  
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
    
    /*** Housing*/  
    @FXML
    private Pane paneClientHousing;
    
    //Question 1/
    @FXML
    private RadioButton rdoHousingOwnershipY;
    @FXML
    private RadioButton rdoHousingOwnershipN;
    private ToggleGroup H1Q1_toggleGroup;
    
     //Secondary Question 1/
    @FXML
    private RadioButton rdoHousingSetupR;
    @FXML
    private RadioButton rdoHousingSetupG;
    @FXML
    private RadioButton rdoHousingSetupO;
    private ToggleGroup H1SQ1_toggleGroup;
    
    //Question 2/
    @FXML
    private RadioButton rdoLivingSetupY;
    @FXML
    private RadioButton rdoLivingSetupN;
    private ToggleGroup H1Q2_toggleGroup;
    
    //Secondary Question 2/
    @FXML
    private RadioButton rdoLivingSetupN1;
    @FXML
    private RadioButton rdoLivingSetupN2;
    private ToggleGroup H1SQ2_toggleGroup;
    
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
    
    @FXML
    private RadioButton rdoLivingSetup;
   
    //Back and Next Button/
    @FXML
    private Button btnBackClientHousing;
    @FXML
    private Button btnNextClientHousing;
    
    /*** Home Environment*/  
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
    
    //Question 3/
    @FXML
    private CheckBox ckKitchenCleaning;
    @FXML
    private CheckBox ckLaundryCleaning;
    @FXML
    private CheckBox ckPatioCleaning;
    @FXML
    private CheckBox ckBedroomCleaning;
    @FXML
    private CheckBox ckToiletCleaning;   
    @FXML
    private CheckBox ckLoungeCleaning;
    @FXML
    private CheckBox ckBathroomCleaning;
    
    //Question 4/
    @FXML
    private TextArea txtCleaningFreq;
    
    //Question 5/
    @FXML
    private TextArea txtCleaningPrefDay;
    
    //Question 6/
    @FXML
    private RadioButton rdoCleaningCarerGenderM;
    @FXML
    private RadioButton rdoCleaningCarerGenderF;
    @FXML
    private RadioButton rdoCleaningCarerGenderN;
    private ToggleGroup HE1Q6_toggleGroup;
    
    //Question 7/
    @FXML
    private RadioButton rdoSocialActivityY;
    @FXML
    private RadioButton rdoSocialActivityN;
    private ToggleGroup HE1Q7_toggleGroup; 
    
    //Question 8/ 
    @FXML
    private RadioButton rdoTransportAssistY;
    @FXML
    private RadioButton rdoTransportAssistN;
    private ToggleGroup HE1Q8_toggleGroup; 
    
    //Question 9/ 
    @FXML
    private RadioButton rdoCanDriveY;
    @FXML
    private RadioButton rdoCanDriveN;
    private ToggleGroup HE1Q9_toggleGroup; 
    
    //Secondary Qustion 9/
    @FXML
    private RadioButton rdoTaxiVoucher;
    @FXML
    private RadioButton rdoShoppingAssist;
    @FXML
    private RadioButton rdoDocAptAssist;
    @FXML
    private RadioButton rdoOtherAssist;
    private ToggleGroup HE1SQ9_toggleGroup;
  
    //Question 10/
    @FXML
    private TextArea txtOtherAssist;
    
    //Back and Next Buttin
    @FXML
    private Button btnBackMHEN;
    @FXML
    private Button btnNextMHEN;
    
    /*** End of Life*/  
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
    
    /*** Contact Info */  
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
    
    
    @FXML private Pane clientDetsPane;
    
    
    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
    }
    private void PHygienge_BtnGrp(){

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
        
        //Question 6
        P1Q6_toggleGroup = new ToggleGroup();
        rdoShowerCarerReqY.setToggleGroup(P1Q6_toggleGroup);
        rdoShowerCarerReqN.setToggleGroup(P1Q6_toggleGroup);
        rdoShowerCarerReqY.setSelected(true);
        
        //Question 7
        P1Q7_toggleGroup = new ToggleGroup();
        rdoShowerCarerGenderM.setToggleGroup(P1Q7_toggleGroup);
        rdoShowerCarerGenderF.setToggleGroup(P1Q7_toggleGroup);
        rdoShowerCarerGenderM.setSelected(true);
        
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
        
        //Question 14
        P1Q14_toggleGroup = new ToggleGroup();
        rdoDressingCarerReqY.setToggleGroup(P1Q14_toggleGroup);
        rdoDressingCarerReqN.setToggleGroup(P1Q14_toggleGroup);
        rdoDressingCarerReqY.setSelected(true);
        
         //Question 15
        P1Q15_toggleGroup = new ToggleGroup();
        rdoDressingCarerGenderM.setToggleGroup(P1Q15_toggleGroup);
        rdoDressingCarerGenderF.setToggleGroup(P1Q15_toggleGroup);
        rdoDressingCarerGenderM.setSelected(true);
        
         //Question 16
        P1Q16_toggleGroup = new ToggleGroup();
        rdoDressingPrefDayA.setToggleGroup(P1Q16_toggleGroup);
        rdoDressingPrefDayP.setToggleGroup(P1Q16_toggleGroup);
        rdoDressingPrefDayA.setSelected(true);
    }
    private void OHygienge_BtnGrp(){
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
    private void Toileting_BtnGrp(){
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
        rdoPDryingY .setSelected(true);
        
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
        rdoSetupPadsY .setSelected(true);
        
        //Question 9
        T1Q9_toggleGroup = new ToggleGroup();
        rdoPWipeDryY.setToggleGroup(T1Q9_toggleGroup);
        rdoPWipeDryN.setToggleGroup(T1Q9_toggleGroup);
        rdoPWipeDryY .setSelected(true);
        
    }   
    private void Mobility_BtnGrp(){
        //Question 1
        M1Q1_toggleGroup = new ToggleGroup();
        rdoMobilityRequirement1.setToggleGroup(M1Q1_toggleGroup);
        rdoMobilityRequirement2.setToggleGroup(M1Q1_toggleGroup);
        rdoMobilityRequirement3.setToggleGroup(M1Q1_toggleGroup);
        rdoMobilityRequirement1.setSelected(true);
       
         //Question 3
        M1Q3_toggleGroup = new ToggleGroup();
        rdoMobilityIssueY.setToggleGroup(M1Q3_toggleGroup);
        rdoMobilityIssueN.setToggleGroup(M1Q3_toggleGroup);
        rdoMobilityIssueY.setSelected(true);
        
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
        
        }   
    private void OMobility_BtnGrp(){
        //Question 1
        OM1Q1_toggleGroup = new ToggleGroup();
        rdoExerciseY.setToggleGroup(OM1Q1_toggleGroup);
        rdoExerciseN.setToggleGroup(OM1Q1_toggleGroup);
        rdoExerciseY.setSelected(true);
        
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
    private void Nutrition_BtnGrp(){
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
    private void Skin_BtnGrp(){
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
        
        //Question 9/
        S1Q9_toggleGroup = new ToggleGroup();
        rdoAttendPodiatristY.setToggleGroup(S1Q9_toggleGroup);
        rdoAttendPodiatristN.setToggleGroup(S1Q9_toggleGroup);
        rdoAttendPodiatristY.setSelected(true);
        
         }   
    private void MHealth_BtnGrp(){
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
        
        //Question 6/
        MH1Q6_toggleGroup = new ToggleGroup();
        rdoSchizophreniaY.setToggleGroup(MH1Q6_toggleGroup);
        rdoSchizophreniaN.setToggleGroup(MH1Q6_toggleGroup);
        rdoSchizophreniaY.setSelected(true);
        
        //Question 7/
        MH1Q7_toggleGroup = new ToggleGroup();
        rdoOtherMentalY.setToggleGroup(MH1Q7_toggleGroup);
        rdoOtherMentalN.setToggleGroup(MH1Q7_toggleGroup);
        rdoOtherMentalY.setSelected(true);
        
        //Question 8/
        MH1Q8_toggleGroup = new ToggleGroup();
        rdoAnxietyY.setToggleGroup(MH1Q8_toggleGroup);
        rdoAnxietyN.setToggleGroup(MH1Q8_toggleGroup);
        rdoAnxietyY.setSelected(true);
        
         }   
    private void Medication_BtnGrp(){
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
    private void PMgt_BtnGrp(){
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
        
        //Question 5/
        PM1Q5_toggleGroup = new ToggleGroup();
        txtRespiteRequireY.setToggleGroup(PM1Q5_toggleGroup);
        rdoRespiteRequireN.setToggleGroup(PM1Q5_toggleGroup);
        txtRespiteRequireY.setSelected(true);
        
        //Question 6/
        PM1SQ5_toggleGroup = new ToggleGroup();
        rdoRespiteDeliveryI.setToggleGroup(PM1SQ5_toggleGroup);
        rdoRespiteDeliveryR.setToggleGroup(PM1SQ5_toggleGroup);
        
        }   
    private void House_BtnGrp(){
        //Question 1/
        H1Q1_toggleGroup = new ToggleGroup();
        rdoHousingOwnershipY.setToggleGroup(H1Q1_toggleGroup);
        rdoHousingOwnershipN.setToggleGroup(H1Q1_toggleGroup);
        rdoHousingOwnershipY.setSelected(true);
        
         //Secondary Question 1/
        H1SQ1_toggleGroup = new ToggleGroup();
        rdoHousingSetupR.setToggleGroup(H1SQ1_toggleGroup);
        rdoHousingSetupG.setToggleGroup(H1SQ1_toggleGroup);
        rdoHousingSetupO.setToggleGroup(H1SQ1_toggleGroup);
        
        //Question 2/
        H1Q2_toggleGroup = new ToggleGroup();
        rdoLivingSetupY.setToggleGroup(H1Q2_toggleGroup);
        rdoLivingSetupN.setToggleGroup(H1Q2_toggleGroup);
        rdoLivingSetupY.setSelected(true);

        //Secondary Question 2/
        H1SQ2_toggleGroup = new ToggleGroup();
        rdoLivingSetupN1.setToggleGroup(H1SQ2_toggleGroup);
        rdoLivingSetupN2.setToggleGroup(H1SQ2_toggleGroup);
        
        //Question 3/
        H1Q3_toggleGroup = new ToggleGroup();
        rdoPersonalAlarmY.setToggleGroup(H1Q3_toggleGroup);
        rdoPersonalAlarmN.setToggleGroup(H1Q3_toggleGroup);
        rdoPersonalAlarmY.setSelected(true);
       
        //Question 6/
        H1Q6_toggleGroup = new ToggleGroup();
        rdoKeyLockBoxY.setToggleGroup(H1Q6_toggleGroup);
        rdoKeyLockBoxN.setToggleGroup(H1Q6_toggleGroup);
        rdoKeyLockBoxY.setSelected(true);
        
            }   
    private void HomeE_BtnGrp(){
        //Question 1/
        HE1Q1_toggleGroup = new ToggleGroup();
        rdoHomeMaintainSupportY.setToggleGroup(HE1Q1_toggleGroup);
        rdoHomeMaintainSupportN.setToggleGroup(HE1Q1_toggleGroup);
        rdoHomeMaintainSupportY.setSelected(true);
        
       //Question 6/ 
        HE1Q6_toggleGroup = new ToggleGroup();
        rdoCleaningCarerGenderM.setToggleGroup(HE1Q6_toggleGroup);
        rdoCleaningCarerGenderF.setToggleGroup(HE1Q6_toggleGroup);
        rdoCleaningCarerGenderN.setToggleGroup(HE1Q6_toggleGroup);
        rdoCleaningCarerGenderM.setSelected(true); 
        
       //Question 7/ 
        HE1Q7_toggleGroup = new ToggleGroup();
        rdoSocialActivityY.setToggleGroup(HE1Q7_toggleGroup);
        rdoSocialActivityN.setToggleGroup(HE1Q7_toggleGroup);
        rdoSocialActivityY.setSelected(true);  
        
        
        //Question 8/ 
        HE1Q8_toggleGroup = new ToggleGroup();
        rdoTransportAssistY.setToggleGroup(HE1Q8_toggleGroup);
        rdoTransportAssistN.setToggleGroup(HE1Q8_toggleGroup);
        rdoTransportAssistY.setSelected(true);  
        
        
        //Question 9/ 
        HE1Q9_toggleGroup = new ToggleGroup();
        rdoCanDriveY.setToggleGroup(HE1Q9_toggleGroup);
        rdoCanDriveN.setToggleGroup(HE1Q9_toggleGroup);
        rdoCanDriveY.setSelected(true); 
        
        //Secondary Question 9/
        HE1SQ9_toggleGroup = new ToggleGroup();
        rdoTaxiVoucher.setToggleGroup(HE1SQ9_toggleGroup);
        rdoShoppingAssist.setToggleGroup(HE1SQ9_toggleGroup);
        rdoDocAptAssist.setToggleGroup(HE1SQ9_toggleGroup);
        rdoOtherAssist.setToggleGroup(HE1SQ9_toggleGroup);                  
}
 
    @FXML
    private void alliedHealthRefY(ActionEvent event) {
    }

    @FXML
    private void alliedHealthRefN(ActionEvent event) {
    }
    
    @FXML
    private void startAssesmentBtn  (ActionEvent event) {
        clientDetsPane.setVisible(false);
        paneClientHygiene.setVisible(true);
    }
    @FXML
    private void btnBackClientHygiene   (ActionEvent event) {
        clientDetsPane.setVisible(true);
        paneClientHygiene.setVisible(false);
    }
    @FXML
    private void btnNextClientHygiene   (ActionEvent event) {
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(true);
    }
    @FXML
    private void btnBackOralHygiene   (ActionEvent event) {
        paneClientHygiene.setVisible(true);
        paneOralHygiene.setVisible(false);
 
    }
    @FXML
    private void btnNextOralHygiene   (ActionEvent event) {
        paneOralHygiene.setVisible(false);
        paneToileting.setVisible(true);
    }
    @FXML
    private void btnContinenceNeeds   (ActionEvent event) {
        paneOralHygiene.setVisible(true);
        paneToileting.setVisible(false);

    }
    @FXML
    private void btnNextContinenceNeeds   (ActionEvent event) {
        paneToileting.setVisible(false);
        paneMobilityNeeds.setVisible(true);
    }
    @FXML
    private void btnBackMobilityNeeds   (ActionEvent event) {
        paneToileting.setVisible(true);
        paneMobilityNeeds.setVisible(false);
        
    }
    @FXML
    private void btnNextMobilityNeeds   (ActionEvent event) {
        paneMobilityNeeds.setVisible(false);
        paneMobilityNeeds1.setVisible(true);
    }
     @FXML
    private void btnBackMobilityNeeds1   (ActionEvent event) {
        paneMobilityNeeds.setVisible(true);
        paneMobilityNeeds1.setVisible(false);
        
    }
    @FXML
    private void btnNextMobilityNeeds1   (ActionEvent event) {
        paneMobilityNeeds1.setVisible(false);
        paneNutritionalNeeds.setVisible(true);
    }
    
   @FXML
    private void btnBackNutritionalNeeds   (ActionEvent event) {
        paneMobilityNeeds1.setVisible(true);
        paneNutritionalNeeds.setVisible(false);
        
    }
    @FXML
    private void btnNextNutritionalNeeds   (ActionEvent event) {
        paneNutritionalNeeds.setVisible(false);
        paneSkinIntegrityNeeds.setVisible(true);
    }
    
    @FXML
    private void btnBackSkinIntegrityNeeds   (ActionEvent event) {
        paneNutritionalNeeds.setVisible(true);
        paneSkinIntegrityNeeds.setVisible(false);
        
    }
    @FXML
    private void btnNextSkinIntegrityNeeds   (ActionEvent event) {
        paneSkinIntegrityNeeds.setVisible(false);
        paneCMHBN.setVisible(true);
    }
    
    @FXML
    private void btnBackCMHBN   (ActionEvent event) {
        paneSkinIntegrityNeeds.setVisible(true);
        paneCMHBN.setVisible(false);
        
    }
    @FXML
    private void btnNextCMHBN   (ActionEvent event) {
        paneCMHBN.setVisible(false);
        paneMPMN.setVisible(true);
    }
    @FXML
    private void btnBackMPMN   (ActionEvent event) {
        paneCMHBN.setVisible(true);
        paneMPMN.setVisible(false);
        
    }
    @FXML
    private void btnNextMPMN   (ActionEvent event) {
        paneMPMN.setVisible(false);
        panePMN.setVisible(true);
    }
    
    @FXML
    private void btnBackPMN   (ActionEvent event) {
        paneMPMN.setVisible(true);
        panePMN.setVisible(false);
        
    }
    @FXML
    private void btnNextPMN   (ActionEvent event) {
        panePMN.setVisible(false);
        paneClientHousing.setVisible(true);
    }
    
     @FXML
    private void btnBackClientHousing   (ActionEvent event) {
        panePMN.setVisible(true);
        paneClientHousing.setVisible(false);
        
    }
    @FXML
    private void btnNextClientHousing   (ActionEvent event) {
        paneClientHousing.setVisible(false);
        paneMHEN.setVisible(true);
    }
    
    @FXML
    private void btnBackMHEN   (ActionEvent event) {
        paneClientHousing.setVisible(true);
        paneMHEN.setVisible(false);
        
    }
    @FXML
    private void btnNextMHEN   (ActionEvent event) {
        paneMHEN.setVisible(false);
        paneNDP.setVisible(true);
    }
    
    @FXML
    private void btnBackNDP   (ActionEvent event) {
        paneMHEN.setVisible(true);
        paneNDP.setVisible(false);
        
    }
    @FXML
    private void btnNextNDP   (ActionEvent event) {
        paneNDP.setVisible(false);
        paneClientContact.setVisible(true);
    }
    
    @FXML
    private void btnBackClientContact   (ActionEvent event) {
        paneNDP.setVisible(true);
        paneClientContact.setVisible(false);
        
    }
    @FXML
    private void btnNextClientContact   (ActionEvent event) {
        paneClientContact.setVisible(false);
        paneClientContact.setVisible(true);
    }
    
    @FXML
    private void lblClientInfo   (MouseEvent event) {
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
    private void lblPersonalHygiene   (MouseEvent event) {
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
    private void lblOralHygiene   (MouseEvent event) {
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
    private void lblToileting   (MouseEvent event) {
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
    private void lblMobility   (MouseEvent event) {
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
    private void lblOtherMobility   (MouseEvent event) {
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
    private void lblNutrition   (MouseEvent event) {
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
    private void lblSkinIntegrity   (MouseEvent event) {
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
    private void lblMentalHealth   (MouseEvent event) {
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
    private void lblMedication   (MouseEvent event) {
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
    private void lblPainManagement   (MouseEvent event) {
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
    private void lvlHousing   (MouseEvent event) {
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
    private void lblHouseEnvironment   (MouseEvent event) {
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
    private void lblEndOfLife   (MouseEvent event) {
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
    private void lblContactInfo   (MouseEvent event) {
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
    
}



