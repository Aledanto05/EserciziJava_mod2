// Derived class (subclass)
class Student extends Person {
    private int studentId;

    // Constructor
    public Student(String name, int age, int studentId) {
        super(name, age); // Call the constructor of the parent class
        this.studentId = studentId;
    }

    // Method to get the student ID
    public int getStudentId() {
        return studentId;
    }
}
