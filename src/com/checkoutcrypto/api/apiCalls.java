package com.checkoutcrypto.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Context;

import com.checkoutcrypto.CheckoutCryptoApp;
import com.checkoutcrypto.data.PrefObj;
import com.checkoutcrypto.data.wallet;
import com.checkoutcrypto.api.connectAPI;
public class apiCalls {

	String apikey = "";
	PrefObj pref;
	String coin_code = "POT";
	String charset = "UTF-8";
	connectAPI connect;

	public apiCalls(String api_key)
	{
		apikey = api_key;
		connect = new connectAPI();

	}
	public String getKey(){
		String apikey = "";
		
		/// get key from provider
		
		return apikey;
	}
	
	public String getnewaddress(String coin) throws UnsupportedEncodingException{
		String queueid = "", address = "";
		//key = getKey();
		
		String action = "getnewaddress";
		
		String query = String.format("apikey=%s&action=%s&coin=%s", 
			     URLEncoder.encode(apikey, charset), 
			     URLEncoder.encode(action, charset),
			     URLEncoder.encode(coin_code, charset));
		
		queueid = connect.begin(query);
		System.out.println("the queue id is "+queueid);
		
		action="getstatus";
		
		query = String.format("apikey=%s&action=%s&queueid=%s", 
			     URLEncoder.encode(apikey, charset), 
			     URLEncoder.encode(action, charset),
			     URLEncoder.encode(queueid, charset));
		address = connect.finishCall(query);
		
		System.out.println("the generated address is "+address);
		return address;
	}
	
	public void getbalance(String coin){
		
		// key = "";
		//key = getKey();
		String action = "getbalance";

	}
	
	public void getreceivedbyaddress(wallet wall){
		
		//String key = "";
		//key = getKey();
		String action = "getnewaddress";

	}
	
	public void getstatus(int orderid){
		String action = "getstatus";

		/*String key = "";
		key = getKey(); */
		
	}
}
