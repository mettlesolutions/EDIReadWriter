package com.mettles.rioc.x12;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.ArrayList;

import org.databene.commons.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLtoEDIWriter {
	
	   private static String  XML2EDI = "";
	   private static int segmentcount = 0;
	
	   
	   public String Write275XMLtoEDI(String XMLString) {
		   Document doc = XMLUtil.parseString(XMLString);
           Element root = doc.getDocumentElement();
           Element[] x12IntchngNodes = XMLUtil.getChildElements(root);
           segmentcount = 0;
           XML2EDI = "";
           
           Element[] mainNodes = XMLUtil.getChildElements(x12IntchngNodes[0]);
           for(int idx = 0; idx < mainNodes.length; idx++ ) {
        	   Element childList = mainNodes[idx];
        	   if(childList.getTagName().startsWith("ISA_InterchangeControl")) {
        		     Element[] attrNodeMap =  XMLUtil.getChildElements(childList);

                     System.out.println("Node name is"+childList.getTagName());
                     String elemTagName = childList.getTagName().substring(0,childList.getTagName().indexOf("_"));
                     XML2EDI = XML2EDI + elemTagName + "*";
                     for (int k = 0; k < attrNodeMap.length; k++){
                         Element childelement = attrNodeMap[k];
                        // String temp = XMLUtil.getAttribute(childList,childelement.getTagName(),true);
                         NodeList templist = childList.getElementsByTagName(childelement.getTagName());
                         System.out.println("Size of templist "+templist.getLength());
                         String temp;
                    
                          temp = templist.item(0).getTextContent();
                        
                         System.out.println("attr name is"+childelement.getTagName());
                         if(k== attrNodeMap.length-1) {
                         	 XML2EDI = XML2EDI + temp ;
                         }else
                         XML2EDI = XML2EDI + temp + "*";
                     }
                     XML2EDI = XML2EDI + "~\n";
        		   
        	   }else if(childList.getTagName().startsWith("IEA_InterchangeControl")) {
        		     Element[] attrNodeMap =  XMLUtil.getChildElements(childList);

                     System.out.println("Node name is"+childList.getTagName());
                     String elemTagName = childList.getTagName().substring(0,childList.getTagName().indexOf("_"));
                     XML2EDI = XML2EDI + elemTagName + "*";
                     for (int k = 0; k < attrNodeMap.length; k++){
                         Element childelement = attrNodeMap[k];
                        // String temp = XMLUtil.getAttribute(childList,childelement.getTagName(),true);
                         NodeList templist = childList.getElementsByTagName(childelement.getTagName());
                         System.out.println("Size of templist "+templist.getLength());
                         String temp;
                    
                          temp = templist.item(0).getTextContent();
                        
                         System.out.println("attr name is"+childelement.getTagName());
                         if(k== attrNodeMap.length-1) {
                         	 XML2EDI = XML2EDI + temp ;
                         }else
                         XML2EDI = XML2EDI + temp + "*";
                     }
                     XML2EDI = XML2EDI + "~\n";
        	   }else if(childList.getTagName().startsWith("X12_FunctionalG")) {
        		   Element[] attrNodeMap =  XMLUtil.getChildElements(childList);
        		   for(int x12idx = 0; x12idx < attrNodeMap.length;x12idx++) {
        			   Element x12fungList = attrNodeMap[x12idx];
        			    if(x12fungList.getTagName().startsWith("GE_FunctionalGrou")) {
        			    	 Element[] attrNodeMapfung =  XMLUtil.getChildElements(x12fungList);

                             System.out.println("Node name is"+childList.getTagName());
                             String elemTagName = x12fungList.getTagName().substring(0,x12fungList.getTagName().indexOf("_"));
                             XML2EDI = XML2EDI + elemTagName + "*";
                             for (int k = 0; k < attrNodeMapfung.length; k++){
                                 Element childelement = attrNodeMapfung[k];
                                // String temp = XMLUtil.getAttribute(childList,childelement.getTagName(),true);
                                 NodeList templist = x12fungList.getElementsByTagName(childelement.getTagName());
                                 System.out.println("Size of templist "+templist.getLength());
                                 String temp;
                            
                                  temp = templist.item(0).getTextContent();
                                
                                 System.out.println("attr name is"+childelement.getTagName());
                                 if(k== attrNodeMapfung.length-1) {
                                 	 XML2EDI = XML2EDI + temp ;
                                 }else
                                 XML2EDI = XML2EDI + temp + "*";
                             }
                             XML2EDI = XML2EDI + "~\n";
        			    }else if(x12fungList.getTagName().startsWith("GS_FunctionalGrou")) {
        			    	 Element[] attrNodeMapfung =  XMLUtil.getChildElements(x12fungList);

                             System.out.println("Node name is"+childList.getTagName());
                             String elemTagName = x12fungList.getTagName().substring(0,x12fungList.getTagName().indexOf("_"));
                             XML2EDI = XML2EDI + elemTagName + "*";
                             for (int k = 0; k < attrNodeMapfung.length; k++){
                                 Element childelement = attrNodeMapfung[k];
                                // String temp = XMLUtil.getAttribute(childList,childelement.getTagName(),true);
                                 NodeList templist = x12fungList.getElementsByTagName(childelement.getTagName());
                                 System.out.println("Size of templist "+templist.getLength());
                                 String temp;
                            
                                  temp = templist.item(0).getTextContent();
                                
                                 System.out.println("attr name is"+childelement.getTagName());
                                 if(k== attrNodeMapfung.length-1) {
                                 	 XML2EDI = XML2EDI + temp ;
                                 }else
                                 XML2EDI = XML2EDI + temp + "*";
                             }
                             XML2EDI = XML2EDI + "~\n";
        			    }else if(x12fungList.getTagName().startsWith("X12_Transaction")) {
        			    	  Element[] childNodes =  XMLUtil.getChildElements(x12fungList);
        			        for (int i = 0; i < childNodes.length; i++) {
        			               Element subchildList = childNodes[i];

        			                   if (subchildList.getTagName().startsWith("Loop")) {
        			                       iterateRecursive(subchildList);
        			                   }else{

        			                       Element[] subattrNodeMap =  XMLUtil.getChildElements(subchildList);

        			                       System.out.println("Node name is"+subchildList.getTagName());
        			                       String elemTagName = subchildList.getTagName().substring(0,subchildList.getTagName().indexOf("_"));
        			                       XML2EDI = XML2EDI + elemTagName + "*";
        			                       for (int k = 0; k < subattrNodeMap.length; k++){
        			                           Element childelement = subattrNodeMap[k];
        			                          // String temp = XMLUtil.getAttribute(childList,childelement.getTagName(),true);
        			                           NodeList templist = subchildList.getElementsByTagName(childelement.getTagName());
        			                           System.out.println("Size of templist "+templist.getLength());
        			                           String temp;
        			                           if(elemTagName.equals("SE") && k==0) {
        			                           	 temp = Integer.toString(segmentcount+1);
        			                           }else {
        			                            temp = templist.item(0).getTextContent();
        			                           }
        			                           System.out.println("attr name is"+childelement.getTagName());
        			                           if(k== subattrNodeMap.length-1) {
        			                           	 XML2EDI = XML2EDI + temp ;
        			                           }else
        			                           XML2EDI = XML2EDI + temp + "*";
        			                       }
        			                       XML2EDI = XML2EDI + "~\n";
        			                       segmentcount++;
        			                   }

        			           }
        			    }
        		   }
        			    
        	   }
   
           }
           
      
           
           return XML2EDI;
       
        
	   }

	    
	    public String WriteXMLtoEDI(String XMLString ,String Header, String Trailer) {
	    	   Document doc = XMLUtil.parseString(XMLString);
	            Element root = doc.getDocumentElement();
	            Element[] childNodes = XMLUtil.getChildElements(root);
	            segmentcount = 0;
	            XML2EDI = "";
	         
	           
	            for (int i = 0; i < childNodes.length; i++) {
	                Element childList = childNodes[i];

	                    if (childList.getTagName().startsWith("Loop")) {
	                        iterateRecursive(childList);
	                    }else{

	                        Element[] attrNodeMap =  XMLUtil.getChildElements(childList);

	                        System.out.println("Node name is"+childList.getTagName());
	                        String elemTagName = childList.getTagName().substring(0,childList.getTagName().indexOf("_"));
	                        XML2EDI = XML2EDI + elemTagName + "*";
	                        for (int k = 0; k < attrNodeMap.length; k++){
	                            Element childelement = attrNodeMap[k];
	                           // String temp = XMLUtil.getAttribute(childList,childelement.getTagName(),true);
	                            NodeList templist = childList.getElementsByTagName(childelement.getTagName());
	                            System.out.println("Size of templist "+templist.getLength());
	                            String temp;
	                            if(elemTagName.equals("SE") && k==0) {
	                            	 temp = Integer.toString(segmentcount+1);
	                            }else {
	                             temp = templist.item(0).getTextContent();
	                            }
	                            System.out.println("attr name is"+childelement.getTagName());
	                            if(k== attrNodeMap.length-1) {
	                            	 XML2EDI = XML2EDI + temp ;
	                            }else
	                            XML2EDI = XML2EDI + temp + "*";
	                        }
	                        XML2EDI = XML2EDI + "~\n";
	                        segmentcount++;
	                    }

	            }
	            
	            return Header+XML2EDI+Trailer;
	    }

	    public void iterateRecursive(Element LoopNode){
	        Element[] childList = XMLUtil.getChildElements(LoopNode);

	        for (int j = 0; j < childList.length; j++) {
	            Element childNode = childList[j];
	            if (childNode.getTagName().startsWith("Loop")) {
	                iterateRecursive(childNode);
	            }else{
	                Element[] attrNodeMap =  XMLUtil.getChildElements(childNode);
	              //  NamedNodeMap attrNodeMap = childNode.getAttributes();
	                System.out.println("Node name is from rec"+childNode.getTagName());
	                String elemTagName = childNode.getTagName().substring(0,childNode.getTagName().indexOf("_"));
	                XML2EDI = XML2EDI + elemTagName + "*";
	                for (int k = 0; k < attrNodeMap.length; k++){
	                    Element childelement = attrNodeMap[k];
	                    Element[] childattrNodeMap =  XMLUtil.getChildElements(childelement);
	                    String childnodestr = "";
	                    if(childattrNodeMap != null)
	                    {
	                    	
	                    	  for (int l = 0; l < childattrNodeMap.length; l++){
	                    		  if(childnodestr != "") {
	                    			  childnodestr =  childnodestr + ":"+ childattrNodeMap[l].getTextContent();
	                    		  }else {
	                    			  childnodestr = childattrNodeMap[l].getTextContent();
	                    		  }
	                    	  }
	                    	  if( childattrNodeMap.length != 0)
	                    	   XML2EDI = XML2EDI +childnodestr; 
	                    	  else {
	                    		 // XML2EDI = XML2EDI + childelement.getTextContent() + "*"; 
	                    		  if(k== attrNodeMap.length-1) {
		                            	 XML2EDI = XML2EDI + childelement.getTextContent() ;
		                            }else
		                            XML2EDI = XML2EDI + childelement.getTextContent() + "*";
	                    	  }
	                    }else {
	                    	  if(k== attrNodeMap.length-1) {
	                            	 XML2EDI = XML2EDI + childelement.getTextContent() ;
	                            }else
	                            XML2EDI = XML2EDI + childelement.getTextContent() + "*";
	                    }
	                    System.out.println("attr name is from rec"+childelement.getTagName());
	                    
	                 
	                }
	                XML2EDI = XML2EDI + "~\n";
	                segmentcount++;
	            }
	        }

	    }
	    
	 
}
