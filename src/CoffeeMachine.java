import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        int cups = getNum("Write how many cups of coffee you will need:");

        System.out.printf("For %d cups of coffee you will need:%n", cups);
        System.out.printf("%d ml of water%n", cups * 200);
        System.out.printf("%d ml of milk%n", cups * 50);
        System.out.printf("%d g of coffee beans%n", cups * 15);
    }

    public static int getNum(String message) {
        System.out.println(message);
        return new Scanner(System.in).nextInt();
    }
}