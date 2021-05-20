import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scan = new Scanner(System.in);
        final CoffeeMachine coffeeMachine = new CoffeeMachine();

        coffeeMachine.start();
        while (coffeeMachine.on()) {
            coffeeMachine.run(scan.nextLine());
        }
    }
}