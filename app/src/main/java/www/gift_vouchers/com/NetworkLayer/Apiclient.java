package www.gift_vouchers.com.NetworkLayer;


import java.util.Arrays;
import java.util.List;

public enum Apiclient {

    /**
     * @API ---> 1) URL OF API METHOD
     * <p>
     * ---> 2) ARRAY OF PARAMETERS KEYS
     */

    LOGIN_USER("auth/login", Arrays.asList("email", "password")),
    INSERT_USER("auth/signup", Arrays.asList("username", "password", "role", "phone", "email")),
    FORGET_PASS("password/create", Arrays.asList("email")),
    CHANGE_PASS("password/change", Arrays.asList("old_password", "password", "password_confirmation")),
    RESET_PASS("Auth_general/reset_password", Arrays.asList("password", "code")),
    COMPANIES("company/all", null),
    COMPANY_DETAILS("company/get?id=", null),
    EDIT_PROFILE("profile", Arrays.asList("username", "phone", "email")),
    EDIT_PROFILE_CO("profile", Arrays.asList("username", "phone", "email", "gold", "silver", "bronze")),
    LOGOUT("Auth_private/logout", null),
    GET_ALL_DESIGNS("card/get", null),
    CHECK_OUT("order/create", Arrays.asList("price", "category_id", "receiver_email", "receiver_name", "phone", "notes")),
    CHANGE_NEW_PASS("password/reset", Arrays.asList("code", "email", "password", "password_confirmation")),
    PROVINCE("order/my", null),
    COMPANIES_ORDER_DETAILS("order/get?id=", null),
    WORK_SHOPS("industrial/work_shops", null),
    INDUSTERIAL_SERVICES("industrial/industrial_services", Arrays.asList("car_model_id", "workShop_id", "province_id")),
    INDUSTERIAL_SERVICES_DETAILS("industrial/single_industrial_services", null),
    TWENTY_FOUR("live_services/live_services", null),
    TWENTY_FOUR_DETAILS("live_services/single_live_services", null),
    WORKSHOP_RATE("live_services/rate", Arrays.asList("rate", "comment")),
    INDUSTERIAL_RATE("industrial/rate", Arrays.asList("rate", "comment")),
    HOMESERVICES_RATE("home_service/rate", Arrays.asList("rate", "comment")),
    REQUEST_WENCH("wench/looking_for_wench", Arrays.asList("location_lat", "location_lng", "location_address", "destination_lat", "destination_lng", "destination_address")),
    MY_ORDERS("order/my", null),
    CONFIRM_ORDER("wench/confirm_order", null),
    ACCEPT_REJECT_REQUEST("wench/change_status", Arrays.asList("status")),
    LANGUAGE("Auth_private/change_lang", Arrays.asList("lang")),
    UPDATE_INVESTOR("update_investor?", Arrays.asList("id", "Name", "Email", "Password", "Age", "Gender", "Work", "Mobile", "Images")),
    UPLOAD_CONSULTATION_FILES("upload-consultation-files", Arrays.asList("consultation_id", "images", "files"));


    //--------------------------------------

    /**
     * @BASE_URL
     */

    String BASE_URL = "http://vouchers-sa.com/api/";

    private final String URL;
    private final List<String> params;


    Apiclient(String URL, List<String> params) {

        this.URL = URL;
        this.params = params;
    }

    public String getURL() {
        return BASE_URL + URL;
    }

    public List<String> getParams() {
        return params;
    }
}
