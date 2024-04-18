import java.util.Scanner;

public class Menu {
    private static Menu INSTANCE;
    private static final String EXIT = "3";

    private Menu() {

    }

    public static Menu getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new Menu();
        }

        return INSTANCE;
    }

    private void printMenu() {
        System.out.println("Kérem válasszon az alábbi lehetőségek közül:");
        System.out.println("1 - IP cím megadása és átváltása (decimális és bináris számrendszer között)");
        System.out.println("2 - Dolgozat írása");
        System.out.println("3 - Kilépés");
    }

    public void menu() {
        System.out.println("Üdvözöljük az IP-cím váltó programban!");
        String input = "";
        Scanner in = new Scanner(System.in);

        while (!(input.equals(EXIT))) {
            printMenu();
            input = in.nextLine();
            switch (input) {
                case "1":
                    //System.out.println("Az 1. menüpontot választotta!");
                    break;
                case "2":
                    //System.out.println("A 2. menüpontot választotta!");
                    break;
                case "3":
                    System.out.println("Viszontlátásra!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Helytelen inputot adott meg!");
                    break;
            }
        }

    }
}
