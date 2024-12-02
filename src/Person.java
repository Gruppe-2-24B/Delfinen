public abstract class Person {
    protected String navn;
    protected String cprNr;
    protected int tlf;
    protected String mail;
    protected CprNr cpr;

    public Person() {}

    public Person(String navn, String cprNr, int tlf, String mail) {
        this.navn = navn;
        this.cprNr = cprNr;
        this.tlf = tlf;
        this.mail = mail;
    }
    public String getNavn() {
        return navn;
    }
    public String getCprNr() {
        return cprNr;
    }
    public int getTlf() {
        return tlf;
    }
    public String getMail() {
        return mail;
    }
}
