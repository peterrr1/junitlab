package junitlab.bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junitlab.bank.impl.GreatSavingsBank;

public class BankTestFixture {

	GreatSavingsBank bank;
	
	String accNum1;
	String accNum2;
	
	@Before
	public void setUp() throws AccountNotExistsException {
		bank = new GreatSavingsBank();
		this.accNum1 = bank.openAccount();
		this.accNum2 = bank.openAccount();
		bank.deposit(accNum1, 1500);
		bank.deposit(accNum2, 12000);
	}
	
	@Test
	public void testTransfer() throws AccountNotExistsException, NotEnoughFundsException {
		bank.transfer(accNum2, accNum1, 3456);
		long balanceAccNum1 = bank.getBalance(accNum1);
		long balanceAccNum2 = bank.getBalance(accNum2);
		assertEquals("Hibás összes", 4956, balanceAccNum1);
		assertEquals("Hibás összeg", 8544, balanceAccNum2);
	}

	@Test(expected=NotEnoughFundsException.class)
	public void testTransferWithoutEnoughFunds() throws AccountNotExistsException, NotEnoughFundsException {
		bank.transfer(accNum1, accNum2, 3456);
	}
	
}
