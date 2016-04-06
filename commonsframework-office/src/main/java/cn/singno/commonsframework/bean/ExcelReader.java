/**
 * Description:Excel数据读取工具类，POI实现，兼容Excel2003，及Excel2007
 **/
package cn.singno.commonsframework.bean;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.common.collect.Lists;

public class ExcelReader
{
        Workbook wb = null;
        List<String[]> dataList = Lists.newArrayList();

        public ExcelReader(InputStream in)
        {
                try
                {
                        wb = WorkbookFactory.create(in);
                } catch (Exception e)
                {
                        throw new IllegalArgumentException("不是Excel文件");
                }

        }

        /**
         * 取Excel所有数据，包含header
         * 
         * @return List<String[]>
         */
        public List<String[]> getAllData(int sheetIndex)
        {
                int columnNum = 0;
                Sheet sheet = wb.getSheetAt(sheetIndex);
                if (sheet.getRow(0) != null)
                {
                        columnNum = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();
                }
                if (columnNum > 0)
                {
                        for (Row row : sheet)
                        {
                                String[] singleRow = new String[columnNum];
                                int n = 0;
                                for (int i = 0; i < columnNum; i++)
                                {
                                        Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
                                        switch (cell.getCellType())
                                        {
                                        case Cell.CELL_TYPE_BLANK:
                                                singleRow[n] = "";
                                                break;
                                        case Cell.CELL_TYPE_BOOLEAN:
                                                singleRow[n] = Boolean.toString(cell.getBooleanCellValue());
                                                break;
                                        // 数值
                                        case Cell.CELL_TYPE_NUMERIC:
                                                if (DateUtil.isCellDateFormatted(cell))
                                                {
                                                        singleRow[n] = String.valueOf(cell.getDateCellValue());
                                                } else
                                                {
                                                        cell.setCellType(Cell.CELL_TYPE_STRING);
                                                        String temp = cell.getStringCellValue();
                                                        // 判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
                                                        if (temp.indexOf(".") > -1)
                                                        {
                                                                singleRow[n] = String.valueOf(new Double(temp)).trim();
                                                        } else
                                                        {
                                                                singleRow[n] = temp.trim();
                                                        }
                                                }
                                                break;
                                        case Cell.CELL_TYPE_STRING:
                                                singleRow[n] = cell.getStringCellValue().trim();
                                                break;
                                        case Cell.CELL_TYPE_ERROR:
                                                singleRow[n] = "";
                                                break;
                                        case Cell.CELL_TYPE_FORMULA:
                                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                                singleRow[n] = cell.getStringCellValue();
                                                if (singleRow[n] != null)
                                                {
                                                        singleRow[n] = singleRow[n].replaceAll("#N/A", "").trim();
                                                }
                                                break;
                                        default:
                                                singleRow[n] = "";
                                                break;
                                        }
                                        n++;
                                }
                                dataList.add(singleRow);
                        }
                }
                return dataList;
        }

        /**
         * 返回Excel最大行index值，实际行数要加1
         * 
         * @return
         */
        public int getRowNum(int sheetIndex)
        {
                Sheet sheet = wb.getSheetAt(sheetIndex);
                return sheet.getLastRowNum();
        }

        /**
         * 返回数据的列数
         * 
         * @return
         */
        public int getColumnNum(int sheetIndex)
        {
                Sheet sheet = wb.getSheetAt(sheetIndex);
                Row row = sheet.getRow(0);
                if (row != null && row.getLastCellNum() > 0)
                {
                        return row.getLastCellNum();
                }
                return 0;
        }

        /**
         * 获取某一行数据
         * 
         * @param rowIndex
         *                计数从0开始，rowIndex为0代表header行
         * @return
         */
        public String[] getRowData(int sheetIndex, int rowIndex)
        {
                String[] dataArray = null;
                if (rowIndex > this.getColumnNum(sheetIndex))
                {
                        return dataArray;
                } else
                {
                        dataArray = new String[this.getColumnNum(sheetIndex)];
                        return this.dataList.get(rowIndex);
                }

        }

        /**
         * 获取某一列数据
         * 
         * @param colIndex
         * @return
         */
        public String[] getColumnData(int sheetIndex, int colIndex)
        {
                String[] dataArray = null;
                if (colIndex > this.getColumnNum(sheetIndex))
                {
                        return dataArray;
                } else
                {
                        if (this.dataList != null && this.dataList.size() > 0)
                        {
                                dataArray = new String[this.getRowNum(sheetIndex) + 1];
                                int index = 0;
                                for (String[] rowData : dataList)
                                {
                                        if (rowData != null)
                                        {
                                                dataArray[index] = rowData[colIndex];
                                                index++;
                                        }
                                }
                        }
                }
                return dataArray;
        }
}
