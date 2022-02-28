package org.jalafoundation.devint34level1.grupo2.example.ContactInfo;

public enum AreaCode {
  BOLIVIA("591"),
  ARGENTINA("54"),
  PERU("52");

  private final String codeNumber;
  private static final String PREFIX = "+";

  AreaCode(String codeNumber) {
    this.codeNumber = PREFIX.concat(codeNumber);
  }

  @Override
  public String toString() {
    return codeNumber;
  }
}
