package com.example.asus.isbul.Models;

public class IlanDetayModel{
	private String aciklama;
	private String tanim;
	private String kid;
	private String departman;
	private String calismasekli;
	private String id;
	private String adres;
	private String firmasektoru;
	private String pozisyonseviyesi;
	private String tecrube;
	private String baslik;
	private String egitimbilgisi;

	public void setAciklama(String aciklama){
		this.aciklama = aciklama;
	}

	public String getAciklama(){
		return aciklama;
	}

	public void setTanim(String tanim){
		this.tanim = tanim;
	}

	public String getTanim(){
		return tanim;
	}

	public void setKid(String kid){
		this.kid = kid;
	}

	public String getKid(){
		return kid;
	}

	public void setDepartman(String departman){
		this.departman = departman;
	}

	public String getDepartman(){
		return departman;
	}

	public void setCalismasekli(String calismasekli){
		this.calismasekli = calismasekli;
	}

	public String getCalismasekli(){
		return calismasekli;
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

	public void setFirmasektoru(String firmasektoru){
		this.firmasektoru = firmasektoru;
	}

	public String getFirmasektoru(){
		return firmasektoru;
	}

	public void setPozisyonseviyesi(String pozisyonseviyesi){
		this.pozisyonseviyesi = pozisyonseviyesi;
	}

	public String getPozisyonseviyesi(){
		return pozisyonseviyesi;
	}

	public void setTecrube(String tecrube){
		this.tecrube = tecrube;
	}

	public String getTecrube(){
		return tecrube;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	public void setEgitimbilgisi(String egitimbilgisi){
		this.egitimbilgisi = egitimbilgisi;
	}

	public String getEgitimbilgisi(){
		return egitimbilgisi;
	}

	@Override
 	public String toString(){
		return 
			"IlanDetayModel{" + 
			"aciklama = '" + aciklama + '\'' + 
			",tanim = '" + tanim + '\'' + 
			",kid = '" + kid + '\'' + 
			",departman = '" + departman + '\'' + 
			",calismasekli = '" + calismasekli + '\'' + 
			",id = '" + id + '\'' + 
			",adres = '" + adres + '\'' + 
			",firmasektoru = '" + firmasektoru + '\'' + 
			",pozisyonseviyesi = '" + pozisyonseviyesi + '\'' + 
			",tecrube = '" + tecrube + '\'' + 
			",baslik = '" + baslik + '\'' + 
			",egitimbilgisi = '" + egitimbilgisi + '\'' + 
			"}";
		}
}
