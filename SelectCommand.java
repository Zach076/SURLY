/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Select where command for surly database
*/
import java.util.LinkedList;

public class SelectCommand extends BaseCommand {
    private static final SurlyDatabase database = SurlyDatabase.getInstance();
    private final String name = "SELECT";
    public void run(String params) {

    }
    public String getName() {
        return name;
    }
}