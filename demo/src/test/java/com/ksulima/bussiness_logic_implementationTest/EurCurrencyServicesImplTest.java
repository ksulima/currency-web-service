package com.ksulima.bussiness_logic_implementationTest;

import com.ksulima.bussiness_logic_implementation.EurBetaServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;

/**
 * Created by Krzysztof Sulima on 28.05.2017.
 */
public class EurCurrencyServicesImplTest {

    private EurBetaServiceImpl util;

    @Before
    public void setup(){
        util = new EurBetaServiceImpl();
    }

    @Test
    public void shouldNotReturnNull(){
        Assert.assertNotNull(util.getCurrency());
    }

    @Test
    public void shouldContainCurrencySymbol(){
        Assert.assertThat(util.getCurrency(), containsString("EUR/PLN") );
    }
}
