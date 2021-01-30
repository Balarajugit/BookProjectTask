package com.app.domain;

import org.springframework.stereotype.Component;

@Component
public class ArticalName {
	
	private String articalName;

	public ArticalName() {
		super();
	}

	public String getArticalName() {
		return articalName;
	}

	public void setArticalName(String articalName) {
		this.articalName = articalName;
	}

	@Override
	public String toString() {
		return "ArticalName [articalName=" + articalName + "]";
	}
	
	

}
