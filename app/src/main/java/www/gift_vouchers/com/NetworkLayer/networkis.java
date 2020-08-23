package www.gift_vouchers.com.NetworkLayer;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoRootClass;

public interface networkis {

    @Multipart
    @Headers({
            "X-Requested-With: XMLHttpRequest"})
    @POST("profile")
    Call<UserInfoRootClass> upload(
            @Header("Authorization") String authorization,
            @Query("username") String username,
            @Query("email") String email,
            @Part("phone") RequestBody phone,
            @Part MultipartBody.Part image
    );

    @Multipart
    @Headers({
            "X-Requested-With: XMLHttpRequest"})
    @POST("profile")
    Call<UserInfoRootClass> upload_company(
            @Header("Authorization") String authorization,
            @Query("username") String username,
            @Query("email") String email,
            @Part("phone") RequestBody phone,
            @Query("gold") String gold,
            @Query("silver") String silver,
            @Query("bronze") String bronze,
            @Part MultipartBody.Part image
    );
}
