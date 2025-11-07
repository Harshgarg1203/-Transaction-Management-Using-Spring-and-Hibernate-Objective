package com.example.bank;

import com.example.bank.config.AppConfig;
import com.example.bank.entity.Account;
import com.example.bank.service.BankService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.hibernate.SessionFactory;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        try {
            BankService bankService = ctx.getBean(BankService.class);
            SessionFactory sf = ctx.getBean(SessionFactory.class);

            // Create sample accounts (using a transaction)
            sf.getCurrentSession().beginTransaction();
            Account a1 = new Account("Alice", 1000.0);
            Account a2 = new Account("Bob", 500.0);
            sf.getCurrentSession().persist(a1);
            sf.getCurrentSession().persist(a2);
            sf.getCurrentSession().getTransaction().commit();

            System.out.println("Initial balances:");
            System.out.println(a1);
            System.out.println(a2);

            // do transfer
            try {
                bankService.transfer(a1.getId(), a2.getId(), 300.0);
                System.out.println("Transfer successful.");
            } catch (Exception e) {
                System.err.println("Transfer failed: " + e.getMessage());
            }

            // show balances after transfer
            sf.getCurrentSession().beginTransaction();
            Account a1r = sf.getCurrentSession().get(Account.class, a1.getId());
            Account a2r = sf.getCurrentSession().get(Account.class, a2.getId());
            sf.getCurrentSession().getTransaction().commit();

            System.out.println("Balances after transfer:");
            System.out.println(a1r);
            System.out.println(a2r);

        } finally {
            ctx.close();
        }
    }
}
