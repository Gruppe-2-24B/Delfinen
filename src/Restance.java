public class Restance {
    private boolean erBetalt;

    public Restance() {
        this.erBetalt = false;
    }

    public boolean erIRestance() {
        return !erBetalt;
    }

    public void setRestanceStatus(boolean status) {
        erBetalt = status;
        if (status) {
            System.out.println("Betalingsstatus opdateret: Betalt");
        } else {
            System.out.println("Betalingsstatus opdateret: Ikke betalt");
        }
    }

    public void redigerRestanceStatus(boolean status) {
        setRestanceStatus(status);
    }

    public boolean getErBetalt() {
        return erBetalt;
    }
} //klasse slut

