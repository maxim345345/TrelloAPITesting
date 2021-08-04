package org.max.pobut;

public class VehicleModification {

    private int id;


    private int modelId;


    private int vehicleId;


    private String vehicleName;


    private int powerHp;


    private int powerKw;

  private int cylinderCapacityCcm;


    private int cylinderCapacityLiter;


    private String constructionType;


    private String typeFuel;

    private String fuelTypeProcess;


    private String impulsionType;


    private String motorType;


    private String motorCode;


    private String firstCountry;

    public VehicleModification(int id, int modelId, int vehicleId, String vehicleName, int powerHp, int powerKw, int cylinderCapacityCcm, int cylinderCapacityLiter, String constructionType, String typeFuel, String fuelTypeProcess, String impulsionType, String motorType, String motorCode, String firstCountry) {
        this.id = id;
        this.modelId = modelId;
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.powerHp = powerHp;
        this.powerKw = powerKw;
        this.cylinderCapacityCcm = cylinderCapacityCcm;
        this.cylinderCapacityLiter = cylinderCapacityLiter;
        this.constructionType = constructionType;
        this.typeFuel = typeFuel;
        this.fuelTypeProcess = fuelTypeProcess;
        this.impulsionType = impulsionType;
        this.motorType = motorType;
        this.motorCode = motorCode;
        this.firstCountry = firstCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getPowerHp() {
        return powerHp;
    }

    public void setPowerHp(int powerHp) {
        this.powerHp = powerHp;
    }

    public int getPowerKw() {
        return powerKw;
    }

    public void setPowerKw(int powerKw) {
        this.powerKw = powerKw;
    }

    public int getCylinderCapacityCcm() {
        return cylinderCapacityCcm;
    }

    public void setCylinderCapacityCcm(int cylinderCapacityCcm) {
        this.cylinderCapacityCcm = cylinderCapacityCcm;
    }

    public int getCylinderCapacityLiter() {
        return cylinderCapacityLiter;
    }

    public void setCylinderCapacityLiter(int cylinderCapacityLiter) {
        this.cylinderCapacityLiter = cylinderCapacityLiter;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }

    public String getTypeFuel() {
        return typeFuel;
    }

    public void setTypeFuel(String typeFuel) {
        this.typeFuel = typeFuel;
    }

    public String getFuelTypeProcess() {
        return fuelTypeProcess;
    }

    public void setFuelTypeProcess(String fuelTypeProcess) {
        this.fuelTypeProcess = fuelTypeProcess;
    }

    public String getImpulsionType() {
        return impulsionType;
    }

    public void setImpulsionType(String impulsionType) {
        this.impulsionType = impulsionType;
    }

    public String getMotorType() {
        return motorType;
    }

    public void setMotorType(String motorType) {
        this.motorType = motorType;
    }

    public String getMotorCode() {
        return motorCode;
    }

    public void setMotorCode(String motorCode) {
        this.motorCode = motorCode;
    }

    public String getFirstCountry() {
        return firstCountry;
    }

    public void setFirstCountry(String firstCountry) {
        this.firstCountry = firstCountry;
    }
}
