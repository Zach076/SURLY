/*
  Stephen Hyde-Donohue and Zachary Richardson
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

  public void insert(String tupleName, String datatype, int maxlen) {
    //System.out.println("In relation " + name);
    //System.out.println("Insert a tuple with name " + tupleName + " datatype " + datatype + " and a maxlen of " + maxlen);
    Tuple newtuple = new Tuple(tupleName, datatype, maxlen);
    tuples.add(newtuple);
  }

	public void print() {
	}

  public String getName() {
    return name;
  }

  public LinkedList<Tuple> getTuples() {
    return tuples;
  }

}
