package www.gift_vouchers.com.main_screen.ui.myorders.model;//
//  UserOrdersCompany.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 15, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class UserOrdersCompany{

	@SerializedName("birthdate")
	private String birthdate;
	@SerializedName("country")
	private String country;
	@SerializedName("description")
	private String description;
	@SerializedName("email")
	private String email;
	@SerializedName("gender")
	private Object gender;
	@SerializedName("id")
	private int id;
	@SerializedName("phone")
	private String phone;
	@SerializedName("picture")
	private String picture;
	@SerializedName("profit_share")
	private Object profitShare;
	@SerializedName("real_password")
	private String realPassword;
	@SerializedName("role_id")
	private String roleId;
	@SerializedName("suspend")
	private String suspend;
	@SerializedName("tax")
	private Object tax;
	@SerializedName("token")
	private Object token;
	@SerializedName("username")
	private String username;

	public void setBirthdate(String birthdate){
		this.birthdate = birthdate;
	}
	public String getBirthdate(){
		return this.birthdate;
	}
	public void setCountry(String country){
		this.country = country;
	}
	public String getCountry(){
		return this.country;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setGender(Object gender){
		this.gender = gender;
	}
	public Object getGender(){
		return this.gender;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
	}
	public void setPicture(String picture){
		this.picture = picture;
	}
	public String getPicture(){
		return this.picture;
	}
	public void setProfitShare(Object profitShare){
		this.profitShare = profitShare;
	}
	public Object getProfitShare(){
		return this.profitShare;
	}
	public void setRealPassword(String realPassword){
		this.realPassword = realPassword;
	}
	public String getRealPassword(){
		return this.realPassword;
	}
	public void setRoleId(String roleId){
		this.roleId = roleId;
	}
	public String getRoleId(){
		return this.roleId;
	}
	public void setSuspend(String suspend){
		this.suspend = suspend;
	}
	public String getSuspend(){
		return this.suspend;
	}
	public void setTax(Object tax){
		this.tax = tax;
	}
	public Object getTax(){
		return this.tax;
	}
	public void setToken(Object token){
		this.token = token;
	}
	public Object getToken(){
		return this.token;
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
	public UserOrdersCompany(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		birthdate = jsonObject.optString("birthdate");
		country = jsonObject.optString("country");
		description = jsonObject.optString("description");
		email = jsonObject.optString("email");
		phone = jsonObject.optString("phone");
		picture = jsonObject.optString("picture");
		realPassword = jsonObject.optString("real_password");
		roleId = jsonObject.optString("role_id");
		suspend = jsonObject.optString("suspend");
		username = jsonObject.optString("username");
		gender = jsonObject.optString("gender");
		id = jsonObject.optInt("id");
		profitShare = jsonObject.optString("profit_share");
		tax = jsonObject.optString("tax");
		token = jsonObject.optString("token");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("birthdate", birthdate);
			jsonObject.put("country", country);
			jsonObject.put("description", description);
			jsonObject.put("email", email);
			jsonObject.put("gender", gender);
			jsonObject.put("id", id);
			jsonObject.put("phone", phone);
			jsonObject.put("picture", picture);
			jsonObject.put("profit_share", profitShare);
			jsonObject.put("real_password", realPassword);
			jsonObject.put("role_id", roleId);
			jsonObject.put("suspend", suspend);
			jsonObject.put("tax", tax);
			jsonObject.put("token", token);
			jsonObject.put("username", username);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}