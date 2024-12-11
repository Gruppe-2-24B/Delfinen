import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RedigerMedlem
{

    public RedigerMedlem() {}

    public void visMenu()
    {
        Scanner scanner = new Scanner(System.in);

        // PersistensReader reader = new PersistensReader();
        // reader.laesMedlemmer();

        System.out.println("Liste over medlemmer:");
        for (Medlem medlem : Medlem.getAlleMedlemmer())
        {
            System.out.println(medlem);
            System.out.println("-------------------------");
        }
        
        System.out.println("\nIndtast telefonnummer for at vælge et medlem:");
        int telefonnummer = scanner.nextInt();
        scanner.nextLine();

        Medlem valgtMedlem = findMedlemVedMedlemsNr(telefonnummer);

        if (valgtMedlem == null)
        {
            System.out.println("Intet medlem fundet med det medlemsnummer.");
            return;
        }

        System.out.println("Rediger oplysninger for medlem #" + telefonnummer);
        redigerOplysninger(valgtMedlem, scanner);


        System.out.println("\nMedlemmet er korrekt opdateret:");
        System.out.println(valgtMedlem);

        // Ensure no duplicates before writing
        List<Medlem> uniqueMedlemmer = new ArrayList<>();
        for (Medlem medlem : Medlem.getAlleMedlemmer()) {
            if (!uniqueMedlemmer.contains(medlem)) {
                uniqueMedlemmer.add(medlem);
            }
        }
        PersistensWriter.medlemsWriter(uniqueMedlemmer);


    }

    protected Medlem findMedlemVedMedlemsNr(int medlemsNr)
    {
        for (Medlem medlem : Medlem.getAlleMedlemmer())
        {
            if (medlem.getMedlemsNr() == medlemsNr)
            {
                return medlem;
            }
        }
        return null;
    }

    protected void redigerOplysninger(Medlem medlem, Scanner scanner)
    {
        // MAIL
        System.out.println("Nuværende mail: " + medlem.getMail());
        String nyMail;
        do
        {
            System.out.println("Indtast ny mail (eller 0 for at springe over):");
            nyMail = scanner.nextLine();
            if ("0".equals(nyMail))
            {
                break;
            }
            if (!nyMail.contains("@"))
            {
                System.out.println("Ugyldig mail. Prøv igen.");
            }
        } while (!"0".equals(nyMail) && !nyMail.contains("@"));

        if (!"0".equals(nyMail))
        {
            medlem.setMail(nyMail);
        }

        // TLF
        System.out.println("Nuværende telefonnummer: " + medlem.getTlf());
        int nytTlf;
        do
        {
            System.out.println("Indtast nyt telefonnummer (eller 0 for at springe over):");
            while (!scanner.hasNextInt())
            {
                System.out.println("Ugyldigt input. Indtast venligst et gyldigt telefonnummer.");
                scanner.next();
            }
            nytTlf = scanner.nextInt();
            scanner.nextLine();
            if (nytTlf < 0 && nytTlf != 0)
            {
                System.out.println("Telefonnummer kan ikke være negativt. Prøv igen.");
            }
        } while (nytTlf < 0 && nytTlf != 0);

        if (nytTlf != 0)
        {
            medlem.setTlf(nytTlf);
        }

        // FORM
        System.out.println("Nuværende aktivitetsform: " + medlem.getAktivitetsForm());
        String[] aktivitetsformer = {"Motionist", "Konkurrencesvømmer"};
        int aktivitetsValg;
        while (true)
        {
            System.out.println("Vælg ny aktivitetsform:");
            for (int i = 0; i < aktivitetsformer.length; i++)
            {
                System.out.println((i + 1) + ". " + aktivitetsformer[i]);
            }
            System.out.println("0. Behold nuværende aktivitetsform");

            if (scanner.hasNextInt())
            {
                aktivitetsValg = scanner.nextInt();
                scanner.nextLine();
                if (aktivitetsValg >= 0 && aktivitetsValg <= aktivitetsformer.length)
                {
                    break;
                }
                System.out.println("Ugyldigt valg. Prøv igen.");
            } else
            {
                System.out.println("Ugyldigt input. Indtast venligst et gyldigt valg.");
                scanner.next();
            }
        }

        if (aktivitetsValg > 0)
        {
            String oldAktivitetsValg = medlem.getAktivitetsForm();
            medlem.setAktivitetsForm(aktivitetsformer[aktivitetsValg - 1]);

            // Hvis medlem ændres til motionist, fjern fra holdListe
            if ("motionist".equalsIgnoreCase(aktivitetsformer[aktivitetsValg - 1])) {
                AutomatiskHoldIndeling.fjernMedlemFraHold(medlem);
                AutomatiskHoldIndeling.gemAlleHold();
                AutomatiskHoldIndeling.indlaesAlleHold();
            } else if ("motionist".equalsIgnoreCase(oldAktivitetsValg)) {
                AutomatiskHoldIndeling.tildelHold(medlem);
                AutomatiskHoldIndeling.gemAlleHold();
                AutomatiskHoldIndeling.indlaesAlleHold();
            }


            //  STATUS
            System.out.println("Nuværende medlemsstatus: " + medlem.getMedlemsStatus());
            String[] medlemsstatusser = {"aktiv", "passiv"};
            int medlemsstatusValg;
            while (true)
            {
                System.out.println("Vælg ny medlemsstatus:");
                for (int i = 0; i < medlemsstatusser.length; i++)
                {
                    System.out.println((i + 1) + ". " + medlemsstatusser[i].substring(0, 1).toUpperCase() + medlemsstatusser[i].substring(1));
                }
                System.out.println("0. Behold nuværende medlemsstatus");

                if (scanner.hasNextInt())
                {
                    medlemsstatusValg = scanner.nextInt();
                    scanner.nextLine();
                    if (medlemsstatusValg >= 0 && medlemsstatusValg <= medlemsstatusser.length)
                    {
                        break;
                    }
                    System.out.println("Ugyldigt valg. Prøv igen.");
                } else
                {
                    System.out.println("Ugyldigt input. Indtast venligst et gyldigt valg.");
                    scanner.next();
                }
            }

            if (medlemsstatusValg > 0)
            {
                medlem.setMedlemsStatus(medlemsstatusser[medlemsstatusValg - 1]);
            }

            //  DISCIPLIN
            System.out.println("Nuværende disciplin: " + medlem.getDisciplinNavn());
            Disciplin[] discipliner = Disciplin.getStandardDiscipliner();
            int disciplinValg;
            while (true)
            {
                System.out.println("Vælg ny disciplin:");
                for (int i = 0; i < discipliner.length; i++)
                {
                    System.out.println((i + 1) + ". " + discipliner[i].getDisciplinNavn());
                }
                System.out.println("0. Behold nuværende disciplin");

                if (scanner.hasNextInt())
                {
                    disciplinValg = scanner.nextInt();
                    scanner.nextLine();
                    if (disciplinValg >= 0 && disciplinValg <= discipliner.length)
                    {
                        break;
                    }
                    System.out.println("Ugyldigt valg. Prøv igen.");
                } else
                {
                    System.out.println("Ugyldigt input. Indtast venligst et gyldigt valg.");
                    scanner.next();
                }
            }

            if (disciplinValg > 0)
            {

                medlem.setDisciplinNavn(discipliner[disciplinValg - 1].getDisciplinNavn());
            }
        }
    }
}