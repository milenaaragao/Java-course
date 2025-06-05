public class MealOrder {

// I am going to use COMPOSITION in this class.

    private Burger burger;
    private Item drink;
    private Item side;

// to add the constructor I will enter manually because I do not want the calling code to pass objects,
// just string  describing what the items in the meal are
    // I just want a burger type (regular or deluxe). A drink type and side type.

    // My first constructor has no args. with this constructor the calling code can quickly set up
    // a default meal with a regular burger, medium fried and medium coke.

    public MealOrder() {
        this("regular", "coke", "fries");
    }


    // Below I will construct the meal, that is the parts within this constructor


    public MealOrder(String burgerType, String drinkType, String sideType) {
        if (burgerType.equalsIgnoreCase("deluxe")) {
            this.burger = new DeluxeBurger(burgerType, 8.5);

        } else {
            this.burger = new Burger(burgerType, 4.0);
        }


        this.burger = new Burger(burgerType, 4.0);
        this.drink = new Item("drink", drinkType, 1.00);
        this.side = new Item("side", sideType, 1.50);
    }

    // next method gets the total price of the full meal

    public double getTotalPrice() {

        if (burger instanceof DeluxeBurger) {
            return burger.getAdjustedPrice();
        }
        return side.getAdjustedPrice() + drink.getAdjustedPrice() +
            burger.getAdjustedPrice();
    }

    public void printItemizedList() {
        burger.printItem();
        if (burger instanceof DeluxeBurger) {
            Item.printItem(drink.getName(), 0);
            Item.printItem(side.getName(), 0);

        } else {

        drink.printItem();
        side.printItem();
        }
        System.out.println("-".repeat(30));
        Item.printItem("TOTAL PRICE", getTotalPrice());
    }

    public static void printItem(String name, double price) {
        System.out.printf("%20s:%6.2f%n", name, price);
    }

// because everything is really an item, I can call the print item method on all the parts of the meal
    // I can also use that static method on item to include a total price after I print another 30 dashes
    // to separate the total from the itemized list.

    // Now the methods will allow me to add toppings and sizes.

    public void addBurgerToppings(String extra1, String extra2, String extra3) {
        burger.addTopping(extra1, extra2, extra3);
    }

    public void addBurgerToppings(String extra1, String extra2, String extra3,
                                  String extra4, String extra5) {
        if (burger instanceof DeluxeBurger db) {
            db.addToppings(extra1, extra2, extra3, extra4, extra5);
        } else {
            burger.addTopping(extra1, extra2, extra3);
        }
    }
    public void setDrinkSize(String size) {
        drink.setSize(size);
    }





}
