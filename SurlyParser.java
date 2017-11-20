/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  File parser for the SURLY DB
*/

import java.util.Scanner;
import java.io.*;
import java.util.LinkedList;

public class SurlyParser {

  private static SurlyParser parser = new SurlyParser();
  private SurlyParser() {
  }

  /* Parses text file for SURLY commands of the format COMMANDNAME parameters.
   * Checks each command against list of known commands. If found the command is
   * run. If not the the user is notified.
   */
  public void parse(String filename, SurlyDatabase database) {
    File file = new File(filename);
    String line;
    try {
      Scanner scan = new Scanner(file);
      while(scan.hasNextLine()) {
        line = scan.nextLine();

        //System.out.println(line);
        // If command is unknown let the user know
        if (!(checkForCommands(line) || line.equals(""))) {
          System.out.println("SURLY doesn't understand the command \'" + line.substring(0, line.indexOf(' ')) + '\'');
          //System.out.println(scan.nextLine());
        }

      }
    }
    catch(FileNotFoundException exception) {
      System.out.println("The file " + file.getPath() + " doesn't exist.");
    }
  }

  public static SurlyParser getInstance() {
    return parser;
  }

  private boolean checkForCommands(String line) {
    return checkBasicCommands(line) || checkAssignmentCommands(line);
  }

  private boolean checkBasicCommands(String line) {
    LinkedList<ICommand> basicCommands = SurlyDatabase.getBasicCommands();
    String command = "";

    Scanner scan = new Scanner(line);
    if (scan.hasNext()) {
      command = scan.next();
    }
    for (int i  = 0; i < basicCommands.size();i++) {
      // Check for known commands
      if (basicCommands.get(i).getName().equals(command)) {
        //System.out.println(basicCommands.get(i).getName());
        basicCommands.get(i).run(scan.nextLine());
        return true;
      }
    }
    return false;
  }
  private boolean checkAssignmentCommands(String line) {

    LinkedList<ICommand> assignmentCommands = SurlyDatabase.getAssignmentCommands();
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
        //System.out.println("!!Assignment!!");
        if (assignmentCommands.get(i).getName().equals(assignCommand)) {
          assignmentCommands.get(i).run(line);
          return true;
        }
      }
    }
    return false;
  }
}
