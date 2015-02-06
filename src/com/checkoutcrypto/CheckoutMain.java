package com.checkoutcrypto;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.checkoutcrypto.api.task.ApiTask;
import com.checkoutcrypto.api.task.QrTask;
import com.checkoutcrypto.display.settings;

public class CheckoutMain extends Activity implements OnClickListener{
	ApiTask at;
	CheckoutCryptoApp app;
	QrTask qt;
	TextView text;
	Button btn_address, btn_qr_open, btn_settings, btn_service;
	APIReplyListener listener;
	Bitmap img;
	ImageView imageview;
	String qr_address = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkout_main);
		app = (CheckoutCryptoApp) ((Activity) this).getApplication();

		text = (TextView)this.findViewById(R.id.input_address);
		btn_address = (Button)this.findViewById(R.id.btn_address);
		btn_qr_open = (Button)this.findViewById(R.id.btn_qr_open);
		btn_settings = (Button)this.findViewById(R.id.btn_settings);
		btn_service = (Button)this.findViewById(R.id.test_service);
		
		 btn_address.setOnClickListener(this);
		 btn_qr_open.setOnClickListener(this);
		btn_settings.setOnClickListener(this); 
		 imageview = (ImageView)this.findViewById(R.id.qr_img);
		 btn_service.setOnClickListener(this);
	}
	
    public interface APIReplyListener {

		public void updateAddress(String address);
		public void updateImg(Bitmap qr);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checkout_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		if (id == R.id.btn_address) {
			at = new ApiTask(this, "getnewaddress", "POT");
			at.setListener(new APIReplyListener(){
				@Override
				public void updateAddress(String address) {
					qr_address = address;
					text.setText(address);
				}

				@Override
				public void updateImg(Bitmap qr) {
					imageview.setImageBitmap(qr);
				}			
			});
			 at.execute();
		}else if(id == R.id.btn_qr_open){
			qt = new QrTask(this, qr_address);
			qt.setListener(new APIReplyListener(){
				@Override
				public void updateAddress(String address) {
					qr_address = address;
					text.setText(address);
				}

				@Override
				public void updateImg(Bitmap qr) {
					imageview.setImageBitmap(qr);
				}			
			});
			qt.execute();
		}else if(id == R.id.btn_settings){
			
			Intent intent = new Intent();
			intent.setClass(CheckoutMain.this, settings.class);
			startActivity(intent);
			
		}else if(id == R.id.test_service){
			String key = "";
			key = app.getKey();
			System.out.println(key);
			
		}
		
	}
	


}
