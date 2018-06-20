package com.worldpay.exception;

public class OfferNotFoundException extends RuntimeException{

    public OfferNotFoundException() {
    }

    public OfferNotFoundException(String message) {
        super(message);
    }
}
