package com.carlos.wuu.brandnewday.enumEntity;

public enum AddressTypeEnum {
    SHANGHAI(1,"上海"),
    FUZHOU(2,"抚州");

    private Integer cityCode;
    private String cityName;

    AddressTypeEnum(Integer cityCode,String cityName){
        this.cityCode = cityCode;
        this.cityName = cityName;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public static AddressTypeEnum getAddressTypeEnum(String cityName){
        switch (cityName){
            case "上海":
                return SHANGHAI;
            case "抚州":
                return FUZHOU;
            default:
                return SHANGHAI;
        }

    }


    @Override
    public String toString() {
        return this.cityName;
    }
}
