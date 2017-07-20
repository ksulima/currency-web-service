package com.ksulima.bussiness_logic_implementationTest;

import com.ksulima.bussiness_logic_implementation.UsdBetaServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;

/**
 * Created by Krzysztof Sulima on 20.07.2017.
 */
public class UsdCurrencyServiceImplTest {

    private UsdBetaServiceImpl util;

    @Before
    public void setup(){
        util = new UsdBetaServiceImpl();
    }

    @Test
    public void shouldNotReturnNull(){
        Assert.assertNotNull(util.getCurrency());
    }

    @Test
    public void shouldContainCurrencySymbol(){
        Assert.assertThat(util.getCurrency(), containsString("USD/PLN") );
    }
}

