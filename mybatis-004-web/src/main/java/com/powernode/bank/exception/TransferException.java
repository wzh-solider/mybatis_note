package com.powernode.bank.exception;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class TransferException extends Exception {
    public TransferException() {}

    public TransferException(String msg) {
        super(msg);
    }
}
