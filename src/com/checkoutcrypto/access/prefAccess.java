package com.checkoutcrypto.access;

import com.checkoutcrypto.ccProvider.datamodel;
import com.checkoutcrypto.data.PrefObj;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class prefAccess {
	static Context ctx;
	Cursor cur;
	ContentResolver contentResolver;
	public static prefAccess instance;
	
	public prefAccess(Context con){
		instance = null;
		ctx = con;
	}
	
	public static void initAccess(Context context){
		if(instance == null)
		{
			instance = new prefAccess(context);
		}
	}
	
	public static prefAccess getInstance(){
		return instance;
	}
	
	/*
	 * Write new settings  to content provider
	 */
	public void newSettings(String uid, String appname, String apikey, String updated, boolean update){		
		contentResolver = ctx.getContentResolver();
    	ContentValues val = new ContentValues();
		if(update){
			val.put(datamodel.SQL_INS_REP, true);  
		}
		val.put(datamodel.Settings.Cols.APPID, uid);
    	val.put(datamodel.Settings.Cols.APPNAME, (appname != null) ? appname : null);
    	val.put(datamodel.Settings.Cols.APIKEY, (apikey != null) ? apikey : null);
    	val.put(datamodel.Settings.Cols.UPDATED, (updated != null) ? updated : null); 

    	contentResolver.insert(datamodel.Settings.CONTENT_URI, val);
    	System.out.println("wrote to db "+contentResolver.insert(datamodel.Settings.CONTENT_URI, val));
	}
	
	/*
	 *  Get settings from content provider
	 */
	public PrefObj getSettings(){
			PrefObj sPref = new PrefObj();
			contentResolver = ctx.getContentResolver();
	        cur = contentResolver.query(datamodel.Settings.CONTENT_URI, null, null, null, null);
	            if  (cur.moveToFirst()) {
	            		sPref.ID = cur.getString(cur.getColumnIndex(datamodel.Settings.Cols.APPID));
	            		sPref.APPNAME  = cur.getString(cur.getColumnIndex(datamodel.Settings.Cols.APPNAME));
	            		sPref.APIKEY = cur.getString(cur.getColumnIndex(datamodel.Settings.Cols.APIKEY));
	            		System.out.println("FOUND APPNAME ="+sPref.APPNAME);
	            		System.out.println("FOUND APIKEY = "+sPref.APIKEY);
	            	//	sPref.UPDATED = cur.getString(cur.getColumnIndex(datamodel.Settings.Cols.UPDATED));
	            }
	        cur.close();
			return sPref;
		}
}
