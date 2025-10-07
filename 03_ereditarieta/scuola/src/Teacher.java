public class Teacher extends Person{
    String subject;
    int salary;

    public Teacher(String name, String surname, int age, String subject, int salary) {
        super(name, surname, age);
        this.subject = subject;
        this.salary = salary;
    }

    public String getSubject() {
        return subject;
    }

    public Teacher setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public int getSalary() {
        return salary;
    }

    public Teacher setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "subject='" + subject + '\'' +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
