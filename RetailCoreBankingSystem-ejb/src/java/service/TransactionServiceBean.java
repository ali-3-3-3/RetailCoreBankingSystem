/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package service;

import Entity.DepositAccount;
import Entity.DepositAccountTransaction;
import Utils.Enumerations.TransactionType;
import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aliya
 */
@Stateless
@Remote(TransactionServiceBeanRemote.class)
public class TransactionServiceBean implements TransactionServiceBeanRemote {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public DepositAccountTransaction createTransaction(Long accountId, BigDecimal amount, TransactionType type, String code) {
        DepositAccount account = em.find(DepositAccount.class, accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        // Adjust account balance based on transaction type
        if (type == TransactionType.CREDIT) {
            account.setLedgerBalance(account.getLedgerBalance().add(amount));
        } else if (type == TransactionType.DEBIT) {
            account.setLedgerBalance(account.getLedgerBalance().subtract(amount));
        }

        // Create transaction record
        DepositAccountTransaction transaction = new DepositAccountTransaction();
        transaction.setDepositAccountTransactionId(accountId);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setCode(code);
        transaction.setTransactionDateTime(new Date());
        
        em.persist(transaction);
        return transaction;
    }
}
