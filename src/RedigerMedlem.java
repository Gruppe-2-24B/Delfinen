import java.util.Scanner;

public class RedigerMedlem
{
    public void visMenu()
    {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Liste over medlemmer:");
        for (Medlem medlem : Medlem.getAlleMedlemmer())
        {
            System.out.println(medlem);
        }


        System.out.println("\n Indtast telefonnummer for at vælge et medlem:");
        int medlemsNr = scanner.nextInt();
        scanner.nextLine();

        Medlem valgtMedlem = findMedlemVedMedlemsNr(medlemsNr);

        if (valgtMedlem == null)
        {
            System.out.println("Intet medlem fundet med det medlemsnummer.");
            return;
        }


        System.out.println("Rediger oplysninger for medlem #" + medlemsNr);
        redigerOplysninger(valgtMedlem, scanner);


        System.out.println("\n Medlemmet er korrekt opdateret:");
        System.out.println(valgtMedlem);
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

    protected void redigerOplysninger (Medlem medlem, Scanner scanner)
    {
        // Rediger mail
        System.out.println("Nuværende mail: " + medlem.getMail());
        System.out.println("Indtast ny mail (eller 0 for at springe over):");
        String nyMail = scanner.nextLine();
        if (!"0".equals(nyMail))
        {
            medlem.setMail(nyMail);
        }

        // Rediger telefonnummer
        System.out.println("Nuværende telefonnummer: " + medlem.getTlf());
        System.out.println("Indtast nyt telefonnummer (eller 0 for at springe over):");
        int nytTlf = scanner.nextInt();
        if (nytTlf != 0)
        {
            medlem.setTlf(nytTlf);
        }
        scanner.nextLine();

        // Rediger aktivitetsform
        System.out.println("Nuværende aktivitetsform: " + medlem.getAktivitetsForm());
        System.out.println("Indtast ny aktivitetsform (eller 0 for at springe over):");
        String nyAktivitetsForm = scanner.nextLine();
        if (!"0".equals(nyAktivitetsForm))
        {
            medlem.setAktivitetsForm(nyAktivitetsForm);
        }

        // Rediger medlemsstatus
        System.out.println("Nuværende medlemsstatus: " + medlem.getMedlemsStatus());
        System.out.println("Indtast ny medlemsstatus (eller 0 for at springe over):");
        String nyMedlemsStatus = scanner.nextLine();
        if (!"0".equals(nyMedlemsStatus))
        {
            medlem.setMedlemsStatus(nyMedlemsStatus);
        }
    }
}