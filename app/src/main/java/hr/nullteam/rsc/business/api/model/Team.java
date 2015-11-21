package hr.nullteam.rsc.business.api.model;

import com.google.gson.annotations.SerializedName;

public final class Team {

    @SerializedName("Id")
    private long id;

    @SerializedName("Name")
    private String name;

    @SerializedName("MaxPlayers")
    private int maxPlayers;

    @SerializedName("LatStart")
    private float latStart;

    @SerializedName("LngStart")
    private float lngStart;

    public Team() {
    }

    public Team(long id, String name, int maxPlayers, float latStart, float lngStart) {
        this.id = id;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.latStart = latStart;
        this.lngStart = lngStart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public float getLatStart() {
        return latStart;
    }

    public void setLatStart(float latStart) {
        this.latStart = latStart;
    }

    public float getLngStart() {
        return lngStart;
    }

    public void setLngStart(float lngStart) {
        this.lngStart = lngStart;
    }
}
