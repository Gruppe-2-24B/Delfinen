import java.util.ArrayList;
import java.util.Scanner;

public class TraenerGenerator {

    private final Scanner scanner;

    public TraenerGenerator() {
        scanner = new Scanner(System.in);
    }

    public void traenerGenerator() {
        System.out.println("=== Opret ny Træner ===");

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

        System.out.println("Indtast telefonnummer");
        int tlf = Integer.parseInt(scanner.nextLine());

        System.out.println("Indtast mail");
        String mail = scanner.nextLine();


        System.out.println("Vælg Alders gruppe:");
        System.out.println("1. Junior");
        System.out.println("2. Senior");
        String medlemsType;
        while (true) {
            System.out.println("Indtast valg (1 eller 2): ");
            String valg = scanner.nextLine();
            if (valg.equals("1")) {
                medlemsType = "Junior";
                break;
            } else if (valg.equals("2")) {
                medlemsType = "Senior";
                break;
            }
            System.out.println("Ugyldigt valg. Prøv igen");
        }

        System.out.println("Vælg disciplin:");
        Disciplin[] standardDiscipliner = Disciplin.getStandardDiscipliner();
        for (int i = 0; i < standardDiscipliner.length; i++) {
            System.out.println((i + 1) + ". " + standardDiscipliner[i].getDisciplinNavn());
        }

        Disciplin valgtDisciplin = null;
        while (true) {
            System.out.println("Indtast valg (1-" + standardDiscipliner.length + "): ");
            String valg = scanner.nextLine();
            try {
                int index = Integer.parseInt(valg) - 1;
                if (index >= 0 && index < standardDiscipliner.length) {
                    valgtDisciplin = standardDiscipliner[index];
                    break;
                }
                System.out.println("Ugyldigt valg. Prøv igen");
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt valg. Prøv igen");
            }
        }

        Traener nyTraener = new Traener(navn, cprNr, tlf, mail, medlemsType, valgtDisciplin);
        System.out.println("\nNy træner oprettet:");
        System.out.println(nyTraener);

        PersistensWriter.traenerWriter(Traener.getAlleTraenere());
    }
}