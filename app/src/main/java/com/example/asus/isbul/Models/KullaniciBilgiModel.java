package com.example.asus.isbul.Models;

public class KullaniciBilgiModel{
	private String durum;
	private String mailadres;
	private String dogrulamakodu;
	private String parola;
	private String id;
	private String kullaniciadi;

	public void setDurum(String durum){
		this.durum = durum;
	}

	public String getDurum(){
		return durum;
	}

	public void setMailadres(String mailadres){
		this.mailadres = mailadres;
	}

	public String getMailadres(){
		return mailadres;
	}

	public void setDogrulamakodu(String dogrulamakodu){
		this.dogrulamakodu = dogrulamakodu;
	}

	public String getDogrulamakodu(){
		return dogrulamakodu;
	}

	public void setParola(String parola){
		this.parola = parola;
	}

	public String getParola(){
		return parola;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
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
			"KullaniciBilgiModel{" + 
			"durum = '" + durum + '\'' + 
			",mailadres = '" + mailadres + '\'' + 
			",dogrulamakodu = '" + dogrulamakodu + '\'' + 
			",parola = '" + parola + '\'' + 
			",id = '" + id + '\'' + 
			",kullaniciadi = '" + kullaniciadi + '\'' + 
			"}";
		}
}
