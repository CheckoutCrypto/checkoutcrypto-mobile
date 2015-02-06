package com.checkoutcrypto.data;

public class wallet {
	public String address;
	public double amount;
	public String label;
	public String coin_code;
	public String updated;


	public wallet(){
		this.address = "";
		this.amount = 0.00000000;
		this.label = "";
		this.coin_code = "";
		this.updated = "";
	}
	
	public void addWallet(String wLabel, String wAddress, String coin){
		this.address = wAddress;
		this.label = wLabel;
		this.coin_code = coin;
	}
	
	public void updateAmount(double amt){
		this.amount = amt;
	}
	
	public void display(){
		System.out.println(label+" "+this.coin_code+" "+this.address+" balance:"+this.amount+" "+this.updated);
	}
}
