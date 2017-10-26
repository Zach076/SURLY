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
    tuples.add(new Tuple());
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
    int tuplesLen = tuples.size();
    int domainLen = domain.size();
    Tuple currTuple;
    Attribute currAttribute;
    int x = 0;
    int y = 0;
    while(y < domainLen) {
      maxlen = "%" + domain.get(y).getMaxLen() + "s";
      System.out.print(String.format(maxlen + "%s", domain.get(y).getName(), "|"));
      y++;
    }
    y = 0;
    System.out.println();
    while(x < tuplesLen) {
      currTuple = tuples.get(x);
      //System.out.println("x = " + x);
      while(y < domainLen) {
        if (domain.get(y).getMaxLen() < domain.get(y).getName().length()) {
          maxlen = "%" + domain.get(y).getName().length() + "s";
        } else {
          maxlen = "%" + domain.get(y).getMaxLen() + "s";
        }
        //System.out.print("x is " + x + " y is " + y + " ");
        currAttribute = currTuple.getAttrib(y);

        //System.out.println('*' + domain.get(y).getName() + '*');
        System.out.print(String.format(maxlen + "%s" , currAttribute.getValue() , "|" ));
        y++;
      }
      y = 0;
      System.out.println();
      x++;

    }
    System.out.println();
  }

}
