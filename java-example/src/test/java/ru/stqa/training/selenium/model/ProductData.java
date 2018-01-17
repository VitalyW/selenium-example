package ru.stqa.training.selenium.model;

import java.io.File;

public class ProductData {
  private String name;
  private String code;
  private String quantity;
  private String keywords;
  private String description;
  private String title;
  private String price;
  private File photo;

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }

  public String getQuantity() {
    return quantity;
  }

  public String getKeywords() {
    return keywords;
  }

  public String getDescription() {
    return description;
  }

  public String getTitle() {
    return title;
  }

  public String getPrice() {
    return price;
  }

  public File getPhoto() {
    return photo;
  }

  public ProductData withName(String name) {
    this.name = name;
    return this;
  }

  public ProductData withCode(String code) {
    this.code = code;
    return this;
  }

  public ProductData withQuantity(String quantity) {
    this.quantity = quantity;
    return this;
  }

  public ProductData withKeywords(String keywords) {
    this.keywords = keywords;
    return this;
  }

  public ProductData withDescription(String description) {
    this.description = description;
    return this;
  }

  public ProductData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ProductData withPrice(String price) {
    this.price = price;
    return this;
  }

  public ProductData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

}
