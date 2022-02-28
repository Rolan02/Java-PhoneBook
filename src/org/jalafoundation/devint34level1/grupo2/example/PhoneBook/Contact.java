package org.jalafoundation.devint34level1.grupo2.example.PhoneBook;

import java.util.ArrayList;
import java.util.Calendar;

import org.jalafoundation.devint34level1.grupo2.example.ContactInfo.ContactInfo;

public class Contact  {

  public static final String DEFAULT_NICKNAME = "no nickname";
  public static final String DEFAULT_GROUP = "Ungrouped";
  private final String name;
  private final String lastName;
  private String nickName;
  private Calendar birthday;
  private ArrayList<ContactInfo> contactInfoList;
  private String groupName = DEFAULT_GROUP;


  public Contact(String name, String lastName) {
    this.name = name;
    this.lastName = lastName;
    this.nickName = DEFAULT_NICKNAME;
    this.contactInfoList = new ArrayList<>();
  }

  public Contact(String name, String lastName, String nickName, ArrayList<ContactInfo> contactInfo) {
    this.name = name;
    this.lastName = lastName;
    this.nickName = nickName;
    this.contactInfoList = contactInfo;
  }

  public void updateNickName(String nickName){
    this.setNickName(nickName);
  }


  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public void setContactInfoList(
      ArrayList<ContactInfo> contactInfoList) {
    this.contactInfoList = contactInfoList;
  }

  public ArrayList<ContactInfo> getContactInfoList() {
    return contactInfoList;
  }

  public String toStringFull() {
    return
        "Name: " + name +
            "\nLastName: " + lastName +
            "\nNickName: " + nickName +
            "\nContactInfo: " + getPreferredContactInfo().toString() +
            "\n";
  }

  public ContactInfo getPreferredContactInfo(){
    ContactInfo preferred = null;
    int count = 0;
    while(preferred == null){
      if(contactInfoList.get(count).getPreferred()){
        preferred = contactInfoList.get(count);
      }
      count++;
    }
  return preferred;
  }

  @Override
  public String toString() {
    return
        "- " + name + " " +lastName + " - " + nickName + "\n";
  }
}
