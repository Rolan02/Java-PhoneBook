package org.jalafoundation.devint34level1.grupo2.example.ContactInfo;

public class Address extends ContactInfo {

  private String address;

  public Address(String address, boolean isPreferred) {
    super(ContactInfoType.ADDRESS, isPreferred, address);
    this.address = address;
  }

  public String getAddress() {
    return address;
  }
}
