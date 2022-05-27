package com.nft.parse.jsoupParse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelWriter {

	public static void readAndUpdateExcel(List<String> ipfsCid, String templateExcel) throws Exception {

		String excelFilePath = templateExcel;
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		Workbook workbook = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
			workbook = WorkbookFactory.create(inputStream);

			Sheet sheet = workbook.getSheetAt(0);
			sheet.autoSizeColumn(2);

			Object[][] bookData = populateObjectData(ipfsCid);

			int rowCount = sheet.getLastRowNum();

			for (Object[] aBook : bookData) {
				Row row = sheet.createRow(++rowCount);

				int columnCount = 0;

				Cell cell = row.createCell(columnCount);
				cell.setCellValue(rowCount);

				for (Object field : aBook) {
					cell = row.createCell(++columnCount);
					if (field instanceof String) {
						cell.setCellValue((String) field);
					} else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					}
					CellStyle cellStyle = cell.getCellStyle();
					cellStyle.setAlignment(HorizontalAlignment.CENTER);
				}
			}

			outputStream = new FileOutputStream("generated_excel.xlsx");
			workbook.write(outputStream);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			try {
				outputStream.close();
				inputStream.close();
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static Object[][] populateObjectData(List<String> ipfsCid) {

		Object[][] data = new Object[ipfsCid.size()][2];

		int i = 0;
		for (String ipfs : ipfsCid) {
			data[i][0] = ipfs;
			data[i++][1] = "";
		}

		return data;

	}

}
