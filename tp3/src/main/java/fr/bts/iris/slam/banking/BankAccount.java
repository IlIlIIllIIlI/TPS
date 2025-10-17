package fr.bts.iris.slam.banking;

/*
 Classe BankAccount - Exemple d'objet métier avec règles de gestion.
*/

public class BankAccount {
    
    protected String accountNumber;
    protected String holderName;
    protected double balance;
    protected boolean isActive;
    protected double overdraftLimit; // Découvert autorisé
    
    /**
     * Constructeur principal
     * @param accountNumber numéro de compte
     * @param holderName nom du titulaire
     * @param initialBalance solde initial
     * @throws IllegalArgumentException si les paramètres sont invalides
     */
    public BankAccount(String accountNumber, String holderName, double initialBalance) {
        // Se référer aux règles métiers pour déterminer les validations&throws à effectuer.
        // Lever IllegalArgumentException avec message explicite si invalide
        if (accountNumber == null||accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        if (holderName == null|| holderName.isEmpty()) {
            throw new IllegalArgumentException("Holder name cannot be null or empty");
        }
        if (initialBalance<0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        this.accountNumber=accountNumber;
        this.holderName=holderName;
        this.balance=initialBalance;
        this.isActive=true;
        this.overdraftLimit=0.0;

    }
    
    /**
     * Effectue un dépôt sur le compte
     * @param amount montant à déposer
     * @throws IllegalStateException si le compte n'est pas actif
     * @throws IllegalArgumentException si le montant est invalide
     */
    public void deposit(double amount) {
        // 1. Se référer aux règles métiers pour déterminer les validations&throws à effectuer.
        //    Lever les exceptions appropriées avec messages clairs
        // 2. Ajouter amount à balance
        if (amount<=0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        if (!this.isActive) {
            throw new IllegalStateException("Account is not active");
        }

        this.balance+=amount;
    }
    
    /**
     * Effectue un retrait du comptel
     * @param amount montant à retirer
     * @throws IllegalStateException si le compte n'est pas actif
     * @throws IllegalArgumentException si le montant est invalide ou insuffisant
     */
    public void withdraw(double amount) {
        // 1. Se référer aux règles métiers pour déterminer les validations&throws à effectuer.
        //    Lever les exceptions appropriées avec messages clairs
        // 2. Déduire amount du balance
        if (amount<=0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (!this.isActive) {
            throw new IllegalStateException("Account is not active");
        }

        if (!this.canWithdraw(amount)) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        this.balance-=amount;
    }
    
    /**
     * Effectue un transfert vers un autre compte
     * @param targetAccount compte de destination
     * @param amount montant à transférer
     * @throws IllegalArgumentException si les paramètres sont invalides
     * @throws IllegalStateException si l'opération ne peut être effectuée
     */
    public void transfer(BankAccount targetAccount, double amount) {
        // 1. Se référer aux règles métiers pour déterminer les validations&throws à effectuer.
        //    Lever les exceptions appropriées avec messages clairs
        // 2. Utiliser withdraw() pour retirer de ce compte
        // 3. Utiliser targetAccount.deposit() pour déposer
        // Note : si withdraw() échoue, le transfert échoue (cohérence)
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }
        double[] backups = {this.balance, targetAccount.balance};
        try {
            this.withdraw(amount);
            targetAccount.deposit(amount);
        } catch (Exception e) {
            this.balance = backups[0];
            targetAccount.setBalance(backups[1]);
            throw e;
        }
    }
    
    /**
     * Désactive le compte (aucune opération possible)
     */
    public void deactivate() {
        this.isActive=false;
    }
    
    /**
     * Réactive le compte
     */
    public void activate() {
        this.isActive=true;
    }
    
    /**
     * Définit la limite de découvert autorisé
     * @param limit montant du découvert autorisé
     * @throws IllegalArgumentException si la limite est négative
     */
    public void setOverdraftLimit(double limit) {
        // 1. Se référer aux règles métiers pour déterminer les validations&throws à effectuer.
        //    Lever les exceptions appropriées avec messages clairs
        // 2. Modifier la limite de découvert
        if (limit<=0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }

        this.overdraftLimit=limit;
    }
    
    /**
     * Vérifie si le compte peut effectuer un retrait d'un montant donné
     * @param amount montant à vérifier
     * @return true si le retrait est possible
     */
    public boolean canWithdraw(double amount) {
        return amount<=this.getMaxAvailableBalance() && this.isActive&&amount>0;
    }
    
    // === GETTERS ===
    
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public String getHolderName() {
        return this.holderName;
    }
    
    public double getBalance() {
        return this.balance;
    }
    
    public boolean isActive() {
        return this.isActive;
    }
    
    public double getOverdraftLimit() {
        return this.overdraftLimit;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Retourne le solde disponible (solde + découvert)
     */
    public double getMaxAvailableBalance() {
        return this.balance+this.overdraftLimit;
    }
    
    @Override
    public String toString() {
        return String.format("BankAccount{number='%s', holder='%s', balance=%.2f, active=%s, overdraft=%.2f}", 
                           this.accountNumber, this.holderName, this.balance, this.isActive, this.overdraftLimit);
    }
}