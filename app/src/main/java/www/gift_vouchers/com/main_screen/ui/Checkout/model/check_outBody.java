package www.gift_vouchers.com.main_screen.ui.Checkout.model;//
//  check_outBody.java
//  Model Generated using http://www.jsoncafe.com/ 
//  Created on July 25, 2020

import org.json.*;

import com.google.gson.annotations.SerializedName;


public class check_outBody{

	@SerializedName("card")
	private check_outCard card;
	@SerializedName("category")
	private check_outCategory category;
	@SerializedName("category_id")
	private String categoryId;
	@SerializedName("category_price")
	private String categoryPrice;
	@SerializedName("company")
	private check_outCompany company;
	@SerializedName("company_id")
	private String companyId;
	@SerializedName("date")
	private Object date;
	@SerializedName("id")
	private int id;
	@SerializedName("order_number")
	private String orderNumber;
	@SerializedName("paid")
	private String paid;
	@SerializedName("percentage")
	private String percentage;
	@SerializedName("price")
	private String price;
	@SerializedName("receiver")
	private check_outReceiver receiver;
	@SerializedName("total_price")
	private String totalPrice;
	@SerializedName("user_id")
	private String userId;

	public void setCard(check_outCard card){
		this.card = card;
	}
	public check_outCard getCard(){
		return this.card;
	}
	public void setCategory(check_outCategory category){
		this.category = category;
	}
	public check_outCategory getCategory(){
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
	public void setCompany(check_outCompany company){
		this.company = company;
	}
	public check_outCompany getCompany(){
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
	public void setPaid(String paid){
		this.paid = paid;
	}
	public String getPaid(){
		return this.paid;
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
	public void setReceiver(check_outReceiver receiver){
		this.receiver = receiver;
	}
	public check_outReceiver getReceiver(){
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
	public check_outBody(JSONObject jsonObject){
		if(jsonObject == null){
			return;
		}
		categoryId = jsonObject.optString("category_id");
		categoryPrice = jsonObject.optString("category_price");
		companyId = jsonObject.optString("company_id");
		orderNumber = jsonObject.optString("order_number");
		paid = jsonObject.optString("paid");
		percentage = jsonObject.optString("percentage");
		price = jsonObject.optString("price");
		totalPrice = jsonObject.optString("total_price");
		userId = jsonObject.optString("user_id");
		date = jsonObject.optString("date");
		id = jsonObject.optInt("id");
		card = new check_outCard(jsonObject.optJSONObject("card"));
		category = new check_outCategory(jsonObject.optJSONObject("category"));
		company = new check_outCompany(jsonObject.optJSONObject("company"));
		receiver = new check_outReceiver(jsonObject.optJSONObject("receiver"));
	}

	/**
	 * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
	 */
	public JSONObject toJsonObject()
	{
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("category_id", categoryId);
			jsonObject.put("category_price", categoryPrice);
			jsonObject.put("company_id", companyId);
			jsonObject.put("date", date);
			jsonObject.put("id", id);
			jsonObject.put("order_number", orderNumber);
			jsonObject.put("paid", paid);
			jsonObject.put("percentage", percentage);
			jsonObject.put("price", price);
			jsonObject.put("total_price", totalPrice);
			jsonObject.put("user_id", userId);
			jsonObject.put("card", card.toJsonObject());
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