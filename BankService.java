package com.example.bank.service;

import com.example.bank.dao.AccountDAO;
import com.example.bank.entity.Account;
import com.example.bank.entity.TransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {

    @Autowired
    private AccountDAO accountDAO;

    @Transactional(rollbackFor = Exception.class)
    public void transfer(Integer fromId, Integer toId, Double amount) throws RuntimeException {
        Account from = accountDAO.findById(fromId);
        Account to = accountDAO.findById(toId);

        if (from == null || to == null) {
            throw new RuntimeException("One of the accounts does not exist.");
        }

        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds in account: " + fromId);
        }

        // Perform transfer
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountDAO.update(from);
        accountDAO.update(to);

        // Save transaction record
        TransactionRecord tr = new TransactionRecord(fromId, toId, amount);
        accountDAO.saveTransaction(tr);
    }
}
