package com.example.asus.isbul.Models;

public class BasvuruListModel{
	private String mailadres;
	private String uyeid;
	private String id;
	private String baslik;
	private String kullaniciadi;

	public void setMailadres(String mailadres){
		this.mailadres = mailadres;
	}

	public String getMailadres(){
		return mailadres;
	}

	public void setUyeid(String uyeid){
		this.uyeid = uyeid;
	}

	public String getUyeid(){
		return uyeid;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	public void setKullaniciadi(String kullaniciadi){
		this.kullaniciadi = kullaniciadi;
	}

	public String getKullaniciadi(){
		return kullaniciadi;
	}

	@Override
 	public String toString(){
		return 
			"BasvuruListModel{" + 
			"mailadres = '" + mailadres + '\'' + 
			",uyeid = '" + uyeid + '\'' + 
			",id = '" + id + '\'' + 
			",baslik = '" + baslik + '\'' + 
			",kullaniciadi = '" + kullaniciadi + '\'' + 
			"}";
		}
}
