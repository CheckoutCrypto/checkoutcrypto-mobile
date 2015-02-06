package com.checkoutcrypto.data;

import android.text.format.Time;


public class PrefObj {

	public String APIKEY = "";
	public String APPNAME = "";
	public String UPDATED = "";
	public String ID;	
	
	
	public PrefObj(){
		APIKEY = "";
		APPNAME = "";
		UPDATED = "";
		ID = "";
	}
	
	public void set(String name, String api_key){
		APPNAME = name;
		APIKEY = api_key;
		Time now = new Time();
		now.setToNow();
		//UPDATED = now.toString();
		
	}
	
	

	
}
