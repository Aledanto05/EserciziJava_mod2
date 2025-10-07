public class Student extends Person{
    String id;
    float average;

    public Student(String name, String surname, int age, String id, float average) {
        super(name, surname, age);
        this.id = id;
        this.average = average;
    }

    public String getId() {
        return id;
    }

    public Student setId(String id) {
        this.id = id;
        return this;
    }

    public float getAverage() {
        return average;
    }

    public Student setAverage(float average) {
        this.average = average;
        return this;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", average=" + average +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
