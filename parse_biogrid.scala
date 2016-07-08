/**
  * Created by dballard on 7/7/16.
  */

 //scala script therefore no main class

val sourceFile = io.Source.fromFile("/Users/dballard/Documents/Files/data/biogrid/temp.txt")
var nodes = Set[(String, String)]()
var relationships = Set[(String, String)]()

for (line <- sourceFile.getLines.drop(1)){
  val cols = line.split("\\t").map(_.trim)
  println(s"${cols(1)},${cols(7)},${cols(2)},${cols(8)}")
  nodes += ((cols(1),cols(7)))
  nodes += ((cols(2),cols(8)))
  relationships += ((cols(1), cols(2)))
}

println(nodes.toString())


for(node <- nodes.toIterator){
  println(node._1 + "," + node._2)
}

import java.io._

val printW = new PrintWriter(new File("/Users/dballard/Documents/Files/data/biogrid/nodes.txt"))
//printW.write("id:ID(GeneID),:LABEL,Symbol\n")
printW.write("GeneID:ID,Symbol\n")
for(node <- nodes.toIterator){
  println(node._1 + "," + node._2)
  printW.write(node._1 + ",\"" + node._2 + "\"\n")
}
printW.close()

val printR = new PrintWriter(new File("/Users/dballard/Documents/Files/data/biogrid/relationship.txt"))
printR.write(":START_ID,:END_ID,:TYPE\n")
for(relationship <- relationships.toIterator){
  println(relationship._1 + "," + relationship._2)
  printR.write(relationship._1 + "," + relationship._2 + ",\"Interaction\"\n")
}
printR.close()

/*

// load data with the command below
mac-5:biogrid dballard$ /usr/local/Cellar/neo4j/3.0.3/bin/neo4j-import --into /usr/local/Cellar/neo4j/3.0.3/libexec/data/databases/biogrid.db --nodes /Users/dballard/Documents/Files/data/biogrid/nodes.txt --relationships /Users/dballard/Documents/Files/data/biogrid/relationship.txt

// update the neo4j.conf property to new database
dbms.active_database=biogrid.db
*/
