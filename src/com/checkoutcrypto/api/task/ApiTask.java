package com.checkoutcrypto.api.task;


	import java.io.UnsupportedEncodingException;

import com.checkoutcrypto.CheckoutCryptoApp;
import com.checkoutcrypto.CheckoutMain.APIReplyListener;
import com.checkoutcrypto.api.apiCalls;
import com.checkoutcrypto.data.PrefObj;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;


	public class ApiTask extends AsyncTask<Void, Void, Void>{
		
		private static final String TAG = null;
		private int SYNC_TYPE = 0;
		Context ctx;
		boolean COMPLETE = false;
		APIReplyListener listener;
		String address = "";
		String type = "", coin = "" ;
		apiCalls api;
		PrefObj pref;
		CheckoutCryptoApp app;
		
		public ApiTask(Context cont, String callType, String cCoin){
			ctx = cont;
			type = callType;
			coin = cCoin;
	    	COMPLETE = false;
	    	app = (CheckoutCryptoApp) ((Activity) cont).getApplication();
	    	pref = new PrefObj();
	    	pref = app.getSettings();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			
			if(type == "getnewaddress"){
				//// contact api and get queueid, then check status in 1 task
				try {
					api  = new apiCalls(pref.APIKEY);
					address = api.getnewaddress(coin);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			}else if(type == "getbalance"){
			}else if(type == "getreceivedbyaddress"){
			}else if(type == "refreshcoins"){
			}

			
			return null;
		}
		
		
	    @Override
		protected void onPostExecute(Void vo) {
	    	COMPLETE = true;
	    	listener.updateAddress(address);
	    }

	    
	    public void setListener(APIReplyListener data){
	    	this.listener = data;
	    }
	}
