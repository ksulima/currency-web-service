package com.ksulima.rest_controllerTest;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.rest_client.ExchangeClient;
import com.ksulima.rest_controller.CurrencyExchangeController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Krzysztof Sulima on 28.05.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CurrencyExchangeControllerTest2 {

    @InjectMocks
    CurrencyExchangeController sut;


    @Mock(answer = Answers.RETURNS_MOCKS)
    private ExchangeClient exchangeClient;


    @Test
    public void shouldReturnExchange(){
        ExchangeModel model = sut.getExchange();
        Assert.assertNotNull(model);
    }




}
