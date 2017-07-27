package com.ksulima.rest_client;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
/**
 * Created by Krzysztof Sulima on 20.07.2017.
 */


@RunWith(MockitoJUnitRunner.class)
public class ExchangeClientTest {


    private String url = "http://api.fixer.io/latest?symbols=USD,GBP,PLN&base=EUR";

    @InjectMocks
    ExchangeClient sut;


    @Mock(answer = Answers.RETURNS_MOCKS)
    RestTemplate rest;

    @Test
    public void getLatestStandardExRatesTest(){
//        sut.getLatestStandardExRates();
//        verify(rest, times(1)).getForObject(url, ExchangeModel.class);

        ExchangeModel item = new ExchangeModel();
        item.setBase("PLN");
        item.setDate("2017-01-01");

        when(rest.getForObject(url, ExchangeModel.class)).thenReturn(item);

        ExchangeModel result = sut.getLatestStandardExRates();

        assertThat(item, is(result));
        assertThat(item.getDate(), is(result.getDate()));
    }

//    @Test
//    public void getSelectedExRatesTest(){
//        Map<String, String> vars = new HashMap<>();
//        vars.put("base", "varA");
//        vars.put("currency", "varB");
//        vars.put("date", "varC");
//
//        ExchangeModel result = sut.getSelectedExRates("varA", "varB", "varC");
//        //TODO
//    }





}
