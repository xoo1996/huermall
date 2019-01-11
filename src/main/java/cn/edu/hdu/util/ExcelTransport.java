package cn.edu.hdu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.alibaba.fastjson.JSONObject;
import com.zlzkj.core.sql.Row;

public class ExcelTransport {

	/**
	 * Read data from a excel file
	 */
	public static List<Object> readExcel(String excelFileName, int sheetNum, String keys) {
		Workbook workbook = null;

		try {
			workbook = Workbook.getWorkbook(new File(excelFileName));
		} catch (Exception e) {

			// throw new Exception("file to import not found!");

		}

		Sheet sheet = workbook.getSheet(sheetNum);
		Cell cell = null;

		int rowCount = sheet.getRows();
		String[] key = keys.split(",");
		List<Object> list = new ArrayList<Object>();

		for (int i = 1; i < rowCount; i++) {
			Map dbo = new HashMap();
			for (int j = 0; j < key.length; j++) {
				cell = sheet.getCell(j, i);
				if (cell.getType() == CellType.NUMBER) {
					dbo.put(key[j], ((NumberCell) cell).getValue());
				} else if (cell.getType() == CellType.DATE) {
					dbo.put(key[j], ((DateCell) cell).getDate());
				} else {
					dbo.put(key[j], (cell.getContents()));
				}
			}
			list.add(dbo);
		}
		workbook.close();
		return list;
	}

	/**
	 * 创建一个Excel文件
	 * 
	 * @param excelFileName
	 *            Excel文件名
	 * @param sheetName
	 *            Excel页名
	 * @param headerNames
	 *            标题名（用逗号分隔）
	 * @param keys
	 *            键名（用逗号分隔）
	 * @param list
	 *            数据
	 * @return
	 * @throws Exception
	 */
	public static File createMainExcelFile(String filePath, String excelFileName, String sheetName, String headerNames, String keys,
			List<Object[]> rowList) throws Exception {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			WritableSheet ws = wwb.createSheet(sheetName, 0);

			String[] aheaderName = headerNames.split(",");
			String[] akey = keys.split(",");
			if (akey.length != aheaderName.length)
				throw new Exception("键名个数和标题个数必须一样!");

			for (int col = 0; col < aheaderName.length; col++) {
				Label header = new Label(col, 0, aheaderName[col]);
				ws.addCell(header);
			}

			try {
				for (int row = 0; row < rowList.size(); row++) {
					Object[] obj = rowList.get(row);
					for (int col = 0; col < akey.length; col++) {
						//判空防止空指针异常
						if(obj[col] != null){
							Label body = new Label(col, row + 1, obj[col].toString());
							ws.addCell(body);
						}
						else{
							Label body = new Label(col, row + 1, "");
							ws.addCell(body);
						}
					}
				}
				for (int row = 0; row < rowList.size(); row++) {
					ws.setColumnView(row, 30);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return file;
	}
}
