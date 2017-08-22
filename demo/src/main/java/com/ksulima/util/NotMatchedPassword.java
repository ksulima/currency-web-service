package com.ksulima.util;

/**
 * Created by Krzysztof Sulima on 20.08.2017.
 */
public final class NotMatchedPassword extends Exception {

    public NotMatchedPassword(){
        super();
    }

    public NotMatchedPassword(final String message, final Throwable cause){
        super(message, cause);
    }

    public NotMatchedPassword(final String message){
        super(message);
    }
}
