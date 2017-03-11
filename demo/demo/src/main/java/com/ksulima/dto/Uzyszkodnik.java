package com.ksulima.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Krzysztof Sulima on 11.03.2017.
 */
@Getter
@Setter

@ToString
public class Uzyszkodnik {

    private String name;
    private String nazwisko;

    public Uzyszkodnik(String name, String nazwisko) {
        this.name = name;
        this.nazwisko = nazwisko;
    }
}
