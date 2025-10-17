package fr.bts.iris.slam;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe Calculator.
 * 
 * Ce fichier sert de template pour apprendre :
 * - Structure AAA (Arrange-Act-Assert)
 * - Nommage descriptif des tests
 * - Tests de cas normaux et d'exceptions
 * - Utilisation de @BeforeEach pour la préparation commune
 */
class CalculatorTest {
    
    private Calculator calculator;
    
    /**
     * Méthode exécutée AVANT chaque test
     * Évite la duplication de code de préparation
     */
    @BeforeEach
    public void setUp() {
        this.calculator = new Calculator();
    }
    
    // === TESTS D'ADDITION ===
    
    @Test
    public void shouldAddPositiveNumbers() {
        // ARRANGE - Préparer les données
        int a = 5;
        int b = 3;
        
        // ACT - Exécuter l'action à tester
        int result = this.calculator.add(a, b);
        
        // ASSERT - Vérifier le résultat
        assertEquals(8, result);
    }
    
    @Test
    public void shouldAddNegativeNumbers() {
        // Tester l'addition avec des nombres négatifs
        // Exemple : (-5) + (-3) = -8

        // ARRANGE - Préparer les données
        int a = -10;
        int b = -8;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.add(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(-18, result);

    }
    
    @Test
    public void shouldAddZero() {
        // Tester l'addition avec zéro
        // Exemple : 5 + 0 = 5
        // ARRANGE - Préparer les données
        int a = 7;
        int b = 0;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.add(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(7, result);
    }
    
    // === TESTS DE SOUSTRACTION ===
    
    @Test
    public void shouldSubtractNumbers() {
        // Exemple : 10 - 3 = 7
        // ARRANGE - Préparer les données
        int a = 6;
        int b = 4;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.subtract(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(2, result);
    }
    
    @Test
    public void shouldSubtractResultingInNegative() {
        // Exemple : 3 - 10 = -7
        // ARRANGE - Préparer les données
        int a = 4;
        int b = 6;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.subtract(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(-2, result);
    }
    
    // === TESTS DE MULTIPLICATION ===
    
    @Test
    public void shouldMultiplyNumbers() {
        // Exemple : 4 * 3 = 12
        // ARRANGE - Préparer les données
        int a = 7;
        int b = 5;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.multiply(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(35, result);
    }
    
    @Test
    public void shouldReturnZeroWhenMultiplyingByZero() {
        // Exemple : 5 * 0 = 0
        // ARRANGE - Préparer les données
        int a = 0;
        int b = 5;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.multiply(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(0, result);
    }
    
    @Test
    public void shouldMultiplyNegativeNumbers() {
        // Exemple : (-2) * (-3) = 6
        // ARRANGE - Préparer les données
        int a = -8;
        int b = -10;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.multiply(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(80, result);
    }
    
    // === TESTS DE DIVISION ===
    
    @Test
    public void shouldDivideNumbers() {
        // Exemple : 15.0 / 3.0 = 5.0
        // ARRANGE - Préparer les données
        double a = 8;
        double b = 2;

        // ACT - Exécuter l'action à tester
        double result = this.calculator.divide(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(4.0, result);
    }
    
    @Test
    public void shouldThrowExceptionWhenDividingByZero() {
        // La division par zéro doit lever une ArithmeticException.
        // Pour pouvoir tester des exceptions, il faut les catch !
        // ARRANGE - Préparer les données
        double a = 4;
        double b = 0;

        // ACT & ASSERT : l'action et la vérification en une fois
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> this.calculator.divide(a, b) // <- lambda function
        );
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
    
    // === TESTS FACTORIELLE ===
    
    @Test
    public void shouldCalculateFactorialOfPositiveNumber() {
        // Exemple : 5! = 120
        // ARRANGE - Préparer les données
        int n = 9;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.factorial(n);

        // ASSERT - Vérifier le résultat
        assertEquals(362880, result);
    }
    
    @Test
    public void shouldReturnOneForFactorialOfZero() {
        // Cas spécial : 0! = 1
        // ARRANGE - Préparer les données
        int n = 0;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.factorial(n);

        // ASSERT - Vérifier le résultat
        assertEquals(1, result);

    }
    
    @Test
    public void shouldReturnOneForFactorialOfOne() {
        // Cas spécial : 1! = 1
        int n = 1;

        // ACT - Exécuter l'action à tester
        int result = this.calculator.factorial(n);

        // ASSERT - Vérifier le résultat
        assertEquals(1, result);
    }
    
    @Test
    public void shouldThrowExceptionForNegativeFactorial() {
        // La factorielle d'un nombre négatif doit lever IllegalArgumentException
        // ARRANGE - Préparer les données
        int n = -4;

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> this.calculator.factorial(n) // <- lambda function
        );
        assertEquals("Number can't be negative", exception.getMessage());
    }
    
    // === TESTS NOMBRE PAIR ===
    
    @Test
    public void shouldReturnTrueForEvenNumbers() {
        // Tester plusieurs nombres pairs : 0, 2, 4, 100
        // ARRANGE - Préparer les données
        int n = 2;
        int n2=0;
        int n3 =4;
        int n4 = 100;


        // ACT & ASSERT : l'action et la vérification en une fois
        assertTrue(calculator.isEven(n));
        assertTrue(calculator.isEven(n2));
        assertTrue(calculator.isEven(n3));
        assertTrue(calculator.isEven(n4));

    }
    
    @Test
    public void shouldReturnFalseForOddNumbers() {
        // Tester plusieurs nombres impairs : 1, 3, 5, 99
        // ARRANGE - Préparer les données
        int n = 1;
        int n2=3;
        int n3 =5;
        int n4 = 99;


        // ACT & ASSERT : l'action et la vérification en une fois
        assertFalse(calculator.isEven(n));
        assertFalse(calculator.isEven(n2));
        assertFalse(calculator.isEven(n3));
        assertFalse(calculator.isEven(n4));
    }
    
    // === TESTS PUISSANCE ===
    
    @Test
    public void shouldCalculatePowerCorrectly() {
        // Exemple : 2^3 = 8.0
        // Attention à la précision (par exemple 0.001) des doubles !
        // assertEquals(8.0, calculator.power(2, 3), 0.001);
        // ARRANGE - Préparer les données
        double base = 8;
        int exponent = 2;

        // ACT - Exécuter l'action à tester
        double result = this.calculator.power(base,exponent);

        // ASSERT - Vérifier le résultat
        assertEquals(64.0, result, 0.001);
    }

    @Test
    public void shouldReturnOneForPowerZero() {
        // Tout nombre puissance 0 = 1
        // ARRANGE - Préparer les données
        double base = 8;
        int exponent = 0;

        // ACT - Exécuter l'action à tester
        double result = this.calculator.power(base,exponent);

        // ASSERT - Vérifier le résultat
        assertEquals(1.0, result, 0.001);
    }
    
    @Test
    public void shouldThrowExceptionForNegativeExponent() {
        // Exposant négatif doit lever IllegalArgumentException
        // ARRANGE - Préparer les données
        double n = 4;
        int exponent =-7;


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> this.calculator.power(n,exponent) // <- lambda function
        );
        assertEquals("Exponent can't be negative", exception.getMessage());
    }
    
    // === TESTS SUPPLÉMENTAIRES ===
    // Idées : cas limites, combinaisons de méthodes, valeurs extrêmes

    @Test
    public void shouldDivideFractalNumbers() {
        // Exemple : 15.0 / 3.0 = 5.0
        // ARRANGE - Préparer les données
        double a = this.calculator.divide(7, 3);
        double b = 2;

        // ACT - Exécuter l'action à tester
        double result = this.calculator.divide(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(1.1666, result, 0.001);
    }

    @Test
    public void shouldReturnOneForDivideWithZero() {
        // Cas spécial : 1! = 1
        // ARRANGE - Préparer les données
        double a = 0;
        double b = 8;

        // ACT - Exécuter l'action à tester
        double result = this.calculator.divide(a, b);

        // ASSERT - Vérifier le résultat
        assertEquals(0, result, 0.001);
    }



}