package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

// acristoferid8@bigcartel.com
// SpFgY5(TiI1

public class Menu {
	
	private Scanner scanner;

	public static void main(String[] args) {
		System.out.println("Welcome to International Toy Bank!");

		Menu menu = new Menu();
		menu.scanner = new Scanner(System.in);

		Customer customer = menu.authenticateUser();

		if(customer != null) {
			Account account = DataSource.getAccount(customer.getAccountId());
			if(account != null) {
menu.showMenu(customer, account);
			}
			
		}

		menu.scanner.close();
	}

	private Customer authenticateUser() {
		System.out.println("Please enter your username: ");
		String username = scanner.next();

		System.out.println("Please enter your password: ");
		String password = scanner.next();

		Customer customer = null;
		try {
			customer = Authenticator.login(username, password);
			
		} catch (LoginException e) {
			System.out.println("Login error: " + e.getMessage());
			e.printStackTrace();
		}

		return customer;
	}

	private void showMenu(Customer customer, Account account) {
		int selection = 0;
		while(selection != 4 && customer.isAuthenticated()) {
			System.out.println("============================================");
			System.out.println("Please select one of the following options: ");
			System.out.println("1: Deposit");
			System.out.println("2: Withdraw");
			System.out.println("3: Check Balance");
			System.out.println("4: Exit");

			selection = scanner.nextInt();
			double amount;

			switch(selection) {
				case 1:
					System.out.println("How much do you want to deposit?");
					amount = scanner.nextDouble();

					try {
						account.deposit(amount);
					} catch(AmountException e) {
						System.out.println("Error: " + e.getMessage());
						System.out.println("Please try again");
					}
					
					break;

				case 2:
					System.out.println("How much do you want to withdraw?");
					amount = scanner.nextDouble();

					try {
						account.withdraw(amount);
					} catch(AmountException e) {
						System.out.println("Error: " + e.getMessage());
						System.out.println("Please try again");
					}
					
					break;

				case 3: 
					System.out.println("Acount balance: " + account.getBalance());
					break;

				case 4:
					System.out.println("Thanks for banking at Toy Bank!");
					Authenticator.logout(customer);
					break;

				default: 
					System.out.println("Invalid option, please try again");
					break;
			}
		}
	}


}
