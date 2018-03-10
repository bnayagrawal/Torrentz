package com.example.bnayagrawal.torrentz.adapter;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bnayagrawal.torrentz.R;
import com.example.bnayagrawal.torrentz.SearchResultActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bnayagrawal on 7/3/18.
 */

public class SearchSuggestionArrayAdapter extends ArrayAdapter<String> {
    private final Context mContext;
    private String[] mValues;
    private String mQueryText;

    public SearchSuggestionArrayAdapter(Context context, String[] values, String queryText) {
        super(context, -1, values);
        this.mContext = context;
        this.mValues = values;
        this.mQueryText = queryText;
    }

    @Override
    public void clear() {
        mValues = new String[]{};
        mQueryText = "";
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mValues.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_search_suggestion, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtQuery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent searchResultIntent = new Intent(mContext, SearchResultActivity.class);
                searchResultIntent.setAction(Intent.ACTION_SEARCH);
                searchResultIntent.putExtra(SearchManager.QUERY,holder.txtQuery.getText().toString());
                mContext.startActivity(searchResultIntent);
            }
        });

        int firstIndex = (mValues[position]).indexOf(mQueryText);
        int lastIndex = firstIndex + mQueryText.length();

        if(firstIndex >= 0 && lastIndex >= 0) {
            Spannable spannable = new SpannableString(mValues[position]);
            spannable.setSpan(
                    new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorAccent)),
                    firstIndex,
                    lastIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            );
            holder.txtQuery.setText(spannable, TextView.BufferType.SPANNABLE);
        } else {
            holder.txtQuery.setText(mValues[position]);
        }

        return holder.txtQuery;
    }

    public void swapDataSet(String[] values, String query) {
        this.mValues = values;
        this.mQueryText = query;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.text_search_suggestion_item) TextView txtQuery;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
