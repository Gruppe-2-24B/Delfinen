public class Traener extends Person {
    private String disciplinSpeciale;
    private String medlemsType;

    public Traener(String navn, int cprNr, int tlf, String mail, String disciplinSpeciale, String medlemsType) {
        super(navn, String.valueOf(cprNr), tlf, mail);
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