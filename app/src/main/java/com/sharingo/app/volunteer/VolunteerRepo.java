package com.sharingo.app.volunteer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;

import com.sharingo.app.DBConnect;

public class VolunteerRepo {

	private fetchType _FetchType;
	enum fetchType {ALL, ID};
	DBConnect dbConnect = new DBConnect();
	
	//Constructor
	public VolunteerRepo(){
		
	}
	
	public List<Volunteer> getVolunteerList(){
		this._FetchType = fetchType.ALL;
		return fetch();
	}
	
	public List<Volunteer> fetch(){
		
		Connection conn = dbConnect.getConnection();
		CallableStatement callableStatement = null;
		String getQueryStatement = null;
		
		List<Volunteer> volunteerList = new ArrayList<Volunteer>();
		
		if(conn != null){
			System.out.println("Connection was established sucessfully!");
		} else {
			System.out.println("Connection failed..");
		}
		
		try {
			
			switch(_FetchType){
			
			case ALL: getQueryStatement = "Select * from Volunteer";
						callableStatement = conn.prepareCall(getQueryStatement);
						break;				
			
			}
			
			ResultSet rs = callableStatement.executeQuery();
			
			while(rs.next()){
				Volunteer volunteer = new Volunteer();
				volunteer.setId(rs.getInt("ID"));
				volunteer.setName(rs.getString("Name"));
				volunteer.setContact(rs.getString("Contact"));
				
				volunteerList.add(volunteer);
			}
			
			
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
		return volunteerList;
	}
	
	public void saveVolunteer(JsonObject inputData){
		Connection conn = dbConnect.getConnection();
		CallableStatement callableStatement = null;
		String getQueryStatement = null;
		
		try {
			
			getQueryStatement = "call addVolunteer(?,?)";
			callableStatement = conn.prepareCall(getQueryStatement);
			callableStatement.setString(1, inputData.getString("name"));
			callableStatement.setString(2, inputData.getString("contact"));
			
			callableStatement.executeUpdate();
			
			System.out.println("Volunteer added!");
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
}
