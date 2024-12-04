import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
public class PersistensReader {

    private static final String FIL_NAVN = "medlemmer.txt";
    private static final String FIL_HOLD = "hold.txt";
    private static final String RESULTAT_FIL = "resultater.txt";


    public static void rydMedlemmer() {
        Medlem.getAlleMedlemmer().clear();
    }

    public static void laesMedlemmer() {
        Medlem.getAlleMedlemmer().clear();
        try (BufferedReader br = new BufferedReader(new FileReader(FIL_NAVN))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    String navn = data[0];
                    String cpr = data[1];
                    int tlf = Integer.parseInt(data[2]);
                    String mail = data[3];
                    String aktivitetsForm = data[4];
                    String medlemsStatus = data[5];
                    //String medlemsType = data[6];
                    String disciplin = data[6];

                    Medlem medlem = new Medlem(navn, cpr, tlf, mail, aktivitetsForm, medlemsStatus, disciplin);
                    Medlem.getAlleMedlemmer().add(medlem);
                    System.out.println("Indlæst medlem: " + medlem.getNavn());
                }
            }
            System.out.println("Medlemmer er indlæst fra fil.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
                if (data.length == 4) {
                    int point = Integer.parseInt(data[0]);
                    String disciplin = data[1];
                    LocalDate dato = LocalDate.parse(data[2], formatter);

                    int telefonnummer = Integer.parseInt(data[3]);
                    Medlem medlem = Medlem.findMedlemVedTelefonnummer(telefonnummer);
                    if (medlem != null) {
                        Resultat resultat = new Resultat(point, disciplin, dato, telefonnummer);
                        resultater.add(resultat);
                    }
                }
            }
            System.out.println("Resultater er indlæst fra fil.");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return resultater;
    }




    public static List<HoldIndeling> laesHold() {
        List<HoldIndeling> holdListe = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FIL_HOLD))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String holdType = data[0];
                    int holdNr = Integer.parseInt(data[1]);
                    int traenerTlf = Integer.parseInt(data[2]);

                    Traener traener = Traener.findTraenerVedTelefonnummer(traenerTlf);
                    if (traener != null) {
                        HoldIndeling hold = new HoldIndeling(holdType, holdNr, traener);


                        if (data.length > 3) {
                            String[] medlemsId = data[3].split(";");
                            for (String id : medlemsId) {
                                int telefonNummer = Integer.parseInt(id);
                                Medlem medlem = Medlem.findMedlemVedTelefonnummer(telefonNummer);
                                if (medlem != null) {
                                    hold.addMedlem(medlem);
                                }
                            }
                        }
                        holdListe.add(hold);
                    }
                }
            }
            System.out.println("Hold er indlæst fra fil!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return holdListe;

    }
}

