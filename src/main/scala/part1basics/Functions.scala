package part1basics

import scala.annotation.tailrec

object Functions {

  // Stack recursion
  def factorial(n: Int): Int = {
    if n < 0 then 0
    else if n == 0 then 1
    else n * factorial(n-1)
  }

  // Stack recursion
  def fibonacci(n: Int): Int = {
    if n <= 0 then 0
    else if n == 1 || n == 2 then 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  // Tail-recursive version of fibonacci. Counts down from n
  def fib(n: Int): BigInt = {
    @tailrec
    def fibTailRec(n: Int, a: BigInt = 0, b: BigInt = 1): BigInt = {
      if n <= 0 then a
      else fibTailRec(n - 1, b, a+ b)
    }
    fibTailRec(n)
  }

  def isPrime(n: Int): Boolean = {
    // Returns true if n is not divisible by each of the successive
    // integers t, t-1, t-2, etc. all the way down to 1.
    // For example, if n = 11, check whether it's divisible by 5, 4, 3, 2, 1
    @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1)          true // If we have checked all the way down to 1, that means it's prime
      else if (n % t == 0) false
      else                 isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  //---------------------------------------------------------------------------
  // This version has identical logic as the previous one and is also
  // tail-recursive despite the occurrence of && in the tail-recursive call,
  // because && is short-circuiting: the recursive call is made only if
  // n % t != 0 is true.

  def isPrime_v2(n: Int): Boolean = {
    // Returns true if n is not divisible by each of the successive
    // integers t, t-1, t-2, etc. all the way down to 1.
    // For example, if n = 11, check whether it's divisible by 5, 4, 3, 2, 1
    @tailrec
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true // If we have checked all the way down to 1, that means it's prime
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
  }

  val fact4 = factorial(4) //24
  val fib8 = fibonacci(8) // 21
  val fib9 = fib(9) // 34
  val prime11 = isPrime(11) // true
  val prime24 = isPrime(24) // false

  def main(args: Array[String]): Unit = {
    println(fact4)
    println(fib8)
    println(fib9)
    println(prime11)
    println(prime24)
  }
}
