package www.gift_vouchers.com.auth.ui.login.model;//
//  login_rootRootClass.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 11, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class login_rootRootClass{

	@SerializedName("body")
	private login_rootBody body;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setBody(login_rootBody body){
		this.body = body;
	}
	public login_rootBody getBody(){
		return this.body;
	}
	public void setMessage(String message){
		this.message = message;
	}
	public String getMessage(){
		return this.message;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public int getStatus(){
		return this.status;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public login_rootRootClass(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		status = jsonObject.optInt("status");
		body = new login_rootBody(jsonObject.optJSONObject("body"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("message", message);
			jsonObject.put("status", status);
			jsonObject.put("body", body.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}