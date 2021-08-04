package org.max;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatabaseTrening {
//    static String JDBCurl = "jdbc:mysql://localhost:3306/shop";
//    static final String username = "root";
//    static final String password = "root";

    public static void main(String[] args) throws IOException {
        Map<Integer, List<String>> a = readingFromExcel();
        System.out.println(a.size());
        a.forEach((key, value) -> System.out.printf("%20s\t%s\n", key, value));

//        List<Artikul> artikulList = new ArrayList<>();
//        Artikul some = new Artikul("some", "spm", "333");
//        artikulList.add(some);
//
//        MainPageB2B.loadToDB(artikulList);}
    }

    static Map<Integer, List<String>> readingFromExcel() throws IOException {
        FileInputStream file = new FileInputStream(new File("d:\\Склад_SNR_03_21.xls"));
        Workbook workbook = new HSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        data.get(new Integer(i)).add(cell.getRichStringCellValue().getString());
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            data.get(i).add(cell.getDateCellValue() + "");
                        } else {
                            data.get(i).add(cell.getNumericCellValue() + "");
                        }
                        break;
                    case BOOLEAN:
                        data.get(i).add(cell.getBooleanCellValue() + "");
                        break;
                    case FORMULA:
                        data.get(i).add(cell.getCellFormula() + "");
                        break;
                    default:
                        data.get(new Integer(i)).add(" ");
                }
            }
            i++;
        }
        return data;
    }


}















