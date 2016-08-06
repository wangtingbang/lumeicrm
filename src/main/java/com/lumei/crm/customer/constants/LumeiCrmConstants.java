package com.lumei.crm.customer.constants;

public class LumeiCrmConstants {
  public static enum SERVICE_TYPE{
    CAR_SELLING((byte)1,"Car Selling"), //
    EMERGENCY_CONTACT((byte)2,"Emergency Contact"), //
    CAR_BUYING((byte)3,"Car Buying"), //
    CAR_RENTAL((byte)4,"Car Rental"),//
    CELLPHONE((byte)5,"Cellphone"),
    AIRPORT_PICKUP((byte)6,"Airport Pickup"),//
    TEMPORARY_HOUSE((byte)7,"Temporary House"),//
    AIM((byte)8,"AIM");

    private Byte value;
    private String desc;

    private SERVICE_TYPE(Byte value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public Byte getValue() {
      return value;
    }
    public String getDesc() {
      return desc;
    }   
  }
  public static enum CUSTOMER_STATUS{
    APPOINTMENTED((byte)1,"Appointmented"),//
    SOLD_BY_LUMEI((byte)2,"Sold by Lumei"),//
    BUY_FROM_OTHER((byte)3,"Buy from Other"),//
    NO_RESPONSE((byte)4,"Noresponse"),//
    STILL_IN_THE_MARKET((byte)5,"Still in the Market");

    private Byte value;
    private String desc;

    private CUSTOMER_STATUS(Byte value, String desc){
        this.value = value;
        this.desc = desc;
    }
    public Byte getValue() {
      return value;
    }
    public String getDesc() {
      return desc;
    }
  }
}