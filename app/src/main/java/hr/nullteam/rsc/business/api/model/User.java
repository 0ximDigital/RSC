package hr.nullteam.rsc.business.api.model;

import com.google.gson.annotations.SerializedName;

public final class User {

    public static final User EMPTY = new User();

    public static final int UNDEFNED_ID = -1;

    @SerializedName("Id")
    private long id;

    @SerializedName("Email")
    private String email;

    @SerializedName("Name")
    private String name;

    @SerializedName("Surname")
    private String surname;

    @SerializedName("PictureUrl")
    private String avatarUrl;

    public User() {
    }

    public User(long id, String email, String name, String surname, String avatarUrl) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.avatarUrl = avatarUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
