package www.gift_vouchers.com.main_screen_company.ui.details.model;//
//  myorder_company_detailsBody.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 20, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class myorder_company_detailsBody{

	@SerializedName("card")
	private Object card;
	@SerializedName("category")
	private myorder_company_detailsCategory category;
	@SerializedName("category_id")
	private String categoryId;
	@SerializedName("category_price")
	private String categoryPrice;
	@SerializedName("company")
	private myorder_company_detailsCompany company;
	@SerializedName("company_id")
	private String companyId;
	@SerializedName("date")
	private Object date;
	@SerializedName("id")
	private int id;
	@SerializedName("order_number")
	private String orderNumber;
	@SerializedName("percentage")
	private String percentage;
	@SerializedName("price")
	private String price;
	@SerializedName("receiver")
	private myorder_company_detailsReceiver receiver;
	@SerializedName("total_price")
	private String totalPrice;
	@SerializedName("user_id")
	private String userId;

	public void setCard(Object card){
		this.card = card;
	}
	public Object getCard(){
		return this.card;
	}
	public void setCategory(myorder_company_detailsCategory category){
		this.category = category;
	}
	public myorder_company_detailsCategory getCategory(){
		return this.category;
	}
	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}
	public String getCategoryId(){
		return this.categoryId;
	}
	public void setCategoryPrice(String categoryPrice){
		this.categoryPrice = categoryPrice;
	}
	public String getCategoryPrice(){
		return this.categoryPrice;
	}
	public void setCompany(myorder_company_detailsCompany company){
		this.company = company;
	}
	public myorder_company_detailsCompany getCompany(){
		return this.company;
	}
	public void setCompanyId(String companyId){
		this.companyId = companyId;
	}
	public String getCompanyId(){
		return this.companyId;
	}
	public void setDate(Object date){
		this.date = date;
	}
	public Object getDate(){
		return this.date;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	public void setOrderNumber(String orderNumber){
		this.orderNumber = orderNumber;
	}
	public String getOrderNumber(){
		return this.orderNumber;
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
	public void setReceiver(myorder_company_detailsReceiver receiver){
		this.receiver = receiver;
	}
	public myorder_company_detailsReceiver getReceiver(){
		return this.receiver;
	}
	public void setTotalPrice(String totalPrice){
		this.totalPrice = totalPrice;
	}
	public String getTotalPrice(){
		return this.totalPrice;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getUserId(){
		return this.userId;
	}

	/**
	 * Instantiate the instance using the passed jsonObject to set the properties values
	 */
	public myorder_company_detailsBody(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		categoryId = jsonObject.optString("category_id");
		categoryPrice = jsonObject.optString("category_price");
		companyId = jsonObject.optString("company_id");
		orderNumber = jsonObject.optString("order_number");
		percentage = jsonObject.optString("percentage");
		price = jsonObject.optString("price");
		totalPrice = jsonObject.optString("total_price");
		userId = jsonObject.optString("user_id");
		card = jsonObject.optString("card");
		date = jsonObject.optString("date");
		id = jsonObject.optInt("id");
		category = new myorder_company_detailsCategory(jsonObject.optJSONObject("category"));
		company = new myorder_company_detailsCompany(jsonObject.optJSONObject("company"));
		receiver = new myorder_company_detailsReceiver(jsonObject.optJSONObject("receiver"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("card", card);
			jsonObject.put("category_id", categoryId);
			jsonObject.put("category_price", categoryPrice);
			jsonObject.put("company_id", companyId);
			jsonObject.put("date", date);
			jsonObject.put("id", id);
			jsonObject.put("order_number", orderNumber);
			jsonObject.put("percentage", percentage);
			jsonObject.put("price", price);
			jsonObject.put("total_price", totalPrice);
			jsonObject.put("user_id", userId);
			jsonObject.put("category", category.toJsonObject());
			jsonObject.put("company", company.toJsonObject());
			jsonObject.put("receiver", receiver.toJsonObject());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

}