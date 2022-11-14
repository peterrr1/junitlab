package junitlab.bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junitlab.bank.impl.GreatSavingsBank;

public class GreatSavingsBankTest {

	GreatSavingsBank bank;
	
	@Before
	public void setUp() {
		bank = new GreatSavingsBank();
	}
	
	//Close account tests
	@Test
	public void testCloseAccountWhenBalanceIsZero() throws AccountNotExistsException {
		String accNum = bank.openAccount();
		boolean succesOfClosing = bank.closeAccount(accNum);
		assertTrue("Sikertelen számla bezárás", succesOfClosing);
	}
	@Test
	public void testCloseAccountWhenBalanceIsNotZero() throws AccountNotExistsException {
		String accNum = bank.openAccount();
		bank.deposit(accNum, 1000);
		boolean succesOfClosing = bank.closeAccount(accNum);
		assertFalse("A számla egyenlege 0.", succesOfClosing);
	}
	
	@Test(expected=AccountNotExistsException.class)
	public void testCloseAccountWhenItsNotExists() throws AccountNotExistsException {
		bank.closeAccount("asd");
	}
	
	//Negative parameter test
	@Test(expected=IllegalArgumentException.class)
	public void testTransferNegativeAmount() throws AccountNotExistsException, NotEnoughFundsException {
		String accNum1 = bank.openAccount();
		String accNum2 = bank.openAccount();
		bank.transfer(accNum1, accNum2, -1000);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testWithdrawNegativeAmount() throws AccountNotExistsException, NotEnoughFundsException {
		String accNum = bank.openAccount();
		bank.withdraw(accNum, -1010);
	}
}
