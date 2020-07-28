package com.dxc.lms.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dxc.lms.exception.LibraryException;
import com.dxc.lms.model.Library;
import com.dxc.lms.service.LibraryService;
import com.dxc.lms.service.LibraryServiceImpl;

public class LibraryManagementsystemApp {
	
	private static Scanner kbScanner;
	private static LibraryService libraryService;
	
	private static final String DATE_FORMAT = "dd-MM-yyyy";
	
	
	
	private static void doAddLibrary() {
		Library library = new Library();
		
		System.out.print("bcode>> "); 
		//List<Library> librarysList = libraryService.getAllLibrarys(); 
	
		while(!kbScanner.hasNextInt()) {
			System.out.println("A non-fractional number expected!");
			System.out.print("bcode>> ");
		}
		library.setBcode(kbScanner.nextInt());
		
		System.out.print("Title>> ");		
		library.setTitle(kbScanner.next());
		
		System.out.print("Price>> ");
		while(!kbScanner.hasNextDouble()) {
			System.out.println("A fractional number expected !");
			System.out.print("Price>> ");
		}
		library.setPrice(kbScanner.nextDouble());
		
		System.out.print("PublishedDate("+DATE_FORMAT+")>> ");
		String pdStr = kbScanner.next();
		library.setPublishedDate(LocalDate.parse(pdStr, DateTimeFormatter.ofPattern(DATE_FORMAT)));
	
		try {
			libraryService.addLibrary(library);
			System.out.println("Item is Saved!");
		} catch (LibraryException exp) {
			System.out.println(exp.getMessage());
		}
		
		
	}

	
	private static void doDispalyLibrary()   {
		
		try {
			 
			 List<Library>	librarysList = libraryService.getAllLibrarys();
		
		
		if(librarysList==null || librarysList.size()==0) {
			System.out.println("No Item Records Yet! Try adding few ");
		}else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
			System.out.println("ICODE\tTitle\tPrice\tPackageDate");
			System.out.println("==================================================");
			for(Library library: librarysList) {
				System.out.println(library.getBcode() 
						+"\t" + library.getTitle()
						+"\t" + library.getPrice()
						+"\t" + library.getPublishedDate().format(dtf)
						);
			}
		}
		} catch (LibraryException exp) {
			
			System.out.println(exp.getMessage());		
			} 
	}


	private static void doDeleteLibrary() {
		System.out.print("Bcode>> ");
		while(!kbScanner.hasNextInt()) {
			System.out.println("A non-fractional number expected!");
			System.out.print("Bcode>> ");
		}
		int bcode = kbScanner.nextInt();
		
		try {
			libraryService.deleteLibrary(bcode);
			System.out.println("Item got deleted!");
		} catch (LibraryException e) {
			System.out.println(e.getMessage());
		}	
	}

	public static void main(String[] args) {
		
		kbScanner = new Scanner(System.in);

		try {
			libraryService = new LibraryServiceImpl();
		}catch(LibraryException e) {
			System.out.println(e.getMessage());
		}
		Menu menu = null;
		
		while(menu != Menu.QUIT) {
			System.out.println("Choice \t Menu");
			for(Menu m : Menu.values()) {
				System.out.println(m.ordinal()+1 + "\t" + m);
			}
			System.out.println("Choice>> ");
			int choice = kbScanner.nextInt();
			int ordinal = choice -1;
			
			if(ordinal >=0 && ordinal<menu.values().length) {
				menu = Menu.values()[ordinal];
			} else {
				System.out.println("Invalid Choice");
				continue;
			}
			switch(menu) {
			case ADD: doAddLibrary(); break;
			case DELETE: doDeleteLibrary(); break;
			case DISPLAY: doDispalyLibrary(); break;
			case QUIT: System.out.println("Application closed"); break;
			default: System.out.println("Invalid choice"); break;
			}
		}
		kbScanner.close();
	}



}


//logger.log("msg",level);
