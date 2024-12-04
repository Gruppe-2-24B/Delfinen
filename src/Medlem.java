import java.util.ArrayList;


public class Medlem extends Person {


    private static ArrayList<Medlem> medlemmer = new ArrayList<>();

    private String aktivitetsForm;
    private String medlemsStatus;
    private String medlemsType;
    private int medlemsNr;
    private int alder;
    private CprNr cpr;
    private String disciplinNavn;


    public Medlem(String navn, String cpr, int tlf, String mail, String aktivitetsForm, String medlemsStatus, String medlemsType, String disciplin) {
    }

    public String getDisciplinNavn() {
        return disciplinNavn;
    }

    public void setDisciplinNavn(String disciplinNavn) {
        this.disciplinNavn = disciplinNavn;
    }

    public Medlem() {}

    public Medlem(String navn, String cprNr, int tlf, String mail, String aktivitetsForm, String medlemsStatus, String disciplinNavn) {
        super(navn, cprNr, tlf, mail);
        setAktivitetsForm(aktivitetsForm);
        setMedlemsStatus(medlemsStatus);
        this.cpr = new CprNr(cprNr);
        this.medlemsNr = getTlf();
        this.medlemsType = udregnMedlemsType();
        this.alder = cpr.getAlder();
        this.disciplinNavn = disciplinNavn;
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

    public String getMedlemsStatus() {
        return medlemsStatus; // Jeg er i tvivl om der skal returnes true eller false.
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
                "\nStatus: " + medlemsStatus+
                "\nDisciplin: " + disciplinNavn;
     }

}