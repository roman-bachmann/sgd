package sgd


object IngestData {

  def getFeatures(row: Array[String]): SparseArray[Double] = {
    val splits = row.map(_.split(":"))

    return splits.map(x => (x(0).toInt, x(1).toDouble)).toMap
  }


  def treatData(buff: scala.io.BufferedSource): (Array[String], DataFrame[Double]) = {
    val rawData = buff.getLines.toArray.map(x => x.split(" "))
    val labels = rawData.map(_.head) // not sure if they are the labels
    val features = rawData.map(_.tail).map(getFeatures)

    return (labels, features)
  }


  def apply(paths: Array[String]): (Array[String], DataFrame[Double]) = {
    val buffs = paths.map(x => scala.io.Source.fromFile(x))
    val datas = buffs.map(treatData)

    val labels = datas.flatMap(x => x._1)
    val features = datas.flatMap(x =>  x._2)

    return (labels, features)
  }
}
