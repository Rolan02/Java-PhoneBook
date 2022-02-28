package org.jalafoundation.devint34level1.grupo2.example.ContactInfo;

public class Email extends ContactInfo {

  private final String user;
  private final Domain domain;

  public Email(String user, Domain domain, boolean isPreferred) {
    super(ContactInfoType.EMAIL, isPreferred, (user.concat(domain.getDomainName())));
    this.domain = domain;
    this.user = user;
  }

  public Domain getDomain() {
    return domain;
  }

  @Override
  public String toString() {
    return user.concat(domain.toString());
  }
}
