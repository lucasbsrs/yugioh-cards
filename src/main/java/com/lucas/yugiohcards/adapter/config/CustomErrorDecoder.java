package com.lucas.yugiohcards.adapter.config;

import com.lucas.yugiohcards.application.exceptions.BadRequestException;
import com.lucas.yugiohcards.application.exceptions.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 400:
                return new BadRequestException("");
            case 404:
                return new NotFoundException("");
            default:
                return new Exception("Generic error");
        }
    }

}
