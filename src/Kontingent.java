import java.util.ArrayList;

public class Kontingent {
    private Medlem medlem;
    private int pris;
    private ArrayList<Integer> prisListe;
    private Dato dato;
    private Restance restance;


    public Kontingent(Medlem medlem) {
        this.medlem = medlem;
        this.restance = new Restance();
    }
    public Medlem getMedlem() {
        return medlem;
    }

    public int getPris() {
        int alder = medlem.getAlder();
        String aktivitetsForm = medlem.getAktivitetsForm();

        //passive medlemmer
        if (aktivitetsForm.equals("passiv")) {
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


    public boolean erIRestance() {

        if (medlem.getMedlemsStatus().equals("passiv")) {
            return false; //ikke i restance
        } else {
            return true; //i restance
        }
    }

    public void visKontingentListe() {
            System.out.println("Medlemsnummer: " + medlem.getMedlemsNr());
            System.out.println("Navn: " + medlem.getNavn());
            System.out.println("Alder:" + medlem.getAlder() + " år");
            System.out.println("Type: " + medlem.getMedlemsType());
            System.out.println("Aktivitetsform: " + medlem.getAktivitetsForm());
            System.out.println("Kontingentpris: " + getPris());
            System.out.println("Er i restance: " + (restance.erIRestance() ? "Ja" : "Nej"));

    }

    public void redigerRestanceStatus(boolean status) {
        restance.redigerRestanceStatus(status);
    }
}

