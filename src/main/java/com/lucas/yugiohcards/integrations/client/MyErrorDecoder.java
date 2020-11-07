package com.lucas.yugiohcards.integrations.client;

import com.lucas.yugiohcards.integrations.utils.BusinessHystrixException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class MyErrorDecoder implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {
        if(response.status() >= 400 && response.status() <= 499){
            return new BusinessHystrixException(methodKey, response.status());
        }
        return feign.FeignException.errorStatus(methodKey, response);
    }
}
