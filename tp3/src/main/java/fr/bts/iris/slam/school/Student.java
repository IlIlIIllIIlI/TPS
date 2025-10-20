package fr.bts.iris.slam.school;

import java.util.ArrayList;

public class Student {
    protected String studentId;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected int age;
    protected ArrayList<Double> grades;

    Student(String studentId, String firstName, String lastName, String email, int age){
        if (studentId == null ||studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        if (!studentId.startsWith("STU")||studentId.length()!=6) {
            throw new IllegalArgumentException("Student ID must match pattern STU### (e.g., STU001)");
        }
        try {
            Integer i = Integer.parseInt(studentId.substring(3));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Student ID must match pattern STU### (e.g., STU001)");
        }

        if (firstName == null||firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }

        if (firstName.trim().length() <2) {
            throw new IllegalArgumentException("First name must be at least 2 characters long");
        }

        if (lastName == null||lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        if (lastName.trim().length() <2) {
            throw new IllegalArgumentException("Last name must be at least 2 characters long");
        }

        if (email == null) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (!email.contains(".")||email.indexOf(".")<email.indexOf("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (age<16||age>65) {
            throw new IllegalArgumentException("Age must be between 16 and 65");
        }

        this.age=age;
        this.studentId=studentId;
        this.firstName=firstName.trim();
        this.lastName=lastName.trim();
        this.email=email.trim();
        this.grades=new ArrayList<>();
    }

    public void addGrade(Double grade){
        if (grade == null||grade<0||grade>20) {
            throw new IllegalArgumentException("Grade must be between 0 and 20");
        }
        this.grades.add(grade);
    }

    public double getAverageGrade(){
        if (this.grades.isEmpty()) {
            throw new IllegalStateException("Cannot calculate average: no grades available");
        }
        double avg = 0.0;

        for (double grade : this.grades) {
            avg+=grade;
        }

        return avg/grades.size();
    }

    public boolean hasPassingGrade(){
        return !this.grades.isEmpty() && this.getAverageGrade()>=10;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    public Double getBestGrade(){
        if (this.grades.isEmpty()) {
            return 0.0;
        }
        Double max = 0.0;
        for (Double grade : grades){
            if (grade>max){
                max = grade;
            }
        }

        return max;
    }

    public Double getWorseGrade(){
        if (this.grades.isEmpty()) {
            return 0.0;
        }
        Double min = 20.0;
        for (Double grade : grades){
            if (grade<min){
                min = grade;
            }
        }

        return min;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public String getEmail() {
        return this.email;
    }

    public int getAge() {
        return this.age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getGradesCount() {
        return this.grades.size();
    }



    public ArrayList<Double> getGrades() {
        return grades;
    }
}
