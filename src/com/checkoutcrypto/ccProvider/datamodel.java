package com.checkoutcrypto.ccProvider;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;



public class datamodel {
	
	public static final String AUTHORITY = "com.checkoutcrypto.provider";
	private static final Uri BASE_URI = Uri.parse("content://" + AUTHORITY);
	public static final UriMatcher URI_MATCHER = MatchContentURI();
	public static final String SQL_INS_REP = "sql_insert_replace";

	private static UriMatcher MatchContentURI(){
		final UriMatcher checkmatch = new UriMatcher(UriMatcher.NO_MATCH);
		final String authority = AUTHORITY;
		checkmatch.addURI(authority, Settings.PATH, Settings.PATH_TOKEN);
		checkmatch.addURI(authority, Settings.PATH_FOR_ID, Settings.PATH_FOR_ID_TOKEN);
	//	checkmatch.addURI(authority, Wallets.PATH, Wallets.PATH_TOKEN);
	//	checkmatch.addURI(authority, Wallets.PATH_FOR_ID, Wallets.PATH_FOR_ID_TOKEN);
		return checkmatch;
	}
	
	public static class Settings {
		
		// an identifying name for entity
				public static final String NAME = "settings";
				
				// define a URI paths to access entity
				// BASE_URI/restaurants - for list of restaurants
				// BASE_URI/restaurants/* - retreive specific restaurant by id
				// the toke value are used to register path in matcher (see above)
				public static final String PATH = "settings";
				public static final int PATH_TOKEN = 100;
				public static final String PATH_FOR_ID = "settings/*";
				public static final int PATH_FOR_ID_TOKEN = 200;
				
				// URI for all content stored as Restaurant entity
				// BASE_URI + PATH ==> "content://com.favrestaurant.contentprovider/restaurants";
				public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH).build();
				
				// define content mime type for entity
				public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.checkoutcrypto.app";
				public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.checkoutcrypto.app";
				public static final int colSize = 4;
				// a static class to store columns in entity
				public static class Cols {
					public static final String ID = BaseColumns._ID; // convention
					public static final String APPNAME  = "app_name";
					public static final String APIKEY = "app_api_key";
					public static final String UPDATED = "app_updated";
					public static final String APPID = "app_id";
				}
				
	}
	/*
	public static class Wallets {
		
		// an identifying name for entity
				public static final String NAME = "wallets";
				
				// define a URI paths to access entity
				// BASE_URI/restaurants - for list of restaurants
				// BASE_URI/restaurants/* - retreive specific restaurant by id
				// the toke value are used to register path in matcher (see above)
				public static final String PATH = "wallets";
				public static final int PATH_TOKEN = 300;
				public static final String PATH_FOR_ID = "wallets/*";
				public static final int PATH_FOR_ID_TOKEN = 400;
				
				// URI for all content stored as Restaurant entity
				// BASE_URI + PATH ==> "content://com.favrestaurant.contentprovider/restaurants";
				public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH).build();
				
				// define content mime type for entity
				public static final String CONTENT_TYPE_DIR = "vnd.android.cursor.dir/vnd.checkoutcrypto.app";
				public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.checkoutcrypto.app";
				
				// a static class to store columns in entity
				public static class Cols {
					public static final String ID = BaseColumns._ID; // convention
					public static final String APPID = "api_id";
					public static final String ADDRESS = "wallet_address";
					public static final String COIN = "wallet_coin";
					public static final String PENDING = "pending";
					public static final String BALANCE = "wallet_balance";
					public static final String STATUS = "wallet_status";
					public static final String UPDATED = "app_updated";
				}
				
	} */
}
