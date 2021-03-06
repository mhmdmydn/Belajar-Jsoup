package com.del.app.jsoup.model;

public class CocModel {
	
    private String imageUrl;
	private String title;
	private String linkNextContent;
	
	
	public CocModel(){}
	
	public CocModel(String imageUrl, String title){
		this.imageUrl = imageUrl;
		this.title = title;
	}
	
	public String getNextContent(){
		return linkNextContent;
	}

	public void setNextContent(String linkNextContent) {
		this.linkNextContent = linkNextContent;
	}
	
	public String getImageUrl(){
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}

	@Override
	public String toString() {
		return  "imageUrl : " + imageUrl+ "title" + title;
	}
}
