package com.example.asus.isbul.Models;

public class EgitimModel{
	private String bitis;
	private String universite;
	private String kid;
	private String baslangic;
	private String id;
	private String bolum;

	public void setBitis(String bitis){
		this.bitis = bitis;
	}

	public String getBitis(){
		return bitis;
	}

	public void setUniversite(String universite){
		this.universite = universite;
	}

	public String getUniversite(){
		return universite;
	}

	public void setKid(String kid){
		this.kid = kid;
	}

	public String getKid(){
		return kid;
	}

	public void setBaslangic(String baslangic){
		this.baslangic = baslangic;
	}

	public String getBaslangic(){
		return baslangic;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setBolum(String bolum){
		this.bolum = bolum;
	}

	public String getBolum(){
		return bolum;
	}

	@Override
 	public String toString(){
		return 
			"EgitimModel{" + 
			"bitis = '" + bitis + '\'' + 
			",universite = '" + universite + '\'' + 
			",kid = '" + kid + '\'' + 
			",baslangic = '" + baslangic + '\'' + 
			",id = '" + id + '\'' + 
			",bolum = '" + bolum + '\'' + 
			"}";
		}
}
