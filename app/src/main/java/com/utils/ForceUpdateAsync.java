package com.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;

/**
 * Created by cygnet on 26/4/17.
 */

public class ForceUpdateAsync extends AsyncTask<String, String, JSONObject> {

    private String latestVersion;
    private String currentVersion;
    private Context context;
    private ProgressDialog progressDialog;

    public ForceUpdateAsync(String currentVersion, Context context) {
        this.currentVersion = currentVersion;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "", "Loading...");
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        try {
            latestVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + "com.whatsapp" + "&hl=en")
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select("div[itemprop=softwareVersion]")
                    .first()
                    .ownText();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (latestVersion != null) {
            if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                // Toast.makeText(context,"update is available.",Toast.LENGTH_LONG).show();
                if (!((Activity) context).isFinishing()) {
                    showForceUpdateDialog();
                }
            }
        }
        super.onPostExecute(jsonObject);
    }

    public void showForceUpdateDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle("Update");
        alertDialogBuilder.setMessage("You are not updated. Latest version:" + latestVersion);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
                dialog.cancel();
            }
        });
        alertDialogBuilder.show();
    }
}