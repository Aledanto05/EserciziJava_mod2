public class StudentWorker extends Student implements Worker{
    public StudentWorker(String name, String surname, int age, String id, int average) {
        super(name, surname, age, id, average);
    }

    @Override
    public String toString() {
        return "StudentWorker{" +
                "id='" + id + '\'' +
                ", average=" + average +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void addBonusToAverage(int hoursWorkerPerWeek) {
        this.average += hoursWorkerPerWeek *0.1;
    }
}
