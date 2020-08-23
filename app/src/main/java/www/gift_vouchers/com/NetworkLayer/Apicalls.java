package www.gift_vouchers.com.NetworkLayer;

import android.content.Context;

import com.android.volley.Request;

import org.json.JSONException;

import java.util.Arrays;

/**
 * @desc Java Api Calls Contains all the Project Calls
 */

public class Apicalls {

    private APIRouter apiRouter;

    public Apicalls(Context context, NetworkInterface networkInterface) {

        apiRouter = new APIRouter(context, networkInterface);
    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func User Login
     */

    public void loginUser(final String email, final String pass, final String firebase_token) {

        apiRouter.performRequest(Apiclient.LOGIN_USER.getURL(), Apiclient.LOGIN_USER.getParams(), Arrays.asList(email, pass), Request.Method.POST, 0);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func FORGET PASSWORD
     */

    public void FORGET_PASS(String email) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.FORGET_PASS.getURL(), Request.Method.POST, Apiclient.FORGET_PASS.getParams(), Arrays.asList(email), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func User Registration
     */

    public void insertUser(final String name, final String type, final String phone, final String email, final String password, String tax, final String firebase_token) {

        apiRouter.performRequest(Apiclient.INSERT_USER.getURL(), Apiclient.INSERT_USER.getParams(), Arrays.asList(name, password, type, phone, email, tax), Request.Method.POST, 2);

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func CHANGE PASSWORD
     */

    public void change_pass(final String oldpass, final String new_pass, final String co_passwor) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHANGE_PASS.getURL(), Request.Method.POST, Apiclient.CHANGE_PASS.getParams(), Arrays.asList(oldpass, new_pass, co_passwor), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func RESET PASSWORD
     */

    public void reset_pass(final String password, final String code) {

        apiRouter.performRequest(Apiclient.RESET_PASS.getURL(), Apiclient.RESET_PASS.getParams(), Arrays.asList(password, code), Request.Method.POST, 2);
    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func ACTIVE ACCOUNT
     */

    public void companies(String search) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.COMPANIES.getURL() + search, Request.Method.GET, Apiclient.COMPANIES.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func COMPANY DETAILS
     */

    public void company_details(String id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.COMPANY_DETAILS.getURL() + id, Request.Method.GET, Apiclient.COMPANY_DETAILS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func EDIT PROFILE
     */

    public void edit_profile(String username, String phone, String email) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.EDIT_PROFILE.getURL(), Request.Method.POST, Apiclient.EDIT_PROFILE.getParams(), Arrays.asList(username, phone, email), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * @func EDIT PROFILE
     */

    public void edit_profile_co(String username, String phone, String email, String gold, String silver, String platinum) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.EDIT_PROFILE_CO.getURL(), Request.Method.POST, Apiclient.EDIT_PROFILE_CO.getParams(),
                    Arrays.asList(username, phone, email, gold, silver, platinum), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func LOGOUT
     */

    public void logout() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.LOGOUT.getURL(), Request.Method.POST, Apiclient.LOGOUT.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func GET ALL DESIGNS
     */

    public void get_all_designs() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.GET_ALL_DESIGNS.getURL(), Request.Method.GET, Apiclient.GET_ALL_DESIGNS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func CHECK OUT
     */

    public void check_out(String price, String cat_id, String email, String name, String phone, String notes) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHECK_OUT.getURL(), Request.Method.POST, Apiclient.CHECK_OUT.getParams(), Arrays.asList(price, cat_id, email, name, phone, notes), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func CHANGE NEW PASSWORD
     */

    public void change_new_pass(String code, String email, String password, String password_confirmation) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CHANGE_NEW_PASS.getURL(), Request.Method.POST, Apiclient.CHANGE_NEW_PASS.getParams(), Arrays.asList(code, email, password, password_confirmation), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func PROVINCE
     */

    public void company_order_details(String id) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.COMPANIES_ORDER_DETAILS.getURL() + id, Request.Method.GET, Apiclient.COMPANIES_ORDER_DETAILS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func WORK SHOP TYPES
     */

    public void GET_FAQ() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.FAQ.getURL(), Request.Method.GET, Apiclient.FAQ.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func INDUSTERIAL SERVICES LIST
     */

    public void industerial_services_list(final String car_model_id, final String workShop_id, final String province_id) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.INDUSTERIAL_SERVICES.getURL() + "?car_model_id=" + car_model_id + "&workShop_id=" + workShop_id + "&province_id=" + province_id, Request.Method.GET, null, null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func INDUSTERIAL DETAILS
     */

    public void industerial_details(final String id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.INDUSTERIAL_SERVICES_DETAILS.getURL() + "/" + id, Request.Method.GET, Apiclient.INDUSTERIAL_SERVICES_DETAILS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func TWINTY FOUR
     */

    public void twenty_four(final String lat, final String lng) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.TWENTY_FOUR.getURL() + "?lat=" + lat + "&lng=" + lng, Request.Method.GET, Apiclient.INDUSTERIAL_SERVICES_DETAILS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Twenty four details
     */

    public void twenty_four_details(final String id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.TWENTY_FOUR_DETAILS.getURL() + "/" + id, Request.Method.GET, Apiclient.TWENTY_FOUR_DETAILS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func WORKSHOP RATE
     */

    public void workshop_rate(final String id, final String rate, final String comment) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.WORKSHOP_RATE.getURL() + "/" + id, Request.Method.POST, Apiclient.WORKSHOP_RATE.getParams(), Arrays.asList(rate, comment), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func Industerial Rate
     */

    public void industerial_rate(final String id, final String rate, final String comment) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.INDUSTERIAL_RATE.getURL() + "/" + id, Request.Method.POST, Apiclient.INDUSTERIAL_RATE.getParams(), Arrays.asList(rate, comment), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //----------------------------------------------------------------------------------------------

    /**
     * @func HOME SERVICES RATE
     */

    public void home_services_rate(final String id, final String rate, final String comment) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.HOMESERVICES_RATE.getURL() + "/" + id, Request.Method.POST, Apiclient.HOMESERVICES_RATE.getParams(), Arrays.asList(rate, comment), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    /**
     * @func Get notifcation Data
     */

    public void request_winch(String location_lat, String location_lng, String location_address, String destination_lat, String destination_lng, String destination_address) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.REQUEST_WENCH.getURL(), Request.Method.POST, Apiclient.REQUEST_WENCH.getParams(), Arrays.asList(location_lat, location_lng, location_address, destination_lat, destination_lng, destination_address), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------


    /**
     * @func My Orders
     */

    public void my_orders() {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.MY_ORDERS.getURL(), Request.Method.GET, Apiclient.MY_ORDERS.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Confirm Order
     */

    public void confirm_order(final String order_id) {

        try {
            apiRouter.makeAdvancedRequest(Apiclient.CONFIRM_ORDER.getURL(), Request.Method.POST, Apiclient.CONFIRM_ORDER.getParams(), Arrays.asList(order_id), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Accept Reject Request
     */

    public void suggestion(final String suggerstion) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.CONTACT_US.getURL(), Request.Method.POST, Apiclient.CONTACT_US.getParams(), Arrays.asList(suggerstion), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------------------------------------------------------

    /**
     * @func Verfication Code
     */


    public void verify_code(String code) {
        try {
            apiRouter.makeAdvancedRequest(Apiclient.VERFIY_CODE.getURL() + code, Request.Method.GET, Apiclient.VERFIY_CODE.getParams(), null, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    //----------------------------------------------------------------------------------------------

    public void bill_amount(final String order_id, final String bill_amount) {

    }


    //----------------------------------------------------------------------------------------------


    /**
     * @func GET PROMO CODE
     */

    public void promoCode(final String promo_code) {


    }
    //----------------------------------------------------------------------------------------------


}
