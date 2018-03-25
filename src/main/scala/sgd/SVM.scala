package sgd

/**
  * @param x Feature data.
  * @param labels Binary int labels (-1 or 1)
  * @param eta Learning rate
  * @param epochs No. of training epochs
  */
class SVM(x: DataFrame[Double], labels: Array[Int], eta: Double=1, epochs: Int=10) {

  // Add a bias term to the data.
  def prepare(x: DataFrame[Double]): DataFrame[Double] = x.map(_ + (0 -> 1.0))

  // Prepared data.
  val df: DataFrame[Double] = prepare(x)

  // Get dimension of data
  val dim: Int = df.flatMap(_.keys).max + 1

  // Weights initialization.
  var w : Array[Double] = (for (_ <- 1 to dim) yield 0.0).toArray


  def fit(): Unit = {
    // Will only be called if classification is wrong.
    def gradient(w: Array[Double], data: SparseArray[Double], label: Int, epoch: Int): Array[Double] = {
      w.zipWithIndex.map(wi => wi._1 + eta * (data.getOrElse[Double](wi._2, 0.0) * label - 2 * (1 / epoch) * wi._1))
    }

    def regularizationGradient(w: Array[Double], label: Int, epoch: Int): Array[Double] = {
      w.map(i => i + eta * (-2  * (1 / epoch) * i))
    }

    // Misclassification treshold.
    def misClassification(x: SparseArray[Double], w: Array[Double], label: Int): Boolean = {
      dotProduct(x, w) * label < 1
    }

    def trainOneEpoch(w: Array[Double], x: DataFrame[Double], labels: Array[Int], epoch: Int): Array[Double] = (x, labels) match {
      // If classification is wrong. Update weights with loss gradient
      case (Array(xh, xs@_*), Array(lh, ls@_*)) if misClassification(xh, w, lh) => {
        trainOneEpoch(gradient(w, xh, lh, epoch), xs.toArray, ls.toArray, epoch)
      }
      // If classification is correct: update weights with regularizer gradient
      case (Array(_, xs@_*), Array(lh, ls@_*)) => {
        trainOneEpoch(regularizationGradient(w, lh, epoch), xs.toArray, ls.toArray, epoch)
      }
      case _ => w
    }

    def trainEpochs(w: Array[Double], epochs: Int, epochCount: Int = 1): Array[Double] = epochs match {
      case 0 => w
      case _ => println("Epoch " + epochCount); trainEpochs(trainOneEpoch(w, df, labels, epochCount), epochs - 1, epochCount + 1)
    }

    // Update weights
    w = trainEpochs(w, epochs)
  }

  def classification(x: DataFrame[Double], w: Array[Double] = w): Array[Int] = x.map(dotProduct(_, w).signum)
}

object dotProduct {
  def apply(x: SparseArray[Double], w: Array[Double]): Double = x.map(v => v._2 * w(v._1)).sum
}