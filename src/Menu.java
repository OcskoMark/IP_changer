import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    private static Menu INSTANCE;
    private static boolean isFirst = true;
    private static final String EXIT = "3";

    private Menu() { }

    public static Menu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Menu();
        }

        return INSTANCE;
    }

    private void printOptions() {
        System.out.println("Kérem válasszon az alábbi lehetőségek közül:");
        System.out.println("1 - IP-cím megadása és átváltása (decimális és bináris formátum között)");
        System.out.println("2 - Dolgozat írása");
        System.out.println("3 - Kilépés");
    }

    private void printWelcome() {
        System.out.println("Üdvözöljük az IP-cím váltó programban!");
        isFirst = false;
    }

    public void handleOptions() {
        if (isFirst) {
            printWelcome();
        }

        String input = "";
        Scanner reader = new Scanner(System.in);

        while (!(input.equals(EXIT))) {
            printOptions();
            input = reader.nextLine();

            switch (input) {
                case "1":
                    addIPAddress();
                    break;

                case "2":
                    //doExam();
                    break;
                case "3":
                    System.out.println("Viszontlátásra!");
                    System.exit(0);

                default:
                    System.out.println("Helytelen inputot adott meg!");
                    System.out.println();
                    break;
            }
        }
    }

    private void addIPAddress() {
        boolean isValidAddress = false;
        Scanner reader = new Scanner(System.in);
        String input = "";

        while (!isValidAddress && !(input.equals("q"))) {
            System.out.println();
            System.out.println("(Visszalépés a főmenübe: 'q')");
            System.out.println("Kérem adja meg az IP-címet (decimális vagy bináris formában)!");
            input = reader.nextLine();

            if (IPAddress.checkIPAddressIsValid(input)) {
                isValidAddress = true;
                IPAddress ipAddress = new IPAddress(input);
                System.out.println();
                ipAddress.printChangedIPAddress();
            } else {
                System.out.println();
                System.out.println("Helytelen IP-címet adott meg (vagy helytelen a formátum)!");
                System.out.println("Kérem, helyes formátumban adja meg az IP címet!");
            }
        }

        System.out.println();
    }
}
