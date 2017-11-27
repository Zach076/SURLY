/*
  Stephen Hyde-Donohue and Zachary Richardson
  SURLY 1
  WWU CSCI 330
  Fall 2017
  Project command for surly database
*/

import java.util.LinkedList;

public class ProjectCommand extends BaseCommand {
    private static final SurlyDatabase database = SurlyDatabase.getInstance();
    private final String name = "PROJECT";
    public void run(String params) {
      System.out.println(params);
      /* B. PROJECT
       *  The interpreter, upon recognizing a 'PROJECT' command, goes on to read the
       *  projection's arguments and calls: RELNUMNEW = PROJECT ATTRIBLISTPTR FROM
       *  RELNUMOLD;
       *  The PROJECT function should do the following:
       *  1. Create a new (temporary) relation, replete with attribute names and formats
       *  (as in the RELATION statement).
       *  2. Create a new (temporary) secondary index with storage structure TREE or HASH,
       *  you decide. (Like the INDEX statement).  Both structures are initially empty.
       *  3. Now, for each tuple T in RELATION.ENTRYPOINT (RELNUMOLD) do; INSERT (RELNUMNEW,
       *  MAKEPROJECTEDTUPLE (T, ATTRIBLISTPTR))
       *  4. If RELATION.TEMPORARY? (RELNUMOLD) = true then DESTROY(RELNUMOLD) and put
       *  RELNUMOLD on the RELATION available space list.
       *  The secondary index takes care of
       *  1. Making sure that the new relation has no duplicates.
       *  2. Making insertion relatively inexpensive.
       *  The most obvious MAKEPROJECTEDTUPLE is order (M*N/2) where N =
       *  the number of attributes in RELATION (RELNUMOLD) and M = the number of
       *  attributes in the Key list. You can do better.
       *  Example.
       *     P1 = PROJECT CREDITS, CNUM FROM COURSE;
       */
       String[] tokens = params.trim().split("\\s\\(|\\)|,\\s|;|\\s");
       String newRelationName = tokens[0];
       for (int i = 0; i < tokens.length; i++) {
         System.out.println(tokens[i]);
       }
       createTemporaryRelation(newRelationName);
    }
    public String getName() {
        return name;
    }

    public Relation createTemporaryRelation(String name) {
      Relation temp = new Relation(name);
      return temp;
    }
}
