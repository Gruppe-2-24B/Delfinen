import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Resultat
{
    protected double svommeTid;
    protected String disciplin;
    protected LocalDate dato;
    protected Medlem medlem;
    protected int telefonnummer;

    public Resultat(double svommeTid, String disciplin, LocalDate dato, int telefonnummer)
    {
        this.svommeTid = svommeTid;
        this.disciplin = disciplin;
        this.dato = dato;
        this.medlem = Medlem.findMedlemVedTelefonnummer(telefonnummer);
        this.telefonnummer = telefonnummer;

        // System.out.println("Nyt resultat oprettet: " + this);
    }

    public double getSvommeTid()
    {
        return svommeTid;
    }
    public void setSvommeTid(double svommeTid)
    {
        this.svommeTid = svommeTid;
    }
    public String getDisciplin()
    {
        return disciplin;
    }
    public void setDisciplin(String disciplin)
    {
        this.disciplin = disciplin;
    }
    public LocalDate getDato()
    {
        return dato;
    }
    public void setDato(LocalDate dato)
    {
        this.dato = dato;
    }
    public Medlem getMedlem()
    {
        return medlem;
    }
    public void setMedlem(Medlem medlem)
    {
        this.medlem = medlem;
    }

    public int getTlf()
    {
        return telefonnummer;
    }
    public void setTlf(int telefonnummer)
    {
        this.telefonnummer = telefonnummer;
    }
    public int getTelefonnummer()
    {
        return telefonnummer;
    }
    public void setTelefonnummer(int telefonnummer)
    {
        this.telefonnummer = telefonnummer;
    }


//  RESULTAT MENU
    public static void resultatMenu()
    {
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("Tilføj eller opdatér resultat.");
            System.out.println("1. Tilføj nyt resultat.");
            System.out.println("2. Opdatér eksisterende resultat.");
            System.out.println("3. Vis liste over resultater.");
            System.out.println("0. Gå tilbage.");
            int valg = scanner.nextInt();
            scanner.nextLine();

            switch (valg)
            {
                case 1:
                    tilfoejResultat(scanner);
                    break;
                case 2:
                    opdaterResultat(scanner);
                    break;
                case 3:
                    visResultater();
                    break;
                case 0:
                    System.out.println("Går tilbage.");
                    return;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }

//  VIS LISTE OVER RESULTATER
public static void visResultater()
{
    try
    {
        List<Resultat> resultater = PersistensReader.laesResultater();

        for (Resultat resultat : resultater)
        {
            System.out.println(resultat);
        }
    } catch (Exception e)
    {
        System.out.println("Fejl ved læsning af resultater: " + e.getMessage());
    }
}




//  VIS NUVÆRENDE MEDLEMMERE
    protected static void visMedlemmere()
    {
        if (Medlem.getAlleMedlemmer().isEmpty())
        {
            System.out.println("Der er ingen medlemmer.");
        }
        else
        {
            System.out.println("\nListe over medlemmere:");
            for (Medlem medlem : Medlem.getAlleMedlemmer())
            {
                System.out.println("Navn: " + medlem.getNavn() + ", Medlemsnummer: " + medlem.getTlf());
            }
        }
    }

//  TILFØJ RESULTAT
    protected static void tilfoejResultat(Scanner scanner)
    {
        System.out.print("Indtast medlemsnummer: ");
        String telefonnummer = scanner.nextLine();

        System.out.println("Vælg disciplin:");
        System.out.println("1. Crawl");
        System.out.println("2. Bryst");
        System.out.println("3. Rygcrawl");
        System.out.println("4. Butterfly");
        int disciplinValg = scanner.nextInt();
        scanner.nextLine();

        String disciplin = "";

        switch (disciplinValg)
        {
            case 1:
                disciplin = "Crawl";
                break;
                case 2:
                    disciplin = "Bryst";
                    break;
                    case 3:
                        disciplin = "Rygcrawl";
                        break;
                        case 4:
                            disciplin = "Butterfly";
                            break;
                            default:
                                System.out.println("Ugyldigt valg. Standarddisciplin 'Crawl' valgt.");
                                disciplin = "Crawl";
                                break;
        }

        System.out.print("Indtast svømmetid: ");
        System.out.println("FORMAT: XX,XX");
        double svommeTid = scanner.nextDouble();
        scanner.nextLine();

        Medlem medlem = Medlem.findMedlemVedTelefonnummer(Integer.parseInt(telefonnummer));

        if (medlem != null)
        {
            LocalDate dato = LocalDate.now();
            Resultat resultat = new Resultat(svommeTid, disciplin, dato, Integer.parseInt(telefonnummer));
            System.out.println("Nyt resultat tilføjet: " + resultat);

            List<Resultat> resultater = PersistensReader.laesResultater();
            resultater.add(resultat);
            PersistensWriter.resultatWriter(resultater);
        }
        else
        {
            System.out.println("Medlem med medlemsnummer " + telefonnummer + " findes ikke.");
        }
    }

//  OPDATÉR RESULTAT

    protected static void opdaterResultat(Scanner scanner)
    {
        System.out.print("Indtast telefonnummer (medlemsnummer): ");
        String telefonnummer = scanner.nextLine();

        Medlem medlem = Medlem.findMedlemVedTelefonnummer(Integer.parseInt(telefonnummer));
        if (medlem != null)
        {

            System.out.println("Vælg disciplin:");
            System.out.println("1. Crawl");
            System.out.println("2. Bryst");
            System.out.println("3. Rygcrawl");
            System.out.println("4. Butterfly");
            int disciplinValg = scanner.nextInt();
            scanner.nextLine();

            String disciplin = "";

            switch (disciplinValg)
            {
                case 1:
                    disciplin = "Crawl";
                    break;
                    case 2:
                        disciplin = "Bryst";
                        break;
                        case 3:
                            disciplin = "Rygcrawl";
                            break;
                            case 4:
                                disciplin = "Butterfly";
                                break;
                                default:
                                    System.out.println("Ugyldigt valg. Standarddisciplin 'Crawl' valgt.");
                                    disciplin = "Crawl";
                                    break;
            }

            System.out.print("Indtast ny svømmetid - ");
            System.out.println("FORMAT: XX,XX");
            double nySvommeTid = scanner.nextDouble();
            scanner.nextLine();

            Resultat eksisterendeResultat = findResultatVedMedlemOgDisciplin(medlem, disciplin);

            if (eksisterendeResultat != null)
            {
                eksisterendeResultat.setSvommeTid(nySvommeTid);
                eksisterendeResultat.setDato(LocalDate.now());
                System.out.println("Resultat opdateret: " + eksisterendeResultat);

                List<Resultat> resultater = PersistensReader.laesResultater();

                for (int i = 0; i < resultater.size(); i++)
                {
                    if (resultater.get(i).getMedlem().equals(medlem) && resultater.get(i).getDisciplin().equals(disciplin))
                    {
                        resultater.set(i, eksisterendeResultat);
                    }
                }

                PersistensWriter.resultatWriter(resultater);
            }
            else
            {
                System.out.println("Der er ikke noget eksisterende resultat for denne disciplin.");
            }
        }
        else
        {
            System.out.println("Medlem med telefonnummer " + telefonnummer + " findes ikke.");
        }
    }


//  FIND RESULTAT VED MEDLEM OG DISCIPLIN

    protected static Resultat findResultatVedMedlemOgDisciplin(Medlem medlem, String disciplin)
    {
        List<Resultat> resultater = PersistensReader.laesResultater();
        for (Resultat resultat : resultater)
        {
            if (resultat.getMedlem().equals(medlem) && resultat.getDisciplin().equalsIgnoreCase(disciplin))
            {
                return resultat;
            }
        }
        return null;
    }


//  FIND MEDLEM VED TELEFONNUMMER

    protected static Medlem findMedlemVedTelefonnummer(String telefonnummer)
    {
        return null;
    }




    @Override
    public String toString()
    {
        return "Resultat: {" +
                "svømmetid: " + svommeTid +
                ", disciplin: '" + disciplin + '\'' +
                ", dato: " + dato +
                ", medlems #: " + telefonnummer +
                ", medlem: " + medlem.getNavn() +
                '}';
    }
}