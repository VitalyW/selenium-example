package ru.stqa.training.selenium.model;

public class RegisrationData {
  private String taxID;
  private String firstName;
  private String lastName;
  private String address;
  private String zipCode;
  private String country;
  private String email;
  private String company;
  private String city;
  private String state;
  private String phone;
  private String desiredPassword;
  private String confirmPassword;

  public String getTaxID() {
    return taxID;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getCountry() {
    return country;
  }

  public String getZipCode() {
    return zipCode;
  }

  public String getEmail() {
    return email;
  }

  public String getCompany() {
    return company;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPhone() {
    return phone;
  }

  public String getDesiredPassword() {
    return desiredPassword;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public RegisrationData withTaxID(String taxID) {
    this.taxID = taxID;
    return this;
  }

  public RegisrationData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public RegisrationData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public RegisrationData withAddress(String address) {
    this.address = address;
    return this;
  }

  public RegisrationData withZipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public RegisrationData withEmail(String email) {
    this.email = email;
    return this;
  }

  public RegisrationData withCountry(String country) {
    this.country = country;
    return this;
  }

  public RegisrationData withCompany(String company) {
    this.company = company;
    return this;
  }

  public RegisrationData withCity(String city) {
    this.city = city;
    return this;
  }

  public RegisrationData withState(String state) {
    this.state = state;
    return this;
  }

  public RegisrationData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public RegisrationData withDesiredPassword(String desiredPassword) {
    this.desiredPassword = desiredPassword;
    return this;
  }

  public RegisrationData withConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
    return this;
  }
}
