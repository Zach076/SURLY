/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Interface for commands
*/

public interface ICommand {
    public void run(String params);
    public String getName();
}
