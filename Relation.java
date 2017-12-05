/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Relation class for SURLY
*/

import java.util.LinkedList;

public class Relation {

  private final String name;
  private final LinkedList<Tuple> tuples = new LinkedList<Tuple>();
  private final LinkedList<DomainNode> domain = new LinkedList<DomainNode>();


  public Relation (String name) {
    this.name = name;
  }

  public void addDomain(String domainName, String datatype, int maxlen) {
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

  public int getSize() {
    return tuples.size();
  }

  public Tuple getAt(int index) {
    return tuples.get(index);
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


  public int findDomainNode(String name) {
    for (int i = 0; i < domain.size(); i++) {
      if (domain.get(i).getName().equals(name)) {
        return i;
      }
    }
    return -1;
  }

  public boolean isTrue(Tuple currRow, String[] tokens) {

    int truthMatrixLength = (int)((tokens.length - 2) / 4);
    boolean[] truthMatrix = new boolean[truthMatrixLength];

    int truthSetIndex;
    int attributeIndex = 0;
    int helperInt;

    for(int i = 0; i < truthMatrixLength; i++) {
      helperInt = (i * 4) + 2;
      if (tokens[helperInt + 2].equals("=")) {
        for(int x = 0; x < domain.size(); x++) {
          if (tokens[helperInt + 1].equals(domain.get(x).getName()))
            attributeIndex = x;
        }
        for(int x = 0; x < tuples.size(); x++) {
          currRow = tuples.get(x);
          //System.out.print(tokens[helperInt+3] + " ");
          //System.out.print(currRow.getAttrib(attributeIndex).getValue());
          if (currRow.getAttrib(attributeIndex).getValue().equals(tokens[helperInt+3])) {
            //System.out.print("*");
            truthMatrix[i] = true;
          }
          // System.out.println();
        }
      } else if (tokens[helperInt + 2].equals(">=")) {
        for(int x = 0; x < domain.size(); x++) {
          if (tokens[helperInt + 1].equals(domain.get(x).getName()))
            attributeIndex = x;
        }
        for(int x = 0; x < tuples.size(); x++) {
          currRow = tuples.get(x);
          if (currRow.getAttrib(attributeIndex).getValue().compareTo(tokens[helperInt+3]) < 0 || currRow.getAttrib(attributeIndex).getValue().equals(tokens[helperInt+3])) {
            truthMatrix[i] = true;
          }
        }
      } else if (tokens[helperInt + 2].equals("<=")) {
        for(int x = 0; x < domain.size(); x++) {
          if (tokens[helperInt + 1].equals(domain.get(x).getName()))
            attributeIndex = x;
        }
        for(int x = 0; x < tuples.size(); x++) {
          currRow = tuples.get(x);
          if (currRow.getAttrib(attributeIndex).getValue().compareTo(tokens[helperInt+3]) > 0 || currRow.getAttrib(attributeIndex).getValue().equals(tokens[helperInt+3])) {
            truthMatrix[i] = true;
          }
        }
      } else if (tokens[helperInt + 2].equals(">")) {
        for(int x = 0; x < domain.size(); x++) {
          if (tokens[helperInt + 1].equals(domain.get(x).getName()))
            attributeIndex = x;
        }
        for(int x = 0; x < tuples.size(); x++) {
          currRow = tuples.get(x);
          if (currRow.getAttrib(attributeIndex).getValue().compareTo(tokens[helperInt+3]) < 0) {
            truthMatrix[i] = true;
          }
        }
      } else if (tokens[helperInt + 2].equals("<")) {
        for(int x = 0; x < domain.size(); x++) {
          if (tokens[helperInt + 1].equals(domain.get(x).getName()))
            attributeIndex = x;
        }
        for(int x = 0; x < tuples.size(); x++) {
          currRow = tuples.get(x);
          if (currRow.getAttrib(attributeIndex).getValue().compareTo(tokens[helperInt+3]) > 0) {
            truthMatrix[i] = true;
          }
        }
      } else if (tokens[helperInt + 2].equals("!=")) {
        for(int x = 0; x < domain.size(); x++) {
          if (tokens[helperInt + 1].equals(domain.get(x).getName()))
            attributeIndex = x;
        }
        for(int x = 0; x < tuples.size(); x++) {
          currRow = tuples.get(x);
          if (currRow.getAttrib(attributeIndex).getValue() != tokens[helperInt+3]) {
            truthMatrix[i] = true;
          }
        }
      }
    }
    // for (int omp = 0; omp < truthMatrixLength; omp++) {
    //   System.out.println(truthMatrix[omp]);
    // }
    if(truthMatrixLength == 1) {
      if(truthMatrix[0] == true) {
        return true;
      }
    } else {
      boolean foundOr = false;
      String[] operatorIndex = new String[truthMatrixLength - 1];
      for(int y = 1; y < truthMatrixLength; y++) {
        operatorIndex[y-1] = tokens[(y * 4) + 1];
      }
      //if any false is detected in an 'and' statement, set both to false to carry the operations boolean value to potential operators on both sides
      for(int y = 0; y < (truthMatrixLength - 1); y++) {
        if(operatorIndex[y] == "and") {
          if(truthMatrix[y] == false || truthMatrix[y+1] == false) {
            truthMatrix[y] = false;
            truthMatrix[y+1] = false;
          }
        }
      }
      for(int y = 0; y < (truthMatrixLength - 1); y++) {
        if(operatorIndex[y] == "or") {
          foundOr = true;
        }
      }
      if(foundOr) {
        for(int y = 0; y < truthMatrixLength; y++) {
          if(truthMatrix[y]) {
            return true;
          }
        }
      } else return truthMatrix[0];
    }
    return truthMatrix[0];
  }

  public void print() {
    String maxlen;
    int tuplesLen = tuples.size();
    int domainLen = domain.size();
    Tuple currTuple;
    Attribute currAttribute;
    int x = 0;
    int y = 0;
    System.out.println(name);
    while(y < domainLen) {
      maxlen = "%" + domain.get(y).getMaxLen() + "s";
      System.out.print(String.format(maxlen + "%s", domain.get(y).getName(), "|"));
      y++;
    }
    y = 0;
    System.out.println();
    while(x < tuplesLen) {
      currTuple = tuples.get(x);
      while(y < domainLen) {
        if (domain.get(y).getMaxLen() < domain.get(y).getName().length()) {
          maxlen = "%" + domain.get(y).getName().length() + "s";
        } else {
          maxlen = "%" + domain.get(y).getMaxLen() + "s";
        }
        currAttribute = currTuple.getAttrib(y);
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
