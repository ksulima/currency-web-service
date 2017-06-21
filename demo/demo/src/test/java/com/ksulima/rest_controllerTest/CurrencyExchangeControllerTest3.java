package com.ksulima.rest_controllerTest;

        import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
        import com.ksulima.rest_client.ExchangeClient;
        import com.ksulima.rest_controller.CurrencyExchangeController;
        import org.junit.Assert;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.runners.MockitoJUnitRunner;

        import static org.mockito.Mockito.when;

/**
 * Created by Krzysztof Sulima on 04.06.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CurrencyExchangeControllerTest3 {

    @InjectMocks
    CurrencyExchangeController sut;


    @Mock
    private ExchangeClient exchangeClient;



    @Test
    public void shouldReturnExchange(){
        ExchangeModel model = new ExchangeModel();
        model.setBase("EUR");

        when(exchangeClient.getLatestStandardExRates()).thenReturn(model);

        ExchangeModel result = sut.getLatestStandardExRates();
        Assert.assertNotNull(result);
        Assert.assertEquals(model.getBase(), result.getBase());
    }
}
