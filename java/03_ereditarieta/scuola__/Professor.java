// Derived class (subclass)
class Professor extends Person {
    private String department;

    // Constructor
    public Professor(String name, int age, String department) {
        super(name, age); // Call the constructor of the parent class
        this.department = department;
    }

    // Method to get the department of the professor
    public String getDepartment() {
        return department;
    }
}
