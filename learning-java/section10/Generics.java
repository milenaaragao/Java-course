/////////////////// main ////////////////////////

package dev.lpa;


// i am using interface here because my BaseballPlayer is a record and
//and records can implement interfaces but cannot extend classes.
// I am going to change my interface to have one abstract method
interface Player {

    String name(); //method without a method body HAS IMPLICITLY  public and static interface
}



record BaseballPlayer(String name, String position) implements Player {} // here I am using an interface over abstract class.
record FootballPlayer(String name, String position) implements Player {}
record VolleyballPlayer(String name, String position) implements Player {} // in real application, diff. player types
                                                                // would probably have diff sets of fields.

public class Main {

    public static void main(String[] args) {

        // this code will use the new phillies and astros teams.

        var philly = new Affiliation("city", "Philadelphia, PA", "US");

        BaseballTeam phillies1 = new BaseballTeam("Philadelphia Phillies");
        BaseballTeam astros1 = new BaseballTeam("Houston Astros");
       //code to score result:
        scoreResult(phillies1, 3, astros1, 5);

        SportsTeam phillies2 = new SportsTeam("Philadelphia Phillies");
        SportsTeam astros2 = new SportsTeam("Houston Astros");
        //code to score result:
        scoreResult(phillies2, 3, astros2, 5);

        Team<BaseballPlayer, Affiliation> phillies = new Team<>("Philadelphia Phillies");
        Team<BaseballPlayer, Affiliation> astros = new Team<>("Houston Astros");
        //code to score result:
        scoreResult(phillies, 3, astros, 5);


        //using type VAR which infers the type as BaseballPlayer
        var harper = new BaseballPlayer("B Harper", "Right Fielder");
        var marsh = new BaseballPlayer("B Marsh", "Right Fielder");
        phillies.addTeamMember(harper);
        phillies.addTeamMember(marsh);
        var guthrie = new BaseballPlayer("D Guthrie", "Central Fielder");
        phillies.addTeamMember(guthrie);
        phillies.listTeamMembers();


        //create instances with football player:

        SportsTeam af1 = new SportsTeam("Adelaide Crows");
        Team<FootballPlayer, Affiliation> afc = new Team<>("Adelaide Crows");
        var tex = new FootballPlayer("Tex Walker", "Centre half forward");
        afc.addTeamMember(tex);
        afc.listTeamMembers();

        var rory = new FootballPlayer("Rory Laird", "Midfield");
        afc.addTeamMember(rory);
        afc.listTeamMembers();


        // Now I am creating new generic instances but TYPE STRING this time

        Team<VolleyballPlayer, Affiliation> adelaide = new Team<>("Adelaide Storm");
        adelaide.addTeamMember(new VolleyballPlayer("N Roberts", "Setter"));
        adelaide.listTeamMembers();

        var canberra = new Team<VolleyballPlayer, Affiliation>("Canberra Heat");
        canberra.addTeamMember(new VolleyballPlayer("B Black", "Opposite"));
        canberra.listTeamMembers();
        scoreResult(canberra, 0, adelaide, 1);

//        Team<Integer> melbourne = new Team<>("Melbourne Vipers");
    }

    // method to score the result
    //overloaded methods ** same name but diff parameters and args.

    public static void scoreResult(BaseballTeam team1, int t1_score,
                                   BaseballTeam team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }


    public static void scoreResult(SportsTeam team1, int t1_score,
                                   SportsTeam team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }

    public static void scoreResult(Team team1, int t1_score,
                                   Team team2, int t2_score) {

        String message = team1.setScore(t1_score, t2_score);
        team2.setScore(t2_score, t1_score);
        System.out.printf("%s %s %s %n", team1, message, team2);
    }
}


////////////// team /////////////////

package dev.lpa;


// CREATING MY FIRST GENERIC CLASS


import java.util.ArrayList;
import java.util.List;

// creating record for affiliation

record Affiliation(String name, String type, String countryCode) {

    @Override
    public String toString() {
        return name + " (" + type + " in " + countryCode + ")";
    }
}

public class Team<T extends Player, S> { //generic type // added nother type S - a second type parameter can be S, U OR V
                                        // i am using S because I don't wanna confuse it with static method parameter
                                        // this particular type is going to represent the city that the team is affiliated


    //encapsulation
    private String teamName;
    private List<T> teamMembers = new ArrayList<>(); //using generic interface and generic class
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;
    private S affiliation;


    //constructor with just team name
    public Team(String teamName) {
        this.teamName = teamName;
    }

    //add a constructor that included affiliation
    public Team(String teamName, S affiliation) {
        this.teamName = teamName;
        this.affiliation = affiliation;
    }

    public void addTeamMember(T t) { //lowercase t for parameter to not be confused

        if (!teamMembers.contains(t)) { //if team member does not contain T , SO ADD T.
            teamMembers.add(t);
        }
    } // since T is a record , and record come with an implicit equals method, this method can test
    // the equality of all the record's attributes

    public void listTeamMembers() { // going to use array list functionality

        System.out.print(teamName + " Roster:");
        System.out.println((affiliation == null ? "" : "AFFILIATION: " + affiliation));
        for (T t : teamMembers) {
            System.out.println(t.name()); //calling using name accessor
        }

    } // I can pass that list directly to the println statement
    // looping to print each in one line

    public int ranking() { // method to rank teams
        return (totalLosses * 2) + totalTies + 1;
    } // the team with most win is ranked number 1. I will make losses count more
    //multiplying them by 2, then add ties, then add one to ties. so that the highest rank is never higher than 1
    //this method is just used to determine how well one team is doing compared to another

    public String setScore(int ourScore, int theirScore) { // two args.

        String message = "lost to";
        if (ourScore > theirScore) { //check if ourScore is greater them other team's score.
            totalWins++; // if is, increment total wins
            message = "beat"; // returns the message beat from this method
        } else if (ourScore == theirScore) { //check if is equal
            totalTies++; //if it is true, increment total Ties.
            message = "tied";
        } else { // to handle a loss.
            totalLosses++;
        }

        return message;
//overall, this method just figures out which variable to increase, totalWins, totalTies, or TotalLosses
    }


    // to print the team name with its rank.
    @Override
    public String toString() {
        return teamName + " (Ranked "  + ranking() + ")";
    }
}



//////////////////// sports team //////////////////////

package dev.lpa;

import java.util.ArrayList;
import java.util.List;

public class SportsTeam {


    //encapsulation
    private String teamName;
    private List<Player> teamMembers = new ArrayList<>(); //using generic interface and generic class
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;

    //constructor with just team name
    public SportsTeam(String teamName) {
        this.teamName = teamName;
    }

    public void addTeamMember(Player player) {

        if (!teamMembers.contains(player)) { //if team member does not contain player , SO ADD player.
            teamMembers.add(player);
        }
    } // since Player is a record , and record come with an implicit equals method, this method can test
    // the equality of all the record's attributes

    public void listTeamMembers() { // going to use array list functionality

        System.out.println(teamName + " Roster:");
        System.out.println(teamMembers);
    } // I can pass that list directly to the println statement.

    public int ranking() { // method to rank teams
        return (totalLosses * 2) + totalTies + 1;
    } // the team with most win is ranked number 1. I will make losses count more
    //multiplying them by 2, then add ties, then add one to ties. so that the highest rank is never higher than 1
    //this method is just used to determine how well one team is doing compared to another

    public String setScore(int ourScore, int theirScore) { // two args.

        String message = "lost to";
        if (ourScore > theirScore) { //check if ourScore is greater them other team's score.
            totalWins++; // if is, increment total wins
            message = "beat"; // returns the message beat from this method
        } else if (ourScore == theirScore) { //check if is equal
            totalTies++; //if it is true, increment total Ties.
            message = "tied";
        } else { // to handle a loss.
            totalLosses++;
        }

        return message;
//overall, this method just figures out which variable to increase, totalWins, totalTies, or TotalLosses
    }


    // to print the team name with its rank.
    @Override
    public String toString() {
        return teamName + " (Ranked "  + ranking() + ")";
    }
}


///////////////////////// baseball team ////////////////////////

package dev.lpa;

import java.util.ArrayList;
import java.util.List;

public class BaseballTeam {


    //encapsulation
    private String teamName;
    private List<BaseballPlayer> teamMembers = new ArrayList<>(); //using generic interface and generic class
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalTies = 0;

    //constructor with just team name
    public BaseballTeam(String teamName) {
        this.teamName = teamName;
    }

    public void addTeamMember(BaseballPlayer player) {

        if (!teamMembers.contains(player)) { //if team member does not contain player , SO ADD player.
            teamMembers.add(player);
        }
    } // since baseballplayer is a record , and record come with an implicit equals method, this method can test
    // the equality of all the record's attributes

    public void listTeamMembers() { // going to use array list functionality

        System.out.println(teamName + " Roster:");
        System.out.println(teamMembers);
    } // I can pass that list directly to the println statement.

    public int ranking() { // method to rank teams
        return (totalLosses * 2) + totalTies + 1;
    } // the team with most win is ranked number 1. I will make losses count more
    //multiplying them by 2, then add ties, then add one to ties. so that the highest rank is never higher than 1
    //this method is just used to determine how well one team is doing compared to another

    public String setScore(int ourScore, int theirScore) { // two args.

        String message = "lost to";
        if (ourScore > theirScore) { //check if ourScore is greater them other team's score.
            totalWins++; // if is, increment total wins
            message = "beat"; // returns the message beat from this method
        } else if (ourScore == theirScore) { //check if is equal
            totalTies++; //if it is true, increment total Ties.
            message = "tied";
        } else { // to handle a loss.
            totalLosses++;
        }

        return message;
//overall, this method just figures out which variable to increase, totalWins, totalTies, or TotalLosses
    }


    // to print the team name with its rank.
    @Override
    public String toString() {
        return teamName + " (Ranked "  + ranking() + ")";
    }
}


/////////////////////////////////////////////////////////////////

