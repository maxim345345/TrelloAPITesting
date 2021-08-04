package org.max.Rozetka;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.stream.Collectors;

import static org.max.training.Rexexp.findNumberOfVidgukiv;

public class MainPage {

    WebDriver driver;
    WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);

    }

    @FindBy(how = How.CSS, using = "input[placeholder='Я шукаю...']")
    WebElement search;

    @FindBy(how = How.CSS, using = "input.custom-checkbox[id='Bosch']")
    WebElement modelChoice;

    @FindBy(how = How.CSS, using = "label[for='Bosch']")
    WebElement modelChoiceNew;

    @FindBy(how = How.CLASS_NAME, using = "goods-tile__title")
    List<WebElement> listOfTitles;

    @FindBy(how = How.CSS, using = "span.goods-tile__price-value")
    List<WebElement> listOfPrices;

    @FindBy(how = How.CLASS_NAME, using = "catalog-grid__cell")
    List<WebElement> allgoods;

    public void searchWindow() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(search)).click();
        wait.until(ExpectedConditions.elementToBeClickable(search)).sendKeys("холодильник");
        wait.until(ExpectedConditions.elementToBeClickable(search)).sendKeys(Keys.ENTER);

    }

    public void setModelChoice() throws InterruptedException {
        Thread.sleep(3000);
        // System.out.println(modelChoice.isSelected());
        wait.until(ExpectedConditions.elementToBeClickable(modelChoiceNew)).click();
        //System.out.println(modelChoice.isSelected());
//        boolean select=wait.until(ExpectedConditions.elementToBeClickable(modelChoice)).isSelected();
//        if(select==false) modelChoice.click();
    }

    public List<String> listOfTitles() throws InterruptedException {

        List<String> m = new ArrayList<>();
        for (WebElement element : listOfTitles) {
            String text = element.getText();
            // System.out.println(text);
            m.add(text);
        }
        return m;
    }

    public List<String> listOfPrices() throws InterruptedException {

        List<String> modelprice = new ArrayList<>();
        for (WebElement element : listOfPrices) {
            String text = element.getText();
            //  System.out.println(text);
            modelprice.add(text);
        }

        return modelprice;
    }

    public void fileIO(List<String> a) throws IOException {

        FileOutputStream file = new FileOutputStream("D:\\Student.txt");
        ObjectOutputStream data = new ObjectOutputStream(file);
        data.writeObject(a);
        data.flush();
        data.close();
        System.out.println("Record added");
    }

    public void allGoods() throws InterruptedException {
        Thread.sleep(2000);
        List<String> listOfStringData = allgoods.stream().map(element -> element.getAttribute("outerText")).collect(Collectors.toList());
        List<String> listOfVidgukNumber = listOfStringData.stream().map(element -> findNumberOfVidgukiv(element)).collect(Collectors.toList());

        int c = listOfVidgukNumber.stream().mapToInt(num -> Integer.parseInt(num)).max().getAsInt();
        System.out.println(c);

        List<WebElement> b = allgoods.stream().filter(element -> element.getAttribute("outerText").contains("180 відгуків")).collect(Collectors.toList());
        wait.until(ExpectedConditions.elementToBeClickable(b.get(0))).click();

    }

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

}
