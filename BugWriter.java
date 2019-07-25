package com.philips.casestudy;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class BugWriter<T> {
	static String path=Commands.destinationPath;
	static XSSFWorkbook workbook = new XSSFWorkbook();

	public void writeData(ArrayList<T> buglist, String Classname, String AnalysisType) throws Exception {
		try {
			XSSFSheet sheet = workbook.createSheet(AnalysisType.toUpperCase());
			Map<Integer, Object[]> data = new TreeMap<>();
			int i = 2;
			if (((ArrayList<?>) buglist).get(0) instanceof FindBugsData) {
				data.put(1, new Object[] { "Class Name", "Error Category", "Error Type" });
				for (T f : buglist)
					data.put(i++, new Object[] { ((FindBugsData) f).getClassName(), ((FindBugsData) f).getCategory(), ((FindBugsData) f).getType() });
			}

			else if (((ArrayList<?>) buglist).get(0) instanceof PmdData) {
				data.put(1, new Object[] { "Class Name", "Error Line", "Error Type","Error" });
				for (T f : buglist)
					data.put(i++, new Object[] { ((PmdData) f).getClassName(), ((PmdData) f).getLine(), ((PmdData) f).getruleSet(),((PmdData) f).getContent() });
			}

			Set<Integer> keyset = data.keySet();
			int rownum = 0;
			for (Integer key : keyset) {
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);
					sheet.setColumnWidth(cellnum - 1, 15000);
					cell.setCellValue((String) obj);
				}
			}
			FileOutputStream out = new FileOutputStream(
					new File(path + Classname+ ".xlsx"));
			workbook.write(out);
			out.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}