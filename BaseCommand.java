/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Abstract class for command classes
*/


abstract public class BaseCommand implements ICommand {
  SurlyDatabase database = SurlyDatabase.getInstance();
  private String name;
  abstract public void run(String params);
  abstract public String getName();

}
