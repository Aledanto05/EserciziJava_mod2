public class Studente extends Persona{
    private String matricola;
    protected float media;

    public Studente(String nome, String cognome, int eta, String matricola, float media) {
        super(nome, cognome, eta);
        this.matricola = matricola;
        this.media = media;
    }

    public String getMatricola() {
        return matricola;
    }

    public Studente setMatricola(String matricola) {
        this.matricola = matricola;
        return this;
    }

    public float getMedia() {
        return media;
    }

    public Studente setMedia(float media) {
        this.media = media;
        return this;
    }

    @Override
    public String toString() {
        return "Studente{" +
                super.toString() +
                "matricola='" + matricola + '\'' +
                ", media=" + media +
                '}';
    }
}
