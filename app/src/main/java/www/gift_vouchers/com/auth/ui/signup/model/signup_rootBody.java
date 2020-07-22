package www.gift_vouchers.com.auth.ui.signup.model;//
//  signup_rootBody.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 11, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class signup_rootBody{

	@SerializedName("access_token")
	private String accessToken;
	@SerializedName("expires_at")
	private String expiresAt;
	@SerializedName("token_type")
	private String tokenType;
	@SerializedName("user")
	private signup_rootUser user;

	public void setAccessToken(String accessToken){
		this.accessToken = accessToken;
	}
	public String getAccessToken(){
		return this.accessToken;
	}
	public void setExpiresAt(String expiresAt){
		this.expiresAt = expiresAt;
	}
	public String getExpiresAt(){
		return this.expiresAt;
	}
	public void setTokenType(String tokenType){
		this.tokenType = tokenType;
	}
	public String getTokenType(){
		return this.tokenType;
	}
	public void setUser(signup_rootUser user){
		this.user = user;
	}
	public signup_rootUser getUser(){
		return this.user;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public signup_rootBody(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		accessToken = jsonObject.optString("access_token");
		expiresAt = jsonObject.optString("expires_at");
		tokenType = jsonObject.optString("token_type");
		user = new signup_rootUser(jsonObject.optJSONObject("user"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("access_token", accessToken);
			jsonObject.put("expires_at", expiresAt);
			jsonObject.put("token_type", tokenType);
			jsonObject.put("user", user.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}