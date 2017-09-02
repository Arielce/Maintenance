package com.richsoft.maintenace.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.richsoft.maintenace.bean.workorder.DocumentBean;
import java.sql.SQLException;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "storage.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, DocumentBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          ConnectionSource connectionSource,
                          int oldVersion,
                          int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, DocumentBean.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
