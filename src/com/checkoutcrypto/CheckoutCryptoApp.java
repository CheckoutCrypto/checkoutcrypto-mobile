package com.checkoutcrypto;

import com.checkoutcrypto.ccProvider.ccService;
import com.checkoutcrypto.ccProvider.datamodel;
import com.checkoutcrypto.ccProvider.dataprovider;
import com.checkoutcrypto.data.PrefObj;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;


public class CheckoutCryptoApp extends Application{
	protected static final String TAG = "cc_service";
	dataprovider mProvider;
	boolean mBounded, mSyncBounded;

	@Override
	public void onCreate(){
		super.onCreate();
		init();
	}

	ccService mIRemoteService;
	private ServiceConnection mConnection = new ServiceConnection() {
	    // Called when the connection with the service is established
	    public void onServiceConnected(ComponentName className, IBinder service) {
	        // Following the example above for an AIDL interface,
	        // this gets an instance of the IRemoteInterface, which we can use to call on the service
	        mIRemoteService = ccService.Stub.asInterface(service);
	    }

	    // Called when the connection with the service disconnects unexpectedly
	    public void onServiceDisconnected(ComponentName className) {
	        Log.e(TAG, "Service has unexpectedly disconnected");
	        mIRemoteService = null;
	    }
	};

	/*
	 * Network login and init
	 */
	protected void init(){
		Intent mIntent = new Intent(this, dataprovider.class);
		bindService(mIntent, mConnection, BIND_AUTO_CREATE);
	}
	
	//// get preference settings
	public PrefObj getSettings() {
		PrefObj pref = new PrefObj();
		pref.set(getAppName(), getKey());
		return pref;
	}
	/// set preference settings
	public void setSettings(PrefObj pref, boolean update){
		try {
			mIRemoteService.setKey(pref.ID, pref.APPNAME, pref.APIKEY, pref.UPDATED, update);
			System.out.println("key set to = " +pref.APIKEY);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getKey(){
	//	String[] arr = new String[datamodel.Settings.colSize];
		String key = "";
		try {
			key = mIRemoteService.getKey();
			System.out.println("found key = " +key);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}
	public String getAppName(){
		String getAppName = "";

		try {
			getAppName = mIRemoteService.getAppName();
			System.out.println("address = " +getAppName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getAppName;
	}
}
