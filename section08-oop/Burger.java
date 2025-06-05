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
