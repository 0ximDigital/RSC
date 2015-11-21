package hr.nullteam.rsc.business.api.model;

import com.google.gson.annotations.SerializedName;

public final class Player {

    @SerializedName("Id")
    private long id;

    @SerializedName("UserId")
    private long userId;

    @SerializedName("TeamId")
    private long teamId;

    @SerializedName("Ready")
    private boolean isReady;

    @SerializedName("Alive")
    private boolean isAlive;

    @SerializedName("Lat")
    private float lat;

    @SerializedName("Lng")
    private float lng;

    @SerializedName("TagNumber")
    private int tagNumber;

    public Player() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public int getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(int tagNumber) {
        this.tagNumber = tagNumber;
    }
}
