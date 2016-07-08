/**
  * Created by dballard on 7/7/16.
  */

 //scala script therefore no main class

//val sourceFile = io.Source.fromFile("/Users/dballard/Documents/Files/data/biogrid/temp.txt")
val sourceFile = io.Source.fromFile("/Users/dballard/Documents/Files/data/biogrid/BIOGRID-ALL-3.4.137.tab2.txt")
var nodes = Set[(String, String, String)]()
var relationships = Set[(String, String, String)]()

for (line <- sourceFile.getLines.drop(1)){
  val cols = line.split("\\t").map(_.trim)
  println(s"${cols(1)},${cols(7)},${cols(2)},${cols(8)}")
  if (!((cols(1) == "-")||(cols(2)=="-"))) {
    nodes += ((cols(1), cols(7), cols(15)))
    nodes += ((cols(2), cols(8), cols(16)))
    relationships += ((cols(1), cols(2), cols(12)))
  }
}

println(nodes.toString())


for(node <- nodes.toIterator){
  println(node._1 + "," + node._2)
}

import java.io._

val printW = new PrintWriter(new File("/Users/dballard/Documents/Files/data/biogrid/nodes_label.txt"))
//printW.write("id:ID(GeneID),:LABEL,Symbol\n")
printW.write("GeneID:ID,Symbol,:LABEL,Organism\n")
for(node <- nodes.toIterator){
  println(node._1 + ",\"" + node._2 + "\"," + node._3 +"\n")
  printW.write(node._1 + ",\"" + node._2 + "\"," + node._3 +"\n")
}
printW.close()

val printR = new PrintWriter(new File("/Users/dballard/Documents/Files/data/biogrid/relationship_label.txt"))
printR.write(":START_ID,:END_ID,:TYPE\n")
for(relationship <- relationships.toIterator){
  println(relationship._1 + "," + relationship._2 + "," + relationship._3 + "\n")
  printR.write(relationship._1 + "," + relationship._2 + ",\"" + relationship._3 + "\"\n")
}
printR.close()

/*

// load data with the command below
mac-5:biogrid dballard$ /usr/local/Cellar/neo4j/3.0.3/bin/neo4j-import --into /usr/local/Cellar/neo4j/3.0.3/libexec/data/databases/biogrid.db --nodes /Users/dballard/Documents/Files/data/biogrid/nodes.txt --relationships /Users/dballard/Documents/Files/data/biogrid/relationship.txt

/usr/local/Cellar/neo4j/3.0.3/bin/neo4j-import --into /usr/local/Cellar/neo4j/3.0.3/libexec/data/databases/biogrid.db --nodes /Users/dballard/Documents/Files/data/biogrid/nodes_label.txt --relationships /Users/dballard/Documents/Files/data/biogrid/relationship_label.txt
// update the neo4j.conf property to new database
dbms.active_database=biogrid.db

/usr/local/Cellar/neo4j/3.0.3/bin/neo4j console
*/
