import scala.io._

val filename = "/Users/dballard/Documents/Files/data/biogrid/temp.txt"
val biogrid = Source.fromFile(filename).getLines.toList

val myfile = biogrid.filterNot(_.isEmpty).map { line =>
  (line.toList).filter(e => e != ' ')
}

println(myfile)




//val biogrid = Source.fromFile(filename).mkString
//println(biogrid.take(5))
