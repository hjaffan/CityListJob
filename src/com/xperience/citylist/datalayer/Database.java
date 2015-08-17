package com.xperience.citylist.datalayer;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.xperience.citylist.XperienceCityList;

public class Database {

	Connection conn = null;
//	private String URL = "jdbc:sqlserver://rm7fxe2rir.database.windows.net:1433";
//	private String UN = "XP_Admin@rm7fxe2rir";
//	private String PW = "Adm!n1234";
	
	public Connection Conn() throws ClassNotFoundException, SQLException{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://rm7fxe2rir.database.windows.net:1433;database=XperienceDev;user=XP_Admin@rm7fxe2rir;password=Adm!n1234;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
		return conn;
	}
	
	public void SaveCityList(ArrayList<XperienceCityList> NewCityList) throws SQLException{
		
		if(conn == null){
			String Sql = null;
			try {
				String TouricoCode = null;
				String HotelBedsCode = null;
				String Language = "ENG";
				String Destination = null;
				String Country = null;
				
				conn = Conn();
				Statement sta = conn.createStatement();
				for(int i = 0; i < NewCityList.size() ; i++){
				
				if(NewCityList.get(i).TouricoCode.equals(null) ||NewCityList.get(i).TouricoCode.equals(""))
					TouricoCode = " ";
				else
					TouricoCode = NewCityList.get(i).TouricoCode;
				
				if(NewCityList.get(i).HotelBedsCode.equals(null) || NewCityList.get(i).HotelBedsCode.equals(""))
					HotelBedsCode = " ";
				else	
					HotelBedsCode = NewCityList.get(i).HotelBedsCode;
				
				if(TouricoCode.equals(" ")){
					if(NewCityList.get(i).State.equals("")){
						Destination = Split(NewCityList.get(i).SubCity) + " - " + Split(NewCityList.get(i).City);
					}else{
						Destination = Split(NewCityList.get(i).SubCity) + " - " + Split(NewCityList.get(i).City) + ", " + NewCityList.get(i).State;
					}
				}else if (HotelBedsCode.equals(" ")){
					if(NewCityList.get(i).State.equals("")){
						Destination = Split(NewCityList.get(i).City);
					}else{
						Destination = Split(NewCityList.get(i).City) + ", " + NewCityList.get(i).State;
					}
				}else
				{
					if(NewCityList.get(i).State.equals("")){
						Destination = Split(NewCityList.get(i).City);
					}else{
						Destination = Split(NewCityList.get(i).City) + ", " + NewCityList.get(i).State;
					}
				}
					
				Country = Split(NewCityList.get(i).Country);
				Sql = "INSERT INTO dbo.NewDestination_old1 (destinationCodeTourico, destinationCodeBed, language, destination, country) VALUES (" +"'" + TouricoCode +"','" + HotelBedsCode +"','" +Language + "','"+ Destination +"','" + Country +"');";
				sta.execute(Sql);
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally{
				System.out.println(Sql);
				conn.close();
			}
		}

		}
	
	private String Split (String City){
		String[] NewCity = new String [6];
		NewCity = City.split("'");
		if(NewCity.length > 1){
			return NewCity[0] + NewCity[1];
		}
		else{
			return City;
		}
		
	}
}
