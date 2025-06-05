////////////////////////////////////// MAIN CLASS //////////////////////////////////////////

public class Main {
    public static void main(String[] args) {

        SmartKitchen kitchen = new SmartKitchen();

//        kitchen.getDishWasher().setHasWorkToDo(true);
//        kitchen.getIceBox().setHasWorkToDo(true);
//        kitchen.getBrewMaster().setHasWorkToDo(true);
//
//        kitchen.getDishWasher().doDishes();
//        kitchen.getBrewMaster().brewCoffee();
//        kitchen.getIceBox().orderFood();

        kitchen.setKitchenState(true,false,true);
        kitchen.doKitchenWork();

    }


}


////////////////////////////// SMART KITCHEN //////////////////////////////////////////////

public class SmartKitchen {

// Methods here will determine what work needs to be done

    private CoffeeMaker brewMaster;
    private DishWasher dishWasher;
    private Refrigerator iceBox;

// Add a constructor so when I create a new smart kitchen,
//     I am just going to instantiate my own set of appliances
    // In this case I will not use parametric constructor from Code generator. I will create with no args.


    public SmartKitchen() {
        brewMaster = new CoffeeMaker();
        dishWasher = new DishWasher();
        iceBox = new Refrigerator();
    }

    public CoffeeMaker getBrewMaster() {
        return brewMaster;
    }

    public DishWasher getDishWasher() {
        return dishWasher;
    }

    public Refrigerator getIceBox() {
        return iceBox;
    }

// Now I am gonna to create 2 more methods that will hide some of the details from the calling code.
    // I will give the calling code a method to pass some boolean flags to my kitchen
    // If the kitchen has a smart appliance that cares about that flag< I will set that flag.
    // Basically below I have a single method that can set each of the appliance's has work to do boolean flags

    public void setKitchenState(boolean coffeeFlag, boolean fridgeFlag, boolean dishWasherFlag) {
        brewMaster.setHasWorkToDo(coffeeFlag);
        dishWasher.setHasWorkToDo(dishWasherFlag);
        iceBox.setHasWorkToDo(fridgeFlag);
    }

// Below the method hides what work will be done from the calling code, which does not  care about all the work

    public void doKitchenWork() {
        brewMaster.brewCoffee();
        iceBox.orderFood();
        dishWasher.doDishes();

    }

}
class CoffeeMaker {

    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void brewCoffee() {

        if (hasWorkToDo) {
            System.out.println("Brewing coffee");
            hasWorkToDo = false;
        }


    }
}
class DishWasher {

    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void doDishes() {

        if (hasWorkToDo) {
            System.out.println("Washing dishes");
            hasWorkToDo = false;
        }

    }
}

class Refrigerator {

    private boolean hasWorkToDo;


    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void orderFood(){
        if (hasWorkToDo) {
            System.out.println("Ordering food");
            hasWorkToDo = false;
        }

    }
}

////////////////////////////////////////////////////////////////////////////////////////
