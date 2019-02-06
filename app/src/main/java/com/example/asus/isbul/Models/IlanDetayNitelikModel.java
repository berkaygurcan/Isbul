package com.example.asus.isbul.Models;

public class IlanDetayNitelikModel{
	private String ilanid;
	private String id;
	private String nitelik;

	public void setIlanid(String ilanid){
		this.ilanid = ilanid;
	}

	public String getIlanid(){
		return ilanid;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNitelik(String nitelik){
		this.nitelik = nitelik;
	}

	public String getNitelik(){
		return nitelik;
	}

	@Override
 	public String toString(){
		return 
			"IlanDetayNitelikModel{" + 
			"ilanid = '" + ilanid + '\'' + 
			",id = '" + id + '\'' + 
			",nitelik = '" + nitelik + '\'' + 
			"}";
		}
}
