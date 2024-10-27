/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package service;

import Entity.AtmCard;
import javax.ejb.Remote;

/**
 *
 * @author aliya
 */
@Remote
public interface AtmCardServiceBeanRemote {
    AtmCard issueAtmCard(Long customerId, String cardNumber, String nameOnCard, String pin);
    
    void changePin(Long atmCardId, String newPin);
    
    AtmCard validateAtmCard(String cardNumber, String pin);
    
    AtmCard issueReplacementAtmCard(Long customerId, Long oldCardId, String newCardNumber, String nameOnCard, String pin);
    
    
}
