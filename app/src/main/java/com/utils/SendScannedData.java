package com.utils;

import android.util.Log;

/**
 * Created by devin on 8/31/17.
 */

public class SendScannedData {
    private static final String TAG = "SendScannedData";
    private String scannedUPC;

    public SendScannedData(String scannedUPC)
    {
        Log.d(TAG, "SendScannedData(): " + scannedUPC);
        this.scannedUPC = scannedUPC;
    }

    public String getScannedUPC()
    {
        Log.d(TAG, "getScannedUPC()");
        return scannedUPC;
    }
}
