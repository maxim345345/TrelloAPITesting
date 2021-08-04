package org.max.pobut;

import java.util.Set;

public class Model {

    private int id;

    private int brandId;

    private int modelId;

    private String modelName;


   // private Set<VehicleModification> vehicles;

    private String image;

    public Model(int id, int brandId, int modelId, String modelName, String image) {
        this.id = id;
        this.brandId = brandId;
        this.modelId = modelId;
        this.modelName = modelName;

        this.image = image;
    }

    public Model() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", brandId=" + brandId +
                ", modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
