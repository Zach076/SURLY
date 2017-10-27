/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Relation command for surly database
*/
import java.util.Scanner;
import java.util.LinkedList;

public class RelationCommand extends BaseCommand {
  private static SurlyDatabase database = SurlyDatabase.getInstance();
  private static LinkedList<Relation> relations = new LinkedList<Relation>();
  private String name = "RELATION";
  private String relationName;
  private String tupleName;
  private String datatype;
  private int maxlen;

  public void run(String params) {
    relations = database.getRelations();
    String[] tokens = params.trim().split("\\s\\(|\\)|,\\s|;|\\s");
    relationName = tokens[0];

    boolean found = false;
    for (int j = 0; j < relations.size(); j++) {
      if (relations.get(j).getName().equals(relationName)) {
        found = true;
      }
    }
    if (!found) {
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
    return name;
  }
}
