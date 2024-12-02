public class Restance {
    public boolean erBetalt;

    public Restance() {
        this.erBetalt = false;
    }

    public boolean erIRestance() {
        return !erBetalt;
    }

    public void setRestanceStatus(boolean status) {
        erBetalt = status;
        System.out.println("Betalingsstatus opdateret: " + (status ? "Betalt" : "Ikke betalt"));
    }

    public void redigerRestanceStatus(boolean status) {
        setRestanceStatus(status);
    }

    public boolean getErBetalt() {
        return erBetalt;
    }
}