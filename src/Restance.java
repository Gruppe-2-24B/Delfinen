import java.util.ArrayList;

public class Restance {

    private boolean erBetalt;
    static ArrayList<BetalingsRecord> betalingsRecords = new ArrayList<>();
    private int currentMedlemsNr;

    // Indre klasse, se dette som en attribut
    static class BetalingsRecord {
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
        for(BetalingsRecord record : betalingsRecords) {
            if (record.medlemsNr == currentMedlemsNr) {
                return !record.erBetalt;
            }
        }
        return true;
    }

    public void setRestanceStatus(boolean status, int medlemsNr) {
        this.currentMedlemsNr = medlemsNr;
        erBetalt = status;

        boolean found = false;
        for (Restance.BetalingsRecord record : betalingsRecords) {
            if (record.medlemsNr == medlemsNr) {
                record.erBetalt = status;
                found = true;
                break;
            }
        }

        if (!found) {
            betalingsRecords.add(new Restance.BetalingsRecord(medlemsNr, status));
        }
        if (status) {
            System.out.println("Betalingsstatus opdateret: Betalt");
        } else {
            System.out.println("Betalingsstatus opdateret: Ikke betalt");
        }
    }

    public void redigerRestanceStatus(boolean status, int medlemsNr) {
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

