/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Select where command for surly database
*/
import java.util.LinkedList;

public class SelectCommand extends BaseCommand {
    private static final SurlyDatabase database = SurlyDatabase.getInstance();
    private final String name = "SELECT";
    public void run(String params) {
      //System.out.println(params);
      String[] tokens = params.trim().split("\\s|;");
      String relationName = tokens[3];
      int index = -1;
      index = database.findRelation(relationName);
      LinkedList<Relation> relations = database.getRelations();
      if (index < 0) {
        System.out.println("The relation \'" + relationName + "\' doesn't exist");
      }

      Relation currRelation = relations.get(index);
      Tuple currRow;

      database.runBasicCommand("RELATION " + tokens[0] + " " + formatDomain(currRelation) + ";");

      for(int z = 0; z < currRelation.getSize(); z++) {
        currRow = currRelation.getAt(z);

        if (currRelation.isTrue(currRow, formatTokens(tokens) )) {
          database.runBasicCommand("INSERT " + tokens[0] + formatAttributes(currRow) + ";");
        }
      }
    }

    public String formatAttributes(Tuple aRow) {
      String returnValue = "";
      for(int i = 0; i < aRow.getAttributes().size(); i++) {
        returnValue = returnValue + " '" + aRow.getAttrib(i).getValue() + "'";
      }
      return returnValue;
    }

    public String formatDomain(Relation aRelation) {
      String returnValue = "(";
      returnValue = returnValue + aRelation.getDomain().get(0).getName();
      returnValue = returnValue + " " + aRelation.getDomain().get(0).getDatatype();
      returnValue = returnValue + " " + aRelation.getDomain().get(0).getMaxLen();
      for(int i = 1; i < aRelation.getDomain().size(); i++) {
        returnValue = returnValue + ", " + aRelation.getDomain().get(i).getName();
        returnValue = returnValue + " " + aRelation.getDomain().get(i).getDatatype();
        returnValue = returnValue + " " + aRelation.getDomain().get(i).getMaxLen();
      }
      returnValue = returnValue + ")";
      return returnValue;
    }

    public String[] formatTokens(String[] aTokens) {
      String[] newTokens = new String[aTokens.length - 2];
      for(int i = 2; i < aTokens.length; i++) {
        newTokens[i-2] = aTokens[i];
      }
      return newTokens;
    }

    public String getName() {
      return name;
    }
}
