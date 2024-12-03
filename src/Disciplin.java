public class Disciplin {

    private String disciplinNavn;

    public Disciplin(String disciplinNavn) {
        this.disciplinNavn = disciplinNavn;
    }

    public String getDisciplinNavn() {
        return disciplinNavn;
    }

    public void setDisciplinNavn(String disciplinNavn) {
        this.disciplinNavn = disciplinNavn;
    }

    public static Disciplin[] getStandardDiscipliner()
    {
        return new Disciplin[]{
                new Disciplin("Bryst"),
                new Disciplin("Crawl"),
                new Disciplin("Rygcrawl"),
                new Disciplin("Butterfly")
        };
    }

    @Override
    public String toString() {
        return disciplinNavn;
    }
}


