package hr.nullteam.rsc.business.api.model;

import com.google.gson.annotations.SerializedName;

public final class RegisterPlayer {

    @SerializedName("Email")
    private String email;

    @SerializedName("Password")
    private String password;

    @SerializedName("Name")
    private String name;

    @SerializedName("Surname")
    private String surname;

    public RegisterPlayer(String email, String password, String name, String surname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
