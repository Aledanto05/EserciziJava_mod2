public class Main {
    public static void main(String[] args) {
        StudenteLavoratore studenteLavoratore1 = new StudenteLavoratore("Ugo", "Tale", 20, "00000", 6);
        System.out.println( studenteLavoratore1.toString());
        studenteLavoratore1.incrementoMediaPerOreLavorate(10);
        System.out.println( studenteLavoratore1.toString());

    }
}