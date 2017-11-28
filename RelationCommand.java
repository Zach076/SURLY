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

    String[] tokens = params.trim().split("\\s\\(|\\)|,\\s|;|\\s");
    String relationName = tokens[0];

    if (database.findRelation(relationName) == -1) {
      createRelation(tokens);
    }
    else {
      //System.out.println("The " + relationName + " relation already exists");
      database.runBasicCommand("DESTROY " + relationName);
      createRelation(tokens);
    }
  }
  public String getName() {
    return "RELATION";
  }

  private void createRelation(String[] line) {
    String relationName = line[0];
    try {
      String tupleName;
      String datatype;
      int maxlen;
      LinkedList<Relation> relations = database.getRelations();

      Relation currRelation = new Relation(relationName);
      relations.add(currRelation);
      for (int i = 1; i < line.length; i++) {
        tupleName = line[i++];
        datatype = line[i++];
        maxlen = Integer.parseInt(line[i]);
        if (checkAttributeFormat(relationName, tupleName, datatype, maxlen)) {
          currRelation.addDomain(tupleName, datatype, maxlen);
        }
      }
    }
    catch(NumberFormatException exception) {
      System.out.println("Max length of relation " + relationName + " isn't a number");
      database.runBasicCommand("DESTROY " + relationName);
    }
    catch(ArrayIndexOutOfBoundsException exception) {
      System.out.println("Relation " + relationName + "s attribute's are formatted incorrectly");
      database.runBasicCommand("DESTROY " + relationName);
    }
  }


  private boolean checkAttributeFormat(String relation, String tuple, String datatype, int length) {
    if (!(datatype.equals("NUM") || datatype.equals("CHAR"))) {
      System.out.println("Relation must have an attribute format of NUM or CHAR");
      database.runBasicCommand("DESTROY " + relation);
    }
    return true;
  }
}
