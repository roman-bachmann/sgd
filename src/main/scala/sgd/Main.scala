package sgd

import sgd.Constants
import sgd.IngestData

object Main {

  def main(args: Array[String]) {

    println(Constants.discoveryPrefix())

    val dataPath = "/Users/lia/work/study/epfl/systemsForDataScience/sgd/resources/rcv1rcv2aminigoutte/EN/Index_EN-EN" // args(0)

    val (labels, features) = IngestData(dataPath)

    println(labels.length)


    println("Hello guys!")
  }
}
