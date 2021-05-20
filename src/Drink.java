public enum Drink {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    final int water;
    final int milk;
    final int beans;
    final int money;

    Drink(int water, int milk, int beans, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.money = money;
    }
}