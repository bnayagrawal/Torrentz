package com.example.bnayagrawal.torrentz;

import android.app.DialogFragment;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bnayagrawal.torrentz.adapter.SearchSuggestionArrayAdapter;
import com.example.bnayagrawal.torrentz.dialog.CloudFlareClearanceDialog;
import com.example.bnayagrawal.torrentz.net.CloudFlareClearance;
import com.example.bnayagrawal.torrentz.net.TorrentzService;
import com.example.bnayagrawal.torrentz.util.JsonUtil;
import com.example.bnayagrawal.torrentz.util.RetrofitUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.CookieManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bnayagrawal.torrentz.net.CloudFlareClearance.getCookies;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = SearchActivity.class.getSimpleName();
    private static final String DIALOG_CLOUD_FLARE_CLEARANCE = "cloud_flare_clearance";

    @BindView(R.id.list_view_suggestions)
    ListView mListViewSuggestions;

    private Context mApplicationContext;
    private SearchSuggestionArrayAdapter mArrayAdapter;

    private TorrentzService mService;
    private retrofit2.Call<String> mCall;
    private String mCookies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        initSearchView();
        mApplicationContext = getApplicationContext();
        mArrayAdapter = new SearchSuggestionArrayAdapter(this, new String[]{}, "");
        mListViewSuggestions.setAdapter(mArrayAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(null != mCall && mCall.isExecuted())
            mCall.cancel();
    }

    private void initSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(
                new ComponentName(getApplicationContext(), SearchResultActivity.class))
        );
        searchView.setIconifiedByDefault(false);
        searchView.setQueryRefinementEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Default implementation
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (query.length() > 0) {
                    getSuggestions(query);
                } else {
                    mArrayAdapter.clear();
                }
                return true;
            }
        });
    }

    private void getSuggestions(final String query) {
        if(null == mService && null == mCall) {
            mService = (RetrofitUtil.buildRetrofitObject()).create(TorrentzService.class);
            mCall = mService.getQuerySuggestions(mCookies, query);
        } else {
            mCall = mCall.clone();
        }

        mCookies = getCookies();
        mCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    if(mCookies != null)
                    Log.d(TAG,mCookies);
                    if(response.isSuccessful()) {
                        String[] suggestions = JsonUtil.getSuggestionsFromJsonArray(new JSONArray(response.body()));
                        mArrayAdapter.swapDataSet(suggestions, query);
                    } else {
                        getCloudFlareClearance();
                    }
                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getCloudFlareClearance() {
        CloudFlareClearanceDialog dialog = new CloudFlareClearanceDialog();
        dialog.show(getFragmentManager(),DIALOG_CLOUD_FLARE_CLEARANCE);
    }
}
