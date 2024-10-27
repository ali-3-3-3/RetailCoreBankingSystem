/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package service;

import Entity.AtmCard;
import Entity.Customer;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aliya
 */
@Stateless
@Remote(AtmCardServiceBeanRemote.class)
public class AtmCardServiceBean implements AtmCardServiceBeanRemote {

    @EJB
    private CustomerServiceBeanLocal customerServiceBean;

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public AtmCard issueAtmCard(Long customerId, String cardNumber, String nameOnCard, String pin) {
        AtmCard atmCard = new AtmCard();
        Customer customer = customerServiceBean.getCustomerById(customerId);
        atmCard.setCustomer(customer);
        atmCard.setCardNumber(cardNumber);
        atmCard.setNameOnCard(nameOnCard);
        atmCard.setPin(pin);
        atmCard.setEnabled(true);
        
        em.persist(atmCard);
        return atmCard;
    }
    
    @Override
    public void changePin(Long atmCardId, String newPin) {
        AtmCard atmCard = em.find(AtmCard.class, atmCardId);
        if (atmCard != null) {
            atmCard.setPin(newPin);
        }
    }
    
    @Override
    public AtmCard validateAtmCard(String cardNumber, String pin) {
        AtmCard atmCard = em.createQuery("SELECT a FROM AtmCard a WHERE a.cardNumber = :cardNumber AND a.pin = :pin AND a.enabled = true", AtmCard.class)
                            .setParameter("cardNumber", cardNumber)
                            .setParameter("pin", pin)
                            .getSingleResult();
        return atmCard;
    }
    
    @Override
    public AtmCard issueReplacementAtmCard(Long customerId, Long oldCardId, String newCardNumber, String nameOnCard, String pin) {
        String jpql = "DELETE FROM AtmCard a WHERE a.id = :atmCardId";
        Query query = em.createQuery(jpql);
        query.setParameter("atmCardId", oldCardId);
        query.executeUpdate();
        
        AtmCard newAtmCard = issueAtmCard(customerId, newCardNumber, nameOnCard, pin);
        return newAtmCard;
    }
}
