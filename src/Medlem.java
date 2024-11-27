public class Medlem extends Person {

    private String aktivitetsForm;
    private boolean medlemsStatus;

    public Medlem(String navn, int cprNr, int tlf, String mail, String aktivitetsForm, boolean medlemsStatus) {
        super(navn, cprNr, tlf, mail);
        this.aktivitetsForm = aktivitetsForm;
        this.medlemsStatus = medlemsStatus;
    }
    public String getAktivitetsForm() {
        return aktivitetsForm;
    }
    public boolean getMedlemsStatus() {
        return true;
    }

}
