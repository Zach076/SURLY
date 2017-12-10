/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Delete command for surly database
*/
import java.util.LinkedList;

public class DeleteCommand extends BaseCommand {
    private static final SurlyDatabase database = SurlyDatabase.getInstance();
    private final String name = "DELETE";
    public void run(String params) {
      //System.out.println(params);
      String[] tokens = params.trim().split("\\s|;");
      String relationName = tokens[0];
      int index;
      index = database.findRelation(relationName);
      LinkedList<Relation> relations = SurlyDatabase.getRelations();
      if (index < 0) {
        System.out.println("The relation \'" + relationName + "\' doesn't exist");
        System.exit(0);
      }

      Relation currRelation = relations.get(index);
      Tuple currRow;

      int z = 0;

      while(z < currRelation.getSize()) {
        currRow = currRelation.getAt(z);

        if(tokens.length == 1){
            SurlyDatabase.getRelations().get(index).getTuples().remove(z);
        } else if (currRelation.isTrue(currRow, tokens)) {
            SurlyDatabase.getRelations().get(index).getTuples().remove(z);
        } else z++;
      }
    }
    public String getName() {
        return name;
    }
}
