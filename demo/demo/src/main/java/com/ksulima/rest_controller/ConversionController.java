package com.ksulima.rest_controller;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.bussiness_logic_interface.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Krzysztof Sulima on 22.06.2017.
 */

@RestController
@RequestMapping
public class ConversionController {

    @Autowired
    private ConversionService conversionService;

    @RequestMapping("/convert/{base}/{currency}/{amount}/{date}")
    public ResponseEntity<ExchangeModel> calculateConversion(@PathVariable String base,
                                                             @PathVariable String currency,
                                                             @PathVariable Integer amount,
                                                             @PathVariable String date) {

        ExchangeModel result = conversionService.calculateConversion(base, currency, amount, date);

        if(result == null){
            return new ResponseEntity<ExchangeModel>(result, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }


}
