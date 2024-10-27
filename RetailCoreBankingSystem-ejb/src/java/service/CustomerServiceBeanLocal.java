/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Entity.Customer;
import javax.ejb.Local;

/**
 *
 * @author aliya
 */
@Local
public interface CustomerServiceBeanLocal {
    Customer getCustomerById(Long customerId);
}
