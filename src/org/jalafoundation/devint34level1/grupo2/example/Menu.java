package org.jalafoundation.devint34level1.grupo2.example;

import java.util.ArrayList;
import java.util.Scanner;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.Address;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.AreaCode;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.Cellphone;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.ContactInfo;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.Domain;
import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.Email;
import org.jalafoundation.devint34level1.grupo2.example.PhoneBook.Contact;
import org.jalafoundation.devint34level1.grupo2.example.PhoneBook.Group;
import org.jalafoundation.devint34level1.grupo2.example.PhoneBook.PhoneBook;

public class Menu {

  private final PhoneBook phoneBook;
  private static final Scanner read = new Scanner(System.in);

  public Menu() {
    this.phoneBook = new PhoneBook();
  }

  public boolean run() {
    boolean terminate = false;
    System.out.println("====== Phone Book Menu ======\n" +
        "  1.- Show Phone Book\n" +
        "  2.- New contact\n" +
        "  3.- Search contact\n" +
        "  4.- Find contacts\n" +
        "  5.- Manage groups\n" +
        "  6.- Finish\n"
    );
    int selectedOption = read.nextInt();
    switch (selectedOption) {
      case 1:
        System.out.println("============= Phone Book ===============");
        this.phoneBook.showGroups();
        System.out.println("====================================");
        break;
      case 2:
        this.newContact();
        break;
      case 3:
        Contact foundContact = this.searchContactBy();
        if (foundContact != null) {
          System.out.printf("---------------------------Contact found!---------------------------\n%s", foundContact.toStringFull());
          System.out.println("---------------------------------------------------------");
          this.phoneBook.manageContact(foundContact);
        } else {
          System.out.println("----------------No contact Found.----------------");
        }
        break;
      case 4:
        ArrayList<Contact> foundContacts = findContactsBy();
        if (!foundContacts.isEmpty()) {
          System.out.printf("Contacts found!\n %s\n", foundContacts.toString().replace("[", "").replace("]", "").replace(",", ""));
        } else {
          System.out.println("No contacts Found.");
        }
        break;
      case 5:
        this.manageGroups();
        break;
      case 6:
        terminate = true;
        this.phoneBook.writeJson();
        System.out.println("Good bye!");
        break;
      default:
        System.out.println("Invalid option");
        this.run();
        break;
    }
    return terminate;
  }

  private void newContact() {
    System.out.println("---- Add new contact ----");
    System.out.println("Please fill new contact fields: ");
    System.out.println("Enter first name:");
    String firstName = read.next();
    System.out.println("Enter last name:");
    String lastName = read.next();
    System.out.println("Enter nickname:");
    String nickname = read.next();
    System.out.println("Add contact Information: ");
    System.out.println("====== Must add at least one contact info ======");
    ArrayList<ContactInfo> contactInfo = addContactInfoMenu();

    if (this.phoneBook
        .addContact(new Contact(firstName, lastName, nickname, contactInfo))) {
      System.out.println("Contact successfully added.");
    }
  }

  private Contact searchContactBy() {
    Contact contactFound = null;
    System.out.println("---- Search contact by: ----\n" +
        "  1.- Name\n" +
        "  2.- Cellphone\n" +
        "  3.- Email\n" +
        "  4.- Address\n" +
        "  5.- Go back\n"
    );
    int selectedParam = read.nextInt();
    switch (selectedParam) {
      case 1:
        System.out.println("Enter first name");
        String firstName = read.next();
        System.out.println("Enter last name");
        String lastName = read.next();
        contactFound = this.phoneBook.searchContactByName(firstName, lastName);
        break;
      case 2:
        System.out.println("Enter cellphone (+AreaCode Number) : ");
        read.nextLine();
        String fullNumber = read.nextLine();
        contactFound = this.phoneBook.searchContactByCellphone(fullNumber);
        break;
      case 3:
        System.out.println("Enter email (user@domain) : ");
        String fullEmail = read.next();
        contactFound = this.phoneBook.searchContactByEmail(fullEmail);
        break;
      case 4:
        System.out.println("Enter address : ");
        String address = read.next();
        contactFound = this.phoneBook.searchContactByAddress(address);
        break;
      case 5:
        this.run();
        break;
      default:
        System.out.println("Invalid option.");
        this.searchContactBy();
        break;
    }
    return contactFound;
  }

  private ArrayList<Contact> findContactsBy() {
    ArrayList<Contact> contactsFound = null;
    System.out.println("---- Find contacts by: ----\n" +
        "  1.- Find contacts by area code.\n" +
        "  2.- Find contacts by domain.\n" +
        "  3.- Go back\n"
    );
    int selectedParam = read.nextInt();
    switch (selectedParam) {
      case 1:
        System.out.println("Select area code");
        AreaCode areaCode = selectAreaCode();
        contactsFound = this.phoneBook.findContactsByAreaCode(areaCode);
        break;
      case 2:
        System.out.println("Select email domain:");
        Domain domain = selectDomain();
        contactsFound = this.phoneBook.findContactsByDomain(domain);
        break;
      case 3:
        this.run();
        break;
      default:
        break;
    }
    return contactsFound;
  }

  private void manageGroups() {
    System.out.println("---- Manage groups: ----\n" +
        "  1.- New Group.\n" +
        "  2.- Show groups.\n" +
        "  3.- Delete group.\n" +
        "  4.- Go back\n"
    );
    int optionSelected = read.nextInt();
    switch (optionSelected) {
      case 1:
        System.out.println("Enter new group's name:");
        String groupToAdd = read.next();
        if (this.phoneBook.addNewGroup(groupToAdd)) {
          System.out.printf("Group %s created\n", groupToAdd);
        } else {
          System.out.println("Add group failed.");
        }
        manageGroups();
        break;
      case 2:
        System.out.println("============= Groups ===============");
        this.phoneBook.showGroups();
        System.out.println("====================================");
        manageGroups();
        break;
      case 3:
        System.out.println("Enter group name to delete:");
        String groupToDelete = read.next();
        if (this.phoneBook.deleteGroup(new Group(groupToDelete))) {
          System.out.println("Group successfully deleted.");
        } else {
          System.out.println("Delete group failed.");
        }
        manageGroups();
        break;
      case 4:
        this.run();
        break;
      default:
        System.out.println("Invalid option");
        this.run();
        break;
    }
  }

  public ArrayList<ContactInfo> addContactInfoMenu() {
    ArrayList<ContactInfo> contactInfos = new ArrayList<>();
    System.out.println(
        "  1.- Add cellphone\n" +
            "  2.- Add email\n" +
            "  3.- Add address\n" +
            "  4.- Finish\n"
    );
    int option = read.nextInt();

    switch (option) {
      case 1:
        Cellphone newCellphone = addNewCellphone();
        if (contactInfos.isEmpty()) {
          newCellphone.setPreferred(true);
        }
        contactInfos.add(newCellphone);
        break;
      case 2:
        Email newEmail = addNewEmail();
        if (contactInfos.isEmpty()) {
          newEmail.setPreferred(true);
        }
        contactInfos.add(newEmail);
        break;
      case 3:
        Address newAddress = addNewAddress();
        if (contactInfos.isEmpty()) {
          newAddress.setPreferred(true);
        }
        contactInfos.add(newAddress);
        break;
      case 4:
        if (contactInfos.isEmpty()) {
          System.out.println("Must add at least one contact info");
          addContactInfoMenu();
        } else {
          this.run();
          break;
        }
      default:
        System.out.println("Invalid option.");
        addContactInfoMenu();
    }
    return contactInfos;
  }

  public Cellphone addNewCellphone() {
    System.out.println("Fill new cellphone fields:");
    System.out.println("Chose area code:");
    AreaCode areaCode = selectAreaCode();
    System.out.println("Insert phone number:");
    String number = read.next();

    return new Cellphone(number, areaCode, false);
  }

  public Email addNewEmail() {
    System.out.println("Fill new email fields:");
    System.out.println("Email user:");
    String user = read.next();
    System.out.println("Email domain:");
    Domain domain = selectDomain();

    return new Email(user, domain, false);
  }

  public Address addNewAddress() {
    System.out.println("Insert Address:");
    String address = read.nextLine();

    return new Address(address, false);
  }

  public AreaCode selectAreaCode() {
    System.out.println(
        "  1.- Bolivia (591)\n" +
            "  2.- Argentina (54)\n" +
            "  3.- Peru (52)\n"
    );
    int selectedAreaCode = read.nextInt();
    AreaCode areaCode = null;
    switch (selectedAreaCode) {
      case 1:
        areaCode = AreaCode.BOLIVIA;
        break;
      case 2:
        areaCode = AreaCode.ARGENTINA;
        break;
      case 3:
        areaCode = AreaCode.PERU;
        break;
      default:
        System.out.println("Invalid option.");
        selectAreaCode();
        break;
    }
    return areaCode;
  }

  public Domain selectDomain() {
    System.out.println(
        "  1.- gmail.com\n" +
            "  2.- hotmail.com\n" +
            "  3.- outlook.com\n" +
            "  4.- yahoo.com\n" +
            "  5.- fundacion-jala.org\n"
    );

    int selectedDomain = read.nextInt();
    Domain domain = null;
    switch (selectedDomain) {
      case 1:
        domain = Domain.GMAIL;
        break;
      case 2:
        domain = Domain.HOTMAIL;
        break;
      case 3:
        domain = Domain.OUTLOOK;
        break;
      case 4:
        domain = Domain.YAHOO;
        break;
      case 5:
        domain = Domain.FUNDACION_JALA;
        break;
      default:
        System.out.println("Invalid option.");
        selectDomain();
        break;
    }
    return domain;
  }
}
