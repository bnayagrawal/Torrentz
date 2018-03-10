package com.example.bnayagrawal.torrentz.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.bnayagrawal.torrentz.R;
import com.example.bnayagrawal.torrentz.net.CloudFlareClearance;

/**
 * Created by bnayagrawal on 10/3/18.
 */

public class CloudFlareClearanceDialog extends DialogFragment
        implements CloudFlareClearance.CloudFlareClearanceListener {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_cloud_flare_clearance,null);
        builder.setView(view)
                .setCancelable(false);

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        CloudFlareClearance.fetchClearanceCookies(this);
    }

    @Override
    public void onClearanceCookiesRetrieved() {
        Context context = getActivity();
        Toast.makeText(context,context.getString(R.string.info_cookie_retrieved),Toast.LENGTH_LONG).show();
        dismiss();
    }

    @Override
    public void onClearanceCookiesRetrieveError() {
        Context context = getActivity();
        Toast.makeText(context,context.getString(R.string.error_retrieving_cookie),Toast.LENGTH_LONG).show();
        dismiss();
    }
}
