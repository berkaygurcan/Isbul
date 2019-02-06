package com.example.asus.isbul.Models;

public class IlanPaylasimiModel{
	private boolean tf;
	private String ilanid;
	private String text;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setIlanid(String ilanid){
		this.ilanid = ilanid;
	}

	public String getIlanid(){
		return ilanid;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"IlanPaylasimiModel{" + 
			"tf = '" + tf + '\'' + 
			",ilanid = '" + ilanid + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}
