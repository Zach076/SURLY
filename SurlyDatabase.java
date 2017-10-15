/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  SURLY Database
*/

import java.util.LinkedList;

public class SurlyDatabase {

  private static SurlyDatabase database = new SurlyDatabase();
  private static LinkedList<ICommand> commands = new LinkedList<ICommand>();
  private static LinkedList<Relation> relations = new LinkedList<Relation>();
  private static RelationCommand relation = new RelationCommand();
  private static InsertCommand insert = new InsertCommand();
  private static PrintCommand print = new PrintCommand();

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
    commands.add(relation);
    commands.add(insert);
    commands.add(print);
  }


  public void insert() {

  }

	public void print() {
	}

  public Relation findRelation(String name) {
    return null;
  }
}
