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
            if (relevantHold != null) {
                relevantHold.addMedlem(nytMedlem);
            } else {
                System.out.println("Ingen tilgængelige trænere. Kan ikke tildele medlem til et hold");
            }
        }
    }

    public static HoldIndeling findHold(String holdType, String disciplin) {
        if ("Junior".equals(holdType)) {
            if (juniorHold == null) {
                Traener traener = findTilgaengeligTraener(holdType);
                if(traener != null) {
                    juniorHold = new HoldIndeling(holdType, 1, traener);
                }
            }
            return juniorHold;
        } else {
            if (seniorHold == null) {
                Traener traener = findTilgaengeligTraener(holdType);
                if(traener != null) {
                    seniorHold = new HoldIndeling(holdType, 2, traener);
                }
            }
            return seniorHold;
        }
    }

    public static Traener findTilgaengeligTraener(String holdType) {
        List<Traener> alleTraenere = Traener.getAlleTraenere();

        if (alleTraenere == null || alleTraenere.isEmpty()) {
            return null;
        }

        for (Traener traener : Traener.getAlleTraenere()) {
            if (traener.getMedlemsType().equals(holdType)) {
                return traener;
            }
        }
        return null;
    }

    public static void opdaterHold() {
        for (Medlem medlem : Medlem.getAlleMedlemmer()) {
            AutomatiskHoldIndeling.tildelHold(medlem);
        }
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

    public static void visSpecifiktHold(String holdType) {
        HoldIndeling hold = null;

        if ("Junior".equals(holdType)) {
            hold = juniorHold;
        } else if ("Senior".equals(holdType)) {
            hold = seniorHold;
        }

        if (hold != null) {
            System.out.println("\n=== " + holdType + " Hold ===");
            visHoldInfo(hold);
        } else {
            System.out.println("Ingen " + holdType.toUpperCase() + " Hold oprettet endnu.");
        }
    }

    public static void fjernMedlemFraHold(Medlem medlem) {
        if (juniorHold != null) {
            juniorHold.removeMedlem(medlem);
        }
        if (seniorHold != null) {
            seniorHold.removeMedlem(medlem);
        }
    }

    public static void visHoldInfo(HoldIndeling hold) {
        System.out.println("\nHold " + hold.getHoldNr());
        System.out.println("Træner: " + hold.getTraener().getNavn());
        System.out.println("Antal medlemmer: " + hold.getMedlemmer().size());
        System.out.println("Medlemmer:");
        for (Medlem medlem : hold.getMedlemmer()) {
            System.out.println("- " + medlem.getNavn() + " (" + medlem.getDisciplinNavn() + ")" + " #" + medlem.getMedlemsNr());
        }
    }
}









