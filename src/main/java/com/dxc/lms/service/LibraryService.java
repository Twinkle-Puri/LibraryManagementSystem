package com.dxc.lms.service;

import java.util.List;

import com.dxc.lms.exception.LibraryException;
import com.dxc.lms.model.Library;

public interface LibraryService {
	
	
	void addLibrary(Library library) throws LibraryException;
	void deleteLibrary(int bcode) throws LibraryException;
	List<Library> getAllLibrarys() throws LibraryException;
	Library getLibraryByBcode(int bcode) throws LibraryException;

}
