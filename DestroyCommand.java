/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Destroy command for surly database
*/
import java.util.LinkedList;

public class DestroyCommand extends BaseCommand {
    private static final SurlyDatabase database = SurlyDatabase.getInstance();
    private final String name = "DESTROY";
    public void run(String params) {

    }
    public String getName() {
        return name;
    }
}
