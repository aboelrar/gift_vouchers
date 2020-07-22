package www.gift_vouchers.com.NetworkLayer;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import www.gift_vouchers.com.main_screen.ui.user_info.model.UserInfoBody;

public interface networkis {

    @Multipart
    @Headers({"Authorization:" + "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiZTU5NWU1YzRhMDlkNGNhNmQ5NjFkNzA3MTRmYjk2MjcyNDhjMmY3N2M3OGI3YjdiMmZlNmQxM2Y2MzJiNWY0Yjk0MzI3M2E4OGY2NjM1YmQiLCJpYXQiOjE1OTUyMTY4MTAsIm5iZiI6MTU5NTIxNjgxMCwiZXhwIjoxNjI2NzUyODEwLCJzdWIiOiIzMyIsInNjb3BlcyI6W119.LATtgFnu6eAdzI4eXBKPy-bLeIo8kwx6olDtR6VlNaHjTw3aRXv-ttfnURoK7zBR0f5wbyNZTLchpr8xjF9d4x0M7o4Gsn_LYo2eu0lUrnsJTwsDjYTrfVs0T3x20nxuAwVNDaepnOPu5PxPzf6Be0_nce7esTK7-E3-CJUKjmgFVkt2DZVr1nsR14bKfP9Ex8JxGqwdObHaa1nUbAi8Ip9tLlMExa0XSNyGiyiKgNDXo4vS8JndJJJPov_vKRVFicbk3WbpfCDlNp7unRWYZrOHPftnAFGCoJR0zTVXHZDp9JBpR3kk-oN7o6MLkYEL61MJnVL5r7Dc-NmQRbIEJMdjiEKUvG3aI0GqkP4rpPCNvR_9WpBYPS8jbfZOkQO8scIXP-ncM7itfHS-_WZ5FoFjRGIa1Df9bn3VgtGFjABjcNE-9fK6r9I1o5Is0qaxxq4tGRNNM2s1wSeNAZav8D2_n3FiUFFmGktDZ-3Mbo-07IE4uee06FYZB1h9fXz1D_gL386WV-c341L3TDwo9EVR-YrpdNEsPcdPDnM98yHBEkVMkvj6hQ2y_Ij6QwGztT_qyqvC9ng6oMQVk4ooF_PZ_88RCvDMalTFLEMIAXW92l6-iSa-xJWj17RKbpbBMvW1b-UIcQGKYaNMrRY33m-v97miawqod4YqdfMoa0k",
            "Content-Type: application/json",
            "X-Requested-With: XMLHttpRequest"})
    @POST("profile")
    Call<ResponseBody> upload(

            @Part("description") RequestBody description,
            @Part MultipartBody.Part file

    );
}
