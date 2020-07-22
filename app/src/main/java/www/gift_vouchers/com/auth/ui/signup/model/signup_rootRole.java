package www.gift_vouchers.com.auth.ui.signup.model;//
//  signup_rootRole.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 11, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class signup_rootRole{

	@SerializedName("id")
	private int id;
	@SerializedName("role")
	private String role;

	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setRole(String role){
		this.role = role;
	}
	public String getRole(){
		return this.role;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public signup_rootRole(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		role = jsonObject.optString("role");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("id", id);
			jsonObject.put("role", role);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}