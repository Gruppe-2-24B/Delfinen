public class TestAutomatiskHoldIndeling {
    public static void main(String[] args) {
       // PersistensReader.rydMedlemmer();
        PersistensReader.laesTraenere();

        System.out.println("Program starter...");
        System.out.println("Arbejdsmappe: " + System.getProperty("user.dir"));

        System.out.println("Indlæser medlemmer...");
        PersistensReader.laesMedlemmer();

        AutomatiskHoldIndeling.indlaesAlleHold();

        System.out.println("Tildeler hold til medlemmer...");
        for (Medlem medlem : Medlem.getAlleMedlemmer()) {
            AutomatiskHoldIndeling.tildelHold(medlem);
        }

        if(AutomatiskHoldIndeling.findHold("Senior", "disciplin")!=null) {
            AutomatiskHoldIndeling.visHoldInfo(AutomatiskHoldIndeling.findHold("Senior", "disciplin"));
        }

        System.out.println("Gemmer og viser alle hold...");
        AutomatiskHoldIndeling.gemAlleHold();
        AutomatiskHoldIndeling.visAlleHold();

        System.out.println("\nAlle indlæste medlemmer:");
        for (Medlem medlem : Medlem.getAlleMedlemmer()) {
            System.out.println(medlem);


        }
    }
}
