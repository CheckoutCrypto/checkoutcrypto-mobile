package com.checkoutcrypto.ccProvider;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import com.checkoutcrypto.CheckoutCryptoApp;
import com.checkoutcrypto.access.prefAccess;
import com.checkoutcrypto.data.PrefObj;

public class dataprovider extends Service {
	ContentResolver contentResolver;
	CheckoutCryptoApp app;
	Context ctx;
	public prefAccess pref;
	PrefObj prefob;

	@Override
	public void onCreate(){
        super.onCreate();
        ctx = this;
		app = (CheckoutCryptoApp)getApplication();
		contentResolver = this.getContentResolver();
		
		prefAccess.initAccess(this);
		pref = prefAccess.getInstance();
	}

    @Override
    public IBinder onBind(Intent intent) {
        // Return the interface
        return mBinder;
    }

    private final ccService.Stub mBinder = new ccService.Stub() {
    	public String getKey(){
    		prefob = pref.getSettings();        		
			return prefob.APIKEY;
    	}
    	public String getAppName(){
    		prefob = pref.getSettings();    		
			return prefob.APPNAME;
    	}
    	public void setKey(String uid, String appname, String apikey, String lastUpdate,  boolean update){
    		pref.newSettings(uid, appname, apikey, lastUpdate, update);
    	}
    };

	@Override
	public void onDestroy(){

	}
	
	public void test(){
		
		
		System.out.println("testing working service exported");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId){
		super.onStart(intent, startId);
	}

	@Override
	public boolean onUnbind(Intent intent){

		return super.onUnbind(intent);
	}
}