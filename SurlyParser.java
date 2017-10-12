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
      int count = 0;
      while(scan.hasNextLine()) {

      }
    }
    catch(FileNotFoundException exception) {
      System.out.println("The file " + file.getPath() + " doesn't exist.");
    }
  }

}
