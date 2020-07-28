package com.dxc.lms.service;

import java.time.LocalDate;
import java.util.ArrayList;
//import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dxc.lms.dao.LibraryDao;
import com.dxc.lms.dao.LibraryDaoJDBCImpl;
import com.dxc.lms.exception.LibraryException;
import com.dxc.lms.model.Library;

public class LibraryServiceImpl implements LibraryService {

	private LibraryDao libraryDao;
	private static final Logger logger = Logger.getLogger("LibraryService");
	
	public LibraryServiceImpl () throws LibraryException{
		this.libraryDao = new LibraryDaoJDBCImpl();
	}
	
	private boolean isValidBcode(int bcode) throws LibraryException {
		
		return bcode > 0 && (libraryDao.getLibraryByBcode(bcode)==null);
	}
	private boolean isValidTitle(String title) {
		return title!=null && title.length()>5 && title.length()<20;
	}
	private boolean isValidPrice(double price) {
		return price>0;
	}
	
	private boolean isValidPublishedDate(LocalDate packageDate) {
		LocalDate today = LocalDate.now();
		//return packageDate.isBefore(today) || packageDate.equals(today);
		return !packageDate.isAfter(today);
	}
	
	private boolean isValidLibrary(Library library) throws LibraryException {
		
		boolean isValid=true;
		
		if(library==null) {
			isValid=false;
			throw new LibraryException("Item can not null");
			
		}
		
		List<String> errMsgs = new ArrayList<String>();
				
		if(!isValidBcode(library.getBcode())){
			logger.error("Err: Icode can be zero or negative");
			errMsgs.add("Err: Icode can be zero or negative, Icode can not be repetative.");
		}
		if(!isValidTitle(library.getTitle())){
			logger.error(" Title can not be blank, and must be of 5 to 20 chars");
			errMsgs.add("Err: Title can not be blank, and must be of 5 to 20 chars in length.");
		}
		if(!isValidPrice(library.getPrice())){
			logger.error("Price can not be zero or negative");
			errMsgs.add("Err: Price can not be zero or negative.");
		}
		if(!isValidPublishedDate(library.getPublishedDate())) {
			logger.error("Published Date can not be a future data");
			errMsgs.add("Err: Published Date can not be a future date.");
		}
	
		if(errMsgs.size()>0) {
			isValid=false;
			throw new LibraryException(errMsgs.toString());
		}
		
		return isValid;
	}

	@Override
	public void addLibrary(Library library) throws LibraryException {
		if(isValidLibrary(library)) {
			libraryDao.addLibrary(library);
		}

	}

	@Override
	public void deleteLibrary(int bcode) throws LibraryException {
		libraryDao.deleteLibrary(bcode);

	}

	@Override
	public List<Library> getAllLibrarys() throws LibraryException{
		// TODO Auto-generated method stub
		return libraryDao.getAllLibrarys();
	}

	@Override
	public Library getLibraryByBcode(int bcode) throws LibraryException{
		// TODO Auto-generated method stub
		return libraryDao.getLibraryByBcode(bcode);
	}

}
