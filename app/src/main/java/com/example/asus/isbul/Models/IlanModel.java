package com.example.asus.isbul.Models;

public class IlanModel{
	private String aciklama;
	private String kid;
	private String id;
	private String adres;
	private String baslik;

	public void setAciklama(String aciklama){
		this.aciklama = aciklama;
	}

	public String getAciklama(){
		return aciklama;
	}

	public void setKid(String kid){
		this.kid = kid;
	}

	public String getKid(){
		return kid;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAdres(String adres){
		this.adres = adres;
	}

	public String getAdres(){
		return adres;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	@Override
 	public String toString(){
		return 
			"IlanModel{" + 
			"aciklama = '" + aciklama + '\'' + 
			",kid = '" + kid + '\'' + 
			",id = '" + id + '\'' + 
			",adres = '" + adres + '\'' + 
			",baslik = '" + baslik + '\'' + 
			"}";
		}
}
