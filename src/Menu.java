import java.util.Scanner;

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
        System.out.println("Please, choose from the options below:");
        System.out.println("1 - IP address change (between decimal and binary form)");
        System.out.println("2 - Exam");
        System.out.println("3 - Quit");
    }

    private void printWelcome() {
        System.out.println("Welcome to the IP address changer program!");
        isFirst = false;
    }

    private void makeExam() {
        Exam exam = new Exam();
    }

    private void addIPAddress() {
        boolean isValidAddress = false;
        Scanner reader = new Scanner(System.in);
        String input = "";

        while (!isValidAddress && !(input.equals("q"))) {
            System.out.println();
            System.out.println("(Back to the main menu: 'q')");
            System.out.println("Please, type an IP address (in decimal or binary form)!");
            input = reader.nextLine();

            if (IPAddress.checkIPAddressIsValid(input)) {
                isValidAddress = true;
                IPAddress ipAddress = new IPAddress(input);
                System.out.println();
                ipAddress.printChangedIPAddress();
            } else {
                System.out.println();
                System.out.println("The IP address is incorrect (invalid value or form)!");
                System.out.println("Please, type a correct IP address!");
            }
        }

        System.out.println();
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
                    makeExam();
                    break;

                case "3":
                    System.out.println("Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Wrong input! Please, type a correct input!");
                    System.out.println();
                    break;
            }
        }
    }
}
