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

    Relation currRelation = new Relation(relationName);
    int index = relations.indexOf(currRelation);
    if (index == -1) {
      relations.add(currRelation);
    }
    else {
      /* Specificatoin says if there is an existing relation to DESTROY the old
       * one but we don't have that command so we'll have to decide what we want
       * to do
       */
      currRelation = relations.get(index);
    }

    for (int i = 1; i < tokens.length; i++) {
      tupleName = tokens[i++];
      datatype = tokens[i++];
      maxlen = Integer.parseInt(tokens[i]);

      currRelation.insert(tupleName, datatype, maxlen);
    }
  }
  public String getName() {
    return name;
  }
}
