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
    String[] tokens  = params.trim().split("\\s\\(|\\)|,\\s|;|\\s");
    String relationName = tokens[0];

    boolean foundQuote = false;
    int startQuote = 0;
    int endQuote = 0;

    for (int j = 1; j < tokens.length; j++) {
      if (tokens[j].charAt(0) == '\'' && foundQuote == false) {
        foundQuote = true;
        startQuote = j;
      }
      if (tokens[j].charAt(tokens[j].length() - 1) == '\'' && foundQuote == true) {
        endQuote = j;

      }
    }
    if (foundQuote) {
      for (int k = startQuote + 1; k <= endQuote; k++) {
        tokens[startQuote] += " " + tokens[k];
      }
      // System.out.println(tokens[startQuote]);
      if (tokens.length > endQuote) {
        for (int h = endQuote + 1; h < tokens.length; h++) {
          tokens[startQuote + (h - endQuote)] = tokens[h];
        }
      }
      tokens[startQuote] = tokens[startQuote].replaceAll("\'","");
      //tokens
    }


    foundQuote = false;


    LinkedList<Relation> currDatabase = SurlyDatabase.getRelations();
    int databaseLen = currDatabase.size();
    int iteratorVal = 0;
    Relation currRelation;
    Tuple currRow;


    while (iteratorVal < databaseLen) {
      currRelation = currDatabase.get(iteratorVal);
      if (currRelation.getName().equals(relationName)) {
        //System.out.println(currRelation.getName());

        currRelation.insertTuple();
        currRow = currRelation.getTuples().getLast();
        for (int x = 1; x < tokens.length; x++) {
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
