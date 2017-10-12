/*
  Stephen Hyde-Donohue
  SURLY 1
  WWU CSCI 330
  Fall 2017
  SURLY Database
*/

import java.util.LinkedList;

public class SurlyDatabase {

  private static SurlyDatabase database = new SurlyDatabase();
  private LinkedList<Tuple> relations = new LinkedList<Tuple>();

  private SurlyDatabase() {
  }

  public static SurlyDatabase getInstance() {
    return database;
  }

  public void insert() {
  }

	public void print() {
	}

  public Relation findRelation(String name) {
    return null;
  }
}
