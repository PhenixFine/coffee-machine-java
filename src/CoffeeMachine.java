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
        inventory();
        System.out.println("\nWrite action (buy, fill, take): ");
        switch (SCAN.next().toLowerCase()) {
            case "buy":
                buyCoffee();
                break;
            case "fill":
                fillMachine();
                break;
            case "take":
                takeMoney();
                break;
            default:
                return;
        }
        System.out.println();
        inventory();
    }

    private static void inventory() {
        System.out.println("The coffee machine has:\n" +
                WATER + " of water\n" +
                MILK + " of milk\n" +
                BEANS + " of coffee beans\n" +
                CUPS + " of disposable cups\n" +
                MONEY + " of money");
    }

    private static void fillMachine() {
        String message = "Write how many %s of %s do you want to add:";

        WATER += getNum(String.format(message, "ml", "water"));
        MILK += getNum(String.format(message, "ml", "milk"));
        BEANS += getNum(String.format(message, "grams", "coffee beans"));
        CUPS += getNum(String.format(message, "disposable cups", "coffee"));
    }

    private static void buyCoffee() {
        int choice = getNum("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        if (choice < 1 || choice > 3) return;
        int[] drink = choice == 1 ? ESPRESSO : choice == 2 ? LATTE : CAPPUCCINO;
        int cups = Math.min(WATER / drink[0], Math.min(MILK / Math.max(drink[1], 1), Math.min(BEANS / drink[2], CUPS)));

        if (cups > 0) {
            WATER -= drink[0];
            MILK -= drink[1];
            BEANS -= drink[2];
            MONEY += drink[3];
            CUPS--;
        } else System.out.println("Not enough resources");
    }

    private static void takeMoney() {
        System.out.printf("I gave you $%d%n", MONEY);
        MONEY = 0;
    }

    private static int getNum(String message) {
        System.out.println(message);
        return SCAN.nextInt();
    }
}