package sgd

import sgd.Settings
import sgd.IngestData

object Main {

  def main(args: Array[String]) {

    val dataPaths = Settings.discoveryDataPaths().take(2) // I get java.lang.OutOfMemoryError: GC overhead limit exceeded if I run the entire dataset

    val (labels, features) = IngestData(dataPaths)

    println("Dataset has " + labels.length + " rows. Have fun!")
  }
}
