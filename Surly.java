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
    parser.parse(args[1], database);
  }

  // Intialize DBMS
  public static void init() {
    SurlyDatabase database = SurlyDatabase.getInstance();
  }
}
