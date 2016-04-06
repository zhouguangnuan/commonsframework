package cn.singno.commonsframework.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.singno.commonsframework.bean.ExcelReader;

public class POIUtils
{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(POIUtils.class);

	/**
	 * excel
	 * @param columnNames
	 * @param columnVals
	 * @param out
	 * @throws Exception
	 */
	public static void saveExcel(String[] columnNames, List<String[]> columnVals, OutputStream out) throws Exception
	{
		Integer startColumn = 0;
		// 获取总列数
		Integer countColumnNum = null;
		if(ArrayUtils.isNotEmpty(columnNames)){
			countColumnNum = columnNames.length;
			startColumn = 1;
		} else if(CollectionUtils.isNotEmpty(columnVals) && ArrayUtils.isNotEmpty(columnVals.get(0))){
			countColumnNum = columnVals.get(0).length;
		} else {
			return;
		}
		
		// 创建Excel文档
		HSSFWorkbook hwb = new HSSFWorkbook();
		// sheet 对应一个工作页
		HSSFSheet sheet = hwb.createSheet("Sheet1");
		HSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始
		HSSFCell[] firstcell = new HSSFCell[countColumnNum];
		
		if(ArrayUtils.isNotEmpty(columnNames)){
			for (short j = 0; j < countColumnNum; j++)
			{
				firstcell[j] = firstrow.createCell(j);
				firstcell[j].setCellValue(new HSSFRichTextString(columnNames[j]));
			}
		}
		
		for (int i = 0; i < columnVals.size(); i++)
		{
			// 创建一行
			HSSFRow row = sheet.createRow(i + startColumn);
			String[] vals = columnVals.get(i);
			if(ArrayUtils.isNotEmpty(vals)){
				// 得到要插入的每一列的值
				for (short colu=0; colu <vals.length; colu++)
				{
					HSSFCell cell = row.createCell(colu);
					cell.setCellValue(vals[colu]);
				}
			}
		}
		try
		{
			// 创建文件输出流，准备输出电子表格
			hwb.write(out);
		} catch (Exception e)
		{
			logger.error(e);
		} finally
		{
			if (null != out)
			{
				out.close();
			}
		}
	}

	/**
	 * @param in
	 * @return
	 */
	public static List<String[]> readExcel(InputStream in) throws Exception
	{
		int sheetIndex = 0;
		ExcelReader reader = new ExcelReader(in);
		List<String[]> excelDate = reader.getAllData(sheetIndex);
		return excelDate;
	}
}
