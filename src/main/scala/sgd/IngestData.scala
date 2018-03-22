package sgd


object IngestData {

  def getFeatures(row: Array[String]): Map[Int, Double] = {
    val splits = row.map(_.split(":"))

    return splits.map(x => (x(0).toInt, x(1).toDouble)).toMap
  }


  def treatData(buff: scala.io.BufferedSource): (Vector[String], Vector[Map[Int, Double]]) = {
    val rawData = buff.getLines.toVector.map(x => x.split(" "))
    val labels = rawData.map(_.head) // not sure if they are the labels
    val features = rawData.map(_.tail).map(getFeatures)

    return (labels, features)
  }


  def apply(path: String): (Vector[String], Vector[Map[Int, Double]]) = {
    val buff = scala.io.Source.fromFile(path)

    return treatData(buff)
  }

}