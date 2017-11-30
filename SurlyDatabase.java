/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  SURLY Database
*/

import java.util.LinkedList;
import java.util.Scanner;

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
    return basicCommands;
  }
  public static LinkedList<ICommand> getAssignmentCommands() {

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

  public boolean runBasicCommand(String line) {
    if (basicCommands.size() == 0) {
      addCommands();
    }
    String command = "";

    Scanner scan = new Scanner(line);
    if (scan.hasNext()) {
      command = scan.next();
    }
    for (int i  = 0; i < basicCommands.size();i++) {
      // Check for known commands
      if (basicCommands.get(i).getName().equals(command)) {
        basicCommands.get(i).run(scan.nextLine());
        return true;
      }
    }
    return false;
  }
  public boolean runAssignmentCommand(String line) {
    if (basicCommands.size() == 0) {
      addCommands();
    }
    String variable = "";
    String assignment = "";
    String assignCommand = "";
    Scanner scan = new Scanner(line);

    if (scan.hasNext()) {
      variable = scan.next();
    }
    if (scan.hasNext()) {
      assignment = scan.next();
    }
    if (scan.hasNext()) {
      assignCommand = scan.next();
    }
    for (int i  = 0; i < assignmentCommands.size();i++) {
      // Check for known commands
      if (assignment.equals("=")) {
        if (assignmentCommands.get(i).getName().equals(assignCommand)) {
          assignmentCommands.get(i).run(line);
          return true;
        }
      }
    }
    return false;
  }

  public boolean copyOverTuples(Relation sourceRel, Relation destinationRel, String[] tupleNames) {
    String destName = destinationRel.getName();
    int srcIndex = 0;

    LinkedList<Tuple> sourceTuples = sourceRel.getTuples();
    for (int j = 0; j < sourceTuples.size(); j++) {
      StringBuilder attributes = new StringBuilder();
      for (int i = 0; i < tupleNames.length; i++) {
        srcIndex = sourceRel.findDomainNode(tupleNames[i]);
        if (srcIndex == -1) {
          return false;
        }
        attributes.append(sourceTuples.get(j).getAttrib(srcIndex).getValue() + " ");
      }
      runBasicCommand("INSERT " + destName + " " + attributes.toString());
    }
    return true;
  }
}
