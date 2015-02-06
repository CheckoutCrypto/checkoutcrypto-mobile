package com.checkoutcrypto.data;

import java.util.ArrayList;

public class coins {
	
	public class coin{
		public String coin_code;
		public String coin_name;
		public String coin_image;
		public double coin_rate;
	}
	
	public ArrayList<coin> EnabCoins;
	int count;

	
	
	public coins(){
		this.EnabCoins = new ArrayList<coin>();
		count = 0;
	}
	
	public void addCoin(String coin_name, String coin_code, String coin_image, double coin_rate){
		
		coin cn = new coin();
		cn.coin_code = coin_code;
		cn.coin_name = coin_name;
		cn.coin_image = coin_image;
		cn.coin_rate = coin_rate;
		
		this.EnabCoins.set(count, cn);
		count++;
	}
	
	public void displayCoins(){
		for(int i=0; i<this.EnabCoins.size(); i++){
			System.out.println(EnabCoins.get(i).coin_code);
		}
	}
	
}
