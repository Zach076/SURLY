/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Insert command for surly database
*/
import java.util.LinkedList;

public class InsertCommand extends BaseCommand {
  private final String name = "INSERT";
  private static final SurlyDatabase database = SurlyDatabase.getInstance();

  public void run(String params) {

    String[] tokens = quoteHandler(params.trim().split("\\s|;"));
    String relationName = tokens[0];

    LinkedList<Relation> relations = SurlyDatabase.getRelations();

    int index = database.findRelation(relationName);

    if (index >= 0) {
      Relation currRelation = relations.get(index);
      if ((tokens.length - 1) != currRelation.getDomain().size()) {
        // Incorrect amount of attributes
        System.out.println("ERRMSG4");
        System.exit(1);
      }
      currRelation.insertTuple();
      Tuple currRow = currRelation.getTuples().getLast();
      for (int x = 1; x < tokens.length; x++) {
        if (checkDatatype(tokens[x], currRelation, x)) {
          String value = checkMaxlen(tokens[x], currRelation, x);
          currRow.getAttributes().add(new Attribute(value));
        } else {
          currRelation.getTuples().removeLast();
          System.out.println("ERRMSG2");
        }
      }
    } else {
      System.out.println("Invalid relation name \'" + relationName + "\'");
    }
  }

  public String getName() {
    return name;
  }

  private String[] quoteHandler (String[] tokens) {
    boolean foundQuote = false;
    int startQuote = 0;
    int endQuote = 0;

    for (int j = 1; j < tokens.length; j++) {
      if (tokens[j].charAt(0) == '\'' && !foundQuote) {
        foundQuote = true;
        startQuote = j;
      }
      if (tokens[j].charAt(tokens[j].length() - 1) == '\'' && foundQuote) {
        endQuote = j;
      }
    }
    if (foundQuote) {
      for (int k = startQuote + 1; k <= endQuote; k++) {
        tokens[startQuote] += " " + tokens[k];
      }
      if (tokens.length > endQuote) {
        for (int h = endQuote + 1; h < tokens.length; h++) {
          tokens[startQuote + (h - endQuote)] = tokens[h];
        }
      }
      tokens[startQuote] = tokens[startQuote].replaceAll("\'","");
    }
    String[] newTokens = new String[tokens.length - (endQuote - startQuote)];
    for (int i = 0; i < newTokens.length; i++) {
      newTokens[i] = tokens[i];
    }
    return newTokens;
  }

  private boolean checkDatatype(String data, Relation relation, int index) {
    String datatype = relation.getDomain().get(index - 1).getDatatype();
    if (datatype.equals("NUM")) {
      for (int i = 0; i < data.length(); i++) {
        if (!Character.isDigit(data.charAt(i))) {
          System.out.println(data.charAt(i) + " in " + data + " is not a \'NUM\'");
          return false;
        }
      }
    } else if (datatype.equals("CHAR")) {
      return true;
    } else {
      System.out.println("Invalid stored datatype in a relation while inputting");
      return false;
    }
    return true;
  }
  private String checkMaxlen(String data, Relation relation, int index) {
    int maxLength = relation.getDomain().get(index - 1).getMaxLen();
    String datatype = relation.getDomain().get(index - 1).getDatatype();

    if (data.length() >  maxLength) {
      int startIndex = 0;
      int endIndex = 0;
      if (datatype.equals("NUM")) {
        //truncate to the left
        startIndex = data.length() - maxLength;
        endIndex = data.length();
        System.out.println(startIndex + ", " + endIndex);
        System.out.println("ERRMSG3");
      } else {
        //truncate to the left
        startIndex = 0;
        endIndex = maxLength;
        System.out.println("ERRMSG1");

      }
      String substring = data.substring(startIndex, endIndex);
      return substring;

    }
    return data;
  }
}
