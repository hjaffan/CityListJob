package com.xperience.citylist.businesslayer;

import java.util.ArrayList;
import com.xperience.citylist.XperienceCityList;

public class MergeLists implements CityListMergeActivity {
	
	public ArrayList<XperienceCityList> CommonList(ArrayList<XperienceCityList> HotelBeds,
			ArrayList<XperienceCityList> TouricoHolidays) {
		ArrayList<XperienceCityList> CommonList = new ArrayList<XperienceCityList>();
		// First we check which List is larger
		int CityCount = 0;
		if(HotelBeds.size() > TouricoHolidays.size()){
			// We choose the smaller list to be the one we loop to find cities in
			for(int i = 0; i < TouricoHolidays.size(); i ++){
				// We query the larger list until we find a match
				for(int j = 0; j < HotelBeds.size(); j ++){
					// We filter out first by country
					if(HotelBeds.get(j).Country.toLowerCase().contains(TouricoHolidays.get(i).Country.toLowerCase())){
						// First we need to check if we have similar states. This will mean that the city will go in
						// the correct state. If not, it will still pass since they might both be empty fields.
						// We will have the else class to handle the other cases.
						if(HotelBeds.get(j).State.toLowerCase().equals(TouricoHolidays.get(i).State.toLowerCase())){
							if((TouricoHolidays.get(i).City.toLowerCase().contains(HotelBeds.get(j).SubCity.toLowerCase()) )){//|| TouricoHolidays.get(i).City.toLowerCase().contains(HotelBeds.get(j).City.toLowerCase()))){
								CommonList.add(CityCount, new XperienceCityList(TouricoHolidays.get(i).City, "", TouricoHolidays.get(i).State, HotelBeds.get(j).Country, HotelBeds.get(j).HotelBedsCode, TouricoHolidays.get(i).TouricoCode));		
								j = HotelBeds.size() -1;
							}
						}
						//else if(TouricoHolidays.get(i).City.toLowerCase().contains(HotelBeds.get(j).SubCity.toLowerCase()) || TouricoHolidays.get(i).City.toLowerCase().contains(HotelBeds.get(j).City.toLowerCase())){
						//	// The Counter is reset after a match is found as to not waste time
						//	CommonList.add(CityCount, new XperienceCityList(TouricoHolidays.get(i).City, "", "", HotelBeds.get(j).Country, HotelBeds.get(j).HotelBedsCode, TouricoHolidays.get(i).TouricoCode));
						//	j = HotelBeds.size() -1;
						//}
					}
				}
			}
		}
		System.out.println(CommonList.size());
		return CommonList;
	}
	
	public ArrayList<XperienceCityList> MergeCommonWithRaw(ArrayList<XperienceCityList> HotelBeds,
			ArrayList<XperienceCityList> TouricoHolidays, ArrayList<XperienceCityList> CommonList){
		if(CommonList.size() < TouricoHolidays.size()){
			for(int i = 0; i < CommonList.size(); i++){
				for(int j = 0; j < TouricoHolidays.size(); j++){
					if(TouricoHolidays.get(j).TouricoCode.toLowerCase().contains(CommonList.get(i).TouricoCode.toLowerCase())){
						TouricoHolidays.remove(j);
						j = TouricoHolidays.size() - 1;
					}
				}
			}
		}
		if(CommonList.size() < HotelBeds.size()){
			for(int i = 0; i < CommonList.size(); i++){
				for(int j = 0; j < HotelBeds.size(); j++){
					if(HotelBeds.get(j).SubCity.toLowerCase().contains(CommonList.get(i).City.toLowerCase()) || 
							HotelBeds.get(j).HotelBedsCode.toLowerCase().contains(CommonList.get(i).HotelBedsCode.toLowerCase())){
						HotelBeds.remove(j);
						j = HotelBeds.size() - 1;
					}
				}
			}
		}
			System.out.println("Tourico Final Size : " + TouricoHolidays.size() + "\nHotelBeds Final Size : " + HotelBeds.size() + "\nCommon Final Size : "+ CommonList.size());
		CommonList.addAll(TouricoHolidays);
		CommonList.addAll(HotelBeds);
		System.out.println("Final Size of Common: " + CommonList.size());
		return CommonList;
	}
}
