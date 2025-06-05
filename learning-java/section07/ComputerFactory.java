
/////////////////////////////// MAIN CLASS ////////////////////////////////////

public class Main {

    public static void main(String[] args) {

// Now I need to create the objects to test my code


        ComputerCase theCase = new ComputerCase("2208", "Dell", "240");

        Monitor theMonitor = new Monitor("27inch Beast", "Acer", 27, "2540 x 1440");

        MotherBoard theMotherBoard = new MotherBoard("BJ-200", "ASUS",
                4, 6, "v2.44");

        PersonalComputer thePC = new PersonalComputer("2208", "Dell",
                theMonitor, theMotherBoard, theCase);

// How to make it do anything? I can do this by telling one of its parts to do sth. So below I am accessing
// functions in other classes the case, the motherboard and monitor.
// But I am accessing them through the PC object. THIS IS WHAT COMPOSITION IS

        thePC.getMonitor().drawPixelAt(10,10,"red");
        thePC.getMotherBoard().loadProgram("Windows OS");
        thePC.getComputerCase().pressPowerButton();


    }

// How to make it do anything? I can do this by telling one of its parts to do sth.

}


////////////////////////////// PRODUCT JAVA /////////////////////////////////

public class Product {

    private String model;
    private String manufacturer;
    private int width;
    private int height;
    private int depth;

    public Product(String model, String manufacturer) {
        this.model = model;
        this.manufacturer = manufacturer;
    }
}


// create my three subclasses EXTENDED to product
// monitor , motherboard and computerCase are TYPES of product.


class Monitor extends Product {

    private int size;
    private String resolution;

    public Monitor(String model, String manufacturer) {
        super(model, manufacturer);
    }

    public Monitor(String model, String manufacturer, int size, String resolution) {
        super(model, manufacturer);
        this.size = size;
        this.resolution = resolution;
    }

    public void drawPixelAt(int x, int y, String color) {
        System.out.println(String.format("Drawing pixel at %d, %d in color %s", x, y, color));

    }

}

class MotherBoard extends Product {

    private int ramSlots;
    private int cardSlots;
    private String bios;

    public MotherBoard(String model, String manufacturer) {
        super(model, manufacturer);
    }

    public MotherBoard(String model, String manufacturer, int ramSlots, int cardSlots, String bios) {
        super(model, manufacturer);
        this.ramSlots = ramSlots;
        this.cardSlots = cardSlots;
        this.bios = bios;
    }

    public void loadProgram(String programName) {
        System.out.println("Program " + programName + " is now loading...");
    }
}

class ComputerCase extends Product {

    private String powerSupply;

    public ComputerCase(String model, String manufacturer) {
        super(model, manufacturer);
    }

    public ComputerCase(String model, String manufacturer, String powerSupply) {
        super(model, manufacturer);
        this.powerSupply = powerSupply;
    }

    public void pressPowerButton() {
        System.out.println("Power button pressed");


    }
}

//////////////////////////////// Personal Computer Class //////////////////////////

public class PersonalComputer extends Product {

// to build this class  I am going to use composition

    private Monitor monitor;
    private MotherBoard motherBoard;
    private ComputerCase computerCase;

    public PersonalComputer(String model, String manufacturer, Monitor monitor,
                            MotherBoard motherBoard, ComputerCase computerCase) {
        super(model, manufacturer);
        this.monitor = monitor;
        this.motherBoard = motherBoard;
        this.computerCase = computerCase;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public MotherBoard getMotherBoard() {
        return motherBoard;
    }

    public ComputerCase getComputerCase() {
        return computerCase;
    }
}


////////////////////////////////////////////////////////////////////////////////////
