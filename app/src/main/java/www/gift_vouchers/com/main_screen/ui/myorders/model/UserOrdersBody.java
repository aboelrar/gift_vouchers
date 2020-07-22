package www.gift_vouchers.com.main_screen.ui.myorders.model;//
//  UserOrdersBody.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 15, 2020

import org.json.*;
import java.util.*;
import com.google.gson.annotations.SerializedName;


public class UserOrdersBody{

	@SerializedName("card")
	private Object card;
	@SerializedName("category")
	private UserOrdersCategory category;
	@SerializedName("category_id")
	private String categoryId;
	@SerializedName("category_price")
	private String categoryPrice;
	@SerializedName("company")
	private UserOrdersCompany company;
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
	private UserOrdersReceiver receiver;
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
	public void setCategory(UserOrdersCategory category){
		this.category = category;
	}
	public UserOrdersCategory getCategory(){
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
	public void setCompany(UserOrdersCompany company){
		this.company = company;
	}
	public UserOrdersCompany getCompany(){
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
	public void setReceiver(UserOrdersReceiver receiver){
		this.receiver = receiver;
	}
	public UserOrdersReceiver getReceiver(){
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
	public UserOrdersBody(JSONObject jsonObject){
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
		category = new UserOrdersCategory(jsonObject.optJSONObject("category"));
		company = new UserOrdersCompany(jsonObject.optJSONObject("company"));
		receiver = new UserOrdersReceiver(jsonObject.optJSONObject("receiver"));
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