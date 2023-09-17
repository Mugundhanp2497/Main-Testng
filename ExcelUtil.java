package org.alphind.alphamcs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelUtil {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;

	public static String[][] getExcelDataIn2DArray(String Path, String SheetName) throws Exception {
		String[][] excelDataArray = null;
		try {

			FileInputStream ExcelFile = new FileInputStream(Path);

			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int numOfColumns = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
			int numOfRows = ExcelWSheet.getPhysicalNumberOfRows();

			excelDataArray = new String[numOfRows - 1][numOfColumns];

			for (int i = 1; i < numOfRows; i++) {

				for (int j = 0; j < numOfColumns; j++) {
					excelDataArray[i - 1][j] = ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return excelDataArray;
	}

	public static Map<String, String> getTestCaseDataInMap(String filePath, String sheetName, String methodName) {

		// Create Fillo instance
		Fillo fillo = new Fillo();

		// Create Connection object
		Connection connection = null;

		// Create HashMap to store the data
		Map<String, String> dataMap = new HashMap<>();

		try {
			// Open Excel file
			connection = fillo.getConnection(filePath);

			// Execute query to fetch all data from the sheet
			String query = "SELECT * FROM " + sheetName + " where testName = '" + methodName + "'";
			Recordset recordset = connection.executeQuery(query);

			// Iterate through each row
			while (recordset.next()) {
				ArrayList<String> collection = recordset.getFieldNames();
				int size = collection.size();
				for (int i = 0; i <= (size - 1); i++) {
					String key = collection.get(i);
					String value = recordset.getField(key);

					// Store the key-value pair in the map
					dataMap.put(key, value);
				}

				if (connection != null) {
					connection.close();
				}
			}

			// Print the map
//			for (Map.Entry<String, String> entry : dataMap.entrySet()) {
//				System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//			}
		} catch (FilloException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataMap;
	}
	
	public static int updateExcelData(String filePath, String sheetName, String methodName, String columnName, String value) {
		
		Fillo fillo = new Fillo();
		
		Connection connection = null;
		
		int status = 0;
		
		try {
			connection = fillo.getConnection(filePath);
		
		
		String query = "Update "+sheetName+" set "+columnName+" = '"
				+value+"' where testName = '"+methodName+"'";
		
		status = connection.executeUpdate(query);
		
		}
		
		catch (FilloException e) {
			e.printStackTrace();
		}
		
		return status;
		
	}
	
}
