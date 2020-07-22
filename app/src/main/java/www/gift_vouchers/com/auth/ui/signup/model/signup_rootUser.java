package www.gift_vouchers.com.auth.ui.signup.model;//
//  signup_rootUser.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 11, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class signup_rootUser{

	@SerializedName("birthdate")
	private Object birthdate;
	@SerializedName("country")
	private Object country;
	@SerializedName("description")
	private Object description;
	@SerializedName("email")
	private String email;
	@SerializedName("gender")
	private String gender;
	@SerializedName("id")
	private int id;
	@SerializedName("phone")
	private String phone;
	@SerializedName("picture")
	private Object picture;
	@SerializedName("profit_share")
	private Object profitShare;
	@SerializedName("real_password")
	private String realPassword;
	@SerializedName("role")
	private signup_rootRole role;
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

	public void setBirthdate(Object birthdate){
		this.birthdate = birthdate;
	}
	public Object getBirthdate(){
		return this.birthdate;
	}
	public void setCountry(Object country){
		this.country = country;
	}
	public Object getCountry(){
		return this.country;
	}
	public void setDescription(Object description){
		this.description = description;
	}
	public Object getDescription(){
		return this.description;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
	}
	public void setGender(String gender){
		this.gender = gender;
	}
	public String getGender(){
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
	public void setPicture(Object picture){
		this.picture = picture;
	}
	public Object getPicture(){
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
	public void setRole(signup_rootRole role){
		this.role = role;
	}
	public signup_rootRole getRole(){
		return this.role;
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
	public signup_rootUser(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		email = jsonObject.optString("email");
		gender = jsonObject.optString("gender");
		phone = jsonObject.optString("phone");
		realPassword = jsonObject.optString("real_password");
		roleId = jsonObject.optString("role_id");
		suspend = jsonObject.optString("suspend");
		username = jsonObject.optString("username");
		birthdate = jsonObject.optString("birthdate");
		country = jsonObject.optString("country");
		description = jsonObject.optString("description");
		id = jsonObject.optInt("id");
		picture = jsonObject.optString("picture");
		profitShare = jsonObject.optString("profit_share");
		tax = jsonObject.optString("tax");
		token = jsonObject.optString("token");
		role = new signup_rootRole(jsonObject.optJSONObject("role"));
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
			jsonObject.put("role", role.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}