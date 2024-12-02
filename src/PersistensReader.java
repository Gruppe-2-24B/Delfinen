import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PersistensReader {

    private static final String FIL_NAVN = "medlemmer.txt";

    public static void laesMedlemmer() {
        try (BufferedReader br = new BufferedReader(new FileReader(FIL_NAVN))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    int medlemsNr = Integer.parseInt(data[0]);
                    String navn = data[1];
                    int alder = Integer.parseInt(data[2]);
                    String cpr = data[3];
                    String aktivitetsForm = data[4];
                    String medlemsStatus = data[5];
                    String medlemsType = data[6];

                    Medlem medlem = new Medlem(navn, cpr, medlemsNr, aktivitetsForm, medlemsStatus, medlemsType);
                    Medlem.getAlleMedlemmer().add(medlem);
                }
            }
            System.out.println("Medlemmer er indlæst fra fil.");
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }

