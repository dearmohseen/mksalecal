package com.mkhan.salecalculator;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by khanm on 5/14/2017.
 */

public class Utility {

    public static String APP_STORE_URL = "http://play.google.com/store/apps/details?id=";

    public static void rateApp(AppCompatActivity activity){
        Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            activity.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(APP_STORE_URL + activity.getPackageName())));
        }
    }

    public static void displayToast(Context context,String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String formatStringUpto2DigitDecimal(String orig) {

        String newValue =  orig.substring(orig.indexOf("."));
        if(newValue.length() > 3){
            System.out.println("Mohseen newValue : " + newValue + " New : " + newValue.substring(0,3));
            //return String.format("%.2f", orig);
            return orig.substring(0,orig.indexOf(".")).concat(newValue.substring(0,3));
        }

        return orig;
    }

    public static Intent createShareForecastIntent(String packageName) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, Utility.APP_STORE_URL + packageName);
        return shareIntent;
    }

}
