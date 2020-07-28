package com.dxc.lms.dao;

import java.util.List;

import com.dxc.lms.exception.LibraryException;
import com.dxc.lms.model.Library;

public interface LibraryDao  {
	
	
	void addLibrary(Library library) throws LibraryException;
	void deleteLibrary(int bcode) throws LibraryException;
	List <Library> getAllLibrarys() throws LibraryException; 	//retriving all item
	Library getLibraryByBcode(int bcode) throws LibraryException; //giving and item an bcode
	

}
