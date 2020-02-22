package com.freightyard.project.utils;

import com.mysql.jdbc.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.text.DecimalFormat;

public class PoiUtil {
    private static FormulaEvaluator evaluator;

    /**
     * 设置单元格提示信息
     * @param sheet
     * @param promptTitle
     * @param promptContent
     * @param firstRow
     * @param endRow
     * @param firstCol
     * @param endCol
     * @return
     */
    public static HSSFSheet setHSSFPrompt(HSSFSheet sheet, String promptTitle,
                                          String promptContent, int firstRow, int endRow, int firstCol,
                                          int endCol) {
        // 构造constraint对象
        DVConstraint constraint = DVConstraint
                .createCustomFormulaConstraint("BB1");
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_view = new HSSFDataValidation(
                regions, constraint);
        data_validation_view.createPromptBox(promptTitle, promptContent);
        sheet.addValidationData(data_validation_view);
        return sheet;
    }

    /**
     * @desc  设置excel文本格式
     * @param targetWorkbook
     * @param targetSheet
     * @param startRow
     * @param startColumn
     * @param endRow
     * @param endColumn
     */
    public static void setColumnToTextFormat(HSSFWorkbook targetWorkbook, HSSFSheet targetSheet, int startRow, int startColumn, int endRow, int endColumn) throws Exception {
        HSSFCellStyle cellStyle = targetWorkbook.createCellStyle();
        HSSFDataFormat format = targetWorkbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("@"));
        if(startRow <0 || endRow <0 || startColumn <0 || endColumn <0 || (startRow > endRow) || (startColumn > endColumn)){
            throw new Exception("生成Excel格式参数错误!");
        }
        for(int rowIndex = startRow ; rowIndex <= endRow ; rowIndex ++ ){
            HSSFRow row = targetSheet.getRow(rowIndex);
            if(row == null ) {
                row = targetSheet.createRow(rowIndex);
            }
            for (int columnIndex = startColumn ; columnIndex <= endColumn ; columnIndex ++ ){
                HSSFCell cell = row.getCell(columnIndex);
                if (cell == null){
                    cell = row.createCell(columnIndex);
                }
                String rawValue1 = cell.getStringCellValue();
                cell.setCellStyle(cellStyle);
                if(!StringUtils.isNullOrEmpty(rawValue1)){
                    cell.setCellValue(rawValue1);
                }
            }
        }
    }

    /**
     * @param cell 一个单元格的对象
     * @return 返回该单元格相应的类型的值
     */
    public static String getCellValueByCell(Cell cell) {
        //判断是否为null或空串
        if (cell==null || cell.toString().trim().equals("")) {
            return "";
        }
        String cellValue = "";
        int cellType=cell.getCellType();
        if(cellType==Cell.CELL_TYPE_FORMULA){ //表达式类型
            cellType=evaluator.evaluate(cell).getCellType();
        }

        switch (cellType) {
            case Cell.CELL_TYPE_STRING: //字符串类型
                cellValue= cell.getStringCellValue().trim();
                cellValue=StringUtils.isNullOrEmpty(cellValue) ? "" : cellValue;
                break;
            case Cell.CELL_TYPE_BOOLEAN:  //布尔类型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC: //数值类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {  //判断日期类型
                    cellValue =    cell.getDateCellValue().toString();
                } else {  //否
                    cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            default: //其它类型，取空串
                cellValue = "";
                break;
        }
        return cellValue;
    }

    /**
     *  根据单元格数据是否为空，判断该行数据是否有效
     *  具体需要哪些单元格不为空 需要查询数据库哪些字段 not null
     * @param wb  表格对象
     * @param indexs  不为空的单元格下标
     * @param colCount  一共有多少字段
     * @return
     */
    public static int getEffectiveRowNum(HSSFWorkbook wb,int[] indexs,int colCount){
        boolean flag = true;
        Sheet sheet = wb.getSheetAt(0);
        //有效行数
        int rowNum = 0;
        for (Row row : sheet) {
            int effectiveValue = 0;
            for(int i = 0; i < colCount; i++){
                Cell cell = row.getCell(i);
                for (int index : indexs) {
                    //如果这个单元格是需要不为空的,则判断它是否不为空
                    if (i == index) {
                        if (!PoiUtil.isNullOrEmpty(cell)) {
                            effectiveValue++;
                            break;
                        }
                        if (i == colCount - 1) {
                            flag = false;
                        }
                    }
                }
                if (effectiveValue > 0) {
                    break;
                }
            }
            if (effectiveValue > 0) {
                rowNum++;
            }
            if(!flag) {
                break;
            }
        }
        //减去表头那一行
        return rowNum - 1;
    }

    /**
     * 对象是否为无效值
     * @param obj 要判断的对象
     * @return 是否为有效值(不为null 和 ""字符串)
     */
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || "".equals(obj.toString());
    }
}
