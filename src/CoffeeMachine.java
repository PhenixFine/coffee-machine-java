public class CoffeeMachine {
    private CurrentState STATUS = CurrentState.EXIT;
    private CurrentState.BUYING BUY = CurrentState.BUYING.START;
    private CurrentState.FILLING FILL = CurrentState.FILLING.START;
    private int WATER = 400;
    private int MILK = 540;
    private int BEANS = 120;
    private int MONEY = 550;
    private int CUPS = 9;

    public void run(String input) {
        if (on()) {
            if (STATUS == CurrentState.START) STATUS = CurrentState.getState(input);
            switch (STATUS) {
                case START:
                    start();
                    return;
                case EXIT:
                    return;
                case BUY:
                    buyCoffee(input);
                    return;
                case FILL:
                    fillMachine(input);
                    return;
                case TAKE:
                    takeMoney();
                    return;
                case REMAINING:
                    inventory();
            }
        }
    }

    public void start() {
        if (STATUS != CurrentState.START) STATUS = CurrentState.START;
        print("Write action (buy, fill, take, remaining, exit):");
    }

    public boolean on() {
        return STATUS != CurrentState.EXIT;
    }

    private void buyCoffee(String order) {
        String msg = "\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:";
        switch (BUY) {
            case START:
                print(msg);
                BUY = CurrentState.BUYING.COMMAND;
                return;
            case COMMAND:
                if (order.length() == 1 && "123".contains(order)) {
                    getCoffee(Integer.parseInt(order));
                } else if (!"back".equalsIgnoreCase(order)) {
                    print(msg);
                    return;
                }
        }
        print("");
        BUY = CurrentState.BUYING.START;
        start();
    }

    private void getCoffee(int choice) {
        Drink drink = (choice == 1) ? Drink.ESPRESSO : (choice == 2) ? Drink.LATTE : Drink.CAPPUCCINO;
        String outOf = (CUPS < 1) ? "disposable cups" : (WATER < drink.water) ? "water"
                : (MILK < drink.milk) ? "milk" : (BEANS < drink.beans) ? "coffee beans" : "";

        if (outOf.length() == 0) {
            print("I have enough resources, making you a coffee!");
            WATER -= drink.water;
            MILK -= drink.milk;
            BEANS -= drink.beans;
            MONEY += drink.money;
            CUPS--;
        } else print(String.format("Sorry, not enough %s!", outOf));
    }

    private void fillMachine(String number) {
        if (number == null || (FILL != CurrentState.FILLING.START && !isNumber(number))) return;
        String message = "Write how many %s of %s do you want to add:";
        int num = (FILL != CurrentState.FILLING.START) ? Integer.parseInt(number) : 0;

        switch (FILL) {
            case START:
                FILL = CurrentState.FILLING.WATER;
                print(String.format("\n" + message, "ml", "water"));
                return;
            case WATER:
                WATER += num;
                FILL = CurrentState.FILLING.MILK;
                print(String.format(message, "ml", "milk"));
                return;
            case MILK:
                MILK += num;
                FILL = CurrentState.FILLING.BEANS;
                print(String.format(message, "grams", "coffee beans"));
                return;
            case BEANS:
                BEANS += num;
                FILL = CurrentState.FILLING.CUPS;
                print(String.format(message, "disposable cups", "coffee"));
                return;
            case CUPS:
                CUPS += num;
                FILL = CurrentState.FILLING.START;
                print("");
                start();
        }
    }

    private void takeMoney() {
        print(String.format("%nI gave you $%d%n", MONEY));
        MONEY = 0;
        start();
    }

    private void inventory() {
        print(String.format("%nThe coffee machine has:%n%d ml of water%n%d ml of milk%n%d g of coffee beans%n" +
                "%d disposable cups%n$%d of money%n", WATER, MILK, BEANS, CUPS, MONEY));
        start();
    }

    private boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void print(String message) {
        System.out.println(message);
    }
}