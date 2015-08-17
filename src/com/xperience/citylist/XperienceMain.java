package com.xperience.citylist;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.xperience.citylist.businesslayer.*;
import com.xperience.citylist.datalayer.Database;
import com.xperience.citylist.supplier.GrabSupplierList;
import com.xperience.citylist.supplier.HotelBedsCityListParse;
import com.xperience.citylist.supplier.TouricoHolidaysCityListParse;

public class XperienceMain {
	  public static void main(String[] args) throws IOException, SQLException {
		
		GrabSupplierList THCL = new TouricoHolidaysCityListParse();
		GrabSupplierList HBCL = new HotelBedsCityListParse();
		
		THCL.ExcelFilePath("/Users/hilaljaffan/Downloads/Tourico.xls");  
	    HBCL.ExcelFilePath("/Users/hilaljaffan/Downloads/Destinations_ING.xls");
	    
	    CityListMergeActivity MCL = new MergeLists();
	    
	    ArrayList<XperienceCityList> TouricoCityList = THCL.GetSupplierCityList();
	    ArrayList<XperienceCityList> HotelBedsCityList = HBCL.GetSupplierCityList();
	    
	    ArrayList<XperienceCityList> CommonList = MCL.CommonList(HotelBedsCityList, TouricoCityList);
	    ArrayList<XperienceCityList> FinalList = MCL.MergeCommonWithRaw(HotelBedsCityList, TouricoCityList, CommonList);
	    

	    Database db = new Database();
	    db.SaveCityList(FinalList);
	  }
}
