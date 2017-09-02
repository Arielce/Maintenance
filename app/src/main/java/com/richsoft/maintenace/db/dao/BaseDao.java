package com.richsoft.maintenace.db.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.richsoft.maintenace.db.DBManager;

public abstract class BaseDao<T, ID>
{

	/** dao管理类 **/
	protected DBManager mDaoManager;
	/** 原始的dao **/
	protected Dao<T, ID> mRawDao;

	/**
	 * 构造方法
	 * 
	 * @param context
	 *            上下文
	 * @param clazz
	 *            数据表对应的Bean
	 */
	protected BaseDao(Context context, Class<T> clazz) {
		mDaoManager = DBManager.getInstance(context);
		mRawDao = mDaoManager.getDao(clazz);
	}

	/**
	 * 返回原始的dao
	 * 
	 * @return
	 */
	public Dao<T, ID> getRawDao()
	{
		return mRawDao;
	}

	/**
	 * 插入或更新数据
	 * 
	 * @param bean
	 */
	public abstract void createOrUpdate(T bean);

}
