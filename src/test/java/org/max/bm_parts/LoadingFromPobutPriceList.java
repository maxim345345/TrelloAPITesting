package org.max.bm_parts;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class LoadingFromPobutPriceList {



    private List<PriceList> basicData;
    private List<InputData> inputData;


    public void loadPriceList(String path) {

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        ObjectReader oReader = csvMapper.reader(PriceList.class).with(schema);
        basicData = new ArrayList<>();

        try (Reader reader = new FileReader("src/test/resources/data.csv")) {
            MappingIterator<PriceList> mi = oReader.readValues(reader);
            while (mi.hasNext()) {
                PriceList current = mi.next();
                basicData.add(current);
            }

            System.out.println(basicData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PriceList> getBasicData() {
        return basicData;
    }

    /**
     * @param brandName -
     * @param delimiter
     * @return
     */
    public List<String> parseBrandName(String brandName, String delimiter) {
        List<String> clearData = new ArrayList<>();

        if (brandName == null || brandName.isEmpty()) return clearData;

        clearData.addAll(Arrays.asList(brandName.replaceAll("[\"/-]", " ").split("[\\s]+")));

        return clearData.stream().map(String::toLowerCase).collect(Collectors.toList());

    }

    public String getClearTextData(String data) {
        return data.replaceAll("\\s|[^A-Za-zА-Яа-я0-9]", "");
    }

    public static void loadDataToInputDB(InputData inputData) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "root"); {


                String query = "insert into  input_data(skuNumber, brandName, created) values (?,?,?);";
                PreparedStatement statement = connection.prepareStatement(query);


                statement.setString(1, inputData.getSkuNumber());
                statement.setString(2, inputData.getBrandName());
                Timestamp created = Timestamp.valueOf(inputData.getCreated());
                statement.setTimestamp(3,created);

                statement.executeUpdate();
                statement.close();
            }

    }

    public static void main(String[] args) {

        LoadingFromPobutPriceList loadingFromPobutPriceList=new LoadingFromPobutPriceList();
        loadingFromPobutPriceList.loadPriceList("src/test/resources/2021-01-15_pobut-price.csv");
        List<String> internalBrandName;
    }
}



