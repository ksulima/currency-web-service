package com.ksulima.util;

/**
 * Created by Krzysztof Sulima on 24.06.2017.
 */
public final class NotSavedException extends RuntimeException {

    public NotSavedException(){
        super();
    };

    public NotSavedException(final String message, final Throwable cause){
        super(message, cause);
    }


    public NotSavedException(final String message){
        super(message);
    }
}
