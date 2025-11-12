package fr.bts.iris.slam;

/**
 * Classe Calculator pour les premiers tests avec JUnit.
 * 
 * Cette classe contient des méthodes mathématiques simples pour apprendre
 * les concepts de base des tests unitaires :
 * - Comportement prévisible
 * - Cas normaux et cas d'erreur
 * - Gestion des exceptions
 */
public class Calculator {
    
    /**
     * Addition de deux entiers
     */
    public int add(int a, int b) {
        return a+b;
    }
    
    /**
     * Soustraction de deux entiers
     */
    public int subtract(int a, int b) {
        return a-b;
    }
    
    /**
     * Multiplication de deux entiers
     */
    public int multiply(int a, int b) {
        return a*b;
    }
    
    /**
     * Division de deux nombres décimaux
     * @throws ArithmeticException si le diviseur est zéro
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a/b;
    }
    
    /**
     * Calcul de la factorielle d'un nombre
     * @throws IllegalArgumentException si n est négatif
     */
    public int factorial(int n) {
        if (n<0) {
            throw new IllegalArgumentException("Number can't be negative");
        }
        int res=1;

        for (int i = 1; i <=n; i++) {
            res*=i;
        }
        return res;
    }
    
    /**
     * Vérifie si un nombre est pair
     */
    public boolean isEven(int number) {
        return number%2 ==0;
    }
    
    /**
     * Calcule une puissance (exposant positif uniquement)
     * @throws IllegalArgumentException si l'exposant est négatif
     */
    public double power(double base, int exponent) {
        if (exponent<0) {
            throw new IllegalArgumentException("Exponent can't be negative");
        }
        double res =1;
        for (int i = 0; i <exponent ; i++) {
            res*=base;
        }
        return res;
    }
}