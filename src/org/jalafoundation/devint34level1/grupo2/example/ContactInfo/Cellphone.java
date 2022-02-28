package org.jalafoundation.devint34level1.grupo2.example.ContactInfo;

public class Cellphone extends ContactInfo {

  private final AreaCode areaCode;
  private final String number;

  public Cellphone(String number, AreaCode areacode, boolean isPreferred) {
    super(ContactInfoType.CELLPHONE, isPreferred, (areacode.toString().concat(" ".concat(number))));
    this.number = number;
    this.areaCode = areacode;
  }

  public AreaCode getAreaCode() {
    return areaCode;
  }

  public String getNumber() {
    return number;
  }

  @Override
  public String toString() {
    return areaCode.toString().concat(" ").concat(number);
  }
}
