package com.minibackend.minibackend.Error.Exception;

public class ProductOutOfStockException extends RuntimeException {
    public ProductOutOfStockException() {
    }

    public ProductOutOfStockException(String arg0) {
        super(arg0);
    }

    public ProductOutOfStockException(Throwable arg0) {
        super(arg0);
    }

    public ProductOutOfStockException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ProductOutOfStockException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

}
