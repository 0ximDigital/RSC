package hr.nullteam.rsc.business.dao.model;


import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import hr.nullteam.rsc.business.dao.TodoDatabase;

@Table(databaseName = TodoDatabase.NAME)
public final class TodoModel extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String wannabeData;

}
