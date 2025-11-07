package com.example.bank.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_record")
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_account")
    private Integer fromAccount;

    @Column(name = "to_account")
    private Integer toAccount;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public TransactionRecord() {}

    public TransactionRecord(Integer fromAccount, Integer toAccount, Double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getFromAccount() { return fromAccount; }
    public void setFromAccount(Integer fromAccount) { this.fromAccount = fromAccount; }

    public Integer getToAccount() { return toAccount; }
    public void setToAccount(Integer toAccount) { this.toAccount = toAccount; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "TransactionRecord{id=" + id + ", from=" + fromAccount + ", to=" + toAccount + ", amount=" + amount + ", time=" + timestamp + "}";
    }
}
