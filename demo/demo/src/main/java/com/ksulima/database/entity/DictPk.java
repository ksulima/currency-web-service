package com.ksulima.database.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * Created by Krzysztof Sulima on 16.06.2017.
 */

@Embeddable
public class DictPk implements Serializable {


    @GeneratedValue
    @Column(name = "DICT_ID")
    private Long dictId;


    @Column(name = "BASE_CODE")
    private String baseCode;


    public DictPk() {
    }

    public DictPk(String baseCode) {
        this.baseCode = baseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DictPk)) return false;

        DictPk that = (DictPk) o;

        if (!dictId.equals(that.dictId)) return false;
        return baseCode.equals(that.baseCode);
    }

    @Override
    public int hashCode() {
        int result = dictId.hashCode();
        result = 31 * result + baseCode.hashCode();
        return result;
    }


    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }
}
