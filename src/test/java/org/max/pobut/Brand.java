package org.max.pobut;

import java.util.ArrayList;
import java.util.List;

public class Brand {


    private int brandId;
    private String brandName;
    private String image;

    private List<Model> models = new ArrayList<>();

    public Brand( int brandId, String brandName, String image) {

        this.brandId = brandId;
        this.brandName = brandName;
        this.image = image;
    }

    public Brand() {
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand{" +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", image='" + image + '\'' +
                ", models=" + models +
                '}';
    }
}
