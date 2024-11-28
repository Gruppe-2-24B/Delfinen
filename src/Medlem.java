public class Medlem extends Person {

    private String aktivitetsForm;
    private boolean medlemsStatus;

    public Medlem(String navn, int cprNr, int tlf, String mail, String aktivitetsForm) {
        super(navn, cprNr, tlf, mail);
        this.aktivitetsForm = aktivitetsForm;
        this.medlemsStatus = medlemsStatus;
        this.cprNr = cprNr;
    }
    public String getAktivitetsForm() {
        return aktivitetsForm;
    }
    public boolean getMedlemsStatus() {
        return true; // Jeg er i tvivl om der skal returnes true eller false.
    }

}
