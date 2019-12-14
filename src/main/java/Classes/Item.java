package Classes;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Classes.Item
 *
 */
@Entity
public class Item implements Serializable {

 @Id
 @GeneratedValue
 long id;
 String name;
 float price;
 int number_of_units;

 @ManyToOne
 private Sale sale;
 @OneToOne
 private Country country;
 
 public Item() {
  super();
 }

 public Item(String name, float price, int number_of_units, Sale sale, Country country) {
  super();
  this.name = name;
  this.price = price;
  this.number_of_units = number_of_units;
  this.sale = sale;
  this.country = country;
 }

 public long getId() {
  return id;
 }

 public void setId(long id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public float getPrice() {
  return price;
 }

 public void setPrice(float price) {
  this.price = price;
 }

 public int getNumber_of_units() {
  return number_of_units;
 }

 public void setNumber_of_units(int number_of_units) {
  this.number_of_units = number_of_units;
 }

 public Sale getSale() {
  return sale;
 }

 public void setSale(Sale sale) {
  this.sale = sale;
 }

 public Country getCountry() {
  return country;
 }

 public void setCountry(Country country) {
  this.country = country;
 }

 @Override
 public String toString() {
  return this.name + " id = " + this.price + ", " + this.number_of_units +  this.sale.getCountry();
 }
}
   

