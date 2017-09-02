package com.richsoft.maintenace.db.dao;

import android.content.Context;
import com.richsoft.maintenace.bean.workorder.DocumentBean;

import java.sql.SQLException;

/**
 * 视频dao
 * 
 * @author song
 * 
 */
public class DocDao extends BaseDao<DocumentBean, String> {

	public DocDao(Context context) {
		super(context, DocumentBean.class);
	}

	@Override
	public void createOrUpdate(DocumentBean bean) {
		try {
			mRawDao.createOrUpdate(bean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据ID查询
	 */
	public DocumentBean queryById(String dId) {
		try {
			return mRawDao.queryForId(dId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据ID删除
	 */
	public void deleteById(String dId) {
		try {
			mRawDao.deleteById(dId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
