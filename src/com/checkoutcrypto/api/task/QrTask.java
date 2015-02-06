package com.checkoutcrypto.api.task;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.checkoutcrypto.CheckoutMain.APIReplyListener;
import com.checkoutcrypto.api.apiCalls;

public class QrTask extends AsyncTask<Void, Void, Void>{
	
	private static final String TAG = null;
	private int SYNC_TYPE = 0;
	Context ctx;
	APIReplyListener listener;
	Bitmap  qr;
	String address = "";
	apiCalls api;
	
	public QrTask(Context cont, String qrAddress){
		ctx = cont;
		address = qrAddress;
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		String url_qr_base = "";
		String url_qr_args = "";
		 url_qr_base = "https://chart.googleapis.com/chart?cht=qr";
         url_qr_args = "&chs=350";
        url_qr_args += "&choe=UTF8";
         url_qr_args += "&chld=L";
         url_qr_args += "&chl="+address;
         
         String src = "";
         src = url_qr_base + url_qr_args;
         System.out.println(src);
         qr = getBitmapFromURL(src);
		return null;
		
	}
	
	public Bitmap getBitmapFromURL(String src){
		try {
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
    @Override
	protected void onPostExecute(Void vo) {
    	listener.updateImg(qr);
    }

    
    public void setListener(APIReplyListener data){
    	this.listener = data;
    }
}

