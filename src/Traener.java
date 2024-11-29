public class Traener extends Person {
    private String disciplinSpeciale;
    private String medlemsType;


    public Traener(String navn, String cprNr, int tlf, String mail, String disciplinSpeciale, String aldersGruppe) {

        super(navn, cprNr, tlf, mail);
        this.disciplinSpeciale = disciplinSpeciale;
        this.medlemsType = medlemsType;
    }
    public String getDisciplinSpeciale() {
        return disciplinSpeciale;
    }
    public String getAldersGruppe() {
        return medlemsType;
    }
}