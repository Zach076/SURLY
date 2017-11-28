/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Print command for surly database
*/
import java.util.LinkedList;

public class PrintCommand extends BaseCommand {
  private final String name = "PRINT";
  public void run(String params) {
    String[] tokens = params.trim().split(",\\s|;|\\s");

    LinkedList<Relation> currDatabase = SurlyDatabase.getRelations();
    int databaseLen = currDatabase.size();
    int iteratorVal;
    Relation currRelation;
    String relationName;
    
    System.out.println();
    for (int x = 0; x < tokens.length ; x++) {
      iteratorVal = 0;
      relationName = tokens[x];
      while (iteratorVal < databaseLen) {
        currRelation = currDatabase.get(iteratorVal);
        if (currRelation.getName().equals(relationName)) {
          currRelation.print();
        }
        iteratorVal++;
      }
    }
  }
  public String getName() {
    return name;
  }
}
