package hr.nullteam.rsc.business.dao;


import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = PlayersDatabase.NAME, version = PlayersDatabase.VERSION)
public final class PlayersDatabase {

    public static final String NAME = "PlayersDatabase";

    public static final int VERSION = 1;

}
