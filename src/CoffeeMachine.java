import java.util.Scanner;

public class CoffeeMachine {
    private static final Scanner SCAN = new Scanner(System.in);
    private static int WATER = 0;
    private static int MILK = 0;
    private static int BEANS = 0;

    public static void main(String[] args) {
        fillMachine();
        makeCoffee();
    }

    private static void fillMachine() {
        String message = "Write how many %s of %s the coffee machine has:";

        WATER = getNum(String.format(message, "ml", "water"));
        MILK = getNum(String.format(message, "ml", "milk"));
        BEANS = getNum(String.format(message, "grams", "coffee beans"));
    }

    private static void makeCoffee() {
        int cups = getNum("Write how many cups of coffee you will need:");
        int mostCups = Math.min(Math.min(WATER / 200, MILK / 50), BEANS / 15);

        System.out.println(cups == mostCups ? "Yes, I can make that amount of coffee" : cups > mostCups ?
                String.format("No, I can make only %d cup(s) of coffee", mostCups) :
                String.format("Yes, I can make that amount of coffee (and even %d more than that)", mostCups - cups));
    }

    private static int getNum(String message) {
        System.out.println(message);
        return SCAN.nextInt();
    }
}