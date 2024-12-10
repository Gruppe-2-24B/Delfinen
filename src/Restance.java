import java.util.ArrayList;

public class Restance {

    private boolean erBetalt;
    private static ArrayList<BetalingsRecord> betalingsRecords = new ArrayList<>();

    // Indre klasse, se dette som en attribut
    private static class BetalingsRecord {
        int medlemsNr;
        boolean erBetalt;

        BetalingsRecord(int medlemsNr, boolean erBetalt) {
            this.medlemsNr = medlemsNr;
            this.erBetalt = erBetalt;
        }
    }

    public Restance() {
        this.erBetalt = false;
    }

    public boolean erIRestance() {
        return !erBetalt;
    }


    public void setRestanceStatus(boolean status, int medlemsNr) {
        erBetalt = status;

        boolean found = false;
        for (BetalingsRecord record : betalingsRecords) {
            if (record.medlemsNr == medlemsNr) {
                record.erBetalt = status;
                found = true;
                break;
            }
        }

        if (!found) {
            betalingsRecords.add(new BetalingsRecord(medlemsNr, status));
        }
        if (status) {
            System.out.println("Betalingsstatus opdateret: Betalt");
        } else {
            System.out.println("Betalingsstatus opdateret: Ikke betalt");
        }
    }

    public void redigerRestanceStatus(boolean status, int medlemsNr) {
        setRestanceStatus(status, medlemsNr);
    }

    public boolean getErBetalt(int medlemsNr) {
        for (BetalingsRecord record : betalingsRecords) {
            if (record.medlemsNr == medlemsNr) {
                return record.erBetalt;
            }
        }
        return false; // Default to not paid if no record found
    }
} //klasse slut

