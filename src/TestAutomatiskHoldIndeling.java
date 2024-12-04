public class TestAutomatiskHoldIndeling {

    public static void main(String[] args) {
        // Indlæs eksisterende data
        System.out.println("Indlæser medlemmer...");
        PersistensReader.laesMedlemmer();
        /*
        AutomatiskHoldIndeling.indlaesAlleHold();

        // Tildel hold til alle medlemmer
        System.out.println("Tildeler hold til medlemmer...");
        for (Medlem medlem : Medlem.getAlleMedlemmer()) {
            AutomatiskHoldIndeling.tildelHold(medlem);
        }

        // Gem og vis alle hold
        System.out.println("Gemmer og viser alle hold...");
        AutomatiskHoldIndeling.gemAlleHold();
        AutomatiskHoldIndeling.visAlleHold();
        *
         */
    }
}