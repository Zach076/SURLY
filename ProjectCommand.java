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
		//System.out.println(params);
		String[] tokens = params.trim().split("\\s\\(|\\)|,\\s|;|\\s");


		Relation projectedRelation = findFromRelation(tokens);
		String[] attributes = parseAttributesToProject(tokens);
		String tempRelationName = tokens[0];
		if (projectedRelation != null) {
			Relation tempRelation	= createTemporaryRelation(tempRelationName, attributes, projectedRelation);
			if (tempRelation != null) {
				populateTempRelation(tempRelation, projectedRelation, attributes);
			}
		}
		//	for (int i = 0; i < tokens.length; i++) {
		//		System.out.println(tokens[i]);
		//	}
		//	System.out.println("***** Projected attributes ****");
		//	for (int i = 0; i < attributes.length; i++) {
		//		System.out.println(attributes[i]);
		//	}

	}
	public String getName() {
			return name;
	}

	private Relation findFromRelation(String[] tokens) {
		int fromIndex = findFrom(tokens);
		if (fromIndex == -1) {
			System.out.println("Project is missing a FROM statement");

		} else {
			String relationName = tokens[fromIndex + 1];
			int index = database.findRelation(relationName);
			if (index == -1) {
				System.out.println("Trying to PROJECT FROM \'" + relationName + "\' which doesn't exist");
			} else {
				return database.getRelations().get(index);
			}
		}
		return null;
	}
	private int findFrom(String[] tokens) {
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].equals("FROM")) {
				return i;
			}
		}
		return -1;
	}

	private String[] parseAttributesToProject(String[] tokens) {
		String[] attributes = new String[countAttributes(tokens)];

		int fromIndex = findFrom(tokens);
		int startofAttributes = 3;
		for (int i = startofAttributes; i < fromIndex; i++) {
			attributes[i - startofAttributes] = tokens[i];
		}
		return attributes;
	}

	private int countAttributes(String[] tokens) {
		int count = 0;
		int fromIndex = findFrom(tokens);
		int startofAttributes = 3;
		for (int i = startofAttributes; i < fromIndex; i++) {
			count++;
		}
		return count;
	}

	private Relation createTemporaryRelation(String relationName, String[] attributes, Relation oldRel) {
		String attributeString = buildAttributeString(attributes, oldRel);
		database.runBasicCommand("RELATION " + relationName + " " + attributeString + ";");
		int index = database.findRelation(relationName);
		if (index == -1) {
			System.out.println("Trying to find a temporary relation but \'" + relationName + "\' doesn't exist");
		} else {
			return database.getRelations().get(index);
		}
		return null;
	}

	private String buildAttributeString(String[] attributes, Relation relation) {
		LinkedList<DomainNode> oldDomain = relation.getDomain();
		StringBuilder sb = new StringBuilder();
		sb.append("(");

		for (int i = 0; i < attributes.length; i++) {
			for (int j = 0; j < oldDomain.size(); j++) {
				if (oldDomain.get(j).getName().equals(attributes[i])) {
					sb.append(oldDomain.get(j).getName() + " ");
					sb.append(oldDomain.get(j).getDatatype() + " ");
					sb.append(oldDomain.get(j).getMaxLen());
					if (attributes.length - i != 1) {
						sb.append(", ");
					}
				}
			}
		}
		sb.append(")");
		return sb.toString();
	}

	private void populateTempRelation(Relation tempRel, Relation oldRel, String[] attributes) {
		for (int i = 0; i < attributes.length; i++) {
			i++;
		}
	}

}
