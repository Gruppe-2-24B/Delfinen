import java.util.ArrayList;

public class Kontingent {
    private Medlem medlem;
    private int pris;
    private ArrayList<Integer> prisListe;
    private Dato dato;
    private Restance restance;


    public Kontingent() {}

    public Kontingent(Medlem medlem) {

        this.medlem = medlem;
        this.restance = new Restance();

    }
    public Medlem getMedlem() {
        return medlem;
    }

    public int getPris() {
        int alder = medlem.getAlder();
        String medlemsStatus = medlem.getMedlemsStatus();

        //passive medlemmer
        if (medlemsStatus.equals("passiv")) {
            return 500; //årlig takst som passiv medlem
        }
        //aktive medlemmer
        if (alder < 18) {
            return 1000; //årlig takst for juniormedlemmer
        } else if (alder >= 60) {
            return (int) (1600 * 0.75); //årlig takst for seniorer over 60 år med 25% rabat
        } else {
            return 1600; //almindeligt seniormedlemskab
        }
    }

    public void udskrivMedlemsInfo() {
        System.out.println("Medlemsnummer: " + medlem.getMedlemsNr());
        System.out.println("CPR-nr: " + medlem.getCprNr());
        System.out.println("Telefon-nr:" + medlem.getTlf());
        System.out.println("Mail-adresse: " + medlem.getMail());
        System.out.println("Navn: " + medlem.getNavn());
        System.out.println("Alder:" + medlem.getAlder() + " år");
        System.out.println("Medlemsstatus:" + medlem.getMedlemsStatus());
        System.out.println("Medlemstype: " + medlem.getMedlemsType());
        System.out.println("Aktivitetsform: " + medlem.getAktivitetsForm());
        System.out.println("Kontingentpris: " + getPris());
        if (restance.erIRestance()) {
            System.out.println("Er i restance: Ja");
        } else {
            System.out.println("Er i restance: Nej");
        }
    }

    public void visKontingentListe() {
            udskrivMedlemsInfo();
    }

    public static ArrayList<Kontingent> genererRestanceListe(ArrayList<Kontingent> kontingentListe) {
        ArrayList<Kontingent> restanceListe = new ArrayList<>();
        for (Kontingent kontingent : kontingentListe) {
            if (kontingent.erIRestance()) {
                restanceListe.add(kontingent);
            }
        }
        return restanceListe;
    }

    public static void visRestanceListe(ArrayList<Kontingent> restanceListe) {
        if (restanceListe.isEmpty()) {
            System.out.println("Der er ingen medlemmer i restance.");
        } else {
            System.out.println("Medlemmer i restance:");
            for (Kontingent kontingent : restanceListe) {
                kontingent.udskrivMedlemsInfo();
            }
        }
    }

    public static int beregnSum(ArrayList<Kontingent> kontingentListe) {
        int samletIndbetaling = 0;

        for (Kontingent kontingent : kontingentListe) {
            if (!kontingent.erIRestance()) {
                samletIndbetaling += kontingent.getPris();
            }
        }
        return samletIndbetaling;
    }

    public static void visSum(ArrayList<Kontingent> kontingentListe) {
        int samletBeloeb = beregnSum(kontingentListe);
        System.out.println("Samlede kontingentindbetalinger: " + samletBeloeb + " kr.");
    }

    public boolean erIRestance() {

        if (medlem.getMedlemsStatus().equals("passiv")) {
            return false; //ikke i restance
        } else {
            return true; //i restance
        }
    }

    public void redigerRestanceStatus(boolean status) {
        restance.redigerRestanceStatus(status);
    }
} //klasse slut

