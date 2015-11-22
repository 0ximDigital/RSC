package hr.nullteam.rsc.business.api;

public class QrData {

    public static final String TYPE_JUDGE = "JUDGE";
    public static final String TYPE_PLAYER = "PLAYER";

    private String type;

    // teamId for player, gameId for judge
    private long id;

    public QrData(String type, long id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
