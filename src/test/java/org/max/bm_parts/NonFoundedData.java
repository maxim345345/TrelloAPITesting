package org.max.bm_parts;

import java.time.LocalDateTime;

public class NonFoundedData {


    private int id;
    private String skuNumber;
    private String brandName;
    private String pageName;
    private LocalDateTime created;

    public NonFoundedData(int id, String skuNumber, String brandName,String pageName, LocalDateTime created) {
        this.id = id;
        this.skuNumber = skuNumber;
        this.brandName = brandName;
        this.pageName = pageName;
        this.created = created;
    }

    public NonFoundedData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "NonFoundedData{" +
                "id=" + id +
                ", skuNumber='" + skuNumber + '\'' +
                ", brandName='" + brandName + '\'' +
                ", pageName='" + pageName + '\'' +
                ", created=" + created +
                '}';
    }
}
