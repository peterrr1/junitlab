package junitlab.bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import junitlab.bank.impl.GreatSavingsBank;

public class BankTest {
	
	GreatSavingsBank bank;
	
	
	@Before
	public void setUp() {
		this.bank = new GreatSavingsBank();
	}
	

	@Test
	public void testOpenAccount() throws AccountNotExistsException {
		String accNum = bank.openAccount();
		long balance = bank.getBalance(accNum);
		assertEquals("Szamla egyenlege nem 0", 0, balance);
	}
	
	@Test
	public void testUniqueAccount() {
		String accNum = bank.openAccount();
		String accNum2 = bank.openAccount();
		assertNotSame("A két számla számlaszáma azonos", (Object) accNum, (Object) accNum2);
	}
	
	@Test(expected=AccountNotExistsException.class)
	public void testInvalidAccount() throws AccountNotExistsException {
		bank.getBalance("asdasd");
	}
	
	@Test
	public void testDeposit() throws AccountNotExistsException {
		String accNum = bank.openAccount();
		bank.deposit(accNum, 2000);
		long balance = bank.getBalance(accNum);
		assertEquals("A számlára nem a megfelelő befizetés történt", 2000, balance);
	}

}
