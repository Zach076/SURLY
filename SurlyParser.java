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
    LinkedList<ICommand> commands = SurlyDatabase.getCommands();
    try {
      Scanner scan = new Scanner(file);
      String command;
      boolean ran;
      while(scan.hasNextLine()) {
        command = scan.next();
        ran = false;
        for (int i  = 0; i < commands.size();i++) {
          // Check for known commands
          if (commands.get(i).getName().equals(command)) {
            commands.get(i).run(scan.nextLine());
            ran = true;
          }
        }
        // If command is unknown let the user know
        if (!ran) {
          System.out.println("SURLY doesn't understand the command \'" + command + '\'');
          scan.nextLine();
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

}
