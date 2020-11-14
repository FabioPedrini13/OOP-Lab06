package it.unibo.oop.lab.exception2;

import org.junit.Test;

/**
 * JUnit test to test
 * {@link StrictBankAccount}.
 * 
 */
public class TestStrictBankAccount {

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
    	StrictBankAccount account1 = new StrictBankAccount(1, 10000, 10);
    	StrictBankAccount account2 = new StrictBankAccount(2, 10000, 10);
    	
    	account1.deposit(holder1.getUserID(), 2000);
    	for (int i=0; i<9; i++) {
    		account1.withdrawFromATM(holder1.getUserID(), 800);
    		System.out.println("Balance= " + account1.getBalance() 
    			+ "  nTransaction= " + account1.getNTransactions());
    	}
    	account2.withdraw(holder2.getUserID(), 2000);
    }
}
