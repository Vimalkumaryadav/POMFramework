package com.BestClass.office;

import com.opencsv.CSVReader;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVHelpers {
	
	public static boolean writeCSVValueBasedOnPrimarykey(String testDataFilesPath, String primaryColumn,
			String primaryValue, String columnName, String valueToUdate) throws IOException {
		File f = new File(testDataFilesPath);

		// read a text file

		String str = FileUtils.readFileToString(f, "UTF-8");
		System.out.println(str);
		String[] lines = str.split("\n");
		System.out.println(lines[0]);
		int ColumnHeaderCount = 0;

		// primaryColumnHeaderCount - to find the column number based on primary column
		// name

		int primaryColumnHeaderCount = 0;
		int secondaryColumnHeaderCount = 0;
		int headerCounter = 0;

		// Consider 1st row as header row and splitting the column based on ","

		String[] headers = lines[0].split(",");

		// iterating through the column to find the primary column index
		for (String expectedPrimaryHeader : headers) {
			// if expected primary column from function matches with csv column then break
			// the counter
			if (expectedPrimaryHeader.equalsIgnoreCase(primaryColumn)) {
				primaryColumnHeaderCount = headerCounter;
				break;
			}
			headerCounter++;
		}

		headerCounter = 0;

		for (String expectedPrimaryHeader : headers) {
			if (expectedPrimaryHeader.equalsIgnoreCase(columnName)) {
				secondaryColumnHeaderCount = headerCounter;
				break;
			}
			headerCounter++;
		}

		System.out.println("secondaryColumnHeaderCount:" + secondaryColumnHeaderCount);

		headerCounter = 0;

		for (String expectedColumnHeader : headers) {
			if (expectedColumnHeader.replace("\r", "").equalsIgnoreCase(columnName)) {
				ColumnHeaderCount = headerCounter;
				System.out.println("Second Column Count:" + ColumnHeaderCount);
				break;
			}
			headerCounter++;
		}

		int recordCounter = 1;
		int findRecordRow = -1;
		System.out.println(primaryColumnHeaderCount);

		for (int i = 1; i <= lines.length - 1; i++) {
			// passing variables 'primaryColumnHeaderCount' to iterate through primary
			// column to find the record to update
			String record = lines[i].split(",")[primaryColumnHeaderCount];
			System.out.println("id:" + record);
			if (String.valueOf(record).equalsIgnoreCase(primaryValue)) {
				findRecordRow = recordCounter;
				System.out.println("findRecordRow:" + findRecordRow);
			}
			recordCounter++;
		}

		if (findRecordRow == -1) {
			return false;
		}

		String getRecordFromCSV = Files.readAllLines(Paths.get(testDataFilesPath)).get(findRecordRow);

		// valueToUpdate
		String getValue = getRecordFromCSV.split(",")[ColumnHeaderCount];
		String UpdatedRecordForCSV = getRecordFromCSV.replaceFirst(getValue, valueToUdate);
		System.out.println(UpdatedRecordForCSV);

		String str1 = FileUtils.readFileToString(f, "UTF-8").replace(getRecordFromCSV, UpdatedRecordForCSV);
		FileUtils.write(f, str1, "UTF-8");

		f = null;
		return true;
	}
	
	public static HashMap<String, String> ReadCSVValueBasedOnCondition(String testDataFilePath, String columnName,
			String columnValue) throws IOException {
		CSVReader reader = null;
		LinkedHashMap map = null;
		try {

			// parsing a CSV file into CSVReader class constructor
			reader = new CSVReader(new FileReader(testDataFilePath));
			String[] nextLine;
			int counter = 0;

			// read one line at a time
			List<String> recordList = null;
			List<String> headerList = null;
			int headerCounter = 0;
			map = new LinkedHashMap();

			int initCounter = 0;
			while ((nextLine = reader.readNext()) != null) {
				headerList = Arrays.asList(nextLine);
				System.out.println("headerList:" + headerList);
				if (initCounter == 0) {
					for (String header : headerList) {
						if (header.equalsIgnoreCase(columnName)) {
							headerCounter = initCounter;
							break;
						}
						initCounter++;
					}
					break;
				}
			}
			counter = 1;
			int headerIterateCounter = 0;
			while ((nextLine = reader.readNext()) != null) {
				recordList = Arrays.asList(nextLine);
				if (recordList.get(headerCounter).equalsIgnoreCase(columnValue)) {
					for (String headers : headerList) {
						map.put(headerList.get(headerIterateCounter), recordList.get(headerIterateCounter));
						headerIterateCounter++;
					}
					break;
				}
				counter++;
			}
			System.out.println("Map:" + map);
			return map;
		} catch (Exception e) {
			return null;
		}
	}

	public static HashMap ReadCSVValueBasedOnCondition(String testDataFilePath, String columnName, String columnValue,
			String columnValue2, String columnValue3) throws IOException {
		CSVReader reader = null;
		LinkedHashMap map = null;
		try {

			// parsing a CSV file into CSVReader class constructor
			reader = new CSVReader(new FileReader(testDataFilePath));
			String[] nextLine;
			int counter = 0;

			// read one line at a time
			List<String> recordList = null;
			List<String> headerList = null;
			int headerCounter = 0;
			int headerCounter1 = 0;
			int headerCounter2 = 0;
			List<String> conditions = Arrays.asList(columnValue, columnValue2, columnValue3);

			map = new LinkedHashMap();

			int initCounter = 0;
			while ((nextLine = reader.readNext()) != null) {
				headerList = Arrays.asList(nextLine);
				System.out.println("headerList:" + headerList);
				if (initCounter == 0) {
					for (String header : headerList) {
						System.out.println(header);
						System.out.println(columnName);
						if (header.equalsIgnoreCase(columnName)) {
							headerCounter = initCounter;
							break;
						}
						initCounter++;
					}
					break;
				}
			}
			counter = 1;
			int headerIterateCounter = 0;
			while ((nextLine = reader.readNext()) != null) {
				recordList = Arrays.asList(nextLine);
				if (recordList.containsAll(conditions)) {
					for (String headers : headerList) {
						map.put(headerList.get(headerIterateCounter), recordList.get(headerIterateCounter));
						headerIterateCounter++;
					}
					break;
				}
				counter++;
			}
			System.out.println("Map:" + map);
			return map;
		} catch (Exception e) {
			return null;
		}
	}
}
