package org.jalafoundation.devint34level1.grupo2.example.ContactInfo;

public enum Domain {
  GMAIL("gmail.com"),
  HOTMAIL("hotmail.com"),
  OUTLOOK("outlook.com"),
  YAHOO("yahoo.com"),
  FUNDACION_JALA("fundacion-jala.org");

  private final String domainName;
  private static final String PREFIX = "@";

  Domain(String domainName) {
    this.domainName = PREFIX.concat(domainName);
  }

  public String getDomainName() {
    return domainName;
  }

  @Override
  public String toString() {
    return domainName;
  }
}