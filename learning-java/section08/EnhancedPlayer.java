
//////////////////////////////////////// MAIN CLASS //////////////////////////////////////////

public class Main {

    public static void main(String[] args) {

//        Player player = new Player();
//        player.name = "Tim";
//        player.health = 20;
//        player.weapon = "Sword";
//
//        int damage = 10;
//        player.loseHealth(damage);
//        System.out.println("Remaining health = " + player.healthRemaining());
//        player.health = 200;
//        player.loseHealth(11);
//        System.out.println("Remaining health = " + player.healthRemaining());

        EnhancedPlayer tim = new EnhancedPlayer("Tim", 200, "Sword");
        System.out.println("Initial health is " + tim.healthRemaining());
    }
}


//////////////////////////////////////// ENHANCED PLAYER //////////////////////////////////////

public class EnhancedPlayer {

    private String fullName;
    private int health;
    private String weapon;

    public EnhancedPlayer(String fullName) {
        this.fullName = fullName;
    }

    public EnhancedPlayer(String fullName, int health, String weapon) {
        this.fullName = fullName;
        if (health <= 0) {
            this.health = 1;
        } else if (health > 100) {
            this.health = 100;
        } else {
            this.health = health;
        }
        this.weapon = weapon;
    }

    public void loseHealth(int damage) {

        health = health - damage;
        if (health <= 0) {
            System.out.println("Player knocked out of game");
        }
    }

    public int healthRemaining() {
        return health;
    }

    public void restoreHealth(int extraHealth) {

        health = health + extraHealth;
        if (health > 100) {
            System.out.println("Player restored to 100%");
            health = 100;
        }
    }
}

////////////////////////////////// PLAYER ////////////////////////////////////////////////


public class Player {

    public String fullName;
    public int health;
    public String weapon;

    public void loseHealth(int damage) {

        health = health - damage;
        if (health <= 0) {
            System.out.println("Player knocked out of game");
        }
    }

    public int healthRemaining() {
        return health;
    }

    public void restoreHealth(int extraHealth) {

        health = health + extraHealth;
        if (health > 100) {
            System.out.println("Player restored to 100%");
            health = 100;
        }
    }
}


//////////////////////////////////////////////////////////////////////////////////////////
