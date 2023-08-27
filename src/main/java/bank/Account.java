package bank;

import bank.exceptions.AmountException;

public class Account {

	// vars
  private int id;
  private String type;
  private double balance;

	// constructor
  public Account(int id, String type, double balance) {
    setId(id);
    setType(type);
    setBalance(balance);
  }

	// getters and setters
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

	// methods
	public void deposit(double amount) throws AmountException {

		if(amount < 0.01) {
			throw new AmountException("The minimum deposit is 0.01");
		} else {
			double newBalance = balance + amount;

			setBalance(newBalance);
			DataSource.updateAccountBalance(id, newBalance);
		}
	}

	public void withdraw(double amount) throws AmountException {
		if(amount < 0.01) {
			throw new AmountException("The minimum deposit is 0.01");
		} else if(amount > balance) {
			throw new AmountException("Insufficient funds for withdrawl");
		} else {
			double newBalance = balance - amount;

			setBalance(newBalance);
			DataSource.updateAccountBalance(id, newBalance);
		}
	}

}
