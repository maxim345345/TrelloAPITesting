package org.max.bm_parts;

import java.time.LocalDateTime;
import java.util.Objects;

public class InputData {


    private int id;
    private String skuNumber;
    private String brandName;
    private LocalDateTime created;

    public InputData(int id, String skuNumber, String brandName, LocalDateTime created) {
        this.id = id;
        this.skuNumber = skuNumber;
        this.brandName = brandName;
        this.created = created;
    }

    public InputData() {
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
        InputData inputData = (InputData) o;
        return id == inputData.id && Objects.equals(skuNumber, inputData.skuNumber) && Objects.equals(brandName, inputData.brandName) && Objects.equals(created, inputData.created);
    }

    @Override
    public String toString() {
        return "InputData{" +
                "id=" + id +
                ", skuNumber='" + skuNumber + '\'' +
                ", brandName='" + brandName + '\'' +
                ", created=" + created +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skuNumber, brandName, created);


    }
}
