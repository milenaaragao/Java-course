///////////////// main //////////////////////

package dev.lpa;

import dev.lpa.domain.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //setup employees:

        Employee e1 = new Employee("Minnie" , "Mouse", "01/02/2015");
        Employee e2 = new Employee("Mickie" , "Mouse", "05/08/2000");
        Employee e3 = new Employee("Daffy" , "Duck", "11/02/2011");
        Employee e4 = new Employee("Daisy" , "Duck", "05/03/2013");
        Employee e5 = new Employee("Goofy" , "Dog", "23/07/2020");

        //setup list:
        List<Employee> list = new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5));

        printOrderedList(list, "name");
        System.out.println();
        printOrderedList(list, "year");
    }

    //Adding a method that will contain both: the LOCAL and ANONYMOUS classes.

    public static void printOrderedList(List<Employee> eList, String sortField) { //passing list + parameter sortFields
                                                                // if field indicate sort by name, I will sort by name.
                                                            //otherwise, by years worked.
        int currentYear = LocalDate.now().getYear(); // using DATE FUNCTION

        //setup my LOCAL CLASS:
        class MyEmployee { //I am calling the class MYEMPLOYEE - i cannot extend because it is a RECORD

            Employee containedEmployee; //Contained Employee field - just reference the ORIGINAL employee instance.
            int yearsWorked; //field
            String fullName; // field


            public MyEmployee(Employee containedEmployee) {
                this.containedEmployee = containedEmployee;
                yearsWorked = currentYear - Integer.parseInt( //get year as integer
                        containedEmployee.hireDate().split("/")[2]); //split string here - third split should be year
                fullName = String.join(" ",
                        containedEmployee.first(), containedEmployee.last());
            }

            @Override
            public String toString() {
                return "%s has been an employee for %d years".formatted(
                        fullName, yearsWorked);
            }


        }

        //now I loop through the list

        List<MyEmployee> list = new ArrayList<>();
        for (Employee employee : eList) {
            list.add(new MyEmployee(employee));
        } // list of my employees.

        // to sort the list I start setting up the comparator as a var.

        var comparator = new Comparator<MyEmployee>() { //just one instance of this class.

            @Override
            public int compare(MyEmployee o1, MyEmployee o2) { //implement compare method so VAR can works.

                if (sortField.equals("name")) { // how to compare the employees . - if sortField has the valueName
                                                                    // the sort will be used fullName field on the
                                                                    // local class to sort by.
                    return o1.fullName.compareTo(o2.fullName);
                }
                return o1.yearsWorked - o2.yearsWorked; // otherwise, it will sort by the yearsWorked field on the class.
            }
        };
// after have my comparator, I can sort my list.
        list.sort(comparator);

        for (MyEmployee myEmployee : list) {
            System.out.println(myEmployee);
        }

    }
}



////////////////////// employee ////////////////////////////

package dev.lpa.domain;

public record Employee(String first, String last, String hireDate) {
}
