import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner SCAN = new Scanner(System.in);
    private static int WATER = 400;
    private static int MILK = 540;
    private static int BEANS = 120;
    private static int MONEY = 550;
    private static int CUPS = 9;

    // 0 - water, 1 - milk, 2 - beans, 3 - money
    private static final int[] ESPRESSO = {250, 0, 16, 4};
    private static final int[] LATTE = {350, 75, 20, 7};
    private static final int[] CAPPUCCINO = {200, 100, 12, 6};

    public static void main(String[] args) {
        while (true) {
            switch (getString("Write action (buy, fill, take, remaining, exit):")) {
                case "exit":
                    return;
                case "buy":
                    buyCoffee();
                    break;
                case "fill":
                    fillMachine();
                    break;
                case "take":
                    takeMoney();
                    break;
                case "remaining":
                    inventory();
            }
            System.out.println();
        }
    }

    private static void inventory() {
        System.out.println("\nThe coffee machine has:\n" +
                WATER + " of water\n" +
                MILK + " of milk\n" +
                BEANS + " of coffee beans\n" +
                CUPS + " of disposable cups\n" +
                "$" + MONEY + " of money");
    }

    private static void fillMachine() {
        String message = "Write how many %s of %s do you want to add:";

        WATER += getNum(String.format(message, "ml", "water"));
        MILK += getNum(String.format(message, "ml", "milk"));
        BEANS += getNum(String.format(message, "grams", "coffee beans"));
        CUPS += getNum(String.format(message, "disposable cups", "coffee"));
    }

    private static void buyCoffee() {
        String command;

        do {
            command = getString("\nWhat do you want to buy? 1 - espresso, 2 - latte, " +
                    "3 - cappuccino, back - to main menu:");
            if (command.length() == 1 && "123".contains(command)) {
                getCoffee(Integer.parseInt(command));
                return;
            }
        } while (!command.equals("back"));
    }

    private static void getCoffee(int choice) {
        int[] drink = choice == 1 ? ESPRESSO : choice == 2 ? LATTE : CAPPUCCINO;
        String outOf = CUPS < 1 ? "disposable cups" : WATER < drink[0] ? "water" : MILK < drink[1] ? "milk" :
                BEANS < drink[2] ? "coffee beans" : "";

        if (outOf.length() == 0) {
            System.out.println("I have enough resources, making you a coffee!");
            WATER -= drink[0];
            MILK -= drink[1];
            BEANS -= drink[2];
            MONEY += drink[3];
            CUPS--;
        } else System.out.printf("Sorry, not enough %s!%n", outOf);
    }

    private static void takeMoney() {
        System.out.printf("I gave you $%d%n", MONEY);
        MONEY = 0;
    }

    private static String getString(String message) {
        System.out.println(message);
        return SCAN.next().toLowerCase();
    }

    private static int getNum(String message) {
        System.out.println(message);
        return SCAN.nextInt();
    }
}