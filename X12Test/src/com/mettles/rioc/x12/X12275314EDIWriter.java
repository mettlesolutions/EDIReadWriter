package com.mettles.rioc.x12;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.mettles.rioc.x12275314.Loop1000A;
import com.mettles.rioc.x12275314.NM1PayerName1000A;
import com.mettles.rioc.x12275314.NM1ProviderNameInformation1000C;
import com.mettles.rioc.x12275314.PERPayerContactInformation1000A;
import com.mettles.rioc.x12275314.Loop1000B;
import com.mettles.rioc.x12275314.BGNBeginningSegment;
import com.mettles.rioc.x12275314.GSFunctionalGroupHeader;
import com.mettles.rioc.x12275314.ISAInterchangeControlHeader;
import com.mettles.rioc.x12275314.ST275TransactionSetHeader;
import com.mettles.rioc.x12275314.X12FunctionalGroup;
import com.mettles.rioc.x12275314.X12Interchange;
import com.mettles.rioc.x12275314.X12TransactionSet;
import com.mettles.rioc.x12275314.X12006020X314275A9;
import com.mettles.rioc.x12275314.NM1SubmitterInformation1000B;
import com.mettles.rioc.x12275314.Loop1000C;
import com.mettles.rioc.x12275314.Loop1000D;
import com.mettles.rioc.x12275314.NM1PatientName1000D;
import com.mettles.rioc.x12275314.NX1ProviderIdentification1100C;
import com.mettles.rioc.x12275314.N3ProviderAddress1100C;
import com.mettles.rioc.x12275314.N4ProviderCityStateZIPCode1100C;
import com.mettles.rioc.x12275314.REFProvidersAssignedClaimIdentifier1000D;
import com.mettles.rioc.x12275314.LXAssignedNumber2000A;
import com.mettles.rioc.x12275314.Loop1100C;
import com.mettles.rioc.x12275314.Loop2000A;
import com.mettles.rioc.x12275314.Loop2100B;
import com.mettles.rioc.x12275314.TRNPayerClaimControlTraceNumberProviderAttachmentControlTraceNumber2000A;
import com.mettles.rioc.x12275314.DTPAdditionalInformationSubmittedDate2100B;
import com.mettles.rioc.x12275314.CATFormatAndVersionIdentifier2100B;
import com.mettles.rioc.x12275314.OOIAssociatedObjectTypeIdentification2110B;
import com.mettles.rioc.x12275314.GEFunctionalGroupTrailer;
import com.mettles.rioc.x12275314.IEAInterchangeControlTrailer;
import com.mettles.rioc.x12275.X12006020X316275A2;
import com.mettles.rioc.x12275314.BDSBinaryDataSegment2110B;
import com.mettles.rioc.x12275314.Loop2110B;

public class X12275314EDIWriter {

	public void CreateEDI() {
		String xmlstring = GenerateXML();
		XMLtoEDIWriter ediwriterobj = new XMLtoEDIWriter();
		String edi = ediwriterobj.Write275XMLtoEDI(xmlstring);
		System.out.println("EDI String is " + edi);
		
	}
	
	public String GenerateXML() {
		String xmlcontent = "";
		X12006020X314275A9 x12275obj = new X12006020X314275A9();
		X12Interchange isaexchng = new X12Interchange();
		ISAInterchangeControlHeader isaheader = new ISAInterchangeControlHeader();
		isaheader.setISA01AuthorizationInformationQualifier("00");
		isaheader.setISA02AuthorizationInformation("          ");
		isaheader.setISA03SecurityInformationQualifier("00");
		isaheader.setISA04SecurityInformation("          ");
		isaheader.setISA05InterchangeIDQualifier("ZZ");
		isaheader.setISA06InterchangeSenderID("360373         "); // valtest oid replace
		isaheader.setISA07InterchangeIDQualifier("ZZ");
		isaheader.setISA08InterchangeReceiverID("111222777      "); //replace with RC oid
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		String strDate = dateFormat.format(date);
		DateFormat timeFormat = new SimpleDateFormat("HHmm");
		String strtime = timeFormat.format(date);
		DateFormat fulltimeFormat = new SimpleDateFormat("HHmmssSS");
		String strfulltime = fulltimeFormat.format(date);
		isaheader.setISA09InterchangeDate(strDate);
		isaheader.setISA10InterchangeTime(strtime);
		isaheader.setISA11RepetitionSeparator("+");
		isaheader.setISA12InterchangeControlVersionNumber("00602");
		isaheader.setISA13InterchangeControlNumber("000104142");
		isaheader.setISA14AcknowledgmentRequested("0");
		isaheader.setISA15InterchangeUsageIndicator("T");
		isaheader.setISA16ComponentElementSeparator(":");
		isaexchng.setISAInterchangeControlHeader(isaheader);
		X12FunctionalGroup x12grp = new X12FunctionalGroup();
		GSFunctionalGroupHeader gsheader = new GSFunctionalGroupHeader();
		gsheader.setGS01FunctionalIdentifierCode("PI");
		gsheader.setGS02ApplicationSendersCode("360373"); //replace with val oid
		gsheader.setGS03ApplicationReceiversCode("111222777"); // replace with rc oid
		DateFormat fulldateFormat = new SimpleDateFormat("yyyyMMdd");
		String fullDate = fulldateFormat.format(date);
		gsheader.setGS04Date(fullDate);
		gsheader.setGS05Time(strfulltime);
		gsheader.setGS06GroupControlNumber("104142");
		gsheader.setGS07ResponsibleAgencyCode("X");
		gsheader.setGS08VersionReleaseOrIndustryIdentifierCode("006020X314");
		x12grp.setGSFunctionalGroupHeader(gsheader);
		X12TransactionSet x12transet = new X12TransactionSet();
		ST275TransactionSetHeader stheader = new ST275TransactionSetHeader();
		stheader.setST01TransactionSetIdentifierCode("275");
		stheader.setST02TransactionSetControlNumber("104142");
		stheader.setST03VersionReleaseOrIndustryIdentifier("006020X314");
		x12transet.setST275TransactionSetHeader(stheader);
		BGNBeginningSegment bghseg = new BGNBeginningSegment();
		bghseg.setBGN01TransactionSetPurposeCode("02");
		DateFormat compdateFormat = new SimpleDateFormat("yyMMddHHmmssSS");
		String compstrDate = compdateFormat.format(date);
		
		bghseg.setBGN02TransactionSetReferenceNumber(compstrDate);
		bghseg.setBGN03TransactionSetCreationDate(fullDate);
		x12transet.setBGNBeginningSegment(bghseg);
		Loop1000A loop1000A = new Loop1000A();
		NM1PayerName1000A nm1seg = new NM1PayerName1000A();
		//NM1*ACV*2*A Vital Response Inc*****XX*1942236344*67*1P~
		nm1seg.setNM101EntityIdentifierCode("PR");
		nm1seg.setNM102EntityTypeQualifier("2"); //replace with 1 or 2
		nm1seg.setNM103PayerName("A Vital Response Inc"); //replace with payer name
		nm1seg.setNM104("");// requester first name replace
		nm1seg.setNM105(""); //replace with requester middle name
		nm1seg.setNM106("");
		nm1seg.setNM107("");
		nm1seg.setNM108IdentificationCodeQualifier("PI");
		nm1seg.setNM109PayerIdentifier("1942236344"); //replace with payer identifier
		loop1000A.setNM1PayerName1000A(nm1seg);
		PERPayerContactInformation1000A persegmnet =new PERPayerContactInformation1000A();
		persegmnet.setPER01ContactFunctionCode("IC");
		persegmnet.setPER02PayerContactName("Anthony Armstrong");//contact payer name replace
		persegmnet.setPER03CommunicationNumberQualifier("TE");
        persegmnet.setPER04PayerContactCommunicationNumber("9043635288");
        persegmnet.setPER05CommunicationNumberQualifier("EM");
        persegmnet.setPER06PayerContactCommunicationNumber("anthony.armstrong@novitas-solutions.com");
       	loop1000A.setPERPayerContactInformation1000A(persegmnet);
		x12transet.setLoop1000A(loop1000A);
		Loop1000B loop1000b = new Loop1000B(); //RC Info loop
		NM1SubmitterInformation1000B nm1submitter = new NM1SubmitterInformation1000B();
		nm1submitter.setNM101EntityIdentifierCode("41");
		nm1submitter.setNM102EntityTypeQualifier("1");
		nm1submitter.setNM103SubmitterLastOrOrganizationName("SMITH"); //replace with submitter last name
		nm1submitter.setNM104SubmitterFirstName("JENNIFER"); //replace with submitter first name
		nm1submitter.setNM105SubmitterMiddleNameOrInitial(""); //replace with submitter with middle name
		nm1submitter.setNM106("");
		nm1submitter.setNM107("");
		nm1submitter.setNM108IdentificationCodeQualifier("46");
		nm1submitter.setNM109SubmitterIdentifier("1234567890"); //replace with submitter NPI
		loop1000b.setNM1SubmitterInformation1000B(nm1submitter);
		x12transet.setLoop1000B(loop1000b);
		Loop1000C loop1000c = new Loop1000C();
		NM1ProviderNameInformation1000C nm1prov = new NM1ProviderNameInformation1000C();
		nm1prov.setNM101EntityIdentifierCode("1P");
		nm1prov.setNM102EntityTypeQualifier("2");
		nm1prov.setNM103ProviderLastOrOrganizationName("JOHN"); //replace with prov last name
		nm1prov.setNM104ProviderFirstName("MUFFY");
		nm1prov.setNM105ProviderMiddleName(""); //replace with prov middle name
		nm1prov.setNM106("");
		nm1prov.setNM107ProviderNameSuffix("");
		nm1prov.setNM108IdentificationCodeQualifier("XX");
		nm1prov.setNM109ProviderIdentifier("1234567890");
		loop1000c.setNM1ProviderNameInformation1000C(nm1prov);
		Loop1100C loop1100c = new Loop1100C();
		NX1ProviderIdentification1100C nxprov = new NX1ProviderIdentification1100C();
		nxprov.setNX101EntityIdentifierCode("1P");
		loop1100c.setNX1ProviderIdentification1100C(nxprov);
		N3ProviderAddress1100C n3prov = new N3ProviderAddress1100C();
		n3prov.setN301ProviderAddressLine("PO BOX 123*");
		loop1100c.setN3ProviderAddress1100C(n3prov);
		N4ProviderCityStateZIPCode1100C n4provstzip = new N4ProviderCityStateZIPCode1100C();
		n4provstzip.setN401ProviderCityName("CINCINNATI");
		n4provstzip.setN402ProviderStateCode("OH");
		n4provstzip.setN403ProviderPostalZoneOrZIPCode("43017");
		loop1100c.setN4ProviderCityStateZIPCode1100C(n4provstzip);
		loop1000c.setLoop1100C(loop1100c);
		x12transet.setLoop1000C(loop1000c);
		Loop1000D loop1000d = new Loop1000D();
		NM1PatientName1000D nm1pat1000d = new NM1PatientName1000D();
		nm1pat1000d.setNM101EntityIdentifierCode("QC");
		nm1pat1000d.setNM102EntityTypeQualifier("1");
		nm1pat1000d.setNM103PatientLastName("CARROL"); //replace with patient event name
		nm1pat1000d.setNM104PatientFirstName("JENNIFER");
		nm1pat1000d.setNM105PatientMiddleNameOrInitial("T");
		nm1pat1000d.setNM106("");
		nm1pat1000d.setNM107PatientNameSuffix("");
		nm1pat1000d.setNM108IdentificationCodeQualifier("MI");
		nm1pat1000d.setNM109PatientPrimaryIdentifier("1234567890");
		loop1000d.setNM1PatientName1000D(nm1pat1000d);
		REFProvidersAssignedClaimIdentifier1000D refclaimid = new REFProvidersAssignedClaimIdentifier1000D();
		refclaimid.setREF01ReferenceIdentificationQualifier("1");
		refclaimid.setREF02ProvidersAssignedClaimIdentifier("1234567890"); //replace with provider assigned claim identifier
		loop1000d.setREFProvidersAssignedClaimIdentifier1000D(refclaimid);
		x12transet.setLoop1000D(loop1000d);
		Loop2000A loop2000a = new Loop2000A();
		LXAssignedNumber2000A lxassgnum = new LXAssignedNumber2000A();
		lxassgnum.setLX01AssignedNumber("293212"); //replace with lx assigned number
		loop2000a.setLXAssignedNumber2000A(lxassgnum);
		TRNPayerClaimControlTraceNumberProviderAttachmentControlTraceNumber2000A trnclaimtrc = new TRNPayerClaimControlTraceNumberProviderAttachmentControlTraceNumber2000A();
		trnclaimtrc.setTRN01TraceTypeCode("1");
		trnclaimtrc.setTRN02PayerClaimControlNumberOrProviderAttachmentControlNumber("ACN500");//replace with ACN num
		loop2000a.setTRNPayerClaimControlTraceNumberProviderAttachmentControlTraceNumber2000A(trnclaimtrc);
		Loop2100B loop2100b = new Loop2100B();
		DTPAdditionalInformationSubmittedDate2100B dtpaddinfsub = new DTPAdditionalInformationSubmittedDate2100B();
		dtpaddinfsub.setDTP01DateTimeQualifier("368");
		dtpaddinfsub.setDTP02DateTimePeriodFormatQualifier("D8");
		dtpaddinfsub.setDTP03AdditionalInformationSubmittedDate(fullDate);
		loop2100b.setDTPAdditionalInformationSubmittedDate2100B(dtpaddinfsub);
		CATFormatAndVersionIdentifier2100B catfrmtvrs = new CATFormatAndVersionIdentifier2100B();
		catfrmtvrs.setCAT01AttachmentReportTypeCode("AE");
		catfrmtvrs.setCAT02AttachmentInformationFormatCode("HL");
		loop2100b.setCATFormatAndVersionIdentifier2100B(catfrmtvrs);
		Loop2110B loop2110b = new Loop2110B();
		OOIAssociatedObjectTypeIdentification2110B ooiseg = new OOIAssociatedObjectTypeIdentification2110B();
		ooiseg.setOOI01ObjectIdentificationGroup("1");
		ooiseg.setOOI02ObjectTypeQualifier("47");
		ooiseg.setOOI03ObjectAttributeIdentification("ATTACHMENT");
		loop2110b.setOOIAssociatedObjectTypeIdentification2110B(ooiseg);
		BDSBinaryDataSegment2110B bdsseg = new BDSBinaryDataSegment2110B();
		bdsseg.setBDS01FilterIDCode("B64");
		String str = "hello";
		bdsseg.setBDS02LengthOfBinaryData(String.valueOf(str.length()));
		bdsseg.setBDS03BinaryData(str);
		loop2110b.setBDSBinaryDataSegment2110B(bdsseg);
		loop2100b.setLoop2110B(loop2110b);
		loop2000a.setLoop2100B(loop2100b);
		x12transet.getLoop2000A().add(loop2000a);
		isaexchng.setISAInterchangeControlHeader(isaheader);
		x12grp.getX12TransactionSet().add(x12transet);
		GEFunctionalGroupTrailer getrailer = new GEFunctionalGroupTrailer();
		getrailer.setGE01NumberOfTransactionSetsIncluded("1");
		getrailer.setGE02GroupControlNumber("104142");
		x12grp.setGEFunctionalGroupTrailer(getrailer);
		isaexchng.getX12FunctionalGroup().add(x12grp);
		IEAInterchangeControlTrailer ieatrailer = new IEAInterchangeControlTrailer();
		ieatrailer.setIEA01NumberOfIncludedFunctionalGroups("1");
		ieatrailer.setIEA02InterchangeControlNumber("000104142");
		isaexchng.setIEAInterchangeControlTrailer(ieatrailer);
		x12275obj.setX12Interchange(isaexchng);
		
		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(X12006020X314275A9.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Print XML String to Console
			StringWriter sw = new StringWriter();
			// subelement
			// Write XML to StringWriter
			jaxbMarshaller.marshal(x12275obj, sw);
			xmlcontent = sw.toString();
			System.out.println("XML string is" + xmlcontent);
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
		return xmlcontent;
	
		
		
		
		
	}
	
}
