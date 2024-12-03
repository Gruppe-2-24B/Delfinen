import java.util.Scanner;

    public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean fortsaet = true;

        // Initialiser objekter
        MedlemsGenerator medlemGenerator = new MedlemsGenerator();
        RedigerMedlem redigerOplysninger = new RedigerMedlem();

        while (fortsaet) {
            System.out.println("\nHvad vil du gøre?");
            System.out.println("1. Medlemsmenu");
            System.out.println("2. Hold og træner:");
            System.out.println("3. Kontingent");
            System.out.println("0. Luk program");

            int valg = input.nextInt();
            input.nextLine();

            switch (valg) {
                case 1:
                    medlemsMenu(medlemGenerator, redigerOplysninger, input);
                    break;

                case 0:
                    fortsaet = false;
                    System.out.println("Programmet afsluttes.");
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
            System.out.println("2. Ret stamkort");
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
}


        // Medlem medlem = new Medlem("Frederik", "1710961111", 20484247, "frederikrasmus@hotmail.dk", "Motionist", "Aktiv");
        // Medlem medlem2 = new Medlem("Birger", "1705961543", 40329453, "frederikrasmus@hotmail.dk", "Motionist", "Aktiv");
        // Medlem medlem3 = new Medlem("Seb", "2009963211", 20859323, "frederikrasmus@hotmail.dk", "Motionist", "Aktiv");


        //System.out.println(medlem);
        //Kontingent kontingent = new Kontingent(medlem);
        //System.out.println(kontingent);
/*

        MedlemsGenerator generator = new MedlemsGenerator();

        //  generator.medlemsGenerator();

        //for (Medlem medlemm : Medlem.getAlleMedlemmer()) {
        //  System.out.println(medlemm);
        // }


        PersistensReader reader = new PersistensReader();



        // sidi test
        RedigerMedlem redigerMedlem = new RedigerMedlem();
       // redigerMedlem.visMenu();

        TraenerGenerator generator2 = new TraenerGenerator();

        generator2.traenerGenerator();



    }
*/