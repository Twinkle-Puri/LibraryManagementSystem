package com.dxc.lms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dxc.lms.exception.LibraryException;
import com.dxc.lms.model.Library;

public class LibraryDaoJDBCImpl implements LibraryDao {
	
	static {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException exp) {
			System.out.println(exp.getMessage());
			System.exit(0);
		}
	}
	private static final Logger logger = Logger.getLogger("LibraryDao");
	private static final String dbUrl = "jdbc:mysql://localhost:3306/dxcbatch";
	private static final String dbUnm = "root";
	private static final String dbPwd = "root";
	
	private static final String INS_BOOKS_QRY =
			"INSERT INTO BOOKS(BCODE,TITLE,price,PDate) VALUES(?,?,?,?)";
	private static final String DEL_BOOKS_QRY =
			"DELETE FROM BOOKS WHERE BCODE =?";
	private static final String SEL_ALL_BOOKS_QRY=
			"SELECT BCODE,TITLE,price,PDate FROM BOOKS";
	private static final String SEL_ITEM_BCODE_QRY=
			"SELECT BCODE,TITLE,price,PDate From BOOKS where BCODE=?";
	@Override
	public void addLibrary(Library library) throws LibraryException {
		if(library != null) {
			try(Connection con = DriverManager.getConnection(dbUrl,dbUnm,dbPwd)){
				PreparedStatement pinsert = con.prepareStatement(INS_BOOKS_QRY);
				pinsert.setInt(1, library.getBcode());
				pinsert.setString(2, library.getTitle());
				pinsert.setDouble(3,library.getPrice());
				pinsert.setDate(4, Date.valueOf(library.getPublishedDate()));
			
				pinsert.executeUpdate();
				logger.info("Book added successfully!..");
			}catch(SQLException exp) {
				logger.error(exp.toString());
				throw new LibraryException("Sorry! ADD Book Operation failed!");
				
			}
		}
	}

	@Override
	public void deleteLibrary(int bcode) throws LibraryException {
		try(Connection con = DriverManager.getConnection(dbUrl,dbUnm,dbPwd)){
			PreparedStatement pdelete = con.prepareStatement(DEL_BOOKS_QRY);
			pdelete.setInt(1, bcode);
			
			pdelete.executeUpdate();
			logger.info("Book deleted successfully!..");
		}catch(SQLException exp) {
			logger.error(exp.toString());
			throw new LibraryException("Sorry! delete Book operation failed");
		}
	}

	@Override
	public List<Library> getAllLibrarys() throws LibraryException {
		List<Library> librarys = new ArrayList<Library>();
		try(Connection con = DriverManager.getConnection(dbUrl,dbUnm,dbPwd)){
			PreparedStatement pselect = con.prepareStatement(SEL_ALL_BOOKS_QRY);
			
			ResultSet rs = pselect.executeQuery();
			while(rs.next()) {
				Library library = new Library();
				
				library.setBcode(rs.getInt(1));
				library.setTitle(rs.getString(2));
				library.setPrice(rs.getDouble(3));
				library.setPublishedDate(rs.getDate(4).toLocalDate());
				
				librarys.add(library);
			}
			logger.info("Could not retrieve book data!..");
		}catch(SQLException exp) {
			logger.error(exp.toString());
			throw new LibraryException("Sorry! could not retrieve data..");
		}
		return librarys;
	}

	@Override
	public Library getLibraryByBcode(int bcode) throws LibraryException {
		
		Library library = null;
		try(Connection con = DriverManager.getConnection(dbUrl,dbUnm,dbPwd)){
			PreparedStatement pselect = con.prepareStatement(SEL_ITEM_BCODE_QRY);
			pselect.setInt(1, bcode);
			
			ResultSet rs = pselect.executeQuery();
			if(rs.next()) {
				library = new Library();
				library.setBcode(rs.getInt(1));
				library.setTitle(rs.getString(2));
				library.setPrice(rs.getDouble(3));
				library.setPublishedDate(rs.getDate(4).toLocalDate());
			}
			logger.info("Book couldn't successfully!..");
		}catch(SQLException exp) {
			logger.error(exp.toString());
			throw new LibraryException("Sorry! could not retrieve data..");
		}
		return library;
	}

}
