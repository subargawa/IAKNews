package id.daprin.iaknewsapp.rest;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GUSTUT on 8/12/2017.
 */

public class ApiClient {
    private static final String BASE_URL = " https://newsapi.org/v1/";
    private static Retrofit mRetrofit;

    public static Retrofit getmRetrofitClient() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

}

