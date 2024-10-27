/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionRemote.java to edit this template
 */
package service;

import Entity.Customer;
import javax.ejb.Remote;

/**
 *
 * @author aliya
 */
@Remote
public interface CustomerServiceBeanRemote {
    Customer createCustomer(String firstName, String lastName, String idNumber, String contactNumber, String address1, String address2, String postalCode);
}
