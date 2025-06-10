package dev.lpa;

import java.util.ArrayList;

//instead of a class for customer, i created a record because does not need a lot of functionality.
record Customer(String name, ArrayList<Double> transactions) {


    //overloaded constructor in a record (default constructor is the first statement)
    public Customer(String name, double initialDeposit) { // 2 args
        this(name.toUpperCase(), // passing name in uppercase
                new ArrayList<Double>(500)); //new lists of double arraylists . (Capacity = 500 (size
        transactions.add(initialDeposit); //after we add the initial deposit to the customers transaction list
                                          //using autoboxing passing a double primitive var to the add method
    }
}

public class Main {

    public static void main(String[] args) {

        Customer bob = new Customer("Bob S", 1000.0);
        System.out.println(bob);


        Bank bank = new Bank("Chase"); // new instance of a bank
        bank.addNewCustomer("Jane A", 500.0); // new costumer
        System.out.println(bank);

        bank.addTransaction("Jane A", -10.25);
        bank.addTransaction("jane A", -75.01);
        bank.printStatement("Jane a");

        bank.addNewCustomer("bob s", 25);
        bank.addTransaction("Bob S", 100);
        bank.printStatement("Bob S");



    }
}

//instead of creating a record I am going to create a new class because I want more functionality  for the bank
class Bank {

    private String name;
    private ArrayList<Customer> customers = new ArrayList<>(5000); //array list of customers
                                                          //capacity of 5000 assuming a bank could easily have
                                                          // that many customers as an example.

    //constructor
    public Bank(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }

    //
    private Customer getCustomer(String customerName) {

        for (var customer : customers) { //enhanced for loop (looping through the customer list
            if (customer.name().equalsIgnoreCase(customerName)) { //compare customer name with the name passed
                // as the method's argument ignoring the case
                return customer;
            }
        }
        System.out.printf("Customer (%s) wasn't found %n", customerName); // if we get to the end of the loop
                     // haven't found a match.

        return null; // null indicates no match
    }

    public void addNewCustomer(String customerName, double initialDeposit) {

        //create new record with these values .

        if (getCustomer(customerName) == null) { // calling customer name method. if returns null I need to add new
            Customer customer = new Customer(customerName, initialDeposit); // creating new customer
            customers.add(customer); //adding to the customer list
            System.out.println("New Customer added: " + customer);
        }

    }

    // allows transactions to happen through a bank instance, which first gets a matching customer
    //then the customer's transactions adding the transaction to the list with the use of auto boxing

    public void addTransaction(String name, double transactionAmount) {

        Customer customer = getCustomer(name);
        if (customer != null) {
            customer.transactions().add(transactionAmount); // but I need a way to see the customer's transactions.

        }
    }

    // that is the way to see customer's transactions
    public void printStatement(String customerName) {

        Customer customer = getCustomer(customerName);
        if (customer == null) { //retrieve the customer. if is not found . exit the method
            return;
        }
//if found those info., print it
        System.out.println("-".repeat(30));
        System.out.println("Customer Name: " + customer.name());
        System.out.println("Transactions:");
        for (double d : customer.transactions()) {  // unboxing. variable typed as a primitive double
            System.out.printf("$%10.2f (%s)%n", d, d < 0 ? "debit" : "credit"); //check the amount
        }

    }


}
