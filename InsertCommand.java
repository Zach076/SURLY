/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Insert command for surly database
*/
import java.util.Scanner;

public class InsertCommand extends BaseCommand {
  private String name = "INSERT";

  public void run(String params) {
    String[] tokens = params.trim().split("\\s\\(|\\)|,\\s|;|\\s");
    String relationName = tokens[0];

    LinkedList<Relation> currDatabase = SurlyDatabase.getRelations();
    int databaseLen = currDatabase.indexOf(currDatabase.getLast())
    int iteratorVal = 0;
    String currRelationName;
    Tuple currRow;

    while (iteratorVal < databaseLen) {
      currRelationName = currDatabase.indexOf(iteratorVal).getRelName();
      if (currRelationName = relationName) {

        currRow = currDatabase.indexOf(iteratorVal).getLast();
        Relation.insertTuple();
        for (int x = 0; x < tokens.length; x++) {
          currRow.insertAttrib(tokens[x]);
        }
        iteratorVal = databaseLen;
      } else iteratorVal++;
    }
  }

  public String getName() {
    return name;
  }
}
