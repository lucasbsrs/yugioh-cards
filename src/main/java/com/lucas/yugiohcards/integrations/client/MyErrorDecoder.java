package br.com.calcadosbeirario.integrations.client;

import br.com.calcadosbeirario.integrations.utils.BusinessHystrixException;
import com.netflix.hystrix.exception.HystrixBadRequestException;
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
