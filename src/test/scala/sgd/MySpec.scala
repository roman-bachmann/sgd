package sgd

import org.scalatest._

class MySpec extends FlatSpec {

  "1 + 1" should "be equals to 2" in {
    assert(1 + 1 === 2)
  }

  it should "not be equals to 3" in {
    assert(1 + 1 != 3)
  }
}