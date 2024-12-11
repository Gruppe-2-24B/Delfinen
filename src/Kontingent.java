import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Kontingent {

    private static class BetalingsRecord {
        int medlemsNr;
        boolean erBetalt;

        BetalingsRecord(int medlemsNr, boolean erBetalt) {
            this.medlemsNr = medlemsNr;
            this.erBetalt = erBetalt;
        }
    }

    private static ArrayList<BetalingsRecord> betalingsRecords = new ArrayList<>();

    private Medlem medlem;

    public Kontingent() {
    }

    public Medlem getMedlem() {
        return medlem;
    }

    public void setMedlem(Medlem medlem) {
        this.medlem = medlem;
    }

    public static int getPris(Medlem medlem) {
        int alder = medlem.getAlder();
        String medlemsStatus = medlem.getMedlemsStatus();

        if (medlemsStatus.equals("passiv")) {
            return 500; // Passiv medlemskab
        }
        if (alder < 18) {
            return 1000; // Junior medlemskab
        } else if (alder >= 60) {
            return (int) (1600 * 0.75); // Senior medlemskab med rabat
        } else {
            return 1600; // Standard medlemskab
        }
    }

    public void redigerRestanceStatus(boolean status) {
        if (medlem != null) {
            updateBetalingsRecord(status, medlem.getMedlemsNr());
        }
    }

    public boolean erIRestance() {
        if (medlem == null) {
            return true; // Ingen medlem betyder automatisk i restance
        }
        return !getErBetalt(medlem.getMedlemsNr());
    }

    public static boolean getErBetalt(int medlemsNr) {
        for (BetalingsRecord record : betalingsRecords) {
            if (record.medlemsNr == medlemsNr) {
                return record.erBetalt;
            }
        }
        return false; // Default til ikke betalt
    }

    private static void updateBetalingsRecord(boolean status, int medlemsNr) {
        boolean found = false;
        for (BetalingsRecord record : betalingsRecords) {
            if (record.medlemsNr == medlemsNr) {
                record.erBetalt = status;
                found = true;
                break;
            }
        }
        if (!found) {
            betalingsRecords.add(new BetalingsRecord(medlemsNr, status));
        }
    }

    public static void visRestanceListe() {
        ArrayList<Kontingent> restanceListe = new ArrayList<>();

        for (Medlem medlem : Medlem.getAlleMedlemmer()) {
            Kontingent kontingent = new Kontingent();
            kontingent.setMedlem(medlem);

            if (kontingent.erIRestance()) {
                restanceListe.add(kontingent);
            }
        }

        if (restanceListe.isEmpty()) {
            System.out.println("Der er ingen medlemmer i restance.");
        } else {
            int samletRestanceBeloeb = 0;
            System.out.println("Medlemmer i restance:");
            for (Kontingent kontingent : restanceListe) {
                Medlem valgtMedlem = kontingent.getMedlem();
                int kontingentPris = kontingent.getPris(valgtMedlem);
                samletRestanceBeloeb += kontingentPris;

                System.out.println("Navn: " + valgtMedlem.getNavn());
                System.out.println("Telefon-nr: " + valgtMedlem.getTlf());
                System.out.println("Kontingentpris: " + kontingentPris);
                System.out.println("-----");
            }
            System.out.println("\nSamlet kontingentpris for restance-medlemmer: " + samletRestanceBeloeb + " kr.");
        }
    }

    public static void redigerRestanceStatus() {
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast telefonnummer for medlemmet:");
        try {
            int telefonnummer = input.nextInt();
            input.nextLine();

            Medlem medlemTilRedigering = Medlem.findMedlemVedTelefonnummer(telefonnummer);
            if (medlemTilRedigering != null) {
                System.out.println("Indtast ny restance-status \nSkriv: (Ja for betalt eller Nej for ikke betalt)");

                String statusInput = input.nextLine().trim().toLowerCase();
                if (!statusInput.equals("ja") && !statusInput.equals("nej")) {
                    System.out.println("Ugyldig input. Indtast venligst kun 'true' eller 'false'.");
                    return;
                }

                boolean nyStatus = Boolean.parseBoolean(statusInput);


                updateBetalingsRecord(nyStatus, medlemTilRedigering.getMedlemsNr());
                List<Kontingent> kontingentListe = new ArrayList<>();
                for (Medlem medlem : Medlem.getAlleMedlemmer()) {
                    Kontingent nyKontingent = new Kontingent();
                    nyKontingent.setMedlem(medlem);
                    kontingentListe.add(nyKontingent);
                }
                PersistensWriter.restanceWriter(kontingentListe);

                System.out.println("Restance-status er blevet opdateret");
            } else {
                System.out.println("Medlem med telefonnummer " + telefonnummer + " blev ikke fundet");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ugyldigt input. Telefonnummer skal være et heltal.");
            input.nextLine();
        }
    }

    public static void beregnSumAfMedlemmer() {
        int samletBeloeb = 0;

        for (Medlem medlem : Medlem.getAlleMedlemmer()) {
            int medlemsPris = getPris(medlem);
            samletBeloeb += medlemsPris;
        }
        System.out.println("Summen af alle kontingentindbetalinger: " + samletBeloeb + " kr.");
    }

    public static void visAlleKontingenter() {
        for (Medlem medlem : Medlem.getAlleMedlemmer()) {
            int pris = getPris(medlem);
            System.out.println("Medlemsnummer: " + medlem.getMedlemsNr());
            System.out.println("Navn: " + medlem.getNavn());
            System.out.println("Kontingentpris: " + pris + " kr.");
            System.out.println("Restance-status: " + (getErBetalt(medlem.getMedlemsNr()) ? "Betalt" : "Ikke betalt"));
            System.out.println("-----");
        }
    }
    public static void clearBetalingsRecords() {
        betalingsRecords.clear();
    }
}
