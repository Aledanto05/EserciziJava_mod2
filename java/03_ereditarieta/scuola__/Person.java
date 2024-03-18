// Parent class
class Person {
    protected String name;
    protected int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Method to get the name of the person
    public String getName() {
        return name;
    }

    // Method to get the age of the person
    public int getAge() {
        return age;
    }
}
