package com.mettles.rioc.x12;


import java.util.Hashtable;

//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.*;

import java.io.StringWriter;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.UUID;
import java.io.File;



public class X12Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		X12278EDIReadWriter testobj = new X12278EDIReadWriter();
		Hashtable<String,String> hhHastable = new Hashtable<String,String>();
		hhHastable.put("AttendingProviderEntityIdentCode","71");
		hhHastable.put("FacilityProviderEntityIdentCode","FA");
		hhHastable.put("OrderingProviderEntityIdentCode","DK");
		hhHastable.put("ServiceProviderEntityIdentCode","S");
		hhHastable.put("ReferringProviderEntityIdentCode","DN");
		hhHastable.put("OperatingPhysicianEntityIdentCode","72");
	//	File file = new File("C://Users//paddu//Downloads//mettle//mettle//CGS_0702//hhh_testdata.xlsx");
		//processFile(file,hhHastable);
		//testobj.EDIWriter(hhHastable,"","","");
		//testobj.
		String EDIText = "ISA*00*          *00*          *ZZ*111222001      *ZZ*360373         *190718*1938*^*00501*000000038*0*T*+~\r\n" + 
				"GS*HI*111222001*360373*20190718*19385828*121901*X*005010X217~\r\n" + 
				"ST*278*121901*005010X217~\r\n" + 
				"BHT*0007*11*000121901*20190718*19385828*18~\r\n" + 
				"HL*1**20*1~\r\n" + 
				"NM1*X3*2*CGS*****PI*15004~\r\n" + 
				"HL*2*1*21*1~\r\n" + 
				"NM1*1P*2*Unitypoint at Home*****XX*1427351956~\r\n" + 
				"HL*3*2*22*1~\r\n" + 
				"NM1*IL*1*Martin*Suzan****MI*513325515C1~\r\n" + 
				"HL*4*3*EV*1~\r\n" + 
				"TRN*1*FSP000000008824*9ESMDSYSTM~\r\n" + 
				"UM*HS*I**32+A~\r\n" + 
				"HCR*A2*15H00000000336~\r\n" + 
				"HI*ABK+R130~\r\n" + 
				"MSG*HH07F~\r\n" + 
				"NM1*DK*1*Weber*Kathleen****XX*1285612259~\r\n" + 
				"NM1*FA*2*Unitypoint at Home*****XX*1427351956~\r\n" + 
				"NM1*71*1*Weber*Kathleen****XX*1285612259~\r\n" + 
				"HL*5*4*SS*0~\r\n" + 
				"HCR*A1*15H00000000336~\r\n" + 
				"DTP*472*RD8*20190201-20190401~\r\n" + 
				"SV2**HC+G0299**UN*10~\r\n" + 
				"HL*6*4*SS*0~\r\n" + 
				"HCR*A3~\r\n" + 
				"REF*NT*15H00000000336~\r\n" + 
				"DTP*472*RD8*20190201-20190401~\r\n" + 
				"SV2**HC+G0156**UN*4~\r\n" + 
				"MSG*HH07F~\r\n" + 
				"SE*28*121901~\r\n" + 
				"GE*1*121901~\r\n" + 
				"IEA*1*000000038~";
		X12278EDIResponse response = testobj.parse278ResponseEDI(EDIText);
		System.out.println(""+response.getClienttransid());
		System.out.println(""+response.getEsmdtransid());
		if(response.getMessage() != null)
			System.out.println(""+response.getMessage());
		
		System.out.println(""+response.getConsolidated_hcr().getAction_code());
		System.out.println(""+response.getConsolidated_hcr().getReason_code());
		System.out.println(""+response.getConsolidated_hcr().getUtn_value());
		
		if(response.getService_healthcare().size() > 0)
		{
			Iterator<HealthCareReviewInfo> ss_hcrit = response.getService_healthcare().iterator();
			while(ss_hcrit.hasNext())
			{
				HealthCareReviewInfo temp = ss_hcrit.next();
				System.out.println(temp.getAction_code());
				System.out.println(temp.getReason_code());
				System.out.println(temp.getSrv_message());
				System.out.println(temp.getUtn_value());
				System.out.println(temp.getService_code());
			}
		}
		

		AckReadWriter ackwriter = new AckReadWriter();
		ackwriter.writeAck(true, "360373", "111222001");
		String EDIErr = "ISA*00*          *00*          *ZZ*111222001      *ZZ*360373         *190716*1734*^*00501*000000001*0*P*:~\r\n" + 
				"GS*FA*111222001*360373*20190716*173401*1*X*005010X231A1~\r\n" + 
				"ST*999*0001*005010X231A1~\r\n" + 
				"AK1*HI*121901*005010X217~\r\n" + 
				"AK2*278*121901*005010X217~\r\n" + 
				"IK3*HI*18*2000*8~\r\n" + 
				"CTX*SUBSCRIBER NAME NM109:1~\r\n" + 
				"IK4*2:1*1270*7*ABK~\r\n" + 
				"IK3*PWK*19*2000*8~\r\n" + 
				"CTX*SUBSCRIBER NAME NM109:1~\r\n" + 
				"IK4*6*67*6*CGSHBOSPRACHATACHCNTLNJL1VA07154~\r\n" + 
				"IK5*R*5~\r\n" + 
				"AK9*R*1*1*0~\r\n" + 
				"SE*12*0001~\r\n" + 
				"GE*1*1~\r\n" + 
				"IEA*1*000000001~";
		EDIErrorInfo result = ackwriter.parse999EDI(EDIErr);
		System.out.println("Status is "+result.getStatus() + " error code is "+result.getErrorCode() + " error description is "+result.getErrorDesp());
		if(result.getSegerrs() != null) {
			Iterator<SegLvlErrInfo> segit = result.getSegerrs().iterator();
			while(segit.hasNext()) {
				
				SegLvlErrInfo  segerr = segit.next();
				System.out.println("Error in Segment "+segerr.getSegmentInfo()+" Context is"+ segerr.getErrorCntext()+" Error code is "+segerr.getSegerrorcode()+" Error desp is "+segerr.getSegerror());
				Iterator<ElemLvlErrInfo> elemlvlIt =  segerr.getElemerrs().iterator();
				while(elemlvlIt.hasNext()) {
					ElemLvlErrInfo eleminfo = elemlvlIt.next();
					System.out.println("Wrong elem is "+eleminfo.getElemerrval()+" Error code is "+eleminfo.getElemerrcode()+" Error Description is "+eleminfo.getElemerrdesp());
				}
			}
		}
		X12275EDIWriter x12275ediwrtr = new X12275EDIWriter();
		x12275ediwrtr.CreateEDI(); 
		X12275314EDIWriter x12275pwk = new X12275314EDIWriter();
		UnSolicitedPWKElems temp = new UnSolicitedPWKElems();
		x12275pwk.CreateEDI(temp,"","");
		//System.out.println(UUID.randomUUID().toString());
	}

/*	 public static Hashtable<String,String> processFile(File file, Hashtable<String,String> inputVals) {
	        // import data
	        // Creating a Workbook from an Excel file (.xls or .xlsx)
	        try {
	            System.out.println("Entering into process file");
	            Workbook workbook = WorkbookFactory.create(file,"esmdrelease");

	            // Retrieving the number of sheets in the Workbook
	            System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
	            Sheet sheet = workbook.getSheetAt(4);

	            // Create a DataFormatter to format and get each cell's value as String
	            DataFormatter dataFormatter = new DataFormatter();

	            // 1. You can obtain a rowIterator and columnIterator and iterate over them
	            System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
	            Iterator<Row> rowIterator = sheet.rowIterator();
	            while (rowIterator.hasNext()) {
	                Row row = rowIterator.next();
                    
	                // Now let's iterate over the columns of the current row
	                Iterator<Cell> cellIterator = row.cellIterator();
	                
                   int count = 0;
                   String key = "", value = "";
	                while (cellIterator.hasNext()) {
	                    Cell cell = cellIterator.next();
	                    String cellValue = dataFormatter.formatCellValue(cell);
	                    String trimmerVal = cellValue.trim();
	                //    System.out.print(trimmerVal + "\t");
	                    if(count == 0) {
	                    	key = trimmerVal;
	                    }else {
	                    	value = trimmerVal;
	                    }
	                    count++;
	                }
	                if(!value.equals("")) {
	                 inputVals.put(key, value);
	                System.out.println("key is"+key+"value is"+value);
	                }
	            }

	        }catch(Exception e){
	          e.printStackTrace();
	        }
	        return inputVals;
	    }*/
	

}
