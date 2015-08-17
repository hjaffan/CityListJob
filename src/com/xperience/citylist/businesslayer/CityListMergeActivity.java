package com.xperience.citylist.businesslayer;

import java.util.ArrayList;
import com.xperience.citylist.*;

public interface CityListMergeActivity {

	ArrayList<XperienceCityList> CommonList(ArrayList<XperienceCityList> HotelBeds,
			ArrayList<XperienceCityList> TouricoHolidays);
	
	ArrayList<XperienceCityList> MergeCommonWithRaw(ArrayList<XperienceCityList> HotelBeds,
			ArrayList<XperienceCityList> TouricoHolidays, ArrayList<XperienceCityList> CommonList);
	
}
