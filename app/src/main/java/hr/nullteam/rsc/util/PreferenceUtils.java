package hr.nullteam.rsc.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import hr.nullteam.rsc.business.api.model.User;

public final class PreferenceUtils {

    private static final String USER_PREFERENCE = "user_preference";

    private static final String KEY_USER = "key_user";
    private static final String KEY_TEAM_ID = "key_team_id";
    private static final String KEY_GAME_ID = "key_game_id";
    private static final String KEY_PLAYER_ID = "key_player_id";

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

    public void setUser(User user) {
        getUserSharedPreferences().edit().putString(KEY_USER, gson.toJson(user)).apply();
    }

    public User getUser() {
        if(getUserSharedPreferences().contains(KEY_USER)) {
            return gson.fromJson(getUserSharedPreferences().getString(KEY_USER, ""), User.class);
        }
        else {
            return User.EMPTY;
        }
    }

    public void setTeamId(long teamId) {
        getUserSharedPreferences().edit().putLong(KEY_TEAM_ID, teamId).apply();
    }

    public long getTeamId() {
        return getUserSharedPreferences().getLong(KEY_TEAM_ID, 0);
    }

    public long getGameId() {
        return getUserSharedPreferences().getLong(KEY_GAME_ID, 0);
    }

    public void setGameId(long gameId) {
        getUserSharedPreferences().edit().putLong(KEY_GAME_ID, gameId).apply();
    }

    public void setPlayerId(long playerId) {
        getUserSharedPreferences().edit().putLong(KEY_PLAYER_ID, playerId).apply();
    }

    public long getPlayerId() {
        return getUserSharedPreferences().getLong(KEY_PLAYER_ID, -1);
    }

    public void endSession() {
        getUserSharedPreferences().edit()
                .remove(KEY_GAME_ID)
                .remove(KEY_TEAM_ID)
                .remove(KEY_PLAYER_ID)
                .apply();
    }

    public void logout() {
        getUserSharedPreferences().edit()
                .remove(KEY_USER)
                .remove(KEY_GAME_ID)
                .remove(KEY_TEAM_ID)
                .remove(KEY_PLAYER_ID)
                .apply();
    }

}
