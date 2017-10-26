/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Insert command for surly database
*/
import java.util.Scanner;
import java.util.LinkedList;

public class InsertCommand extends BaseCommand {
  private String name = "INSERT";

  public void run(String params) {
    String[] tokens = params.trim().split("\\s\\(|\\)|,\\s|;|\\s");
    String relationName = tokens[0];

    LinkedList<Relation> currDatabase = SurlyDatabase.getRelations();
    int databaseLen = currDatabase.indexOf(currDatabase.getLast());
    int iteratorVal = 0;
    Relation currRelation;
    Tuple currRow;


    while (iteratorVal < databaseLen) {
      currRelation = currDatabase.get(currDatabase.indexOf(iteratorVal));
      if (currRelation.getName().equals(relationName)) {

        currRow = currRelation.getTuples().getLast();
        //currRelation.insertTuple();
        for (int x = 0; x < tokens.length; x++) {
          Attribute newattribute = new Attribute(tokens[x]);
          currRow.getAttributes().add(newattribute);
        }
        iteratorVal = databaseLen;
      } else iteratorVal++;
    }
  }

  public String getName() {
    return name;
  }
}
