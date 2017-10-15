/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Tuple class for SURLY
*/

import java.util.LinkedList;

public class Tuple {

  private String name;
  private String datatype;
  private int maxlen;
  private LinkedList<Attribute> attributes = new LinkedList<Attribute>();

  public Tuple (String name, String datatype, int maxlen) {
    this.name = name;
    this.datatype = datatype;
    this.maxlen = maxlen;
  }

  public void insert() {
  }

	public void print() {
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
