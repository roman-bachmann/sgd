package sgd

import sgd.Settings
import sgd.IngestData

object Main {

  def main(args: Array[String]) {

    val dataPaths = Settings.discoveryDataPaths().filter(_ contains "EN-EN")
    //.take(2) // I get java.lang.OutOfMemoryError: GC overhead limit exceeded if I run the entire dataset

    val (labels, features) = IngestData(dataPaths)

    println("Dataset has " + labels.length + " rows. Have fun!")

    // Map labels to {-1, 1}
    val targets = features.map(x => if (x == "A") 1 else -1)

    // initialize new SVM object
    val svm = new SVM(features, targets, 1, 10)
    // train svm
    svm.fit()

    println("Classification accuracy:",
      (svm.classification(svm.df), targets).zipped.count(i => i._1 == i._2).toDouble / svm.df.length)
  }
}
