/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Relation command for surly database
*/
import java.util.Scanner;

public class RelationCommand extends BaseCommand {
  SurlyDatabase database = SurlyDatabase.getInstance();
  String name = "RELATION";
  String tupleName;
  String attributeName;
  String datatype;
  int len;

  public void run(String params) {
    String[] tokens = params.trim().split("\\s\\(|\\)|,\\s|;|\\s");
    tupleName = tokens[0];
    for (int i = 1; i < tokens.length; i++) {
      attributeName = tokens[i++];
      datatype = tokens[i++];
      len = Integer.parseInt(tokens[i]);
    }

  }
  public String getName() {
    return name;
  }
}
