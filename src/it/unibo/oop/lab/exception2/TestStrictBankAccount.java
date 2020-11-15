package it.unibo.oop.lab.exception2;

import org.junit.Test;
import static org.junit.Assert.fail;

/**
 * JUnit test to test
 * {@link StrictBankAccount}.
 * 
 */
public class TestStrictBankAccount {

    private static final int INITIAL_BALANCE = 10000;

	/**
     * Used to test Exceptions on {@link StrictBankAccount}.
     */
    @Test
    public void testBankOperations() {
        /*
         * 1) Creare due StrictBankAccountImpl assegnati a due AccountHolder a
         * scelta, con ammontare iniziale pari a 10000 e nMaxATMTransactions=10
         * 
         * 2) Effetture un numero di operazioni a piacere per verificare che le
         * eccezioni create vengano lanciate soltanto quando opportuno, cioè in
         * presenza di un id utente errato, oppure al superamento del numero di
         * operazioni ATM gratuite.
         */
    	AccountHolder holder1 = new AccountHolder("Mario", "Rossi", 1);
    	AccountHolder holder2 = new AccountHolder("Luigi", "Verdi", 2);
    	StrictBankAccount account1 = new StrictBankAccount(holder1.getUserID(), INITIAL_BALANCE, 10);
    	StrictBankAccount account2 = new StrictBankAccount(holder2.getUserID(), INITIAL_BALANCE, 10);
    	
    	try {
    		account1.deposit(holder2.getUserID(), 2000);
    	} catch (WrongAccountHolderException e) {
    		fail("Wrong account holder");
    	}
    	
    	for (int i=0; i<9; i++) {
    		try {
    			account1.withdrawFromATM(holder1.getUserID(), 800);
    		} catch (WrongAccountHolderException e) {
    			fail("Wrong account holder");
    		}
    		System.out.println("Balance= " + account1.getBalance() 
    			+ "  nTransaction= " + account1.getNTransactions());
    	}
    	
    	try {
    		account1.withdrawFromATM(holder1.getUserID(), 2000);
    	} catch (WrongAccountHolderException e) {
    		fail("Wrong account holder");
    	} catch (TransactionsOverQuotaException e) {
    		fail("No more transactions available");
    	} 
    	
    	try {
    		account2.withdraw(holder2.getUserID(), INITIAL_BALANCE * 2);
    	} catch (WrongAccountHolderException e) {
    		fail("Wrong account holder");
    	} catch (NotEnoughFoundsException e) {
    		fail("Not enough founds");
    	}
    }
}
