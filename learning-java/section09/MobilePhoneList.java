//////////////////////////////////// mobile phone class ////////////////////////////////////

import java.util.ArrayList;
public class MobilePhone {
    // write code here
    
    // Creating two fields. A string called myNumber 
    // and ArrayList of type Contact called myContacts.
    
    private String myNumber;
    private ArrayList<Contact> myContacts;

    
     // Constructor
    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    // Adds a new contact if it doesn't already exist
    public boolean addNewContact(Contact contact) {
        if (findContact(contact.getName()) >= 0) {
            return false;
        }
        myContacts.add(contact);
        return true;
    }

    // Updates an existing contact with a new one
    public boolean updateContact(Contact oldContact, Contact newContact) {
        int position = findContact(oldContact);
        if (position < 0) {
            return false;
        }

        // Check if newContact's name already exists (and it's not the same object)
        int existingContactIndex = findContact(newContact.getName());
        if (existingContactIndex >= 0 && !myContacts.get(position).getName().equals(newContact.getName())) {
            return false; // name conflict
        }

        myContacts.set(position, newContact);
        return true;
    }

    // Removes a contact if it exists
    public boolean removeContact(Contact contact) {
        int position = findContact(contact);
        if (position < 0) {
            return false;
        }
        myContacts.remove(position);
        return true;
    }

    // Finds the index of a contact (by Contact object)
    private int findContact(Contact contact) {
        return myContacts.indexOf(contact);
    }

    // Finds the index of a contact (by name)
    private int findContact(String contactName) {
        for (int i = 0; i < myContacts.size(); i++) {
            if (myContacts.get(i).getName().equals(contactName)) {
                return i;
            }
        }
        return -1;
    }

    // Queries a contact by name
    public Contact queryContact(String name) {
        int position = findContact(name);
        if (position >= 0) {
            return myContacts.get(position);
        }
        return null;
    }

    // Prints the full contact list in the required format
    public void printContacts() {
        System.out.println("Contact List:");
        for (int i = 0; i < myContacts.size(); i++) {
            Contact c = myContacts.get(i);
            System.out.println((i + 1) + ". " + c.getName() + " -> " + c.getPhoneNumber());
        }
    }
}



/////////////////////////////// contact class ///////////////////////////////////////

public class Contact {
    // write code here
     private String name;
     private String phoneNumber;

    // Constructor
    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Static method to create a Contact
    public static Contact createContact(String name, String phoneNumber) {
        return new Contact(name, phoneNumber);
    }

}

///////////////////////////////////////////////////////////////////////////////////




