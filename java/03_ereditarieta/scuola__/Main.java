public class Main {
    public static void main(String[] args) {
        // Creating a Student object
        Student student = new Student("Marco", 20, 12345);

        // Accessing methods inherited from the parent class Person
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());

        // Accessing the method specific to the Student class
        System.out.println("Student ID: " + student.getStudentId());

        System.out.println();

        // Creating a Professor object
        Professor professor = new Professor("Dr. Smith", 45, "Computer Science");

        // Accessing methods inherited from the parent class Person
        System.out.println("Name: " + professor.getName());
        System.out.println("Age: " + professor.getAge());

        // Accessing the method specific to the Professor class
        System.out.println("Department: " + professor.getDepartment());
    }
}
