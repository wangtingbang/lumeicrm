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
    AIM((byte)8,"AIM"),//
    CAR_INSURANCE((byte)9,"CAR INSURANCE");

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
  
  public static enum CAR_DEAL_STATUS{
	POTENTIAL_TO_BUY((byte)1,"Potential to Buy"),//
    APPOINTMENT_MADE((byte)2,"Appointment Made"),//
    SOLD_BY_LUMEI((byte)3,"Sold by Lumei"),//
    BUY_FROM_OTHER((byte)4,"Bought from Others"),//
    NO_RESPONSE((byte)5,"No Response");//

    private Byte value;
    private String desc;

    private CAR_DEAL_STATUS(Byte value, String desc){
        this.value = value;
        this.desc = desc;
    }
    public Byte getValue() {
      return value;
    }
    public String getDesc() {
      return desc;
    }
    
    public static String getDesc(Byte value){
    	for(CAR_DEAL_STATUS e : CAR_DEAL_STATUS.values()){
    		if(e.getValue().equals(value)){
    			return e.getDesc();
    		}
    	}
    	return "";
    }
  }

  public static enum CUSTOMER_RATING{
    A("A","A"),//
    B("B","B"),//
    C("C","C");

    private String value;
    private String desc;

    private CUSTOMER_RATING(String value, String desc){
      this.value = value;
      this.desc = desc;
    }
    public String getValue() {
      return value;
    }
    public String getDesc() {
      return desc;
    }
  }

  public static enum FINANCE_STATUS {
    PENDING((byte)1,"Pending"),
    APPROVED((byte)2,"Approved"),
    UNQUALIFIED((byte)3,"Unqualified");

    private Byte value;
    private String desc;

    private FINANCE_STATUS(Byte value, String desc){
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

  public static enum LEASE_STATUS {
    PENDING((byte)1,"Pending"),
    APPROVED((byte)2,"Approved"),
    UNQUALIFIED((byte)3,"Unqualified");

    private Byte value;
    private String desc;

    private LEASE_STATUS(Byte value, String desc){
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
  
  public static enum CAR_DEAL_SOURCE {
    FRIENDS_REFER((byte)1,"Friends Refer"),
    WECHAT_GROUP((byte)2,"WeChat Group"),
    WECHAT_PUBLIC_ACCOUNT ((byte)3,"WeChat Public Account"),
    WEBSITE ((byte)4,"Website"),
    CITIC_BANK((byte)5,"Citic Bank"),
    FLYER_DM((byte)6,"Flyer/DM"),
    UNIVERSITY_AMBASSADOR((byte)7,"University Ambassador"),
    UNIVERSITY_EVENT((byte)8,"University Event"),
    LONE_CLUB((byte)9,"LONE Club"),
    AIRPORT_PICKUP((byte)10,"Airport Pickup"),
    EMERGENCY_CONTACT ((byte)11,"Emergency Contact"),
    CAR_RENTAL((byte)12,"Car Rental"),
    CELLPHONE((byte)13,"Cellphone"),
    TEMPORARY_ACCOMMODATION ((byte)14,"Temporary Accommodation"),
    AIM((byte)15,"AiM"),
    CUSTOMER_REFER((byte)16,"Customer Refer");

    private Byte value;
    private String desc;

    private CAR_DEAL_SOURCE(Byte value, String desc){
      this.value = value;
      this.desc = desc;
    }
    public Byte getValue() {
      return value;
    }
    public String getDesc() {
      return desc;
    }
    
    public static String getDesc(Byte value){
    	for(CAR_DEAL_SOURCE e : CAR_DEAL_SOURCE.values()){
    		if(e.getValue().equals(value)){
    			return e.getDesc();
    		}
    	}
    	return "";
    }
  }
  
  public static class CaseNo{
	  public static final String CarSale="CS";
	  public static final String CarTrade="CT";
	  public static final String CarRental="CR";
	  public static final String CarInsur="CI";
	  public static final String EmerCon="EC";
	  public static final String TempAcc="TA";
	  public static final String PhoneCard="PC";
	  public static final String AirportPickup="AP";
  }
}
