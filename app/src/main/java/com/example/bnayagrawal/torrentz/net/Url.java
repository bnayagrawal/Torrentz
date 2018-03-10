package com.example.bnayagrawal.torrentz.net;

import android.support.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by bnayagrawal on 8/3/18.
 */

public final class Url {
    //Base website url
    public static final String BASE_URL= "https://torrentz2.is";

    public static final String PATH_SEARCH = "search";
    public static final String PATH_SEARCH_SORTED_BY_PEERS = "search";
    public static final String PATH_SEARCH_SORTED_BY_RATING = "searchN";
    public static final String PATH_SEARCH_SORTED_BY_DATE = "searchA";
    public static final String PATH_SEARCH_SORTED_BY_SIZE = "searchS";

    public static final String PATH_VERIFIED = "verified";
    public static final String PATH_VERIFIED_SORTED_BY_PEERS = "verifiedP";
    public static final String PATH_VERIFIED_SORTED_BY_RATING = "verifiedN";
    public static final String PATH_VERIFIED_SORTED_BY_DATE = "verifiedA";
    public static final String PATH_VERIFIED_SORTED_BY_SIZE = "verifiedS";

    public static final String PATH_POPULAR_TORRENTS = "my";
    public static final String PATH_SUGGESTIONS = "suggestions";

    //safe param value 0 for adult and 1 for non-adult
    public static final String PARAM_SAFE = "safe";
    public static final String PARAM_SEARCH = "f";
    public static final String PARAM_PAGE = "p";

    //Torrent fixed age
    public static final String[] AGES = {"1d","3d","7d","1m"};

    //To be appended with query string for filtering age.
    //example base_url/search?f=movies+added:%3A3d
    //Where "%3A" means ":" and "3d" is the age
    public static final String PARAM_AGE_VALUE = "added:";

    public static class Builder {

        public static String buildMagnetUrl(@NonNull String[] trackerList,@NonNull String hash) {
            String magnetUrl = "magnet:?xt=urn:";
            String infoHash = "btih:" + hash;
            String trackers = "";
            try {
                for (String tracker : trackerList)
                    trackers = trackers.concat("&tr=" + URLEncoder.encode(tracker, "utf-8"));
                magnetUrl = magnetUrl.concat(infoHash).concat(trackers);
            } catch (UnsupportedEncodingException e) {
                magnetUrl = null;
                e.printStackTrace();
            }
            return magnetUrl;
        }
    }
}
