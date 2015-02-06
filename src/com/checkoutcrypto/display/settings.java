package com.checkoutcrypto.display;

import com.checkoutcrypto.CheckoutCryptoApp;
import com.checkoutcrypto.R;
import com.checkoutcrypto.data.PrefObj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class settings extends Activity implements OnClickListener {

	PrefObj pref;
	TextView Register;
	EditText iKey, iName;
	Button Save, Cancel;
	CheckoutCryptoApp app;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkout_pref);
		app = (CheckoutCryptoApp) ((Activity) this).getApplication();
		pref = new PrefObj();
		
		iKey = (EditText)findViewById(R.id.pref_key);
		iName = (EditText)findViewById(R.id.pref_name);
		
		Save = (Button)findViewById(R.id.btn_pref_save);
		Cancel = (Button)findViewById(R.id.btn_pref_exit);
		
		Save.setOnClickListener(this);
		Cancel.setOnClickListener(this);
		
		/// init cached preferences
		pref = app.getSettings();
		iKey.setText(pref.APIKEY);
		iName.setText(pref.APPNAME); 
	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		String name = "";
		String api_key = "";
		boolean updated = true;
		
		if(i == R.id.btn_pref_save){
			name = iName.getText().toString();
			api_key = iKey.getText().toString();
			if(pref.APPNAME == "" && pref.APIKEY == ""){
				updated = false;
			}
			pref.set(name, api_key);
			app.setSettings(pref, updated);
		}
	}	
}
