import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.io.BufferedReader;
import java.io.File;
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
    private static final String TRAENER_FIL = "traenere.txt";


    public static void laesMedlemmer() {

       // System.out.println("Starter indlæsning af medlemmer fra: " + FIL_NAVN); // Tilføjet til test
       Medlem.getAlleMedlemmer().clear(); // sidi


        try (BufferedReader br = new BufferedReader(new FileReader(FIL_NAVN))) {
            String line;
            int lineNumber = 0; // Tilføjet til test

            while ((line = br.readLine()) != null) {

                    lineNumber++;
                    //System.out.println("Læser linje " + lineNumber + ": " + line); // Tilføjet til test

                    String[] data = line.split(",");
                    //System.out.println("ANtal felter fundet: " + data.length); // Tilføjet til test

                    if (data.length == 8) {
                        try {
                        String navn = data[0];
                        String cpr = data[1];
                        int tlf = Integer.parseInt(data[2]);
                        String mail = data[3];
                        String aktivitetsForm = data[4];
                        String medlemsStatus = data[5];
                        String disciplin = data[7];


                        Medlem medlem = new Medlem(navn, cpr, tlf, mail, aktivitetsForm, medlemsStatus, disciplin);
                                // Medlem.getAlleMedlemmer().add(medlem);
                                //System.out.println("Indlæst medlem: " + medlem.getNavn());

                    } catch (Exception e) {
                        System.err.println("Fejl ved behandling af linje " + lineNumber + ": " + e.getMessage());
                        e.printStackTrace();
                    }
                } else{
                    System.err.println("Ugyldig linje " + lineNumber + ": Forventede 7 felter, men fandt " + data.length);
                }

            }
            System.out.println("Afsluttet indlæsning. Antal medlemmer: " + Medlem.getAlleMedlemmer().size());
        } catch (IOException e) {
            System.err.println("Fejl ved åbning/læsning af fil: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void laesTraenere() {
        // System.out.println("Starter indlæsning af trænere fra: " + TRAENER_FIL);

        Traener.getAlleTraenere().clear();

        File file = new File(TRAENER_FIL);
        if (file.exists() && !file.isDirectory()) {
            try (BufferedReader br = new BufferedReader(new FileReader(TRAENER_FIL))) {
                String line;
                int lineNumber = 0;

                while ((line = br.readLine()) != null) {
                    lineNumber++;
                    // System.out.println("Læser linje " + lineNumber + ": " + line);

                    String[] data = line.split(",");
                    // System.out.println("Antal felter fundet: " + data.length);

                    if (data.length == 5) { // Tilpas til det forventede antal felter
                        try {
                            String navn = data[0];
                            String cprNr = data[1];
                            int tlf = Integer.parseInt(data[2]);
                            String mail = data[3];
                            String medlemsType = data[4];
                            //String tildeltDisciplin = data[5];

                            // Opret disciplin-objekt baseret på navnet, hvis nødvendigt
                            //Disciplin disciplin = new Disciplin(tildeltDisciplin);

                            // Opret og tilføj træner
                            Traener traener = new Traener(navn, cprNr, tlf, mail, medlemsType);
                            // System.out.println("Indlæst træner: " + traener);

                        } catch (Exception e) {
                            System.err.println("Fejl ved behandling af linje " + lineNumber + ": " + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("Ugyldig linje " + lineNumber + ": Forventede 6 felter, men fandt " + data.length);
                    }
                }
                System.out.println("Afsluttet indlæsning. Antal trænere: " + Traener.getAlleTraenere().size());
            } catch (IOException e) {
                System.err.println("Fejl ved åbning/læsning af fil: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    public static List<Resultat> laesResultater()
    {
        List<Resultat> resultater = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RESULTAT_FIL)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] data = line.split(",");
                if (data.length == 4)
                {
                    double svommeTid = Double.parseDouble(data[0]);
                    String disciplin = data[1];
                    String dateStr = data[2].trim();
                    LocalDate dato = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    int telefonnummer = Integer.parseInt(data[3]);
                    Medlem medlem = Medlem.findMedlemVedTelefonnummer(telefonnummer);
                    if (medlem != null)
                    {
                        Resultat resultat = new Resultat(svommeTid, disciplin, dato, telefonnummer);
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
    public static void laesRestance() {

        Kontingent.clearBetalingsRecords();
        try (BufferedReader reader = new BufferedReader(new FileReader("restance.txt"))) {
            String linje;
            reader.readLine(); // Spring header over

            while ((linje = reader.readLine()) != null) {
                String[] data = linje.split(",");
                if (data.length == 2) {
                    String telefonnummer = data[0];
                    boolean erIRestance = data[1].equals("Ja");

                    Medlem medlem = Medlem.findMedlemVedTelefonnummer(Integer.parseInt(telefonnummer));
                    if (medlem != null) {
                        Kontingent kontingent = new Kontingent();
                        kontingent.setMedlem(medlem);
                        kontingent.redigerRestanceStatus(!erIRestance);
                    }
                }
            }

            System.out.println("Restance-status indlæst!");
        } catch (IOException e) {
            System.out.println("Ingen eksisterende restance-fil fundet.");
        }
    }
}

