package org.max.bm_parts;

import java.time.LocalDateTime;
import java.util.Objects;

public class OpponentPrice {

    private int id;
    private String pageName;
    private String skuNumber;
    private String brandName;
    private double price;
    private double euroAveCourse;
    private double usdAveCourse;
    private LocalDateTime created;

    public OpponentPrice() {
    }

    public OpponentPrice(int id, String pageName, String skuNumber,String brandName,  double price, double euroAveCourse, double usdAveCourse, LocalDateTime created ) {
        this.id = id;
        this.pageName = pageName;
        this.skuNumber = skuNumber;
        this.brandName = brandName;
        this.price = price;
        this.euroAveCourse = euroAveCourse;
        this.usdAveCourse = usdAveCourse;
         this.created = LocalDateTime.now();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getEuroAveCourse() {
        return euroAveCourse;
    }

    public void setEuroAveCourse(double euroAveCourse) {
        this.euroAveCourse = euroAveCourse;
    }

    public double getUsdAveCourse() {
        return usdAveCourse;
    }

    public void setUsdAveCourse(double usdAveCourse) {
        this.usdAveCourse = usdAveCourse;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpponentPrice that = (OpponentPrice) o;
        return id == that.id && Double.compare(that.price, price) == 0 && Double.compare(that.euroAveCourse, euroAveCourse) == 0 && Double.compare(that.usdAveCourse, usdAveCourse) == 0 && Objects.equals(pageName, that.pageName) && Objects.equals(brandName, that.brandName) && Objects.equals(skuNumber, that.skuNumber) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pageName, skuNumber,brandName,  price, euroAveCourse, usdAveCourse, created);
    }

    @Override
    public String toString() {
        return "OpponentPrice{" +
                "id=" + id +
                ", pageName='" + pageName + '\'' +
                ", skuNumber='" + skuNumber + '\'' +
                ", brandName='" + brandName + '\'' +
                ", price=" + price +
                ", euroAveCourse=" + euroAveCourse +
                ", usdAveCourse=" + usdAveCourse +
                ", created=" + created +
                '}';
    }

}
