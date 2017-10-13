/*
  Stephen Hyde-Donohue
  SURLY 1
  WWU CSCI 330
  Fall 2017
  File parser for the SURLY DB
*/

import java.util.Scanner;
import java.io.*;

public class SurlyParser {

  public void parse (String filename) {
    File file = new File(filename);
    try {
      Scanner scan = new Scanner(file);
      String command;
      while(scan.hasNextLine()) {
        command = scan.next();

        /* 
          Simple not easily extendable version. Going to implement an abstract command
          class and have the parser to iterate through commands find a match and then run.
        */

        if ("RELATION".equals(command)) {
          //handle insert commands
          //System.out.println("RELATION COMMAND");
        }
        else if ("INSERT".equals(command)) {
          //handle insert commands
          //System.out.println("INSERT COMMAND");
        }
        else if ("PRINT".equals(command)) {
          //handle insert commands
          //System.out.println("PRINT COMMAND");
        }
        else {
          System.out.println("SURLY doesn't understand the command \'" + command + '\'');
        }
        scan.nextLine();

      }
    }
    catch(FileNotFoundException exception) {
      System.out.println("The file " + file.getPath() + " doesn't exist.");
    }
  }

}
