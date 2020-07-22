package www.gift_vouchers.com.main_screen.ui.compainies.model;//
//  CompaniniesCategory.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 12, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class CompaniniesCategory{

	@SerializedName("category")
	private String category;
	@SerializedName("company_id")
	private String companyId;
	@SerializedName("id")
	private int id;
	@SerializedName("percentage")
	private String percentage;
	@SerializedName("price")
	private String price;

	public void setCategory(String category){
		this.category = category;
	}
	public String getCategory(){
		return this.category;
	}
	public void setCompanyId(String companyId){
		this.companyId = companyId;
	}
	public String getCompanyId(){
		return this.companyId;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setPercentage(String percentage){
		this.percentage = percentage;
	}
	public String getPercentage(){
		return this.percentage;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return this.price;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public CompaniniesCategory(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		category = jsonObject.optString("category");
		companyId = jsonObject.optString("company_id");
		percentage = jsonObject.optString("percentage");
		price = jsonObject.optString("price");
		id = jsonObject.optInt("id");
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("category", category);
			jsonObject.put("company_id", companyId);
			jsonObject.put("id", id);
			jsonObject.put("percentage", percentage);
			jsonObject.put("price", price);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}