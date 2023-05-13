package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	
	public Connection getConnection(String typeOfDb) {
		String url=null;
		
		if(typeOfDb.contentEquals("state")) {
			url = "";
		}else {
			url="";
		}
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url);
		}catch(Exception objException) {
			objException.printStackTrace();
		}
		
		return con;
	}
	
	public void closeConnection(Connection objConnection){
		if(!(objConnection==null)) {
			try {
				objConnection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
