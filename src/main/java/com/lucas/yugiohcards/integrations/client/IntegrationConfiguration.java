package br.com.calcadosbeirario.integrations.client;


import br.com.calcadosbeirario.integrations.utils.BusinessHystrixException;
import br.com.calcadosbeirario.integrations.utils.FeignBadResponseWrapper;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class IntegrationConfiguration {
    @Bean
    public Retryer retryer() {
        return new CustomRetryer(2000, Integer.valueOf(5));
    }

    @Bean
    public ErrorDecoder myErrorDecoder() {
        return new MyErrorDecoder();
    }
}
