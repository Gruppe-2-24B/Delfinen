import java.util.ArrayList;

public class Traener extends Person {
    private String medlemsType;
    //private Disciplin tildeltDisciplin;
    private static ArrayList<Traener> traenere = new ArrayList<>();
    private CprNr cpr;

    public Traener(String navn, String cprNr, int tlf, String mail, String medlemsType) {
        super(navn, cprNr, tlf, mail);
        this.medlemsType = medlemsType;
        //this.tildeltDisciplin = tildeltDisciplin;
        this.cpr = new CprNr(cprNr);
        traenere.add(this);
    }

    public static Traener findTraenerVedTelefonnummer(int telefonnummer) {
        for (Traener traener : traenere) {
            if (traener.getTlf() == telefonnummer) {
                return traener;
            }
        }
        return null;
    }

    public int getTlf() {
        return tlf;
    }

    public String getAldersGruppe() {
        return medlemsType;
    }

    /*
    public Disciplin getTildeltDisciplin() {
        return tildeltDisciplin;
    }

     */

    public String getMedlemsType () {
        return medlemsType;
    }

    public static ArrayList<Traener> getAlleTraenere() {
        return new ArrayList<>(traenere);
    }

    @Override
    public String toString() {
        return "Træner #" + navn +
                "\nCpr: " + cpr.getCprNr() +
                "\nTlf: " + tlf +
                "\nMail: " + mail +
                "\nMedlemstype: " + medlemsType;
               // "\nDisciplin: " + tildeltDisciplin;
    }
}