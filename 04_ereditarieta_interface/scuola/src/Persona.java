public class Persona {
    private String nome;
    private String cognome;
    private int eta;

    public Persona(String nome, String cognome, int eta) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
    }

    public String getNome() {
        return nome;
    }

    public Persona setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCognome() {
        return cognome;
    }

    public Persona setCognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public int getEta() {
        return eta;
    }

    public Persona setEta(int eta) {
        this.eta = eta;
        return this;
    }

    @Override
    public String toString() {
        return "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", eta=" + eta +
                '}';
    }
}
