package com.pslyp.quailsmartfarm_home_client.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Prefs {

    private static Context mContext;

    private SharedPreferences sp;

    private Prefs(Prefs.Builder builder) {
        this.sp = mContext.getSharedPreferences(builder.name, builder.mode);
    }

    public SharedPreferences getPreferences() {
        if(sp != null) {
            return sp;
        }
        throw new RuntimeException("Don't use Preference");
    }

    public void putString(final String key, final String value) {
        Editor editor = edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void putBoolean(final String key, final Boolean value) {
        Editor editor = edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getString(final String key, final String value) {
        return getPreferences().getString(key, value);
    }

    public Boolean getBoolean(final String key, final Boolean value) {
        return getPreferences().getBoolean(key, value);
    }

    public Editor edit() {
        return getPreferences().edit();
    }

    public void clear() {
        getPreferences().edit().clear().commit();
    }

    public static class Builder {

        private String name;
        private int mode;

        public Builder(Context context) {
            Prefs.mContext = context;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder mode(final int mode) {
            this.mode = mode;
            return this;
        }

        public Prefs build() {
            return new Prefs(this);
        }

    }
}
