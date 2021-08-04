package org.max.bm_parts;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"brand", "skuNumber", "description", "price", "kiev", "kharkiv", "odessa"})

public class PriceList {

        private String brand;
        private String skuNumber;
        private String description;
        private String price;
        private String kiev;
        private String kharkov;
        private String odessa;

        public PriceList() {
        }

        public PriceList(String brand, String skuNumber, String description, String price, String kiev, String kharkov, String odessa) {
            this.brand = brand;
            this.skuNumber = skuNumber;
            this.description = description;
            this.price = price;
            this.kiev = kiev;
            this.kharkov = kharkov;
            this.odessa = odessa;
        }

        public String getBrand() {
            return brand;
        }

        public String getSkuNumber() {
            return skuNumber;
        }

        public String getDescription() {
            return description;
        }

        public String getPrice() {
            return price;
        }

        public String getKiev() {
            return kiev;
        }

        public String getKharkov() {
            return kharkov;
        }

        public String getOdessa() {
            return odessa;
        }

        public String getForComparing(){
            StringBuilder builder = new StringBuilder();
            String internalOutput = builder.append(brand.replaceAll("\"", "").toLowerCase())
                    .append(" ")
                    .append(skuNumber).toString();
            return builder.toString();
        }

        public String getForComparingNext() {
            StringBuilder builder = new StringBuilder();
            String line = builder.append(brand.replaceAll("\"", "").toLowerCase())
                    .append(" ")
                    .append(skuNumber).toString();


            return builder.toString().replaceAll("/", " ");
        }

        @Override
        public String toString() {
            return "PriceList{" +
                    "brand='" + brand + '\'' +
                    ", skuNumber='" + skuNumber + '\'' +
                    ", description='" + description + '\'' +
                    ", price=" + price +
                    ", kiev='" + kiev + '\'' +
                    ", kharkov='" + kharkov + '\'' +
                    ", odessa='" + odessa + '\'' +
                    '}';
        }

    }
