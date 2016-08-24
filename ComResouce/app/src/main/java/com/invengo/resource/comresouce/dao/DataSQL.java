package com.invengo.resource.comresouce.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.invengo.resource.entity.DaoMaster;


public class DataSQL extends DaoMaster.OpenHelper {

	public DataSQL(Context context, String name, CursorFactory factory) {
		super(context, name, factory);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
