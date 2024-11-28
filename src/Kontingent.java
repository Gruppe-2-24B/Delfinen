import java.util.ArrayList;

public class Kontingent {
    private Medlem medlem;
    private ArrayList<Integer> prisListe;
    private Dato dato;

    public Kontingent(Medlem medlem) {
        this.medlem = medlem;
    }

    public int getPris() {
        int alder = medlem.getCprNr().getAlder();
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
        return !medlem.getMedlemsStatus();
    }
}