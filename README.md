# dsa
Everything from basics

Maven
-----
To compile with Maven:

  mvn compile

To build a packaged JAR (skip tests):

  mvn -B -DskipTests package

To run the BinaryTree main class using the exec plugin:

  mvn exec:java

(If needed without plugin config: mvn exec:java -Dexec.mainClass=BinaryTree)

Notes:
- Java 17 is targeted by the pom.xml.
- Source files are under src/main/java.
