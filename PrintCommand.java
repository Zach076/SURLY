/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Print command for surly database
*/
import java.util.Scanner;
import java.util.LinkedList;

public class PrintCommand extends BaseCommand {
  private String name = "PRINT";
  public void run(String params) {
    String[] tokens = params.trim().split("\\s\\(|\\)|,\\s|;|\\s");
    String relationName = tokens[0];

    LinkedList<Relation> currDatabase = SurlyDatabase.getRelations();
    int databaseLen = currDatabase.indexOf(currDatabase.getLast());
    int iteratorVal = 0;
    Relation currRelation;

    while (iteratorVal < databaseLen) {
      currRelation = currDatabase.get(currDatabase.indexOf(iteratorVal));
      if (currRelation.getName().equals(relationName)) {
        currRelation.print();
      }
    }
  }
  public String getName() {
    return name;
  }
}
