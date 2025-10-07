public class StudenteLavoratore extends Studente implements Lavoratore{

    public StudenteLavoratore(String nome, String cognome, int eta, String matricola, float media) {
        super(nome, cognome, eta, matricola, media);
    }

    @Override
    public void incrementoMediaPerOreLavorate(int oreLavoratePerSettimana) {
         media += oreLavoratePerSettimana * 0.10;
    }


}
