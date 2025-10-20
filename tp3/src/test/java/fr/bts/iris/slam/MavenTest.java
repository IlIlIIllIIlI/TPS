package fr.bts.iris.slam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MavenTest {

    @BeforeEach
    public void setUp() {
        this.calculator = new Calculator();
    }

    @Test
    public void shouldVerifyMavenAndJUnitWork() {
// Test simple pour vérifier que tout fonctionne
        String message = "Maven et JUnit fonctionnent !";
        assertEquals("Maven et JUnit fonctionnent !", message);
        assertTrue(true);
    }

    @Test
    public void shouldThrowExceptionWhenDividingByZero() {
        // ARRANGE : calculator déjà prêt via @BeforeEach
        // ACT & ASSERT : l'action et la vérification en une fois
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10, 0) // <- lambda function
        );
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }
}