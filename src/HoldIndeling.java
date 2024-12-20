import java.util.*;

public class HoldIndeling {

    private String holdType;
    private int holdNr;
    private Traener traener;
    private Medlem medlem;
    private ArrayList<Medlem> medlemmer;

    public HoldIndeling(String holdType, int holdNr, Traener traener) {
        this.holdType = holdType;
        this.holdNr = holdNr;
        this.traener = traener;
        this.medlemmer = new ArrayList<>();
    }

    public void addMedlem(Medlem medlem) {
        if("konkurrencesvømmer".equals(medlem.getAktivitetsForm())) {
            if("Junior".equals(holdType) || "Senior".equals(holdType) && !medlemmer.contains(medlem)) {
                boolean medlemFindesAllerede = false;

                for (Medlem m : medlemmer) {
                    if(m.getTlf() == medlem.getTlf()) {
                        medlemFindesAllerede = true;
                        break;
                    }
                }
                if (!medlemFindesAllerede) {
                    medlemmer.add(medlem);
                }
            }
        }
    }

    public String getHoldType() {
        return holdType;
    }

    public int getHoldNr() {
        return holdNr;
    }

    public Traener getTraener() {
        return traener;
    }

    public void removeMedlem(Medlem medlem) {
        medlemmer.remove(medlem);
    }

    public ArrayList<Medlem> getMedlemmer() {
        return new ArrayList<>(medlemmer);
    }
}
