package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NationalDataBase {
	
	public static DataBase objDataBase = new DataBase();
	public static NationalDataBase objNationalDataBase = new NationalDataBase();
	
	public void verifyEventTypeCd(String mailitm_fid) {
		
		String query =("").replaceAll("data", mailitm_fid);
	
		Connection con = objDataBase.getConnection("national");
		
		Statement stm=null;
		try {
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			while(rs.next()) {
				System.out.println("Test: "+rs.getString("RESULT"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		objDataBase.closeConnection(con);
	}
	
	public static void main(String args[]) { objNationalDataBase.verifyEventTypeCd("");}
	
}
