import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.io.File;


public class PersistensWriter {

    private static final String fil = "medlemmer.txt";
    private static final String fil2 = "traenere.txt";
    private static final String HOLD_FIL = "hold.txt";
    private static final String fil3 = "resultater.txt";

    public static void resultatWriter(List<Resultat> resultater)
    {
        File resultaterFile = new File(fil3);

        if (!resultaterFile.exists())
        {
            try
            {
                resultaterFile.createNewFile();
                System.out.println("Fil oprettet: resultater.txt");
            }
            catch (IOException e)
            {
                System.err.println("Fejl ved oprettelse af filen: " + e.getMessage());
            }
        }

        try (FileWriter writer = new FileWriter(fil3, false))
        {
            for (Resultat resultat : resultater)
            {
                writer.write(resultat.getSvommeTid() + ",");
                writer.write(resultat.getDisciplin() + ",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                writer.write(resultat.getDato().format(formatter) + ",");
                writer.write(resultat.getTelefonnummer() + "\n");
            }
            System.out.println("Resultater er gemt til fil!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void medlemsWriter(List<Medlem> medlemmer) {
        try (FileWriter writer = new FileWriter(fil, false)) {
            for (Medlem medlem : medlemmer) {
                    writer.write(medlem.getNavn() + ",");
                    writer.write(medlem.getCprNr() + ",");
                    writer.write(medlem.getTlf()+ ",");
                    writer.write(medlem.getMail() + ",");
                    writer.write(medlem.getAktivitetsForm() + ",");
                    writer.write(medlem.getMedlemsStatus() + ",");
                    writer.write(medlem.getMedlemsType() + ",");
                    writer.write(medlem.getDisciplinNavn() + "\n");
                }
                System.out.print("Medlemmet er gemt til fil!");
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public static void traenerWriter(List<Traener> traenere) {
        if(traenere.size() > 2) {
            System.out.println("Fejl: Der må maksimalt kun være 2 trænere." +
                    "\nDu skal fyre en træner, hvis du vil tilføje ny");
            return;
        }

        try (FileWriter writer = new FileWriter(fil2, false)) {
            for (Traener medlem : traenere) {
                writer.write(medlem.getNavn() + ",");
                writer.write(medlem.getCprNr() + ",");
                writer.write(medlem.getTlf()+ ",");
                writer.write(medlem.getMail() + ",");
                writer.write(medlem.getMedlemsType() + ",");
                //writer.write(medlem.getTildeltDisciplin() + "\n");
            }
            System.out.print("Træner er gemt til fil!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void kontingentWriter(List<Kontingent> kontingenter) {
        String fil = "kontingenter.txt";
        try (FileWriter writer = new FileWriter(fil, false)) {
            writer.write("Medlemsnummer,Navn,Alder,Medlemstype,Aktivitetsform,Kontingentpris,Restance\n");

            int samletKontingentBeloeb = 0;
            int antalMedlemmer = 0;
            int antalIRestance = 0;

            for (Kontingent kontingent : kontingenter) {
                Medlem medlemsDetaljer = kontingent.getMedlem();
                int kontingentPris = kontingent.getPris(kontingent.getMedlem());
                boolean erIRestance = kontingent.erIRestance();

                writer.write(
                        medlemsDetaljer.getMedlemsNr() + "," +
                                medlemsDetaljer.getNavn() + "," +
                                medlemsDetaljer.getAlder() + "," +
                                medlemsDetaljer.getMedlemsType() + "," +
                                medlemsDetaljer.getAktivitetsForm() + "," +
                                kontingentPris + "," +
                                (erIRestance ? "Ja" : "Nej") + "\n"
                );


                samletKontingentBeloeb += kontingentPris;
                antalMedlemmer++;
                if (erIRestance) {
                    antalIRestance++;
                }
            }

            writer.write("\nOpsummering:\n");
            writer.write("Samlet kontingentbeløb: " + samletKontingentBeloeb + " kr\n");
            writer.write("Antal medlemmer: " + antalMedlemmer + "\n");
            writer.write("Antal medlemmer i restance: " + antalIRestance + "\n");

            System.out.println("Kontingentoplysningerne er gemt til fil!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void holdWriter(List<HoldIndeling> hold) {
        try (FileWriter writer = new FileWriter(HOLD_FIL, false)) {
            for (HoldIndeling holdIndeling : hold) {
                writer.write(holdIndeling.getHoldType() + ",");
                writer.write(holdIndeling.getHoldNr() + ",");
                writer.write(holdIndeling.getTraener().getTlf() + ",");

                for (Medlem medlem : holdIndeling.getMedlemmer()) {
                    writer.write(medlem.getTlf() + ";");
                }
                writer.write("\n");
            }

            System.out.print("Hold er gemt til fil!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void restanceWriter(List<Kontingent> kontingenter) {
        String fil = "restance.txt";
        try (FileWriter writer = new FileWriter(fil, false)) {
            writer.write("Telefonnummer,Restance\n");

            for (Kontingent kontingent : kontingenter) {
                Medlem medlemsDetaljer = kontingent.getMedlem();
                writer.write(
                        medlemsDetaljer.getTlf() + "," +
                                (kontingent.erIRestance() ? "Ja" : "Nej") + "\n"
                );
            }

            System.out.println("Restance-status er gemt til fil!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




