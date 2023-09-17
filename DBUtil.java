package org.alphind.alphamcs.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;;

public class DBUtil {

	public DBUtil() {
		// Loading the required JDBC Driver class
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private Connection getConnection(String connectionString) throws SQLException, ClassNotFoundException {
		// Creating a connection to the database
		return DriverManager.getConnection(connectionString);
	}

	public Map<String, String> executeQuery(String connectionString, String query) {
		Map<String, String> resultMap = new HashMap<>();
		try {
			Connection conn = getConnection(connectionString);
			// Executing SQL query and fetching the result
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			ResultSetMetaData meta = rs.getMetaData();
			while (rs.next()) {
				for (int i = 1; i <= meta.getColumnCount(); i++) {
					String key;
					if(i-1>1 && meta.getColumnName(i).equals(meta.getColumnName(i-1)))
					{
						 key = meta.getColumnName(i)+i;
						 System.out.println(key);
					}else {
					key = meta.getColumnName(i); 
					}
					String value = rs.getString(key);					
					resultMap.put(key, value);
				}
			}
			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	public void executeUpdate(String connectionString, String query) {
		try {
			Connection conn = getConnection(connectionString);
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executeSP(String connectionString, String storedProcedureName) {
		try {
			Connection conn = getConnection(connectionString);
			String SQL = "{call " + storedProcedureName + "}";// + "(?)}";
			CallableStatement cs = conn.prepareCall(SQL);
			ResultSet rs = cs.executeQuery(); 
			ResultSetMetaData meta = rs.getMetaData();			
			while (rs.next()) 
			{ 
				for (int i = 1; i <= meta.getColumnCount(); i++) 
				{ 
					String key = meta.getColumnName(i); 
					String value = rs.getString(key);
					//System.out.println(key + " " + value); 
				} 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	//created new method to overload the executeSP which can be used for executing SP with parameters
	//created by Nandhalala on 26-June-2023
	public void executeSP(String connectionString, String storedProcedureName, Map<String, String> queryParameters) {
		try {
			Connection conn = getConnection(connectionString);
			int numOfParameters = queryParameters.size();
			
			String SQL = "{call " + storedProcedureName;//  + "(?)}";
			
			
			
			//to add number of parameters in the sql statement
			String parameterLength = "(";
			for(int i = 0; i < numOfParameters; i++) {
				if(i+1 == numOfParameters) {
					parameterLength+="?)};";
				}
				else {
					parameterLength+="?,";
				}	
			}
			SQL += parameterLength;
			
			CallableStatement cs = conn.prepareCall(SQL);
			
			//added for set parameters to the statement
			for(Map.Entry<String , String> parameter : queryParameters.entrySet()) {
				cs.setNString(parameter.getKey(), parameter.getValue());
			}
			//cs.setNString(storedProcedureName, SQL);
			
			//get the output resultset
			ResultSet rs = cs.executeQuery(); 
			ResultSetMetaData meta = rs.getMetaData();			
			while (rs.next()) 
			{ 
				for (int i = 1; i <= meta.getColumnCount(); i++) 
				{ 
					
					String key = meta.getColumnName(i);
					String value = rs.getString(key);					
				}			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> executeQueryHeader(String connectionString, String query) {
		List<String> resultMap = new ArrayList<String>();
		try {
			Connection conn = getConnection(connectionString);
			// Executing SQL query and fetching the result
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData meta = rs.getMetaData();
			while (rs.next()) 
			{ 
				for (int i = 1; i <= meta.getColumnCount(); i++) 
				{ 
					
					String key = meta.getColumnName(i);
					String value = rs.getString(key);	
					System.out.println(resultMap.add(value));
				}			
				}
			
			if (conn != null) {
				conn.close();
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return (List<String>) resultMap;
	}
	
}
