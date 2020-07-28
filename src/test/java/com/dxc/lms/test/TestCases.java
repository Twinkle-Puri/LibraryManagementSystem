package com.dxc.lms.test;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dxc.lms.model.Library;

public class TestCases {
	
	static Library lib;
	
	@BeforeEach
	public void setUp() {
		lib = new Library();
		lib.setBcode(11);
		lib.setTitle("Hello");
		lib.setPrice(10.9);
		lib.setPublishedDate(LocalDate.now());
	}
	
	@AfterEach
	public void tearDown() {
		lib = null;
	}
	@DisplayName("Testing all data entered by users")
	@Test
	public void testbCode() {
		Assertions.assertEquals(11,lib.getBcode()); 
	}
	
	@DisplayName("Testing Book Title")
	@Test
	public void testTitle() {
		Assertions.assertEquals("Hello",lib.getTitle());
		
	}
	
	@DisplayName("Testing Price")
	@Test
	public void testPrice() {
		Assertions.assertEquals(10.9,lib.getPrice());
	}
	
	@DisplayName("Testing Published Date")
	@Test
	public void testPublishedDate() {
		Assertions.assertEquals(LocalDate.now(),lib.getPublishedDate());
	}

}
