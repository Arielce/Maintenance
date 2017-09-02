package com.richsoft.maintenace.db;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;

/**
 * 数据库管理类
 * 
 * @author zhou
 * 
 */
public class DBManager {

	private OrmLiteSqliteOpenHelper helper;
	private static DBManager instance;

	private DBManager(Context context) {
		helper = OpenHelperManager.getHelper(context, DBHelper.class);
	}

	/**
	 * 获取该类实例
	 */
	public synchronized static DBManager getInstance(Context context) {
		if (instance == null) {
			instance = new DBManager(context);
		}
		return instance;
	}

	/**
	 * 获取对应数据表的dao
	 * 
	 * @param clazz
	 *            表对应的Bean
	 */
	public <T, ID> Dao<T, ID> getDao(Class<T> clazz) {
		try {
			return helper.getDao(clazz);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 释放资源
	 */
	public void release() {
		OpenHelperManager.releaseHelper();
	}

}
