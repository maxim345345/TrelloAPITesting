package org.max.pobut;

public class VehiclesTypes {

    private int vehiclesTypeId;
    private String vehiclesTypeName;


    public VehiclesTypes(int vehiclesTypeId, String vehiclesTypeName) {
        this.vehiclesTypeId = vehiclesTypeId;
        this.vehiclesTypeName = vehiclesTypeName;
    }

    public int getVehiclesTypeId() {
        return vehiclesTypeId;
    }

    public void setVehiclesTypeId(int vehiclesTypeId) {
        this.vehiclesTypeId = vehiclesTypeId;
    }

    public String getVehiclesTypeName() {
        return vehiclesTypeName;
    }

    public void setVehiclesTypeName(String vehiclesTypeName) {
        this.vehiclesTypeName = vehiclesTypeName;
    }

    @Override
    public String toString() {
        return "VehiclesTypes{" +
                "vehiclesTypeId=" + vehiclesTypeId +
                ", vehiclesTypeName='" + vehiclesTypeName + '\'' +
                '}';
    }
}

