package com.cooperq.teatime;

import android.database.sqlite.*;
import android.provider.BaseColumns;
public abstract class TeaContract implements BaseColumns {
	public final static String TABLE_NAME = "tea";
	public final static String COLUMN_NAME_TEA_ID		= "teaId";
	public final static String COLUMN_NAME_NAME		= "name";
	public final static String COLUMN_NAME_DESC		= "description";
	public final static String COLUMN_NAME_RESID		= "resourceId";
	public final static String COLUMN_NAME_STEEP_TIME	= "steepTime";
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String NUMBER_TYPE = " INTEGER";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + TABLE_NAME + " (" +
	    TeaContract._ID + " INTEGER PRIMARY KEY," +
	    TeaContract.COLUMN_NAME_TEA_ID + TEXT_TYPE + COMMA_SEP +
	    TeaContract.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    TeaContract.COLUMN_NAME_DESC + TEXT_TYPE + COMMA_SEP +
	    TeaContract.COLUMN_NAME_RESID + TEXT_TYPE + COMMA_SEP +
	    TeaContract.COLUMN_NAME_STEEP_TIME + NUMBER_TYPE +
	    " )";

	private static final String SQL_DELETE_ENTRIES =
	    "DROP TABLE IF EXISTS " + TABLE_NAME;
	
	//the contract should never be instantiated
	private TeaContract(){};
}
