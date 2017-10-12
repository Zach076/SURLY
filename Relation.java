/*
  Stephen Hyde-Donohue
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Relation class for SURLY
*/

import java.util.LinkedList;

public class Relation {

  private String name;
  private LinkedList<Tuple> tuples = new LinkedList<Tuple>();

  public Relation (String name) {
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
