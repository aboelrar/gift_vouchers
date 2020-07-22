package www.gift_vouchers.com.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class saved_data {

    public String get_lan(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("language", Context.MODE_PRIVATE);
        return sharedPreferences.getString("lan", "en");

    }


    //GET NAME
    public String get_name(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "0");
        return name;
    }

    //GET EMAIL
    public String get_email(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "0");
        return email;
    }

    //GET EMAIL
    public String get_phone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String email = sharedPreferences.getString("phone", "0");
        return email;
    }

    //GET PHONE
    public String get_token(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "0");
        return token;
    }


    //GET LOGIN STATUS
    public Boolean get_login_status(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("login_bool", MODE_PRIVATE);
        Boolean status = sharedPreferences.getBoolean("login_bool", false);
        return status;
    }

    //SAVE USER TYPE
    public String get_user_type(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("personal_info", MODE_PRIVATE);
        String type = sharedPreferences.getString("type", "user");
        return type;
    }

    //GET ORDER ID
    public String order_id(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        String type = sharedPreferences.getString("order_id", "0");
        return type;
    }

    //GET PRICE
    public String get_price(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        String price = sharedPreferences.getString("price", "0");
        return price;
    }

    //GET ORDER LAT
    public String get_type(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        String type = sharedPreferences.getString("type", "0");
        return type;
    }

    //GET ORDER LNG
    public String get_logo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        String logo = sharedPreferences.getString("logo", "0");
        return logo;
    }

    //GET ORDER design
    public String get_design(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        String design = sharedPreferences.getString("design", "0");
        return design;
    }

    //GET CAT ID
    public String cat_id(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        String cat_id = sharedPreferences.getString("cat_id", "0");
        return cat_id;
    }

    //GET CAT ID
    public String get_order_name(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("order", MODE_PRIVATE);
        String cat_id = sharedPreferences.getString("order_name", "0");
        return cat_id;
    }

    //GET Cart
    public String get_cart(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cart", MODE_PRIVATE);
        String type = sharedPreferences.getString("cart", "0");
        return type;
    }

    //GET WINCH LAT
    public Double get_winch_owner_lng(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("winch", MODE_PRIVATE);
        long type = sharedPreferences.getLong("winch_lng", Double.doubleToLongBits(0.0));
        return Double.longBitsToDouble(type);
    }

    //GET WINCH LAT
    public String get_winch_request_id(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("winch", MODE_PRIVATE);
        String id = sharedPreferences.getString("request_id", "0");
        return id;
    }

    //GET USER PHONE
    public String get_user_phone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("winch", MODE_PRIVATE);
        String phone = sharedPreferences.getString("phone", "0");
        return phone;
    }

}
