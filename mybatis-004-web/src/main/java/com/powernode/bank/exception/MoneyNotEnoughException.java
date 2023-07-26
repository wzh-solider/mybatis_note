package com.powernode.bank.exception;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class MoneyNotEnoughException extends Exception {
    public MoneyNotEnoughException() {}

    public MoneyNotEnoughException(String msg) {
        super(msg);
    }
}
