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
        // If command is unknown let the user know
        if (!(checkForCommands(line, database) || line.equals(""))) {
          System.out.println("SURLY doesn't understand the command \'" + line.substring(0, line.indexOf(' ')) + '\'');
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

  private boolean checkForCommands(String line, SurlyDatabase database) {
    return database.runBasicCommand(line) || database.runAssignmentCommand(line);
  }
}
