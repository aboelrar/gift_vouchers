package www.gift_vouchers.com.main_screen.ui.select_gift_design.model;//
//  designsRootClass.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 14, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class designsRootClass{

	@SerializedName("body")
	private designsBody[] body;
	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private int status;

	public void setBody(designsBody[] body){
		this.body = body;
	}
	public designsBody[] getBody(){
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
	public designsRootClass(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		message = jsonObject.optString("message");
		status = jsonObject.optInt("status");
		JSONArray bodyJsonArray = jsonObject.optJSONArray("body");
		if(bodyJsonArray != null){
			ArrayList<designsBody> bodyArrayList = new ArrayList<>();
			for (int i = 0; i < bodyJsonArray.length(); i++) {
				JSONObject bodyObject = bodyJsonArray.optJSONObject(i);
				bodyArrayList.add(new designsBody(bodyObject));
			}
			body = (designsBody[]) bodyArrayList.toArray();
		}
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
			if(body != null && body.length > 0){
				JSONArray bodyJsonArray = new JSONArray();
				for(designsBody bodyElement : body){
					bodyJsonArray.put(bodyElement.toJsonObject());
				}
				jsonObject.put("body", bodyJsonArray);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}