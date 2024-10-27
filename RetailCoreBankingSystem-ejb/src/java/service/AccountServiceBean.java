/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package service;

import Entity.Customer;
import Entity.DepositAccount;
import Utils.Enumerations.DepositAccountType;
import service.AccountServiceBeanRemote;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aliya
 */
@Stateless
@Remote(AccountServiceBeanRemote.class)
public class AccountServiceBean implements AccountServiceBeanRemote {

    @EJB
    private CustomerServiceBeanLocal customerServiceBean;

    @PersistenceContext
    private EntityManager em;
    
    
    
    @Override
    public DepositAccount openDepositAccount(Long customerId, BigDecimal holdingBalance, BigDecimal initialDeposit, DepositAccountType accountType) {
        DepositAccount account = new DepositAccount();
        Customer customer = customerServiceBean.getCustomerById(customerId);
        account.setCustomer(customer);
        account.setAccountType(accountType);
        account.setHoldBalance(holdingBalance);
        account.setLedgerBalance(initialDeposit);
        account.setEnabled(Boolean.TRUE);
        
        em.persist(account);
        em.flush();
        return account;
    }
    
    @Override
    public BigDecimal enquireAvailableBalance(Long accountId) {
        DepositAccount account = em.find(DepositAccount.class, accountId);
        BigDecimal availableBalance = account.getLedgerBalance().subtract(account.getHoldBalance());
        return availableBalance;
    }
}
