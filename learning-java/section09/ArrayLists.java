package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;

// I will create a RECORD (instead of class) with grocery item. RECORD IS BEFORE MAIN METHOD CLASS
// I pass the fields I want for the record in the declaration's parentheses.

record GroceryItem(String name, String type, int count) {

// I am going to create a constructor considering that count will be always 1 and dairy. (default)
// The constructor takes just one field (name)
    // For a record I have to call the generated default constructor first with all 3 fields.
    // That's why THIS. caLL those three.

    public GroceryItem(String name) {
        this(name, "DAIRY", 1);
    }
}

// in the main method I create an array.

public class Main {

    public static void main(String[] args) {

        // calling name of a constructor and creating objects (instances)

        // declaring arrays with a specific type allows compile-time type checking
        // prevents runtime expections when instances assigned to arrays are not what they are expected to be
        GroceryItem[] groceryArray = new GroceryItem[3];
        groceryArray[0] = new GroceryItem("milk");
        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
        groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);
        System.out.println(Arrays.toString(groceryArray));

// now I am going to create an array list.
// if I do not specify the array list it's going to use the object class by default.

        ArrayList objectList = new ArrayList(); // arraylist is a class here.
        objectList.add(new GroceryItem("Butter")); // here I add a grocery item
        objectList.add("Yogurt"); // here I add a string with this add method call. // NOT INDICATED TO DO!!!!!!!!!

// the CORRECT WAY is bellow. Using angle brackets and specified the type for the array list. a grocery item record.
        ArrayList<GroceryItem> groceryList = new ArrayList<>(); // <> = DIAMOND OPERATOR. == used when JAVA can infer the type
        groceryList.add(new GroceryItem("Butter"));
    }
}
