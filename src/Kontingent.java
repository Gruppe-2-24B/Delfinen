import java.util.ArrayList;
import java.util.*;

public class Kontingent {
    private Medlem medlem;
    private Restance restance;

    public Kontingent() {
        this.restance = new Restance(); // Fjernet tom konstruktør
    }

    public Medlem getMedlem() {
        return medlem;
    }

    public static int getPris(Medlem medlem) { // Sat getPris til Medlem medlem.
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

    public void setMedlem(Medlem medlem) {
        this.medlem = medlem;
    }

    public void udskrivMedlemsInfo() {

        //Tilføjet en scanner, så vi kan indtaste et telefonnummer, og få specifik medlem vi gerne vil have info på.
        // Ligesom Sidi har gjort. Før henviste vi til medlem, men den er jo tom i klassen medlem.
        // Nu får vi info på specifik medlem, efter vi har laestmedlemmer fra persistens og lagt det i en liste.
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nIndtast telefonnummer for at vælge et medlem:");

        int telefonnummer = scanner.nextInt();
        scanner.nextLine();

        Medlem valgtMedlem =  Medlem.findMedlemVedTelefonnummer(telefonnummer);

        if (valgtMedlem == null)
        {
            System.out.println("Intet medlem fundet med det medlemsnummer.");
            return;
        }

        setMedlem(valgtMedlem); // Her sætter vi objektet medlem til valgtMedlem.

        System.out.println("Medlemsnummer: " + valgtMedlem.getMedlemsNr());
        System.out.println("CPR-nr: " + valgtMedlem.getCprNr());
        System.out.println("Telefon-nr:" + valgtMedlem.getTlf());
        System.out.println("Mail-adresse: " + valgtMedlem.getMail());
        System.out.println("Navn: " + valgtMedlem.getNavn());
        System.out.println("Alder:" + valgtMedlem.getAlder() + " år");
        System.out.println("Medlemsstatus: " + valgtMedlem.getMedlemsStatus());
        System.out.println("Medlemstype: " + valgtMedlem.getMedlemsType());
        System.out.println("Aktivitetsform: " + valgtMedlem.getAktivitetsForm());
        System.out.println("Disciplin: " + valgtMedlem.getDisciplinNavn());
        System.out.println("Kontingentpris: " + getPris(valgtMedlem));
        if (restance.erIRestance()) {
            System.out.println("Restance-status: Ikke betalt");
        } else {
            System.out.println("Restance-status: Betalt");
        }
    }

    public void visKontingentListe() {
            udskrivMedlemsInfo();
    }

    public static ArrayList<Kontingent> genererRestanceListe(ArrayList<Kontingent> kontingentListe) {

        if (kontingentListe.isEmpty()) {
            for (Medlem medlem : Medlem.getAlleMedlemmer()) {
                Kontingent kontingent = new Kontingent();
                kontingent.setMedlem(medlem);
                kontingentListe.add(kontingent);
            }
        }

        ArrayList<Kontingent> restanceListe = new ArrayList<>();
        for (Kontingent kontingent : kontingentListe) {
            if (kontingent.erIRestance()) {
                restanceListe.add(kontingent);
            }
        }
        return restanceListe;
    }

    public static void visRestanceListe() {
        ArrayList<Kontingent> restanceListe = new ArrayList<>();

        // Iterate through all members and create a Kontingent object for those in restance
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
                int kontingentPris = Kontingent.getPris(kontingent.getMedlem());
                System.out.println("Navn: " + kontingent.getMedlem().getNavn() +
                        ", Telefonnummer: " + kontingent.getMedlem().getTlf() +
                        ", Kontingentpris: " + kontingentPris + " kr.");
                samletRestanceBeloeb += kontingentPris;
            }
            System.out.println("\nSamlet kontingentpris for restance-medlemmer: " + samletRestanceBeloeb + " kr.");
        }
    }

    public static int beregnSum(ArrayList<Kontingent> kontingentListe) {
        int samletIndbetaling = 0;

        for (Kontingent kontingent : kontingentListe) {
            if (!kontingent.erIRestance()) {
                samletIndbetaling += kontingent.getPris(kontingent.getMedlem());
            }
        }
        return samletIndbetaling;
    }

    public void redigerRestanceStatus(Kontingent kontingent) {
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast telefonnummer for medlemmet:");
        try {
            int telefonnummer = input.nextInt();
            input.nextLine();

            Medlem medlemTilRedigering = Medlem.findMedlemVedTelefonnummer(telefonnummer);
            if (medlemTilRedigering != null) {
                System.out.println("Indtast ny restance-status \nSkriv: (true for betalt eller false for ikke betalt)");

                // Læs input som streng og valider
                String statusInput = input.nextLine().trim().toLowerCase();
                if (!statusInput.equals("true") && !statusInput.equals("false")) {
                    System.out.println("Ugyldig input. Indtast venligst kun 'true' eller 'false'.");
                    return; // Afbryd metoden, hvis input er ugyldigt
                }

                boolean nyStatus = Boolean.parseBoolean(statusInput);
                kontingent.setMedlem(medlemTilRedigering);
                kontingent.redigerRestanceStatus(nyStatus);
                System.out.println("Restance-status er blevet opdateret");
            } else {
                System.out.println("Medlem med telefonnummer " + telefonnummer + " blev ikke fundet");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ugyldigt input. Telefonnummer skal være et heltal.");
            input.nextLine();
        }
    }


    public static void visBetalingsStatusForMedlem(Kontingent kontingent) {
        Scanner input = new Scanner(System.in);
        System.out.println("Indtast telefonnummer for medlemmet:");
        int telefonnummer = input.nextInt();
        input.nextLine();

        Medlem medlemTilTjek = Medlem.findMedlemVedTelefonnummer(telefonnummer);
        if (medlemTilTjek != null) {
            kontingent.setMedlem(medlemTilTjek);
            System.out.println("Betalingsstatus for " + medlemTilTjek.getNavn() + ":");
            if (kontingent.erIRestance()) {
                System.out.println("Medlemmet er i restance");
            } else {
                System.out.println("Medlemmet har betalt");
            }
        } else {
            System.out.println("Medlem med telefonnummer " + telefonnummer + " blev ikke fundet");
        }
    }

    public static void beregnSumAfMedlemmer(){

        int samletBeloeb = 0;

        for (Medlem medlem : Medlem.getAlleMedlemmer()) {

            int medlemsPris = getPris(medlem);
            samletBeloeb += medlemsPris;
        }
        System.out.println("Summen af alle kontingentindbetalinger: " + samletBeloeb + " kr.");
    }

    public void visSum(ArrayList<Kontingent> kontingentListe) {
        int samletBeloeb = beregnSum(kontingentListe);
        System.out.println("Samlede kontingentindbetalinger: " + samletBeloeb + " kr.");
    }

    public boolean erIRestance() {
        if (medlem == null) {
            return true;
        }
        return !restance.getErBetalt(medlem.getMedlemsNr());
    }

    public void redigerRestanceStatus(boolean status) {
        if (medlem != null) {
            restance.redigerRestanceStatus(status, medlem.getMedlemsNr());
        }
    }
} //klasse slut

