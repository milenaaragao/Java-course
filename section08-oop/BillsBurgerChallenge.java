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
