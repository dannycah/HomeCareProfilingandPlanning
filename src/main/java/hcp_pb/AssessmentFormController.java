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
import javafx.scene.layout.Pane;
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
    private Pane paneClientHygiene;
    @FXML
    private RadioButton rdoBpgRequirement3;
    @FXML
    private RadioButton bpgRequirement4;
    @FXML
    private RadioButton rdoBpgRequirement2;
    @FXML
    private RadioButton rdoBpgRequirement1;
    @FXML
    private Pane showerIntervalsD;
    @FXML
    private RadioButton rdoBathingY;
    @FXML
    private RadioButton rdoBathingN;
    @FXML
    private RadioButton rdoBodyScrabY;
    @FXML
    private RadioButton rdoBodyScrabN;
    @FXML
    private RadioButton rdoGroomingY;
    @FXML
    private RadioButton rdoGroomingN;
    @FXML
    private RadioButton rdoShowerIntervalsD;
    @FXML
    private RadioButton rdoShowerIntervalsA;
    @FXML
    private RadioButton rdoShowerIntervalsP;
    @FXML
    private RadioButton rdoShowerIntervals2;
    @FXML
    private RadioButton rdoShowerCarerReqY;
    @FXML
    private RadioButton rdoShowerCarerReqN;
    @FXML
    private RadioButton rdoShowerCarerGenderM;
    @FXML
    private RadioButton rdoShowerCarerGenderN;
    @FXML
    private RadioButton rdoShowerCarerGenderF;
    @FXML
    private RadioButton rdoShowerPrefDayP;
    @FXML
    private RadioButton rdoShowerPrefDayA;
    @FXML
    private RadioButton rdoDressingRequirement1;
    @FXML
    private RadioButton rdoDressingRequirement3;
    @FXML
    private RadioButton rdoDressingRequirement4;
    @FXML
    private RadioButton rdoDressingRequirement2;
    @FXML
    private RadioButton rdoDressingY;
    @FXML
    private RadioButton rdoDressingN;
    @FXML
    private RadioButton rdoUndressingY;
    @FXML
    private RadioButton rdoUndressingN;
    @FXML
    private RadioButton rdoSocksY;
    @FXML
    private RadioButton rdoSocksN;
    @FXML
    private RadioButton rdoShoesY;
    @FXML
    private RadioButton rdoShoesN;
    @FXML
    private RadioButton rdoDressingCarerReqY;
    @FXML
    private RadioButton rdoDressingCarerReqN;
    @FXML
    private RadioButton rdoDressingCarerGenderM;
    @FXML
    private RadioButton rdoDressingCarerGenderN;
    @FXML
    private RadioButton rdoDressingCarerGenderF;
    @FXML
    private Button btnBackClientHygiene;
    @FXML
    private Button btnNextClientHygiene;
    @FXML
    private RadioButton rdoDressingPrefDayA;
    @FXML
    private RadioButton rdoDressingPrefDayP;
    @FXML
    private Pane paneOralHygiene;
    @FXML
    private RadioButton rdoOralHygieneRequirement1;
    @FXML
    private RadioButton rdoOralHygieneRequirement3;
    @FXML
    private RadioButton rdoOralHygieneRequirement4;
    @FXML
    private RadioButton rdoOralHygieneRequirement2;
    @FXML
    private RadioButton rdoHoldToothbrushY;
    @FXML
    private RadioButton rdoHoldToothbrushN;
    @FXML
    private RadioButton rdoToothPasteSqY;
    @FXML
    private RadioButton rdoToothPasteSqN;
    @FXML
    private RadioButton rdoMouthRinseY;
    @FXML
    private RadioButton rdoMouthRinseN;
    @FXML
    private RadioButton rdoMouthIssuesY;
    @FXML
    private RadioButton rdoMouthIssuesG;
    @FXML
    private RadioButton rdoMouthIssuesN;
    @FXML
    private RadioButton rdoTeethFormsCrowns;
    @FXML
    private RadioButton rdoTeethFormsD;
    @FXML
    private RadioButton rdoMouthIssuesM;
    @FXML
    private RadioButton teethFormsCaps;
    @FXML
    private RadioButton rdoTeethFormsP;
    @FXML
    private DatePicker dateDentistVisit;
    @FXML
    private RadioButton rdoToiletingRequirement1;
    @FXML
    private RadioButton rdoToiletingRequirement3;
    @FXML
    private RadioButton rdoToiletingRequirement4;
    @FXML
    private RadioButton rdoToiletingRequirement2;
    @FXML
    private RadioButton rdoWalkToiletY;
    @FXML
    private RadioButton rdoWalkToiletN;
    @FXML
    private RadioButton rdoPantsOffY;
    @FXML
    private RadioButton rdoPantsOffN;
    @FXML
    private RadioButton rdoToiletSittingY;
    @FXML
    private RadioButton rdoToiletSittingN;
    @FXML
    private RadioButton rdoPWashingY;
    @FXML
    private RadioButton rdoPWashingN;
    @FXML
    private RadioButton rdoPDryingY;
    @FXML
    private RadioButton rdoPDryingN;
    @FXML
    private DatePicker dateLastVisit;
    @FXML
    private TextArea TextDentistName;
    @FXML
    private Button btnBackOralHygiene;
    @FXML
    private Button btnNextOralHygiene;
    @FXML
    private Pane paneContinenceNeeds;
    @FXML
    private RadioButton rdoContinenceRequirement1;
    @FXML
    private RadioButton rdoContinenceRequirement2;
    @FXML
    private RadioButton rdoContinenceRequirement3;
    @FXML
    private RadioButton rdoContinenceRequirement4;
    @FXML
    private RadioButton rdoSetupPadsY;
    @FXML
    private RadioButton rdoSetupPadsN;
    @FXML
    private RadioButton rdoPWipeDryY;
    @FXML
    private RadioButton rdoPWipeDryN;
    @FXML
    private TextField txtContinenceNeed;
    @FXML
    private TextField txtSize;
    @FXML
    private TextField txtContinenceFund;
    @FXML
    private Button btnContinenceNeeds;
    @FXML
    private Button btnNextContinenceNeeds;
    @FXML
    private Pane paneMobilityNeeds;
    @FXML
    private RadioButton rdoMobilityRequirement1;
    @FXML
    private RadioButton rdoMobilityRequirement2;
    @FXML
    private RadioButton rdoMobilityRequirement3;
    @FXML
    private TextArea txtIssueDets;
    @FXML
    private RadioButton rdoMobilityIssueY;
    @FXML
    private RadioButton rdoMobilityIssueN;
    @FXML
    private RadioButton rdoTStandY;
    @FXML
    private RadioButton rdoTStandN;
    @FXML
    private RadioButton rdoTSitY;
    @FXML
    private RadioButton rdoTSitN;
    @FXML
    private RadioButton rdoTLyingY;
    @FXML
    private RadioButton rdoTLyingN;
    @FXML
    private RadioButton rdoWalkingY;
    @FXML
    private RadioButton rdoWalkingN;
    @FXML
    private RadioButton rdoCarRideY;
    @FXML
    private RadioButton rdoCarRideN;
    @FXML
    private RadioButton rdoUseStairsY;
    @FXML
    private RadioButton rdoUseStairsN;
    @FXML
    private RadioButton rdoTSittingY;
    @FXML
    private RadioButton rdoTSittingN;
    @FXML
    private TextArea txtFallDets;
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
    @FXML
    private RadioButton rdoLifterRequirementY;
    @FXML
    private RadioButton rdoLifterRequirementN;
    @FXML
    private RadioButton rdoFallHistoryY;
    @FXML
    private RadioButton rdoFallHistoryN;
    @FXML
    private Button btnContinenceNeeds1;
    @FXML
    private Button btnNextContinenceNeeds1;
    @FXML
    private Pane paneMobilityNeeds1;
    @FXML
    private RadioButton rdoHAidsFittingY;
    @FXML
    private RadioButton rdoHAidsFittingN;
    @FXML
    private RadioButton rdoBattChangeY;
    @FXML
    private RadioButton rdoBattChangeN;
    @FXML
    private RadioButton rdoAidCleaningY;
    @FXML
    private RadioButton rdoAidCleaningN;
    @FXML
    private RadioButton rdoUsingPhoneN;
    @FXML
    private RadioButton rdoUsingPhoneY;
    @FXML
    private RadioButton rdoHearingRefY;
    @FXML
    private RadioButton rdoHearingRefN;
    @FXML
    private RadioButton rdoCommsRequirement1;
    @FXML
    private RadioButton rdoCommsRequirement2;
    @FXML
    private RadioButton rdoCommsRequirement3;
    @FXML
    private RadioButton rdoCommsRequirement4;
    @FXML
    private TextArea txtBreathIssueDets;
    @FXML
    private RadioButton rdoBreathIssueY;
    @FXML
    private RadioButton rdoBreathIssueN;
    @FXML
    private TextArea txtExerciseDets;
    @FXML
    private RadioButton rdoExerciseY;
    @FXML
    private RadioButton rdoExerciseN;
    @FXML
    private RadioButton rdoAlliedHealthRefY;
    @FXML
    private RadioButton rdoAlliedHealthRefN;
    @FXML
    private RadioButton rdoUnsteadyY;
    @FXML
    private RadioButton rdoUnsteadyN;
    @FXML
    private RadioButton rdoStoopedY;
    @FXML
    private RadioButton rdoStoopedN;
    @FXML
    private RadioButton rdoLeaningY;
    @FXML
    private RadioButton rdoLeaningN;
    @FXML
    private Button btnBackMobilityNeeds1;
    @FXML
    private Button btnNextMobilityNeeds1;
    @FXML
    private Pane paneNutritionalNeeds;
    @FXML
    private RadioButton rdoNutritionRequirement1;
    @FXML
    private RadioButton rdoNutritionRequirement2;
    @FXML
    private RadioButton rdoNutritionRequirement3;
    @FXML
    private RadioButton rdoNutritionRequirement4;
    @FXML
    private RadioButton rdoMealPrepY;
    @FXML
    private RadioButton rdoMealPrepN;
    @FXML
    private RadioButton rdoFoodServiceY;
    @FXML
    private RadioButton rdoFoodServiceN;
    @FXML
    private RadioButton rdoSwallowIssueY;
    @FXML
    private RadioButton rdoSwallowIssueN;
    @FXML
    private RadioButton rdoWeightLossY;
    @FXML
    private RadioButton rdoWeightLossN;
    @FXML
    private RadioButton rdoNeedSupplementY;
    @FXML
    private RadioButton rdoNeedSupplementN;
    @FXML
    private RadioButton rdoUseCutleryY;
    @FXML
    private RadioButton rdoUseCutleryN;
    @FXML
    private RadioButton rdoDietaryRequirementY;
    @FXML
    private RadioButton rdoDietaryRequirementN;
    @FXML
    private RadioButton rdoMealSizeS;
    @FXML
    private RadioButton rdoMealSizeM;
    @FXML
    private RadioButton rdoMealSizeL;
    @FXML
    private RadioButton rdoDietRefY;
    @FXML
    private RadioButton rdoDietRefN;
    @FXML
    private Button btnBackNutritionalNeeds;
    @FXML
    private Button btnNextNutritionalNeeds;
    @FXML
    private Pane paneSkinIntegrityNeeds;
    @FXML
    private TextArea txtSkinProductDets;
    @FXML
    private TextArea txtInjuryDets;
    @FXML
    private TextArea txtPodiatrist;
    @FXML
    private TextArea txtVisitFreq;
    @FXML
    private RadioButton rdoSkinTearY;
    @FXML
    private RadioButton rdoSkinTearN;
    @FXML
    private RadioButton rdoSkinProblemY;
    @FXML
    private RadioButton rdoSkinProblemN;
    @FXML
    private RadioButton rdoPrescribedO;
    @FXML
    private RadioButton rdoPrescribedP;
    @FXML
    private RadioButton rdoWoundDressY;
    @FXML
    private RadioButton rdoWoundDressN;
    @FXML
    private RadioButton rdoAssistReqY;
    @FXML
    private RadioButton rdoAssistReqN;
    @FXML
    private RadioButton rdoPressureInjuryY;
    @FXML
    private RadioButton rdoPressureInjuryN;
    @FXML
    private RadioButton rdoAttendPodiatristY;
    @FXML
    private RadioButton rdoAttendPodiatristN;
    @FXML
    private RadioButton rdoUseSkinProductY;
    @FXML
    private RadioButton rdoUseSkinProductN;
    @FXML
    private Button btnBackSkinIntegrityNeeds;
    @FXML
    private Button btnNextSkinIntegrityNeeds;
    @FXML
    private Pane paneCMHBN;
    @FXML
    private RadioButton rdoCognitiveImpairmentY;
    @FXML
    private RadioButton rdoCognitiveImpairmentN;
    @FXML
    private RadioButton rdoCognitiveAssessmentY;
    @FXML
    private RadioButton rdoCognitiveAssessmentN;
    @FXML
    private RadioButton rdoMemoryLossY;
    @FXML
    private RadioButton rdoMemoryLossN;
    @FXML
    private RadioButton rdoDepressionY;
    @FXML
    private RadioButton rdoDepressionN;
    @FXML
    private TextArea txtOtherMentalText;
    @FXML
    private RadioButton rdoSchizophreniaY;
    @FXML
    private RadioButton rdoSchizophreniaN;
    @FXML
    private RadioButton rdoOtherMentalY;
    @FXML
    private RadioButton rdoOtherMentalN;
    @FXML
    private RadioButton rdoAnxietyY;
    @FXML
    private RadioButton rdoAnxietyN;
    @FXML
    private Button btnBackCMHBN;
    @FXML
    private Button btnNextCMHBN;
    @FXML
    private Pane paneMPMN;
    @FXML
    private RadioButton rdoInjectAssistY;
    @FXML
    private RadioButton rdoInjectAssistN;
    @FXML
    private RadioButton rdoBloodMonitorAssistY;
    @FXML
    private RadioButton rdoBloodMonitorAssistN;
    @FXML
    private RadioButton rdoTakingPrescY;
    @FXML
    private RadioButton rdoTakingPrescN;
    @FXML
    private RadioButton rdoMedsMismanagementY;
    @FXML
    private RadioButton rdoMedsMismanagementN;
    @FXML
    private RadioButton rdoSupplementY;
    @FXML
    private RadioButton rdoSupplementN;
    @FXML
    private TextArea txtSmDets;
    @FXML
    private TextArea txtSpecialMed;
    @FXML
    private TextArea txtMedInterval;
    @FXML
    private RadioButton rdoMedPepO;
    @FXML
    private RadioButton rdoMedPepW;
    @FXML
    private RadioButton rdoMedAdminY;
    @FXML
    private RadioButton rdoMedicationRequirement1;
    @FXML
    private RadioButton rdoMedicationRequirement2;
    @FXML
    private RadioButton rdoMedicationRequirement3;
    @FXML
    private RadioButton rdoMedicationRequirement4;
    @FXML
    private RadioButton rdoNeedReminderY;
    @FXML
    private RadioButton rdoNeedReminderN;
    @FXML
    private RadioButton rdoNeedPromptY;
    @FXML
    private RadioButton rdoNeedPromptN;
    @FXML
    private RadioButton rdoMedAdminN;
    @FXML
    private RadioButton rdoMedPickupY;
    @FXML
    private RadioButton rdoMedPickupN;
    @FXML
    private Button btnBackMPMN;
    @FXML
    private Button btnNextMPMN;
    @FXML
    private Pane panePMN;
    @FXML
    private TextArea txtPainMedFreq;
    @FXML
    private TextArea txtPainLevel;
    @FXML
    private RadioButton rdoRespiteDeliveryI;
    @FXML
    private RadioButton txtRespiteRequireY;
    @FXML
    private RadioButton rdoRespiteRequireN;
    @FXML
    private RadioButton rdoPainExpY;
    @FXML
    private RadioButton rdoPainExpN;
    @FXML
    private RadioButton rdoPainMedY;
    @FXML
    private RadioButton rdoPainMedN;
    @FXML
    private RadioButton rdoRespiteDeliveryR;
    @FXML
    private Button btnBackPMN;
    @FXML
    private Button btnNextPMN;
    @FXML
    private Pane paneClientHousing;
    @FXML
    private RadioButton rdoHousingOwnershipY;
    @FXML
    private RadioButton rdoHousingOwnership;
    @FXML
    private RadioButton rdoHousingSetupR;
    @FXML
    private RadioButton rdoHousingSetupG;
    @FXML
    private RadioButton rdoHousingSetupO;
    @FXML
    private RadioButton rdoLivingSetupN2;
    @FXML
    private TextArea txtAlarmCompany;
    @FXML
    private TextField txtKeyLockCode;
    @FXML
    private RadioButton rdoKeyLockBoxN;
    @FXML
    private RadioButton rdoLivingSetup;
    @FXML
    private RadioButton rdoLivingSetupN;
    @FXML
    private RadioButton rdoLivingSetupN1;
    @FXML
    private RadioButton rdoPersonalAlarmY;
    @FXML
    private RadioButton rdoPersonalAlarmN;
    @FXML
    private RadioButton rdoKeyLockBoxY;
    @FXML
    private Button btnBackClientHousing;
    @FXML
    private Button btnNextClientHousing;
    @FXML
    private Pane paneMHEN;
    @FXML
    private TextArea txtCleaningPrefDay;
    @FXML
    private RadioButton rdoKitchenCleaning;
    @FXML
    private RadioButton rdoLaundryCleaning;
    @FXML
    private RadioButton rdoLoungeCleaning;
    @FXML
    private RadioButton rdoPatioCleaning;
    @FXML
    private RadioButton rdoBathroomCleaning;
    @FXML
    private RadioButton rdoCleaningCarerGenderM;
    @FXML
    private RadioButton rdoCleaningCarerGenderF;
    @FXML
    private RadioButton rdoCleaningCarerGenderN;
    @FXML
    private RadioButton rdoBedroomCleaning;
    @FXML
    private RadioButton rdoToiletCleaning;
    @FXML
    private RadioButton rdoHomeMaintainSupportY;
    @FXML
    private RadioButton rdoHomeMaintainSupportN;
    @FXML
    private RadioButton rdoCleaning;
    @FXML
    private RadioButton rdoDecluttering;
    @FXML
    private RadioButton rdoDusting;
    @FXML
    private RadioButton rdoVacuum;
    @FXML
    private RadioButton rdoHangClothes;
    @FXML
    private RadioButton rdoLightGardening;
    @FXML
    private RadioButton rdoMowGarden;
    @FXML
    private RadioButton rdoWindowCleaning;
    @FXML
    private RadioButton rdoLaundry;
    @FXML
    private TextArea txtCleaningFreq;
    @FXML
    private RadioButton rdoMop;
    @FXML
    private RadioButton rdoSweeping;
    @FXML
    private RadioButton rdoGutterCleaning;
    @FXML
    private RadioButton rdoSocialActivityY;
    @FXML
    private RadioButton rdoSocialActivityN;
    @FXML
    private RadioButton rdoTaxiVoucher;
    @FXML
    private RadioButton rdoShoppingAssist;
    @FXML
    private RadioButton rdoDocAptAssist;
    @FXML
    private RadioButton rdoCanDriveY;
    @FXML
    private RadioButton rdoCanDriveN;
    @FXML
    private RadioButton rdoOtherAssist;
    @FXML
    private RadioButton rdoTransportAssistY;
    @FXML
    private RadioButton rdoTransportAssistN;
    @FXML
    private TextArea txtOtherAssist;
    @FXML
    private Button btnBackMHEN;
    @FXML
    private Button btnNextMHEN;
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
    
    @FXML private Pane clientDetsPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        paneOralHygiene.setVisible(false);
    }
          @FXML
    private void btnNextClientHygiene   (ActionEvent event) {
        paneClientHygiene.setVisible(false);
        paneOralHygiene.setVisible(true);
    }

    
    

}



