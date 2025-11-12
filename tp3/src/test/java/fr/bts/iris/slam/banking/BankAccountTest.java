package fr.bts.iris.slam.banking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe BankAccount.
 * 
 * Cette classe de test illustre :
 * - Tests exhaustifs d'un objet métier
 * - Validation des règles de gestion
 * - Tests de construction avec différents paramètres
 * - Tests d'exceptions avec messages explicites
 * - Tests d'état et de comportement
 */
class BankAccountTest {
    
    private BankAccount account;
    private final String ACCOUNT_NUMBER = "ACC001";
    private final String HOLDER_NAME = "Alice Dupont";
    private final double INITIAL_BALANCE = 100.0;
    
    /**
     * Prépare un compte valide avant chaque test
     */
    @BeforeEach
    void setUp() {
        account = new BankAccount(ACCOUNT_NUMBER, HOLDER_NAME, INITIAL_BALANCE);
    }
    
    // === TESTS DE CONSTRUCTION ===
    
    @Test
    void shouldCreateAccountWithValidParameters() {
        // ARRANGE & ACT - Création d'un nouveau compte
        BankAccount newAccount = new BankAccount("ACC002", "Bob Martin", 50.0);
        
        // ASSERT - Vérification de l'état initial
        assertEquals("ACC002", newAccount.getAccountNumber());
        assertEquals("Bob Martin", newAccount.getHolderName());
        assertEquals(50.0, newAccount.getBalance());
        assertTrue(newAccount.isActive());
        assertEquals(0.0, newAccount.getOverdraftLimit());
    }
    
    @Test
    void shouldRejectNullAccountNumber() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount(null, "Bob Martin", 50.0)
                // <- lambda function
        );
        assertEquals("Account number cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldRejectEmptyAccountNumber() {
        // Tester avec une chaîne vide ""

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount("", "Bob Martin", 50.0)
                // <- lambda function
        );
        assertEquals("Account number cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldRejectNullHolderName() {

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount("ACC002", null, 50.0)
                // <- lambda function
        );
        assertEquals("Holder name cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldRejectEmptyHolderName() {

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount("ACC002", "", 50.0)
                // <- lambda function
        );
        assertEquals("Holder name cannot be null or empty", exception.getMessage());
    }
    
    @Test
    void shouldRejectNegativeInitialBalance() {
        // Tester avec -50.0 par exemple

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount("ACC002", "Bob Martin", -50.0)
                // <- lambda function
        );
        assertEquals("Initial balance cannot be negative", exception.getMessage());
    }
    
    @Test
    void shouldAcceptZeroInitialBalance() {
        // ARRANGE & ACT
        BankAccount zeroAccount = new BankAccount("ACC003", "Charlie", 0.0);
        
        // ASSERT
        assertEquals(0.0, zeroAccount.getBalance());
    }
    
    // === TESTS DE DÉPÔT ===
    
    @Test
    void shouldIncreaseBalanceOnValidDeposit() {
        // ARRANGE
        double initialBalance = account.getBalance();
        double depositAmount = 50.0;
        
        // ACT
        account.deposit(depositAmount);
        
        // ASSERT
        assertEquals(initialBalance + depositAmount, account.getBalance());
    }
    
    @Test
    void shouldRejectZeroDeposit() {
        // Le dépôt de 0.0 doit intercepter IllegalArgumentException
        // ARRANGE - Préparer les données
        double depositAmount = 0.0;


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(depositAmount) // <- lambda function
        );
        assertEquals("Deposit amount must be positive", exception.getMessage());
    }
    
    @Test
    void shouldRejectNegativeDeposit() {
        // ARRANGE - Préparer les données
        double depositAmount = -42.0;


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(depositAmount) // <- lambda function
        );
        assertEquals("Deposit amount must be positive", exception.getMessage());
    }
    
    @Test
    void shouldRejectDepositOnInactiveAccount() {
        // ARRANGE
        account.deactivate();
        
        // ACT & ASSERT
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> account.deposit(50.0)
        );
        
        // Vérification optionnelle du message
        assertTrue(exception.getMessage().contains("not active") || 
                  exception.getMessage().contains("inactive"));
    }
    
    // === TESTS DE RETRAIT ===
    
    @Test
    void shouldDecreaseBalanceOnValidWithdrawal() {
        // ARRANGE
        double initialBalance = account.getBalance();
        double withdrawAmount = 50.0;

        // ACT
        account.withdraw(withdrawAmount);

        // ASSERT
        assertEquals(initialBalance - withdrawAmount, account.getBalance());
    }
    
    @Test
    void shouldRejectWithdrawalExceedingBalance() {
        // Doit intercepter IllegalArgumentException
        double withdrawAmount = 152.0;


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(withdrawAmount) // <- lambda function
        );
        assertEquals("Insufficient funds", exception.getMessage());
    }
    
    @Test
    void shouldAllowWithdrawalWithinOverdraftLimit() {
        // ARRANGE
        account.setOverdraftLimit(50.0);
        double withdrawalAmount = 130.0; // Plus que le solde mais dans la limite
        
        // ACT
        account.withdraw(withdrawalAmount);
        
        // ASSERT
        assertEquals(-30.0, account.getBalance()); // 100 - 130 = -30
    }
    
    @Test
    void shouldRejectWithdrawalExceedingOverdraftLimit() {
        // Doit intercepter IllegalArgumentException
        account.setOverdraftLimit(50.0);
        double withdrawAmount = 252.0;


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(withdrawAmount) // <- lambda function
        );
        assertEquals("Insufficient funds", exception.getMessage());
    }
    
    @Test
    void shouldRejectWithdrawalOnInactiveAccount() {
        // Désactiver le compte puis tenter un retrait
        account.deactivate();

        // ACT & ASSERT
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> account.withdraw(50.0)
        );

        // Vérification optionnelle du message
        assertTrue(exception.getMessage().contains("not active") ||
                exception.getMessage().contains("inactive"));
    }
    
    @Test
    void shouldRejectNegativeWithdrawal() {
        // Tentative de retrait de -50.0
        double withdrawAmount = -50.0;


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.withdraw(withdrawAmount) // <- lambda function
        );
        assertEquals("Withdrawal amount must be positive", exception.getMessage());
    }
    
    // === TESTS DE TRANSFERT ===
    
    @Test
    void shouldTransferMoneyBetweenAccounts() {
        // ARRANGE
        BankAccount targetAccount = new BankAccount("ACC002", "Bob Martin", 0.0);
        double transferAmount = 30.0;
        double initialSourceBalance = account.getBalance();
        double initialTargetBalance = targetAccount.getBalance();
        
        // ACT
        account.transfer(targetAccount, transferAmount);
        
        // ASSERT
        assertEquals(initialSourceBalance - transferAmount, account.getBalance());
        assertEquals(initialTargetBalance + transferAmount, targetAccount.getBalance());
    }
    
    @Test
    void shouldRejectTransferWithInsufficientFunds() {
        // ARRANGE
        BankAccount targetAccount = new BankAccount("ACC002", "Bob Martin", 0.0);
        double transferAmount = 158.0;


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.transfer(targetAccount, transferAmount) // <- lambda function
        );
        assertEquals("Insufficient funds", exception.getMessage());
    }
    
    @Test
    void shouldRejectTransferToNullAccount() {
        // Tentative de transfert vers null
        double transferAmount = 58.0;


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.transfer(null, transferAmount) // <- lambda function
        );
        assertEquals("Target account cannot be null", exception.getMessage());
    }
    
    @Test
    void shouldRejectTransferToInactiveAccount() {
        // Créer un compte cible, le désactiver, tenter le transfert
        // ARRANGE
        BankAccount targetAccount = new BankAccount("ACC002", "Bob Martin", 0.0);
        double transferAmount = 58.0;
        targetAccount.deactivate();


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> account.transfer(targetAccount, transferAmount) // <- lambda function
        );
        assertTrue(exception.getMessage().contains("not active") ||
                exception.getMessage().contains("inactive"));    }
    
    @Test
    void shouldRejectTransferFromInactiveAccount() {

        // Désactiver le compte source, tenter le transfert
        BankAccount targetAccount = new BankAccount("ACC002", "Bob Martin", 0.0);
        double transferAmount = 58.0;
        account.deactivate();


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> account.transfer(targetAccount, transferAmount) // <- lambda function
        );
        assertTrue(exception.getMessage().contains("not active") ||
                exception.getMessage().contains("inactive"));
    }
    
    // === TESTS D'ACTIVATION/DÉSACTIVATION ===
    
    @Test
    void shouldDeactivateAccount() {
        // ARRANGE
        assertTrue(account.isActive());
        
        // ACT
        account.deactivate();
        
        // ASSERT
        assertFalse(account.isActive());
    }
    
    @Test
    void shouldActivateAccount() {
        // ARRANGE
        account.deactivate();
        assertFalse(account.isActive());
        
        // ACT
        account.activate();
        
        // ASSERT
        assertTrue(account.isActive());
    }
    
    // === TESTS LIMITE DE DÉCOUVERT ===
    
    @Test
    void shouldSetValidOverdraftLimit() {
        // ARRANGE
        double newLimit = 100.0;
        
        // ACT
        account.setOverdraftLimit(newLimit);
        
        // ASSERT
        assertEquals(newLimit, account.getOverdraftLimit());
    }
    
    @Test
    void shouldRejectNegativeOverdraftLimit() {
        double overdraftAmount = -20.0;


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> account.setOverdraftLimit(overdraftAmount) // <- lambda function
        );
        assertEquals("Overdraft limit cannot be negative", exception.getMessage());

    }
    
    // === TESTS DES MÉTHODES UTILITAIRES ===
    
    @Test
    void shouldCalculateAvailableBalanceCorrectly() {
        // ARRANGE
        account.setOverdraftLimit(50.0);
        
        // ACT & ASSERT
        assertEquals(150.0, account.getMaxAvailableBalance()); // 100 + 50
    }
    
    @Test
    void shouldReturnTrueWhenCanWithdraw() {
        // Vérifier que canWithdraw(50.0) retourne true pour un compte avec 100.0
        assertTrue(account.canWithdraw(50.0));


    }
    
    @Test
    void shouldReturnFalseWhenCannotWithdraw() {
        // Vérifier que canWithdraw(950.0) retourne false pour un compte avec 100.0 sans découvert
        assertFalse(account.canWithdraw(950.0));

    }
    
    @Test
    void shouldReturnFalseWhenCanWithdrawOnInactiveAccount() {
        // ARRANGE
        account.deactivate();
        
        // ACT & ASSERT
        assertFalse(account.canWithdraw(50.0));
    }
    
    // === TESTS DE SCÉNARIOS COMPLEXES ===
    
    @Test
    void shouldHandleMultipleOperationsCorrectly() {
        // ARRANGE
        assertEquals(100.0, account.getBalance());
        
        // ACT - Série d'opérations
        account.deposit(50.0);    // Solde : 150.0
        account.withdraw(30.0);   // Solde : 120.0
        account.deposit(20.0);    // Solde : 140.0
        
        // ASSERT
        assertEquals(140.0, account.getBalance());
        assertTrue(account.isActive());
    }
    
    @Test
    void shouldMaintainConsistencyAfterFailedOperation() {
        // ARRANGE
        double initialBalance = account.getBalance();
        
        // ACT - Tentative d'opération invalide
        try {
            account.withdraw(1000.0); // Doit échouer
        } catch (IllegalArgumentException e) {
            // Attendu
        }
        
        // ASSERT - L'état ne doit pas avoir changé
        assertEquals(initialBalance, account.getBalance());
        assertTrue(account.isActive());
    }
    @Test
    void shouldFormatAccount(){
        assertEquals("BankAccount{number='ACC001', holder='Alice Dupont', balance=100,00, active=true, overdraft=0,00}",account.toString());
    }
    @Test
    void shouldFormatAccountWithOverdraft(){
        account.setOverdraftLimit(50);
        assertEquals("BankAccount{number='ACC001', holder='Alice Dupont', balance=100,00, active=true, overdraft=50,00}",account.toString());
    }
}