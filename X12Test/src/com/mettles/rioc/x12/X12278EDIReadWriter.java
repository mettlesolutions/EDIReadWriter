package com.mettles.rioc.x12;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Hashtable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.mettles.rioc.BHTBeginningOfHierarchicalTransaction;
import com.mettles.rioc.DMGSubscriberDemographicInformation2010C;
import com.mettles.rioc.DTPEventDate2000E;
import com.mettles.rioc.HIPatientDiagnosis2000E;
import com.mettles.rioc.HLPatientEventLevel2000E;
import com.mettles.rioc.HLRequesterLevel2000B;
import com.mettles.rioc.HLServiceLevel2000F;
import com.mettles.rioc.HLSubscriberLevel2000C;
import com.mettles.rioc.HLUtilizationManagementOrganizationUMOLevel2000A;
import com.mettles.rioc.Loop2000A;
import com.mettles.rioc.Loop2000B;
import com.mettles.rioc.Loop2000C;
import com.mettles.rioc.Loop2000E;
import com.mettles.rioc.Loop2000F;
import com.mettles.rioc.Loop2010A;
import com.mettles.rioc.Loop2010B;
import com.mettles.rioc.Loop2010C;
import com.mettles.rioc.Loop2010EA;
import com.mettles.rioc.N3PatientEventProviderAddress2010EA;
import com.mettles.rioc.N3RequesterAddress2010B;
import com.mettles.rioc.N3SubscriberAddress2010C;
import com.mettles.rioc.N4PatientEventProviderCityStateZIPCode2010EA;
import com.mettles.rioc.N4RequesterCityStateZIPCode2010B;
import com.mettles.rioc.N4SubscriberCityStateZIPCode2010C;
import com.mettles.rioc.NM1PatientEventProviderName2010EA;
import com.mettles.rioc.NM1RequesterName2010B;
import com.mettles.rioc.NM1SubscriberName2010C;
import com.mettles.rioc.NM1UtilizationManagementOrganizationUMOName2010A;
import com.mettles.rioc.PERRequesterContactInformation2010B;
import com.mettles.rioc.PWKAdditionalPatientInformation2000E;
import com.mettles.rioc.PWKAdditionalServiceInformation2000F;
import com.mettles.rioc.SETransactionSetTrailer;
import com.mettles.rioc.STTransactionSetHeader;
import com.mettles.rioc.SV1ProfessionalService2000F;
import com.mettles.rioc.SV2InstitutionalServiceLine2000F;
import com.mettles.rioc.UMHealthCareServicesReviewInformation2000E;
import com.mettles.rioc.X12005010X217278A1;

public class X12278EDIReadWriter {
	public static String x12278Versionnum = "278";
	public static String X12278ControlNum = "121901";
	public static String x12278IndentifierCode = "005010X217";
	public static String bhtHierhStrCode = "0007";
	public static String bhtTransPurpCode = "13";
	public static String loop2010ANM1IdCode = "X3";
	public static String loop2019ANM1TypeQual = "2";
	public static String loop2019ANM1IdCodeQual = "PI";
	private static String pwk = "";
	public static String mandateElemsKey[] = {"ContractorNumber","RequesterEntityIdentCode","RequesterEntityTypeQualifier","RequesterLastName","RequesterRequesterNPI","OrgName","RequesterAddressLine1","RequesterCity","RequesterState","RequesterZIPCode",	"RequesterContactName","RequesterTelephone","SubscriberLastName","SubscriberFirstName","SubscriberID","SubscriberAddressLine1","SubscriberCity","SubscriberStateCode","SubscriberZIPCode","SubscriberBirthDate",
			"SubscriberGender","BillTypePOS","EventDate_1","EventDate_2","PrimaryDiagnosisTypeCode:DiagnosisCode","OrderingProviderLastName","AttendingProviderLastName","FacilityProviderLastName","ServiceProviderLastName","ReferringProviderLastName","OperatingPhysicianLastName","ProffService1ProcedureCode","InstlService1ProcedureCode"};
    public static String mandateOrdProvElems[] = {"OrderingProviderEntityIdentCode","OrderingProviderEntityTypeQualifier","OrderingProviderNPI","OrderingProviderAddressLine1","OrderingProviderCity","OrderingProviderState","OrderingProviderZIPCode"};
	public static String mandateAttProvElems[] = {"AttendingProviderEntityIdentCode","AttendingProviderEntityTypeQualifier","AttendingProviderNPI","AttendingProviderAddressLine1","AttendingProviderCity","AttendingProviderState","AttendingProviderZIPCode"};
    public static String mandateFacProvElems[] = {"FacilityProviderEntityTypeQualifier","FacilityProviderEntityIdentCode","FacilityProviderNPI","FacilityProviderAddressLine1","FacilityProviderCity","FacilityProviderState","FacilityProviderZIPCode"};
    public static String mandateSrvProvElems[]= {"ServiceProviderEntityTypeQualifier","ServiceProviderEntityIdentCode","ServiceProviderNPI","ServiceProviderAddressLine1","ServiceProviderCity","ServiceProviderState","ServiceProviderZIPCode"};
    public static String mandateRfrProvElems[]= {"ReferringProviderEntityTypeQualifier","ReferringProviderEntityIdentCode","ReferringProviderNPI","ReferringProviderAddressLine1","ReferringProviderCity","ReferringProviderState","ReferringProviderZIPCode"};
    public static String mandateOprProvElems[]= {"OperatingPhysicianEntityTypeQualifier","OperatingPhysicianEntityIdentCode","OperatingPhysicianNPI","OperatingPhysicianAddressLine1","OperatingPhysicianCity","OperatingPhysicianState","OperatingPhysicianZIPCode"};
	public Loop2010C SetSubscriberInfo(Hashtable<String,String> inputVals) {
		Loop2010C subNM1Loop = new Loop2010C();
		NM1SubscriberName2010C subNM1Segment = new NM1SubscriberName2010C();
	
		subNM1Segment.setNM101EntityIdentifierCode("IL");
		subNM1Segment.setNM102EntityTypeQualifier("1");
		subNM1Segment.setNM103SubscriberLastName(inputVals.get("SubscriberLastName")); // replace with subscriber last name
		subNM1Segment.setNM104SubscriberFirstName(inputVals.get("SubscriberFirstName")); // replace with subscriber first name
		if(inputVals.get("SubscriberMiddle") != null)
			subNM1Segment.setNM105SubscriberMiddleNameOrInitial(inputVals.get("SubscriberMiddle")); 
		else
		subNM1Segment.setNM105SubscriberMiddleNameOrInitial(""); // replace with sub middle name
		
		if(inputVals.get("SubscriberNamePrefix") != null)
			subNM1Segment.setNM106SubscriberNamePrefix(inputVals.get("SubscriberNamePrefix")); 
		else
		subNM1Segment.setNM106SubscriberNamePrefix(""); // replace with sub middle name
		
		if(inputVals.get("SubscriberNameSuffix") != null)
			subNM1Segment.setNM107SubscriberNameSuffix(inputVals.get("SubscriberNameSuffix")); 
		else
		subNM1Segment.setNM107SubscriberNameSuffix(""); // replace with sub middle name
		
		subNM1Segment.setNM108IdentificationCodeQualifier("MI");
		subNM1Segment.setNM109SubscriberPrimaryIdentifier(inputVals.get("SubscriberID"));// replace with Sub ID
		subNM1Loop.setNM1SubscriberName2010C(subNM1Segment);
		N3SubscriberAddress2010C subAddress = new N3SubscriberAddress2010C();
		subAddress.setN301SubscriberAddressLine(inputVals.get("SubscriberAddressLine1")); // replace with sub addr line 1
	    if(inputVals.get("SubscriberAddressLine2") != null)
	    	subAddress.setN302SubscriberAddressLine(inputVals.get("SubscriberAddressLine2"));

		subNM1Loop.setN3SubscriberAddress2010C(subAddress);
		N4SubscriberCityStateZIPCode2010C subcitystate = new N4SubscriberCityStateZIPCode2010C();
		subcitystate.setN401SubscriberCityName(inputVals.get("SubscriberCity")); // replace with sub city name
		subcitystate.setN402SubscriberStateCode(inputVals.get("SubscriberStateCode")); // replace with sub state code
		subcitystate.setN403SubscriberPostalZoneOrZIPCode(inputVals.get("SubscriberZIPCode")); // replace with sub zip cpde
		subNM1Loop.setN4SubscriberCityStateZIPCode2010C(subcitystate);
		DMGSubscriberDemographicInformation2010C subperinfo = new DMGSubscriberDemographicInformation2010C();
		subNM1Loop.setDMGSubscriberDemographicInformation2010C(subperinfo);
		subperinfo.setDMG01DateTimePeriodFormatQualifier("D8");
		subperinfo.setDMG02SubscriberBirthDate(inputVals.get("SubscriberBirthDate")); // replace with sub DOB
		subperinfo.setDMG03SubscriberGenderCode(inputVals.get("SubscriberGender")); // replace with sub gender
		subNM1Loop.setDMGSubscriberDemographicInformation2010C(subperinfo);
		return subNM1Loop;
	}

	public Loop2000F setServiceLevelInfo(String ProcedureCode, String Quantity, String hiearachialIDNumber,
			String parentID, boolean bSV1, String pwk) {
		Loop2000F loop2000f = new Loop2000F();
		HLServiceLevel2000F hlServiceLevel2000F = new HLServiceLevel2000F();
		hlServiceLevel2000F.setHL01HierarchicalIDNumber(hiearachialIDNumber);
		hlServiceLevel2000F.setHL02HierarchicalParentIDNumber(parentID);
		hlServiceLevel2000F.setHL03HierarchicalLevelCode("SS");
		hlServiceLevel2000F.setHL04HierarchicalChildCode("0");
		loop2000f.setHLServiceLevel2000F(hlServiceLevel2000F);
		if (bSV1) {
			SV1ProfessionalService2000F sv1ProfessionalService2000F = new SV1ProfessionalService2000F();
			SV1ProfessionalService2000F.SV101CompositeMedicalProcedureIdentifier2000F sv1procedureinfo = new SV1ProfessionalService2000F.SV101CompositeMedicalProcedureIdentifier2000F();
			sv1procedureinfo.setSV10101ProductOrServiceIDQualifier("HC");
			sv1procedureinfo.setSV10102ProcedureCode(ProcedureCode);
			sv1ProfessionalService2000F.setSV101CompositeMedicalProcedureIdentifier2000F(sv1procedureinfo);
			sv1ProfessionalService2000F.setSV102ServiceLineAmount("");
			sv1ProfessionalService2000F.setSV103UnitOrBasisForMeasurementCode("UN");
			sv1ProfessionalService2000F.setSV104ServiceUnitCount(Quantity);
			loop2000f.setSV1ProfessionalService2000F(sv1ProfessionalService2000F);
		} else {
			SV2InstitutionalServiceLine2000F sv2InstitutionalServiceLine2000F = new SV2InstitutionalServiceLine2000F();
			sv2InstitutionalServiceLine2000F.setSV201ServiceLineRevenueCode(""); // replace with revenue code
			SV2InstitutionalServiceLine2000F.SV202CompositeMedicalProcedureIdentifier2000F sv202CompositeMedicalProcedureIdentifier2000F = new SV2InstitutionalServiceLine2000F.SV202CompositeMedicalProcedureIdentifier2000F();
			sv202CompositeMedicalProcedureIdentifier2000F.setSV20201ProductOrServiceIDQualifier("HC");
			sv202CompositeMedicalProcedureIdentifier2000F.setSV20202ProcedureCode(ProcedureCode);
			sv2InstitutionalServiceLine2000F
					.setSV202CompositeMedicalProcedureIdentifier2000F(sv202CompositeMedicalProcedureIdentifier2000F);
			sv2InstitutionalServiceLine2000F.setSV203ServiceLineAmount("");
			sv2InstitutionalServiceLine2000F.setSV204UnitOrBasisForMeasurementCode("UN");
			sv2InstitutionalServiceLine2000F.setSV205ServiceUnitCount(Quantity);
			loop2000f.setSV2InstitutionalServiceLine2000F(sv2InstitutionalServiceLine2000F);
		}
		PWKAdditionalServiceInformation2000F pwkinfo = new PWKAdditionalServiceInformation2000F();
		pwkinfo.setPWK01AttachmentReportTypeCode("77"); // replace with attachment report type code
		pwkinfo.setPWK02ReportTransmissionCode("FX"); // replace with transmission code
		pwkinfo.setPWK03("");
		pwkinfo.setPWK04("");
		pwkinfo.setPWK05IdentificationCodeQualifier("AC");
		pwkinfo.setPWK06AttachmentControlNumber(pwk);
		loop2000f.getPWKAdditionalServiceInformation2000F().add(pwkinfo);

		return loop2000f;
	}

	public Loop2000E setPatientEventLevelInfo(Hashtable<String,String> inputVals) {
		Loop2000E pateventloop = new Loop2000E();
		HLPatientEventLevel2000E HLPatientEvent = new HLPatientEventLevel2000E();
		HLPatientEvent.setHL01HierarchicalIDNumber("4");
		HLPatientEvent.setHL02HierarchicalParentIDNumber("3");
		HLPatientEvent.setHL03HierarchicalLevelCode("EV");
		HLPatientEvent.setHL04HierarchicalChildCode("1");
		pateventloop.setHLPatientEventLevel2000E(HLPatientEvent);
		UMHealthCareServicesReviewInformation2000E UMEventInfo = new UMHealthCareServicesReviewInformation2000E();
		UMEventInfo.setUM01RequestCategoryCode("HS");
		UMEventInfo.setUM02CertificationTypeCode("I");
		UMEventInfo.setUM03ServiceTypeCode(""); // replace service type code
		UMHealthCareServicesReviewInformation2000E.UM04HealthCareServiceLocationInformation2000E UM04Info = new UMHealthCareServicesReviewInformation2000E.UM04HealthCareServiceLocationInformation2000E();
		
		UM04Info.setUM0401FacilityTypeCode(inputVals.get("BillTypePOS")); // replace with bill type POS
		UM04Info.setUM0402FacilityCodeQualifier("A"); // change Qualifier from bil type POS
		// UM04HealthCareServiceLocationInformation2000E UM04info = new
		// UMHealthCareServicesReviewInformation2000E.UM04HealthCareServiceLocationInformation2000E();
		UMEventInfo.setUM04HealthCareServiceLocationInformation2000E(UM04Info);
		pateventloop.setUMHealthCareServicesReviewInformation2000E(UMEventInfo);
		//pateventloop.getUMHealthCareServicesReviewInformation2000E().
		DTPEventDate2000E dtpEventDate2000E = new DTPEventDate2000E();
		dtpEventDate2000E.setDTP01DateTimeQualifier("AAH");
		dtpEventDate2000E.setDTP02DateTimePeriodFormatQualifier("RD8"); // replace with D8 if it is not range
		
		dtpEventDate2000E.setDTP03ProposedOrActualEventDate(inputVals.get("EventDate_1")+"-"+inputVals.get("EventDate_2")); // replace with event start and end
																					// date
		pateventloop.setDTPEventDate2000E(dtpEventDate2000E);
		HIPatientDiagnosis2000E hiPatientDiagnosis2000E = new HIPatientDiagnosis2000E();
		HIPatientDiagnosis2000E.HI01HealthCareCodeInformation2000E HI01DiagCode = new HIPatientDiagnosis2000E.HI01HealthCareCodeInformation2000E();
		
		String diagVal = inputVals.get("PrimaryDiagnosisTypeCode:DiagnosisCode");
		StringTokenizer strtknzr = new StringTokenizer(diagVal,":");
		int cnt = 0;
		while(strtknzr.hasMoreTokens()) {
			if(cnt == 0)
				HI01DiagCode.setHI0101DiagnosisTypeCode(strtknzr.nextToken());
			else
				HI01DiagCode.setHI0102DiagnosisCode(strtknzr.nextToken());
			
			cnt++;
		}
	
		hiPatientDiagnosis2000E.setHI01HealthCareCodeInformation2000E(HI01DiagCode);
		pateventloop.setHIPatientDiagnosis2000E(hiPatientDiagnosis2000E);
		PWKAdditionalPatientInformation2000E pwkInfo = new PWKAdditionalPatientInformation2000E();
		pwkInfo.setPWK01AttachmentReportTypeCode("77"); // replace with attachment report type code
		pwkInfo.setPWK02ReportTransmissionCode("FX"); // replace with transmission code
		pwkInfo.setPWK03("");
		pwkInfo.setPWK04("");
		pwkInfo.setPWK05IdentificationCodeQualifier("AC");
		
		String uniquestr = "CMSSPRACHATACHCNTLNJL1VA"; // replace with RC name and PA type
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSS");
		String strDate = dateFormat.format(date);
		uniquestr = uniquestr + strDate;
		pwk = uniquestr;
		pwkInfo.setPWK06AttachmentControlNumber(uniquestr);
		pateventloop.getPWKAdditionalPatientInformation2000E().add(pwkInfo);
		// Replace Ordering Provider Info
		
		
		if(inputVals.containsKey("OrderingProviderLastName")) {
			
		
		EntityIdentityInfo prov = new EntityIdentityInfo();
		
		prov.setEntityIdentCode("DK"); // ordering provider code
		prov.setEntityTypeQualifier(inputVals.get("OrderingProviderEntityTypeQualifier")); // replace with ordering provider qualifier
		prov.setEntityLastName(inputVals.get("OrderingProviderLastName"));
		
		if(inputVals.get("OrderingProviderFirstName") != null)
		prov.setEntityFirstName(inputVals.get("OrderingProviderFirstName"));
		else
			prov.setEntityFirstName("");
		
		if(inputVals.get("OrderingProviderMiddleName") != null)
			prov.setEntityMiddleName(inputVals.get("OrderingProviderMiddleName"));
			else
				prov.setEntityMiddleName("");
		
		
		prov.setEntityNPI(inputVals.get("OrderingProviderNPI"));
		prov.setEntityAddressLine1(inputVals.get("OrderingProviderAddressLine1"));
		if(inputVals.get("OrderingProviderAddressLine2") != null)
			prov.setEntityAddressLine2(inputVals.get("OrderingProviderAddressLine2"));
		
		prov.setEntityCity(inputVals.get("OrderingProviderCity"));
		prov.setEntityState(inputVals.get("OrderingProviderState"));
		prov.setEntityZIPCode(inputVals.get("OrderingProviderZIPCode"));
		pateventloop.getLoop2010EA().add(setProviderInfo(prov));
		}
		//AttendingProviderEntityIdentCode,AttendingProviderEntityTypeQualifier,AttendingProviderLastName,AttendingProviderNPI,AttendingProviderAddressLine1,AttendingProviderCity,AttendingProviderState,AttendingProviderZIPCode
		if(inputVals.containsKey("AttendingProviderLastName")) {
			EntityIdentityInfo atteprov = new EntityIdentityInfo();
		atteprov.setEntityIdentCode(inputVals.get("AttendingProviderEntityIdentCode")); // Attending provider code
		atteprov.setEntityTypeQualifier(inputVals.get("AttendingProviderEntityTypeQualifier")); // replace with Attending provider qualifier
		
		atteprov.setEntityLastName(inputVals.get("AttendingProviderLastName"));
		if(inputVals.get("AttendingProviderFirstName") != null)
			atteprov.setEntityFirstName(inputVals.get("AttendingProviderFirstName"));
		else
			atteprov.setEntityFirstName("");
		
		if(inputVals.get("AttendingProviderMiddleName") != null)
			atteprov.setEntityMiddleName(inputVals.get("AttendingProviderMiddleName"));
		else
			atteprov.setEntityMiddleName("");
			
		atteprov.setEntityNPI(inputVals.get("AttendingProviderNPI"));
		atteprov.setEntityAddressLine1(inputVals.get("AttendingProviderAddressLine1"));
		if(inputVals.get("AttendingProviderAddressLine2") != null)
			atteprov.setEntityAddressLine2(inputVals.get("AttendingProviderAddressLine2"));
		atteprov.setEntityCity(inputVals.get("AttendingProviderCity"));
		atteprov.setEntityState(inputVals.get("AttendingProviderState"));
		atteprov.setEntityZIPCode(inputVals.get("AttendingProviderZIPCode"));
		pateventloop.getLoop2010EA().add(setProviderInfo(atteprov));
		}
			if(inputVals.containsKey("FacilityProviderLastName")) {
				EntityIdentityInfo facilityprov = new EntityIdentityInfo();
		facilityprov.setEntityIdentCode(inputVals.get("FacilityProviderEntityIdentCode")); // Facility provider code
		facilityprov.setEntityTypeQualifier(inputVals.get("FacilityProviderEntityTypeQualifier")); // replace with Facility provider qualifier
		facilityprov.setEntityLastName(inputVals.get("FacilityProviderLastName"));
		if(inputVals.get("FacilityProviderFirstName") != null)
			facilityprov.setEntityFirstName(inputVals.get("FacilityProviderFirstName"));
		else
		facilityprov.setEntityFirstName("");
		
		if(inputVals.get("FacilityProviderMiddleName") != null)
			facilityprov.setEntityMiddleName(inputVals.get("FacilityProviderMiddleName"));
		else
		facilityprov.setEntityMiddleName("");
		
		facilityprov.setEntityNPI(inputVals.get("FacilityProviderNPI"));
		facilityprov.setEntityAddressLine1(inputVals.get("FacilityProviderAddressLine1"));
		facilityprov.setEntityCity(inputVals.get("FacilityProviderCity"));
		facilityprov.setEntityState(inputVals.get("FacilityProviderState"));
		facilityprov.setEntityZIPCode(inputVals.get("FacilityProviderZIPCode"));
		pateventloop.getLoop2010EA().add(setProviderInfo(facilityprov));
		}
			  
		if (inputVals.containsKey("ServiceProviderLastName")) { // service provider code != null
			EntityIdentityInfo serviceprov = new EntityIdentityInfo();
			serviceprov.setEntityIdentCode(inputVals.get("ServiceProviderEntityIdentCode")); // service provider code
			serviceprov.setEntityTypeQualifier(inputVals.get("ServiceProviderEntityTypeQualifier")); // replace with service provider qualifier
			serviceprov.setEntityLastName(inputVals.get("ServiceProviderLastName"));
			serviceprov.setEntityFirstName("");
			serviceprov.setEntityMiddleName("");
			serviceprov.setEntityNPI(inputVals.get("ServiceProviderNPI"));
			serviceprov.setEntityAddressLine1(inputVals.get("ServiceProviderAddressLine1"));
			if(inputVals.get("ServiceProviderAddressLine2") != null)
				serviceprov.setEntityAddressLine2(inputVals.get("ServiceProviderAddressLine2"));
			serviceprov.setEntityCity(inputVals.get("ServiceProviderCity"));
			serviceprov.setEntityState(inputVals.get("ServiceProviderState"));
			serviceprov.setEntityZIPCode(inputVals.get("ServiceProviderZIPCode"));
			pateventloop.getLoop2010EA().add(setProviderInfo(serviceprov));
		}
		
		//"ReferringProviderEntityTypeQualifier","ReferringProviderLastName","ReferringProviderNPI","ReferringProviderAddressLine1","ReferringProviderCity","ReferringProviderState","ReferringProviderZIPCode"};

		if (inputVals.containsKey("ReferringProviderLastName")) {  // referring provider code != null
			EntityIdentityInfo refrov = new EntityIdentityInfo();
			refrov.setEntityIdentCode(inputVals.get("ReferringProviderEntityIdentCode")); // referring provider code
			refrov.setEntityTypeQualifier(inputVals.get("ReferringProviderEntityTypeQualifier")); // replace with referring provider qualifier
			refrov.setEntityLastName(inputVals.get("ReferringProviderLastName"));
			refrov.setEntityFirstName("");
			refrov.setEntityMiddleName("");
			refrov.setEntityNPI(inputVals.get("ReferringProviderNPI"));
			refrov.setEntityAddressLine1(inputVals.get("ReferringProviderEntityIdentCode"));
			refrov.setEntityCity(inputVals.get("ReferringProviderAddressLine1"));
			refrov.setEntityState(inputVals.get("ReferringProviderState"));
			refrov.setEntityZIPCode(inputVals.get("ReferringProviderZIPCode"));
			pateventloop.getLoop2010EA().add(setProviderInfo(refrov));
		}
		
		if (inputVals.containsKey("OperatingPhysicianLastName")) { // operating provider code != null
			EntityIdentityInfo operprov = new EntityIdentityInfo();
			operprov.setEntityIdentCode(inputVals.get("OperatingPhysicianEntityIdentCode")); // operating provider code
			operprov.setEntityTypeQualifier(inputVals.get("OperatingPhysicianEntityTypeQualifier")); // replace with operating provider qualifier
			operprov.setEntityLastName(inputVals.get("OperatingPhysicianLastName"));
			operprov.setEntityFirstName("");
			operprov.setEntityMiddleName("");
			operprov.setEntityNPI(inputVals.get("OperatingPhysicianNPI"));
			operprov.setEntityAddressLine1(inputVals.get("OperatingPhysicianAddressLine1"));
			operprov.setEntityCity(inputVals.get("OperatingPhysicianCity"));
			operprov.setEntityState(inputVals.get("OperatingPhysicianState"));
			operprov.setEntityZIPCode(inputVals.get("OperatingPhysicianZIPCode"));
			pateventloop.getLoop2010EA().add(setProviderInfo(operprov));
		}
		if(inputVals.containsKey("InstlService1ProcedureCode")) {
			  int count = 1;	
			  String key = "InstlService" + count + "ProcedureCode";
			   Integer parentID = 4;
			  
			  while(inputVals.containsKey(key)) {
				  
			      String quantitykey = "InstlService" + count + "UnitCount";
			      parentID++;
			      count++;
			    
			      
			      pateventloop.getLoop2000F().add(setServiceLevelInfo(inputVals.get(key), inputVals.get(quantitykey), parentID.toString(), "4", false, uniquestr)); // replace
			      key = "InstlService" + count + "ProcedureCode";
			  }

		
			
		}else if(inputVals.containsKey("ProffService1ProcedureCode")) {
			
		  int count = 1;	
		  String key = "ProffService" + count + "ProcedureCode";
		  Integer parentID = 4;
		   UMHealthCareServicesReviewInformation2000E.UM04HealthCareServiceLocationInformation2000E UM04Info1 = new UMHealthCareServicesReviewInformation2000E.UM04HealthCareServiceLocationInformation2000E();
		   UM04Info1.setUM0401FacilityTypeCode(inputVals.get("BillTypePOS")); // replace with bill type POS
		   UM04Info1.setUM0402FacilityCodeQualifier("B"); //TODO change Qualifier from bil type POS
			pateventloop.getUMHealthCareServicesReviewInformation2000E().setUM04HealthCareServiceLocationInformation2000E(UM04Info1);
		
		  
		  while(inputVals.containsKey(key)) {
			  
		      String quantitykey = "ProffService" + count + "UnitCount";
		      parentID++;
		      count++;
		      
		      
		      pateventloop.getLoop2000F().add(setServiceLevelInfo(inputVals.get(key), inputVals.get(quantitykey), parentID.toString(), "4", true, uniquestr)); // replace
		      key = "ProffService" + count + "ProcedureCode";
		  }

		}
		
		return pateventloop;
	}

	public Loop2010EA setProviderInfo(EntityIdentityInfo prov) {
		Loop2010EA loop2010ea = new Loop2010EA();
		NM1PatientEventProviderName2010EA nm1PatientEventProviderName2010EA = new NM1PatientEventProviderName2010EA();
		nm1PatientEventProviderName2010EA.setNM101EntityIdentifierCode(prov.getEntityIdentCode()); // replace
																											// with
																											// OrderingProviderEntityIdentCode
		nm1PatientEventProviderName2010EA.setNM102EntityTypeQualifier(prov.getEntityTypeQualifier()); // replace
																												// with
																												// size
																												// of
																												// the
																												// org
		nm1PatientEventProviderName2010EA
				.setNM103PatientEventProviderLastOrOrganizationName(prov.getEntityLastName());// replace with provider
																								// last name
		nm1PatientEventProviderName2010EA.setNM104PatientEventProviderFirstName(prov.getEntityFirstName());
		if (prov.getEntityMiddleName() != null)
			nm1PatientEventProviderName2010EA.setNM105PatientEventProviderMiddleName(prov.getEntityMiddleName());
		else
			nm1PatientEventProviderName2010EA.setNM105PatientEventProviderMiddleName("");
		if (prov.getEntityPrefix() != null)
			nm1PatientEventProviderName2010EA.setNM106PatientEventProviderNamePrefix(prov.getEntityPrefix());
		else
			nm1PatientEventProviderName2010EA.setNM106PatientEventProviderNamePrefix("");
		if (prov.getEntitySuffix() != null)
			nm1PatientEventProviderName2010EA.setNM107PatientEventProviderNameSuffix(prov.getEntitySuffix());
		else
			nm1PatientEventProviderName2010EA.setNM107PatientEventProviderNameSuffix("");

		nm1PatientEventProviderName2010EA.setNM108IdentificationCodeQualifier("XX");
		nm1PatientEventProviderName2010EA.setNM109PatientEventProviderIdentifier(prov.getEntityNPI());
		loop2010ea.setNM1PatientEventProviderName2010EA(nm1PatientEventProviderName2010EA);
		N3PatientEventProviderAddress2010EA patientEventProviderAddress2010EA = new N3PatientEventProviderAddress2010EA();
		patientEventProviderAddress2010EA.setN301PatientEventProviderAddressLine(prov.getEntityAddressLine1());
		if (prov.getEntityAddressLine2() != null) {
			patientEventProviderAddress2010EA.setN302PatientEventProviderAddressLine(prov.getEntityAddressLine2());
		}
		loop2010ea.setN3PatientEventProviderAddress2010EA(patientEventProviderAddress2010EA);
		N4PatientEventProviderCityStateZIPCode2010EA patientEventProviderCityStateZIPCode2010EA = new N4PatientEventProviderCityStateZIPCode2010EA();
		patientEventProviderCityStateZIPCode2010EA.setN401PatientEventProviderCityName(prov.getEntityCity());
		patientEventProviderCityStateZIPCode2010EA.setN402PatientEventProviderStateCode(prov.getEntityState());
		patientEventProviderCityStateZIPCode2010EA
				.setN403PatientEventProviderPostalZoneOrZIPCode(prov.getEntityZIPCode());
		loop2010ea.setN4PatientEventProviderCityStateZIPCode2010EA(patientEventProviderCityStateZIPCode2010EA);
		return loop2010ea;
	}
   
	  public String getHeader(String senderIDorig, String senderID, String recvIDorig, String recvID, String Date) {
	        Date date = Calendar.getInstance().getTime();
	        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
	        String strDate = dateFormat.format(date); 
	        DateFormat timeFormat = new SimpleDateFormat("HHmm");
	        String strtime = timeFormat.format(date); 
	        DateFormat fulltimeFormat = new SimpleDateFormat("HHmmssSS");
	        String strfulltime = fulltimeFormat.format(date); 
	    	String header = "ISA*00*          *00*          *ZZ*"+senderID+"*ZZ*"+recvID+"*"+strDate+"*"+strtime+"*+*00501*000121901*0*T*:~\n" + 
	            		"GS*HI*"+senderIDorig+"*"+recvIDorig+"*"+Date+"*"+strtime+"*121901*X*005010X217~\n";
	            return header; //replace T to P in prod environment
	    }
	    public String getTrailer() {
	    	String trailer = "GE*1*121901~\n"+
	    			"IEA*1*000121901~\n";
	            return trailer;
	    }

	    public EDIWriteStatus EDIWriter(Hashtable<String, String> inputVals, String senderEDI, String rcvrEDI,String uniqueid) {
	    	EDIWriteStatus retVal = new EDIWriteStatus();
	    	if(inputVals == null) {
	    		System.out.println("Input Hashtable is null");
	    		retVal.setStatus("Input Hashtable is null");
	    		retVal.setStatus_code(1);
	    		retVal.setErr_message("Input Excel file provided is invalid");
	    		return retVal;
	    	}
	    	retVal =  ValidateInputs( inputVals);
	    	if(retVal.getStatus_code() == 0 && retVal.getStatus().equals("Success")) {
	    		String xmlcontent = writeXML(inputVals,uniqueid);
	    		String edi = createXMLtoEDI(xmlcontent,senderEDI,rcvrEDI);
	    		retVal.setEDIString(edi);
	    		retVal.setPwk(pwk);
	    		retVal.setStatus_code(0);
	    		retVal.setStatus("Success");
	    		System.out.println("Home Health EDI is"+edi);
	    	}else {
	    		System.out.println("error code"+retVal.getStatus_code()+"Status is"+retVal.getStatus());
	    	}
	    	return retVal;
	    }
	    public EDIWriteStatus ValidateInputs(Hashtable<String, String> inputVals) {
	    	
	    	EDIWriteStatus retVal = new EDIWriteStatus();
	    	retVal.setStatus_code(0);
	    	retVal.setStatus("Success");
	    	//"ContractorNumber","RequesterEntityIdentCode","RequesterEntityTypeQualifier","RequesterLastName","RequesterRequesterNPI","OrgName","RequesterAddressLine1","RequesterCity","RequesterState","RequesterZIPCode",	"RequesterContactName","RequesterTelephone","SubscriberLastName","SubscriberFirstName","SubscriberID","SubscriberAddressLine1","SubscriberCity","SubscriberStateCode","SubscriberZIPCode","SubscriberBirthDate",
			//"SubscriberGender","BillTypePOS","EventDate_1","EventDate_2","PrimaryDiagnosisTypeCode:DiagnosisCode","OrderingProviderEntityIdentCode(S)","AttendingProviderEntityIdentCode(S)","FacilityProviderEntityIdentCode(S)","OperatingPhysicianEntityIdentCode(S)","ReferringProviderEntityIdentCode(S)","ServiceProviderEntityIdentCode(S)","(ProffService1ProcedureCode &ProffService1UnitCount)||(InstlService1ProcedureCode && InstlService1UnitCount)"};
			 //"OrderingProviderLastName","AttendingProviderLastName","FacilityProviderLastName","ServiceProviderLastName","ReferringProviderLastName","OperatingPhysicianLastName"
	    	for(int i = 0; i < mandateElemsKey.length; i++) {
	    		if(mandateElemsKey[i].equals("OrderingProviderLastName")) {
	    			
	    			if(inputVals.containsKey(mandateElemsKey[i])) {
	    				boolean bfail = false;
	    				for(int j = 0; j < mandateOrdProvElems.length; j++) {
	    					if(!inputVals.containsKey(mandateOrdProvElems[j])) {
	    					System.out.println("mandatory element is null"+mandateOrdProvElems[j]);
	    		    		retVal.setStatus("mandatory element"+mandateOrdProvElems[j]+"is null");
	    		    		retVal.setStatus_code(2);
	    		    		retVal.setErr_message("Mandatory element is null");
	    		    		bfail = true;
	    		    		break;
	    					}
	    				}
	    				if(bfail)
	    					break;
	    				
	    			}else 
	    				continue;
	    			
	    			
	    		}else if(mandateElemsKey[i].equals("AttendingProviderLastName")) {

	    			if(inputVals.containsKey(mandateElemsKey[i])) {
	    				boolean bfail = false;
	    				for(int j = 0; j < mandateAttProvElems.length; j++) {
	    					if(!inputVals.containsKey(mandateAttProvElems[j])) {
	    					System.out.println("mandatory element is null");
	    		    		retVal.setStatus("mandatory element"+mandateAttProvElems[j]+"is null");
	    		    		retVal.setStatus_code(2);
	    		    		retVal.setErr_message("Mandatory element is null");
	    		    		bfail = true;
	    		    		break;
	    					}
	    				}
	    				if(bfail)
	    					break;
	    				
	    			}else {
	    				continue;
	    			}
	    			
	    		}else if(mandateElemsKey[i].equals("ServiceProviderLastName")) {
	    			
	    			if(inputVals.containsKey(mandateElemsKey[i])) {
	    				boolean bfail = false;
	    				for(int j = 0; j < mandateFacProvElems.length; j++) {
	    					if(!inputVals.containsKey(mandateFacProvElems[j])) {
	    					System.out.println("mandatory element is null");
	    		    		retVal.setStatus("mandatory element"+mandateFacProvElems[j]+"is null");
	    		    		retVal.setStatus_code(2);
	    		    		retVal.setErr_message("Mandatory element is null");
	    		    		bfail = true;
	    		    		break;
	    					}
	    				}
	    				if(bfail)
	    					break;
	    				
	    			}else {
	    				continue;
	    			}
	    			
	    		}else if(mandateElemsKey[i].equals("OperatingPhysicianLastName")) {
	    			if(inputVals.containsKey(mandateElemsKey[i])) {
	    				boolean bfail = false;
	    				for(int j = 0; j < mandateOprProvElems.length; j++) {
	    					if(!inputVals.containsKey(mandateOprProvElems[j])) {
	    					System.out.println("mandatory element is null");
	    		    		retVal.setStatus("mandatory element"+mandateOprProvElems[j]+"is null");
	    		    		retVal.setStatus_code(2);
	    		    		retVal.setErr_message("Mandatory element is null");
	    		    		bfail = true;
	    		    		break;
	    					}
	    				}
	    				if(bfail)
	    					break;
	    			}else {
	    				continue;
	    			}
	    			
	    		}else if(mandateElemsKey[i].equals("ReferringProviderLastName")) {
	    			if(inputVals.containsKey(mandateElemsKey[i])) {
	    				boolean bfail = false;
	    				for(int j = 0; j < mandateRfrProvElems.length; j++) {
	    					if(!inputVals.containsKey(mandateRfrProvElems[j])) {
	    					System.out.println("mandatory element is null");
	    		    		retVal.setStatus("mandatory element"+mandateRfrProvElems[j]+"is null");
	    		    		retVal.setStatus_code(2);
	    		    		retVal.setErr_message("Mandatory element is null");
	    		    		bfail = true;
	    		    		break;
	    					}
	    				}
	    				if(bfail)
	    					break;
	    			}else {
	    				continue;
	    			}
	    			
	    		}else if(mandateElemsKey[i].equals("ServiceProviderLastName")) {
	    			if(inputVals.containsKey(mandateElemsKey[i])) {
	    				boolean bfail = false;
	    				for(int j = 0; j < mandateSrvProvElems.length; j++) {
	    					if(!inputVals.containsKey(mandateSrvProvElems[j])) {
	    					System.out.println("mandatory element is null");
	    		    		retVal.setStatus("mandatory element"+mandateSrvProvElems[j]+"is null");
	    		    		retVal.setStatus_code(2);
	    		    		retVal.setErr_message("Mandatory element is null");
	    		    		bfail = true;
	    		    		break;
	    					}
	    				}
	    				if(bfail)
	    					break;
	    			}else {
	    				continue;
	    			}
	    			
	    			
	    		}else if(mandateElemsKey[i].equals("ProffService1ProcedureCode")) {
	    			if(inputVals.containsKey(mandateElemsKey[i])) {
	    				if(!inputVals.containsKey("ProffService1UnitCount")) {
	    					System.out.println("mandatory element is null");
	    		    	    retVal.setStatus("mandatory element ProffService1UnitCount is null");
	    		    		retVal.setStatus_code(2);
	    		    		retVal.setErr_message("Mandatory element is null");
	    		    		break;
	    				}
	    			}else {
	    				if(!inputVals.containsKey("InstlService1ProcedureCode")) {
	    					System.out.println("mandatory element is null");
	    		    		retVal.setStatus("mandatory element"+mandateElemsKey[i]+"is null");
	    		    		retVal.setStatus_code(2);
	    		    		retVal.setErr_message("Mandatory element is null");
	    		    		break;
	    				}else {
	    					continue;
	    				}
	    			}
	    		}else if(mandateElemsKey[i].equals("InstlService1ProcedureCode")) {
	    			if(inputVals.containsKey(mandateElemsKey[i])) {
	    				if(!inputVals.containsKey("InstlService1UnitCount")) {
	    					System.out.println("mandatory element is null");
	    		    	    retVal.setStatus("mandatory element InstlService1UnitCount is null");
	    		    		retVal.setStatus_code(2);
	    		    		retVal.setErr_message("Mandatory element is null");
	    		    		break;
	    				}
	    			}else {
	    				if(!inputVals.containsKey("ProffService1ProcedureCode")) {
	    					System.out.println("mandatory element is null");
	    		    		retVal.setStatus("mandatory element"+mandateElemsKey[i]+"is null");
	    		    		retVal.setStatus_code(2);
	    		    		retVal.setErr_message("Mandatory element is null");
	    		    		break;
	    				}else {
	    					continue;
	    				}
	    			}
	    		}
	    		
	    		if(!inputVals.containsKey(mandateElemsKey[i])) {
	    			System.out.println("Input Hashtable is null");
		    		retVal.setStatus("mandatory element"+mandateElemsKey[i]+"is null");
		    		retVal.setStatus_code(2);
		    		retVal.setErr_message("Mandatory element is null");
		    		break;
	    		}
	    		
	    		if(mandateElemsKey[i].equals("RequesterEntityIdentCode") ) {
	    			if(!(inputVals.get(mandateElemsKey[i]).equals("1P") || inputVals.get(mandateElemsKey[i]).equals("FA"))) {
	    				System.out.println("RequesterEntityIdentCode is not valid");
			    		retVal.setStatus("element value"+mandateElemsKey[i]+"is not valid");
			    		retVal.setStatus_code(3);
			    		retVal.setErr_message("Not a valid allowed value");
			    		break;
	    			}
	    		}
	    		

	    		if(mandateElemsKey[i].equals("RequesterEntityTypeQualifier") ) {
	    			if(!(inputVals.get(mandateElemsKey[i]).equals("1") || inputVals.get(mandateElemsKey[i]).equals("2"))) {
	    				System.out.println("RequesterEntityTypeQualifier is not valid");
			    		retVal.setStatus("element value"+mandateElemsKey[i]+"is not valid");
			    		retVal.setStatus_code(3);
			    		retVal.setErr_message("Not a valid allowed value");
			    		break;
	    			}
	    		}
	    	}
	    	return retVal;
	    }
	    public String createXMLtoEDI(String xmlContent, String senderEDI, String rcvrEDI) {
	    	XMLtoEDIWriter ediwriterobj = new XMLtoEDIWriter();
			 String Header = "";
	            String Trailer = "";
	            String senderID =senderEDI;
	            String origsenderID = senderID;
	            while(senderID.length() < 15) {
	            	senderID = senderID + " ";
	            }
	            String receiverID = rcvrEDI;
	            String origreceiverID = receiverID; 
	            while(receiverID.length() < 15) {
	            	receiverID = receiverID + " ";
	            }
	          
	            Date currdate = Calendar.getInstance().getTime();
	            DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
	            String currstrDate = dateFormatter.format(currdate);
	            
	             Header = getHeader(origsenderID,senderID,origreceiverID,receiverID,currstrDate);
	             Trailer = getTrailer();
	            
			String edi = ediwriterobj.WriteXMLtoEDI(xmlContent,Header,Trailer);
			System.out.println("EDI String is " + edi);
	    	return edi;
	    }
	   
	public String writeXML(Hashtable<String, String> inputVals,String uniqueid) {
		X12005010X217278A1 subelement = new X12005010X217278A1();
		String xmlContent = null;
		STTransactionSetHeader stHeader = new STTransactionSetHeader();
		stHeader.setST01TransactionSetIdentifierCode(x12278Versionnum);
		stHeader.setST02TransactionSetControlNumber(X12278ControlNum);
		stHeader.setST03ImplementationGuideVersionName(x12278IndentifierCode);
		subelement.setSTTransactionSetHeader(stHeader);
		BHTBeginningOfHierarchicalTransaction bhtelement = new BHTBeginningOfHierarchicalTransaction();
		bhtelement.setBHT01HierarchicalStructureCode(bhtHierhStrCode);
		bhtelement.setBHT02TransactionSetPurposeCode(bhtTransPurpCode);
		bhtelement.setBHT03SubmitterTransactionIdentifier(uniqueid);
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String strDate = dateFormat.format(date);
		bhtelement.setBHT04TransactionSetCreationDate(strDate);
		DateFormat timeFormat = new SimpleDateFormat("HHmmss");
		String strtime = timeFormat.format(date);
		bhtelement.setBHT05TransactionSetCreationTime(strtime);
		subelement.setBHTBeginningOfHierarchicalTransaction(bhtelement);
		Loop2000A rcloopelement = new Loop2000A();
		HLUtilizationManagementOrganizationUMOLevel2000A hlrcelemet = new HLUtilizationManagementOrganizationUMOLevel2000A();
		hlrcelemet.setHL01HierarchicalIDNumber("1");
		hlrcelemet.setHL02("");
		hlrcelemet.setHL03HierarchicalLevelCode("20");
		hlrcelemet.setHL04HierarchicalChildCode("1");
		rcloopelement.setHLUtilizationManagementOrganizationUMOLevel2000A(hlrcelemet);
		Loop2010A loop2010a = new Loop2010A();
		
		
		NM1UtilizationManagementOrganizationUMOName2010A nmrcelement = new NM1UtilizationManagementOrganizationUMOName2010A();
		nmrcelement.setNM101EntityIdentifierCode(loop2010ANM1IdCode);
		nmrcelement.setNM102EntityTypeQualifier(loop2019ANM1TypeQual);
		nmrcelement.setNM103UtilizationManagementOrganizationUMOLastOrOrganizationName(inputVals.get("OrgName"));// replaceRC Name
		nmrcelement.setNM104UtilizationManagementOrganizationUMOFirstName("");
		nmrcelement.setNM105UtilizationManagementOrganizationUMOMiddleName("");
		nmrcelement.setNM106("");
		nmrcelement.setNM107UtilizationManagementOrganizationUMONameSuffix("");
		nmrcelement.setNM108IdentificationCodeQualifier(loop2019ANM1IdCodeQual);
		nmrcelement.setNM109UtilizationManagementOrganizationUMOIdentifier(inputVals.get("ContractorNumber")); // replace workload number
		loop2010a.setNM1UtilizationManagementOrganizationUMOName2010A(nmrcelement);
		rcloopelement.setLoop2010A(loop2010a);
		Loop2000B requesterloopelement = new Loop2000B();
		HLRequesterLevel2000B hlrequester = new HLRequesterLevel2000B();
		hlrequester.setHL01HierarchicalIDNumber("2");
		hlrequester.setHL02HierarchicalParentIDNumber("1");
		hlrequester.setHL03HierarchicalLevelCode("21");
		hlrequester.setHL04HierarchicalChildCode("1");
		requesterloopelement.setHLRequesterLevel2000B(hlrequester);
		Loop2010B reqnameelement = new Loop2010B();
		NM1RequesterName2010B nm1reqnameelement = new NM1RequesterName2010B();
		nm1reqnameelement.setNM101EntityIdentifierCode(inputVals.get("RequesterEntityIdentCode")); // replace requester 1P or FA
		nm1reqnameelement.setNM102EntityTypeQualifier(inputVals.get("RequesterEntityTypeQualifier")); // replace with size of org
		nm1reqnameelement.setNM103RequesterLastOrOrganizationName(inputVals.get("RequesterLastName")); // replace requester last org
																							// name
		if(inputVals.get("RequesterFirstName") == null)
		   nm1reqnameelement.setNM104RequesterFirstName("");
		else
			nm1reqnameelement.setNM104RequesterFirstName(inputVals.get("RequesterFirstName"));	
		
		if(inputVals.get("RequesterMiddleName") == null)
		   nm1reqnameelement.setNM105RequesterMiddleName("");
		else
			nm1reqnameelement.setNM105RequesterMiddleName(inputVals.get("RequesterMiddleName"));	
		
				
		if(inputVals.get("RequesterNamePreffix") == null)
			   nm1reqnameelement.setNM106("");
			else
				nm1reqnameelement.setNM106(inputVals.get("RequesterNamePreffix"));
		
		if(inputVals.get("RequesterNameSuffix") == null)
			   nm1reqnameelement.setNM107RequesterNameSuffix("");
			else
				nm1reqnameelement.setNM107RequesterNameSuffix(inputVals.get("RequesterNameSuffix"));
		
		nm1reqnameelement.setNM108IdentificationCodeQualifier("XX");
		nm1reqnameelement.setNM109RequesterIdentifier(inputVals.get("RequesterRequesterNPI")); // replace requester NPI
		reqnameelement.setNM1RequesterName2010B(nm1reqnameelement);
		N3RequesterAddress2010B n3reqaddrelement = new N3RequesterAddress2010B();
		
		n3reqaddrelement.setN301RequesterAddressLine(inputVals.get("RequesterAddressLine1")); // replace requester addr line1
		/// replace if req addr line 2 presetn
		if(inputVals.get("RequesterAddressLine2") != null) {
			n3reqaddrelement.setN302RequesterAddressLine(inputVals.get("RequesterAddressLine2"));
		}
		
		reqnameelement.setN3RequesterAddress2010B(n3reqaddrelement);
		N4RequesterCityStateZIPCode2010B n4reqaddrcityzip = new N4RequesterCityStateZIPCode2010B();
		

		n4reqaddrcityzip.setN401RequesterCityName(inputVals.get("RequesterCity")); // replace requester city name
		n4reqaddrcityzip.setN402RequesterStateOrProvinceCode(inputVals.get("RequesterState")); // replace requester
		n4reqaddrcityzip.setN403RequesterPostalZoneOrZIPCode(inputVals.get("RequesterZIPCode")); // replace requester
		reqnameelement.setN4RequesterCityStateZIPCode2010B(n4reqaddrcityzip);
		// PER*IC*Carrie Smith*TE*8035555555~
		PERRequesterContactInformation2010B reqperinfo = new PERRequesterContactInformation2010B();
		reqperinfo.setPER01ContactFunctionCode("IC");
		reqperinfo.setPER02RequesterContactName(inputVals.get("RequesterContactName")); // replace requester contact name
	    reqperinfo.setPER03CommunicationNumberQualifier("TE");
		reqperinfo.setPER04RequesterContactCommunicationNumber(inputVals.get("RequesterTelephone")); // repkace requester ph number
		boolean bEmailNtNull = false;
		if(inputVals.get("RequesterEmail") != null) {
			reqperinfo.setPER05CommunicationNumberQualifier("EM");
			reqperinfo.setPER06RequesterContactCommunicationNumber(inputVals.get("RequesterEmail"));
			bEmailNtNull = true;
		}
		if(inputVals.get("RequesterFAX") != null) {
			if(bEmailNtNull) {
				reqperinfo.setPER07CommunicationNumberQualifier("FX");
				reqperinfo.setPER08RequesterContactCommunicationNumber(inputVals.get("RequesterFAX"));
			}else {
				reqperinfo.setPER05CommunicationNumberQualifier("FX");
				reqperinfo.setPER06RequesterContactCommunicationNumber(inputVals.get("RequesterFAX"));
			}
			
		}
		reqnameelement.setPERRequesterContactInformation2010B(reqperinfo);
		requesterloopelement.setLoop2010B(reqnameelement);
		Loop2000C subloopelement = new Loop2000C();
		HLSubscriberLevel2000C hlsubelemet = new HLSubscriberLevel2000C();
		hlsubelemet.setHL01HierarchicalIDNumber("3");
		hlsubelemet.setHL02HierarchicalParentIDNumber("2");
		hlsubelemet.setHL03HierarchicalLevelCode("22");
		hlsubelemet.setHL04HierarchicalChildCode("1");
		subloopelement.setHLSubscriberLevel2000C(hlsubelemet);

		subloopelement.setLoop2010C(SetSubscriberInfo(inputVals));
		subloopelement.setLoop2000E(setPatientEventLevelInfo(inputVals));

		requesterloopelement.setLoop2000C(subloopelement);

		rcloopelement.setLoop2000B(requesterloopelement);

		subelement.setLoop2000A(rcloopelement);
		SETransactionSetTrailer seTransTrailer = new SETransactionSetTrailer();
		seTransTrailer.setSE01TransactionSegmentCount("32");
		seTransTrailer.setSE02TransactionSetControlNumber(X12278ControlNum);
		subelement.setSETransactionSetTrailer(seTransTrailer);
		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(X12005010X217278A1.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Print XML String to Console
			StringWriter sw = new StringWriter();
			// subelement
			// Write XML to StringWriter
			jaxbMarshaller.marshal(subelement, sw);
			xmlContent = sw.toString();
			System.out.println("XML string is" + xmlContent);
			/*
			 * DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			 * DocumentBuilder builder = factory.newDocumentBuilder(); ByteArrayInputStream
			 * input = new ByteArrayInputStream( xmlContent.getBytes("UTF-8")); Document doc
			 * = builder.parse(input);
			 */
			// doc.getDocumentElement().normalize();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Loop2010C hlloop
		// subloopelement.setLoop2010C();
		// .setLoop2000C();
       return xmlContent;
	}

	public X12278EDIResponse parse278ResponseEDI(String Text) {
		X12278EDIResponse response = new X12278EDIResponse();
		StringTokenizer st1 = new StringTokenizer(Text, System.getProperty("line.separator"));
		String prevSegment = null;
		boolean bRequesterSeg = false, bSubscriberSeg = false, bRecvSeg = false, bEventSeg = false,
				bProviderSeg = false, bServicelvlSeg = false;
		HealthCareReviewInfo sshcrinfo = null;
		while (st1.hasMoreTokens()) {
			String currSegment = st1.nextToken();
			
			if (currSegment.startsWith("ISA") || currSegment.startsWith("GS") || currSegment.startsWith("ST")
					|| currSegment.startsWith("SE") || currSegment.startsWith("GE") || currSegment.startsWith("IEA")) {
              if(currSegment.startsWith("SE"))
              {
            	  if(bServicelvlSeg & sshcrinfo != null) {
            		  response.getService_healthcare().add(sshcrinfo);
            	 
            	  }
            	  
            	 
            	  
              }
              
              if(currSegment.startsWith("GS")) {
            		int len = currSegment.length();
    				String trnStr = currSegment.substring(0, len - 1);
    				System.out.println("Segment str is" + trnStr);
    				StringTokenizer trnStrTokenizer = new StringTokenizer(trnStr, "*");
    				int count = 0;
    				while (trnStrTokenizer.hasMoreTokens()) {
    					String trnToken = trnStrTokenizer.nextToken();
    					// System.out.println("Token is"+trnToken);
    					count++;
    					if(count == 3)
    						response.setSenderId(trnToken);
    					else if(count == 4)
    						response.setRcvrId(trnToken);
    				}
        	  }
			} else if (currSegment.startsWith("BHT")) {
				int len = currSegment.length();
				String trnStr = currSegment.substring(0, len - 1);
				System.out.println("Segment str is" + trnStr);
				StringTokenizer trnStrTokenizer = new StringTokenizer(trnStr, "*");
				int count = 0;
				while (trnStrTokenizer.hasMoreTokens()) {
					String trnToken = trnStrTokenizer.nextToken();
					// System.out.println("Token is"+trnToken);
					count++;
					if(count == 4)
						response.setClienttransid(trnToken);
				}

			} else if (currSegment.startsWith("HL*1")) {
				bRecvSeg = true;
			} else if (currSegment.startsWith("HL*2")) {
				bRecvSeg = false;
				bRequesterSeg = true;
			} else if (currSegment.startsWith("HL*3")) {
				bRequesterSeg = false;
				bSubscriberSeg = true;
			} else if (currSegment.startsWith("HL*4")) {
				bSubscriberSeg = false;
				bEventSeg = true;
			} else if (bEventSeg && currSegment.startsWith("NM1")) {
				bEventSeg = false;
				bProviderSeg = true;
			} else if (currSegment.startsWith("HL*5")) {
				bProviderSeg = false;
				bEventSeg = false;
				bServicelvlSeg = true;
				
			} else {
				if (bEventSeg) {
					if (currSegment.startsWith("TRN*")) {
						int len = currSegment.length();
						String trnStr = currSegment.substring(0, len - 1);
						System.out.println("Segment str is" + trnStr);
						StringTokenizer trnStrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						boolean besmdtranx = false;
						String transid = null;
						while (trnStrTokenizer.hasMoreTokens()) {
							String trnToken = trnStrTokenizer.nextToken();
							// System.out.println("Token is"+trnToken);
							count++;
							if (count == 3) {
								System.out.println("Transaction ID is" + trnToken);
								transid = trnToken;
							} else if (count == 2) {
								if (trnToken.equals("1")) {
									besmdtranx = true;
								} else if (trnToken.equals("2")) {
									break;
								}
							}else if(count == 4) {
								if(trnToken.equals("9ESMDSYSTM") & besmdtranx & transid != null)
									response.setEsmdtransid(transid);
									
							}
						}
					} else if (currSegment.startsWith("AAA*")) {
						int len = currSegment.length();
						String trnStr = currSegment.substring(0, len - 1);
						StringTokenizer AAAStrTokenizer = new StringTokenizer(trnStr, "*");
						AAAErrorInfo aaaerror = new AAAErrorInfo();
						aaaerror.setSegment_info("Event Level Information Segment");
						int count = 0;
						while (AAAStrTokenizer.hasMoreTokens()) {
							String trnToken = AAAStrTokenizer.nextToken();
							count++;
							if (count == 4) {
								System.out.println("Receiver Loop AAA error reject reason code " + trnToken);
								aaaerror.setError_code(trnToken);
								if(AAAErrorCodes.valueOf("AAAERR" + trnToken) != null)
									aaaerror.setError_string(AAAErrorCodes.valueOf("AAAERR" + trnToken).getDescription());
							} else if (count == 5) {
								if(trnToken.equals("C")) {
									aaaerror.setFollowup_action("Please correct and resubmit");
								}else {
									aaaerror.setFollowup_action(trnToken);
								}
								System.out.println("Receiver Loop AAA error follow up action code" + trnToken);
							}
						}
						response.getErrors().add(aaaerror);
					} else if (currSegment.startsWith("HCR*")) {
						int len = currSegment.length();
						HealthCareReviewInfo hcrinfo = new HealthCareReviewInfo();
						String trnStr = currSegment.substring(0, len - 1);
						StringTokenizer HCRStrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						String decsCode = "";
						while (HCRStrTokenizer.hasMoreTokens()) {
							String trnToken = HCRStrTokenizer.nextToken();
							System.out.println("Token is "+trnToken);
							count++;
							if (count == 2) {
								System.out.println("HCR segment elemts are" + trnToken);
								decsCode = trnToken;
								hcrinfo.setAction_code(PADecisionCodes.valueOf("HCR"+trnToken).getDescription());
							}else if(count == 3) {
								System.out.println("Token is A1 or A2"+decsCode);
								if(decsCode.equals("A1") || decsCode.equals("A2")) {
									hcrinfo.setUtn_value(trnToken);
									
								}
								else if(!trnToken.equals(""))
									hcrinfo.setReason_code(RCDecisionReasonCode.valueOf("RC"+trnToken).getDescription());
						
							}

						}
						response.setConsolidated_hcr(hcrinfo);
					} else if (currSegment.startsWith("REF*")) {
						int len = currSegment.length();
						String trnStr = currSegment.substring(0, len - 1);
						StringTokenizer REFStrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						while (REFStrTokenizer.hasMoreTokens()) {
							String trnToken = REFStrTokenizer.nextToken();
							count++;
							if (count == 3) {
								System.out.println("UTN value is" + trnToken);
								if(!trnToken.equals("ESMDNUMBER"))
								response.getConsolidated_hcr().setUtn_value(trnToken);
							}

						}
					} else if (currSegment.startsWith("MSG*")) {
						int len = currSegment.length();
						String trnStr = currSegment.substring(0, len - 1);
						StringTokenizer MSGStrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						while (MSGStrTokenizer.hasMoreTokens()) {
							String trnToken = MSGStrTokenizer.nextToken();
							count++;
							if (count == 2) {
								response.setMessage(trnToken);
								System.out.println("MSG value is" + trnToken);
							}

						}
					}
				}
				if (bProviderSeg) {
					if (currSegment.startsWith("AAA*")) {
						int len = currSegment.length();
						String trnStr = currSegment.substring(0, len - 1);
						AAAErrorInfo aaaerror = new AAAErrorInfo();
						aaaerror.setSegment_info("List of Service Provider Information Segments");
						StringTokenizer AAAStrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						while (AAAStrTokenizer.hasMoreTokens()) {
							String trnToken = AAAStrTokenizer.nextToken();
							count++;
							if (count == 4) {
								System.out.println("Receiver Loop AAA error reject reason code " + trnToken);
								aaaerror.setError_code(trnToken);
								if(AAAErrorCodes.valueOf("AAAERR" + trnToken) != null)
									aaaerror.setError_string(AAAErrorCodes.valueOf("AAAERR" + trnToken).getDescription());
							} else if (count == 5) {
								if(trnToken.equals("C")) {
									aaaerror.setFollowup_action("Please correct and resubmit");
								}else {
									aaaerror.setFollowup_action(trnToken);
								}
								System.out.println("Receiver Loop AAA error follow up action code" + trnToken);
							}
						}
						response.getErrors().add(aaaerror);
					}
				}
				if (bRecvSeg || bRequesterSeg || bSubscriberSeg) {
					if (currSegment.startsWith("AAA*")) {
						int len = currSegment.length();
						AAAErrorInfo aaaerror = new AAAErrorInfo();
						if(bRecvSeg) {
							aaaerror.setSegment_info("Ultilization Management Segment");
						}
						else if(bRequesterSeg) {
							aaaerror.setSegment_info("Requester/Provider Segment");
						}
						else if(bSubscriberSeg) {
							aaaerror.setSegment_info("Subscriber/Beneficiary Segment");
						}
						String trnStr = currSegment.substring(0, len - 1);
						StringTokenizer AAAStrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						while (AAAStrTokenizer.hasMoreTokens()) {
							String trnToken = AAAStrTokenizer.nextToken();
							count++;
							if (count == 4) {
								System.out.println("Receiver Loop AAA error reject reason code " + trnToken);
								aaaerror.setError_code(trnToken);
								if(AAAErrorCodes.valueOf("AAAERR" + trnToken) != null)
									aaaerror.setError_string(AAAErrorCodes.valueOf("AAAERR" + trnToken).getDescription());
								
							} else if (count == 5) {
								if(trnToken.equals("C")) {
									aaaerror.setFollowup_action("Please correct and resubmit");
								}else {
									aaaerror.setFollowup_action(trnToken);
								}
								System.out.println("Receiver Loop AAA error follow up action code" + trnToken);
							}
						}
						response.getErrors().add(aaaerror);
					}
				}
				if (bServicelvlSeg) {
					 System.out.println("Inside service loop"+currSegment);
					if (currSegment.startsWith("TRN*")) {
						int len = currSegment.length();
						String trnStr = currSegment.substring(0, len - 1);
						StringTokenizer TRNStrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						while (TRNStrTokenizer.hasMoreTokens()) {
							String trnToken = TRNStrTokenizer.nextToken();
							count++;
							if (count != 1) {
								System.out.println("TRN Token in Service loop " + trnToken);
							}
						}
					} else if (currSegment.startsWith("AAA*")) {
						int len = currSegment.length();
						
						String trnStr = currSegment.substring(0, len - 1);
						StringTokenizer AAAStrTokenizer = new StringTokenizer(trnStr, "*");
						AAAErrorInfo aaaerror = new AAAErrorInfo();
						aaaerror.setSegment_info("Service Level Information Segment");
						
						int count = 0;
						while (AAAStrTokenizer.hasMoreTokens()) {
							String trnToken = AAAStrTokenizer.nextToken();
							count++;
							if (count == 4) {
								System.out.println("Receiver Loop AAA error reject reason code " + trnToken);
								aaaerror.setError_code(trnToken);
								if(AAAErrorCodes.valueOf("AAAERR" + trnToken) != null)
									aaaerror.setError_string(AAAErrorCodes.valueOf("AAAERR" + trnToken).getDescription());
							} else if (count == 5) {
								if(trnToken.equals("C")) {
									aaaerror.setFollowup_action("Please correct and resubmit");
								}else {
									aaaerror.setFollowup_action(trnToken);
								}
								System.out.println("Receiver Loop AAA error follow up action code" + trnToken);
							}
							
						}
						response.getErrors().add(aaaerror);
					} else if (currSegment.startsWith("HCR*")) {
						sshcrinfo = new HealthCareReviewInfo();
						int len = currSegment.length();
						
						String trnStr = currSegment.substring(0, len - 1);
						System.out.println("curr seg"+trnStr);
						StringTokenizer HCRStrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						String decsCode = null;
						while (HCRStrTokenizer.hasMoreTokens()) {
							String trnToken = HCRStrTokenizer.nextToken();
							count++;
							if (count == 2) {
								System.out.println("HCR segment elemts are" + trnToken);
								decsCode = trnToken;
								sshcrinfo.setAction_code(PADecisionCodes.valueOf("HCR"+trnToken).getDescription());
								
							}else if(count == 3) {
								if(decsCode.equals("A1") || decsCode.equals("A2"))
								{
									sshcrinfo.setUtn_value(trnToken);
								}else if(!trnToken.equals(""))
									sshcrinfo.setReason_code(RCDecisionReasonCode.valueOf("RC"+trnToken).getDescription());
								
								
							}

						}
					} else if (currSegment.startsWith("REF*")) {
						int len = currSegment.length();
						String trnStr = currSegment.substring(0, len - 1);
						StringTokenizer REFStrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						while (REFStrTokenizer.hasMoreTokens()) {
							String trnToken = REFStrTokenizer.nextToken();
							count++;
							if (count == 3) {
								System.out.println("UTN value is" + trnToken);
								if(!trnToken.equals("ESMDNUMBER"))
									sshcrinfo.setUtn_value(trnToken);
							}

						}
					} else if (currSegment.startsWith("MSG*")) {
						int len = currSegment.length();
						String trnStr = currSegment.substring(0, len - 1);
						StringTokenizer MSGStrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						while (MSGStrTokenizer.hasMoreTokens()) {
							String trnToken = MSGStrTokenizer.nextToken();
							count++;
							if (count == 2) {
								sshcrinfo.setSrv_message(trnToken);
								System.out.println("MSG value is" + trnToken);
							}

						}
					} else if (currSegment.startsWith("HL*6")) {
						if(sshcrinfo != null) {
							System.out.println("Adding ss info");
						  response.getService_healthcare().add(sshcrinfo);
						}
						
						System.out.println("Next Service Line");
						sshcrinfo = new HealthCareReviewInfo();
					}else if(currSegment.startsWith("SV1*") || currSegment.startsWith("SV2*")) {
						int len = currSegment.length();
						boolean bsv1 = false;
						
						String trnStr = currSegment.substring(0, len - 1);
						StringTokenizer SVtrTokenizer = new StringTokenizer(trnStr, "*");
						int count = 0;
						while (SVtrTokenizer.hasMoreTokens()) {
							String trnToken = SVtrTokenizer.nextToken();
							count++;
							if( count == 2) {
								sshcrinfo.setService_code(trnToken);
							}
							
						}
					}
				}
			}

		
		}
		return response;
	}
}
