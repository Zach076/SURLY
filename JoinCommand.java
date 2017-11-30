/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Join command for surly database
*/
import java.util.LinkedList;

public class JoinCommand extends BaseCommand {
  private static final SurlyDatabase database = SurlyDatabase.getInstance();
  private final String name = "JOIN";
  public void run(String params) {
    //System.out.println(params);
    String[] tokens = params.trim().split(",\\s|;|\\s");
    Relation[] relationsToJoin = parseRelations(tokens);
    String[] joiningAttributes = parseAttributesToJoinOn(tokens); //check these
    String tempRelName = tokens[0];
    Relation tempRelation = createTemporaryRelation(tempRelName, joiningAttributes, relationsToJoin);
    populateTempRelation(relationsToJoin, tempRelation, joiningAttributes);
  }
  public String getName() {
      return name;
  }

  private Relation[] parseRelations(String[] tokens) {
    Relation[] relationsToJoin = new Relation[2];
    int onIndex = 5;
    int startofAttributes = 3;
    for (int i = startofAttributes; i < onIndex; i++) {
      int index = database.findRelation(tokens[i]);
      if (index != -1) {
        relationsToJoin[i - startofAttributes] = database.getRelations().get(index);
      } else {
        System.out.println("Can't find relation \'" + tokens[i] + "\' to join on");
      }
    }
    return relationsToJoin;
  }

  private String[] parseAttributesToJoinOn(String[] tokens) {
    String[] joiningAttributes = new String[2];
    int firstAttribute = 6;
    int secondAttribute = 8;
    joiningAttributes[0] = tokens[firstAttribute];
    joiningAttributes[1] = tokens[secondAttribute];
    return joiningAttributes;
  }
  private Relation createTemporaryRelation(String newRelName, String[] joiningAttributes, Relation[] relationsToJoin) {
    String attributeString = buildAttributeString(joiningAttributes[1], relationsToJoin);
    //System.out.println("RELATION " + newRelName + " " + attributeString + ";");
    database.runBasicCommand("RELATION " + newRelName + " " + attributeString + ";");
    int index = database.findRelation(newRelName);
    if (index == -1) {
      System.out.println("Trying to find a temporary relation but \'" + newRelName + "\' doesn't exist");
    } else {
      return database.getRelations().get(index);
    }
    return null;
  }

  private String buildAttributeString(String attributeToIgnore, Relation[] relations) {
    StringBuilder sb = new StringBuilder();
    sb.append("(");
    for (int i = 0; i < relations.length; i++) {
      LinkedList<DomainNode> oldDomain = relations[i].getDomain();

      for (int j = 0; j < oldDomain.size(); j++) {
        if (!oldDomain.get(j).getName().equals(attributeToIgnore)) {
          sb.append(oldDomain.get(j).getName() + " ");
          sb.append(oldDomain.get(j).getDatatype() + " ");
          sb.append(oldDomain.get(j).getMaxLen());
          if (relations.length - i != 1) {
            sb.append(", ");
          }
        }
      }
    }
    sb.append(")");
    return sb.toString();
  }

  private boolean populateTempRelation(Relation[] joinedRels, Relation tempRel, String[] joiningAttributes) {
    String[] tempAttributes = getTempAttributes(tempRel);
    String tempName = tempRel.getName();
    int srcIndex = 0;

    LinkedList<Tuple> firstRelTuples = joinedRels[0].getTuples();
    LinkedList<Tuple> secondRelTuples = joinedRels[1].getTuples();
    int[] joiningIndices = findJoiningIndices(joinedRels, joiningAttributes);
    for (int i = 0; i < firstRelTuples.size(); i++) {
      String joiningValue1 = firstRelTuples.get(i).getAttrib(srcIndex).getValue();
      for (int j = 0; j < secondRelTuples.size(); j ++) {
        String joiningValue2 = secondRelTuples.get(i).getAttrib(srcIndex).getValue();
        if (joiningValue1.equals(joiningValue2)) {
          StringBuilder attributes = new StringBuilder();
          for (int k = 0; k < tempAttributes.length; k++) {
            String value = "";
            if (joinedRels[0].findDomainNode(tempAttributes[k]) != -1) {
              srcIndex = joinedRels[0].findDomainNode(tempAttributes[k]);
              value = firstRelTuples.get(i).getAttrib(srcIndex).getValue();
            } else if (joinedRels[1].findDomainNode(tempAttributes[k]) != -1) {
              srcIndex = joinedRels[1].findDomainNode(tempAttributes[k]);
              value = secondRelTuples.get(i).getAttrib(srcIndex).getValue();
            } else {
              return false;
            }
            attributes.append("\'" + value + "\' ");
          }
          System.out.println("INSERT " + tempName + " " + attributes.toString());
          database.runBasicCommand("INSERT " + tempName + " " + attributes.toString());
        }
      }
    }
    return true;
  }

  private String[] getTempAttributes(Relation tempRel) {
    String[] attributes = new String[tempRel.getDomain().size()];
    for (int i = 0; i < attributes.length; i++) {
      attributes[i] = tempRel.getDomain().get(i).getName();
    }
    return attributes;
  }

  private int[] findJoiningIndices(Relation[] joinedRels, String[] joiningAttributes) {
    int[] indices = new int[joinedRels.length];
    for (int i = 0; i < joinedRels.length; i++) {
      indices[i] = joinedRels[i].findDomainNode(joiningAttributes[i]);
    }
    return indices;
  }
}
