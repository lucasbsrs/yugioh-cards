package com.lucas.yugiohcards.integrations.client;

import feign.RetryableException;
import feign.Retryer;

public class CustomRetryer implements Retryer {
    private final int maxAttempts;
    private final long backoff;
    int attempt;
    public CustomRetryer() {
        this(2000, 5); //5 times, each 2 seconds
    }
    public CustomRetryer(long backoff, int maxAttempts) {
        this.backoff = backoff;
        this.maxAttempts = maxAttempts;
        this.attempt = 1;
    }
    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts) {
            throw e;
        }
        try {
            Thread.sleep(backoff);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
    @Override
    public Retryer clone() {
        return new CustomRetryer(backoff, maxAttempts);
    }
}
