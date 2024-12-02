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
                if (data.length == 8) {
                    String navn = data[0];
                    String cpr = data[1];
                    int tlf = Integer.parseInt(data[2]);
                    String mail = data[3];
                    String aktivitetsForm = data[4];
                    String medlemsStatus = data[5];
                    String disciplin = data[6];

                    Medlem medlem = new Medlem(navn, cpr, tlf, mail, aktivitetsForm, medlemsStatus, disciplin);
                    Medlem.getAlleMedlemmer().add(medlem);
                }
            }
            System.out.println("Medlemmer er indlæst fra fil.");
        } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }

