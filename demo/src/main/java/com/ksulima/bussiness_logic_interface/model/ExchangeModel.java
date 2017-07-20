package com.ksulima.bussiness_logic_interface.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Created by Krzysztof Sulima on 19.03.2017.
 */

@Data
@Getter
@Setter
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


}
