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
  private String datatype;
  private int maxlen;

  public Attribute (V value, String name, String datatype) {
    this.value = value;
    this.name = name;
    this.datatype = datatype;
  }

  public V getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  public String getDatatype() {
    return datatype;
  }

  public int getMaxlen() {
    return maxlen;
  }
}
