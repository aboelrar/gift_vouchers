package www.gift_vouchers.com.main_screen.ui.contact_us.model;//
//  Body.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on November 28, 2020

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;


public class Body{

	@SerializedName("address")
	private Object address;
	@SerializedName("email")
	private Object email;
	@SerializedName("id")
	private int id;
	@SerializedName("phone")
	private Object phone;
	@SerializedName("text")
	private String text;

	public void setAddress(Object address){
		this.address = address;
	}
	public Object getAddress(){
		return this.address;
	}
	public void setEmail(Object email){
		this.email = email;
	}
	public Object getEmail(){
		return this.email;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setPhone(Object phone){
		this.phone = phone;
	}
	public Object getPhone(){
		return this.phone;
	}
	public void setText(String text){
		this.text = text;
	}
	public String getText(){
		return this.text;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public Body(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		text = jsonObject.optString("text");
		address = jsonObject.optString("address");
		email = jsonObject.optString("email");
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
			jsonObject.put("address", address);
			jsonObject.put("email", email);
			jsonObject.put("id", id);
			jsonObject.put("phone", phone);
			jsonObject.put("text", text);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}