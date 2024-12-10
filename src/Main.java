import java.util.ArrayList;
import java.util.Scanner;

    public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean fortsaet = true;

        Medlem.getAlleMedlemmer().clear();

        // Initialiser Automatisk Hold Indeling

        PersistensReader.laesMedlemmer();
        PersistensReader.laesTraenere();
        AutomatiskHoldIndeling.indlaesAlleHold();
        PersistensReader.laesRestance();
        // Initialiser objekter
        MedlemsGenerator medlemGenerator = new MedlemsGenerator();
        RedigerMedlem redigerOplysninger = new RedigerMedlem(); // Medlemmer læses
        Kontingent kontingent = new Kontingent();


        for (Medlem medlem : Medlem.getAlleMedlemmer()) {
            AutomatiskHoldIndeling.tildelHold(medlem);
        }


        while (fortsaet) {
            System.out.println("\nHvad vil du gøre?");
            System.out.println("1. Medlemsmenu");
            System.out.println("2. Hold og træner:");
            System.out.println("3. Kontingent");
            System.out.println("4. Resultater");
            System.out.println("5. Konkurrence Hold");
            System.out.println("0. Luk program");

            int valg = input.nextInt();
            input.nextLine();

            switch (valg) {
                case 1:
                    medlemsMenu(medlemGenerator, redigerOplysninger, input);
                    break;

                case 3:
                    kontingentMenu(kontingent, input);
                    break;

                case 4:
                    Resultat.resultatMenu();
                    break;

                case 5:
                    holdMenu(input);
                    break;

                case 0:
                    fortsaet = false;
                    System.out.println("Programmet afsluttes.");
                    AutomatiskHoldIndeling.gemAlleHold();

                    ArrayList<Kontingent> kontingentListe = new ArrayList<>();
                    for (Medlem medlem : Medlem.getAlleMedlemmer()) {
                        Kontingent nyKontingent = new Kontingent();
                        nyKontingent.setMedlem(medlem);
                        kontingentListe.add(nyKontingent);
                    }
                    PersistensWriter.restanceWriter(kontingentListe);
                    break;



                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    private static void medlemsMenu(MedlemsGenerator generator, RedigerMedlem redigerOplysninger, Scanner input) {
        boolean iMedlemsMenu = true;
        while (iMedlemsMenu) {
            System.out.println("1. Opret medlem");
            System.out.println("2. Ret medlem");
            System.out.println("0. Tilbage til hovedmenu");


            int valg2 = input.nextInt();
            input.nextLine();

            switch (valg2) {
                case 1:
                    generator.medlemsGenerator(); // Kald oprettelsesmetode
                    break;

                case 2:
                    redigerOplysninger.visMenu(); // Kald redigeringsmetode
                    break;

                case 0:
                    iMedlemsMenu = false; // Afslut medlemsmenu
                    break;

                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
            }
        }
    }

    private static void kontingentMenu(Kontingent kontingent, Scanner input) {
        boolean iKontingentMenu = true;
        while (iKontingentMenu) {
            System.out.println("\nKontingentmenu:");
            System.out.println("1. Vis medlemsinfo og kontingent");
            System.out.println("2. Vis medlemmer i restance");
            System.out.println("3. Beregn samlet kontingent");
            System.out.println("4. Rediger restance-status");
            System.out.println("5. Vis betalingsstatus for medlem");
            System.out.println("6. Beregn summen af alle kontingenter");
            System.out.println("0. Tilbage til hovedmenu");

            int valg2 = input.nextInt();
            input.nextLine();

            switch (valg2) {
                case 1:
                    kontingent.visKontingentListe();
                    break;

                case 2:
                    ArrayList<Kontingent> restanceListe = Kontingent.genererRestanceListe(new ArrayList<>());
                    Kontingent.visRestanceListe(restanceListe);
                    break;

                case 3:
                    ArrayList<Kontingent> alleKontingenter = new ArrayList<>();
                    kontingent.visSum(alleKontingenter);
                    break;

                case 4:
                    kontingent.redigerRestanceStatus(kontingent);
                    break;

                case 5:
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
                    break;

                case 6:
                    ArrayList<Kontingent> kontingentListe = new ArrayList<>();

                    for (Medlem medlem : Medlem.getAlleMedlemmer()) {
                        Kontingent nyKontingent = new Kontingent();
                        nyKontingent.setMedlem(medlem);
                        kontingentListe.add(nyKontingent);
                    }

                    int samletBeloeb = Kontingent.beregnSum(kontingentListe);
                    System.out.println("Summen af alle kontingentindbetalinger: " + samletBeloeb + " kr.");
                    break;

                case 0:
                    iKontingentMenu = false;
                    break;


                default:
                    System.out.println("Ugyldigt valg. Prøv igen");
            }
        }
    }

    private static void holdMenu(Scanner input) {
        boolean iHoldMenu = true;
        while (iHoldMenu) {
            System.out.println("\nVis hold:");
            System.out.println("1. Vis Junior hold");
            System.out.println("2. Vis Senior hold");
            System.out.println("0. Tilbage til hovedmenu");

            int valg = input.nextInt();
            input.nextLine();
            switch (valg) {
                case 1:
                    AutomatiskHoldIndeling.visSpecifiktHold("Junior");
                    break;

                case 2:
                    AutomatiskHoldIndeling.visSpecifiktHold("Senior");
                    break;

                    case 0:
                        iHoldMenu = false;
                        break;

                default:
                    System.out.println("Ugyldigt valg, prøv igen");
            }
        }
    }

} //slut klasse

