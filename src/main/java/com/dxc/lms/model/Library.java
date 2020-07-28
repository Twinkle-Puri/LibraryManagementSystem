package com.dxc.lms.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Library implements Serializable{
	
	private int bcode;
	private String title;
	private double price;
	private LocalDate publishedDate;
	
	public Library() {
		//left unimplemented
	}

	public Library(int bcode, String title, double price, LocalDate publishedDate) {
		super();
		this.bcode = bcode;
		this.title = title;
		this.price = price;
		this.publishedDate = publishedDate;
	}

	public int getBcode() {
		return bcode;
	}

	public void setBcode(int bcode) {
		this.bcode = bcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	

}
