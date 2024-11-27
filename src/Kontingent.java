import java.util.ArrayList;

public class Kontingent {
    private Medlem medlem;
    private int pris;
    private ArrayList<Integer> prisListe;
    private Dato dato;

    public Kontingent(Medlem medlem){
        this.medlem = medlem;
    }
    public int getPris(){
        return pris;
    }
}
