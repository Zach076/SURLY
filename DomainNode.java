/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  DomainNode class for SURLY
*/

import java.util.LinkedList;

public class DomainNode {
  private String name;
  private String datatype;
  private int maxlen;

  public DomainNode (String name, String datatype, int maxlen) {
    this.name = name;
    this.datatype = datatype;
    this.maxlen = maxlen;
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
