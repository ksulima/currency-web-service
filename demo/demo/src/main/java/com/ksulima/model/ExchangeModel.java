package com.ksulima.model;

import lombok.Data;

import java.util.Map;

/**
 * Created by Krzysztof Sulima on 19.03.2017.
 */

@Data
public class ExchangeModel {

    //@SerializedName("base")
    //@Expose
    private String base;

    //@SerializedName("data")
    //@Expose
    private String date;

    //@SerializedName("rates")
    //@Expose
    private Map<String, String> rates;




}
