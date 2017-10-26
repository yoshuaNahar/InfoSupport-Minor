package nl.infosupport.javaminor.blok1.week2.tdd.iban;

public class IbanBuilder {

  private String countryCode;
  private String bankCode;
  private String cardCode;

  public IbanBuilder() {
    this.countryCode = "NL12";
    this.bankCode = "INGB";
    this.cardCode = "06123456789";
  }

  public IbanBuilder setCountryCode(String countryCode) {
    this.countryCode = countryCode;

    return this;
  }

  public IbanBuilder setBankCode(String bankCode) {
    this.bankCode = bankCode;

    return this;
  }

  public IbanBuilder setCardCode(String cardCode) {
    this.cardCode = cardCode;

    return this;
  }

  public String build() {
    return countryCode + bankCode + cardCode;
  }

}
