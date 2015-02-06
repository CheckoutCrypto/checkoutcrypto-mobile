package com.checkoutcrypto.ccProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "cc_db1_5.db";
	private static final int DATABASE_VERSION = 1;
	
	public database(Context ctx){
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE " + datamodel.Settings.NAME+ " ( " +
				datamodel.Settings.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				datamodel.Settings.Cols.APPID 	+ " TEXT DEFAULT '0' , " +
				datamodel.Settings.Cols.APPNAME 	+ " TEXT DEFAULT '0' , " +
				datamodel.Settings.Cols.APIKEY 	+ " TEXT DEFAULT '0' , " +
				datamodel.Settings.Cols.UPDATED 	+ " TEXT DEFAULT '0' , " +
				"UNIQUE (" + 
					datamodel.Settings.Cols.ID + 
				") ON CONFLICT REPLACE)"
			);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}
}
