package hr.nullteam.rsc.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

public final class PreferenceUtils {

    private static final String USER_PREFERENCE = "user_preference";

    private final Context context;
    private final Gson gson;

    @Inject
    public PreferenceUtils(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    private SharedPreferences getUserSharedPreferences() {
        return context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
    }

}
