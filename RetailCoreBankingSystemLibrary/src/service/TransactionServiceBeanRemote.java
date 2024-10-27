/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package service;

import Entity.DepositAccountTransaction;
import Utils.Enumerations.TransactionType;
import java.math.BigDecimal;
import javax.ejb.Remote;

/**
 *
 * @author aliya
 */
@Remote
public interface TransactionServiceBeanRemote {
    DepositAccountTransaction createTransaction(Long accountId, BigDecimal amount, TransactionType type, String code);
}
