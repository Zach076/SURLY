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
    //System.out.println("Insert a tuple with name " + tupleName + " datatype " + datatype + " and a maxlen of " + maxlen);
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

  public void insertTuple() {
    Tuple newtuple = new Tuple();
    tuples.add(newtuple);
  }

  private String formatMaxlens() {
    String maxlen = "";
    int length = domain.indexOf(domain.getLast());
    int i = 0;
    while (i < length) {
      maxlen = maxlen + "%" + domain.get(i).getMaxLen() + "s";
      i++;
    }
    return maxlen;
  }

  public void print() {
    String maxlen = "";
    int tuplesLen = tuples.indexOf(tuples.getLast());
    int domainLen = domain.indexOf(domain.getLast());
    int x = 0;
    int y = 0;
    while(y < domainLen) {
      maxlen = "%" + domain.get(y).getMaxLen() + "s";
      System.out.print(String.format(maxlen + "%s", domain.get(y).getName(), "|"));
      y++;
    }
    y = 0;
    while(x < tuplesLen) {
      while(y < domainLen) {
        maxlen = "%" + domain.get(y).getMaxLen() + "s";
        System.out.print(String.format(maxlen + "%s" , tuples.get(x).getAttributes().get(y).getValue() , "|" ));
        y++;
      }
      System.out.println("");
      x++;
    }
  }

}
