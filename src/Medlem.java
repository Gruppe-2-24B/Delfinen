import java.util.ArrayList;


public class Medlem extends Person {


    private static ArrayList<Medlem> medlemmer = new ArrayList<>();

    private String aktivitetsForm;
    private String medlemsStatus;
    private String medlemsType;
    private int medlemsNr;
    private int alder;
    private CprNr cpr;


    public Medlem(String navn, String cprNr, int tlf, String mail, String aktivitetsForm, String medlemsStatus) {
        super(navn, cprNr, tlf, mail);
        setAktivitetsForm(aktivitetsForm);
        setMedlemsStatus(medlemsStatus);
        this.cpr = new CprNr(cprNr);
        this.medlemsNr = getTlf();
        this.medlemsType = udregnMedlemsType();
        this.alder = cpr.getAlder();
        medlemmer.add(this);
    }


    public String getAktivitetsForm() {
        return aktivitetsForm;
    }

    public void setAktivitetsForm(String aktivitetsForm) { // Denne her funktion fungerer ligesom enum, man kan kun værem otionist eller konkurrencesvømmer
        aktivitetsForm = aktivitetsForm.trim().toLowerCase();
        if ("motionist".equals(aktivitetsForm) || "konkurrencesvømmer".equals(aktivitetsForm)) {
            this.aktivitetsForm = aktivitetsForm;
        } else {
            throw new IllegalArgumentException("Aktivitetsformen skal enten være 'motionist' eller 'konkurrencesvømmer'");
        }
    }


    public void setMedlemsStatus(String status) {
        status = status.trim().toLowerCase();
        if (status.equals("aktiv") || status.equals("passiv")) {
            this.medlemsStatus = status;
        } else {
            throw new IllegalArgumentException("Medlemsstatus skal være enten 'aktiv' eller 'passiv'");
        }
    }

    public String udregnMedlemsType() {
        if(cpr.getAlder() >= 18) {
            medlemsType = "Senior";
        } else {
            medlemsType = "Junior";
        }
        return medlemsType;
    }

    public boolean getMedlemsStatus() {
        return "aktiv".equalsIgnoreCase(medlemsStatus); // Jeg er i tvivl om der skal returnes true eller false.

    }

    public String getMedlemsType() {
        return medlemsType;
    }

    public int getAlder() {
        return alder;
    }

    public int getMedlemsNr() {
        return medlemsNr;
    }


    public Medlem findMedlemVedTelefonnummer(int telefonnummer) {


        for (Medlem medlem : medlemmer) {
            if (medlem.getTlf() == telefonnummer) {
                return medlem;
            }
        }
        return null;
    }

    public static ArrayList<Medlem> getAlleMedlemmer() {
        return new ArrayList<>(medlemmer);
    }

    @Override
    public String toString() {
        return "Medlem #" + medlemsNr + ": " + navn +
                "\nAlder: " + alder + " år." +
                "\nCpr: " + cpr.getCprNr() +
                "\nTlf: " + tlf +
                "\nMail: " + mail +
                "\nAktivitetsform: " + aktivitetsForm +
                "\nMedlemstype: " + medlemsType +
                "\nStatus: " + medlemsStatus;
     }

}
