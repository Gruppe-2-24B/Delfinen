public abstract class Person {
    protected String navn;
    protected int cprNr;
    protected int tlf;
    protected String mail;
    protected CprNr cpr;

    public Person(String navn, int cprNr, int tlf, String mail) {
        this.navn = navn;
        this.cprNr = cprNr;
        this.tlf = tlf;
        this.mail = mail;
    }
    public String getNavn() {
        return navn;
    }
    public int getCprNr() {
        return cprNr;
    }
    public int getTlf() {
        return tlf;
    }
    public String getMail() {
        return mail;
    }
}
