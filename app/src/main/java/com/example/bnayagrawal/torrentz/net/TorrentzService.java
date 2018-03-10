package com.example.bnayagrawal.torrentz.net;

import android.support.annotation.NonNull;



import org.json.JSONArray;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bnayagrawal on 8/3/18.
 */

public interface TorrentzService {
    /*
     * ALL GET REQUESTS
     */
    @GET("{hash}")
    Call<String> getTorrentDetails(@Path("hash") String hash);


    @GET(Url.PATH_SUGGESTIONS + "/{query}")
    Call<String> getQuerySuggestions(@Path("query") String query);

    //There are different path for different sort orders, so i cant use a specific path here.
    //The path needs to by passed by the caller based on the sorting order.
    //See Url class for different paths.
    @GET("{search}")
    Call<String> searchTorrents(@Path("search") String searchPath ,@Query(Url.PARAM_SEARCH) String query,@Query(Url.PARAM_SAFE) String adult);

    @GET("{path}")
    Call<String> getDocument(@Path("path") String path);
}
