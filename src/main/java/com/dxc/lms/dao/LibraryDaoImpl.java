package com.dxc.lms.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dxc.lms.exception.LibraryException;
import com.dxc.lms.model.Library;

public class LibraryDaoImpl implements LibraryDao {
	
	private static final String DATA_FILE_NAME = "Books.dat";
	
	private Map<Integer, Library> librarysMap;
	
	public LibraryDaoImpl() throws LibraryException{
		File file = new File(DATA_FILE_NAME);
		
		if(file.exists()) {
			try(ObjectInputStream oin = new ObjectInputStream
					(new FileInputStream(file))){
				librarysMap = (Map<Integer, Library>)oin.readObject();
			}catch(IOException | ClassNotFoundException exp) {
				librarysMap = new TreeMap<>();
				throw new LibraryException("Unable to read data!!!!.");
				
			}
		} else {
			librarysMap = new TreeMap<>();
		}
	}
	
	private void saveData() throws LibraryException {
		try(ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(DATA_FILE_NAME))){
			oout.writeObject(librarysMap);
	} catch(IOException exp) {
		throw new LibraryException("unable to save data..");
	}
	}

	@Override
	public void addLibrary(Library library) throws LibraryException {
		if(library!= null) {
			librarysMap.put(library.getBcode(),library);
			saveData();
		} else {
			throw new LibraryException("null item can not be filled");
		}

	}

	@Override
	public void deleteLibrary(int bcode) throws LibraryException {
		if(librarysMap.containsKey(bcode)) {
			librarysMap.remove(bcode);
			saveData();
		} else {
			throw new LibraryException("Item# " + bcode +" Not found" );
		}

	}

	@Override
	public List<Library> getAllLibrarys() {
		
		return new ArrayList<>(librarysMap.values());
	}

	@Override
	public Library getLibraryByBcode(int bcode) {

		return librarysMap.get(bcode);
	}

}
