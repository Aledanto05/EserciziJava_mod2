public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Mario", "Rossi", 19, "0000", 7);
        System.out.println(s1.toString());

        StudentWorker sw1 = new StudentWorker("Emma", "Rossi", 24, "00001", 6);

        System.out.println(sw1.toString());
        sw1.addBonusToAverage(8);
        System.out.println(sw1.toString());
    }
}