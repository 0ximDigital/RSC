package hr.nullteam.rsc.business.api.model;

import com.google.gson.annotations.SerializedName;

public final class Judge {

    @SerializedName("Id")
    private long id;

    @SerializedName("UserId")
    private int userId;

    @SerializedName("GameId")
    private int gameId;

    public Judge() {
    }

    public Judge(long id, int userId, int gameId) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
