///////////////////// MAIN CLASS //////////////////////////////////

package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;

record GroceryItem(String name, String type, int count) {

    public GroceryItem(String name) {
        this(name, "DAIRY", 1);
    }

 // I am going to add my own TOOSTRING method to this record above.
    // I am overriden the TOOSTRING method and returning a format string using string.format that has three specifiers.
    // first specifier is for integer, then 2 strings specifiers for name uppercase and type.

    @Override
    public String toString() {
        return String.format("%d %s in %s", count, name.toUpperCase(), type);
    }
}

public class Main {

    public static void main(String[] args) {

        GroceryItem[] groceryArray = new GroceryItem[3];
        groceryArray[0] = new GroceryItem("milk");
        groceryArray[1] = new GroceryItem("apples", "PRODUCE", 6);
        groceryArray[2] = new GroceryItem("oranges", "PRODUCE", 5);
        System.out.println(Arrays.toString(groceryArray));

        ArrayList objectList = new ArrayList();
        objectList.add(new GroceryItem("Butter"));
        objectList.add("Yogurt");

// adding items to the list.

        ArrayList<GroceryItem> groceryList = new ArrayList<>();
        groceryList.add(new GroceryItem("Butter"));
        groceryList.add(new GroceryItem("milk"));
        groceryList.add(new GroceryItem("oranges", "PRODUCE", 5));

// Next I am going to add an entry at the start of the list. --> i am using OVERLOADED VERSION of the add method.
        // index 0 as the first argument.

        groceryList.set(0, //set method to replace add method.
                new GroceryItem("apples", "PRODUCE", 6)); // this won;t be added to the end of my list
                                                               // but instead add it at index 0 moving all the elements
                                                               // so instead to start with butter item, it started with apples

        groceryList.remove(1); // here I removed milk from the list
        System.out.println(groceryList);
    }
}


//////////////////////////// more lists /////////////////////////////////////////

package dev.lpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MoreLists {
    // Starting with string array with some groceries I might buy
    public static void main(String[] args) {

        String[] items = {"apples", "bananas", "milk", "eggs"};        // item with four elements.
// to transform the array of string to a list of string:
        List<String> list = List.of(items); //return items I just created (METHOD ON LIST = FACTORY METHOD)
                                            // factory methods is where you can call a static method on a class and
                                            // return a new instance of sth back to us.
                                            // I am also using type string on the left side. now lists is not arraylist.
        System.out.println(list);

//creating an array list of string using the variable groceries and assigning a new instance of the array list to it.
        ArrayList<String> groceries = new ArrayList<>(list); //passing list an argument. ---- instanciation
                                                              // now this list is mutable.
        groceries.add("yogurt"); // add yogurt in the list.
        System.out.println(groceries);

 // the next array list can be mutable also.
        ArrayList<String> nextList = new ArrayList<>(List.of("pickles", "mustard", "cheese"));
        System.out.println(nextList);

// and there is another method called add all method which takes any list as an argument.

        groceries.addAll(nextList);
        System.out.println(groceries); // ADD ALL THE ITEMS


// retrieve an element in an ArrayList by its index - like the way to reference by its position in square brackets
        // for ARRAYLIST use the get method.
        // third item is index 2 (start from zero). so next code simply prints out the name of the third item.
        System.out.println("Third item = " + groceries.get(2));

// HOW TO SEARCH FOR AN ITEM IN THE LIST?
        // e.g = contains methods that returns a boolean. true meaning it found a match.

        if (groceries.contains("mustard")) { // METHOD CONTAINS = calls the class's overridden equals method.
            System.out.println("List contains mustard");
        } // return a message saying if mustard is in the list.


// arrayslists can have duplicate info.
        groceries.add("yogurt");
        System.out.println("first = " + groceries.indexOf("yogurt"));
        System.out.println("last = " + groceries.lastIndexOf("yogurt"));

// TO FIND ELEMENTS there are several methods we can use to remove elements from a list.
// remove by index OR remove by the elements value itself .
        System.out.println(groceries); // printing the full list.
        groceries.remove(1); // then remove second element in the list passing int value 1 to the remove method.
        System.out.println(groceries); // print list again
        groceries.remove("yogurt"); // remove yogurt from the list passing the string value
        System.out.println(groceries); // print list again

// option to remove more than one item at a time.
        groceries.removeAll(List.of("apples", "eggs")); //passing a temporary list with the elements I want to remove.
        System.out.println(groceries); //  print elements without those items above.

// another method to remove items. functionallity is opposite to remove all. remove elements from a list.
        groceries.retainAll(List.of("apples", "milk", "mustard", // but in this case retain those items mentioned
                "cheese")); // and remove the rest items from the list.
        System.out.println(groceries);

// CLEAR method remove all the items from a list.
        groceries.clear();
        System.out.println(groceries);
        System.out.println("isEmpty = " + groceries.isEmpty());

// TO ADD the items I mentioned I use this method below.
        groceries.addAll(List.of("apples", "milk", "mustard",
                "cheese"));
        groceries.addAll(Arrays.asList("eggs", "pickles", "mustard", "ham"));

// SORT methods.
        System.out.println(groceries);
        groceries.sort(Comparator.naturalOrder()); // COMPARATOR is needed to use SORT method.
        // strings from A t oZ. numbers from smallest to largest.
        System.out.println(groceries);
// for reverse results : from Z to A and from largest to smallest .
        groceries.sort(Comparator.reverseOrder());
        System.out.println(groceries);


        // GETTING  a list from an array, with a different method on arrayList.
        // overloaded method called TOARRAY
        // it takes a typed array as an argument.
        // this method return array the same size as the array I passed. and same type.

        var groceryArray = groceries.toArray(new String[groceries.size()]); // same size and type
        System.out.println(Arrays.toString(groceryArray)); // this method return an array of string.
        // there may be times I want to pass the data as an array to methods that accept arrays rather than lists.
    }





}

