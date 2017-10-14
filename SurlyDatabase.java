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
  private LinkedList<Tuple> relations = new LinkedList<Tuple>();
  private RelationCommand relation = new RelationCommand();
  private InsertCommand insert = new InsertCommand();
  private PrintCommand print = new PrintCommand();

  private SurlyDatabase() {
    commands.add(relation);
    commands.add(insert);
    commands.add(print);
  }

  public static SurlyDatabase getInstance() {
    return database;
  }

  public static LinkedList<ICommand> getCommands() {
    return commands;
  }


  public void insert() {
  }

	public void print() {
	}

  public Relation findRelation(String name) {
    return null;
  }
}
