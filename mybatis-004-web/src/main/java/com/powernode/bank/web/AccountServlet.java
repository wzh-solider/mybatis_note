package com.powernode.bank.web;

import com.powernode.bank.exception.MoneyNotEnoughException;
import com.powernode.bank.exception.TransferException;
import com.powernode.bank.service.AccountService;
import com.powernode.bank.service.impl.AccountServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Solider
 * @version 1.0
 * @since 1.0
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取表单数据
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));

        try {
            // 调用service业务层
            accountService.transfer(fromActno, toActno, money);
            // 更新成功，展示View
            response.sendRedirect(request.getContextPath() + "/success.html");
        } catch (MoneyNotEnoughException e) {
            // 更新失败，余额不足
            response.sendRedirect(request.getContextPath() + "/error1.html");
        } catch (TransferException e) {
            // 更新失败
            response.sendRedirect(request.getContextPath() + "/error2.html");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error2.html");
        }
    }
}
