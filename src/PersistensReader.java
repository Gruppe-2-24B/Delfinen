import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersistensReader {


    public static void rydMedlemmer()
    {
        Medlem.getAlleMedlemmer().clear();
    }

    private static final String FIL_NAVN = "medlemmer.txt";

    public static void laesMedlemmer() {
        Medlem.getAlleMedlemmer().clear(); // sidi
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
                    String medlemsType = data[6];
                    String disciplin = data[7];

                    Medlem medlem = new Medlem(navn, cpr, tlf, mail, aktivitetsForm, medlemsStatus, medlemsType, disciplin);
                    Medlem.getAlleMedlemmer().add(medlem);
                }
            }
            System.out.println("Medlemmer er indlæst fra fil.");
        } catch (IOException e) {
            e.printStackTrace();
            }
        }

    private static final String RESULTAT_FIL = "resultater.txt";

    public static List<Resultat> laesResultater()
    {
        List<Resultat> resultater = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(RESULTAT_FIL)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(",");
                if (data.length == 3)
                {
                    int point = Integer.parseInt(data[0]);
                    String disciplin = data[1];
                    LocalDate dato = LocalDate.parse(data[2], formatter);

                    int telefonnummer =Integer.parseInt(data[3]);
                            Medlem medlem = Medlem.findMedlemVedTelefonnummer(telefonnummer);
                    if (medlem != null)
                    {
                        Resultat resultat = new Resultat(point, disciplin, dato, medlem);
                        resultater.add(resultat);
                    }
                }
            }
            System.out.println("Resultater er indlæst fra fil.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return resultater;
    }


    }

