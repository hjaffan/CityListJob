package com.xperience.citylist.supplier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.xperience.citylist.XperienceCityList;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TouricoHolidaysCityListParse implements GrabSupplierList{
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
	      for (int j = 1; j < sheet.getRows(); j++) {
	    	  String City = sheet.getCell(2, j).getContents();
	    	  String Country = sheet.getCell(3,j).getContents();
	    	  String State = sheet.getCell(4,j).getContents().trim();
	    	  String THCode = sheet.getCell(0,j).getContents();
	    	  XCL.add(j-1, new XperienceCityList(City, "" , State , Country, "", THCode));
	      }
	      
	      System.out.println(XCL.size());	     
	    } catch (BiffException e) {
	      e.printStackTrace();
	    }
	    return XCL;
	  }
}
