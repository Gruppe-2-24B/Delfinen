import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class PersistensWriter {

    private static final String fil = "medlemmer.txt";

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
    }


