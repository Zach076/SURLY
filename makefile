JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Main.java \
	SurlyDatabase.java \
	SurlyParser.java \
	Relation.java \
	Tuple.java \
	Attribute.java \
	ICommand.java \
	BaseCommand.java \
	RelationCommand.java \
	InsertCommand.java \
	DomainNode.java \
	PrintCommand.java \
	DestroyCommand.java \
	DeleteCommand.java \
	SelectCommand.java \
	ProjectCommand.java \
	JoinCommand.java

MAIN = Main
default: classes

classes: $(CLASSES:.java=.class)

run : classes
	$(JVM) $(MAIN).class
