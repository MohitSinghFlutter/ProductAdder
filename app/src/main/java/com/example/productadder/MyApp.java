package com.example.productadder;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class MyApp extends Application {

    public FirebaseFirestore db;
    public AlertDialog dialog;
    public ConnectivityManager connectivityManager;

    @Override
    public void onCreate() {
        super.onCreate();
        setup();
    }

    private void setup() {
        db = FirebaseFirestore.getInstance();
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public void showLoadingDialog(Context context) {
        dialog = new AlertDialog.Builder(context)
                .setTitle("Loading...")
                .setMessage("Please Wait")
                .show();
    }

    public void hideLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public boolean isOffline() {
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo dataNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return !(wifiNetworkInfo.isConnected() || dataNetworkInfo.isConnected());
    }

}
