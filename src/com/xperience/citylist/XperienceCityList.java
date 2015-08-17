package com.xperience.citylist;

public class XperienceCityList {

	public String City;
	public String SubCity;
	public String State;
	public String Country;
	public String HotelBedsCode;
	public String TouricoCode;
	
	public XperienceCityList(){}
	public XperienceCityList(String XCity, String XSubCity, String XState, String XCountry, String HBCode, String THCode){
		this.City = XCity;
		this.SubCity = XSubCity;
		this.State = XState;
		this.Country = XCountry;
		this.HotelBedsCode = HBCode;
		this.TouricoCode = THCode;
	}

}
