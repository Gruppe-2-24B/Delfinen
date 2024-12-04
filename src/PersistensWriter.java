import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class PersistensWriter {

    private static final String fil = "medlemmer.txt";
    private static final String fil2 = "traenere.txt";

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
        try (FileWriter writer = new FileWriter(fil2, true)) {
            for (Traener medlem : traenere) {
                writer.write(medlem.getNavn() + ",");
                writer.write(medlem.getCprNr() + ",");
                writer.write(medlem.getTlf()+ ",");
                writer.write(medlem.getMail() + ",");
                writer.write(medlem.getMedlemsType() + ",");
                writer.write(medlem.getTildeltDisciplin() + "\n");
            }
            System.out.print("Medlemmet er gemt til fil!");
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
                int kontingentPris = kontingent.getPris();
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


}




