/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Tuple class for SURLY
*/

import java.util.LinkedList;

public class Tuple {
  private LinkedList<Attribute> attributes = new LinkedList<Attribute>();

  public LinkedList<Attribute> getAttributes() {
    return attributes;
  }

  public Attribute getAttrib(int x) {
    return (attributes.get(x));
  }
}
