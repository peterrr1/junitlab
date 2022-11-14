package junitlab.bank;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import junitlab.bank.impl.FirstNationalBank;
import junitlab.bank.impl.GreatSavingsBank;


@RunWith(Parameterized.class)
public class BankParamTest {

	FirstNationalBank bank;
	
	long amount;
	long rounded;
	
	
	public BankParamTest(long pAmount, long pRounded) {
		this.amount = pAmount;
		this.rounded = pRounded;
	}

	
	@Parameters
	public static List<Object[]> parameters() {
		List<Object[]> params = new ArrayList<Object[]>();
		params.add(new Object[] {1100, 1100});
		params.add(new Object[] {1101, 1100});
		params.add(new Object[] {1149, 1100});
		params.add(new Object[] {1150, 1200});
		params.add(new Object[] {1151, 1200});
		params.add(new Object[] {1199, 1200});
		params.add(new Object[] {1200, 1200});
		return params;
	}
	
	@Test
	public void testWithdrawRounding() throws AccountNotExistsException, NotEnoughFundsException {
		GreatSavingsBank bank = new GreatSavingsBank();
		String accNum = bank.openAccount();
		bank.deposit(accNum, 10000);
		bank.withdraw(accNum, amount);
		long balance = bank.getBalance(accNum);
		long remaining = 10000-rounded;
		assertEquals("Hibás kerekítés",remaining, balance);
	}
}
