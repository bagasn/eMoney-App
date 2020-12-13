package com.bagasn.demoemoney.util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.bagasn.demoemoney.BuildConfig;

public class SPSession {
    private static final String appSessionMaster = BuildConfig.APPLICATION_ID + ":master";
    private final SharedPreferences mSP;

    public static final String keyUserLoggedIn = "user-has-logged-in";
    public static final String keyUserName = "user-name";
    public static final String keyUserEmail = "user-email";
    public static final String keyUserNoId = "user-no-id";
    public static final String keyUserRank = "user-rank";

    public SPSession(@NonNull Context context) {
        mSP = context.getSharedPreferences(appSessionMaster, Context.MODE_PRIVATE);
    }

    public static SPSession init(@NonNull Context context) {
        return new SPSession(context);
    }

    public void putString(String key, String value) {
        mSP.edit()
                .putString(key, value)
                .apply();
    }

    public String getString(String key, String defValue) {
        return mSP.getString(key, defValue);
    }

    public String getString(String key) {
        return mSP.getString(key, "");
    }

}
