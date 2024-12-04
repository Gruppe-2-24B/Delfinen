import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HoldGenerator {
    private Scanner scanner;
    private static List<HoldIndeling> alleHold = new ArrayList<>();

    public HoldGenerator() {
        scanner = new Scanner(System.in);
    }

    public void holdGenerator() {
        System.out.println("=== Opret nyt Hold===");
    }

}
