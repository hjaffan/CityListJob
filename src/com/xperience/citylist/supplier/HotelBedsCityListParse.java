package com.xperience.citylist.supplier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.xperience.citylist.XperienceCityList;
import com.xperience.citylist.businesslayer.USAStates;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class HotelBedsCityListParse implements GrabSupplierList{
	  private String inputFile;

	  public void ExcelFilePath(String inputFile) {
	    this.inputFile = inputFile;
	  }

	  public ArrayList<XperienceCityList> GetSupplierCityList() throws IOException  {
	    File inputWorkbook = new File(inputFile);
	    ArrayList<XperienceCityList> XCL = new ArrayList<XperienceCityList>();
	    
	    Workbook w;
	    try {
	      w = Workbook.getWorkbook(inputWorkbook);
	      // Get the first sheet
	      Sheet sheet = w.getSheet(0);
	      // Loop over first 10 column and lines
	      String Split = "-";
	      for (int j = 1; j < sheet.getRows(); j++) {
	    	  String[] CityState = new String[3];
	    	  CityState = sheet.getCell(3, j).getContents().split(Split);
	    	  String Country = sheet.getCell(1,j).getContents();
	    	  String HBCode = sheet.getCell(2,j).getContents();
	    	  String SubCity = sheet.getCell(7,j).getContents();
	    	  if(CityState.length < 2){
	    			  XCL.add(j-1, new XperienceCityList(CityState[0], SubCity, "", Country, HBCode, ""));
	    	  }else{
	    		  if(Country.toLowerCase().contains("usa")){
	    			  if(CityState.length > 2){
	    				  XCL.add(j-1, new XperienceCityList(CityState[0], SubCity, USAStates.valueOfAbbreviation(CityState[2].trim()).toString(), Country,  HBCode, ""));
	    			  }else{
	    				  XCL.add(j-1, new XperienceCityList(CityState[0], SubCity, USAStates.valueOfAbbreviation(CityState[1].trim()).toString(), Country,  HBCode, ""));
	    			  }
	    				  
	    		  }else
	    			  XCL.add(j-1, new XperienceCityList(CityState[0], SubCity, CityState[1], Country,  HBCode, ""));
	    	  }
	    	  
	    	  //System.out.println(XCL.get(j-1).City + " " + XCL.get(j-1).Country+ " " + XCL.get(j-1).HotelBedsCode+ " " + XCL.get(j-1).State);
	      }
	      
	      System.out.println(XCL.size());
	    } catch (BiffException e) {
	      e.printStackTrace();
	    }
	    return XCL;
	  }

}
