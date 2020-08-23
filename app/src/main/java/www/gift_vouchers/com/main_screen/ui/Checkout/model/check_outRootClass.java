package www.gift_vouchers.com.main_screen.ui.Checkout.model;//
//  check_outRootClass.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 25, 2020

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class check_outRootClass{

	@SerializedName("body")
	private check_outBody body;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setBody(check_outBody body){
		this.body = body;
	}
	public check_outBody getBody(){
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
	public check_outRootClass(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		status = jsonObject.optInt("status");
		body = new check_outBody(jsonObject.optJSONObject("body"));
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