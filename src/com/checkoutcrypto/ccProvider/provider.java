package com.checkoutcrypto.ccProvider;

import com.checkoutcrypto.data.PrefObj;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;



	/**
	 * This class defines the TunaProfileContentProvider.
	 * When registered with in the Android manifest file, the Android runtime
	 * will manage the instantiation and shutdown of the provider.
	 * @author vladimir
	 *
	 */
	public class provider extends ContentProvider {
		private database tunaDb;

		@Override
		public boolean onCreate() {
			Context ctx = getContext();
			tunaDb = new database(ctx);
			return true;
		}
		
		/**
		 * Utility function to return the mime type based on a given URI
		 */
		@Override
		public String getType(Uri uri) {
			final int match = datamodel.URI_MATCHER.match(uri);
			switch(match){
			case datamodel.Settings.PATH_TOKEN:
				return datamodel.Settings.CONTENT_TYPE_DIR;
	        default:
	            throw new UnsupportedOperationException ("URI " + uri + " is not supported.");
			}	
		}

		@Override
		public Uri insert(Uri uri, ContentValues values) {
			SQLiteDatabase db = tunaDb.getWritableDatabase();
			int token = datamodel.URI_MATCHER.match(uri);
			switch(token){
				case datamodel.Settings.PATH_TOKEN:{
					boolean replace = false;
					  String grpID = "";
					    if ( values.containsKey( datamodel.SQL_INS_REP ) ){
					        replace = values.getAsBoolean( datamodel.SQL_INS_REP );
					        values = new ContentValues( values );
					        values.remove( datamodel.SQL_INS_REP );
					    }

					    if( values.containsKey(datamodel.Settings.Cols.APPID)){
					    	grpID = values.getAsString(datamodel.Settings.Cols.APPID);
					    }
					    long rowId;
					    if ( replace ) {
					        rowId = db.update(datamodel.Settings.NAME, values, datamodel.Settings.Cols.APPID +"=?", new String[] {grpID} );
					    } else {
					        rowId = db.insert(datamodel.Settings.NAME, null, values);
					    }
					    getContext().getContentResolver().notifyChange(uri, null);
						return datamodel.Settings.CONTENT_URI.buildUpon().appendPath(String.valueOf(rowId)).build();
				}
	            default: {
	                throw new UnsupportedOperationException("URI: " + uri + " not supported.");
	            }
			}
		} 

		/**
		 * Function to query the content provider.  This example queries the backing database.
		 * It uses the SQLite API to retrieve TunaProfile data based on the URI specified.
		 */
		public Cursor MyQuery(Uri uri, String[] projection, String selection,
				String[] selectionArgs, String sortOrder, String limit) {
			

			SQLiteDatabase db = tunaDb.getReadableDatabase();
			final int match = datamodel.URI_MATCHER.match(uri);
			switch(match){
				case datamodel.Settings.PATH_TOKEN:{
					SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
					builder.setTables(datamodel.Settings.NAME);
					return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder, limit);
				}
				default: return null;
			}
		}
		@Override
		public int delete(Uri uri, String selection, String[] selectionArgs) {
			final int match = datamodel.URI_MATCHER.match(uri);
		    SQLiteDatabase sqlDB = tunaDb.getWritableDatabase();
		    int rowsAffected = 0;
		    switch (match) {
		    case datamodel.Settings.PATH_TOKEN:
		        rowsAffected = sqlDB.delete(datamodel.Settings.NAME,
		                selection, selectionArgs);
		        break;
		    default:
		        throw new IllegalArgumentException("Unknown or Invalid URI " + uri);
		    }
		    getContext().getContentResolver().notifyChange(uri, null);
		    return rowsAffected;
		}

		@Override
		public int update(Uri uri, ContentValues values, String selection,
				String[] selectionArgs) {
			return 0;
		}

		@Override
		public Cursor query(Uri uri, String[] projection, String selection,
				String[] selectionArgs, String sortOrder) {
			
			SQLiteDatabase db = tunaDb.getReadableDatabase();
			final int match = datamodel.URI_MATCHER.match(uri);
			switch(match){
				case datamodel.Settings.PATH_TOKEN:{
					SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
					builder.setTables(datamodel.Settings.NAME);
					return builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
				}
				default: return null;
			}

	}
}