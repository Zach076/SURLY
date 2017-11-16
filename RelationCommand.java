/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Relation command for surly database
*/
import java.util.LinkedList;

public class RelationCommand extends BaseCommand {
  private static final SurlyDatabase database = SurlyDatabase.getInstance();
  private final String name = "RELATION";

  public void run(String params) {
    int maxlen;
    String relationName;
    String tupleName;
    String datatype;
    LinkedList<Relation> relations = database.getRelations();
    String[] tokens = params.trim().split("\\s\\(|\\)|,\\s|;|\\s");
    relationName = tokens[0];

    if (database.findRelation(relationName) == -1) {
      Relation currRelation = new Relation(relationName);
      relations.add(currRelation);
      for (int i = 1; i < tokens.length; i++) {
        tupleName = tokens[i++];
        datatype = tokens[i++];
        maxlen = Integer.parseInt(tokens[i]);

        currRelation.addDomain(tupleName, datatype, maxlen);
      }
    }
    else {
      System.out.println("The " + relationName + " relation already exists");
    }
  }
  public String getName() {
    return "RELATION";
  }
}
