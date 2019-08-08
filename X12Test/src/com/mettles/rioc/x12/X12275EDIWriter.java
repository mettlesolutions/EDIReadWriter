package com.mettles.rioc.x12;
import com.mettles.rioc.x12275.X12006020X316275A2;
import com.mettles.rioc.x12275.X12Interchange;
import com.mettles.rioc.x12275.X12FunctionalGroup;
import com.mettles.rioc.x12275.GSFunctionalGroupHeader;
import com.mettles.rioc.x12275.X12TransactionSet;
import com.mettles.rioc.x12275.ST275TransactionSetHeader;
import com.mettles.rioc.X12005010X217278A1;
import com.mettles.rioc.x12275.BDSBinaryDataSegment2110A;
import com.mettles.rioc.x12275.BGNBeginningSegment;
import com.mettles.rioc.x12275.DTPAdditionalInformationSubmittedDate2100A;
import com.mettles.rioc.x12275.Loop1000A;
import com.mettles.rioc.x12275.Loop1000B;
import com.mettles.rioc.x12275.Loop1000C;
import com.mettles.rioc.x12275.NM1InformationSourceName1000A;
import com.mettles.rioc.x12275.NM1InformationReceiverName1000B;
import com.mettles.rioc.x12275.PERInformationSourceContactInformation1000A;
import com.mettles.rioc.x12275.CATFormatAndVersionIdentifier2100A;
import com.mettles.rioc.x12275.TRNAttachmentControlTraceNumber2000A;
import com.mettles.rioc.x12275.REFPatientAccountNumber1000C;
import com.mettles.rioc.x12275.SE275TransactionSetTrailer;
import com.mettles.rioc.x12275.LXAssignedNumber2000A;
import com.mettles.rioc.x12275.NM1PatientName1000C;
import com.mettles.rioc.x12275.OOIAssociatedObjectTypeIdentification2110A;
import com.mettles.rioc.x12275.IEAInterchangeControlTrailer;


import com.mettles.rioc.x12275.GEFunctionalGroupTrailer;
import com.mettles.rioc.x12275.Loop2000A;
import com.mettles.rioc.x12275.Loop2100A;
import com.mettles.rioc.x12275.Loop2110A;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.mettles.rioc.x12275.ISAInterchangeControlHeader;

public class X12275EDIWriter {
	
	public void CreateEDI() {
		String xmlstring = GenerateXML();
		XMLtoEDIWriter ediwriterobj = new XMLtoEDIWriter();
		String edi = ediwriterobj.Write275XMLtoEDI(xmlstring);
		System.out.println("EDI String is " + edi);
		
	}
	
	public String GenerateXML() {
		String xmlcontent = "";
		X12006020X316275A2 x12275obj = new X12006020X316275A2();
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
		gsheader.setGS08VersionReleaseOrIndustryIdentifierCode("006020X316");
		x12grp.setGSFunctionalGroupHeader(gsheader);
		X12TransactionSet x12transet = new X12TransactionSet();
		ST275TransactionSetHeader stheader = new ST275TransactionSetHeader();
		stheader.setST01TransactionSetIdentifierCode("275");
		stheader.setST02TransactionSetControlNumber("104142");
		stheader.setST03ImplementationConventionReferenceIdentifier("006020X316");
		x12transet.setST275TransactionSetHeader(stheader);
		BGNBeginningSegment bghseg = new BGNBeginningSegment();
		bghseg.setBGN01TransactionSetPurposeCode("02");
		DateFormat compdateFormat = new SimpleDateFormat("yyMMddHHmmssSS");
		String compstrDate = compdateFormat.format(date);
		
		bghseg.setBGN02TransactionSetReferenceNumber(compstrDate);
		bghseg.setBGN03TransactionSetCreationDate(fullDate);
		DateFormat partimeFormat = new SimpleDateFormat("HHmmss");
		String parstrtime = partimeFormat.format(date);
		bghseg.setBGN04TransactionSetCreationTime(parstrtime);
		x12transet.setBGNBeginningSegment(bghseg);
		Loop1000A loop1000A = new Loop1000A();
		NM1InformationSourceName1000A nm1seg = new NM1InformationSourceName1000A();
		//NM1*ACV*2*A Vital Response Inc*****XX*1942236344*67*1P~
		nm1seg.setNM101EntityIdentifierCode("ACV");
		nm1seg.setNM102EntityTypeQualifier("2"); //replace with 1 or 2
		nm1seg.setNM103InformationSourceLastOrOrganizationName("A Vital Response Inc"); //replace with org name
		nm1seg.setNM104InformationSourceFirstName("");// requester first name replace
		nm1seg.setNM105InformationSourceMiddleName(""); //replace with requester middle name
		nm1seg.setNM106("");
		nm1seg.setNM107InformationSourceNameSuffix("");
		nm1seg.setNM108IdentificationCodeQualifier("XX");
		nm1seg.setNM109InformationSourceIdentifier("1942236344"); //replace with requester NPI
		nm1seg.setNM110EntityRelationshipCode("67");
		nm1seg.setNM110EntityRelationshipCode("1P"); //replace with 1P or FA
		loop1000A.setNM1InformationSourceName1000A(nm1seg);
		PERInformationSourceContactInformation1000A persegmnet =new PERInformationSourceContactInformation1000A();
		persegmnet.setPER01ContactFunctionCode("IC");
		persegmnet.setPER02InformationSourceContactName("Anthony Armstrong");//contact name replace
		persegmnet.setPER03CommunicationNumberQualifier("TE");
        persegmnet.setPER04InformationSourceContactCommunicationNumber("9043635288");
        persegmnet.setPER05CommunicationNumberQualifier("EM");
        persegmnet.setPER06InformationSourceContactCommunicationNumber("anthony.armstrong@novitas-solutions.com"); //replace
       	loop1000A.setPERInformationSourceContactInformation1000A(persegmnet);
		x12transet.setLoop1000A(loop1000A);
		Loop1000B loop1000b = new Loop1000B(); //RC Info loop
		NM1InformationReceiverName1000B nm1rcvloop = new NM1InformationReceiverName1000B();
		nm1rcvloop.setNM101EntityIdentifierCode("40");
		nm1rcvloop.setNM102EntityTypeQualifier("2");
		nm1rcvloop.setNM103InformationReceiverLastOrOrganizationName("Novitas"); //replace with RC name
		nm1rcvloop.setNM104InformationReceiverFirstName("");
		nm1rcvloop.setNM105InformationReceiverMiddleName("");
		nm1rcvloop.setNM106("");
		nm1rcvloop.setNM107InformationReceiverNameSuffix("");
		nm1rcvloop.setNM108IdentificationCodeQualifier("PI");
		nm1rcvloop.setNM109InformationReceiverIdentifier("12502"); //replace with workload number
		nm1rcvloop.setNM110EntityRelationshipCode("67");
		nm1rcvloop.setNM111EntityIdentifierCode("X3");
		loop1000b.setNM1InformationReceiverName1000B(nm1rcvloop);
		x12transet.setLoop1000B(loop1000b);
		Loop1000C loop100c = new Loop1000C();
		NM1PatientName1000C nm1subseg = new NM1PatientName1000C();
		nm1subseg.setNM101EntityIdentifierCode("IL");
		nm1subseg.setNM102EntityTypeQualifier("1");
		nm1subseg.setNM103PatientLastName("LEWIS");//replace with sub last name
		nm1subseg.setNM104PatientFirstName("JACK"); //replace with sub first name
		nm1subseg.setNM105PatientMiddleNameOrInitial("");
		nm1subseg.setNM106("");
		nm1subseg.setNM107PatientNameSuffix("");
		nm1subseg.setNM108IdentificationCodeQualifier("MI");
		nm1subseg.setNM109PatientPrimaryIdentifier("9N21UA0RW80"); //replace with sub NPI/HICN
		loop100c.setNM1PatientName1000C(nm1subseg);
		REFPatientAccountNumber1000C refsubseg = new REFPatientAccountNumber1000C();
		refsubseg.setREF01ReferenceIdentificationQualifier("2I");
		refsubseg.setREF02PatientAccountNumber("TRN"); //X12 278 tranx number replace
		
		loop100c.setREFPatientAccountNumber1000C(refsubseg);
		x12transet.setLoop1000C(loop100c);
		Loop2000A loop2000a = new Loop2000A();
		LXAssignedNumber2000A lxseg = new LXAssignedNumber2000A();
		lxseg.setLX01AssignedNumber("1"); //increment based on Loop2000A list
		loop2000a.setLXAssignedNumber2000A(lxseg);
		TRNAttachmentControlTraceNumber2000A trn2000a = new TRNAttachmentControlTraceNumber2000A();
		trn2000a.setTRN01TraceTypeCode("1");
		trn2000a.setTRN02AttachmentControlNumber("PWK");//replace with ACN PWK from x12 278
		loop2000a.setTRNAttachmentControlTraceNumber2000A(trn2000a);
		Loop2100A loop2100 = new Loop2100A();
		DTPAdditionalInformationSubmittedDate2100A  dtpseg = new DTPAdditionalInformationSubmittedDate2100A();
		dtpseg.setDTP01DateTimeQualifier("368");
		dtpseg.setDTP02DateTimePeriodFormatQualifier("D8");
		dtpseg.setDTP03AdditionalInformationSubmissionDate(fullDate);
		loop2100.setDTPAdditionalInformationSubmittedDate2100A(dtpseg);
		CATFormatAndVersionIdentifier2100A catseg = new CATFormatAndVersionIdentifier2100A();
		catseg.setCAT01AttachmentReportTypeCode("AE");
		catseg.setCAT02AttachmentInformationFormatCode("HL");
		loop2100.setCATFormatAndVersionIdentifier2100A(catseg);
		Loop2110A loop2110a = new Loop2110A();
		OOIAssociatedObjectTypeIdentification2110A ooiobj = new OOIAssociatedObjectTypeIdentification2110A();
		ooiobj.setOOI01ObjectIdentificationGroup("1");
		ooiobj.setOOI02ObjectTypeQualifier("47");
		ooiobj.setOOI03ObjectAttributeIdentification("ATTACHMENT");
		loop2110a.setOOIAssociatedObjectTypeIdentification2110A(ooiobj);
		BDSBinaryDataSegment2110A bdsseg = new BDSBinaryDataSegment2110A();
		bdsseg.setBDS01FilterIDCode("B64");
		String filecontents = "hello";
		bdsseg.setBDS02LengthOfBinaryData(String.valueOf(filecontents.length()));
		bdsseg.setBDS03BinaryData(filecontents);
		loop2110a.setBDSBinaryDataSegment2110A(bdsseg);
		loop2100.setLoop2110A(loop2110a);
	
		loop2000a.setLoop2100A(loop2100);
		x12transet.getLoop2000A().add(loop2000a);
		SE275TransactionSetTrailer setrailer = new SE275TransactionSetTrailer();
		setrailer.setSE01TransactionSegmentCount("14");
		setrailer.setSE02TransactionSetControlNumber("104142");		
		x12transet.setSE275TransactionSetTrailer(setrailer);
	    
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
			JAXBContext jaxbContext = JAXBContext.newInstance(X12006020X316275A2.class);

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
