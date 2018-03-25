package object sgd {
  type SparseArray[A] = Map[Int, A]
  type DataFrame[A] = Array[SparseArray[A]]
}
