package com.mettles.rioc.x12;

import com.company.rioc.ack999.X12005010X231A1999A1;
import com.mettles.rioc.X12005010X217278A1;
import com.company.rioc.ack999.STTransactionSetHeader;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;
//import java.io.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.company.rioc.ack999.AK1FunctionalGroupResponseHeader;
import com.company.rioc.ack999.AK2TransactionSetResponseHeader2000;
import com.company.rioc.ack999.AK9FunctionalGroupResponseTrailer;
import com.company.rioc.ack999.Loop2000;
import com.company.rioc.ack999.IK5TransactionSetResponseTrailer2000;
import com.company.rioc.ack999.SETransactionSetTrailer;

public class AckReadWriter {
	
	public static String x12278controlnum = "121901";
	public static String x12275controlnum = "104142";
	public static String x12999controlnum = "000000001";
	public static String x12999grpnum = "1";

	public String writeAck(boolean bX12278, String senderEDIID, String rcvrEDIID) {
		String edi = null;
		X12005010X231A1999A1 ack999 = new X12005010X231A1999A1();
		STTransactionSetHeader stheader = new STTransactionSetHeader();
		stheader.setST01TransactionSetIdentifierCode("999");
		stheader.setST02TransactionSetControlNumber("0001");
		stheader.setST03ImplementationConventionReference("005010X231A1");
		ack999.setSTTransactionSetHeader(stheader);
		AK1FunctionalGroupResponseHeader akheader = new AK1FunctionalGroupResponseHeader();
		if(bX12278) {
		akheader.setAK101FunctionalIdentifierCode("HI"); //replace it for 278 or 275
		akheader.setAK102GroupControlNumber(x12278controlnum);
		}else {
			akheader.setAK101FunctionalIdentifierCode("PI"); //replace it for 278 or 275
			akheader.setAK102GroupControlNumber(x12275controlnum);
		}
		akheader.setAK103VersionReleaseOrIndustryIdentifierCode("005010X217");
		ack999.setAK1FunctionalGroupResponseHeader(akheader);
		Loop2000 loop2000 = new Loop2000();
		AK2TransactionSetResponseHeader2000 respheaderloop = new AK2TransactionSetResponseHeader2000();
		if(bX12278) {
		respheaderloop.setAK201TransactionSetIdentifierCode("278");
		respheaderloop.setAK202TransactionSetControlNumber(x12278controlnum);
		}else {
			respheaderloop.setAK201TransactionSetIdentifierCode("275");
			respheaderloop.setAK202TransactionSetControlNumber(x12275controlnum);
		}
		respheaderloop.setAK203ImplementationConventionReference("005010X217");
		loop2000.setAK2TransactionSetResponseHeader2000(respheaderloop);
		// loop2000.getLoop2100().add(e) add loop2100 in case of errors
		IK5TransactionSetResponseTrailer2000 ik5tranxset = new IK5TransactionSetResponseTrailer2000();
		ik5tranxset.setIK501TransactionSetAcknowledgmentCode("A"); // replace it based on response to be sent
		loop2000.setIK5TransactionSetResponseTrailer2000(ik5tranxset);
		ack999.getLoop2000().add(loop2000);
		AK9FunctionalGroupResponseTrailer ak9segment = new AK9FunctionalGroupResponseTrailer();
		ak9segment.setAK901FunctionalGroupAcknowledgeCode("A");// replace based on error or success
		ak9segment.setAK902NumberOfTransactionSetsIncluded("1");// replace based on number of trans sets
		ak9segment.setAK903NumberOfReceivedTransactionSets("1");// replace based on received tran sets
		ak9segment.setAK904NumberOfAcceptedTransactionSets("1"); // replace based on accepted trans sets
		ack999.setAK9FunctionalGroupResponseTrailer(ak9segment);
		SETransactionSetTrailer setrailer = new SETransactionSetTrailer();
		setrailer.setSE01NumberOfIncludedSegments("6");
		setrailer.setSE02TransactionSetControlNumber("001");
		ack999.setSETransactionSetTrailer(setrailer);
		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(X12005010X231A1999A1.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Print XML String to Console
			StringWriter sw = new StringWriter();
			// subelement
			// Write XML to StringWriter
			jaxbMarshaller.marshal(ack999, sw);
			String xmlContent = sw.toString();
			System.out.println("XML string is" + xmlContent);
			/*
			 * DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			 * DocumentBuilder builder = factory.newDocumentBuilder(); ByteArrayInputStream
			 * input = new ByteArrayInputStream( xmlContent.getBytes("UTF-8")); Document doc
			 * = builder.parse(input);
			 */
			// doc.getDocumentElement().normalize();
			XMLtoEDIWriter ediwriterobj = new XMLtoEDIWriter();
			 String Header = "";
	            String Trailer = "";
	            String senderID = senderEDIID;
	            String origsenderID = senderID;
	            while(senderID.length() < 15) {
	            	senderID = senderID + " ";
	            }
	            String receiverID = rcvrEDIID;
	            String origreceiverID = receiverID; 
	            while(receiverID.length() < 15) {
	            	receiverID = receiverID + " ";
	            }
	          
	            Date currdate = Calendar.getInstance().getTime();
	            DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
	            String currstrDate = dateFormatter.format(currdate);
	            
	             Header = getHeader(origsenderID,senderID,origreceiverID,receiverID,currstrDate);
	             Trailer = getTrailer();
			 edi = ediwriterobj.WriteXMLtoEDI(xmlContent,Header,Trailer);
			System.out.println("EDI String is " + edi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edi;
	}
	  public String getHeader(String senderIDorig, String senderID, String recvIDorig, String recvID, String Date) {
	        Date date = Calendar.getInstance().getTime();
	        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
	        String strDate = dateFormat.format(date); 
	        DateFormat timeFormat = new SimpleDateFormat("HHmm");
	        String strtime = timeFormat.format(date); 
	        DateFormat fulltimeFormat = new SimpleDateFormat("HHmmssSS");
	        String strfulltime = fulltimeFormat.format(date); 
	    	String header = "ISA*00*          *00*          *ZZ*"+senderID+"*ZZ*"+recvID+"*"+strDate+"*"+strtime+"*+*00501*"+x12999controlnum+"*0*T*:~\n" + 
	            		"GS*HI*"+senderID+"*"+recvID+"*"+Date+"*"+strtime+"*"+x12999grpnum+"*X*005010X231A1~\n";
	            return header; //replace T to P in prod environment
	    }
	    public String getTrailer() {
	    	String trailer = "GE*1*"+x12999grpnum+"~\n"+
	    			"IEA*1*"+x12999controlnum+"~\n";
	            return trailer;
	    }

	public EDIErrorInfo parse999EDI(String Text) {
		StringTokenizer st1 = new StringTokenizer(Text, System.getProperty("line.separator"));

		EDIErrorInfo edierr = new EDIErrorInfo();
		ArrayList<SegLvlErrInfo> errors = new ArrayList<>();

		SegLvlErrInfo errorInfo = null;
		while (st1.hasMoreTokens()) {
			String currSegment = st1.nextToken();
			if (currSegment.startsWith("ISA") || currSegment.startsWith("GS") || currSegment.startsWith("ST")
					|| currSegment.startsWith("SE") || currSegment.startsWith("GE") || currSegment.startsWith("IEA")
					|| currSegment.startsWith("AK1")) {
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
    						edierr.setSenderId(trnToken);
    					else if(count == 4)
    						edierr.setRcvrId(trnToken);
    				}
             }
				
			} else if (currSegment.startsWith("IK3")) {
				int len = currSegment.length();
				String trnStr = currSegment.substring(0, len - 1);
				System.out.println("Segment str is" + trnStr);
				StringTokenizer trnStrTokenizer = new StringTokenizer(trnStr, "*");
				if (errorInfo != null) {
					errors.add(errorInfo);
				}
				errorInfo = new SegLvlErrInfo();

				int count = 0;
				while (trnStrTokenizer.hasMoreTokens()) {
					String trnToken = trnStrTokenizer.nextToken();
					count++;
					if (count == 2) { // segment info
						errorInfo.setSegmentInfo(trnToken);
						
					} else if (count == 3) { // segment position in transaction set

					} else if (count == 5) { // error code // get description from hash table
						IK3ErrorCodes errdes = IK3ErrorCodes.valueOf("ERR" + trnToken);
						errorInfo.setSegerrorcode(trnToken);
						errorInfo.setSegerror(errdes.getDescription());
						
						System.out.println(errdes.getDescription());

					}
				}

			}else if (currSegment.startsWith("CTX")) {
				int len = currSegment.length();
				String trnStr = currSegment.substring(0, len - 1);
				System.out.println("Segment str is" + trnStr);
				StringTokenizer trnStrTokenizer = new StringTokenizer(trnStr, "*");
				int count = 0;
				while (trnStrTokenizer.hasMoreTokens()) {
					String trnToken = trnStrTokenizer.nextToken();
					count++;
					
					if (count == 2)
						errorInfo.setErrorCntext(trnToken);

				}

			} else if (currSegment.startsWith("IK4")) {
				ElemLvlErrInfo elemerr = new ElemLvlErrInfo();
				int len = currSegment.length();
				String trnStr = currSegment.substring(0, len - 1);
				System.out.println("Segment str is" + trnStr);
				StringTokenizer trnStrTokenizer = new StringTokenizer(trnStr, "*");
				int count = 0;
				while (trnStrTokenizer.hasMoreTokens()) {
					String trnToken = trnStrTokenizer.nextToken();
					count++;

					if (count == 4) {
						elemerr.setElemerrcode(trnToken);
						
						Ik4ErrorCodes errdes = Ik4ErrorCodes.valueOf("ERR" + trnToken);
						elemerr.setElemerrdesp(errdes.getDescription());
						System.out.println(errdes.getDescription());
						
					} else if (count == 5) {
						elemerr.setElemerrval(trnToken);
						errorInfo.getElemerrs().add(elemerr);
					}

				}

			} else if (currSegment.startsWith("IK5")) {

				int len = currSegment.length();
				String trnStr = currSegment.substring(0, len - 1);
				System.out.println("Segment str is" + trnStr);
				StringTokenizer trnStrTokenizer = new StringTokenizer(trnStr, "*");
				int count = 0;
			    
				if (errorInfo != null)
					errors.add(errorInfo);
				
          
				edierr.setSegerrs(errors);

				while (trnStrTokenizer.hasMoreTokens()) {

					String trnToken = trnStrTokenizer.nextToken();
					count++;

					if (count == 2) {
						String status = "";
						switch (trnToken) {
						case "A":
							status = "Accepted";
							break;
						case "E":
							status = "Accepted But Errors Were Noted";
							break;
						case "W":
							status = "Rejected, Assurance Failed Validity Tests";
							break;
						case "R":
							status = "Rejected";
							break;
						case "M":
							status = "Rejected, Message Authentication Code (MAC) Failed";
							break;
						case "X":
							status = "Rejected, Content After Decryption Could Not Be Analyzed";
							break;
						}
						edierr.setStatus(status);
					} else if (count == 3) {
						edierr.setErrorCode(trnToken);
						IK5ErrorCodes errdes = IK5ErrorCodes.valueOf("ERR" + trnToken);
						edierr.setErrorDesp(errdes.getDescription());
						System.out.println(errdes.getDescription());
					}

				}
			}
		}
		return edierr;

	}
}
