/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  DomainNode class for SURLY
*/

public class DomainNode {
  private final String name;
  private final String datatype;
  private final int maxlen;

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

  public int getMaxLen() {
    return maxlen;
  }
}
