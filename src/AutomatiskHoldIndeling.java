import java.util.ArrayList;
import java.util.List;

public class AutomatiskHoldIndeling {

    private static HoldIndeling juniorHold;
    private static HoldIndeling seniorHold;


    public AutomatiskHoldIndeling() {
    }

    public static void tildelHold(Medlem nytMedlem) {
        if ("konkurrencesvømmer".equals(nytMedlem.getAktivitetsForm())) {
            String holdType = nytMedlem.getMedlemsType();
            HoldIndeling relevantHold = findHold(holdType, nytMedlem.getDisciplinNavn());
            relevantHold.addMedlem(nytMedlem);
            System.out.println("Medlem tildelt til hold: " + holdType);
        } else {
            System.out.println("Medlem er ikke en konkurrencesvømmer: " + nytMedlem.getNavn());
        }
    }

    public static HoldIndeling findHold(String holdType, String disciplin) {
        if ("Junior".equals(holdType)) {
            if (juniorHold == null) {
                Traener traener = findTilgaengeligTraener(disciplin, holdType);
                juniorHold = new HoldIndeling(holdType, 1, traener);
            }
            return juniorHold;
        } else {
            if (seniorHold == null) {
                Traener traener = findTilgaengeligTraener(disciplin, holdType);
                seniorHold = new HoldIndeling(holdType, 2, traener);
            }
            return seniorHold;
        }
    }

    public static Traener findTilgaengeligTraener(String disciplinNavn, String holdType) {

        /*
        // Konverter disciplinNavn til disciplinObjekt
        Disciplin disciplin = null;

        for (Disciplin d : Disciplin.getStandardDiscipliner()) {
            if (d.getDisciplinNavn().equals(disciplinNavn)) {
                disciplin = d;
                break;
            }
        }
        if (disciplin == null) {
            throw new IllegalArgumentException("Kunne ikke finde disciplin" + disciplinNavn);
        }*/

        for (Traener traener : Traener.getAlleTraenere()) {
            if (traener.getTildeltDisciplin().getDisciplinNavn().equals(disciplinNavn) && traener.getMedlemsType().equals(holdType)) {
                return traener;
            }
        }

        for (Traener traener : Traener.getAlleTraenere()) {
            if (traener.getTildeltDisciplin().getDisciplinNavn().equals(disciplinNavn)) {
                return traener;
            }
        }

        throw new IllegalArgumentException("Ingen tilgængelige trænere fundet " + disciplinNavn + " i " + holdType);
    }

    public static void indlaesAlleHold() {
        List<HoldIndeling> alleHold = PersistensReader.laesHold();

        for (HoldIndeling hold : alleHold) {
            if ("Junior".equals(hold.getHoldType())) {
                juniorHold = hold;
            } else if ("Senior".equals(hold.getHoldType())) {
                seniorHold = hold;
            }
        }
    }

    public static void gemAlleHold() {
        List<HoldIndeling> alleHold = new ArrayList<>();
        if (juniorHold != null) alleHold.add(juniorHold);
        if (seniorHold != null) alleHold.add(seniorHold);
        PersistensWriter.holdWriter(alleHold);
    }

    // Metode til at vise alle hold
    public static void visAlleHold() {
        System.out.println("\n=== Junior Hold ===");
        if (juniorHold != null) {
            visHoldInfo(juniorHold);
        } else {
            System.out.println("Ingen junior hold oprettet endnu.");
        }

        System.out.println("\n=== Senior Hold ===");
        if (seniorHold != null) {
            visHoldInfo(seniorHold);
        } else {
            System.out.println("Ingen senior hold oprettet endnu.");
        }
    }

    public static void visHoldInfo(HoldIndeling hold) {
        System.out.println("\nHold " + hold.getHoldNr());
        System.out.println("Træner: " + hold.getTraener().getNavn());
        System.out.println("Antal medlemmer: " + hold.getMedlemmer().size());
        System.out.println("Medlemmer:");
        for (Medlem medlem : hold.getMedlemmer()) {
            System.out.println("- " + medlem.getNavn() + " (" + medlem.getDisciplinNavn() + ")");
        }
    }
}









