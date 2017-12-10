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

    LinkedList<Relation> relations = database.getRelations();
    String[] tokens = params.trim().split("\\s|;");

    String relationName = tokens[0];
    int index = database.findRelation(relationName);
    String[] attributes = parseAttributes(tokens);

    if (index >= 0) {
      Relation currRelation = relations.get(index);
      if (runChecks(currRelation, attributes)) {
        attributes = enforceMaxlen(currRelation, attributes);
        addAttributes(currRelation, attributes);
      }
    } else {
      System.out.println("Relation \'" + relationName + "\' doesn't exist");
    }
  }

  public String getName() {
    return name;
  }

  private String[] parseAttributes(String[] tokens) {
    String[] quotedAttributes = removeFirstToken(tokens);
    for (int i = 0; i < quotedAttributes.length; i++) {
      quotedAttributes = quoteHandler(quotedAttributes);
    }
    String[] attributes = quotedAttributes;
    return attributes;
  }
  private String[] removeFirstToken(String[] tokens) {
    String newTokens[] = new String[tokens.length - 1];
    for (int i = 1; i < tokens.length; i++) {
      newTokens[i - 1] = tokens[i];
    }
    return newTokens;
  }

  private String[] quoteHandler (String[] tokens) {
    boolean foundQuote = false;
    boolean foundEndQuote = false;
    int startQuote = 0;
    int endQuote = 0;

    for (int j = 0; j < tokens.length; j++) {
      if (tokens[j].charAt(0) == '\'' && !foundQuote) {
        foundQuote = true;
        startQuote = j;
      }
      if (tokens[j].charAt(tokens[j].length() - 1) == '\'' && foundQuote && !foundEndQuote) {
        endQuote = j;
        foundEndQuote = true;
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

  private boolean runChecks(Relation relation, String[] attributes) {
    if (!checkAttributeLength(relation, attributes)) {
      return false;
    }
    if (!checkDatatype(relation, attributes)){
      return false;
    }
    return true;
  }

  private boolean checkAttributeLength(Relation relation, String[] attributes) {
    if (attributes.length != relation.getDomain().size()) {
      // Incorrect amount of attributes
      System.out.println("ERRMSG4");
      return false;
    }
    return true;
  }

  private boolean checkDatatype(Relation relation, String[] attributes) {
    String currDatatype;
    String currAttribute;
    for (int i = 0; i < attributes.length; i++) {
      currAttribute = attributes[i];
      currDatatype = relation.getDomain().get(i).getDatatype();
      if (currDatatype.equals("NUM")) {
        for (int j = 0; j < currAttribute.length(); j++) {
          if (!Character.isDigit(currAttribute.charAt(j))) {
            System.out.println(currAttribute.charAt(j) + " in " + currAttribute + " is not a \'NUM\'");
            return false;
          }
        }
      }
      else if (!currDatatype.equals("CHAR")) {
        System.out.println("Invalid stored datatype in a relation while inputting");
        return false;
      }
    }
    return true;
  }

  private void addAttributes(Relation relation, String[] attributes) {
    relation.insertTuple();
    Tuple currRow = relation.getTuples().getLast();
    for (int x = 0; x < attributes.length; x++) {
      currRow.getAttributes().add(new Attribute(attributes[x]));
    }
  }

  private String[] enforceMaxlen(Relation relation, String[] attributes) {
    String[] truncatedAttributes = new String[attributes.length];
    String truncatedAttribute;
    int maxLength;
    String datatype;

    for (int i = 0; i < attributes.length; i++) {
      maxLength = relation.getDomain().get(i).getMaxLen();
      datatype = relation.getDomain().get(i).getDatatype();
      truncatedAttribute = attributes[i];
      if (attributes[i].length() > maxLength) {
        if (datatype.equals("NUM")) {
          System.out.println("ERRMSG3");
          truncatedAttribute = truncateLeft(maxLength, attributes[i]);
        } else {
          System.out.println("ERRMSG1");
          truncatedAttribute = truncateRight(maxLength, attributes[i]);
        }
      }
      truncatedAttributes[i] = truncatedAttribute;
    }
    return truncatedAttributes;
  }

  private String truncateLeft(int newLength, String word) {
    int startIndex = word.length() - newLength;
    int endIndex = word.length();
    return word.substring(startIndex, endIndex);
  }

  private String truncateRight(int newLength, String word) {
    int startIndex = 0;
    int endIndex = newLength;
    return word.substring(startIndex, endIndex);
  }
}
