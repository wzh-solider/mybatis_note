package com.powernode.bank.service;

import com.powernode.bank.exception.MoneyNotEnoughException;
import com.powernode.bank.exception.TransferException;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface AccountService {

    /**
     * 银行转账业务
     * @param fromActno 转出账号
     * @param toActno 转入账号
     * @param money 转账金额
     */
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException;
}
