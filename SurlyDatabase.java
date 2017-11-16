/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  SURLY Database
*/

import java.util.LinkedList;

public class SurlyDatabase {

  private static final SurlyDatabase database = new SurlyDatabase();
  private static final LinkedList<ICommand> commands = new LinkedList<ICommand>();
  private static final LinkedList<Relation> relations = new LinkedList<Relation>();

  private SurlyDatabase() {
  }

  public static SurlyDatabase getInstance() {
    return database;
  }

  public static LinkedList<ICommand> getCommands() {
    if (commands.size() == 0) {
      addCommands();
    }
    return commands;
  }

  public static LinkedList<Relation> getRelations() {
    return relations;
  }

  public static void addCommands() {
    commands.add(new RelationCommand());
    commands.add(new InsertCommand());
    commands.add(new PrintCommand());
    commands.add(new DestroyCommand());
    commands.add(new DeleteCommand());
    commands.add(new SelectCommand());
    commands.add(new ProjectCommand());
    commands.add(new JoinCommand());
  }

  public int findRelation(String relationName) {
    for (int index = 0; index < relations.size(); index++) {
      if (relations.get(index).getName().equals(relationName)) {
        return index;
      }
    }
    return -1;
  }
}
