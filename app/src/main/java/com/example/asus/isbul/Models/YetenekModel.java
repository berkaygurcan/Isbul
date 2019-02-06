package com.example.asus.isbul.Models;

public class YetenekModel{
	private String yetenekcount;
	private String yetenek;
	private String yetenekid;

	public void setYetenekcount(String yetenekcount){
		this.yetenekcount = yetenekcount;
	}

	public String getYetenekcount(){
		return yetenekcount;
	}

	public void setYetenek(String yetenek){
		this.yetenek = yetenek;
	}

	public String getYetenek(){
		return yetenek;
	}

	public void setYetenekid(String yetenekid){
		this.yetenekid = yetenekid;
	}

	public String getYetenekid(){
		return yetenekid;
	}

	@Override
 	public String toString(){
		return 
			"YetenekModel{" + 
			"yetenekcount = '" + yetenekcount + '\'' + 
			",yetenek = '" + yetenek + '\'' + 
			",yetenekid = '" + yetenekid + '\'' + 
			"}";
		}
}
