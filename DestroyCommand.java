/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Destroy command for surly database
*/

public class DestroyCommand extends BaseCommand {
  private static final SurlyDatabase database = SurlyDatabase.getInstance();
  private final String name = "DESTROY";
  public void run(String params) {
    String[] tokens = params.trim().split("\\s|;");
    String relationName;
    int index;

    for (int i = 0; i < tokens.length; i++) {
      relationName = tokens[i];
      index = database.findRelation(relationName);
      if (index >= 0) {
        SurlyDatabase.getRelations().remove(index);
      } else {
        System.out.println("The relation \'" + relationName + "\' doesn't exist");
      }
    }
  }
  public String getName() {
    return name;
  }
}
