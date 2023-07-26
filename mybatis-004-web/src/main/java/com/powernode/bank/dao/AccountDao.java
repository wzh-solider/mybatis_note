package com.powernode.bank.dao;

import com.powernode.bank.domain.Account;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public interface AccountDao {
    /**
     * 根据账号返回账户信息
     * @param actno 账号
     * @return 账户信息
     */
    Account selectByActno(String actno);

    /**
     * 更新账户
     * @param act 账户信息
     * @return 1 更新成功
     */
    int updateByActno(Account act);
}
