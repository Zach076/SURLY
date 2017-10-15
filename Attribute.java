/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Attribute class for SURLY
*/

public class Attribute<V> {

  private V value;
  private String name;


  public Attribute (V value) {
    this.value = value;
  }

  public V getValue() {
    return value;
  }
}
