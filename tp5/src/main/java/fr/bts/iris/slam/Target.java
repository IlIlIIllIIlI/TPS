package fr.bts.iris.slam;

public class Target {
    private int maxHealth;
    private int currentHealth;
    private boolean isStunned;
    private double stunDuration;
    
    public Target(int maxHealth) {
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("Max health must be positive");
        }
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.isStunned = false;
        this.stunDuration = 0.0;
    }
    
    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        currentHealth = Math.max(0, currentHealth - damage);
    }
    
    public void heal(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative");
        }
        currentHealth = Math.min(maxHealth, currentHealth + amount);
    }
    
    public void applyStun(double duration) {
        if (duration < 0) {
            throw new IllegalArgumentException("Stun duration cannot be negative");
        }
        this.isStunned = true;
        this.stunDuration = duration;
    }
    
    public int getCurrentHealth() {
        return currentHealth;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }
    
    public boolean isStunned() {
        return isStunned;
    }
    
    public double getStunDuration() {
        return stunDuration;
    }
}