/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package service;

import Entity.Customer;
import javax.ejb.Local;
import javax.ejb.Remote;
import service.CustomerServiceBeanLocal;
import service.CustomerServiceBeanRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aliya
 */


@Stateless
@Local(CustomerServiceBeanLocal.class)
@Remote(CustomerServiceBeanRemote.class)
public class CustomerServiceBean implements CustomerServiceBeanLocal, CustomerServiceBeanRemote {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Customer createCustomer(String firstName, String lastName, String idNumber, String contactNumber, String address1, String address2, String postalCode) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setIdentificationNumber(idNumber);
        customer.setContactNumber(contactNumber);
        customer.setAddressLine1(address1);
        customer.setAddressLine2(address2);
        customer.setPostalCode(postalCode);
        
        em.persist(customer);
        em.flush();
        return customer;
    }
    
    @Override
    public Customer getCustomerById(Long customerId) {
        return em.find(Customer.class, customerId);
    }
}
