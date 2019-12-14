package Classes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sale implements Serializable {
 private static final long serialVersionUID = 1L;
 
 @Id

 float price;
 int number_of_units;
 String country;

 @OneToMany
 private List<Item> items;
 
 
 public Sale() {
  super();
 }
 
 public Sale(float price,  int number_of_units, String country) {
  super();
  this.price = price;
  this.number_of_units = number_of_units;
  this.country = country;
 }

 public float getPrice() {
  return price;
 }
 public void setPrice(float price) {
  this.price = price;
 }


 public int getNumberUnits() {
  return number_of_units;
 }
 public void setNumberUnits(int number_of_units) {
  this.number_of_units = number_of_units;
 }


 public String getCountry() {
  return country;
 }
 public void setCountry(String country) {
  this.country = country;
 }


 public List<Item> getItems() {
  return items;
 }
 public void setItems(List<Item> items) {
  this.items = items;
 }

}