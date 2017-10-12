/*
  Stephen Hyde-Donohue
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Tuple class for SURLY
*/

import java.util.LinkedList;

public class Tuple {

  private String name;
  private LinkedList<Attribute> attributes = new LinkedList<Attribute>();

  public Tuple (String name) {
    this.name = name;
  }

  public void insert() {
  }

	public void print() {
	}

  public String getName() {
    return name;
  }
}
