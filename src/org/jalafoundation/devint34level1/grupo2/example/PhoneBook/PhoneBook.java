package org.jalafoundation.devint34level1.grupo2.example.PhoneBook;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.Address;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.AreaCode;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.ContactInfo;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.ContactInfoType;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.Domain;
import org.jalafoundation.devint34level1.grupo2.example.JsonUtil;


public class PhoneBook {

  private ArrayList<Group> groups;
  private ArrayList<Contact> contacts;
  private static final Scanner read = new Scanner(System.in);

  public PhoneBook() {
    this.groups = JsonUtil.readJsonFile();
    this.contacts = new ArrayList<>();
  }

  public boolean addContact(Contact contact) {
    boolean added = false;
    if (this.groups.isEmpty()) {
      if (this.addNewGroup("Ungrouped")) {
        System.out.println("Ungrouped is default group");
      }
    }
    for (Group group : this.groups) {
      if (group.getName().equals("Ungrouped")) {
        added = group.addContact(contact);
      }
    }
    writeJson();
    return added;
  }

  public void writeJson() {
    JsonUtil.writeJsonFile(this.groups);
  }

  public boolean addNewGroup(String name) {
    boolean added = false;
    if (!groups.contains(new Group(name))) {
      added = this.groups.add(new Group(name));
    }
    writeJson();
    return added;
  }

  public void manageContact(Contact contact) {
    System.out.println(
        "  1.- Update nickname\n" +
            "  2.- Delete contact\n" +
            "  3.- Move contact to group\n" +
            "  4.- Go back to menu.\n"
    );

    int selectedOption = read.nextInt();
    switch (selectedOption) {
      case 1:
        System.out.println("Enter new nickname:");
        String nickname = read.next();
        contact.setNickName(nickname);
        System.out.println("Contact nickname updated successfully: ");
        System.out.println(contact);
        break;
      case 2:
        if (this.deleteContact(contact)) {
          System.out.println("Contact deleted successfully: ");
        } else {
          System.out.println("Delete contact failed. ");
        }
        break;
      case 3:
        System.out.println("Enter the name of group you want to move contact:");
        String group = read.next();
        this.moveContactGroup(contact, group);
        break;
      case 4:
        break;
      default:
        System.out.println("Invalid option");
        break;
    }
  }

  private void moveContactGroup(Contact contact, String groupName) {
    for (Group group : this.groups) {
      if (contact.getGroupName().equals(group.getName())) {
        group.removeContact(contact);
        break;
      }
    }
    this.newGroup(groupName);
    for (Group group : this.groups) {
      if (group.getName().equals(groupName)) {
        contact.setGroupName(groupName);
        group.addContact(contact);
      }
    }
  }

  public void newGroup(String name) {
    if (!groups.contains(new Group(name))) {
      this.groups.add(new Group(name));
    }
  }
  //Remover de las siguientes formas Hard - Soft
  public boolean deleteContact(Contact contact) {
    boolean removed = false;
    for (Group group : this.groups) {
      removed = group.removeContact(contact);
      break;
    }
    return removed;
  }

  public Contact searchContactByName(String firstName, String lastName) {
    Contact contactFound = null;

    if (this.groups.isEmpty()) {
      System.out.println("Phone book is empty.");
    } else {
      for (Group group : this.groups) {
        this.contacts = group.getContacts();
        for (Contact contact : this.contacts) {
          if (contact.getName().equalsIgnoreCase(firstName) && contact
              .getLastName().equalsIgnoreCase(lastName)) {
            contactFound = contact;
            break;
          }
        }
      }
    }
    return contactFound;
  }

  public Contact searchContactByCellphone(String fullNumber) {
    Contact contactFound = null;

    if (this.groups.isEmpty()) {
      System.out.println("Phone book is empty.");
    } else {
      for (Group group : this.groups) {
        this.contacts = group.getContacts();
        for (Contact contact : this.contacts) {
          for (ContactInfo contactInfo : contact.getContactInfoList()) {
            if (contactInfo.getContactInfoType().equals(ContactInfoType.CELLPHONE)) {
              if (contactInfo.getValue().equals(fullNumber)) {
                contactFound = contact;
                break;
              }
            }
          }
        }
      }
    }
    return contactFound;
  }

  public Contact searchContactByEmail(String fullEmail) {
    Contact contactFound = null;

    if (this.groups.isEmpty()) {
      System.out.println("Phone book is empty.");
    } else {
      for (Group group : this.groups) {
        this.contacts = group.getContacts();
        for (Contact contact : this.contacts) {
          for (ContactInfo contactInfo : contact.getContactInfoList()) {
            if (contactInfo.getContactInfoType().equals(ContactInfoType.EMAIL)) {
              if (contactInfo.getValue().equals(fullEmail)) {
                contactFound = contact;
                break;
              }
            }
          }
        }
      }
    }
    return contactFound;
  }

  public ArrayList<Contact> findContactsByAreaCode(AreaCode areaCode) {
    ArrayList<Contact> contactsFound = new ArrayList<>();

    if (this.groups.isEmpty()) {
      System.out.println("Phone book is empty.");
    } else {
      for (Group group : this.groups) {
        this.contacts = group.getContacts();
        for (Contact contact : this.contacts) {
          for (ContactInfo contactInfo : contact.getContactInfoList()) {
            if (contactInfo.getContactInfoType().equals(ContactInfoType.CELLPHONE)) {
              StringTokenizer tokens = new StringTokenizer(contactInfo.getValue());
              String contactInfoAreaCode = tokens.nextToken();
              if (contactInfoAreaCode.equals(areaCode.toString())) {
                contactsFound.add(contact);
              }
            }
          }
        }
      }
    }
    return contactsFound;
  }

  public ArrayList<Contact> findContactsByDomain(Domain domainToFind) {
    ArrayList<Contact> contactsFound = new ArrayList<>();

    if (this.groups.isEmpty()) {
      System.out.println("Phone book is empty.");
    } else {
      for (Group group : this.groups) {
        this.contacts = group.getContacts();
        for (Contact contact : this.contacts) {
          for (ContactInfo contactInfo : contact.getContactInfoList()) {
            if (contactInfo.getContactInfoType().equals(ContactInfoType.EMAIL)) {
              String domain = contactInfo.getValue()
                  .substring(contactInfo.getValue().indexOf("@"));
              if (domain.equals(domainToFind.toString())) {
                contactsFound.add(contact);
              }
            }
          }
        }
      }
    }
    return contactsFound;
  }

  public void showGroups() {
    this.groups = JsonUtil.readJsonFile();
    for (Group group : this.groups) {
      System.out.println(group.toString().replace("[", "").replace("]", "").replace(",", ""));
    }
  }

  public boolean deleteGroup(Group groupToDelete) {
    boolean deleted = false;
    if (!groupToDelete.getName().equals("Ungrouped")) {
      for (Group group : this.groups) {
        if (group.getName().equals(groupToDelete.getName())) {
          deleted = this.groups.remove(group);
          break;
        }
      }
    } else {
      System.out.println("Unable to delete default group.");
    }
    writeJson();
    return deleted;
  }

  public Contact searchContactByAddress(String address) {
    Contact contactFound = null;

    if (this.groups.isEmpty()) {
      System.out.println("Phone book is empty.");
    } else {
      for (Group group : this.groups) {
        this.contacts = group.getContacts();
        for (Contact contact : this.contacts) {
          for (ContactInfo contactInfo : contact.getContactInfoList()) {
            if (contactInfo.getContactInfoType().equals(ContactInfoType.ADDRESS)) {
              Address currentAddress = (Address) contactInfo;
              if (currentAddress.getAddress().equals(address)) {
                System.out.printf("Contact found: %s", contact);
                contactFound = contact;
              }
            }
          }
        }
      }
    }
    return contactFound;
  }

  public void showPhoneBook() {
    this.groups = JsonUtil.readJsonFile();
    for (Group group : this.groups) {
      System.out.println(group.toString().replace("[", "").replace("]", "").replace(",", ""));
    }
  }
}
