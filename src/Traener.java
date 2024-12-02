public class Traener extends Person {
    private String disciplinSpeciale;
    private String medlemsType;
    private Disciplin tildeltDisciplin;

    public Traener(String navn, int cprNr, int tlf, String mail, String disciplinSpeciale, String medlemsType) {
        super(navn, String.valueOf(cprNr), tlf, mail);
        this.disciplinSpeciale = disciplinSpeciale;
        this.medlemsType = medlemsType;
    }

    public Traener(String navn, int cprNr, int tlf, String mail, String disciplinSpeciale, String medlemsType, Disciplin disciplin) {
        this(navn, cprNr, tlf, mail, disciplinSpeciale, medlemsType);
        this.tildeltDisciplin = disciplin;
    }

    public String getDisciplinSpeciale() {
        return disciplinSpeciale;
    }

    public String getAldersGruppe() {
        return medlemsType;
    }

    public Disciplin getTildeltDisciplin() {
        return tildeltDisciplin;
    }
}