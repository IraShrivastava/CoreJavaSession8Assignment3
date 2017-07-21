package assignment8;
class BankAccount 
{
	private int balance;
	public BankAccount(int balance) 
	{
		this.balance = balance;
	}
	public int getBalance() 
	{
		return balance;
	}
	// withdraw method - deduct the amount from balance
	public void withdraw(int amount) 
	{
		balance = balance - amount;
	}
	// deposit method - add the amount to balance
	public void deposit(int amount) 
	{
		balance = balance + amount;
	}
}
public class Assignment8_3 implements Runnable {
	private BankAccount acct = new BankAccount(100);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Assignment8_3 bank = new Assignment8_3();
		Thread first = new Thread(bank);
		Thread second = new Thread(bank);
		first.setName("Ram");
		second.setName("Shyam");
		// Trigger both the threads.
		first.start();
		second.start();
	}
	public void run() 
	{
		// Perform withdraw 5 times
		for (int x = 0; x < 5; x++) 
		{
			makeWithdrawal(20);
			if (acct.getBalance() < 0) 
			{
				System.out.println("account is overdrawn!");
			}
		}
		//perform deposit 5 times
		for (int x = 0; x < 5; x++) 
		{
			makeDeposit(20);
		}
	}
	// Synchronized makeWithdrawal method. So that, only one withdraw can happen at a time.
	private synchronized void makeWithdrawal(int amt)
	{
		if (acct.getBalance() >= amt) 
		{
			System.out.println(Thread.currentThread().getName() + " is going to withdraw " + 20);
			try 
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException ex) 
			{
			}
			acct.withdraw(amt);
			System.out.println(Thread.currentThread().getName() + " completes the withdraw");
			System.out.println("Balance = " + acct.getBalance());
		} 
		else 
		{
			System.out.println("Not enough in account for " + Thread.currentThread().getName() + " to withdraw " + amt);
		}
		System.out.println("------------------------------------------------------");
	}

	// Synchronized makeDeposit method. So that, only one deposit can happen at a time.
	private synchronized void makeDeposit(int amt) 
	{
		System.out.println(Thread.currentThread().getName() + " is going to Deposit " +amt);
		try 
		{
			Thread.sleep(100);
		} 
		catch (InterruptedException ex) 
		{
		}
		acct.deposit(amt);
		System.out.println(Thread.currentThread().getName() + " completes the deposit");
		System.out.println("------------------------------------------------------");
	}
}
