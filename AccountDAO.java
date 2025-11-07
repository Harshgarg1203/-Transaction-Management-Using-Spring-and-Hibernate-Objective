package com.example.bank.dao;

import com.example.bank.entity.Account;
import com.example.bank.entity.TransactionRecord;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Account findById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }

    public void update(Account account) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(account);
    }

    public void saveTransaction(TransactionRecord tr) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(tr);
    }
}
