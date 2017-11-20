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
  private static final LinkedList<ICommand> basicCommands = new LinkedList<ICommand>();
  private static final LinkedList<ICommand> assignmentCommands = new LinkedList<ICommand>();
  private static final LinkedList<Relation> relations = new LinkedList<Relation>();

  private SurlyDatabase() {
  }

  public static SurlyDatabase getInstance() {
    return database;
  }

  public static LinkedList<ICommand> getBasicCommands() {
    if (basicCommands.size() == 0) {
      addCommands();
    }
    return basicCommands;
  }
  public static LinkedList<ICommand> getAssignmentCommands() {
    if (basicCommands.size() == 0) {
      addCommands();
    }
    return assignmentCommands;
  }

  public static LinkedList<Relation> getRelations() {
    return relations;
  }

  public static void addCommands() {
    basicCommands.add(new RelationCommand());
    basicCommands.add(new InsertCommand());
    basicCommands.add(new PrintCommand());
    basicCommands.add(new DestroyCommand());
    basicCommands.add(new DeleteCommand());
    assignmentCommands.add(new SelectCommand());
    assignmentCommands.add(new ProjectCommand());
    assignmentCommands.add(new JoinCommand());
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
