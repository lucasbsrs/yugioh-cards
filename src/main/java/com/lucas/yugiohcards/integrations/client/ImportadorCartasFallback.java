package com.lucas.yugiohcards.integrations.client;

import feign.FeignException;
import feign.hystrix.FallbackFactory;

public class ImportadorCartasFallback implements FallbackFactory<ImportadorCartasClient> {

    @Override
    public ImportadorCartasClient create(Throwable cause) {
        String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : "";

        return null;
    }

}
