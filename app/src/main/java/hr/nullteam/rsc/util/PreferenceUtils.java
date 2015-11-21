package hr.nullteam.rsc.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import hr.nullteam.rsc.business.api.model.Player;

public final class PreferenceUtils {

    private static final String USER_PREFERENCE = "user_preference";

    private static final String KEY_USER = "key_user";

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

    public void setUser(Player player) {
        getUserSharedPreferences().edit().putString(KEY_USER, gson.toJson(player)).apply();
    }

    public Player getUser() {
        if(getUserSharedPreferences().contains(KEY_USER)) {
            return gson.fromJson(getUserSharedPreferences().getString(KEY_USER, ""), Player.class);
        }
        else {
            return Player.EMPTY;
        }
    }

    public void logout() {
        getUserSharedPreferences().edit()
                .remove(KEY_USER)
                .apply();
    }

}
