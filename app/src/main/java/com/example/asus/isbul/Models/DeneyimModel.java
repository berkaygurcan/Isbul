package com.example.asus.isbul.Models;

public class DeneyimModel{
	private String sirket;
	private String kid;
	private String id;
	private String title;
	private String yil;

	public void setSirket(String sirket){
		this.sirket = sirket;
	}

	public String getSirket(){
		return sirket;
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

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setYil(String yil){
		this.yil = yil;
	}

	public String getYil(){
		return yil;
	}

	@Override
 	public String toString(){
		return 
			"DeneyimModel{" + 
			"sirket = '" + sirket + '\'' + 
			",kid = '" + kid + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",yil = '" + yil + '\'' + 
			"}";
		}
}
