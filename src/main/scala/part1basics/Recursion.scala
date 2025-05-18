package part1basics

import scala.annotation.tailrec

object Recursion {

  // Stack recursive version; a and b are inclusive
  def sumNumbersBetween(a: Int, b: Int): Int =
    if (a > b) 0
    else a + sumNumbersBetween(a + 1, b) // corresponds to 'a + acc' in the tail recursive version

  //---------------------------------------------------------------------------
  // Tail recursive version using helper function. Count upwards from a until b is reached
  def sumNumBet(a: Int, b: Int): Int = {
    @tailrec
    def sumNumTailrec(m: Int, acc: Int): Int =
      if (m > b) acc
      else sumNumTailrec(m + 1, acc + m)

    sumNumTailrec(a, 0)
  }

  //---------------------------------------------------------------------------
  // Shorter tail recursive version. Eliminates helper function by using
  // default value for accumulator.
  // Count upwards from a until b is reached

  @tailrec
  def sumNumBetweenTailRec(a: Int, b: Int, acc: Int = 0): Int = {
      if (a > b) acc
      else sumNumBetweenTailRec(a + 1, b, acc + a)
  }

  val sum3to10: Int = sumNumbersBetween(3, 10) // 52
  val sum3to10b: Int = sumNumBet(3, 10) // 52
  val sum3to10bt: Int = sumNumBetweenTailRec(3, 10) // 52

  //---------------------------------------------------------------------------
  // Concatanate a string n times
  def concatN(str: String, n: Int): String =
    @tailrec // Pass only those params that keep changing at every call. So no need to pass str
    def concatTailrec(m: Int, acc: String): String =
      if m <= 0 then acc
      else concatTailrec(m - 1, acc + str)

    concatTailrec(n, "")
  end concatN

  val scala5: String = concatN("Scala", 5)

  //---------------------------------------------------------------------------
  def fibonacci(n: Int): Int = {
    @tailrec  // The tail rec function has two accumulators: last and prev. i is the iteration counter
    def fibTailrec(i: Int, last: Int, prev: Int): Int =
      if (i >= n) last
      else fibTailrec(i + 1, last + prev, last)

    fibTailrec(1, 1, 0)
  }

  val fib: Int = fibonacci(5)

  //---------------------------------------------------------------------------
  def main(args: Array[String]): Unit = {
    println (sum3to10)
    println(sum3to10b)
    println(sum3to10bt)
    // println(scala5)
    println(fib)
  }
}
