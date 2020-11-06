package br.com.calcadosbeirario.integrations.utils;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import lombok.Getter;

public class BusinessHystrixException extends HystrixBadRequestException {

    @Getter
    private int errCode = 0;

    public BusinessHystrixException(String message, int errCode) {
        super(message);
        this.errCode = errCode;
    }
}
