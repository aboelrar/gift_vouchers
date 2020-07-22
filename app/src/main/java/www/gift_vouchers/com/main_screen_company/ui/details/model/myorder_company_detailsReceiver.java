package www.gift_vouchers.com.main_screen_company.ui.details.model;//
//  myorder_company_detailsReceiver.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 20, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class myorder_company_detailsReceiver{

	@SerializedName("email")
	private String email;
	@SerializedName("id")
	private int id;
	@SerializedName("notes")
	private String notes;
	@SerializedName("phone")
	private Object phone;
	@SerializedName("username")
	private String username;

	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setNotes(String notes){
		this.notes = notes;
	}
	public String getNotes(){
		return this.notes;
	}
	public void setPhone(Object phone){
		this.phone = phone;
	}
	public Object getPhone(){
		return this.phone;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public myorder_company_detailsReceiver(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		email = jsonObject.optString("email");
		notes = jsonObject.optString("notes");
		username = jsonObject.optString("username");
		id = jsonObject.optInt("id");
		phone = jsonObject.optString("phone");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("email", email);
			jsonObject.put("id", id);
			jsonObject.put("notes", notes);
			jsonObject.put("phone", phone);
			jsonObject.put("username", username);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}