package org.jalafoundation.devint34level1.grupo2.example.PhoneBook;

import java.util.ArrayList;
import java.util.Calendar;

public class Group {

  private String name;

  public ArrayList<Contact> getContacts() {
    return contacts;
  }

  private final ArrayList<Contact> contacts;

  public Group(String name,
      ArrayList<Contact> contacts) {
    this.name = name;
    this.contacts = contacts;
  }

  public Group(String name) {
    this.name = name;
    this.contacts = new ArrayList<Contact>();
  }

  public boolean addContact(Contact contact) {
    return this.contacts.add(contact);
  }

  public boolean removeContact(Contact contact) {
    boolean removed = false;
    for (int i = 0; i < this.contacts.size() ; i++) {
      if(contact.getName().equals(this.contacts.get(i).getName())){
        this.contacts.remove(this.contacts.get(i));
        removed = true;
        break;
      }
    }
    return removed;
  }

  public ArrayList<Contact> getContactForRangeDate(Calendar dateStart, Calendar dateEnd){
    return null;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return
        name +
            "\n" + contacts;
  }

}
