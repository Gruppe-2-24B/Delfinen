import java.util.ArrayList;

public class Traener extends Person {
    private String medlemsType;
    private Disciplin tildeltDisciplin;
    private static ArrayList<Traener> traenere = new ArrayList<>();

    public Traener(String navn, int cprNr, int tlf, String mail, String medlemsType, Disciplin tildeltDisciplin) {
        super(navn, String.valueOf(cprNr), tlf, mail);
        this.medlemsType = medlemsType;
        this.tildeltDisciplin = tildeltDisciplin;
        traenere.add(this);
    }

    public String getAldersGruppe() {
        return medlemsType;
    }

    public Disciplin getTildeltDisciplin() {
        return tildeltDisciplin;
    }

    public String getMedlemsType () {
        return medlemsType;
    }

    public static ArrayList<Traener> getAlleTraenere() {
        return new ArrayList<>(traenere);
    }
}