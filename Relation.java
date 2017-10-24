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
  private LinkedList<DomainNode> domain = new LinkedList<DomainNode>();


  public Relation (String name) {
    this.name = name;
  }

  public void addDomain(String domainName, String datatype, int maxlen) {
    //System.out.println("In relation " + name);
    //System.out.println("Insert a tuple with name " + domainName + " datatype " + datatype + " and a maxlen of " + maxlen);
    DomainNode newDomain = new DomainNode(domainName, datatype, maxlen);
    domain.add(newDomain);
  }

  public String getName() {
    return name;
  }

  public LinkedList<Tuple> getTuples() {
    return tuples;
  }

  public LinkedList<DomainNode> getDomain() {
    return domain;
  }

}
