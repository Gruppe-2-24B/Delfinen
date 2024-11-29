import java.util.Scanner;

public class MedlemsGenerator {

    private final Scanner scanner;

    public MedlemsGenerator() {

        scanner = new Scanner(System.in);
    }

    public void medlemsGenerator() {
        System.out.println("=== Opret ny Medlem ===");

        System.out.println("Indtast navn: ");
        String navn = scanner.nextLine();

        boolean validCpr;
        String cprNr;
        do {
            System.out.println("Indtast CPR Nummer (10 cifre)");
            cprNr = scanner.nextLine();
            CprNr cpr = new CprNr(cprNr);
            validCpr = cpr.erValid();
            if (!validCpr) {
                System.out.println("Ugyldigt CPR nummer. Prøv igen");
            }
        } while (!validCpr);

        System.out.println("indtast telefonummer");
        int tlf = Integer.parseInt(scanner.nextLine());

        System.out.println("Indtast mail");
        String mail = scanner.nextLine();

        System.out.println("Vælg aktivitetstype");
        System.out.println("1. Motionist");
        System.out.println("2. Konkurrencesvømmer");
        String aktivitetsForm;
        while (true) {
            System.out.println("Indtast valg (1 eller 2): ");
            String valg = scanner.nextLine();
            if (valg.equals("1")) {
                aktivitetsForm = "motionist";
                break;
            } else if (valg.equals("2")) {
                aktivitetsForm = "konkurrence";
                break;
            }
            System.out.println("Ugyldigt valg. Prøv igen");
        }

        System.out.println("Vælg medlemsstatus");
        System.out.println("1. Aktiv");
        System.out.println("2. Passiv");
        String medlemsStatus;
        while (true) {
            System.out.println("Indtast valg (1 eller 2): ");
            String valg = scanner.nextLine();
            if (valg.equals("1")) {
                medlemsStatus = "Aktiv";
                break;
            } else if (valg.equals("2")) {
                medlemsStatus = "Passiv";
                break;
            }
            System.out.println("Ugyldigt valg. Prøv igen");
        }


        Medlem nytMedlem = new Medlem(navn, cprNr, tlf, mail, aktivitetsForm, medlemsStatus);
        System.out.println("\nNyt medlem oprettet med medlemsnummer: " + nytMedlem.getMedlemsNr());
        System.out.println(nytMedlem);
    }
}