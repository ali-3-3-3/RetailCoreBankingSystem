/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package automatedtellermachineclient;

import Entity.AtmCard;
import java.math.BigDecimal;
import java.util.Scanner;
import javax.ejb.EJB;
import service.AccountServiceBeanRemote;
import service.AtmCardServiceBeanRemote;

/**
 *
 * @author aliya
 */
public class Main {
    
    @EJB
    private static AtmCardServiceBeanRemote atmCardService;

    @EJB
    private static AccountServiceBeanRemote accountService;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- ATM ---");
            System.out.println("1. Insert ATM Card");
            System.out.println("2. Change PIN");
            System.out.println("3. Enquire Available Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    insertAtmCard(scanner);
                    break;
                case 2:
                    changePin(scanner);
                    break;
                case 3:
                    enquireAvailableBalance(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static AtmCard insertAtmCard(Scanner scanner) {
        System.out.print("Enter ATM card number: ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        try {
            System.out.println("ATM card inserted successfully.");
            return atmCardService.validateAtmCard(cardNumber, pin);
            
        } catch (Exception e) {
            System.out.println("Invalid card or PIN.");
            return null;
        }
        
    }

    private static void changePin(Scanner scanner) {
        System.out.print("Enter ATM card ID: ");
        Long atmCardId = scanner.nextLong();
        System.out.print("Enter current PIN: ");
        String currentPin = scanner.nextLine();
        System.out.print("Enter new PIN: ");
        String newPin = scanner.next();

        atmCardService.changePin(atmCardId, newPin);
        
        System.out.println("PIN changed successfully.");
    }

    private static BigDecimal enquireAvailableBalance(Scanner scanner) {
        System.out.print("Enter ATM card ID: ");
        Long atmCardId = scanner.nextLong();

        System.out.println(accountService.enquireAvailableBalance(atmCardId));
        return accountService.enquireAvailableBalance(atmCardId);
    }
    
}
