package org.jalafoundation.devint34level1.grupo2.example.ContactInfo;

public class ContactInfo {

  private boolean isPreferred;
  private final ContactInfoType contactInfoType;
  private final String value;

  public ContactInfo( ContactInfoType contactInfoType, boolean isPreferred , String value) {
    this.isPreferred = isPreferred;
    this.contactInfoType = contactInfoType;
    this.value = value;
  }

  public void setPreferred(boolean preferred) {
    isPreferred = preferred;
  }

  public ContactInfoType getContactInfoType() {
    return contactInfoType;
  }

  public boolean getPreferred() {
    return isPreferred;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}

