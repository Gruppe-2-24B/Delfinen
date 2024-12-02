import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

       Medlem medlem = new Medlem("Frederik","1710961111",20484247,"frederikrasmus@hotmail.dk","Motionist","Aktiv", "Bryst");
       // Medlem medlem2 = new Medlem("Birger","1705961543",40329453,"frederikrasmus@hotmail.dk","Motionist","Aktiv");
       // Medlem medlem3 = new Medlem("Seb","2009963211",20859323,"frederikrasmus@hotmail.dk","Motionist","Aktiv");

        //System.out.println(medlem);
        //Kontingent kontingent = new Kontingent(medlem);
        //System.out.println(kontingent);

        //kontingent.visKontingentListe();
        //kontingent.redigerRestanceStatus(true);






        MedlemsGenerator generator = new MedlemsGenerator();

        generator.medlemsGenerator();

        for (Medlem medlemm : Medlem.getAlleMedlemmer()) {
            System.out.println(medlemm);
        }
    }
}