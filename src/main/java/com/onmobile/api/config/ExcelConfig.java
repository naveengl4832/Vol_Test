package com.onmobile.api.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelConfig {

	static Workbook workbook;
	static Sheet worksheet;
	static String sourceClass = ExcelConfig.class.getName();
	static Logger LOGGER = Logger.getLogger(sourceClass);
	static String sourceMethod;

	/**
	 * Return Test Data as Map with column name as "key" and cell Value as "Value"
	 * 
	 * @author
	 * @param sheetName
	 * @return Test Data as Map
	 */

	public static Object[][] getTestDataAsMap(String fileName, String sheetName) {
		sourceMethod = "getTestDataAsMap";
		LOGGER.entering(sourceClass, sourceMethod);
		DataFormatter fmt = new DataFormatter();
		
		FileInputStream file = null;

		try {

			file = new FileInputStream(fileName);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		try {

			workbook = WorkbookFactory.create(file);

		} catch (Exception e) {
			e.printStackTrace();
		}

		worksheet = workbook.getSheet(sheetName);
		
		LOGGER.info("Number of Rows: " + worksheet.getLastRowNum() + " Number of cells : "
				+ worksheet.getRow(0).getLastCellNum());

		Object[][] data = new Object[worksheet.getLastRowNum()][worksheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < worksheet.getLastRowNum(); i++) {
			Map<Object, Object> datamap = new HashMap<Object, Object>();

			for (int j = 0; j < worksheet.getRow(0).getLastCellNum(); j++) {
				String valueAsSeenInExcel = fmt.formatCellValue(worksheet.getRow(i + 1).getCell(j));
				
				datamap.put(worksheet.getRow(0).getCell(j).toString(), valueAsSeenInExcel);
			}

			data[i][0] = datamap;
		}
		LOGGER.exiting(sourceClass, sourceMethod);
		return data;
		
	}

}
