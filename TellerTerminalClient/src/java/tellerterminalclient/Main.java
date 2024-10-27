/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tellerterminalclient;

import Utils.Enumerations.DepositAccountType;
import java.math.BigDecimal;
import java.util.Scanner;
import javax.ejb.EJB;
import service.AccountServiceBeanRemote;
import service.AtmCardServiceBeanRemote;
import service.CustomerServiceBeanRemote;

/**
 *
 * @author aliya
 */
public class Main {
    
    @EJB
    private static CustomerServiceBeanRemote customerServiceBean;

    @EJB
    private static AccountServiceBeanRemote accountServiceBean;

    @EJB
    private static AtmCardServiceBeanRemote atmCardServiceBean;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Teller Terminal ---");
            System.out.println("1. Create Customer");
            System.out.println("2. Open Deposit Account");
            System.out.println("3. Issue ATM Card");
            System.out.println("4. Issue Replacement ATM Card");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createCustomer(scanner);
                    break;
                case 2:
                    openDepositAccount(scanner);
                    break;
                case 3:
                    issueAtmCard(scanner);
                    break;
                case 4:
                    issueReplacementAtmCard(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void createCustomer(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter identification number: ");
        String idNumber = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contactNumber = scanner.nextLine();
        System.out.print("Enter address line 1: ");
        String address1 = scanner.nextLine();
        System.out.print("Enter address line 2: ");
        String address2 = scanner.nextLine();
        System.out.print("Enter postal code: ");
        String postalCode = scanner.next();
        
        customerServiceBean.createCustomer(firstName, lastName, idNumber, contactNumber, address1, address2, postalCode);
        
        System.out.println("Customer created successfully.");
    }

    private static void openDepositAccount(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        Long customerId = scanner.nextLong();
        System.out.print("Enter initial deposit amount: ");
        BigDecimal holdingBalance = scanner.nextBigDecimal();
        System.out.print("Enter initial deposit amount: ");
        BigDecimal initialDeposit = scanner.nextBigDecimal();
        
        int i = 0;
        
        do {
        System.out.println("Choose account type: ");
        System.out.println("1. Savings");
        System.out.println("2. Current");
        
        DepositAccountType accountType = DepositAccountType.SAVINGS;
        switch (i) {
                case 1:
                    accountType = DepositAccountType.SAVINGS;
                    accountServiceBean.openDepositAccount(customerId, holdingBalance, initialDeposit, accountType);
                    break;
                case 2:
                    accountType = DepositAccountType.CURRENT;
                    accountServiceBean.openDepositAccount(customerId, holdingBalance, initialDeposit, accountType);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while(i != 2);
        
        System.out.println("Deposit account opened successfully.");
    }

    private static void issueAtmCard(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        Long customerId = scanner.nextLong();
        System.out.print("Enter new card number: ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter new name on card: ");
        String nameOnCard = scanner.nextLine();
        System.out.print("Enter new pin: ");
        String pin = scanner.next();

        atmCardServiceBean.issueAtmCard(customerId, cardNumber, nameOnCard, pin);
        System.out.println("ATM card issued successfully.");
    }

    private static void issueReplacementAtmCard(Scanner scanner) {
        System.out.print("Enter customer ID: ");
        Long customerId = scanner.nextLong();
        System.out.print("Enter old ATM card ID: ");
        Long oldAtmCardId = scanner.nextLong();
        System.out.print("Enter new card number: ");
        String newCardNumber = scanner.nextLine();
        System.out.print("Enter new name on card: ");
        String nameOnCard = scanner.nextLine();
        System.out.print("Enter new pin: ");
        String pin = scanner.next();
        
        atmCardServiceBean.issueReplacementAtmCard(customerId, oldAtmCardId, newCardNumber, nameOnCard, pin);
        System.out.println("Replacement ATM card issued successfully.");
    }
    
}
