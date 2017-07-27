package com.ksulima.rest_controllerTest;

import com.ksulima.bussiness_logic_implementation.ConversionServiceImpl;
import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.rest_controller.ConversionController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


/**
 * Created by Krzysztof Sulima on 21.07.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConversionControllerTest {


    @InjectMocks
    ConversionController sut;

    @Mock
    ConversionServiceImpl conversionServiceMOCK;

    @Test
    public void calculateConversionShouldHasHttpStatusBad() {

        when(conversionServiceMOCK.calculateConversion(any(), any(), any(), any())).thenReturn(null);
        ResponseEntity result = sut.calculateConversion(any(), any(), any(), any());
        assertThat(HttpStatus.BAD_REQUEST, is(result.getStatusCode()));
    }


    @Test
    public void calculateConversionShouldHasHttpStatusOK() {

        ExchangeModel item = new ExchangeModel();
        when(conversionServiceMOCK.calculateConversion(any(), any(), any(), any())).thenReturn(item);
        ResponseEntity result = sut.calculateConversion(any(), any(), any(), any());
        assertThat(HttpStatus.OK, is(result.getStatusCode()));
    }



}
