package com.aswdc_Dweather.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonMethods {



    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    public static String convert24ToAMPM(String time) {
        Date _12HourDt = null;
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            final Date dateObj = sdf.parse(time);
            int hour = Integer.parseInt(time.substring(0, 2));
            String minute = time.substring(3, 5);
            String x;
            if (hour > 12) {
                x="PM";
            } else
                x="AM";
            hour=hour - ((hour > 12)?12:0);
            if(hour<10)
                time="0"+hour+":"+minute+" "+x;
            else
                time=hour+":"+minute+" "+x;

        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void screenDisable(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static void screenEnable(Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }


}
