package mtr.Utils;

import java.util.HashMap;

import mtr.Response.Plates.ResponsePlates;
import mtr.Response.deviceidValidation.ResponseValidateID;
import mtr.Response.regularjobs.ResponseRegularJobs;
import mtr.Response.reservedjobs.ResponseReservedJobs;
import mtr.Response.validateaccesscode.ResponseCode;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by saurabh on 6/4/17.
 */

public class RestClient {


    private static GitApiInterface gitApiInterface;
//    private static String baseUrl="http://smartrides.us/";
    private static String baseUrl="http://103.48.187.45/";

    public static GitApiInterface getClient() {
//        if (gitApiInterface == null) {
//
//
//            RestAdapter adapter = new RestAdapter.Builder()
//                    .setLogLevel(RestAdapter.LogLevel.FULL)
//                    .setEndpoint(baseUrl)
//                    .build();
//            gitApiInterface = adapter.create(GitApiInterface.class);
//
//        }
//        return gitApiInterface;


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        //The Retrofit builder will have the client attached, in order to get connection logs
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

        gitApiInterface = retrofit.create(GitApiInterface.class);

        return gitApiInterface;


    }

    public interface GitApiInterface {

//        @POST("/restapis/validate_deviceid.php")
        @POST("/driver/v1/validate_deviceid")
        Call<ResponseValidateID> validateid(@Body HashMap<String, String> hashMap);
//        @POST("/restapis/validate_accesscode.php")
        @POST("/driver/v1/validate_accesscode")
        Call<ResponseCode> sendaccesscode(@Body HashMap<String, String> hashMap);
//        @POST("/restapis/driver_plates.php")
        @POST("/driver/v1/driver_plates")
        Call<ResponsePlates> getPlates(@Body HashMap<String, String> hashMap);
        //  @POST("/restapis/available_jobs.php")
        @POST("/driver/v1/available_jobs")
        Call<ResponseRegularJobs> getjobs(@Body HashMap<String, String> hashMap);
        //  @POST("/restapis/driver_reservedjobs.php")
        @POST("/driver/v1/driver_reservedjobs")
        Call<ResponseReservedJobs> getReservedJobs(@Body HashMap<String, String> hashMap);



    }
}

