package com.example.asus.isbul.Models;

public class BasvuruSonucModel{
	private String text;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"BasvuruSonucModel{" + 
			"text = '" + text + '\'' + 
			"}";
		}
}
