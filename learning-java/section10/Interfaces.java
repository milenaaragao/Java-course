////////////////////// main /////////////////////////////

// context of interfaces: There are a number of situations in software engineering
// when it is important for disparate groups of programmers to agree to a "contract"
// that spells out how their software interacts. Each group should be able to write their code
// without any knowledge of how the other group's code is written. Generally speaking, interfaces are such contracts.

// For example, imagine a futuristic society where computer-controlled robotic cars transport passengers
// through city streets without a human operator. Automobile manufacturers write software (Java, of course)
// that operates the automobile—stop, start, accelerate, turn left, and so forth. Another industrial group,
// electronic guidance instrument manufacturers, make computer systems that receive GPS (Global Positioning System)
// position data and wireless transmission of traffic conditions and use that information to drive the car.

//The auto manufacturers must publish an industry-standard interface that spells out in detail what methods can
// be invoked to make the car move (any car, from any manufacturer). The guidance manufacturers can then write
// software that invokes the methods described in the interface to command the car. Neither industrial group needs
// to know how the other group's software is implemented. In fact, each group considers its software highly
// proprietary and reserves the right to modify it at any time, as long as it continues
// to adhere to the published interface.

// In the Java programming language, an interface is a reference type, similar to a class,
// that can contain only constants, method signatures, default methods, static methods, and nested types.
// Method bodies exist only for default methods and static methods. Interfaces cannot be instantiated—they c
// an only be implemented by classes or extended by other interfaces.


import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Bird bird = new Bird(); //new instance
        Animal animal = bird; // assign bird to var type animal
        FlightEnabled flier = bird; //assign in this interface
        Trackable tracked = bird; //assign bird in this interface

        animal.move();

        // the type determines which methods I can call in my code. the compiler only cares about the declared type
//        flier.move();
//       tracked.move();
//
//        flier.takeOff();
//        flier.fly();
//        tracked.track();
//        flier.land();

        inFlight(flier);
        inFlight(new Jet());
        Trackable truck = new Truck();
        truck.track(); // truck can be track because it runs the method trackable

        // testing constant fields from animal.
        double kmsTraveled = 100;
        double milesTraveled = kmsTraveled * FlightEnabled.KM_TO_MILES;
        System.out.printf("The truck traveled %.2f km or %.2f miles%n",
                kmsTraveled, milesTraveled);



        //linkedList or ArrayList is not the best option when dealing with interfaces.
        LinkedList<FlightEnabled> fliers = new LinkedList<>();
        fliers.add(bird);

        //it is recommended to use the interface list type for the declared variable.
        // this case = declared variable = list of flightenabled element and not an arraylist.
        //this one is better than the first.
        List<FlightEnabled> betterFliers = new LinkedList<>();
        betterFliers.add(bird);

        triggerFliers(fliers);
        flyFliers(fliers);
        landFliers(fliers);

        triggerFliers(betterFliers);
        flyFliers(betterFliers);
        landFliers(betterFliers);


    }


    private static void inFlight(FlightEnabled flier) { //arg, is sth that flies

        flier.takeOff(); //calling this method
        flier.fly(); // calling this method
        if (flier instanceof Trackable tracked) {
            tracked.track(); //i want to track it now if it is actually trackable
            //to test I used instanceof operator with pattern matching
            // this is testing an interface type trackable
            //if object is a class that implements trackable then a local variable is set up names tracked
            //thats why i can call the track method on that variable
        }
        flier.land();

    }

    private static void triggerFliers(List<FlightEnabled> fliers) {

        for (var flier : fliers) {
            flier.takeOff();
        }
    }

    private static void flyFliers(List<FlightEnabled> fliers) {

        for (var flier : fliers) {
            flier.fly();
        }
    }

    private static void landFliers(List<FlightEnabled> fliers) {

        for (var flier : fliers) {
            flier.land();
        }
    }
}


//////////////////////// test //////////////////////////

public class Test {

    public static void main(String[] args) {

        inFlight(new Jet()); // new instance of jet class.
        // but i need to override this in the jet method.

        OrbitEarth.log("Testing" + new Satellite()); // new instance of satellite
        // the date and type are printed out FORMAT = LONG FORM

        orbit(new Satellite());

    }

    private static void inFlight(FlightEnabled flier) {

        flier.takeOff();
        flier.transition(FlightStages.LAUNCH); //insert a call to the transition method between takeoff and fly.
        flier.fly();
        if (flier instanceof Trackable tracked) {
            tracked.track();
        }
        flier.land();
    }

    private static void orbit(OrbitEarth flier) {
// the only class that implement orbitearth is satellite
        //it overrode the methods
        flier.takeOff();
        flier.fly();
        flier.land();
    }
}


/////////////////////// Animal ///////////////////////

//ADDING ENUM
// enums can be used inside interfaces classes. but CANNOT extend classes.
enum FlightStages implements Trackable {GROUNDED, LAUNCH, CRUISE, DATA_COLLECTION;
//this enum describes four stages of a satellite launch
    // "I really want each of these stages to be trackable
    //but for that I need to create the track method.
    @Override
    public void track() {

        if (this != GROUNDED) {
            System.out.println("Monitoring " + this);
        }
    }

    public FlightStages getNextStage() { // with this code I've purposely avoided using any of the enum constant values
                                        // if the enums constant changes or more are included or some are moved,
                                        // I still want this method to work.

        FlightStages[] allStages = values(); // so firstly I created a local variable ALLSTAGES assigned for enum values.
        return allStages[(ordinal() + 1) % allStages.length]; //return nextstage which is the stage ordinal +1 in
                                                              // in every instance
        //the expression uses % modulus operator. returning ordinal +1 in every case.
        //in this case will be 4 over 4 as the constants lists stands now with a remainder of zero and giver the 1st stage
        // I am cycling through the stages .
    }
}


// I can also create a RECORD inside a interface class.
// normally records wouldn't have class bodies. but because in this case I am implementing flightenabled
// this record needs to implement flightenabled abstract methods.
record DragonFly(String name, String type) implements FlightEnabled {

    // i can add special functionality , this means it can be treated like any other flightenabled object.
    @Override
    public void takeOff() {

    }

    @Override
    public void land() {

    }

    @Override
    public void fly() {

    }
}

class Satellite implements OrbitEarth { //new class

    FlightStages stage = FlightStages.GROUNDED;

    // method needed to be defined as a public.
    public void achieveOrbit() {
        transition("Orbit achieved!");
    }

    // I always need to implement all the methods from the flightenabled because it is an interface from that claSS.
    @Override
    public void takeOff() {
        transition("Taking off");
    }

    @Override
    public void land() {
        transition("Landing");
    }

    @Override
    public void fly() {
        transition("Data collection while orbiting");
    }

    public void transition(String description) { //method parameter is just a description that gets printed out.
        System.out.println(description);
        stage = transition(stage);
        stage.track();

    }
}

interface OrbitEarth extends FlightEnabled { //new interface

    void achieveOrbit(); //one abstract method

    // I am going to add a method to log data to the console in a flexible way
    //HELPER METHOD
    static void log(String description) { // I didn't specify public
        var today = new java.util.Date(); // there are two classes called DATE in java.
                                            // in java.util and java.sql.
        System.out.println(today + ": " + description);

    }

    private void logStage(FlightStages stage, String description) {
        description = stage + ": " + description; //pre-fixing the stage to the description that was passed
        log(description); // I can only use  a private method from another private method or a default method

    }

    @Override
    default FlightStages transition(FlightStages stage) {
        FlightStages nextStage = FlightEnabled.super.transition(stage); //assign what come back from FlightEnabled
                                                                            // to a local variable NextStage.
        logStage(stage, "Beginning Transition to " + nextStage); // print passing the current stage as the first
                                                                            // paramenter to the private method
                                                                            // print and rturn nextStage.
        return nextStage;
    }
}

interface FlightEnabled {

    // adding fields (both doubles) - gone against the naming convention + uppercase letters.
    // why did I do this ?
    // when turns out any fields declared on an interface , are not really instance fields.
    //they are implicitly public, static and final . which means = they are CONSTANTS

    // when declaring FIELDS in an interface , we are creating the FINAL STATIC field.

    // constant in java is a variable that cannot be changed.

    //a constant variable is a final variable of primitive type or type string initialized with a constant expression
    //static constant means we access it via the type name.
    //examples: INTEGER.MAX_VALUE field.
    double MILES_TO_KM = 1.60934;
    double KM_TO_MILES = 0.621371;

    void takeOff();
    void land();
    void fly();


// SUPPOSE I learned about new requirements for flightEnabled objects. so A NEW METHOD is needed. called transition
// it uses the flight stage enum types.
// I am going to use a default method:
    //this means I really want each class to use this interface and override this method before they actually use
    // use it in the code.
    default FlightStages transition(FlightStages stage) {
//        System.out.println("transition not implemented on " + getClass().getName());
//        return null;

        FlightStages nextStage = stage.getNextStage();
        System.out.println("Transitioning from " + stage + " to " + nextStage);
        return nextStage;
    }

}


interface Trackable {

    void track();
}


public abstract class Animal {

    public abstract void move();
}

///////////////////// Jet /////////////////////////

// two interfaces
//with same methods from bird.

    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName() + " is taking off");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName() + " is landing");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName() + " is flying");
    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    }

    @Override
//    public FlightStages transition(FlightStages stage) { //default overridden implementation.
//        System.out.println(getClass().getSimpleName() + " transitioning");
//        return FlightEnabled.super.transition(stage); // this SUPER means the default method on the Interface gets called
//    } //

    public FlightStages transition(FlightStages stage) {
        System.out.println(getClass().getSimpleName() + " transitioning");
        return FlightEnabled.super.transition(stage); // called SUPER because this method called transition is in superclass
    }
}

///////////////////////// truck ///////////////////////

public class Truck implements Trackable {

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
    } //using just one method. (what they have in common)
    //(between diff classes.
}

////////////////////// bird /////////////////////////

public class Bird extends Animal implements FlightEnabled, Trackable {
//bringing both interfaces from class animal.



        @Override
        public void move() {
            System.out.println("Flaps wings");
        }

        @Override
        public void takeOff() {
            System.out.println(getClass().getSimpleName() + " is taking off");
        }

        @Override
        public void land() {
            System.out.println(getClass().getSimpleName() + " is landing");
        }

        @Override
        public void fly() {
            System.out.println(getClass().getSimpleName() + " is flying");
        }

        @Override
        public void track() {
            System.out.println(getClass().getSimpleName() + "'s coordinates recorded");
        }
}


///////////////////////////////////////////////////////////////////////


