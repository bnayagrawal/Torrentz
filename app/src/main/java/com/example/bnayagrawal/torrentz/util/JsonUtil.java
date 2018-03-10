package com.example.bnayagrawal.torrentz.util;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by bnayagrawal on 9/3/18.
 */

public class JsonUtil {

    public static String[] getSuggestionsFromJsonArray(@NonNull JSONArray suggestionJsonArray) {
        try {
            JSONArray jsonArray = suggestionJsonArray.getJSONArray(1);
            String[] suggestions = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++)
                suggestions[i] = (String) jsonArray.get(i);
            return suggestions;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
