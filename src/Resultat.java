import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Resultat
{
    protected int point;
    protected String disciplin;
    protected LocalDate dato;
    protected Medlem medlem;

    public Resultat(int point, String disciplin, LocalDate dato, Medlem medlem)
    {
        this.point = point;
        this.disciplin = disciplin;
        this.dato = dato;
        this.medlem = medlem;
    }

    public int getPoint()
    {
        return point;
    }
    public void setPoint(int point)
    {
        this.point = point;
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
//  RESULTAT MENU
    public static void resultatMenu()
    {
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("Tilføj eller opdatér resultat.");
            System.out.println("1. Tilføj nyt resultat.");
            System.out.println("2. Opdatér eksisterende resultat.");
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
                case 0:
                    System.out.println("Går tilbage.");
                    return;
                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
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

        System.out.print("Indtast point: ");
        int point = scanner.nextInt();
        scanner.nextLine();

        Medlem medlem = Medlem.findMedlemVedTelefonnummer(Integer.parseInt(telefonnummer));
        if (medlem != null)
        {
            LocalDate dato = LocalDate.now();
            Resultat resultat = new Resultat(point, disciplin, dato, medlem);
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
        if (medlem != null) {

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

            System.out.print("Indtast nye point: ");
            int nyePoint = scanner.nextInt();
            scanner.nextLine();

            Resultat eksisterendeResultat = findResultatVedMedlemOgDisciplin(medlem, disciplin);

            if (eksisterendeResultat != null) {
                eksisterendeResultat.setPoint(nyePoint);
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
    private static Resultat findResultatVedMedlemOgDisciplin(Medlem medlem, String disciplin)
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
    private static Medlem findMedlemVedTelefonnummer(String telefonnummer)
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "Resultat{" +
                "point=" + point +
                ", disciplin='" + disciplin + '\'' +
                ", dato=" + dato +
                ", medlem=" + medlem.getNavn() +
                '}';
    }

}