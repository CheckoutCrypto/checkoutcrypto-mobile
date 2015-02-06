package com.checkoutcrypto;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CheckoutSplash extends Activity implements OnClickListener {
	
	Button nextButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkout_splash);
		
	nextButton = (Button) findViewById(R.id.btn_next);
	nextButton.setOnClickListener(this);

	}
	
	@Override
	public void onClick(View v) {
	
		Intent intent = new Intent();
		int id = v.getId();
		if (id == R.id.btn_next) {
			intent.setClass(CheckoutSplash.this, CheckoutMain.class);
			startActivity(intent);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checkout_main, menu);
		return true;
	}

}
