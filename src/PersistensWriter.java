import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.time.format.DateTimeFormatter;


public class PersistensWriter {

    private static final String fil = "medlemmer.txt";
    private static final String fil2 = "traenere.txt";
    private static final String HOLD_FIL = "hold.txt";
    private static final String fil3 = "resultater.txt";


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
        try (FileWriter writer = new FileWriter(fil2, false)) {
            for (Traener medlem : traenere) {
                writer.write(medlem.getNavn() + ",");
                writer.write(medlem.getCprNr() + ",");
                writer.write(medlem.getTlf()+ ",");
                writer.write(medlem.getMail() + ",");
                writer.write(medlem.getMedlemsType() + ",");
                writer.write(medlem.getTildeltDisciplin() + "\n");
            }
            System.out.print("Træner er gemt til fil!");
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

                for (Medlem medlem : holdIndeling.getMedlemmer())
                    writer.write(medlem.getTlf() + ";");
            }
            writer.write("\n");
            System.out.print("Hold er gemt til fil!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void resultatWriter(List<Resultat> resultater) {
        try (FileWriter writer = new FileWriter(fil3, false))
        {
            for (Resultat resultat : resultater)
            {
                writer.write(resultat.getPoint() + ",");
                writer.write(resultat.getDisciplin() + ",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                writer.write(resultat.getDato().format(formatter) + "\n");
            }
            System.out.print("Resultater er gemt til fil!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}




