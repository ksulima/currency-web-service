package com.ksulima.bussiness_logic_interface.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Map;

/**
 * Created by Krzysztof Sulima on 19.03.2017.
 */



@Data
public class ExchangeModel {

   @SerializedName("base")
    @Expose
    private String base;

    @SerializedName("data")
    @Expose
    private String date;

    @SerializedName("rates")
    @Expose
    private Map<String, String> rates;


     public String getBase() {
      return base;
     }

     public void setBase(String base) {
      this.base = base;
     }

     public String getDate() {
      return date;
     }

     public void setDate(String date) {
      this.date = date;
     }

     public Map<String, String> getRates(){
      return rates;
     }

     public void setRates(Map<String, String> rates) {
      this.rates = rates;
     }
}
