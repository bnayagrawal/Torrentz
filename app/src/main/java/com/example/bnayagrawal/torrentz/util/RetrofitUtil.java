package com.example.bnayagrawal.torrentz.util;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.bnayagrawal.torrentz.net.Url.BASE_URL;

/**
 * Created by bnayagrawal on 9/3/18.
 */

public class RetrofitUtil {
    /*For POST requests, hence not required*/
    public static final String REQUEST_BODY_TYPE_PLAIN_TEXT = "text/plain";
    public static final String REQUEST_BODY_TYPE_JSON = "application/json";

    public static Retrofit buildRetrofitObject() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
