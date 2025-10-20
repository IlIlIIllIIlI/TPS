package fr.bts.iris.slam.school;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student student;
    private final String STUDENT_ID = "STU001";
    private final String FIRST_NAME = "Jean";
    private final String LAST_NAME = "Oui";
    private final String EMAIL = "jean_oui@mail.fr";
    private final int AGE = 16;


    /**
     * Prépare un compte valide avant chaque test
     */
    @BeforeEach
    void setUp() {
        student = new Student(STUDENT_ID, FIRST_NAME, LAST_NAME, EMAIL, AGE);
    }

    @Test
    void shouldRejectNullStudentId() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student(null, "Bob", "GAH", "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("Student ID cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectEmptyStudentId() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student(" ", "Bob", "GAH", "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("Student ID cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectStudentIdWithoutSTU() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->new Student("UTS001", "Bob", "GAH", "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("Student ID must match pattern STU### (e.g., STU001)", exception.getMessage());
    }

    @Test
    void shouldRejectWrongSizeStudentId() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU1111", "Bob", "GAH", "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("Student ID must match pattern STU### (e.g., STU001)", exception.getMessage());
    }

    @Test
    void shouldRejectStudentIdWithoutRightNumbers() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU21A", "Bob", "GAH", "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("Student ID must match pattern STU### (e.g., STU001)", exception.getMessage());
    }

    @Test
    void shouldRejectNullFirstName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", null, "GAH", "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("First name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectEmptyFirstName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", " ", "GAH", "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("First name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectShortFirstName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", "Z", "GAH", "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("First name must be at least 2 characters long", exception.getMessage());
    }

    @Test
    void shouldRejectNullLastName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", "Bob", null, "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("Last name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectEmptyLastName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", "Bob", " ", "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("Last name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectShortLastName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", "Bob", "Z", "po@kdak.com", 16)
                // <- lambda function
        );
        assertEquals("Last name must be at least 2 characters long", exception.getMessage());
    }

    @Test
    void shouldRejectNullEmail() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", "Bob", "GAAAH", null, 16)
                // <- lambda function
        );
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void shouldRejectEmailWithoutAt() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", "Bob", "GAAAH", "podak.com", 16)
                // <- lambda function
        );
        assertEquals("Invalid email format", exception.getMessage());

    }

    @Test
    void shouldRejectEmailWithoutDot() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", "Bob", "GAAAH", "po@dakcom", 16)
                // <- lambda function
        );
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void shouldRejectEmailWithDotBeforeAt() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", "Bob", "GAAAH", "po.ddak@com", 16)
                // <- lambda function
        );
        assertEquals("Invalid email format", exception.getMessage());
    }

    @Test
    void shouldRejectYoungAge() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", "Bob", "GAAAH", "po@dak.com", 15)
                // <- lambda function
        );
        assertEquals("Age must be between 16 and 65", exception.getMessage());
    }

    @Test
    void shouldRejectOldAge() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Student("STU001", "Bob", "GAAAH", "po@dak.com", 75)
                // <- lambda function
        );
        assertEquals("Age must be between 16 and 65", exception.getMessage());
    }

    @Test
    void shouldCreateStudentWithValidParameters() {
        // ARRANGE & ACT - Création d'un nouveau compte
        Student student = new Student("STU001", "Bob", "GAAAH", "po@dak.com", 63);

        // ASSERT - Vérification de l'état initial
        assertEquals("STU001", student.getStudentId());
        assertEquals("Bob", student.getFirstName());
        assertEquals("GAAAH", student.getLastName());
        assertEquals("po@dak.com",student.getEmail());
        assertEquals(63, student.getAge());
        assertEquals(0,student.getGradesCount());
    }

    @Test
    void shouldRejectNegativeGrade() {
        double grade = -20.0;

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> student.addGrade(grade) // <- lambda function
        );
        assertEquals("Grade must be between 0 and 20", exception.getMessage());

    }

    @Test
    void shouldRejectHighGrade() {
        double grade = 25.0;

        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> student.addGrade(grade) // <- lambda function
        );
        assertEquals("Grade must be between 0 and 20", exception.getMessage());

    }

    @Test
    void shouldAddGrade(){
        //Arrange

        double grade = 15.6;
        //ACT
        student.addGrade(grade);
        //ASSERT
        assertEquals(grade, student.getGrades().getFirst());
    }

    @Test
    void shouldRejectAverageWithEmptyGrades() {
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> student.getAverageGrade() // <- lambda function
        );
        assertEquals("Cannot calculate average: no grades available", exception.getMessage());
    }

    @Test
    void shouldAverageGrades(){
        //Arrange

        double grade = 15.6;
        double grade2 = 14.3;
        double avg = (grade+grade2)/2;
        //ACT
        student.addGrade(grade);
        student.addGrade(grade2);
        //ASSERT
        assertEquals(avg, student.getAverageGrade());
    }

    @Test
    void ShouldReturnFalseWhenGradeIsEmpty(){
        assertFalse(student.hasPassingGrade());
    }

    @Test
    void ShouldReturnFalseWhenGradesAreFailling(){
        //Arrange

        double grade = 5.6;
        double grade2 = 7.3;
        //ACT
        student.addGrade(grade);
        student.addGrade(grade2);
        assertFalse(student.hasPassingGrade());
    }

    @Test
    void ShouldReturnTrueWhenGradesArePassing(){
        //Arrange

        double grade = 15.6;
        double grade2 = 17.3;
        //ACT
        student.addGrade(grade);
        student.addGrade(grade2);
        assertTrue(student.hasPassingGrade());
    }

    @Test
    void ShouldReturnFullName(){
        assertEquals("Jean Oui", student.getFullName());
    }

    @Test
    void ShouldReturnGradeCount(){
        //Arrange

        double grade = 15.6;
        double grade2 = 17.3;
        //ACT
        student.addGrade(grade);
        student.addGrade(grade2);
        //ASSERT
        assertEquals(2,student.getGradesCount());
    }

    @Test
    void ShouldReturnBestGrade(){
        //Arrange

        double grade = 15.6;
        double grade2 = 17.3;
        //ACT
        student.addGrade(grade);
        student.addGrade(grade2);
        //ASSERT
        assertEquals(17.3,student.getBestGrade());
    }

    @Test
    void ShouldReturnZeroWhenBestGradeEmpty(){
        assertEquals(0.0,student.getBestGrade());
    }

    @Test
    void ShouldReturnWorstGrade(){
        //Arrange

        double grade = 15.6;
        double grade2 = 17.3;
        //ACT
        student.addGrade(grade);
        student.addGrade(grade2);
        //ASSERT
        assertEquals(15.6,student.getWorseGrade());
    }

    @Test
    void ShouldReturnZeroWhenWorseGradeEmpty(){
        assertEquals(0.0,student.getWorseGrade());
    }

}