
//////////////////////////////// MAIN CLASS ////////////////////////////////

public class Main {

    public static void main(String[] args) {

        // I am creating a drink that is a coke with a base price of 1.50

//        Item coke = new Item("drink", "coke", 1.50);
//        coke.printItem(); // price of the medium drink
//        coke.setSize("LARGE"); // then I change the size for large
//        coke.printItem();
//
//        Item avocado = new Item("Topping", "avocado", 1.50);
//        avocado.printItem();

//        Burger burger = new Burger("regular", 4.00);
//        burger.addTopping("BACON", "CHEESE", "MAYO");
//        burger.printItem();

//        MealOrder regularMeal = new MealOrder();
//        regularMeal.addBurgerToppings("BACON", "CHEESE", "MAYO");
//        regularMeal.setDrinkSize("LARGE");
//        regularMeal.printItemizedList();
//
//        MealOrder secondMeal = new MealOrder("turkey", "7-up", "chili");
//        secondMeal.addBurgerToppings("LETTUCE", "CHEESE", "MAYO");
//        secondMeal.setDrinkSize("SMALL");
//        secondMeal.printItemizedList();
//
//        MealOrder thirdMeal = new MealOrder("beef", "orange juice", "fries");
//        thirdMeal.addBurgerToppings("CHEESE", "MAYO", "BACON");
//        thirdMeal.setDrinkSize("MEDIUM");
//        thirdMeal.printItemizedList();

        MealOrder deluxeMeal = new MealOrder("deluxe", "7-up", "chili");
        deluxeMeal.addBurgerToppings("AVOCADO", "BACON", "LETTUCE", "CHEESE", "MAYO");
        deluxeMeal.setDrinkSize("SMALL");
        deluxeMeal.printItemizedList();


    }


}


//////////////////////////////// MEAL ORDER CLASS ////////////////////////////////


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

//////////////////////////////// BURGER CLASS ////////////////////////////////

public class Burger extends Item {

    private Item extra1;
    private Item extra2;
    private Item extra3;

// Containers = a way to group the same kind of items together (later course)

// Now I need to create a constructor with none option, but still add code to initialise the super class.
// Instead of including extras in the constructor, I will include a method to add toppings,
// because these are probably going to be something specific for each customer.
// So I will remove type of the constructor and add Burger to the super call.

    public Burger(String name, double price) {
        super("Burger", name, price);
    }

// Now I am going to override a few methods from the item class
// I am selecting just GETNAME and GETADJUSTEDPRICE


    @Override
    public String getName() {
        return super.getName() + " BURGER"; // I added Burger because the type of burger will
                                                // indicate the specific type of burger.
    }

    @Override
    public double getAdjustedPrice() { // here I need the price to include the toppings (using ternary operator)
        return getBasePrice() +
                ((extra1 == null) ? 0 : extra1.getAdjustedPrice()) +
                ((extra2 == null) ? 0 : extra2.getAdjustedPrice()) +
                ((extra3 == null) ? 0 : extra3.getAdjustedPrice());
    } // basically each extra topping will get added if it has a price.

// Now I need to add a method to add toppings to be able to do some tests.
// I am going to let a string be passed to this method, containing the topping to be added.
// The code will create the right kind of item, based on the value of the string.

// I also need another method to determine the price based on the name of topping that is passed. (I will create that first)

    public double getExtraPrice(String toppingName) {
        return switch (toppingName.toUpperCase()) {
            case "AVOCADO" , "CHEESE" -> 1.0;
            case "BACON", "HAM", "SALAMI" -> 1.5;
            default -> 0;
        };

    }

    // now I create add topping method.

    public void addTopping(String extra1, String extra2, String extra3) {

        this.extra1 = new Item("TOPPING", extra1,
                getExtraPrice(extra1));
        this.extra2 = new Item("TOPPING", extra2,
                getExtraPrice(extra2));
        this.extra3 = new Item("TOPPING", extra3,
                getExtraPrice(extra3));
    }

// with those two methods above I can see if I can create a burger and add some toppings, and print out its price.

// Now I am going to add a method to burger that itemized the extra toppings.
// So first print out the base price of the burger,
    // then if extra1 is not full, I will print that out via a call to its print item method.

    public void printItemizedList() {
        printItem("BASE BURGER", getBasePrice());
        if (extra1 != null) {
            extra1.printItem();
        }
        if (extra2 != null) {
            extra2.printItem();
        }
        if (extra3 != null) {
            extra3.printItem();
        }    // I am gonna to add this to the burger print item method

    }

    @Override
    public void printItem() {
        printItemizedList();
        System.out.println("-".repeat(30));
        super.printItem();
    }
}


//////////////////////////////// DELUXEBURGER CLASS ////////////////////////////////

public class DeluxeBurger extends Burger {

    Item deluxe1;
    Item deluxe2;

    // constructor without DELUXE attributes
    public DeluxeBurger(String name, double price) {
        super(name, price);
    }

    // I want to overload add toppings with two more items (five total)

    public void addToppings(String extra1, String extra2, String extra3,
                            String extra4, String extra5) {
        super.addTopping(extra1, extra2, extra3);
        deluxe1 = new Item("TOPPING", extra4, 0);
        deluxe2 = new Item("TOPPING", extra5, 0);
    }

    @Override
    public void printItemizedList() {
        super.printItemizedList();
        if (deluxe1 != null) {
            deluxe1.printItem();
        }
        if (deluxe2 != null) {
            deluxe2.printItem();
        }
    }

    @Override
    public double getExtraPrice(String toppingName) {
        return 0;
    }


}

//////////////////////////////// ITEM CLASS ////////////////////////////////

public class Item {

// Fields defined - encapsulation method

    private String type;
    private String name;
    private double price;
    private String size = "MEDIUM";
    // I set as a default MEDIUM size so price does not change in this case

// Now I can have a constructor including all the fields except size.
// I will change the constructor to make both the TYPE and NAME of the item UPPERCASE

    public Item(String type, String name, double price) {
        this.type = type.toUpperCase();
        this.name = name.toUpperCase();
        this.price = price;
    }

// I am going to add GETTERS  for name and price fields.
// Here I will include the size of the item.
// In the code below I am saying if the type is a side or drink.
    // return the size which I am expecting to be small, medium or large.

    public String getName() {

        if (type.equals("SIDE") || type.equals("DRINK")) {
            return size + " " + name;
        }

        return name;
    }

// For more clarity I want to getter get price to actually be get BASE PRICE

    public double getBasePrice() {
        return price;
    }

// now I need a method to get the adjusted price of an item.
// Because I am not subclassing the item class expect for burger
// I want to customize what happens for the other types on this method.
// So next code adjusts pricing for drinks and sides

    public double getAdjustedPrice() {
        return switch (size) {
            case "SMALL" -> getBasePrice() - 0.5;
            case "LARGE" -> getBasePrice() + 1;
            default -> getBasePrice();
        };
    }

// next I want a setter for the size attribute. In this case the employees will be able to adjust the size

    public void setSize(String size) {
        this.size = size;
    }

// Lastly -> add a method to print the item name and the item's price (adjusted price)
// In this case I want everything to be printed out all the items, burger, its toppings, and subtotals and totals.
// First I am going to create a static method in a format string
// (so any code can call this method, passing a label and price and it will get formatted the same).

    // %20s --> s is for a string. --> reserving 20 characters for the first argument --> passing name to that
    // %6.2f%n --> f is for a floating point number --> reserving 6 characters up to the decimal point
    // --> and I want a precision of 2, meaning I want 2 decimal places

// This method can be called from anywhere.

    public static void printItem(String name, double price) {
        System.out.printf("%20s:%6.2f%n", name, price);
    }


    public void printItem() {
        printItem(getName(), getAdjustedPrice());
    }

// These methods will get overridden for any subclass. I can test in the main method.

}


///////////////////////////////////////////////////////////////////////////////////////
