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
      currRelation.insertTuple();
      Tuple currRow = currRelation.getTuples().getLast();
      if ((tokens.length - 1) != currRelation.getDomain().size()) {
        // Incorrect amount of attriobutes
        System.out.println("ERRMSG4");
        System.exit(1);
      }
      for (int x = 1; x < tokens.length; x++) {
        if (tokens[x].length() > currRelation.getDomain().get(x - 1).getMaxLen() ) {
          System.out.println(tokens[x] + " exceeds maxlength of " + currRelation.getDomain().get(x - 1).getMaxLen());
        } else {
          currRow.getAttributes().add(new Attribute(tokens[x]));
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
}
