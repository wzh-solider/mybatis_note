package com.powernode.bank.service.impl;

import com.powernode.bank.dao.AccountDao;
import com.powernode.bank.domain.Account;
import com.powernode.bank.exception.MoneyNotEnoughException;
import com.powernode.bank.exception.TransferException;
import com.powernode.bank.service.AccountService;
import com.powernode.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
public class AccountServiceImpl implements AccountService {
    // private AccountDao accountDao = new AccountDaoImpl();

    // 使用javassist动态代理机制，动态生成dao接口的实现类
    private AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);

    @Override
    public void transfer(String fromActno, String toActno, double money)
            throws MoneyNotEnoughException, TransferException {
        // 拿到一个sqlSession对象
        SqlSession sqlSession = SqlSessionUtil.openSession();

        // 1、检查用户余额是否充足
        Account fromAct = accountDao.selectByActno(fromActno);
        if (fromAct.getBalance() < money){
            // 转出不足，提示用户
            throw new MoneyNotEnoughException("余额不足");
        }

        // 转出充足，更新数据
        Account toAct = accountDao.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);

        // 更新转出用户
        int count = accountDao.updateByActno(fromAct);

        // 模拟异常
//        String s = null;
//        s.toString();

        // 更新转入用户
        count += accountDao.updateByActno(toAct);
        if (count != 2) {
            // 如果返回记录条数不是2，则更新失败，抛出异常
            throw new TransferException("更新失败，请重试");
        }

        // 提交事务，关闭连接
        sqlSession.commit();

        SqlSessionUtil.close(sqlSession);
    }
}
