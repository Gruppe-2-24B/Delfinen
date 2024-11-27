public class Traener extends Person {
    private String disciplinSpeciale;
    private String aldersGruppe;

    public Traener(String navn, int cprNr, int tlf, String mail, String disciplinSpeciale, String aldersGruppe) {
        super(navn, cprNr, tlf, mail);
        this.disciplinSpeciale = disciplinSpeciale;
        this.aldersGruppe = aldersGruppe;
    }
    public String getDisciplinSpeciale() {
        return disciplinSpeciale;
    }
    public String getAldersGruppe() {
        return aldersGruppe;
    }
}
