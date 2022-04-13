/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.sac.bo;

import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author tscortes
 */
public class ExcelCreator {

    public HSSFWorkbook createWorkbook(List<String[]> contentList, String[] headers) throws Exception {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Exportação Contas");

        /**
         * Style for the header cells.
         */
        HSSFCellStyle headerCellStyle = wb.createCellStyle();
        HSSFFont boldFont = wb.createFont();
        boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerCellStyle.setFont(boldFont);
        int col = 0;
        int rowCount = 0;
        HSSFRow row = sheet.createRow(rowCount);
        HSSFCell cell = row.createCell(col);

        for (String header : headers) {
            cell.setCellStyle(headerCellStyle);
            cell.setCellValue(new HSSFRichTextString(header));
            col++;
            cell = row.createCell(col);
        }
        for (String[] array : contentList) {
            row = sheet.createRow(++rowCount);
            col = 0;
            for (String data : array) {
                cell = row.createCell(col);
                HSSFRichTextString value = new HSSFRichTextString(data);
                cell.setCellValue(value);
                col++;
            }
        }

        return wb;
    }
}
