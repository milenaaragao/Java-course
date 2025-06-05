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

