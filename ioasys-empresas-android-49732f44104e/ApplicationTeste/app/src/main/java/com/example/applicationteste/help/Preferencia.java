package com.example.applicationteste.help;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferencia {
    SharedPreferences pref; // Shared Preferences
    SharedPreferences.Editor editor; // Editor for Shared preferences
    Context _context; // Context
    //int PRIVATE_MODE = 0; // Shared pref mode
    public static final String PREF_NAME = "ApplicationTestePreference"; // Shared preferences file name

    private static Preferencia mInstance;

    // All Shared Preferences Keys
    private static final String KEY_LOGGEN = "keyLoggen";

    private static final String KEY_ACCESS_TOKEN = "keyAccessToken";
    private static final String KEY_CLIENT = "keyClient";
    private static final String KEY_UID = "keyUid";

    public Preferencia(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public Boolean getKeyLoggen() {
        return pref.getBoolean(KEY_LOGGEN, false);
    }
    
    public void setKeyLoggen( boolean loggen){
        editor.putBoolean(KEY_LOGGEN, loggen);
        editor.commit();
    }
    
    public String getKeyAccessToken() {
        return pref.getString(KEY_ACCESS_TOKEN, "");
    }
    public void setKeyAccessToken( String accessToken){
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }
    public String getKeyClient() {
        return pref.getString(KEY_CLIENT, "");
    }
    public void setKeyClient( String keyClient){
        editor.putString(KEY_CLIENT, keyClient);
        editor.commit();
    }
    public String getKeyUid() {
        return pref.getString(KEY_UID, "");
    }
    public void setKeyUid( String uid ){
        editor.putString(KEY_UID, uid);
        editor.commit();
    }
    
    public static Preferencia getInstance(Context context) {
        if(mInstance == null){
            mInstance = new Preferencia(context);
        }
        return mInstance;
    }

    public void saveLogin (Boolean loggen, String accessToken, String client, String uid){
        editor.putBoolean(KEY_LOGGEN, loggen);
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.putString(KEY_CLIENT, client);
        editor.putString(KEY_UID, uid);

        editor.commit();
    }
}
