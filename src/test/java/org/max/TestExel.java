package org.max;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestExel {


    public static void main(String[] args) {
        try {
            x();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    public static List createlist(){
//        List biaotou=new ArrayList();
//        List resultList=new ArrayList();
//        biaotou.add("name");
//        biaotou.add("id");
//        resultList.add(biaotou);
//        List neirong=new ArrayList();
//        neirong.add("A");
//        neirong.add("AA");
//        resultList.add(neirong);
//        neirong = new ArrayList (); // initialize it, this value will not join before
//        neirong.add("b");
//        neirong.add("bb");
//        resultList.add(neirong);
//        neirong=new ArrayList();
//        neirong.add("C");
//        neirong.add("CC");
//        resultList.add(neirong);
//        System.out.print(resultList);
//        return resultList;

//    public static void writeToXls(List resultList )throws Exception{
//        // Create a EXCEL
//        Workbook wb = new HSSFWorkbook();
//        // Create a SHEET
//        Sheet sheet1 = wb.createSheet ( "Report 1");
//        if(resultList!=null){
//            for (int i = 0; i < resultList.size(); i++) {
//                // Create a row
//                Row row = sheet1.createRow(i);
//                List rowList=(List)resultList.get(i);
//                for (int j = 0; j < rowList.size(); j++) {
//                    Cell cell = row.createCell(j);
//                    String cellLiString=(String)rowList.get(j);
//                    cell.setCellValue(cellLiString );
//                }
//            }
//        }
//        FileOutputStream fileOut = new FileOutputStream("d:\\testa.xls");
//        wb.write(fileOut);
//        fileOut.close();
//    }
public static void x() throws IOException {

    Workbook workbook = new XSSFWorkbook();

    Sheet sheet = workbook.createSheet("Persons");
    Row header = sheet.createRow(0);

    Cell headerCell = header.createCell(0);
    headerCell.setCellValue("Name");
    headerCell = header.createCell(1);
    headerCell.setCellValue("Age");

    Row row = sheet.createRow(2);
    Cell cell = row.createCell(0);
    cell.setCellValue("John Smith");

    cell = row.createCell(1);
    cell.setCellValue(20);

    FileOutputStream outputStream = new FileOutputStream("d:\\tesita.xls");
    workbook.write(outputStream);
    workbook.close();
}
}
