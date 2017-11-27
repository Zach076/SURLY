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
      //System.out.println(params);
      String[] tokens = params.trim().split("\\s|;");
      String relationName = tokens[0];
      int index = -1;
      index = database.findRelation(relationName);
      if (index < 0) {
        System.out.println("The relation \'" + relationName + "\' doesn't exist");
        break;
      }

      int truthMatrixLength = (int)((tokens.length - 1) / 4);
      boolean[] truthMatrix = new boolean[truthMatrixLength];

      Relation currRelation = relations.get(index);
      Tuple currRow;
      LinkedList<DomainNode> domain = currRelation.getDomain();

      int truthSetIndex;
      int attributeIndex = 0;
      for(int z = 0; z < currRelation.size(); z++) {
        currRow = currRelation.get(z);
        for(int i = 0; i < truthMatrixLength; i++) {
          helperInt = (i * 4) + 1;
          if (tokens[helperInt + 2] == "="){
            for(int x = 0; x < domain.size(); x++) {
              if (tokens[helperInt + 1] == domain.get(x).getName())
                attributeIndex = x;
            }
            for(int x = 0; x < currRelation.size(); x++) {
              currRow = currRelation.getTuples().get(x);
              if (currRow.getAttrib(attributeIndex).getValue() == tokens[helperInt+3]) {
                truthMatrix[i] = true;
              }
            }
          } else if (tokens[helperInt + 2] == ">=") {
            for(int x = 0; x < domain.size(); x++) {
              if (tokens[helperInt + 1] == domain.get(x).getName())
                attributeIndex = x;
            }
            for(int x = 0; x < currRelation.size(); x++) {
              currRow = currRelation.getTuples().get(x);
              if (currRow.getAttrib(attributeIndex).getValue() >= tokens[helperInt+3]) {
                truthMatrix[i] = true;
              }
            }
          } else if (tokens[helperInt + 2] == "<=") {
            for(int x = 0; x < domain.size(); x++) {
              if (tokens[helperInt + 1] == domain.get(x).getName())
                attributeIndex = x;
            }
            for(int x = 0; x < currRelation.size(); x++) {
              currRow = currRelation.getTuples().get(x);
              if (currRow.getAttrib(attributeIndex).getValue() <= tokens[helperInt+3]) {
                truthMatrix[i] = true;
              }
            }
          } else if (tokens[helperInt + 2] == ">") {
            for(int x = 0; x < domain.size(); x++) {
              if (tokens[helperInt + 1] == domain.get(x).getName())
                attributeIndex = x;
            }
            for(int x = 0; x < currRelation.size(); x++) {
              currRow = currRelation.getTuples().get(x);
              if (currRow.getAttrib(attributeIndex).getValue() > tokens[helperInt+3]) {
                truthMatrix[i] = true;
              }
            }
          } else if (tokens[helperInt + 2] == "<") {
            for(int x = 0; x < domain.size(); x++) {
              if (tokens[helperInt + 1] == domain.get(x).getName())
                attributeIndex = x;
            }
            for(int x = 0; x < currRelation.size(); x++) {
              currRow = currRelation.getTuples().get(x);
              if (currRow.getAttrib(attributeIndex).getValue() < tokens[helperInt+3]) {
                truthMatrix[i] = true;
              }
            }
          } else if (tokens[helperInt + 2] == "!=") {
            for(int x = 0; x < domain.size(); x++) {
              if (tokens[helperInt + 1] == domain.get(x).getName())
                attributeIndex = x;
            }
            for(int x = 0; x < currRelation.size(); x++) {
              currRow = currRelation.getTuples().get(x);
              if (currRow.getAttrib(attributeIndex).getValue() != tokens[helperInt+3]) {
                truthMatrix[i] = true;
              }
            }
          }
        }

        if(truthMatrixLength == 1) {
          if(truthMatrix[0] == true) {
            //add tuple to temp relation
          }
        } else {
          boolean foundOr = false;
          String[] operatorIndex = new String[truthMatrixLength - 1];
          for(int y = 1; y < truthMatrixLength; y++) {
            operatorIndex[y-1] = tokens[(y * 4) + 1]
          }

          //if any false is detected in an 'and' statement, set both to false to carry the operations boolean value to potential operators on both sides
          for(int y = 0; y < (truthMatrixLength - 1); y++) {
            if(operatorIndex[y] == "and") {
              if(truthMatrix[y] == false || truthMatrix[y+1] == false) {
                truthMatrix[y] = false;
                truthMatrix[y+1] = false;
              }
            }
          }

          for(int y = 0; y < (truthMatrixLength - 1); y++) {
            if(operatorIndex[y] == "or") {
              foundOr = true;
            }
          }

          if(foundOr) {
            for(int y = 0; y < truthMatrixLength; y++) {
              if(truthMatrix[y]) {
                //add currTuple to temp relation
              }
            }
          } else {
            if(truthMatrix[0]) {
              //add currTuple to temp relation
            }
          }
        }

      }
    }

    public String getName() {
      return name;
    }
}
