package com.xperience.citylist.supplier;

import java.io.IOException;
import java.util.ArrayList;

import com.xperience.citylist.XperienceCityList;

public interface GrabSupplierList {

	ArrayList<XperienceCityList> GetSupplierCityList() throws IOException;
	void ExcelFilePath(String File);
}
