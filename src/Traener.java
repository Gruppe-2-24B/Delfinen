public class Traener extends Person {
    private String medlemsType;
    private Disciplin tildeltDisciplin;

    public Traener(String navn, int cprNr, int tlf, String mail, String medlemsType, Disciplin tildeltDisciplin) {
        super(navn, String.valueOf(cprNr), tlf, mail);
        this.medlemsType = medlemsType;
        this.tildeltDisciplin = tildeltDisciplin;
    }

    public String getAldersGruppe() {
        return medlemsType;
    }

    public Disciplin getTildeltDisciplin() {
        return tildeltDisciplin;
    }
}