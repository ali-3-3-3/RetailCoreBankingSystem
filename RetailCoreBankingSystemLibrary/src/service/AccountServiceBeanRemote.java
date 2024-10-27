/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package service;

import Entity.DepositAccount;
import Utils.Enumerations.DepositAccountType;
import java.math.BigDecimal;
import javax.ejb.Remote;

/**
 *
 * @author aliya
 */
@Remote
public interface AccountServiceBeanRemote {
    DepositAccount openDepositAccount(Long customerId, BigDecimal holdingBalance, BigDecimal initialDeposit, DepositAccountType accountType);
    
    BigDecimal enquireAvailableBalance(Long accountId);
}
