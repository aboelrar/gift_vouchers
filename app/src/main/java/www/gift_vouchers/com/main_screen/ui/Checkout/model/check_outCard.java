package www.gift_vouchers.com.main_screen.ui.Checkout.model;//
//  check_outCard.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 25, 2020

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class check_outCard{

	@SerializedName("description")
	private Object description;
	@SerializedName("extension")
	private String extension;
	@SerializedName("id")
	private int id;
	@SerializedName("name")
	private String name;
	@SerializedName("path")
	private String path;
	@SerializedName("type")
	private String type;

	public void setDescription(Object description){
		this.description = description;
	}
	public Object getDescription(){
		return this.description;
	}
	public void setExtension(String extension){
		this.extension = extension;
	}
	public String getExtension(){
		return this.extension;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setPath(String path){
		this.path = path;
	}
	public String getPath(){
		return this.path;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public check_outCard(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		extension = jsonObject.optString("extension");
		name = jsonObject.optString("name");
		path = jsonObject.optString("path");
		type = jsonObject.optString("type");
		description = jsonObject.optString("description");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("description", description);
			jsonObject.put("extension", extension);
			jsonObject.put("id", id);
			jsonObject.put("name", name);
			jsonObject.put("path", path);
			jsonObject.put("type", type);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}