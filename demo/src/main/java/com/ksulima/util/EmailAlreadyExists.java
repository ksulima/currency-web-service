package com.ksulima.util;

/**
 * Created by Krzysztof Sulima on 20.08.2017.
 */
public final class EmailAlreadyExists extends Exception {

    public EmailAlreadyExists(){
        super();
    }

    public EmailAlreadyExists(final String message, final Throwable cause){
        super(message, cause);
    }

    public EmailAlreadyExists(final String message){
        super(message);
    }

}
