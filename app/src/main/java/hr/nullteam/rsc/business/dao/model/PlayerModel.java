package hr.nullteam.rsc.business.dao.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import hr.nullteam.rsc.business.dao.PlayersDatabase;

@Table(databaseName = PlayersDatabase.NAME)
public final class PlayerModel extends BaseModel {

    @Column
    @PrimaryKey
    public long id;

    @Column
    public String email;

    @Column
    public String name;

    @Column
    public String surname;

    @Column
    public String avatarUrl;

    public PlayerModel() {
    }
}
