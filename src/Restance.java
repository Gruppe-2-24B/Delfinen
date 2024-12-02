public class Restance {
    private Kontingent kontingent;
    public boolean erBetalt;

    public Restance(Kontingent kontingent) {
        this.kontingent = kontingent;
        this.erBetalt = false;
    }
    public boolean geterBetalt() {
        return erBetalt;
    }


}
