package mtr.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import mtr.Activity.MainActivity;

/**
 * Created by saurabh on 6/4/17.
 */

public class CommonUtil {

    static Context context;
    public static final String SENDER_ID ="465187345380";
    public static final String TAG = "Utils";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PREFS_USERNAME_KEY = "username";
    private static final String PREFS_BASE_KEY = "base";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    public static final String MY_PREFS_NAME="MTR_PREFS";
    public static final String PREFS_DOMAINID_KEY="domain_id";
    public static final String PREFS_DRIVERID_KEY="driver_id";

    public CommonUtil(Context context) {

        this.context = context;
    }


    public SharedPreferences getGCMPreferences() {
        return context.getSharedPreferences("MTR_PREFS", Context.MODE_PRIVATE);
    }

//

    public static void saveDriverId(Context context, String driverId) {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PREFS_DRIVERID_KEY, driverId);
        editor.apply();
    }

    public static String getDriverId(Context context) {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String str = sharedpreferences.getString(PREFS_DRIVERID_KEY, "");
        return str;
    }
    public static void saveDomainId(Context context, String domainId) {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PREFS_DOMAINID_KEY, domainId);
        editor.apply();
    }

    public static String getDomainId(Context context) {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String str = sharedpreferences.getString(PREFS_DOMAINID_KEY, "");
        return str;
    }
    public static void saveUsername(Context context, String username) {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PREFS_USERNAME_KEY, username);
        editor.apply();
    }

    public static String getUserName(Context context) {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String str = sharedpreferences.getString(PREFS_USERNAME_KEY, "");
        return str;
    }
    public static void saveBase(Context context, String base) {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(PREFS_BASE_KEY, base);
        editor.apply();
    }

    public static String getBase(Context context) {
        SharedPreferences sharedpreferences;
        sharedpreferences = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String str = sharedpreferences.getString(PREFS_BASE_KEY, "");
        return str;
    }
    public String getRegistrationId() {
        final SharedPreferences prefs = getGCMPreferences();
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            Log.i(TAG, "Registration not found.");
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION,
                Integer.MIN_VALUE);
        int currentVersion = getAppVersion();
        if (registeredVersion != currentVersion) {
            Log.i(TAG, "App version changed.");
            return "";
        }
        return registrationId;
    }

    static int getAppVersion() {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public void storeRegistrationId(String regId) {
        final SharedPreferences prefs = getGCMPreferences();
        int appVersion = getAppVersion();
        Log.i(TAG, "Saving regId on app version " + appVersion);
        Log.i(TAG, "Reg ID : " + regId);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.apply();
    }


}
