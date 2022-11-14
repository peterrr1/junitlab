package junitlab.bank;

/**
 * Ez a kiv�tel jelzi, ha �rv�nytelen vagy nem l�tez� banksz�mlasz�mot
 * adtunk meg valamelyik tranzakci� sor�n.
 */
public class AccountNotExistsException extends BankException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5689676544530088738L;

	/**
	 * A kiv�tel l�trehoz�sa.
	 * @param accountNumber A nem l�tez� sz�mlasz�m.
	 */
	public AccountNotExistsException(String accountNumber) {
		super(accountNumber, "Account does not exist: " + accountNumber);
	}
}
