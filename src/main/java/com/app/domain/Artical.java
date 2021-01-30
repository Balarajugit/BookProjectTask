package com.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Artical {
	@Id
	@GeneratedValue
	private Integer id;
	private String articalName;
	private String author;
	private String description;
	private String articalType;
	
	public Artical() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArticalName() {
		return articalName;
	}

	public void setArticalName(String articalName) {
		this.articalName = articalName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getArticalType() {
		return articalType;
	}

	public void setArticalType(String articalType) {
		this.articalType = articalType;
	}

	@Override
	public String toString() {
		return "Artical [id=" + id + ", articalName=" + articalName + ", author=" + author + ", description="
				+ description + ", articalType=" + articalType + "]";
	}


	
	
	
}
