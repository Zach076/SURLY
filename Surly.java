/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Main driver program
*/
public class Surly {

  public static void main(String[] args) {
    SurlyDatabase database = SurlyDatabase.getInstance();
    SurlyParser parser = SurlyParser.getInstance();
    try {
      parser.parse(args[0], database);
    }
    catch(ArrayIndexOutOfBoundsException exception) {
      System.out.println("SURLY takes one command line argument the name of the txt file");
    }
  }

  // Intialize DBMS
  public static void init() {
    SurlyDatabase database = SurlyDatabase.getInstance();
  }
}
