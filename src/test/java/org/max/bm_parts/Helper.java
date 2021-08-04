package org.max.bm_parts;

import com.opencsv.CSVReader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.StringUtils.remove;

public class Helper {

    public static void main(String[] args) throws IOException {
        CSVreader();
    }

    public static String trimSpaceAndDota(String b) {

        String c = remove(b, '.');

        return remove(c, ' ');
    }

    public static String trimSpace(String b) {


        return remove(b, ' ');
    }

    public static void exportToFile(List<OpponentPrice> object) throws FileNotFoundException {

        java.io.File file = new java.io.File("d:\\scores4.csv");
        if (file.exists()) {
            System.out.println("File already exists");
            System.exit(1);
        }
        java.io.PrintWriter output = new java.io.PrintWriter(file);

        for (OpponentPrice b : object) {
            output.println(b);

        }
        output.close();
    }

    public static Map<Integer, List<String>> importFromExcel() throws IOException {
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

    //    public void loadPriceList(String path) {
//
//        CsvMapper csvMapper = new CsvMapper();
//        CsvSchema schema = CsvSchema.emptySchema().withHeader();
//        ObjectReader oReader = csvMapper.reader(PriceList.class).with(schema);
//        basicData = new ArrayList<>();
//
//        try (Reader reader = new FileReader("src/test/resources/bykanov/pricelists/testFile.csv")) {
//            MappingIterator<PriceList> mi = oReader.readValues(reader);
//            while (mi.hasNext()) {
//                PriceList current = mi.next();
//                basicData.add(current);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        public void checkTheIdea (String skuNumber){
//            List<WebElement> li = parent.findElements(By.tagName("li"));
//            for (WebElement element : li) {
//                WebElement sku = element.findElement(By.className("products-list__item__container__article"));
//                if (sku.getText().equals(skuNumber)) {
//                    WebElement price = element.findElement(By.className("products-list__item__container__price"));
//                    System.out.println(price.getText());
//                    break;
//                }
//            }
//        }
//    }
    public static String getCurrentDate() {
        DateFormat dform = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date obj = new Date();
        String date = dform.format(obj);
        return date;
    }

    public void exportToExcel(List<String> a, List<String> b) throws IOException {
        System.out.println(a.size());
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Bosch");
        Row header = sheet.createRow(0);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Model");
        headerCell = header.createCell(1);
        headerCell.setCellValue("Price");


        for (int i = 0; i < a.size(); i++) {

            Row row = sheet.createRow(i + 2);
            Cell cell = row.createCell(0);
            cell.setCellValue(a.get(i));

            cell = row.createCell(1);
            cell.setCellValue(b.get(i));
        }

        FileOutputStream outputStream = new FileOutputStream("d:\\bosch.xls");
        workbook.write(outputStream);
        workbook.close();
    }

    public static void getUSDandEuroExchangeRate() {
        String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";

        List<String> A =
                given()
                        .when()
                        .get(url)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("buy");

        for (String a : A
        ) {
            System.out.println(a);

        }

        String[] usdAndEuro = new String[2];
        //      usdAndEuro[0] = ((response.jsonPath().getDouble("buy[0]") + response.jsonPath().getDouble("sale[0]")) / 2) + "";
        //    usdAndEuro[1] = ((response.jsonPath().getDouble("buy[1]") + response.jsonPath().getDouble("sale[1]")) / 2) + "";

    }

    String formattedDouble = new DecimalFormat("#0.00").format(0.1321231);

    public static void CSVreader() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("d:/data.csv"), ',', '"', 1);

        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                //Verifying the read data here
                System.out.println(Arrays.toString(nextLine));
            }
        }
    }

}